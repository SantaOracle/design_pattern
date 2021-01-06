package org.santaoracle.dp.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jiangpeiheng create on 2021/1/5
 */
@Data
@Accessors(chain = true)
public class AuthResult {

    private String orderId;

    private String status;

    /**
     * 下一节点待审批的uid
     */
    private String uidToAuth;
}
