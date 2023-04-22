# Board project

## 프로젝트 소개

### 프로젝트 시작 이유

이번에 Spring과 JPA에 대해 학습한 후 이러한 것들을 직접 사용해보고 싶어 이번 프로젝트를 진행하게 되었습니다. 

### 프로젝트 기능

- 게시판 - CRUD기능, 조회수 증가 로직, 페이징 처리 및 검색 기능
- 유저 - SpringSecurity를 통한 회원가입 및 로그인 기능, 회원가입시의 중복 처리 및 중복 로그인된 사용자에 대한 처리기능
- 댓글  - CRUD기능
- 채팅방 - 채팅방 생성 기능 및 참가, 채팅 기능, 채팅방 참여인원 확인기능, 입장 및 퇴장 메세지 기능

### 사용 기술

프레임워크/ 라이브러리

- Java 11
- SpringBoot 2.7.8
- SringSecurity
- JPA(Spring Data JPA)
- WebSocket 2.3.3-1 , SockJS 1.1.2

DataBase

프로젝트 진행 초반에는 테스트의 용이성을이유로 h2를 이용하였고 배포할 시점에서는 h2 → mysql로 변경하였습니다.

- Mysql / h2

프론트엔드

- ThymeLeaf
- Html/css
- Bootstrap
- JavaScript

배포

- aws ec2
- aws rds
- docker

Build Tool

- Gradle

### 실행 화면

<details>
  <summary>회원</summary>
  <div markdown="1">
    <img width="1624" alt="Untitled 1" src="https://user-images.githubusercontent.com/29451636/233762910-d50a0c66-c36f-4bb7-9a27-6388d18e3623.png">
    <img width="1512" alt="Untitled 2" src="https://user-images.githubusercontent.com/29451636/233762921-8cbfe299-b5a3-4a13-b75e-33ab9b21e900.png">
    <img width="1512" alt="Untitled 3" src="https://user-images.githubusercontent.com/29451636/233762925-76d43ed7-470c-4700-9dd8-2cc71a040694.png">

  </div>
</details>




    


<details>
  <summary>게시글</summary>
    <div markdown="1">
      <img width="1512" alt="Untitled 4" src="https://user-images.githubusercontent.com/29451636/233762930-1942d76b-408c-4897-9001-03b804d995b8.png">
      <img width="1505" alt="123" src="https://user-images.githubusercontent.com/29451636/233763908-7b4611cc-2b5e-45ab-9a1d-9a9daf5f144e.png">
      <img width="1512" alt="Untitled 5" src="https://user-images.githubusercontent.com/29451636/233762934-f08a1c02-45aa-4417-923f-e930e077d209.png">
      <img width="1512" alt="123141" src="https://user-images.githubusercontent.com/29451636/233764009-8cf9fabe-d391-4097-bfe3-9387f4efa7c7.png">

    </div>
</details>
    
    


    
<details>
  <summary>채팅방</summary>
    <div markdown="1">
      <img width="1512" alt="11" src="https://user-images.githubusercontent.com/29451636/233763848-0116f49d-d636-453d-a712-8722571729dc.png">
    </div>
</details>
    
    

    

## 구조 및 설계


<details>
<summary>DB 설계</summary>
<div markdown="1">
  <img width-"1404" src="https://user-images.githubusercontent.com/29451636/233763678-9f1657af-9c0f-47a5-be46-0eaf3c4f63d7.png">
</div>
</details>


<details>
  <summary>API설계</summary>
    <div markdown="1">
      <img width="1404" alt="스크린샷 2023-04-22 오후 1 55 46" src="https://user-images.githubusercontent.com/29451636/233763387-894ef10d-da53-4d8b-a7aa-956f66ccd65a.png">
      <img width="1404" alt="스크린샷 2023-04-22 오후 1 57 06" src="https://user-images.githubusercontent.com/29451636/233763445-e2331c56-618b-4c35-a8ce-cf8e26a77619.png">
      <img width="1404" alt="스크린샷 2023-04-22 오후 1 57 21" src="https://user-images.githubusercontent.com/29451636/233763462-93a7c9ea-e630-4455-865f-131404c4088b.png">
      <img width="1404" alt="스크린샷 2023-04-22 오후 1 57 35" src="https://user-images.githubusercontent.com/29451636/233763463-5804839e-0857-47ea-92a4-a4c34d975183.png">
    </div>
 </details>





## 개발 일지

[10. 프로젝트 진행과정](https://www.notion.so/10-4961e334ce2d40e69a2f08ee3bece156)


