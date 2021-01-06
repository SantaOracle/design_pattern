package org.santaoracle.dp.demo.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangpeiheng create on 2021/1/4
 */
public class AuthService {

    // 审批记录
    private static Map<String, LocalDateTime> authMap = new
            ConcurrentHashMap<String, LocalDateTime>();

    /**
     * 查询审批记录
     *
     * @param uId
     * @param orderId
     * @return
     */
    public static LocalDateTime queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }

    /**
     * 执行审批
     *
     * @param uId
     * @param orderId
     */
    public static void auth(String uId, String orderId) {
        authMap.put(uId.concat(orderId), LocalDateTime.now());
    }
}
