package oop.kevin.clients.datasync;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-6
 * Time: 下午1:34
 * <
 * p/>
 */
@Service
public class ResolveDataAndAsync {
    private static Logger logger = LoggerFactory.getLogger(ResolveDataAndAsync.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final ThreadLocal<MemcachedClient> memcachedClient = new ThreadLocal<MemcachedClient>() {
        @Override
        protected MemcachedClient initialValue() {
            try {
                return new MemcachedClient(new InetSocketAddress("10.188.189.181", 12000));
            } catch (IOException e) {
                logger.warn("memcached server is issue.....");
                e.printStackTrace();
            }
            return null;
        }
    };

    /**
     * 加载用户表所有数据
     * @param lastCutOfTime
     * @return
     */
    public List receiveCustomerData(String lastCutOfTime) {
        String sql = "select * from cus_customer_tbl limit 0, 20";
        String sql_select = "select CUS_PWD, EMAIL, MOBILE, SEX, SUBJECT_ID, REG_TIME, CUS_FROM_URL, CUS_WEB_FROM, FROMTYPE from CUS_CUSTOMER_TBL CUS ";
        String sql_where = " where CUS.REG_TIME > ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql_select);
//        stringBuilder.append(sql_where);
//        stringBuilder.append(lastCutOfTime);
        System.out.println(stringBuilder.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = simpleDateFormat.format(date);

        long beginstr = System.currentTimeMillis();
        List list = jdbcTemplate.queryForList(stringBuilder.toString());
        System.out.println(list.size() + "-----------");
                                                 long endTimes = System.currentTimeMillis();
        logger.info("开始时间:{}, 结束时间:{}", beginstr, endTimes);

        System.out.println("执行时长====" +  String.valueOf(endTimes-beginstr));
        if (list != null && list.size() > 0) {
            return list;
            //System.out.println("list========" + list.size());
            //logger.info("-----------receive data -------------");
            // long currentedKey =  System.currentTimeMillis();
            //memcachedClient.get().set(currentedKey, list);
        }
        return null;
    }

    /**
     * 加载订单表数据
     * @param userId
     * @return
     */
    public List receiveOrdersData(String userId) {
        String sql = "select * from finance_account_tbl limit 0, 20";
        String sql_select = "select * from FINANCE_ACCOUNT_TBL  ";
        String sql_where = " where CUS.REG_TIME > ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql_select);
        stringBuilder.append(sql_where);
//        stringBuilder.append(lastCutOfTime);
        System.out.println(stringBuilder.toString());
        return null;
    }

    public static void initSpringContainer() {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
//        classPathXmlApplicationContext.start();
        ApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
//       initSpringContainer();
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");

//        ResolveDataAndAsync rdaa = new ResolveDataAndAsync();
        String lastEndTime = " '2012-12-06 12:39:28 '";
        ResolveDataAndAsync rdaa = (ResolveDataAndAsync) ctx.getBean("resolveDataAndAsync");
        rdaa.receiveCustomerData(lastEndTime);
    }


}
