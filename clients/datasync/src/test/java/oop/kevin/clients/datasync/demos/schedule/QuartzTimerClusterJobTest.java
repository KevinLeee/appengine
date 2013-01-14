package oop.kevin.clients.datasync.demos.schedule;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import oop.kevin.service.test.category.UnStable;
import oop.kevin.service.test.log.Log4jMockAppender;
import oop.kevin.service.test.spring.SpringTransactionalTestCase;
import oop.kevin.service.utils.Threads;

/**
 * Quartz可集群Timer Job测试.
 * 
 * @author Michael·Lee
 */
@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml", "/schedule/applicationContext-quartz-timer-cluster.xml" })
public class QuartzTimerClusterJobTest extends SpringTransactionalTestCase {

	private static Log4jMockAppender appender;

	@BeforeClass
	public static void initLogger() {
		// 加载测试用logger appender
		appender = new Log4jMockAppender();
		appender.addToLogger(QuartzClusterableJob.class);
	}

	@AfterClass
	public static void removeLogger() {
		appender.removeFromLogger(QuartzClusterableJob.class);
	}

	@Test
	public void scheduleJob() throws Exception {
		// 等待任务延时2秒启动并执行完毕
		Threads.sleep(4000);

		// 验证任务已执行
		assertEquals(1, appender.getLogsCount());

		assertEquals("There are 6 user in database, printed by quartz cluster job on node default.",
				appender.getFirstMessage());
	}
}
