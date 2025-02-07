const signup = () => {
  const midInput = document.querySelector(".midInput");
  const mpwdInput = document.querySelector(".mpwdInput");
  const mpwdInputCheck = document.querySelector(".mpwdInputCheck");
  const mnameInput = document.querySelector(".mnameInput");
  const mphoneInput = document.querySelector(".mphoneInput");

  const mid = midInput.value;
  const pwd = mpwdInput.value;
  const pwdCheck = mpwdInputCheck.value;
  const name = mnameInput.value;
  const phone = mphoneInput.value;

  const dataObj = { mid, mpwd, mname, mphine };

  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  };

  fetch("매핑주소(컨트롤러)", option)
    .then((Response) => Response)
    .then();
};
