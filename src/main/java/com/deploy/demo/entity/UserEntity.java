package com.deploy.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends Base {

	int id;

	String name;

	String account;

	String password;

	String checkPassword;

	String salt;

	String phone;

}
