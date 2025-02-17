// mypage.js 랑 myInfo.js 는 mypage.html의 js파일인데 나눠진 이유는
// 둘이 붙여두면 에러나서 나눠서 구현했습니다.
// mypage는 화면 구현이랑 데이터가 들어가는 코드
// myInfo는 로그아웃 수정 탈퇴 관련 코드

console.log("mypage.js 실행확인");

const getMyInfo = () => {
  fetch("/member/mypage.do", { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        document.querySelector("#idInput").value = data.mid;
        sessionStorage.setItem("sessionUserId", data.mid); // 세션에 사용자 아이디 저장
        sessionStorage.setItem("sessionUserName", data.mname); // 세션에 사용자 이름 저장
        const pwdInput = document.querySelector("#pwdInput");
        const nameInput = document.querySelector("#nameInput");
        const phoneInput = document.querySelector("#phoneInput");

        pwdInput.value = data.mpwd;
        pwdInput.classList.add("placeholder-color");
        pwdInput.setAttribute("data-initial", data.mpwd);

        nameInput.value = data.mname;
        nameInput.classList.add("placeholder-color");
        nameInput.setAttribute("data-initial", data.mname);

        phoneInput.value = data.mphone;
        phoneInput.classList.add("placeholder-color");
        phoneInput.setAttribute("data-initial", data.mphone);
      }
    })
    .catch((e) => {
      console.log(e);
      alert("로그인된 상태가 아닙니다");
      window.location.href = "http://localhost:8080/";
    });
};
document.addEventListener("DOMContentLoaded", getMyInfo);

document.querySelectorAll("input").forEach((input) => {
  input.addEventListener("focus", () => {
    if (input.classList.contains("placeholder-color")) {
      input.classList.remove("placeholder-color");
      input.classList.add("new-value");
    }
  });

  input.addEventListener("blur", () => {
    const initialValue = input.getAttribute("data-initial");
    if (input.value === "" || input.value === initialValue) {
      input.value = initialValue;
      input.classList.remove("new-value");
      input.classList.add("placeholder-color");
    }
  });
});
