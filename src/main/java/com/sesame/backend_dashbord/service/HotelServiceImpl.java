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

    @Autowired
    private UserRepository UserRepository;


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

    public Map<Date, Integer> CountReservationsbyByMonths(Integer Months) {
        Iterable<Reservation> reservations = reservationRepository.findAll();
        Map<Date, Integer> map = new HashMap<>();

        for (Reservation reservation : reservations) {
            Date reservationDate = reservation.getDate();

            // Ensure the month matches
            if (reservationDate.getMonth() == Months) {
                // Normalize the date (strip time information)
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(reservationDate);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Date normalizedDate = calendar.getTime();

                // Add to the map
                map.put(normalizedDate, map.getOrDefault(normalizedDate, 0) + 1);
            }
        }

        return map;
    }

    @Override
    public User AddUser(User user) {
        return UserRepository.save(user);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public boolean deleteImage(String imageName) {
        try {
            File file = Paths.get(UPLOAD_DIR, imageName).toFile();
            if (file.exists()) {
                return file.delete(); // Delete the image file
            }
            return false; // File doesn't exist
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

