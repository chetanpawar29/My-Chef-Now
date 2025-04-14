package com.chetan.my_chef_now.controller;

import com.chetan.my_chef_now.model.ChefBooking;
import com.chetan.my_chef_now.model.ChefDTO;
import com.chetan.my_chef_now.model.Customer;
import com.chetan.my_chef_now.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("home")
    public String customerHome()
    {
        return "Welcome Customer in MyChefNow..!!";
    }


    @PutMapping("password/{customerId}")
    public ResponseEntity<String> changePass(@PathVariable Integer customerId ,@RequestBody Map<String, String> requestBody)
    {
        String customerEmail = requestBody.get("customerEmail");
        String customerPassword = requestBody.get("customerPassword");
        return customerService.changePass(customerId, customerEmail, customerPassword);
    }

    @GetMapping("chefs/city-wise")
    public ResponseEntity<List<ChefDTO>> viewAllChefs()
    {
        return customerService.viewAllChefs();
    }

    @GetMapping("chefs/search/speciality/{speciality}")
    public ResponseEntity<List<ChefDTO>> searchChefBySpeciality(@PathVariable String speciality)
    {
        return customerService.searchChefBySpeciality(speciality);
    }

    @PostMapping("book/customerId={customerId}/chefId={chefId}")
    public ResponseEntity<String> bookChef(@PathVariable("customerId") Integer customerId, @PathVariable("chefId") Integer chefId, @RequestBody ChefBooking booking)
    {
        return customerService.bookChef(customerId,chefId,booking);
    }

    @GetMapping("myBooking/{customerId}")
    public ResponseEntity<List<ChefBooking>> viewMyBookings(@PathVariable Integer customerId)
    {
        return customerService.viewMyBookings(customerId);
    }



}
