package org.santaoracle.dp.demo;

import org.junit.Test;
import org.santaoracle.dp.demo.pojo.AuthResult;
import org.santaoracle.dp.demo.service.AuthResultService;
import org.santaoracle.dp.demo.service.AuthService;

import java.time.LocalDateTime;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
public class AuthBizTest {

    private static final String ORDER_ID = "DS001";

    @Test
    public void test1() {
        // 审批/查询时间
        LocalDateTime operateTime = LocalDateTime.of(2021, 5, 21, 12, 0);
        AuthResult res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟三级审批
        AuthService.auth(AuthResultService.LEVEL_3_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());
    }

    @Test
    public void test2() {
        // 审批/查询时间
        LocalDateTime operateTime = LocalDateTime.of(2021, 6, 2, 12, 0);
        AuthResult res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟三级审批
        AuthService.auth(AuthResultService.LEVEL_3_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟二级审批
        AuthService.auth(AuthResultService.LEVEL_2_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());
    }

    @Test
    public void test3() {
        // 审批/查询时间
        LocalDateTime operateTime = LocalDateTime.of(2021, 6, 15, 12, 0);
        AuthResult res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟三级审批
        AuthService.auth(AuthResultService.LEVEL_3_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟二级审批
        AuthService.auth(AuthResultService.LEVEL_2_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());

        // 模拟一级审批
        AuthService.auth(AuthResultService.LEVEL_1_UID, ORDER_ID);
        res = AuthResultService.queryAuthResult(ORDER_ID, operateTime);
        System.out.println(res.getStatus());
    }
}