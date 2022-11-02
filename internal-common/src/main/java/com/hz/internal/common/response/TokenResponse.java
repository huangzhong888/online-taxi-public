package com.hz.internal.common.response;

import lombok.Data;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/30 - 10 - 30 - 16:23
 * @Description: com.hz.internal.common.response
 * @version: 1.0
 */

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
