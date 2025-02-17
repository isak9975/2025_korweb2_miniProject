console.log("myinfo.js 실행 확인");

// 로그아웃
const onLogout = () => {
  const option = { method: "GET" };
  fetch("/member/logout.do", option)
    .then((response) => response.json())
    .then((data) => {
      if (data == true) {
        alert("로그아웃");
        location.href = "/";
      }
    })
    .catch((e) => {
      console.log(e);
    });
};

// 정보수정
const onUpdate = () => {
  const mpwd = document.querySelector(".pwdInput").value;
  const mname = document.querySelector(".nameInput").value;
  const mphone = document.querySelector(".phoneInput").value;
  const dataObj = { mpwd, mname, mphone };
  const option = {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dataObj),
  };
  fetch("/member/update.do", option)
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        alert("수정 성공");
        location.href = "";
      } else {
        alert("수정 실패");
      }
    })
    .catch((e) => {
      console.log(e);
    });
};

// 회원탈퇴
const onDelete = () => {
  const result = confirm("회원탈퇴를 진행합니다. ");
  if (result == false) {
    return;
  }
  fetch("/member/delete.do", { method: "DELETE" })
    .then((response) => response.json())
    .then((data) => {
      if (data == true) {
        alert("탈퇴 성공");
        location.href = "/";
      } else {
        alert("탈퇴 실패");
      }
    })
    .catch((e) => {
      console.log(e);
    });
};
