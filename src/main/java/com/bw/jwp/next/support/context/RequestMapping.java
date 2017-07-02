package com.bw.jwp.next.support.context;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.next.controller.Controller;
import com.bw.jwp.next.controller.ForwardController;
import com.bw.jwp.next.controller.HomeController;
import com.bw.jwp.next.controller.qna.CreateAnswerController;
import com.bw.jwp.next.controller.qna.CreateQuestionController;
import com.bw.jwp.next.controller.qna.GetQuestionController;
import com.bw.jwp.next.controller.user.CreateUserController;
import com.bw.jwp.next.controller.user.ListUserController;
import com.bw.jwp.next.controller.user.LoginController;
import com.bw.jwp.next.controller.user.LogoutController;
import com.bw.jwp.next.controller.user.ProfileController;
import com.bw.jwp.next.controller.user.UpdateFormController;
import com.bw.jwp.next.controller.user.UpdateUserController;
import com.google.common.collect.Maps;

/**
 * @author Byungwook, Lee
 */
public class RequestMapping {
	private static final Logger LOG = LoggerFactory.getLogger(RequestMapping.class);

	private Map<String, Controller> mappings = Maps.newHashMap();

	void initMapping() {
		mappings.put("/home", new HomeController());
		mappings.put("/users/form", new ForwardController("/user/form.jsp"));
		mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
		mappings.put("/users", new ListUserController());
		mappings.put("/users/login", new LoginController());
		mappings.put("/users/profile", new ProfileController());
		mappings.put("/users/logout", new LogoutController());
		mappings.put("/users/create", new CreateUserController());
		mappings.put("/users/updateForm", new UpdateFormController());
		mappings.put("/users/update", new UpdateUserController());
		mappings.put("/qna/create", new CreateQuestionController());
		mappings.put("/qna/form", new ForwardController("/qna/form.jsp"));
		mappings.put("/qna/show", new GetQuestionController());
		mappings.put("/api/qna/addAnswer", new CreateAnswerController());

		LOG.info("initiate mappings");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}
}
