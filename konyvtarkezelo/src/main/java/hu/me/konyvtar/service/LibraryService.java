package hu.me.konyvtar.service;

import hu.me.konyvtar.dto.LibraryDto;
import hu.me.konyvtar.entity.Library;
import hu.me.konyvtar.exception.ResourceNotFoundException;
import hu.me.konyvtar.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private static final Logger log = LoggerFactory.getLogger(LibraryService.class);
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        log.info("LibraryService is being created..."); // DIAGNOSZTIKAI ÜZENET
        this.libraryRepository = libraryRepository;
        log.info("LibraryService has been successfully created."); // DIAGNOSZTIKAI ÜZENET
    }

    public List<LibraryDto> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LibraryDto getLibraryById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found with id: " + id));
        return convertToDto(library);
    }

    public LibraryDto createLibrary(LibraryDto libraryDto) {
        Library library = convertToEntity(libraryDto);
        Library savedLibrary = libraryRepository.save(library);
        return convertToDto(savedLibrary);
    }

    public LibraryDto updateLibrary(Long id, LibraryDto libraryDto) {
        Library existingLibrary = libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found with id: " + id));

        existingLibrary.setName(libraryDto.getName());
        existingLibrary.setAddress(libraryDto.getAddress());

        Library updatedLibrary = libraryRepository.save(existingLibrary);
        return convertToDto(updatedLibrary);
    }

    public void deleteLibrary(Long id) {
        if (!libraryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Library not found with id: " + id);
        }
        libraryRepository.deleteById(id);
    }

    private LibraryDto convertToDto(Library library) {
        LibraryDto dto = new LibraryDto();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setAddress(library.getAddress());
        return dto;
    }

    private Library convertToEntity(LibraryDto dto) {
        Library library = new Library();
        library.setName(dto.getName());
        library.setAddress(dto.getAddress());
        return library;
    }
}