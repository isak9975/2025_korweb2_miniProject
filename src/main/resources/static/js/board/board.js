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

  // * JS가 열렸는지 확인하기 위해
  console.log("board.js open");
  // * 현재 URL 의 쿼리스트링 매개변수 가져오기
  console.log(new URL(location.href));
  console.log(new URL(location.href).searchParams);
  console.log(new URL(location.href).searchParams.get("cno"));

  // [1] 게시물 전체 조회 요청 함수
  const findAll = () => {
    // 1. 현재 페이지 URL 에서 매개변수 cno 값 구하기
    const cno = new URL(location.href).searchParams.get("cno");
    // 2. fetch option
    const option = { method: "GET" };
    // 3. fetch
    fetch(`/board/findall.do?cno=${cno}`, option)
      .then((r) => r.json())
      .then((data) => {
        // 4. 요청 결과 응답 자료 확인
        console.log(data);
        // 5. HTML을 출력할 구역 DOM 가져오기
        const postsContainer = document.querySelector(".posts-container");
        // 6. 출력할 HTML을 저장하는 변수 선언
        let html = "";
        // 7. 응답 자료를 반복문 이용하여 하나씩 순회해서 HTML 누적으로 더해주기
        data.forEach((board) => {
          html += `<div class="post">
                    <div class="post-title">${board.btitle}</div>
                    <div class="post-date">${board.cdate}</div>
                    <div class="post-author">${board.mid}</div>
                    <div class="post-comments">${board.bview}</div>
                  </div>`;
        }); // 반복문 종료
        // 8. 반복문 종료 후 HTML 변수에 누적된 <div> 출력하기
        postsContainer.innerHTML = html;
      })
      .catch((e) => {
        console.log(e);
      });
  }; // 함수 종료

  findAll(); // JS가 실행될 때 함수 실행
});
