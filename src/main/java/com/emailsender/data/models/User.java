package com.emailsender.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Generated
    private String id;
    private String email;
    private String username;
    private String password;

    List<EmailHistory> emailHistory = new ArrayList<>();
}
