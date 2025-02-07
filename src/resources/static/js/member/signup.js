document.getElementById("midInput").addEventListener("keyup", validateInputs);
document.getElementById("mpwdInput").addEventListener("keyup", validateInputs);
document
  .getElementById("mpwdInputCheck")
  .addEventListener("keyup", validateInputs);
document.getElementById("mnameInput").addEventListener("keyup", validateInputs);

function validateInputs() {
  const midInput = document.querySelector("#midInput");
  const mpwdInput = document.querySelector("#mpwdInput");
  const mpwdInputCheck = document.querySelector("#mpwdInputCheck");
  const mnameInput = document.querySelector("#mnameInput");

  const mid = midInput.value;
  const mpwd = mpwdInput.value;
  const mpwdCheck = mpwdInputCheck.value;
  const mname = mnameInput.value;

  let isFormValid = true;

  //아이디 체크(길이)
  if (mid.length >= 6 && mid.length <= 20) {
    const checktext1 = document.querySelector(".checktext1");
    const checkbox1 = document.querySelector(".checkbox1");
    checktext1.style.color = "#79CF9F";
    checkbox1.innerHTML = "☑";
  } else {
    const checktext1 = document.querySelector(".checktext1");
    const checkbox1 = document.querySelector(".checkbox1");
    checktext1.style.color = "";
    checkbox1.innerHTML = "☐";
    isFormValid = false;
  }

  //아이디 체크(문자열+숫자)
  if (/[0-9]/.test(mid) && /[a-zA-Z]/.test(mid)) {
    const checktext2 = document.querySelector(".checktext2");
    const checkbox2 = document.querySelector(".checkbox2");
    checktext2.style.color = "#79CF9F";
    checkbox2.innerHTML = "☑";
  } else {
    const checktext2 = document.querySelector(".checktext2");
    const checkbox2 = document.querySelector(".checkbox2");
    checktext2.style.color = "";
    checkbox2.innerHTML = "☐";
    isFormValid = false;
  }

  //비밀번호 체크(길이)
  if (mpwd.length >= 6 && mpwd.length <= 20) {
    const checktext3 = document.querySelector(".checktext3");
    const checkbox3 = document.querySelector(".checkbox3");
    checktext3.style.color = "#79CF9F";
    checkbox3.innerHTML = "☑";
  } else {
    const checktext3 = document.querySelector(".checktext3");
    const checkbox3 = document.querySelector(".checkbox3");
    checktext3.style.color = "";
    checkbox3.innerHTML = "☐";
    isFormValid = false;
  }

  //비밀번호 체크(문자열+숫자)
  if (/[0-9]/.test(mpwd) && /[a-zA-Z]/.test(mpwd)) {
    const checktext4 = document.querySelector(".checktext4");
    const checkbox4 = document.querySelector(".checkbox4");
    checktext4.style.color = "#79CF9F";
    checkbox4.innerHTML = "☑";
  } else {
    const checktext4 = document.querySelector(".checktext4");
    const checkbox4 = document.querySelector(".checkbox4");
    checktext4.style.color = "";
    checkbox4.innerHTML = "☐";
    isFormValid = false;
  }

  //비밀번호 체크(특수문자)
  if (/[!#?]/.test(mpwd)) {
    const checktext5 = document.querySelector(".checktext5");
    const checkbox5 = document.querySelector(".checkbox5");
    checktext5.style.color = "#79CF9F";
    checkbox5.innerHTML = "☑";
  } else {
    const checktext5 = document.querySelector(".checktext5");
    const checkbox5 = document.querySelector(".checkbox5");
    checktext5.style.color = "";
    checkbox5.innerHTML = "☐";
    isFormValid = false;
  }

  //비밀번호 확인
  if (mpwd === mpwdCheck) {
    const checktext6 = document.querySelector(".checktext6");
    const checkbox6 = document.querySelector(".checkbox6");
    checktext6.style.color = "#79CF9F";
    checkbox6.innerHTML = "☑";
  } else {
    const checktext6 = document.querySelector(".checktext6");
    const checkbox6 = document.querySelector(".checkbox6");
    checktext6.style.color = "";
    checkbox6.innerHTML = "☐";
    isFormValid = false;
  }

  //이름 확인
  if (mname.length >= 2 && mname.length <= 20) {
    const checktext7 = document.querySelector(".checktext7");
    const checkbox7 = document.querySelector(".checkbox7");
    checktext7.style.color = "#79CF9F";
    checkbox7.innerHTML = "☑";
  } else {
    const checktext7 = document.querySelector(".checktext7");
    const checkbox7 = document.querySelector(".checkbox7");
    checktext7.style.color = "";
    checkbox7.innerHTML = "☐";
    isFormValid = false;
  }

  // 모든 조건이 충족되면 버튼 활성화
  document.querySelector(".signupBtn").disabled = !isFormValid;
}

document.getElementById("signupForm").addEventListener("submit", (event) => {
  event.preventDefault();

  const midInput = document.querySelector("#midInput");
  const mpwdInput = document.querySelector("#mpwdInput");
  const mpwdInputCheck = document.querySelector("#mpwdInputCheck");
  const mnameInput = document.querySelector("#mnameInput");
  const mphoneInput = document.querySelector("#mphoneInput");

  const mid = midInput.value;
  const mpwd = mpwdInput.value;
  const mpwdCheck = mpwdInputCheck.value;
  const mname = mnameInput.value;
  const mphone = mphoneInput.value;

  // 비밀번호 확인
  if (mpwd !== mpwdCheck) {
    alert("비밀번호가 일치하지 않습니다");
    return;
  }

  const dataObj = { mid, mpwd, mname, mphone };

  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  };

  fetch("/member/signup.do", option)
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        location.href = "/";
        alert("가입등록 완료");
      } else {
        alert("가입 실패");
      }
    })
    .catch((error) => {
      alert("Error: " + error);
    });
});
