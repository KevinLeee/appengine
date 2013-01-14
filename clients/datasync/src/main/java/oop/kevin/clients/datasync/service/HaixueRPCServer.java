
package oop.kevin.clients.datasync.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-26
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 *
 */
public interface HaixueRPCServer {


    public String sayHaixueServer(String serverAddress);

    public ResultSet findAllCustomer(String sql) throws SQLException;

    public List receiveCustomerData();


}
