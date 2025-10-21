package com.emailsender.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
    private String email;
    private String name;
    private String subject;
    private String message;
}
