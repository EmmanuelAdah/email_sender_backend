package com.emailsender.data.repositories;

import com.emailsender.data.models.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailHistory, String> {
}
