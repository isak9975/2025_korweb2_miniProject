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
        const myboard = document.querySelector(".board");
        const myname1 = document.querySelector(".leftBtn");
        const myname2 = document.querySelector(".rightBtn");
        if (myname1 && myname2) {
          myname1.innerHTML = `${data.mname}님의 게시글 조회`;
          myname2.innerHTML = `${data.mname}님의 댓글 조회`;
        }
        if (myboard) {
          let html =
            '<div class="header"><div class="title">제목</div><div class="author">작성자</div><div class="date">작성일</div><div class="comments">댓글</div></div>';
          data.forEach((board) => {
            html += `<a href="/view?bno=${board.bno}" class="post">
                      <div class="title">${board.btitle}</div>
                      <div class="author">${board.mid}</div>
                      <div class="date">${board.cdate}</div>
                      <div class="comments">${board.bview}</div>
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
