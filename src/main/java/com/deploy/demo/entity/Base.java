package com.deploy.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Base {

	private String crUser;

	private LocalDateTime crDateTime;

	private String upUser;

	private LocalDateTime upDateTime;
	
}
