// document.addEventListener("DOMContentLoaded", function () {
//   fetch("/header.html")
//     .then((response) => response.text())
//     .then((data) => {
//       document.getElementById("header-placeholder").innerHTML = data;
//     });
// });

// const getLoginMid = () => {
//   //  fetch 함수 활용하여 현재 로그인 상태 체크
//   // 1. fetch option
//   const option = { method: "GET" };
//   // 2. fetch
//   // 1. 출력할 위치 DOM 가져오기
//   let memberBox = document.querySelector(".member_box");
//   let html = "";
//   fetch("/member/mypage.do", option)
//     .then((response) => response.json()) // SyntaxError: Unexpected token 'q', "qweqwe" is not valid JSON
//     //.then( response => response.text() ) // String controller 에서 String 타입으로 반환할 경우에는 .text() 함수로 변환해야한다. // day70 : dto 반환하므로 json()변경
//     .then((data) => {
//       console.log(data);
//       // - 로그인 상태에 따라 버튼 활성화 여부 다르게 표현
//       console.log("로그인상태");
//       //3. 로그아웃 버튼 , 마이페이지 버튼 , 로그인된 아이디 활성화
//       html += `<li class="nav-item">
//                       <a class="nav-link" href="#">

//                           ${data.mid}님
//                       <span class="pointbox"> </span> </a>
//                   </li>
//                   <li class="nav-item">  <a class="nav-link" href="#" onclick="logOut()"> 로그아웃 </a> </li>
//                   <li class="nav-item">  <a class="nav-link" href="/member/info"> 마이페이지 </a> </li>`;
//       // 4. 출력하기
//       member_box.innerHTML = html;
//       // 5. 포인트 지급 불러오기
//     })
//     .catch((error) => {
//       console.log(error);
//       console.log("비로그인상태");
//       // 3. 회원가입 버튼 , 로그인 버튼 활성화
//       html += `<li class="nav-item">  <a class="nav-link" href="/member/signup"> 회원가입 </a> </li>
//                   <li class="nav-item">  <a class="nav-link" href="/member/login"> 로그인 </a> </li>`;
//       // 4. 출력하기
//       member_box.innerHTML = html;
//     });
// }; // f end
