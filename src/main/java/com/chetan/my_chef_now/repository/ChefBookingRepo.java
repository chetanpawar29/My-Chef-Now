package com.chetan.my_chef_now.repository;

import com.chetan.my_chef_now.model.ChefBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChefBookingRepo extends JpaRepository<ChefBooking, Integer> {

    List<ChefBooking> findAllByCustomerId(Integer customerId);

    @Query("SELECT c FROM ChefBooking c WHERE c.bookingStatus = :status")
    List<ChefBooking> findByBookingStatus(String status);

    @Query("SELECT c FROM ChefBooking c WHERE c.bookingDate = :today AND c.chefId = :chefId")
    List<ChefBooking> findByBookingDate(LocalDate today, Integer chefId);

    @Query("SELECT c FROM ChefBooking c WHERE c.chefId = :chefId ORDER BY c.bookingDate ASC")
    List<ChefBooking> findByBookingDateWise(Integer chefId);

    @Query("SELECT c FROM ChefBooking c WHERE c.chefId = :chefId AND c.bookingStatus = :status")
    List<ChefBooking> findByChefIdOrBookingStatus(Integer chefId,String status);
}
