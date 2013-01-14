package oop.kevin.service.test.shiro;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import oop.kevin.service.test.security.shiro.ShiroTestUtils;

public class ShiroTestUtilsTest {

	@Test
	public void mockSubject() {
		ShiroTestUtils.mockSubject("foo");
		assertTrue(SecurityUtils.getSubject().isAuthenticated());
		assertEquals("foo", SecurityUtils.getSubject().getPrincipal());

		ShiroTestUtils.clearSubject();

		ShiroTestUtils.mockSubject("bar");
		assertTrue(SecurityUtils.getSubject().isAuthenticated());
		assertEquals("bar", SecurityUtils.getSubject().getPrincipal());

	}

}
