package com.example.laptopone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSendingDTO {
    private String email, subject, body;
}
