package org.santaoracle.dp.demo.service;

import org.santaoracle.dp.demo.pojo.AuthResult;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
public class Level1AuthLink extends AuthLink {

    public static final String UID = "100011";
    public static final String USERNAME = "李总";

    public Level1AuthLink(String uid, String username) {
        super(uid, username);
    }

    @Override
    public AuthResult queryAuthResult(String orderId, LocalDateTime time) {
        LocalDateTime operateTime = AuthService.queryAuthInfo(UID, orderId);
        if (Objects.isNull(operateTime)) {
            return new AuthResult()
                    .setOrderId(orderId)
                    .setStatus("待一级节点审批！")
                    .setUidToAuth(UID);
        }

        return buildDoneResult(orderId);
    }

}
