# ✅ Todo List

### 🔔인증인가 전까지!

<br>

#### 📌 Common
- [x] Common module
- [x] Error code - 구현하면서 필요한 에러코드 추가 예정

#### 📌 Auth
- [x] 회원가입(DB 저장) 구현
- [x] 로그인(DB 확인) 구현

#### 📌 User
- [X] 유저 전체 조회
- [X] 유저 단건 조회
- [X] 유저 수정
- [X] 유저 soft delete

#### 📌 Post
- [X] 게시글 생성
- [X] 게시글 전체 조회
- [X] 게시글 단건 조회
- [X] 게시글 수정
- [X] 게시글 soft delete
- [X] 게시글 조회할 때 해당 게시글에 달린 댓글도 조회
- [X] 게시글 삭제할 때 해당 게시글에 달린 댓글도 삭제

#### 📌 Comment
- [X] 댓글 생성
- [X] 댓글 전체 조회
- [X] 댓글 단건 조회
- [X] 댓글 수정
- [X] 댓글 soft delete

<br>

#### 📌 Code Refactoring
- 모놀리틱이라고 꼭 결합도가 높도록 할 필요는 없다. 최대한 결합도 낮게 해도 됨
- [X] 에러코드는 각 패키지에 들어가도록 수정하기
- [X] 게시글-댓글 결합도 낮추기
  - [X] 댓글 엔티티에서 게시글 엔티티를 직접 참조하는 구조를 수정
  - [X] 게시글이 삭제되었을 때 댓글은 이벤트 퍼블리셔로 삭제

<br>

#### 📌 인증인가
- [X] SecurityConfig
- [X] JwtUtil
- [X] JwtFilter
- [X] CustomUserDetails
- [X] CustomUserDetailsService
- [X] 로그인 시 응답 DTO에 토큰 값 반환하기

<br>

### 🔔다음 해야할 일 : Swagger 문서, MSA 전환