package com.example.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String username;
    String email;
    String password;
}

/***
 * {
 *     "laptop_name": "",
 *     "laptop_model": "",
 *     "laptop_color": "",
 *     "user_name": "",
 *     "user_age": ""
 * }
 *
 * {
 *     "laptop_id" :
 *     "user_roll_no":
 * }
 *
 *
 */
