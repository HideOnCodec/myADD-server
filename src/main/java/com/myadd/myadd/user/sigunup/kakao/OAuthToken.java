package com.myadd.myadd.user.sigunup.kakao;

import lombok.Data;

@Data
public class OAuthToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String refresh_token_expires_in;

    // body 파싱 결과
    //  access_token: "vZ7bkplfgxl-9Wi0T1C_JeLGWvkzZIZatDXqN91vCj1zTgAAAYmV2PDg",
    //  token_type: "bearer",
    //  refresh_token: "D23i7P1iRmbqcWIQu66dgON6eGY7qtrRPgvNtjYhCj1zTgAAAYmV2PDf",
    //  expires_in: 21599,
    //  scope: "account_email profile_image profile_nickname",
    //  refresh_token_expires_in: 5183999
}
