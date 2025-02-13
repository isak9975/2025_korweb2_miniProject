const onFind = () => {
  const bno = new URL(location.href).searchParams.get("bno");

  fetch(`/board/find.do?bno=${bno}`)
    .then((r) => r.json())
    .then((data) => {
      console.log(data);
      document.querySelector(".mnameBox").innerHTML = data.mname;
      document.querySelector(".bviewBox").innerHTML = data.bview;
      document.querySelector(".cdateBox").innerHTML = data.cdate;

      document.querySelector(".btitle").innerHTML = data.btitle;
      document.querySelector(".bcontent").innerHTML = data.bcontent;
    })
    .catch((e) => {
      console.log(e);
    });
};
onFind();

const onReplyWrite = () => {
  const rcontentInput = document.querySelector(".rcontentInput");
  const rcontent = rcontentInput.value;
  const bno = new URL(location.href).searchParams.get("bno");
  const obj = { rcontent, bno };
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  };
  fetch("/reply/write.do", option)
    .then((r) => r.json())
    .then((data) => {
      if (data) {
        alert("댓글 등록");
        onReplyFindAll();
      } else {
        alert("댓글 등록 실패");
      }
    });
};
