const main_btn = document.querySelector("#home_box--btn");

main_btn.addEventListener("click", () => {
  window.alert("구현 안됨");
});

document.addEventListener("DOMContentLoaded", () => {
  const boardTypes = [2, 3, 4, 5]; // 잡담, 노하우, 정보, 질문 게시판 타입
  const boardTitles = {
    2: "잡담 게시판",
    3: "노하우 게시판",
    4: "정보 게시판",
    5: "질문 게시판",
  };

  const fetchBoardData = (type) => {
    const option = { method: "GET" };
    fetch(`/board/findall.do?cno=${type}&page=1`, option)
      .then((r) => r.json())
      .then((data) => {
        const boardList = data.data.slice(0, 5); // 최신 5개 게시글만 가져오기
        const postsContainer = document.querySelector(`.posts-container-${type}`);

        let html = "";
        boardList.forEach((board) => {
          html += `<a href="/view?bno=${board.bno}" class="post">
                    <div class="post-title">${board.btitle}</div>
                    <div class="post-author">${board.mname}</div>
                  </a>`;
        });
        postsContainer.innerHTML = html;
      })
      .catch((e) => {
        console.log(e);
      });
  };

  boardTypes.forEach((type) => {
    fetchBoardData(type);
  });
});

