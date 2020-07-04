package org.santaoracle.design.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * double check单例
 *
 * @author jiangpeiheng create on 2020/7/4
 */
public class MySingleton {

    /**
     * 不需要考虑加volatile
     * 获取半初始化对象的问题，在高版本jdk已解决
     * 解决思路：开辟对象空间 + 初始化对象 + 返回对象引用已在jdk成为原子操作
     */
    private static MySingleton instance;
    private AtomicLong gener = new AtomicLong(0);

    private MySingleton() {}

    public static MySingleton getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    public static void init() {
        if (instance != null) {
            return;
        }
        synchronized (MySingleton.class) {
            if (instance != null) {
                return;
            }
            instance = new MySingleton();
        }
    }

    public long genId() {
        return gener.getAndIncrement();
    }

}
