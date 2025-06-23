package hu.me.konyvtar.controller;

import hu.me.konyvtar.dto.LibraryDto;
import hu.me.konyvtar.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<LibraryDto> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary(@Valid @RequestBody LibraryDto libraryDto) {
        LibraryDto createdLibrary = libraryService.createLibrary(libraryDto);
        return new ResponseEntity<>(createdLibrary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDto> updateLibrary(@PathVariable Long id, @Valid @RequestBody LibraryDto libraryDto) {
        return ResponseEntity.ok(libraryService.updateLibrary(id, libraryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}