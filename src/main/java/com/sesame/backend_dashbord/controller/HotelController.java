package com.sesame.backend_dashbord.controller;


import com.sesame.backend_dashbord.entity.HotelOffer;
import com.sesame.backend_dashbord.entity.Reservation;
import com.sesame.backend_dashbord.entity.User;
import com.sesame.backend_dashbord.service.HotelServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/offers")

public class HotelController {
    @Autowired
    private HotelServiceImpl hotelService;

    @GetMapping("/getAllHotelOffers")
    public Iterable<HotelOffer> getAllHotelOffers() {
        return hotelService.getAllHotelOffers();
    }

    @GetMapping("/getHotelOfferById/{id}")
    public Optional<HotelOffer> getHotelOfferById(@PathVariable("id") Long id) {
        return hotelService.getHotelOfferById(id);
    }

    @PostMapping("/createHotelOffer")
    public HotelOffer createHotelOffer(@RequestBody HotelOffer hotelOffer) {
        return hotelService.createHotelOffer(hotelOffer);
    }
    @PostMapping("/createReservation")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return hotelService.createReservation(reservation);
    }

    @PutMapping("/updateHotelOffer")
    public HotelOffer updateHotelOffer(@RequestBody HotelOffer hotelOffer) {
        return hotelService.updateHotelOffer(hotelOffer);
    }

    @PostMapping("/AddUser")
    public User addUser(@RequestBody User user) {
           return hotelService.AddUser(user);
    }

    @DeleteMapping("/deleteHotelOffer/{id}")
    public void deleteHotelOffer(@PathVariable("id") Long id) {
        hotelService.deleteHotelOffer(id);
    }

    @PostMapping("/Auth")
    public User auth(@RequestBody User user) {
        return hotelService.Auth(user);
    }

    // Upload an image and save it
    @PostMapping("/uploadImage")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            String fileName = hotelService.saveImage(file);
            response.put("message", "Image uploaded successfully");
            response.put("fileName", fileName);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("error", "Error uploading image: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/GetAllResrvations")
    public Iterable<Reservation> getAllResrvations() {
        return hotelService.getAllReservedHotelOffers();
    }

    @PutMapping("/UpdateReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return hotelService.UpdateReservation(reservation);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id") Long id) {
        hotelService.deleteReservation(id);
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        String imagePath = "src/main/resources/assets/" + imageName;
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            System.out.println("File does not exist");
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        return ResponseEntity.ok().body(imageBytes);
    }

    @GetMapping("/CountReservationsbyStatus")
    public Map CountReservationsbyStatus(){
        return hotelService.CountReservationsbyStatus();
    }

    @GetMapping("/CountReservationsbyByMonths/{months}")
        public Map CountReservationsbyByMonths(@PathVariable("months") int months){
        return hotelService.CountReservationsbyByMonths(months);
        }

    @DeleteMapping("images/{imageName}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageName) {
        boolean isDeleted = hotelService.deleteImage(imageName);
        if (isDeleted) {
            return ResponseEntity.ok("Image deleted successfully");
        } else {
            return ResponseEntity.status(500).body("Error deleting image");
        }
    }
}


