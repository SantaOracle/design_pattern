package org.santaoracle.dp.demo.biz;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
public class Ent4HystrixBiz {

    public static Map<String, LocalDateTime> BANED_MAP = new ConcurrentHashMap<>();

    public static void ban(String provider) {
        BANED_MAP.put(provider, LocalDateTime.now());
    }

    public static Set<String> queryAll() {
        return BANED_MAP.keySet();
    }

    public static boolean contains(String provider) {
        return BANED_MAP.containsKey(provider);
    }
}
