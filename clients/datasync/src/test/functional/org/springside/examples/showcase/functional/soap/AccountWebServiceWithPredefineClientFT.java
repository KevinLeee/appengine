package oop.kevin.clients.datasync.functional.soap;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import oop.kevin.clients.datasync.data.UserData;
import oop.kevin.clients.datasync.entity.User;
import oop.kevin.clients.datasync.functional.BaseFunctionalTestCase;
import oop.kevin.clients.datasync.webservice.soap.AccountSoapService;
import oop.kevin.clients.datasync.webservice.soap.response.GetUserResult;
import oop.kevin.clients.datasync.webservice.soap.response.SearchUserResult;
import oop.kevin.clients.datasync.webservice.soap.response.base.IdResult;
import oop.kevin.clients.datasync.webservice.soap.response.base.WSResult;
import oop.kevin.clients.datasync.webservice.soap.response.dto.UserDTO;
import oop.kevin.service.mapper.BeanMapper;
import oop.kevin.service.test.category.Smoke;

/**
 * AccountService Web服务的功能测试, 测试主要的接口调用.
 * 
 * 使用在Spring applicaitonContext.xml中用<jaxws:client/>，根据AccountWebService接口创建的Client.
 * 
 * 集中在User相关接口.
 * 
 * @author Michael·Lee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-soap-client.xml" })
public class AccountWebServiceWithPredefineClientFT extends BaseFunctionalTestCase {

	@Autowired
	private AccountSoapService accountWebServiceClient;

	/**
	 * 测试获取用户.
	 */
	@Test
	@Category(Smoke.class)
	public void getUser() {
		GetUserResult response = accountWebServiceClient.getUser(1L);
		assertEquals("admin", response.getUser().getLoginName());
	}

	/**
	 * 测试搜索用户
	 */
	@Test
	public void searchUser() {

		SearchUserResult response = accountWebServiceClient.searchUser(null, null);

		assertTrue(response.getUserList().size() >= 4);
		assertEquals("Admin", response.getUserList().get(0).getName());
	}

	/**
	 * 测试创建用户.
	 */
	@Test
	public void createUser() {
		User user = UserData.randomUser();
		UserDTO userDTO = BeanMapper.map(user, UserDTO.class);

		IdResult response = accountWebServiceClient.createUser(userDTO);
		assertNotNull(response.getId());
		GetUserResult response2 = accountWebServiceClient.getUser(response.getId());
		assertEquals(user.getLoginName(), response2.getUser().getLoginName());
	}

	/**
	 * 测试创建用户,使用错误的登录名.
	 */
	@Test
	public void createUserWithInvalidLoginName() {
		User user = UserData.randomUser();
		UserDTO userDTO = BeanMapper.map(user, UserDTO.class);

		//登录名为空
		userDTO.setLoginName(null);
		IdResult response = accountWebServiceClient.createUser(userDTO);
		assertEquals(WSResult.PARAMETER_ERROR, response.getCode());

		//登录名重复
		userDTO.setLoginName("user");
		response = accountWebServiceClient.createUser(userDTO);
		assertEquals(WSResult.PARAMETER_ERROR, response.getCode());
	}
}
