package oop.kevin.clients.search.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import oop.kevin.clients.search.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
