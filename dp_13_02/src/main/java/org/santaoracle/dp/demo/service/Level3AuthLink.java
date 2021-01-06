package org.santaoracle.dp.demo.service;

import org.santaoracle.dp.demo.pojo.AuthResult;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
public class Level3AuthLink extends AuthLink {

    public static final String UID = "100013";
    public static final String USERNAME = "王工";
    private static LocalDateTime START = LocalDateTime.of(2021, 6, 1, 0, 0);
    private static LocalDateTime END = LocalDateTime.of(2021, 6, 25, 0, 0);

    public Level3AuthLink(String uid, String username) {
        super(uid, username);
    }

    @Override
    public AuthResult queryAuthResult(String orderId, LocalDateTime time) {
        LocalDateTime operateTime = AuthService.queryAuthInfo(UID, orderId);
        if (Objects.isNull(operateTime)) {
            return new AuthResult()
                    .setOrderId(orderId)
                    .setStatus("待三级节点审批！")
                    .setUidToAuth(UID);
        }

        AuthLink next = super.getNext();
        if (Objects.isNull(next) || time.isBefore(START) || time.isAfter(END)) {
            return buildDoneResult(orderId);
        }

        return next.queryAuthResult(orderId, time);
    }
}
