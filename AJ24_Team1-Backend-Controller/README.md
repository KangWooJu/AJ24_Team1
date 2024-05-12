# AJ24_Team1
문제해결을 위한 자바활용 팀프로젝트 - Team1 

5/2 BackEnd Controller :

0. Dependency 추가 -
   (1) mysql-connector-java ( mysql 연동 )
   (2) hibernate-entitymanager ( JPA 구현체인 Hibernate 의 entitymanager 모듈 추가 )
   (3) Spring web 
   (4) Spring Security ( 로그인 화면 구현을 위해 Spring Security 의 모듈을 통해 세션 로그인 구현 )
   (5) Spring JPA 

2. 프로젝트 파일 생성 -
   (1) DOM_PROJECT 파일 생성
   (2) 프로젝트의 main 에 Application , Controller 파일 생성

3. Controller 파일 내부 아키텍쳐 구성
   (1) RequestMapping 을 통해 사용자가 로그인 할 경우에 얻을 수 있는 화면 


5/5 Backend Controller : 

0. 프로젝트 재 생성 ( 주요한 기능의 변경 요구 이슈로 삭제 후 재생성 )
   (1) SecurityConfig.java : 회원 가입 기능 Controller 파일
   (2) SiteruserEntity.java : 회원 가입 기능의 Entity 파일 ( 기존 파일에서 변경 )

5/9 Backend Controller : 

1. SecurityConfig.Java 
(1) 로그아웃 기능 추가
(2) AuthenticationManager 예외처리 -> SiteuserSecurityService 와 PasswordEncoder를 내부적으로 사용하여 인증 및 권한 부여 설정

2. SiteuserService.java
(1) 매개변수 5개로 변경 및 순서 변경
(2) bcryptpasswordencoder 객체 삭제 및 라이브러리 Import 삭제 -> 객체 unused 오류 해결 

3. siteuserRepository.java
(1) Optional 객체 ( SiteuserEntity 객체 받기 ) 사용하여 FindByusername 메소드 사용 ( 받은 객체에서 Id 찾기 ) 

4. SiteuserRole.java 추가
(1) ADMIN , USER 를 enum 변수로 받음 -> 데이터베이스에 존재하는 아이디에게 변수에 값을 던져줌으로써 존재하는 사용자만 사용할 수 있게 설정
