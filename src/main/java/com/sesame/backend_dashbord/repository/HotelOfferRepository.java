package com.sesame.backend_dashbord.repository;

import com.sesame.backend_dashbord.entity.HotelOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelOfferRepository extends CrudRepository<HotelOffer, Long> {
}
