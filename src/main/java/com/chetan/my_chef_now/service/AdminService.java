package com.chetan.my_chef_now.service;

import com.chetan.my_chef_now.model.*;
import com.chetan.my_chef_now.repository.AdminRepo;
import com.chetan.my_chef_now.repository.ChefRepo;
import com.chetan.my_chef_now.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ChefRepo chefRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    AdminRepo adminRepo;


    public ResponseEntity<List<ChefDTO>> viewChefsByChefStatus(String status) {

        List<Chef> chefs = chefRepo.findByChefStatus(status);

        List<ChefDTO> chefDTOList = new ArrayList<>();
        for(Chef chef : chefs)
        {
            ChefDTO chefDTO = new ChefDTO();
            chefDTO.setChefName(chef.getChefName());
            chefDTO.setChefAddress(chef.getChefAddress());
            chefDTO.setChefCity(chef.getChefCity());
            chefDTO.setChefPincode(chef.getChefPincode());
            chefDTO.setChefContact(chef.getChefContact());
            chefDTO.setChefId(chef.getChefId());
            chefDTO.setChefEmail(chef.getChefEmail());
            chefDTO.setChefArea(chef.getChefArea());
            chefDTO.setSpeciality(chef.getSpeciality());
            chefDTO.setOpenTime(chef.getOpenTime());
            chefDTO.setCloseTime(chef.getCloseTime());

            chefDTOList.add(chefDTO);
        }

        if(!chefDTOList.isEmpty())
        {
            return new ResponseEntity<>(chefDTOList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<String> changeChefStatus(Integer chefId, String chefStatus) {
        Optional<Chef> optionalChef = chefRepo.findById(chefId);

        if (optionalChef.isPresent()) {
            Chef chef = optionalChef.get();

            if ("Pending".equals(chef.getChefStatus())) {
                chef.setChefStatus(chefStatus);
                chefRepo.save(chef);
                return new ResponseEntity<>("Chef Status Changed Successfully..!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Chef Status is Already changed..!!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Chef Not Found..!!", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<List<CustomerDTO>> viewCustomers() {

        List<Customer> customers = customerRepo.findAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer customer : customers)
        {
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerCity(customer.getCustomerCity());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerPincode(customer.getCustomerPincode());
            customerDTO.setCustomerContact(customer.getCustomerContact());
            customerDTO.setCustomerEmail(customer.getCustomerEmail());

            customerDTOList.add(customerDTO);
        }

        if(!customerDTOList.isEmpty())
        {
            return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    public Admin findAdminByEmail(String email) {
        return adminRepo.findByAdminEmail(email); // Assuming you're using Spring Data JPA
    }
}
