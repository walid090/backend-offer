package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsupplierRepository extends CrudRepository<Supplier, Integer> {
}
