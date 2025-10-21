package com.emailsender.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class RecipientDto {
    private long id;
    private String email;
    private String status;
    private String name;
    private String message;
}
