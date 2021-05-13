import Swal from "sweetalert2";

function success(text){
    Swal.fire({
        position: 'center',
        icon: 'success',
        text: text,
        showConfirmButton: false,
        timer: 1500,
      });
}

function inputtext() {
    Swal.fire({
        title: '프로젝트 삭제',
        input: 'text',
        inputLabel: '프로젝트 삭제를 위해 프로젝트명을 기입해주세요.',
        showCancelButton: true
      })
}
    
export default { success, inputtext } ;