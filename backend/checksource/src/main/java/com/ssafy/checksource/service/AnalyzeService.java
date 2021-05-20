package com.ssafy.checksource.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.ssafy.checksource.model.dto.LicenseDetailDTO;
import com.ssafy.checksource.model.dto.OpensourceDetailDTO;
import com.ssafy.checksource.model.dto.ParsingDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.OpensourceProject;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.UnmappedOpensource;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.OpensourceProjectRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UnmappedOpensourceRepository;
import com.ssafy.checksource.parser.PackageJsonParser;
import com.ssafy.checksource.parser.PomxmlSaxHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {

	private final ModelMapper modelMapper = new ModelMapper();

	private final OpensourceRepository opensourceRepository;
	private final LicenseOpensourceRepository licenseopensourceRepository;
	private final LicenseRepository licenseRepository;
	private final OpensourceProjectRepository opensourceProjectRepository;
	private final ProjectRepository projectRepository;
	private final UnmappedOpensourceRepository unmappedOpensourceRepository;

	// packageManager = "pom.xml"
	// content = base64 encoding data
	public void analyze(Long projectId, String fileName, String content,String filePath) throws Exception {
		//기존 데이터 삭제
		
		List<Long> list = null;
		byte[] decoded = Base64.getDecoder().decode(content);
		content = new String(decoded, StandardCharsets.UTF_8);
		if (fileName.equals("pom.xml")) {
			list = getOpensourceId(filePath, projectId ,pomxmlParsing(content));
		} else if(fileName.equals("build.gradle")) {
			
		} else if(fileName.equals("package.json")) {
			list = getOpensourceId(filePath, projectId, packageJsonParsing(content));
		}

		//opensourceProject insert
		for (Long opensourceId : list) {
			OpensourceProject opensourceProject = new OpensourceProject();
			Opensource opensource = new Opensource();
			opensource.setOpensourceId(opensourceId);
			Project project = new Project();
			project.setProjectId(projectId);
			opensourceProject.setOpensource(opensource);
			opensourceProject.setProject(project);
			opensourceProject.setPath(filePath);
			opensourceProjectRepository.save(opensourceProject);
		}
		
	}

	// pom.xml Parsing
	// groupId, artifactId, version만 파싱하여 return
	public List<ParsingDTO> pomxmlParsing(String xml) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();
		PomxmlSaxHandler handler = new PomxmlSaxHandler();
		InputSource inputSource = new InputSource(new StringReader(xml));

		parser.parse(inputSource, handler);
		List<ParsingDTO> list = handler.getDepenList();

		return list;
	}
	
	// build.gradle Parsing
	// groupId, artifactId, version만 파싱하여 return
	public List<ParsingDTO> buildGradleParsing(String gradle) throws Exception {
		

		return null;
	}

	// package.json Parsing
	// artifactId, version만 파싱하여 return
	public List<ParsingDTO> packageJsonParsing(String json) throws Exception {
		PackageJsonParser packageJsonParser = new PackageJsonParser(json);
		return packageJsonParser.getDepenList();
	}

	
	
	//groupId와 artifactId로 opensourceId 찾아오기
	public List<Long> getOpensourceId(String filePath, Long projectId, List<ParsingDTO> list){
		List<Long> opensourceList = new ArrayList<Long>();
		for (ParsingDTO dto : list) {
			Opensource ops = opensourceRepository.findByGroupIdAndArtifactId(dto.getGroupId(), dto.getArtifactId());
			if (ops != null) {
				opensourceList.add(ops.getOpensourceId());
			}else { //db에 오픈소스 없는 경우
				UnmappedOpensource unmappedOps = new UnmappedOpensource();
				unmappedOps.setArtifactId(dto.getArtifactId());
				unmappedOps.setGroupId(dto.getGroupId());
				unmappedOps.setVersion(dto.getVersion());
				unmappedOps.setPath(filePath);
				Project project = new Project();
				project.setProjectId(projectId);
				unmappedOps.setProject(project);
				unmappedOpensourceRepository.save(unmappedOps);
			}
		}
		return opensourceList;
	}
}
