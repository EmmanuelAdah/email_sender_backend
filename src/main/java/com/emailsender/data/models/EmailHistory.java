package com.emailsender.data.models;

import lombok.Generated;
import org.springframework.data.annotation.Id;

public class EmailHistory {
    @Id
    @Generated
    private long id;
    private String email;
    private String subject;
    private String message;
}
