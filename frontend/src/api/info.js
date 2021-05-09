/*
  공통된 정보를 모아두기 위한 JS 파일
  
  @version 21-05-07
  @author 남우진
*/

let departmentList = [
	"금융개발부",
	"디지털개발부",
	"글로벌개발부",
	"기관개발부",
	"정보개발부",
	"ICT운영부",
	"ICT기획부",
];

let jobList = ["선임", "수석", "부장"];

let profileImageList = [
	{
		url: require("@/assets/images/sol.png"),
		alt: "sol",
	},
	{
		url: require("@/assets/images/moli.png"),
		alt: "moli",
	},
	{
		url: require("@/assets/images/rino.png"),
		alt: "rino",
	},
	{
		url: require("@/assets/images/shoo.png"),
		alt: "shoo",
	},
	{
		url: require("@/assets/images/doremi.png"),
		alt: "doremi",
	},
	{
		url: require("@/assets/images/lululala.png"),
		alt: "lululala",
	},
];

function GetDepartmentList() {
	return departmentList;
}

function GetDepartmentName(index) {
	return departmentList[index];
}

function GetJobList() {
	return jobList;
}

function GetJobName(index) {
	return jobList[index];
}

function GetProfileImageList() {
	return profileImageList;
}

function GetProfileImage(index) {
	return profileImageList[index];
}

export default {
	GetDepartmentList,
	GetDepartmentName,
	GetJobList,
	GetJobName,
	GetProfileImageList,
	GetProfileImage,
};
