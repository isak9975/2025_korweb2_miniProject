document.addEventListener("DOMContentLoaded", () => {
  const getQueryParam = (param) => {
    const params = new URLSearchParams(window.location.search);
    return params.get(param);
  };

  const type = getQueryParam("type");

  const boardTitles = {
    1: "공지 게시판",
    2: "잡담 게시판",
    3: "노하우 게시판",
    4: "정보 게시판",
    5: "질문 게시판",
  };

  document.getElementById("board-title").innerText = boardTitles[type];

  console.log("board.js open");
  console.log(new URL(location.href));
  console.log(new URL(location.href).searchParams);
  console.log(new URL(location.href).searchParams.get("cno"));

  const findAll = () => {
    const cno = new URL(location.href).searchParams.get("type");
    const option = { method: "GET" };
    fetch(`/board/findall.do?cno=${cno}`, option)
      .then((r) => r.json())
      .then((data) => {
        console.log(data);
        const postsContainer = document.querySelector(".posts-container");
        let html = "";
        data.forEach((board) => {
          html += `<a href="/view?bno=${board.bno}" class="post">
                    <div class="post-title">${board.btitle}</div>
                    <div class="post-author">${board.mid}</div>
                    <div class="post-comments">${board.bview}</div>
                    <div class="post-date">${board.cdate}</div>
                  </a>`;
        });
        postsContainer.innerHTML = html;
      })
      .catch((e) => {
        console.log(e);
      });
  };

  findAll();
});
