package com.bw.web.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bw.web.db.DataBase;
import com.bw.web.model.User;

/**
 * @author Byungwook, Lee
 *
 */
public class UserServiceTest {
	private UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		userService = new UserService();
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
		assertEquals("user가 추가 됐으므로 총 사용자는 5명이어야한다.", DataBase.findAll().size(), 5);
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
		assertTrue("사용자와 비밀번호가 일치하므로 true를 return 한다..", actual);
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
		assertFalse("사용자와 비밀번호가 틀리므로 flase를 return 한다..", actual);
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
		assertFalse("사용자 아이디가 없으므로 flase를 return 한다..", actual);
	}
}
