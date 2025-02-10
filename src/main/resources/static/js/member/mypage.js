const getMyInfo = () => {
  fetch("/member/mypage.do", { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.mid) {
        document.querySelector("#usernameInput").value = data.mid;
        document.querySelector("#nameInput").value = data.mname;
      }
    })
    .catch((e) => {
      console.log(e);
      alert("로그인된 상태가 아닙니다");
      window.location.href = "http://localhost:8080/";
    });
};

document.addEventListener("DOMContentLoaded", getMyInfo);

// const onDelete = ( ) => {
//     let result = confirm('정말 탈퇴 하실건가요?');
//     if( result == false ) { return; }
//     fetch( '/member/delete.do' , { method : "DELETE"} )
//     .then( response => response.json() )
//     .then( data => {
//         if( data == true ){ alert('탈퇴 성공'); location.href='/'; }
//         else{ alert('탈퇴 실패'); }
//     }).catch( e => { console.log(e); })
// }
