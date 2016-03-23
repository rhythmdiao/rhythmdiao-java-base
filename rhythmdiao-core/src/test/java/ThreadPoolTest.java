import base.BaseTest;
import com.rhythmdiao.thread.ExecutorThreadPool;
import com.rhythmdiao.thread.ThreadPool;
import com.rhythmdiao.util.time.TimeCounter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest extends BaseTest{
    @Test
    public void testExecutorThreadPool() {
        TimeCounter interval = new TimeCounter().start();
        ThreadPool threadPool = new ExecutorThreadPool();
        ThreadPool threadPool2 = new ExecutorThreadPool(10, 100L, TimeUnit.MILLISECONDS, Executors.newFixedThreadPool(5));
        for (int i = 1; i < 1000; i++) {
            final int j = i;
            threadPool2.execute(new Runnable() {
                @Override
                public void run() {
                    LOG.info("{}", j);
                }
            });
        }
        threadPool2.shutDown();
        LOG.info("Thread pool execution spent {} milliseconds", interval.end());
    }
}
