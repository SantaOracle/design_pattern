package org.santaoracle.dp.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jiangpeiheng create on 2021/1/6
 */
@Data
@Accessors(chain = true)
public class Ent4CertReq {

    private String name;
    private String mobile;
    private String idCard;
    private String bankCard;
}
