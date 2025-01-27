package com.sesame.backend_dashbord.service;

import com.sesame.backend_dashbord.entity.HotelOffer;
import com.sesame.backend_dashbord.entity.Reservation;
import com.sesame.backend_dashbord.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


public interface HotelServiceInterface {
    Iterable<HotelOffer> getAllHotelOffers();
    Optional<HotelOffer> getHotelOfferById(Long id);
    HotelOffer createHotelOffer(HotelOffer hotelOffer);
    HotelOffer updateHotelOffer( HotelOffer hotelOffer);
    void deleteHotelOffer(Long id);
    User Auth(User user);
    String saveImage(MultipartFile file) throws IOException;
    Iterable<Reservation> getAllReservedHotelOffers();
    Reservation UpdateReservation(Reservation reservation);
    void deleteReservation(Long id);
    Map<String, Integer> CountReservationsbyStatus();
    Map<Date, Integer> CountReservationsbyByMonths(Integer Months);
    User AddUser(User user);
    Reservation createReservation(Reservation reservation);
    boolean deleteImage(String imageName);
}
