# 웹개발 miniProject

## 1. 프로젝트 주제

- 커피와 관련된 지식,정보,잡담 등을 나누는 커뮤니티 설계

## 2. 프로젝트 소개

- SpringBoot 및 JPA를 이용한 백엔드 설계,HTML,CSS,JS를 통해 프론트엔드를 설계하고, MySQL을 통하여 DB를 관리하는 커뮤니티의 설계
- MVC패턴을 이용하여 회원 및 게시판,댓글의 CRUD 기능 생성

## 3. 개발 기간

- 2025년 2월 3일(월) ~ 2025년 2월 17일(월)

## 4. 구현한 기능

1. 회원 
    1. 회원 등록 - 회원 가입 기능을 통해 회원의 정보를 받아 DB에 저장함으로서 회원으로 등록하는 기능 및 정해진 양식에 맞게 정보값을 입력하는 기능 구현
    2. 회원 로그인/로그아웃 - 등록된 회원의 정보를 받아 DB에 저장된 회원과 일치하는지 조회, 일치할 시 로그인 성공을 반환하고 현재 로그인된 회원의 회원번호를 세션에 저장,

       로그아웃 시 세션에 저장된 회원번호를 지우고 로그인 페이지로 돌아가는 기능 구현
        
    4. 마이페이지 - 현재 로그인 된 회원의 데이터 및 작성 게시글 과 댓글 조회, 회원의 정보를 수정 및 삭제할 수 있는 마이페이지 기능의 구현
    5. 회원 정보 수정 - 현재 로그인된 회원의 수정될 데이터를 입력받아 DB에 업데이트하는 회원 정보 수정 기능
    6. 회원 탈퇴 - 현재 로그인 된 회원의 참조관계인 게시글과 댓글의 관계를 끊고, DB에서 회원의 정보를 삭제한 후 최종적으로 세션에서도 지우는 회원 탈퇴 기능
    7. 내 게시글/댓글 조회 - 현재 로그인된 회원 번호를 참조하여 현재 로그인된 회원이 작성한 게시글/댓글만을 찾아 출력하는 내 게시글/댓글 조회 기능
2. 게시판
    1. 게시물 전체 출력 - DB에 기록된 게시글 제목 리스트을 전체 출력하는 기능. 게시글이 참조하는 카테고리 번호를 통해 5개의 카테고리에서 작성된 게시글을 조회할 수 있음. 
    2. 페이징 기능 - 이전과 다음 버튼을 통해 게시글 리스트의 페이지를 넘어 갈 수 있으며, 번호가 적힌 버튼을 통해 특정 게시글 리스트 페이지로 이동할 수 있는 페이징 기능의 구현
    3. 게시물 작성 - 게시물 목록에서 게시물 작성을 클릭 시 게시물을 작성할 수 있는 기능의 구현
    4. 게시물 수정 및 삭제 = 특정 게시물을 조회 중 작성된 게시물의 참조 회원번호와 현재 로그인 중인 회원의 회원번호가 일치할 시 게시물을 수정 및 삭제할 수 있는 기능
    5. 게시물 목록에서 특정 게시물을 클릭하면 그 게시물의 내용과 작성자,그 게시물에 작성된 댓글들을 조회하는 기능
3. 댓글 
    1. 특정 게시물을 조회 중 그 게시물에 댓글을 달 수 있는 댓글 기능의 구현.
    2. 작성된 댓글은 게시물의 하단부 댓글 조회 페이지에서 그 내용을 확인 할 수 있게 구현하였음.
  

## 5. 팀원 소개 및 역할

1. 김이삭 - 깃 리포지토리 생성 및 브랜치 관리. 게시글 및 댓글 기능의 DB 설계 및 백엔드 구현
2. 우현서 - 전체 기능의 프론트엔드 설계 및 구현, JS를 통해 프론트엔드와 백엔드간의 통신 구현
3. 김민형 - 회원 기능의 DB 설계 및 백엔드 구현 , ERD 작성

## 6. ERD 캡처 사진

![Image](https://github.com/user-attachments/assets/3b41b09b-65a6-4707-bde7-e88f875621be)

## 7. 프로젝트 구조 트리(TREE)

![Image](https://github.com/user-attachments/assets/905c6d64-00d6-4c41-a6dd-e538c5e0ae29)

![Image](https://github.com/user-attachments/assets/10ef5ccc-fced-4f24-afc0-e27fa0fc57d3)

## 8. 기획 및 설계서 스프레드 시트 주소

https://docs.google.com/spreadsheets/d/1y0Qkkm64MAnUk9WBr0i1KObMo0kIFCwoCBlk-1ZKkAw/edit?usp=sharing

## 9. 프로젝트 실행 결과 녹화본
https://www.youtube.com/watch?v=Au1ogLVxfaM

