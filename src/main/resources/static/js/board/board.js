
const printPageNation = (data,cno) => {console.log(data);
//(1)어디에
const pagebox = document.querySelector(".pagebox")

let page = data.page;//현재페이지 pageDto에
let totalpage = data.totalpage;
let startbtn = data.startbtn;
let endbtn = data.endbtn;

//(2) 무엇을
    let html = ``

    html += `<li class="page-item"><a class="page-link" href="/board?type=${cno}&page${page<=1?1:page-1}">이전</a></li>`

    for(let index = startbtn; index<=endbtn; index++){
                //만약에 현재 페이지와 버튼 번호가 같다면 .active 부트스트랩 클래스 부여
                html += `
                        <li class="page-item"><a class="page-link" ${page==index?'active':''} href="/board?type=${cno}&page=${index}">${index}</a></li>`;
                }

            //다음버튼, 현재페이지에서 +1 증가한 페이지 이동
                //만약에 +1 했을때 전체 페이지수 보다 이상이면 전체페이지수로 고정
                //
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

