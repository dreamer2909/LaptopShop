package com.example.laptopone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private String authorName;
    private String content;
    private int rating;
    private int status;
    private int orderId;
    private int entryId;
}
