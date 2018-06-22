package ocean;

import lombok.extern.slf4j.Slf4j;
import ocean.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务处理
 * @author xieyi
 */
@Slf4j
@Component
public class TimeTaskSchedule {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TaskExecutor taskExecutor;
    @Scheduled(fixedDelay = 1000)
    public  void  cron(){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
       /* log.info(Thread.currentThread().getName());*/
    }
}
