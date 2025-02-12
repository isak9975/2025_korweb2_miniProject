console.log("체크1");

const getMyInfo = () => {
  fetch("/member/mypage.do", { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        document.querySelector("#idInput").value = data.mid;
        document.querySelector("#pwdInput").value = data.mpwd;
        document.querySelector("#nameInput").value = data.mname;
        document.querySelector("#phoneInput").value = data.mphone;
      }
    })
    .catch((e) => {
      console.log(e);
      alert("로그인된 상태가 아닙니다");
      window.location.href = "http://localhost:8080/";
    });
};
document.addEventListener("DOMContentLoaded", getMyInfo);
