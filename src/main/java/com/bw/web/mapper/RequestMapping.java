package com.bw.web.mapper;

import java.util.Map;

import com.bw.web.controller.Controller;
import com.bw.web.controller.CreateUserController;
import com.bw.web.controller.ListUserController;
import com.bw.web.controller.LoginController;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class RequestMapping {
	public static Map<String, Controller> mapper = Maps.newHashMap();

	static {
		mapper.put("/user/create", new CreateUserController());
		mapper.put("/user/list", new ListUserController());
		mapper.put("/user/login.html", new LoginController());
		mapper.put("/user/login", new LoginController());
		mapper.put("/user/form.html", new CreateUserController());
	}

	public static Controller getController(final String url) {
		return mapper.get(url);
	}
}
