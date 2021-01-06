package org.santaoracle.dp.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
@Slf4j
public class AuthBizTest {

    public static final String ORDER_ID = "DS001";

    @Test
    public void test() {
        AuthLink link = new Level3AuthLink(Level3AuthLink.UID, Level3AuthLink.USERNAME)
                .appendNext(new Level2AuthLink(Level2AuthLink.UID, Level2AuthLink.USERNAME)
                        .appendNext(new Level1AuthLink(Level1AuthLink.UID, Level1AuthLink.USERNAME)));

        LocalDateTime operateTime = LocalDateTime.of(2021, 6, 13, 12, 0);

        log.info("审批结果：{}", link.queryAuthResult(ORDER_ID, operateTime));

        // 模拟三级审批
        AuthService.auth(Level3AuthLink.UID, ORDER_ID);
        log.info("审批结果：{}", link.queryAuthResult(ORDER_ID, operateTime));

        // 模拟二级审批
        AuthService.auth(Level2AuthLink.UID, ORDER_ID);
        log.info("审批结果：{}", link.queryAuthResult(ORDER_ID, operateTime));

        // 模拟一级审批
        AuthService.auth(Level1AuthLink.UID, ORDER_ID);
        log.info("审批结果：{}", link.queryAuthResult(ORDER_ID, operateTime));
    }
}