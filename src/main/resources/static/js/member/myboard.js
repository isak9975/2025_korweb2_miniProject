console.log("myboard.js 실행확인");

document.addEventListener("DOMContentLoaded", () => {
  const getSessionUserId = () => {
    return sessionStorage.getItem("sessionUserId");
  };

  const getSessionUserName = () => {
    return sessionStorage.getItem("sessionUserName");
  };

  const sessionUserId = getSessionUserId();
  const sessionUserName = getSessionUserName();

  const findUserPosts = () => {
    const option = { method: "GET", credentials: "include" };
    fetch(`/member/myboard.do`, option)
      .then((r) => r.json())
      .then((data) => {
        console.log(data);
        const myboard = document.querySelector(".rightBox_content1");
        const myname1 = document.querySelector(".leftBtn");
        const myname2 = document.querySelector(".rightBtn");
        if (myname1 && myname2) {
          myname1.innerHTML = `${sessionUserName}님의 게시글 조회`;
          myname2.innerHTML = `${sessionUserName}님의 댓글 조회`;
        }
        if (myboard) {
          let html = "";
          data.forEach((board) => {
            html += `<a href="/view?bno=${board.bno}" class="post">
                      <div class="post-title">${board.btitle}</div>
                      <div class="post-date">${board.cdate}</div>
                    </a>`;
          });
          myboard.innerHTML = html;
        } else {
          console.log("게시글이 존재하지 않습니다.");
        }
      })
      .catch((e) => {
        console.log(e);
        alert("내 게시물을 불러올 수 없습니다.");
      });
  };

  findUserPosts();
});

const rightBtn = document.querySelector(".rightBtn");
const leftBtn = document.querySelector(".leftBtn");
const rightBox_content1 = document.querySelector(".rightBox_content1");
const rightBox_content2 = document.querySelector(".rightBox_content2");

leftBtn.addEventListener("mouseover", () => {
  leftBtn.style.backgroundColor = "#dadada";
  rightBtn.style.backgroundColor = "white";
  rightBox_content1.style.display = "flex";
  rightBox_content2.style.display = "none";
});

rightBtn.addEventListener("mouseover", () => {
  leftBtn.style.backgroundColor = "white";
  rightBtn.style.backgroundColor = "#dadada";
  rightBox_content1.style.display = "none";
  rightBox_content2.style.display = "flex";
});
