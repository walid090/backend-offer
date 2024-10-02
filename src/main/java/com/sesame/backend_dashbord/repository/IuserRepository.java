package com.sesame.backend_dashbord.repository;


import com.sesame.backend_dashbord.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IuserRepository extends CrudRepository<User, Integer> {
}
