// alert("예외처리 미구현, 스타일 임시용");

// const signin = () => {
//   const midInput = document.querySelector("#midInput");
//   const mpwdInput = document.querySelector("#mpwdInput");

//   const mid = midInput.value;
//   const mpwd = mpwdInput.value;
//   console.log(mid, mpwd);

//   const dataObj = { mid, mpwd };

//   const option = {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(dataObj),
//   };

//   fetch("/member/signin", option)
//     .then((response) => response.json())
//     .then((data) => {
//       if (data) {
//         alert("로그인 성공");
//         // location.href = "";
//       } else {
//         alert("회원정보 없음");
//       }
//     })
//     .catch((error) => {
//       console.log(error);
//     });
// };

document.getElementById("signinForm").addEventListener("submit", (event) => {
  event.preventDefault();

  const midInput = document.querySelector("#midInput");
  const mpwdInput = document.querySelector("#mpwdInput");

  const mid = midInput.value;
  const mpwd = mpwdInput.value;
  console.log(mid, mpwd);

  const dataObj = { mid, mpwd };

  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  };

  fetch("/member/signin.do", option)
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        alert("로그인 성공");
        location.href = "/";
      } else {
        alert("회원정보 없음");
      }
    })
    .catch((error) => {
      console.log(error);
    });
});
