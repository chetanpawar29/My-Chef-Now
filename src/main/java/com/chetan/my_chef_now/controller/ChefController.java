package com.chetan.my_chef_now.controller;

import com.chetan.my_chef_now.model.Chef;
import com.chetan.my_chef_now.model.ChefBooking;
import com.chetan.my_chef_now.model.ChefDTO;
import com.chetan.my_chef_now.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chef")
public class ChefController {

    @Autowired
    ChefService chefService;

    @GetMapping("home")
    public String chefHome()
    {
        return "Welcome Chef in MyChefNow..!!";
    }

    @GetMapping("booking/pending/{chefId}")
    public ResponseEntity<List<ChefBooking>> viewPendingBooking(@PathVariable Integer chefId)
    {
        return chefService.viewPendingBooking(chefId);
    }

    @PutMapping("bookingStatus/{bookingId}/{bookingStatus}")
    public ResponseEntity<String> changeBookingStatus(@PathVariable("bookingId") Integer bookingId, @PathVariable("bookingStatus") String bookingStatus)
    {
        return chefService.changeBookingStatus(bookingId,bookingStatus);
    }

    @GetMapping("booking/today's/{chefId}")
    public ResponseEntity<List<ChefBooking>> viewTodayBooking(@PathVariable Integer chefId)
    {
        return chefService.viewTodayBooking(chefId);
    }

    @GetMapping("booking/date-wise/{chefId}")
    public ResponseEntity<List<ChefBooking>> viewBookingDateWise(@PathVariable Integer chefId)
    {
        return chefService.viewBookingDateWise(chefId);
    }

    @GetMapping("booking/status/approve/{chefId}")
    public ResponseEntity<List<ChefBooking>> viewApproveBooking(@PathVariable Integer chefId)
    {
        return chefService.viewApproveBooking(chefId);
    }

    @GetMapping("booking/status/disapprove/{chefId}")
    public ResponseEntity<List<ChefBooking>> viewDisapproveChef(@PathVariable Integer chefId)
    {
        return chefService.viewDisapproveBooking(chefId);
    }


//    @GetMapping("allChefs")
//    public ResponseEntity<List<Chef>> getAllChef()
//    {
//        return chefService.getAllChef();
//    }
//
//    @GetMapping("getChef/{chefId}")
//    public ResponseEntity<Chef> getChefById(@PathVariable("chefId") Integer chefId)
//    {
//        return chefService.getChefById(chefId);
//    }

    @PutMapping("password/{chefId}")
    public ResponseEntity<String> changePass(@PathVariable Integer chefId ,@RequestBody Map<String, String> requestBody)
    {
        String chefEmail = requestBody.get("chefEmail");
        String chefPassword = requestBody.get("chefPassword");
        return chefService.changePass(chefId, chefEmail, chefPassword);
    }

}
