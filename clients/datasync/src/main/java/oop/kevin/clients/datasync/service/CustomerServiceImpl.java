package oop.kevin.clients.datasync.service;

import oop.kevin.clients.datasync.entity.Customer;
import oop.kevin.clients.datasync.repository.mybatis.CustomerDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-29
 * Time: 下午6:31
 * <p/>
 * Transactional表示该类被Spring作为管理事务的类，@Resource引入一个Spring定义的资源，资源名为menuMapper（name值），即为第七步定义的映射类。
 */
@Resource(name = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAllCustomer() {
        String sql = "select * from cus_customer_tbl";
        return customerDao.operateReturnBeans(sql);
    }

    @Override
    public List findAllOrders() {
        return null;
    }

    public static void main(String[] args) {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.findAllCustomer();
    }
}
