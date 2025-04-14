package com.chetan.my_chef_now.controller;

import com.chetan.my_chef_now.model.ChefDTO;
import com.chetan.my_chef_now.model.CustomerDTO;
import com.chetan.my_chef_now.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("home")
    public String adminHome()
    {
        return "Welcome Admin in MyChefNow..!!";
    }

    @GetMapping("chefs/{chefStatus}")
    public ResponseEntity<List<ChefDTO>> viewChefsByChefStatus(@PathVariable String chefStatus)
    {
        return adminService.viewChefsByChefStatus(chefStatus);
    }

    @PutMapping("chefStatus/{chefId}/{chefStatus}")
    public ResponseEntity<String> changeChefStatus(@PathVariable("chefId") Integer chefId, @PathVariable("chefStatus") String chefStatus)
    {
        return adminService.changeChefStatus(chefId,chefStatus);
    }

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDTO>> viewCustomers()
    {
        return adminService.viewCustomers();
    }

}
