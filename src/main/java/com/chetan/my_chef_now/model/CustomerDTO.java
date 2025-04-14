package com.chetan.my_chef_now.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Integer customerId;
    private String customerName;
    private String customerCity;
    private String customerAddress;
    private long customerPincode;
    private long customerContact;
    private String customerEmail;
}

