package org.santaoracle.dp.demo.service;

import org.apache.commons.lang.StringUtils;
import org.santaoracle.dp.demo.pojo.AuthResult;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
public class AuthResultService {

    public static final String LEVEL_3_UID = "100013";
    public static final String LEVEL_2_UID = "100012";
    public static final String LEVEL_1_UID = "100011";

    private static LocalDateTime LEVEL_2_START = LocalDateTime.of(2021, 6, 1, 0, 0);
    private static LocalDateTime LEVEL_2_END = LocalDateTime.of(2021, 6, 25, 0, 0);

    private static LocalDateTime LEVEL_1_START = LocalDateTime.of(2021, 6, 11, 0, 0);
    private static LocalDateTime LEVEL_1_END = LocalDateTime.of(2021, 6, 20, 0, 0);

    public static AuthResult queryAuthResult(String orderId, LocalDateTime time) {
        // 校验三级审批
        LocalDateTime authTime = AuthService.queryAuthInfo(LEVEL_3_UID, orderId);
        if (Objects.isNull(authTime)) {
            return new AuthResult()
                    .setOrderId(orderId)
                    .setUidToAuth(LEVEL_3_UID)
                    .setStatus("待三级审批人员审批！");
        }
        if (time.isBefore(LEVEL_2_START) || time.isAfter(LEVEL_2_END)) {
            return buildDoneResult(orderId);
        }

        // 校验二级审批
        authTime = AuthService.queryAuthInfo(LEVEL_2_UID, orderId);
        if (Objects.isNull(authTime)) {
            return new AuthResult()
                    .setOrderId(orderId)
                    .setUidToAuth(LEVEL_2_UID)
                    .setStatus("待二级审批人员审批！");
        }
        if (time.isBefore(LEVEL_1_START) || time.isAfter(LEVEL_1_END)) {
            return buildDoneResult(orderId);
        }

        // 校验一级审批
        authTime = AuthService.queryAuthInfo(LEVEL_1_UID, orderId);
        if (Objects.isNull(authTime)) {
            return new AuthResult()
                    .setOrderId(orderId)
                    .setUidToAuth(LEVEL_1_UID)
                    .setStatus("待一级审批人员审批！");
        }

        return buildDoneResult(orderId);
    }

    private static AuthResult buildDoneResult(String orderId) {
        return new AuthResult()
                .setOrderId(orderId)
                .setUidToAuth(StringUtils.EMPTY)
                .setStatus("审批完成！");
    }
}
