package org.santaoracle.dp.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
@Data
@Accessors(chain = true)
public class Ent4CertResp {

    private boolean pass;
    private String desc;
    private String provider;
    private String orderNum;
}
