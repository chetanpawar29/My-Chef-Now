package com.chetan.my_chef_now.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChefDTO {
    private Integer chefId;
    private String chefName;
    private String speciality;
    private Time openTime;
    private Time closeTime;
    private String chefAddress;
    private String chefArea;
    private String chefCity;
    private long chefPincode;
    private long chefContact;
    private String chefEmail;
}
