import com.rhythmdiao.thread.ThreadPool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolTest.class);

    @Test
    public void testThreadPool() {
        long before = System.currentTimeMillis();
        ThreadPool threadPool = new ThreadPool(1);
        ThreadPool threadPool2 = new ThreadPool().time(100).timeUnit(TimeUnit.MILLISECONDS).executorService(Executors.newFixedThreadPool(1));
        for (int i = 1; i < 10; i++) {
            final int j = i;
            threadPool2.execute(new Runnable() {
                @Override
                public void run() {
                    LOG.info("{}", j);
                }
            });
        }
        threadPool2.shutDown();
        long after = System.currentTimeMillis();
        LOG.info("Thread pool execution spent {} milliseconds", after - before);
    }
}
