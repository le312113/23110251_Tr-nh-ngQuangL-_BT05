package com.BT11.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
}
