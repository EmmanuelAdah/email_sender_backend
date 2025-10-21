package com.emailsender.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(regex, email);
    }

    public static String extractEmail(Row row) {
        Iterator<Cell> cell = row.cellIterator();
        String email = "";
        while (cell.hasNext()) {
            if (isValidEmail(cell.next().toString()))
                email = cell.next().toString();
        }
        return email;
    }
}
