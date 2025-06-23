package hu.me.konyvtar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class LibraryDto {
    private Long id;

    @NotBlank(message = "A könyvtár neve nem lehet üres.")
    @Size(min = 3, message = "A könyvtár nevének legalább 3 karakter hosszúnak kell lennie.")
    private String name;

    private String address;
    private List<BookDto> books;
}
