package oop.kevin.clients.datasync.service;

import com.caucho.hessian.server.HessianServlet;
import oop.kevin.clients.datasync.DatabaseConn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-26
 * Time: 下午9:13
 */
public class HaixueRPCServerImpl extends HessianServlet implements HaixueRPCServer {
    private String serverURL = "http://rpc.highso.org:8282";
    DatabaseConn databaseConn = new DatabaseConn();
    protected static final Logger logger = LoggerFactory.getLogger(HaixueRPCServer.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public String sayHaixueServer(String serverAddress) {
        return serverURL + serverAddress;
    }

    @Override
    public ResultSet findAllCustomer(String sql) throws SQLException {
//       String sql = "select * from cus_customer_tbl limit 0, 20";
        //jdbcTemplate.execute(sql);
        ResultSet resultSet = databaseConn.getQuery(sql);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + ", request from consumer: " + resultSet.getString("EMAIL"));
            }
        }
        return null;
    }

    /**
     * 加载用户表所有数据
     *
     * @param
     * @return
     */
    public List receiveCustomerData() {
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

        System.out.println("执行时长====" + String.valueOf(endTimes - beginstr));
        if (list != null && list.size() > 0) {
            return list;
            //System.out.println("list========" + list.size());
            //logger.info("-----------receive data -------------");
            // long currentedKey =  System.currentTimeMillis();
            //memcachedClient.get().set(currentedKey, list);
        }
        return null;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public void setDatabaseConn(DatabaseConn databaseConn) {
        this.databaseConn = databaseConn;
    }
}
