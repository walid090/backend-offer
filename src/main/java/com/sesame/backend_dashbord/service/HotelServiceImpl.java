package com.sesame.backend_dashbord.service;

import com.sesame.backend_dashbord.entity.HotelOffer;
import com.sesame.backend_dashbord.entity.Reservation;
import com.sesame.backend_dashbord.entity.User;
import com.sesame.backend_dashbord.repository.HotelOfferRepository;
import com.sesame.backend_dashbord.repository.ReservationRepository;
import com.sesame.backend_dashbord.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.*;

@Service
@AllArgsConstructor
public class HotelServiceImpl  implements HotelServiceInterface{
    private static final String UPLOAD_DIR = "src/main/resources/assets/";


    @Autowired
    private HotelOfferRepository hotelOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Iterable<HotelOffer> getAllHotelOffers() {
        return hotelOfferRepository.findAll();
    }

    @Override
    public Optional<HotelOffer> getHotelOfferById(Long id) {
        return hotelOfferRepository.findById(id);
    }

    @Override
    public HotelOffer createHotelOffer(HotelOffer hotelOffer) {
        return hotelOfferRepository.save(hotelOffer);

    }

    @Override
    public HotelOffer updateHotelOffer(HotelOffer hotelOffer) {
            return hotelOfferRepository.save(hotelOffer);
    }


    @Override
    public void deleteHotelOffer(Long id) {
        if (hotelOfferRepository.existsById(id)) {
            hotelOfferRepository.deleteById(id);
        } else {
            throw new RuntimeException("HotelOffer not found with id " + id);
        }
    }

    @Override
    public User Auth(User user) {

        List<User> list= (List<User>) userRepository.findAll();
        for (User u : list) {
            System.out.println(u.getUsername());
            if (u.getUsername().equals( user.getUsername()) && u.getPassword().equals(user.getPassword()) ) {
                System.out.println("true");
                return u;
            }
        }
        return null;
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());
        System.out.println(filePath);

        return fileName; // Return the saved file name
    }

    @Override
    public Iterable<Reservation> getAllReservedHotelOffers() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation UpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
         reservationRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> CountReservationsbyStatus() {
        Iterable<Reservation> reservations = reservationRepository.findAll();
        Map<String, Integer> map = new HashMap<>();
        int p=0;
        int r=0;
        int a=0;
        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getStatut(), "Padding")){
                p++;
                map.put("Padding",p);
            }
            if (Objects.equals(reservation.getStatut(), "Refuse")){
                r++;
                map.put("Refuse",r);
            }
            if (Objects.equals(reservation.getStatut(), "Accept")){
                a++;
                map.put("Accept",a);
            }
        }
        System.out.println(map);
        return map;
    }

    @Override
    public Map<Date, Integer> CountReservationsbyByMonths(Integer Months) {
        Iterable<Reservation> reservations = reservationRepository.findAll();
        Map<Date, Integer> map = new HashMap<>();
        int p=0;
        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getDate().getMonth(), Months)){
                Date day = reservation.getDate();
                // Strip the time information (optional, to just get the day part)
                day.setHours(0);
                day.setMinutes(0);
                day.setSeconds(0);
                map.put(day, map.getOrDefault(day, 0) + 1);
            }
        }
        return map;
    }

}

