package com.ssafy.checksource.service;

import com.google.gson.Gson;
import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.*;
import com.ssafy.checksource.model.entity.*;
import com.ssafy.checksource.model.key.GitLabUserKey;
import com.ssafy.checksource.model.repository.ProjectRepository;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import com.ssafy.checksource.model.repository.GitHubRepository;
import com.ssafy.checksource.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sound.midi.Soundbank;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class GithubService {
    @Autowired
    RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper = new ModelMapper();
    private final GitHubRepository gitHubRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AnalyzeService analyzeService;
    private final String baseURL = "https://api.github.com/";

    //github 계정 연동 체크
    public GitHubConnectDTO githubConnect(String username, String token, String personalAccessToken) {
//        GithubUser githubUser = gitHubRepository.findById(githubId).orElseThrow(() -> new IllegalArgumentException("no github data"));
//        String accessToken = githubUser.getPersonalAccessToken();
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);

        String url = baseURL + "users/";
        url += username;
        GitHubConnectDTO gitHubConnectDTO = new GitHubConnectDTO();
        gitHubConnectDTO.setFlag(false);
        //헤더에 토큰 담기
        String s = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", personalAccessToken);
        HttpEntity entity = new HttpEntity(headers);
        try {
            //api요청
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            GithubUser githubUser = new GithubUser();
            githubUser.setUser(user);
            githubUser.setUsername(username);
            githubUser.setPersonalAccessToken(personalAccessToken);
            GithubUser githubSaveUser = gitHubRepository.save(githubUser);

            gitHubConnectDTO.setFlag(true);
            gitHubConnectDTO.setUserId(githubSaveUser.getUser().getUserId());
            gitHubConnectDTO.setGithubId(githubSaveUser.getGithubId());
            gitHubConnectDTO.setUsername(username);
            return gitHubConnectDTO;
        } catch (HttpClientErrorException e) {
            return gitHubConnectDTO;
        }
    }

    //github 계정  연결 끊기
    public void deleteGitConnect(Long githubId, String token) {
        Optional<GithubUser> githubUser = Optional.ofNullable(gitHubRepository.findById(githubId).orElseThrow(() -> new IllegalArgumentException("no github data")));
        gitHubRepository.delete(githubUser.get());
    }

    //브랜치 가져오기
    public List<GithubBranchesDTO> getBranches(String token, String username, String repo, Long githubId) {
        GithubUser githubUser = gitHubRepository.findById(githubId)
                .orElseThrow(() -> new IllegalArgumentException("no github data"));
        String url = baseURL + "/repos/" + username + "/" + repo + "/branches";
        List<GithubBranchesDTO> projectBranchesList = new ArrayList<GithubBranchesDTO>();
        String personalAccessToken = githubUser.getPersonalAccessToken();
        // 헤더 담음
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", personalAccessToken);
        HttpEntity entity = new HttpEntity(headers);

        try {
            // api 요청
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Gson gson = new Gson();
            GithubBranchesDTO[] branchList = gson.fromJson(responseEntity.getBody(), GithubBranchesDTO[].class);
            System.out.println(responseEntity.getBody());
            projectBranchesList = Arrays.asList(branchList);

        } catch (HttpClientErrorException e) {
            return projectBranchesList;
        }
        return projectBranchesList;

    }

    //프로젝트 목록 가져오기
    public GitHubProjectListDTO getProjects(String token, Long githubId) {
        // 목록
        GitHubProjectListDTO returnDto = new GitHubProjectListDTO();
        List<GitHubProjectDTO> gitHubProjectList = new ArrayList<GitHubProjectDTO>();

        String userId = jwtTokenProvider.getUserId(token);
        GithubUser gitHub = gitHubRepository.findById(githubId)
                .orElseThrow(() -> new IllegalArgumentException("no github data"));


        String url = baseURL + "/users/" + gitHub.getUsername() + "/repos";
        System.out.println(url);

        String personalAccessToken = gitHub.getPersonalAccessToken();
        System.out.println(personalAccessToken);
        // 헤더 담음
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", personalAccessToken);
        HttpEntity entity = new HttpEntity(headers);
        List<GitHubProjectDTO> gitHubProjectlist = new ArrayList<GitHubProjectDTO>();
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Gson gson = new Gson();
            GitHubProjectDTO[] gitHubProjectDto = gson.fromJson(responseEntity.getBody(), GitHubProjectDTO[].class);
            gitHubProjectlist = Arrays.asList(gitHubProjectDto);

            // 검증, 비검증으로 나누기
            for (GitHubProjectDTO gitHubProject : gitHubProjectlist) {
                String githubProjectId = gitHubProject.getId();
                Project project = projectRepository.findByGitProjectIdAndGitType(githubProjectId, githubId);
                if (project == null) {// 비검증
                    gitHubProjectList.add(gitHubProject);
                } else {// 검증
                    gitHubProject.setVerified(true);
                    gitHubProjectList.add(gitHubProject);
                }
            }
            returnDto.setAccessflag(true);
            returnDto.setProjectList(gitHubProjectList);
            return returnDto;
        } catch (Exception e) {
            // 유저 토큰 유효하지 않는 경우 - 깃랩 연동 다시 시키기
            returnDto.setAccessflag(false);
            returnDto.setProjectList(gitHubProjectList);
            return returnDto;
        }
    }

    public boolean deleteProject(String token, String projectId, Long githubId) {
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        Project project = projectRepository.findByGitProjectIdAndGitType(projectId, githubId);
        if (user.getDepart().getDepartId() == project.getDepart().getDepartId()) {
            projectRepository.delete(project);
            return true;
        }
        return false;
    }

    public boolean addProject(String token, List<GitHubProjectDTO> projectList, Long githubId, String sha) throws Exception {
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        Depart depart = user.getDepart();

        // 검증할 프로젝트 리스트
        List<AnalyProjectListDTO> analyProjectList = new ArrayList<AnalyProjectListDTO>();
        GithubUser gitHub = gitHubRepository.findById(githubId)
                .orElseThrow(() -> new IllegalArgumentException("no github Id in database"));

        // 프로젝트별 검증
        System.out.println(projectList.size());
        for (GitHubProjectDTO gitHubProjectDTO : projectList) {
            AnalyProjectListDTO analyProjectListDto = new AnalyProjectListDTO();
            String githubProjectId = gitHubProjectDTO.getId();
            String projectName = gitHubProjectDTO.getName();
            String branch = gitHubProjectDTO.getBranch();
            String htmlUrl = gitHubProjectDTO.getHtml_url();


            // 1. repositoryTree 전체 리스트로 가져오기
            String url = baseURL + "repos/" + gitHub.getUsername() + "/" + projectName + "/git/trees/" + sha + "?recursive=true";
            List<GithubRepoTreeDTO> githubRepoTreeDTOList = new ArrayList<GithubRepoTreeDTO>();
            GithubRepoPathDTO githubRepoPathDTO = new GithubRepoPathDTO();
            // 헤더 담음
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.set("Authorization", gitHub.getPersonalAccessToken());
            HttpEntity entity = new HttpEntity(headers);
            try {// api - RepositoryTree 리스트 받아옴
                ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                        String.class);
                Gson gson = new Gson();
                GithubRepoTreeDTO githubRepoTreeDTOS = gson.fromJson(responseEntity.getBody(),
                        GithubRepoTreeDTO.class);
                githubRepoTreeDTOList = Arrays.asList(githubRepoTreeDTOS);
            } catch (HttpClientErrorException e) {
                // 토큰이 유효하지 않을 경우 401
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    return false;
                }
                // repository tree가 없을 경우 404 - nocontents
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    // 검증할 필요가 없으므로 검증완료 후 저장
                    // 기존꺼 있는지 체크
                    Project existProject = projectRepository.findByGitProjectIdAndGitType(githubProjectId, (long)3);
                    if (existProject != null) { // 기존에 검증한 프로젝트가 있다면 지우기
                        projectRepository.delete(existProject);
                    }
                    Project project = new Project();
                    project.setUser(user);
                    project.setDepart(depart);
                    project.setName(gitHubProjectDTO.getName());
                    project.setGitProjectId(githubProjectId); // gitlabProjectId
                    project.setBranch(gitHubProjectDTO.getBranch());// 브랜치 설정
                    project.setGitType((long)3);// gitType
                    project.setStatus(true);
                    project.setWebUrl(gitHubProjectDTO.getHtml_url());
                    projectRepository.save(project);
                    continue;
                }

            }

            // 2. repositoryTreeList에서 패키지 매니저 파일 리스트 뽑음
            List<GithubRepoPathDTO> packageManageFileList = new ArrayList<GithubRepoPathDTO>();
            for (int i = 0; i < githubRepoTreeDTOList.get(0).getTree().length; i++) {
                String name = githubRepoTreeDTOList.get(0).getTree()[i].getPath();
                if (name.contains("pom.xml") || name.contains("package.json")) {
                    System.out.println(name);
                    githubRepoPathDTO.setSha(githubRepoTreeDTOList.get(0).getSha());
                    githubRepoPathDTO.setUrl(githubRepoTreeDTOList.get(0).getUrl());
                    githubRepoPathDTO.setPath(name);
                    packageManageFileList.add(githubRepoPathDTO);
                }
            }

//            // set
            analyProjectListDto.setGitProjectId(githubProjectId);
            analyProjectListDto.setProjectName(projectName);
            analyProjectListDto.setBranch(branch);
            analyProjectListDto.setWebUrl(htmlUrl);
            analyProjectListDto.setGithubpackageManageFileList(packageManageFileList); // 패키지매니저파일 리스트
            analyProjectList.add(analyProjectListDto);
        } // end project for문

        System.out.println(analyProjectList);

        // 3. 검증할 프로젝트별 패키지매니저 파일 리스트에서 패키지 매니터 파일의 contents 뽑기
        for (AnalyProjectListDTO analyProjectListDto : analyProjectList) {
            List<GithubRepoPathDTO> packageManageFileList = analyProjectListDto.getGithubpackageManageFileList();
            String githubProjectId = analyProjectListDto.getGitProjectId();

//            // 기존꺼 지움
            Project existProject = projectRepository.findByGitProjectIdAndGitType(githubProjectId, (long)3);
            if (existProject != null) { // 기존에 검증한 프로젝트가 있다면
                projectRepository.delete(existProject);
            }
//
            Project project = new Project();
            project.setUser(user);
            project.setDepart(depart);
            project.setName(analyProjectListDto.getProjectName());
            project.setGitProjectId(githubProjectId); // gitlabProjectId
            project.setBranch(analyProjectListDto.getBranch());// 브랜치 설정
            project.setGitType((long)3);// gitType
            project.setStatus(true);
            project.setWebUrl(analyProjectListDto.getWebUrl());
            Project newProject = projectRepository.save(project);
            Long projectId = newProject.getProjectId();

            // 프로젝트별 패키지파일 리스트
            int idx = 0;
            for (GithubRepoPathDTO packageManageFile : packageManageFileList) {
                String path = packageManageFile.getPath();

                // path 경로 url encoding해서 api 요청
                //contents를 얻기위한 api
                String url = baseURL + "repos/" + gitHub.getUsername() + "/" + project.getName() + "/contents//" + path;
                System.out.println(url);
                // 헤더
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_XML);
                headers.set("Authorization", gitHub.getPersonalAccessToken());
                HttpEntity entity = new HttpEntity(headers);
                try {
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                            String.class);
                    Gson gson = new Gson();
                    GithubPackageManageFileDTO packageManageFileDto = gson.fromJson(responseEntity.getBody(),
                            GithubPackageManageFileDTO.class);
                    // contents 뽑음
                    String contents = packageManageFileDto.getContent().replace("\n","");
                    System.out.println(contents);
                    String filePath = packageManageFileDto.getPath();
                    String fileName = packageManageFileDto.getName();
                    // 4. base64 - decoding 등 승환 코드
                    analyzeService.analyze(projectId, fileName, contents, filePath); // 변경

                } catch (HttpClientErrorException e) {
                    // 토큰이 유효하지 않을 경우 401
                    if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}