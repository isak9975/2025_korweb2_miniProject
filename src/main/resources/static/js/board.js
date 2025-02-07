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

  const posts = [
    {
      title: "게시글 제목 1",
      date: "2025-02-07",
      author: "사용자1",
      comments: 5,
    },
    {
      title: "게시글 제목 2",
      date: "2025-02-06",
      author: "사용자2",
      comments: 3,
    },
    {
      title: "게시글 제목 3",
      date: "2025-02-05",
      author: "사용자3",
      comments: 7,
    },
    {
      title: "게시글 제목 4",
      date: "2025-02-04",
      author: "사용자4",
      comments: 2,
    },
    {
      title: "게시글 제목 5",
      date: "2025-02-03",
      author: "사용자5",
      comments: 8,
    },
  ];

  const content = document.querySelector(".content");
  posts.forEach((post) => {
    const postDiv = document.createElement("div");
    postDiv.className = "post";
    postDiv.innerHTML = `
      <div class="post-title">${post.title}</div>
      <div class="post-date">${post.date}</div>
      <div class="post-author">${post.author}</div>
      <div class="post-comments">${post.comments}</div>
  `;
    content.appendChild(postDiv);
  });
});
