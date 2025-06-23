package hu.me.konyvtar.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDto {
    private Long id;

    @NotBlank(message = "A könyv címe nem lehet üres.")
    private String title;

    @NotBlank(message = "A szerző neve nem lehet üres.")
    private String author;

    private String isbn;
    private Long libraryId;
}