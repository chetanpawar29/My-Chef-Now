package com.chetan.my_chef_now.repository;

import com.chetan.my_chef_now.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Integer> {

    @Query("SELECT c FROM Chef c WHERE c.chefStatus = :status")
    List<Chef> findByChefStatus(@Param("status") String status);


    @Query("SELECT c FROM Chef c ORDER BY c.chefCity")
    List<Chef> findAllChefsCityWise();


    @Query("Select c from Chef c where Lower(c.speciality) Like Lower(Concat('%',:speciality,'%')) Order By c.chefCity")
    List<Chef> searchChefBySpeciality(String speciality);

    Chef findByChefEmail(String email);
}

