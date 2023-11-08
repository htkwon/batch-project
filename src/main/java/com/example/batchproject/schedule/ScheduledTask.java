package com.example.batchproject.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
//@RequiredArgsConstructor
public class ScheduledTask {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job addPassesJob;
    @Autowired
    private Job expirePassJob;

    @Autowired
    private Job switchPassesJob;

    @Autowired
    private Job sendNotificationBeforeClassJob;

    @Autowired
    private Job makeStatisticsJob;

    /**
     * 이용권 일괄 지급 스케쥴
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void addPassesJobSchedule() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(addPassesJob, jobParameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                log.info("KAKAO MESSAGE - 이용권 지급 성공!");
            } else {
                log.info("KAKAO MESSAGE - 이용권 지급 실패!");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * 이용권 만료 스케쥴
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void expirePassesJobSchedule() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(expirePassJob, jobParameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                log.info("KAKAO MESSAGE - 이용권 기간 만료");
            } else {
                log.info("KAKAO MESSAGE - 이용권 기간 만료 실패 !");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * 이용권 상태변경 스케쥴
     */
    @Scheduled(cron = "*/7 * * * * *")
    public void switchPassesJobSchedule() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(switchPassesJob, jobParameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                log.info("이용권 상태 변경 완료");
            } else {
                log.info("이용권 상태 변경 실패");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /** kakao api 호출(알림) 스케쥴 ->*/
    /**
     * kakao restAPI(메세지 발송)에서 발신자 본인은 수신자로 설정할 수 없어, 주석처리.
     */
    //    @Scheduled(cron = "*/3 * * * * *")
//    public void sendNotificationBeforeClassJobSchedule(){
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("time", System.currentTimeMillis())
//                    .toJobParameters();
//            JobExecution jobExecution = jobLauncher.run(sendNotificationBeforeClassJob,jobParameters);
//            if(jobExecution.getStatus()==BatchStatus.COMPLETED){
//                log.info("카카오톡 전송 성공");
//            }else {
//                log.info("카카오톡 전송 실패");
//            }
//        }catch (Exception e){
//            log.info(e.getMessage());
//        }
//    }

    /**
     * 통계 집계 스케쥴
     */
//    @Scheduled(cron = "*/8 * * * * *")
//    public void makeStatisticsJobSchedule() {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//            LocalDateTime from = LocalDateTime.now().minusDays(7);
//            LocalDateTime to = LocalDateTime.now();
//
//            String fromString = from.format(formatter);
//            String toString = to.format(formatter);
//
//
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("time", System.currentTimeMillis())
//                    .addString("from", fromString)
//                    .addString("to", toString)
//                    .toJobParameters();
//            JobExecution jobExecution = jobLauncher.run(makeStatisticsJob, jobParameters);
//            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//                log.info("통계 집계 성공");
//            } else {
//                log.info("통계 집계 실패");
//            }
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//    }


}
