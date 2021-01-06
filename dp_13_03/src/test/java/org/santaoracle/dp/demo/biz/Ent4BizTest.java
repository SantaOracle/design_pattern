package org.santaoracle.dp.demo.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.santaoracle.dp.demo.pojo.Ent4CertReq;
import org.santaoracle.dp.demo.pojo.Ent4CertResp;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
@Slf4j
public class Ent4BizTest {

    @Test
    public void test() {
        // 构建调用降级链，这个理论上可以再加一个manager来做
        AbstractEnt4CertLink ent4Link = new DatapayEnt4()
                .appendNext(new SkySearchEnt4()
                        .appendNext(new AnnlopEnt4()));

        Ent4CertReq req = new Ent4CertReq()
                .setName("John")
                .setMobile("15100910678")
                .setBankCard("6214834315480091")
                .setIdCard("450109199807140192");

        // 未降级阶段调用
        Ent4CertResp resp = ent4Link.cert(req);
        log.info("未降级时调用，resp:{}", resp);

        // datapay降级
        Ent4HystrixBiz.ban(DatapayEnt4.PROVIDER_NAME);
        resp = ent4Link.cert(req);
        log.info("降级数据宝时调用，resp:{}", resp);

        // skysearch降级
        Ent4HystrixBiz.ban(SkySearchEnt4.PROVIDER_NAME);
        resp = ent4Link.cert(req);
        log.info("降级天眼查时调用，resp:{}", resp);

        // 安洛普降级
        Ent4HystrixBiz.ban(AnnlopEnt4.PROVIDER_NAME);
        resp = ent4Link.cert(req);
        log.info("降级安洛普时调用，resp:{}", resp);
    }

}