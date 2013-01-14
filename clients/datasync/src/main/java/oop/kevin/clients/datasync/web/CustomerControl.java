package oop.kevin.clients.datasync.web;

import oop.kevin.clients.datasync.entity.Customer;
import oop.kevin.clients.datasync.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-11-30
 * Time: 下午1:57
 * <p/>
 */
@Controller
public class CustomerControl extends MultiActionController {
    private CustomerService customerService;
    @Resource
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();
          List<Customer> customers = customerService.findAllCustomer();
        for (Customer customer : customers) {
            System.out.println(customer.getCusName() + "===============");
        }
    }
}
