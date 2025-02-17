// * 썸머노트 실행
$(document).ready(function () {
  $("#summernote").summernote({
    height: 500, // 썸머노트 게시판의 높이조절 속성
    lang: "ko-KR", // 썸머노트 메뉴 한글화 속성
    placeholder: "게시물 내용 입력해주세요", // 입력 전에 가이드라인 제공 속성
  });
});

// [1] 게시물 등록 요청 함수
const onWrite = () => {console.log("onWrite() 실행")

  // [1] 현재 html 의 DOM 객체 의 입력받은값(value) 가져오기
  const cno = document.querySelector("#cnoInput").value; console.log(cno);
  const btitle = document.querySelector("#btitleInput").value; console.log(btitle);
  const bcontent = $('#summernote').summernote('code'); console.log(bcontent); // 썸머 노트 내용 값 가져오기

  // [2] 입력받은 값들을 JSON 보내기 위해서 입력받은 값 으로 객체 만들기
  const obj = { cno: cno, btitle: btitle, bcontent: bcontent };
  // [3] fetch
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  };

  fetch("/board/write.do", option)
    .then((r) => r.json())

    .then((data) => {
      console.log(data);

      if(btitle===""){alert("제목이 없습니다.");console.log(btitle);}
      else{
      //썸머 노트 내용 비어있는지 확인 -> 띄어쓰기 엔터 많으면 true 인식 -> 구림
//        const isEmpty = $('#summernote').summernote('isEmpty')

        const isEmpty = bcontent.replace(/<[^>]*>/g, '').trim();//썸모 노트내 html 내용 삭제 및 .trim 으로 빈칸도 제거

        if(isEmpty===""){alert("내용이 없습니다.");console.log(bcontent);}
        else{
                  if (data == true) {
                    alert("글쓰기 성공");
                    location.href = `/board?type=${cno}`;
                  } else {
                    alert("글쓰기 실패 : 로그인후 가능합니다. ");
                  }
          }//end f2 else
      }//end f1 else

    }) //end data
    .catch((e) => {
      console.log(e);
    });//end catch
};
