package com.qlazy.tmall.dto;

import java.util.Arrays;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
	private String name;
	private String password;
	private String salt;
	private String anonymousName;

//    设置匿名
	public String getAnonymousName() {
		if (!(null == anonymousName)) {
			return anonymousName;
		}
		if (null == name) {
			anonymousName = "";
		} else if (name.length() <= 1) {
			anonymousName = "*";
		} else if (name.length() > 1) {
			char[] temp = new char[name.length()];
			Arrays.fill(temp, '*');
			temp[0] = name.charAt(0);
			anonymousName = String.valueOf(temp);
		}
		return anonymousName;
	}
}
