package com.chetan.my_chef_now.repository;

import com.chetan.my_chef_now.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin findByAdminEmail(String email);
}
