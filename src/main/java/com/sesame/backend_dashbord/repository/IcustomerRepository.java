package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcustomerRepository extends CrudRepository<Customer,Integer> {
}
