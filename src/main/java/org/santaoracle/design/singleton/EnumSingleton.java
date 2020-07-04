package org.santaoracle.design.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 枚举单例
 *
 * @author jiangpeiheng create on 2020/7/4
 */
public enum EnumSingleton {

    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public long genId() {
        return id.getAndIncrement();
    }
}
