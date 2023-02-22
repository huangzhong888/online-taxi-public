package com.hz.internal.common.response;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2023/2/21 - 02 - 21 - 22:08
 * @Description: com.hz.internal.common.response
 * @version: 1.0
 */
@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private int ifExists;
}
