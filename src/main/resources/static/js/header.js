// document.addEventListener("DOMContentLoaded", function () {
//   fetch("/header.html")
//     .then((response) => response.text())
//     .then((data) => {
//       document.getElementById("header-placeholder").innerHTML = data;
//     });
// });

const getLoginMid = () => {
  const option = { method: "GET" };
  let html = "";
  fetch("/member/mypage.do", option)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      console.log("로그인상태");
      const signupbar = document.querySelector(".signup");
      const signinbar = document.querySelector(".signin");
      const mypagebar = document.querySelector(".mypage");
      signupbar.style.display = "none";
      signinbar.style.display = "none";
      mypagebar.style.display = "inline";
    })
    .catch((error) => {
      console.log(error);
      console.log("비로그인상태");
    });
};
getLoginMid();
