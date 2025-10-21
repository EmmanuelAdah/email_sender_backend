package com.emailsender.services;

import com.emailsender.data.models.Recipient;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.emailsender.utils.Validator.extractEmail;
import static com.emailsender.utils.Validator.isValidEmail;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {
    private final JavaMailSender mailSender;

    public void sendEmailsFromFile(String filePath) throws Exception {
        List<Recipient> recipients = parseFile(filePath);

        for (Recipient r : recipients) {
            System.out.println("Sending email to: " + r.getEmail());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("edogbanya02@gmail.com");
            message.setTo(r.getEmail());
            message.setSubject(r.getSubject());
            message.setText(r.getMessage());
            mailSender.send(message);
        }
        System.out.println("✅ All emails sent successfully!");
    }

    private List<Recipient> parseFile(String filePath) throws Exception {
        if (filePath.endsWith(".csv")) {
            return new CsvToBeanBuilder<Recipient>(new FileReader(filePath))
                    .withType(Recipient.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } else if (filePath.endsWith(".xlsx")) {
            return parseExcel(filePath);
        } else if (filePath.endsWith(".txt")) {
            return parseTxt(filePath);
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }

    private List<Recipient> parseExcel(String filePath) throws IOException {
        List<Recipient> recipients = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;// skip header

                String email = extractEmail(row);
                String name = getCellValue(row.getCell(0));
                String subject = getCellValue(row.getCell(2));
                String message = getCellValue(row.getCell(3));
                if (isValidEmail(email)) {
                    recipients.add(new Recipient(email, name, subject, message));
                }
            }
        }
        return recipients;
    }

    private List<Recipient> parseTxt(String filePath) throws IOException {
        List<Recipient> recipients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Expected format: email|name|subject|message
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Recipient recipient = new Recipient();

                    Arrays.stream(parts).forEach(part -> {
                        if (isValidEmail(part.trim()))
                            recipient.setEmail(part);

                        if (!isValidEmail(part.trim()))
                            recipient.setName(part);
                    });
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return recipients;
    }

    private String getCellValue(Cell cell) {
        return cell == null ? "" : cell.toString().trim();
    }
}
