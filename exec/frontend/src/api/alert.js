import Swal from "sweetalert2";

function success(text) {
	return Swal.fire({
		position: "center",
		icon: "success",
		text: text,
		showConfirmButton: false,
		timer: 1500,
	});
}

function error(text) {
	return Swal.fire({
		position: "center",
		icon: "error",
		text: text,
		showConfirmButton: false,
		timer: 1500,
	});
}

function warning(text) {
	return Swal.fire({
		position: "center",
		icon: "warning",
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

export default { success, error, warning, confirm, inputtext, selectBranch };
