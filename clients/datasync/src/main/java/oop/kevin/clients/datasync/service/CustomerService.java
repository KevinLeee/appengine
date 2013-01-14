package oop.kevin.clients.datasync.service;

import oop.kevin.clients.datasync.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-29
 * Time: 下午6:30
 * <p/>
 */
public interface CustomerService {

    List<Customer> findAllCustomer();

    List findAllOrders();
}
