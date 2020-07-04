package org.santaoracle.design.singleton;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author jiangpeiheng create on 2020/7/4
 */
public class SingletonTest {

    private static final int CONCURRENT_THREAD_COUNT = 20;

    @Test
    public void testMySingleton() throws InterruptedException {
        Map<Long, Boolean> recorder = Maps.newConcurrentMap();
        CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_COUNT);
        IntStream.rangeClosed(1, CONCURRENT_THREAD_COUNT).forEach(i -> new Thread(() -> {
            IntStream.rangeClosed(1, 1000).forEach(ii -> {
                        Long id = MySingleton.getInstance().genId();
                        System.out.println(String.format("Id: %s", id));
                        Assert.assertNull(recorder.get(id));
                        recorder.put(id, true);
                    }
            );
            latch.countDown();
        }
        ).start());

        latch.await(20, TimeUnit.SECONDS);
    }

    @Test
    public void testInnerSingleton() throws InterruptedException {
        Map<Long, Boolean> recorder = Maps.newConcurrentMap();
        CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_COUNT);
        IntStream.rangeClosed(1, CONCURRENT_THREAD_COUNT).forEach(i -> new Thread(() -> {
            IntStream.rangeClosed(1, 1000).forEach(ii -> {
                        Long id = InnerSingleton.getInstance().genId();
                        System.out.println(String.format("Id: %s", id));
                        Assert.assertNull(recorder.get(id));
                        recorder.put(id, true);
                    }
            );
            latch.countDown();
        }
        ).start());

        latch.await(20, TimeUnit.SECONDS);
    }

    @Test
    public void testEnumSingleton() throws InterruptedException {
        Map<Long, Boolean> recorder = Maps.newConcurrentMap();
        CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_COUNT);
        IntStream.rangeClosed(1, CONCURRENT_THREAD_COUNT).forEach(i -> new Thread(() -> {
            IntStream.rangeClosed(1, 1000).forEach(ii -> {
                        Long id = EnumSingleton.getInstance().genId();
                        System.out.println(String.format("Id: %s", id));
                        Assert.assertNull(recorder.get(id));
                        recorder.put(id, true);
                    }
            );
            latch.countDown();
        }
        ).start());

        latch.await(20, TimeUnit.SECONDS);
    }
}