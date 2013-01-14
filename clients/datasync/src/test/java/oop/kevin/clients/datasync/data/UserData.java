package oop.kevin.clients.datasync.data;

import oop.kevin.clients.datasync.entity.Role;
import oop.kevin.clients.datasync.entity.User;
import oop.kevin.service.test.data.RandomData;

/**
 * 用户测试数据生成.
 * 
 * @author Michael·Lee
 */
public class UserData {

	public static User randomUser() {
		String userName = RandomData.randomName("User");

		User user = new User();
		user.setLoginName(userName);
		user.setName(userName);
		user.setPlainPassword("123456");
		user.setEmail(userName + "@sunland.org.cn");

		return user;
	}

	public static User randomUserWithAdminRole() {
		User user = UserData.randomUser();
		Role adminRole = UserData.adminRole();
		user.getRoleList().add(adminRole);
		return user;
	}

	public static Role adminRole() {
		Role role = new Role();
		role.setId(1L);
		role.setName("Admin");

		return role;
	}
}
