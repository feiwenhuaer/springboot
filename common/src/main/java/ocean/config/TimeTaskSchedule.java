package ocean.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务处理
 * @author xieyi
 */
@Slf4j
@Component
public class  TimeTaskSchedule {
    @Scheduled(fixedDelay = 1000*60)
    public  void  cron(){
       /* log.info(Thread.currentThread().getName());*/
    }
}
