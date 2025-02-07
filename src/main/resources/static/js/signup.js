const signup = () => {
  const midInput = document.querySelector(".midInput");
  const mpwdInput = document.querySelector(".mpwdInput");
  const mpwdInputCheck = document.querySelector(".mpwdInputCheck");
  const mnameInput = document.querySelector(".mnameInput");
  const mphoneInput = document.querySelector(".mphoneInput");

  const id = midInput.value;
  const pwd = mpwdInput.value;
  const pwdCheck = mpwdInputCheck.value;
  const name = mnameInput.value;
  const phone = mphoneInput.value;

  const dataObj = { id, pwd, name, phone };

  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  };

  fetch("매핑주소(컨트롤러)", option)
    .then((Response) => Response.json())
    .then((data) => {
      if (data) {
        location.href = "src/main/resources/templates/index.html";
        alert("가입등록 완료");
      } else {
        alert("가입 실패");
      }
    })
    .catch((error) => {
      alert(Error + "에러");
    });
};
