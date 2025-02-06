// document.addEventListener("DOMContentLoaded", function () {
//   function getQueryParam(param) {
//     let params = new URLSearchParams(window.location.search);
//     return params.get(param);
//   }

//   let type = getQueryParam("type");

//   // 게시판 제목 설정
//   let boardTitle = "";
//   switch (type) {
//     case "1":
//       boardTitle = "공지 게시판";
//       break;
//     case "2":
//       boardTitle = "잡담 게시판";
//       break;
//     case "3":
//       boardTitle = "노하우 게시판";
//       break;
//     case "4":
//       boardTitle = "정보 게시판";
//       break;
//     case "5":
//       boardTitle = "Q&A";
//       break;
//     default:
//   }
//   document.getElementById("board-title").innerText = boardTitle;

//   fetch(`/api/posts?type=${type}`)
//     .then((response) => response.json())
//     .then((posts) => {
//       let postsDiv = document.getElementById("posts");
//       posts.forEach((post) => {
//         let postDiv = document.createElement("div");
//         postDiv.innerHTML = `<h2>${post.title}</h2>
//                                   <p>작성자: ${post.author}</p>
//                                   <p>${post.content}</p>
//                                   <p>작성일: ${post.createDate}</p>
//                                   <p>댓글수: ${post.commentCount}</p>`;
//         postsDiv.appendChild(postDiv);
//       });
//     })
//     .catch((error) => console.error("Error:", error));
// });

// document.addEventListener("DOMContentLoaded", () => {
//   const getQueryParam = (param) => {
//     const params = new URLSearchParams(window.location.search);
//     return params.get(param);
//   };

//   const type = getQueryParam("type");

//   const boardTitles = {
//     1: "공지 게시판",
//     2: "잡담 게시판",
//     3: "노하우 게시판",
//     4: "정보 게시판",
//     5: "Q&A",
//   };

//   document.getElementById("board-title").innerText =
//     boardTitles[type] || "커뮤니티 게시판";

//   const fetchPosts = async () => {
//     try {
//       const response = await fetch(`/api/posts?type=${type}`);
//       const posts = await response.json();
//       const postsDiv = document.getElementById("posts");

//       posts.forEach((post) => {
//         const postDiv = document.createElement("div");
//         postDiv.innerHTML = `
//                   <h2>${post.title}</h2>
//                   <p>작성자: ${post.author}</p>
//                   <p>${post.content}</p>
//                   <p>작성일: ${post.createDate}</p>
//                   <p>댓글수: ${post.commentCount}</p>
//               `;
//         postsDiv.appendChild(postDiv);
//       });
//     } catch (error) {
//       console.error("Error:", error);
//     }
//   };

//   fetchPosts();
// });

const fetchPosts = async () => {
  const examplePosts = [
    {
      title: "공지사항: 커뮤니티 규칙 안내",
      author: "관리자",
      content: "커뮤니티 사용에 대한 규칙을 안내드립니다.",
      createDate: "2025-02-06",
      commentCount: 10,
    },
    {
      title: "잡담: 오늘 날씨 좋네요",
      author: "회원1",
      content: "오늘 날씨가 정말 좋아요!",
      createDate: "2025-02-06",
      commentCount: 5,
    },
    {
      title: "노하우: 커피 내리는 방법",
      author: "바리스타",
      content: "최고의 커피를 내리는 방법을 공유합니다.",
      createDate: "2025-02-06",
      commentCount: 3,
    },
  ];
};

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
    5: "Q&A",
  };

  document.getElementById("board-title").innerText =
    boardTitles[type] || "커뮤니티 게시판";

  const fetchPosts = async () => {
    try {
      const response = await fetch(`/api/posts?type=${type}`);
      const posts = await response.json();
      const postsDiv = document.getElementById("posts");

      const table = document.createElement("table");
      table.innerHTML = `
              <thead>
                  <tr>
                      <th>제목</th>
                      <th>작성자</th>
                      <th>작성일</th>
                      <th>댓글수</th>
                  </tr>
              </thead>
              <tbody id="posts-body"></tbody>
          `;
      postsDiv.appendChild(table);

      const postsBody = document.getElementById("posts-body");

      posts.forEach((post) => {
        const postRow = document.createElement("tr");
        postRow.innerHTML = `
                  <td><a href="/post/${post.id}">${post.title}</a></td>
                  <td>${post.author}</td>
                  <td>${post.createDate}</td>
                  <td>${post.commentCount}</td>
              `;
        postsBody.appendChild(postRow);
      });
    } catch (error) {
      console.error("Error:", error);
    }
  };

  fetchPosts();
});
