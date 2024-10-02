package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Integer> {
}
