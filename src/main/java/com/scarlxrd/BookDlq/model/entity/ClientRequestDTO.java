package com.scarlxrd.BookDlq.model.entity;


import java.util.List;

public record ClientRequestDTO(
        String name,

        String lastName,

        String cpfNumber,

        List<BookRequestDTO> books
) {
}
