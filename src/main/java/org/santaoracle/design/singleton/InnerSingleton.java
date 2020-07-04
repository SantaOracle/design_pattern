package org.santaoracle.design.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 静态内部类实现方式
 *
 * @author jiangpeiheng create on 2020/7/4
 */
public class InnerSingleton {

    private AtomicLong id = new AtomicLong(0);
    private InnerSingleton() {}

    /**
     * 仅当getInstance被调用时，holder才会被创建并初始化instance
     * 初始化的原子性由jvm保证
     */
    private static class SingletonHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public long genId() {
        return id.getAndIncrement();
    }
}
