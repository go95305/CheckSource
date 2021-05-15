import Swal from "sweetalert2";

function success(text) {
	Swal.fire({
		position: "center",
		icon: "success",
		text: text,
		showConfirmButton: false,
		timer: 1500,
	});
}

function confirm(text) {
	return Swal.fire({
		icon: "warning",
		text: text,
		showDenyButton: true,
		confirmButtonText: `확인`,
		denyButtonText: `취소`,
	});
}

function inputtext(title, inputLabel) {
	return Swal.fire({
		title: title,
		input: "text",
		inputLabel: inputLabel,
		showCancelButton: true,
	});
}

function selectBranch(title, inputPlaceHolder, options) {
	return Swal.fire({
		title: title,
		input: "select",
		showCancelButton: true,
		inputPlaceHolder: inputPlaceHolder,
		inputOptions: options,
	});
}

export default { success, confirm, inputtext, selectBranch };
