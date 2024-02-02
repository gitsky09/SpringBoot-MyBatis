package com.deploy.demo.utils;

public class ValidFormat {

	public static boolean isPhone(String text) {
		return text.matches("^09[0-9]{8}$");
	}

	public static boolean isEmail(String text) {
		return text.matches("^[_a-zA-Z0-9-]+([.][_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+([.][a-zA-Z0-9-]+)*$");
	}

	public static boolean isPassword(String text) {
		return text.matches("^.*(?=.{6,16})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?\\(\\)]).*$");
	}

}
