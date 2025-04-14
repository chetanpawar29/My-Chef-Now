package com.chetan.my_chef_now.service;

import com.chetan.my_chef_now.model.Chef;
import com.chetan.my_chef_now.model.ChefBooking;
import com.chetan.my_chef_now.model.ChefDTO;
import com.chetan.my_chef_now.model.Customer;
import com.chetan.my_chef_now.repository.ChefBookingRepo;
import com.chetan.my_chef_now.repository.ChefRepo;
import com.chetan.my_chef_now.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ChefRepo chefRepo;

    @Autowired
    ChefBookingRepo chefBookingRepo;

    public ResponseEntity<String> registerCustomer(Customer customer) {
        try{
            customerRepo.save(customer);
            return new ResponseEntity<>("Customer Register Successfully..!!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> changePass(Integer customerId, String customerEmail, String customerPassword) {
        Customer customer = customerRepo.findById(customerId).get();

        if(customer.getCustomerId() != null)
        {
            if(customer.getCustomerEmail().equals(customerEmail))
            {
                customer.setCustomerPassword(customerPassword);
                customerRepo.save(customer);
                return new ResponseEntity<>("Password is changed successfully..!!",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Enter valid email..!!",HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("Enter valid email..!!",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ChefDTO>> viewAllChefs() {
        List<Chef> chefs = chefRepo.findAllChefsCityWise();

        List<ChefDTO> cityWiseChefs = new ArrayList<>();

        for (Chef chef : chefs) {
            ChefDTO chefDTO = new ChefDTO();
            chefDTO.setChefId(chef.getChefId());
            chefDTO.setChefName(chef.getChefName());
            chefDTO.setChefAddress(chef.getChefAddress());
            chefDTO.setChefCity(chef.getChefCity());
            chefDTO.setChefPincode(chef.getChefPincode());
            chefDTO.setChefContact(chef.getChefContact());
            chefDTO.setChefEmail(chef.getChefEmail());
            chefDTO.setChefArea(chef.getChefArea());
            chefDTO.setSpeciality(chef.getSpeciality());
            chefDTO.setOpenTime(chef.getOpenTime());
            chefDTO.setCloseTime(chef.getCloseTime());

            cityWiseChefs.add(chefDTO);
        }

        if(!cityWiseChefs.isEmpty())
        {
            return new ResponseEntity<>(cityWiseChefs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<ChefDTO>> searchChefBySpeciality(String speciality) {
        List<Chef> chefs = chefRepo.searchChefBySpeciality(speciality);

        List<ChefDTO> cityWiseChefs = new ArrayList<>();

        for (Chef chef : chefs) {
            ChefDTO chefDTO = new ChefDTO();
            chefDTO.setChefId(chef.getChefId());
            chefDTO.setChefName(chef.getChefName());
            chefDTO.setChefAddress(chef.getChefAddress());
            chefDTO.setChefCity(chef.getChefCity());
            chefDTO.setChefPincode(chef.getChefPincode());
            chefDTO.setChefContact(chef.getChefContact());
            chefDTO.setChefEmail(chef.getChefEmail());
            chefDTO.setChefArea(chef.getChefArea());
            chefDTO.setSpeciality(chef.getSpeciality());
            chefDTO.setOpenTime(chef.getOpenTime());
            chefDTO.setCloseTime(chef.getCloseTime());

            cityWiseChefs.add(chefDTO);
        }

        if(!cityWiseChefs.isEmpty())
        {
            return new ResponseEntity<>(cityWiseChefs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<String> bookChef(Integer customerId, Integer chefId, ChefBooking booking) {
        booking.setBookingStatus("Pending");
        booking.setChefId(chefId);
        booking.setCustomerId(customerId);

        try{
            chefBookingRepo.save(booking);
            return new ResponseEntity<>("Chef Booked Successfully..!!",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something is wrong..!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ChefBooking>> viewMyBookings(Integer customerId) {

        List<ChefBooking> chefBookings = chefBookingRepo.findAllByCustomerId(customerId);

        if(! chefBookings.isEmpty())
        {
            return new ResponseEntity<>(chefBookings,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public Customer findCustomerByEmail(String customerEmail) {
        return customerRepo.findByCustomerEmail(customerEmail);
    }
}
