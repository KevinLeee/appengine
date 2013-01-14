package oop.kevin.clients.datasync.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import oop.kevin.clients.datasync.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

}
