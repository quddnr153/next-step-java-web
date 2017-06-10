package com.bw.jwp.next.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bw.jwp.core.db.DataBase;
import com.bw.jwp.next.model.User;

/**
 * @author Byungwook, Lee
 */
public class UserServiceTest {
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
		User user1 = new User("userId1", "password1", "name1", "email1");
		User user2 = new User("userId2", "password2", "name2", "email2");
		User user3 = new User("userId3", "password3", "name3", "email3");
		User user4 = new User("userId4", "password4", "name4", "email4");
		DataBase.addUser(user1);
		DataBase.addUser(user2);
		DataBase.addUser(user3);
		DataBase.addUser(user4);
	}

	@After
	public void tearDown() throws Exception {
		DataBase.deleteUser("userId1");
		DataBase.deleteUser("userId2");
		DataBase.deleteUser("userId3");
		DataBase.deleteUser("userId4");
	}

	@Test
	public void testCreateUser() {
		// Given
		User user5 = new User("userId5", "password5", "name5", "email5");

		// When
		DataBase.addUser(user5);

		// Then
		assertEquals("user 가 추가 됐으므로 admin 사용자 포함하여 총 사용자는 6명이어야한다.", DataBase.findAll().size(), 6);
		assertEquals("user 등록 확인.", DataBase.findUserById("userId5"), user5);
	}

	@Test
	public void testIsRightUser_Right() {
		// Given
		User user = new User();
		user.setUserId("userId1");
		user.setPassword("password1");

		// When
		boolean actual = userService.login(user);

		// Then
		assertTrue("사용자와 비밀번호가 일치하므로 true 를 return 한다..", actual);
	}

	@Test
	public void testIsRightUser_Password_Not_Correct() {
		// Given
		User user = new User();
		user.setUserId("userId1");
		user.setPassword("password5");

		// When
		boolean actual = userService.login(user);

		// Then
		assertFalse("사용자와 비밀번호가 틀리므로 false 를 return 한다..", actual);
	}

	@Test
	public void testIsRightUser_UserId_Not_Exist() {
		// Given
		User user = new User();
		user.setUserId("userId5");
		user.setPassword("password5");

		// When
		boolean actual = userService.login(user);

		// Then
		assertFalse("사용자 아이디가 없으므로 false 를 return 한다..", actual);
	}

	@Test
	public void testGetUsers() {
		// Given
		List<User> expected = new ArrayList<>();
		expected.add(new User("userId1", "password1", "name1", "email1"));
		expected.add(new User("userId2", "password2", "name2", "email2"));
		expected.add(new User("userId3", "password3", "name3", "email3"));
		expected.add(new User("userId4", "password4", "name4", "email4"));
		expected.add(new User("admin", "admin", "admin", "admin@admin.com"));

		// When
		List<User> actual = userService.getUsers();

		// Then
		assertEquals("User 가 몇명이지", 5, actual.size());
	}

	@Test
	public void testUpdate() {
		// Given
		List<User> expected = new ArrayList<>();
		expected.add(new User("userId1", "password1", "name1", "email1"));
		expected.add(new User("userId2", "password2", "name2", "email2"));
		expected.add(new User("userId3", "password3", "name3", "email3"));
		expected.add(new User("userId4", "password4", "name4", "email4"));
		expected.add(new User("admin", "admin", "admin", "admin@admin.com"));

		User updatedUser = new User("userId4", "password6", "name6", "email6");

		// When
		userService.update(updatedUser);

		// Then
		assertEquals("update 하면 db에 덮어쓴다.", DataBase.findUserById("userId4"), updatedUser);
	}
}
