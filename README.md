# batch-project
이용권 관리 서비스

Spring batch 기술 연습을 위한 PT 이용권 관리 service 입니다. 
- 프로젝트  batch Job 기능 종류
  - 이용권 만료
  - 이용권 일괄 지급
  - 수업 전 알림
  - 수업 후 이용권 차감
  - 이용권 상태 변경 
  - 통계 데이터 구축
 
*Job의 실행은 SpringBoot에서 제공하는 Scheduler를 사용하여 구동하였습니다.*

<img width="547" alt="batch 다이어그램" src="https://github.com/htkwon/batch-project/assets/117131575/ec942f26-6a94-4de5-9642-28fb2ba175a2">



# 서비스 요구사항
- 이용권 관련
    - 사용자는 N개의 이용권을 가질 수 있다.
    - 이용권은 횟수가 모두 소진되거나 이용기간이 지나면 만료된다.
    - 이용권 지급 후 이용권의 시작 날짜 기준으로 이용권의 상태를 자동 변경된다.
    - 이용권 만료 전 사용자에게 알림을 준다.
    - 관리자 페이지에서 PT 시작 날짜를 설정하여, 일괄로 사용자들에게 PT 시작 날짜 하루 전 이용권을 자동 지급 할 수 있다.
- 수업 관련
    - 예약된 수업 10분 전 출석 안내 알람을 준다.
    - 수업 종료 시간 시점 수업을 예약한 학생의 이용권 횟수를 자동 차감한다.
- 통계 관련
    - 사용자의 수업 예약, 출석, 이용권 횟수 등의 데이터로 유의미한 통계 데이터를 만든다.


# 기술 스택
- Java 17
- Spring Boot 2.7.3
- Gradle
- MySQL
- Docker
- Spring Data JPA
- Spring Batch
- Lombok
- Mapstruct
