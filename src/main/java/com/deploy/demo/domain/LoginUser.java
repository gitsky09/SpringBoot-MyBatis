package com.deploy.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUser {

	private Integer id;

    private String account;

    private String jwt;
    
}
