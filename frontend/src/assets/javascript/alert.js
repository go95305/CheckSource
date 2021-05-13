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
    
export default { success } ;