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
	"@/assets/images/sol.png",
	"@/assets/images/moli.png",
	"@/assets/images/rino.png",
	"@/assets/images/shoo.png",
	"@/assets/images/doremi.png",
	"@/assets/images/lululala.png",
];

function GetDepartmentName(index) {
	return departmentList[index];
}

function GetJobName(index) {
	return jobList[index];
}

function GetProfileImage(index) {
	return profileImageList[index];
}

export default { GetDepartmentName, GetJobName, GetProfileImage };
