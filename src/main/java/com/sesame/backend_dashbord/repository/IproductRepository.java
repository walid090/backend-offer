package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductRepository extends CrudRepository<Product,Integer > {
}
