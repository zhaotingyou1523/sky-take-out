🌌 苍穹外卖 프로젝트

苍穹外卖는 음식 배달 서비스를 제공하는 레스토랑을 위한 통합 솔루션으로, 관리 백엔드와 소비자용 애플리케이션으로 구성되어 있습니다.​

📌 프로젝트 소개

이 프로젝트는 레스토랑이 효율적으로 음식 배달 서비스를 운영할 수 있도록 지원합니다. 시스템은 두 가지 주요 모듈로 구성되어 있습니다:​

관리 백엔드: 레스토랑 직원이 사용하며, 메뉴, 주문, 직원 관리 및 데이터 통계를 수행할 수 있습니다.​
소비자용 애플리케이션: 고객이 사용하며, 메뉴 탐색, 주문, 결제 및 주문 추적 기능을 제공합니다.​

🧩 주요 기능

관리 백엔드
직원 관리: 직원 정보 추가, 수정 및 비활성화 기능.​
카테고리 관리: 음식 및 세트 메뉴의 카테고리 관리.​
메뉴 관리: 음식 항목의 추가, 수정, 판매 시작/중지 기능.​
세트 메뉴 관리: 세트 메뉴의 구성 및 판매 상태 관리.​
주문 관리: 주문 조회, 취소, 배달 및 완료 처리.​
首尔国立大学开放课程网站
데이터 통계: 매출, 고객 수, 주문 수 등의 통계 데이터 제공.​
주문 알림: 새로운 주문에 대한 실시간 알림 기능.​
소비자용 애플리케이션
회원 가입 및 로그인: 고객 계정 생성 및 인증.​
주소 관리: 배송지 주소 추가, 수정 및 삭제 기능.​
메뉴 탐색: 음식 카테고리 및 상세 정보 조회.​
장바구니: 음식 항목 추가, 수정 및 삭제 기능.​
주문 처리: 주문 생성, 결제 및 주문 상태 추적.​
주문 내역: 과거 주문 기록 조회.​
🛠 기술 스택

백엔드: Spring Boot, Spring MVC, MyBatis, Redis, MySQL, JWT, Spring Cache, Spring Task, WebSocket, Apache POI, HTTPClient.​
프런트엔드: Vue.js, Element UI, ECharts.​
모바일 애플리케이션: WeChat 미니 프로그램.​
기타: Nginx(리버스 프록시 및 로드 밸런싱), Swagger(API 문서화), Alibaba Cloud OSS(오브젝트 스토리지).​
🚀 시작하기

필수 조건
Java 17 이상​
Node.js 20.11.1 이상​
pnpm 9.12.0 이상​
MySQL 8.0 이상​
Redis​
프로젝트 클론
git clone https://github.com/zhaotingyou1523/sky-take-out.git
백엔드 설정 및 실행
데이터베이스 및 Redis 연결 정보를 설정합니다.​
프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다:​
mvn clean install
mvn spring-boot:run
프런트엔드 설정 및 실행
프런트엔드 프로젝트 디렉토리로 이동합니다.​
다음 명령어를 실행하여 의존성을 설치하고 개발 서버를 시작합니다:​
pnpm install
pnpm dev
모바일 애플리케이션 설정
WeChat 개발자 도구를 사용하여 미니 프로그램 프로젝트를 가져옵니다.​
백엔드 API 주소를 설정합니다.​
프로그램을 실행하고 디버깅합니다.​
📄 프로젝트 구조

sky-take-out/
├── sky-common/        # 공통 모듈
├── sky-pojo/          # 엔티티 클래스 모듈
├── sky-server/        # 백엔드 서비스 모듈
├── sky-frontend/      # 관리 백엔드 프런트엔드 프로젝트
├── sky-applet/        # 소비자용 미니 프로그램 프로젝트
├── pom.xml            # Maven 프로젝트 구성 파일
└── README.md          # 프로젝트 설명 파일
