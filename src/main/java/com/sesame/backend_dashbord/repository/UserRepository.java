package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
