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

function inputtext(title, inputLabel) {
    return Swal.fire({
        title: title,
        input: "text",
        inputLabel: inputLabel,
        showCancelButton: true,
    });
}

export default { success, inputtext };
