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
