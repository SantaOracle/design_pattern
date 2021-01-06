package org.santaoracle.dp.demo.biz;

import org.santaoracle.dp.demo.pojo.Ent4CertReq;
import org.santaoracle.dp.demo.pojo.Ent4CertResp;

import java.util.Objects;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
public abstract class AbstractEnt4CertLink {

    private AbstractEnt4CertLink next;

    protected abstract boolean canProcess();

    protected abstract Ent4CertResp doCert(Ent4CertReq req);

    public AbstractEnt4CertLink appendNext(AbstractEnt4CertLink next) {
        this.next = next;
        return this;
    }

    public Ent4CertResp cert(Ent4CertReq req) {
        Ent4CertResp resp = null;
        if (canProcess()) {
            resp = doCert(req);
        }

        if (Objects.isNull(resp) && Objects.nonNull(next)) {
            resp = next.cert(req);
        }

        if (Objects.isNull(resp)) {
            throw new RuntimeException("所有服务商故障！");
        }

        return resp;
    }
}
