package com.chetan.my_chef_now.service;

import com.chetan.my_chef_now.model.Admin;
import com.chetan.my_chef_now.model.Chef;
import com.chetan.my_chef_now.model.ChefBooking;
import com.chetan.my_chef_now.model.ChefDTO;
import com.chetan.my_chef_now.repository.ChefBookingRepo;
import com.chetan.my_chef_now.repository.ChefRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    ChefRepo chefRepo;
    
    @Autowired
    ChefBookingRepo chefBookingRepo;

    public ResponseEntity<String> registerChef(Chef chef) {
        chef.setChefStatus("Pending");
        try{
            chefRepo.save(chef);
            return new ResponseEntity<>("Chef Register Successfully..!!",HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Chef>> getAllChef() {
        List<Chef> chefs = chefRepo.findAll();

        if(!chefs.isEmpty())
        {
            return new ResponseEntity<>(chefs,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Chef> getChefById(Integer chefId) {
        Chef chef = chefRepo.findById(chefId).get();

        if(chef.getChefId() != null)
        {
            return new ResponseEntity<>(chef,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> changePass(Integer chefId, String chefEmail, String chefPassword) {
        Chef chef = chefRepo.findById(chefId).get();

        if(chef.getChefId() != null)
        {
            if(chef.getChefEmail().equals(chefEmail))
            {
                chef.setChefPassword(chefPassword);
                chefRepo.save(chef);
                return new ResponseEntity<>("Password is changed successfully..!!",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Enter valid email..!!",HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("Enter valid email..!!",HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<List<ChefBooking>> viewPendingBooking(Integer chefId) {
        String status = "Pending";

        List<ChefBooking> chefBookings = chefBookingRepo.findByChefIdOrBookingStatus(chefId,status);

        if(!chefBookings.isEmpty()) {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity<String> changeBookingStatus(Integer bookingId, String bookingStatus) {
        Optional<ChefBooking> chefBooking = chefBookingRepo.findById(bookingId);

        if (chefBooking.isPresent()) {
            ChefBooking chefBook = chefBooking.get();

            if ("Pending".equals(chefBook.getBookingStatus())) {
                chefBook.setBookingStatus(bookingStatus);
                chefBookingRepo.save(chefBook);
                return new ResponseEntity<>("Chef Status Changed Successfully..!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Chef Status is Already changed..!!", HttpStatus.ALREADY_REPORTED);
            }
        } else {
            return new ResponseEntity<>("Chef Not Found..!!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ChefBooking>> viewTodayBooking(Integer chefId) {
        LocalDate today = LocalDate.now();

        List<ChefBooking> chefBookings = chefBookingRepo.findByBookingDate(today,chefId);

        if(!chefBookings.isEmpty()) {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity<List<ChefBooking>> viewBookingDateWise(Integer chefId) {
        List<ChefBooking> chefBookings = chefBookingRepo.findByBookingDateWise(chefId);

        if(!chefBookings.isEmpty()) {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<ChefBooking>> viewApproveBooking(Integer chefId) {
        String status = "Approved";

        List<ChefBooking> chefBookings = chefBookingRepo.findByChefIdOrBookingStatus(chefId,status);

        if(!chefBookings.isEmpty()) {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<ChefBooking>> viewDisapproveBooking(Integer chefId) {

        String status = "Disapproved";

        List<ChefBooking> chefBookings = chefBookingRepo.findByChefIdOrBookingStatus(chefId,status);

        if(!chefBookings.isEmpty()) {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    public Chef findChefByEmail(String chefEmail) {
        return chefRepo.findByChefEmail(chefEmail);
    }
}
