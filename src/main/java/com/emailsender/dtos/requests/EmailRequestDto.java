package com.emailsender.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDto {

    @NotBlank
    private String filePath;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;
}
