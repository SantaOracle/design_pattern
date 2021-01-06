package org.santaoracle.dp.demo.biz;

import org.santaoracle.dp.demo.pojo.Ent4CertReq;
import org.santaoracle.dp.demo.pojo.Ent4CertResp;

import java.util.UUID;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
public class AnnlopEnt4 extends AbstractEnt4CertLink {

    public static final String PROVIDER_NAME = "ANNLOP";

    @Override
    protected boolean canProcess() {
        return !Ent4HystrixBiz.contains(PROVIDER_NAME);
    }

    @Override
    protected Ent4CertResp doCert(Ent4CertReq req) {
        return new Ent4CertResp()
                .setPass(true)
                .setDesc("校验通过")
                .setProvider(PROVIDER_NAME)
                .setOrderNum(req.getName().hashCode() + "_" + UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
