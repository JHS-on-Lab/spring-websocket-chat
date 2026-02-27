# Spring WebSocket Chat

세션 기반 인증과 STOMP WebSocket을 이용한 실시간 채팅 애플리케이션.

------------------------------------------------------------------------

# 1. Tech Stack

-   Java 25
-   Spring Boot 4
-   Spring Security (Session 기반 인증)
-   WebSocket (STOMP + SockJS)
-   Spring Data JPA
-   H2 Database
-   Thymeleaf
-   Docker
-   GitHub Actions
-   AWS EC2

------------------------------------------------------------------------

# 2. System Architecture

```
Client (Browser)
    │
    ▼
EC2 (Docker Container)
    │
    ▼
Spring Boot App
    │
    ├─ H2
    └─ WebSocket Simple Broker
```

------------------------------------------------------------------------

# 3. Authentication

-   Form Login 기반 세션 인증 (JSESSIONID)
-   CustomUserDetailsService 구현
-   서버 시작 시 admin 계정 자동 생성
-   ROLE_USER / ROLE_ADMIN 권한 구조

------------------------------------------------------------------------

# 4. Domain Model

## User

-   id
-   username (unique)
-   password (BCrypt 암호화)
-   role

## ChatRoom

-   id
-   name
-   createdAt

## ChatRoomMember

-   User ↔ ChatRoom N:M
-   joinedAt

## Message

-   id
-   roomId
-   sender
-   content
-   createdAt

------------------------------------------------------------------------

# 5. 주요 기능

## 채팅

-   참여 채팅방 목록 조회
-   채팅방 생성
-   실시간 메시지 송수신 (STOMP)
-   과거 메시지 REST 조회
-   멤버 목록 조회
-   Ajax 기반 멤버 초대

## 관리자

-   관리자 계정 자동 생성
-   사용자 목록 조회
-   사용자 생성 (@Valid 검증)

------------------------------------------------------------------------

# 6. Security 정책

-   채팅방 접근 시 isMember(roomId, username) 검증
-   메시지 조회 REST API 권한 검증
-   초대 API 권한 검증
-   관리자 페이지 ROLE_ADMIN 제한

------------------------------------------------------------------------

# 7. WebSocket 설정

-   Endpoint: /ws
-   Send: /app/chat.send
-   Subscribe: /topic/room.{roomId}
-   SimpleBroker 사용

------------------------------------------------------------------------

# 8. Deployment Process

## 배포 구조

```
Developer
    │
    │ git push
    ▼
GitHub (Repository)
    │
    ▼
GitHub Actions (CI/CD)
    │
    ├─ Gradle Build
    ├─ Docker Image Build
    ├─ Docker Hub Push
    │
    ▼
EC2 Server
    ├─ docker pull (latest image)
    └─ docker run (container 교체)
```

## CI/CD 단계

1.  main 브랜치 push 시 자동 실행
2.  Gradle 빌드 및 테스트 수행
3.  Docker 이미지 빌드
4.  Docker Hub 업로드
5.  EC2 SSH 접속 후 기존 컨테이너 교체 실행

## GitHub Secrets

-   DOCKER_USERNAME
-   DOCKER_PASSWORD
-   EC2_HOST
-   EC2_USER
-   EC2_SSH_KEY
-   기타 실행 인자 (Spring Profile 등)

실행 인자는 저장소에 노출되지 않으며 GitHub Secrets로 관리

------------------------------------------------------------------------

# 9. 현재 완료 상태

-   로그인 기능
-   관리자 자동 생성
-   관리자 사용자 생성
-   사용자 목록 페이지
-   채팅방 생성
-   사용자별 채팅방 조회
-   채팅방 접근 권한 검증
-   실시간 채팅
-   과거 메시지 로딩
-   채팅방 멤버 목록 표시
-   Ajax 멤버 초대
-   역할별 패키지 구조 정리
-   Docker 기반 배포 자동화
-   GitHub Actions CI/CD 구축 완료

------------------------------------------------------------------------

# 10. 향후 개선 사항

-   Presence 기능
-   메시지 읽음 처리
-   외부 RDBMS 전환 (MySQL/PostgreSQL)
-   Redis 기반 Broker 확장
