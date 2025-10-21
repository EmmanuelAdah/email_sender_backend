package com.emailsender.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
    @Id
    @Generated
    private long id;
    private String email;
    private String name;
}
