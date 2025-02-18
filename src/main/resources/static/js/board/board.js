
const printPageNation = (data,cno) => {console.log(data);
const pagebox = document.querySelector(".pagebox")

let page = data.page;
let totalpage = data.totalpage;
let startbtn = data.startbtn;
let endbtn = data.endbtn;

    let html = ``

    html += `<li class="page-item"><a class="page-link" href="/board?type=${cno}&page${page<=1?1:page-1}">이전</a></li>`

    for(let index = startbtn; index<=endbtn; index++){
                html += `
                        <li class="page-item"><a class="page-link" ${page==index?'active':''} href="/board?type=${cno}&page=${index}">${index}</a></li>`;
                }
            html += `<li class="page-item"><a class="page-link" href="/board?type=${cno}&page=${page>=totalpage?totalpage:page+1}">다음</a></li>`

        pagebox.innerHTML = html;



}





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
    let page = new URL(location.href).searchParams.get("page");
      console.log(new URL(location.href).searchParams.get("page"));
    if(page==null)page=1;


    const option = { method: "GET" };
    fetch(`/board/findall.do?cno=${cno}&page=${page}`, option)
      .then((r) => r.json())
      .then((data) => {
        console.log(data);

        let  boardList = data.data;

        const postsContainer = document.querySelector(".posts-container");

        let html = "";
        boardList.forEach((board) => {
          html += `<a href="/view?bno=${board.bno}" class="post">
                    <div class="post-title">${board.btitle}</div>
                    <div class="post-author">${board.mid}</div>
                    <div class="post-comments">${board.bview}</div>
                    <div class="post-date">${board.cdate}</div>
                  </a>`;
        });
        postsContainer.innerHTML = html;

        printPageNation( data , cno);

      })
      .catch((e) => {
        console.log(e);
      });
  };

  findAll();
});

