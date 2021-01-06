package org.santaoracle.dp.demo.service;

import lombok.Getter;
import org.santaoracle.dp.demo.pojo.AuthResult;

import java.time.LocalDateTime;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
public abstract class AuthLink {

    protected String levelUid;
    protected String levelUserName;

    @Getter
    private AuthLink next;

    public AuthLink(String uid, String username) {
        this.levelUid = uid;
        this.levelUserName = username;
    }

    public AuthLink appendNext(AuthLink node) {
        this.next = node;
        return this;
    }

    public abstract AuthResult queryAuthResult(String orderId, LocalDateTime time);

    protected AuthResult buildDoneResult(String orderId) {
        return new AuthResult()
                .setOrderId(orderId)
                .setStatus("审批完成！");
    }
}
