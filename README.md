# 🛡️ 인증인가 게시판

**인증인가 게시판**은 회원 가입, 로그인, 권한 인증 기능이 포함된 기본 게시판 서비스입니다.  
회원은 게시글과 댓글을 작성할 수 있으며, 각 기능은 명확한 역할 분리를 통해 설계되었습니다.

> - **회원(User)** 은 여러 개의 게시글 및 댓글을 작성할 수 있습니다.
> - **게시글(Post)** 은 여러 개의 댓글을 가질 수 있습니다.
> - 모든 관계는 **1:N 구조**로 설계되었습니다.

<br/>

---

## 📌 ERD (Entity Relationship Diagram)

<img src="./crudboard.png" alt="ERD" width="600"/>

<br/>

---

## 🛠️ 사용 기술 스택

### 🖥️ Language & Framework
<div>
  <img src="https://img.shields.io/badge/Java_17-007396?style=flat&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?logo=spring-security&logoColor=white"/>
</div>

### 🗄️ Database & Infra
<div>
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-DC382D?logo=redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white"/>
</div>

### 🔐 Auth
<div>
  <img src="https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens&logoColor=white"/>
</div>

### ⚙️ Dev Tools
<div>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?logo=intellij-idea&logoColor=white"/>
</div>