package hu.me.konyvtar.service;

import hu.me.konyvtar.dto.LibraryDto;
import hu.me.konyvtar.entity.Library;
import hu.me.konyvtar.exception.ResourceNotFoundException;
import hu.me.konyvtar.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void getAllLibraries_shouldReturnDtoList() {
        // Given
        Library library = new Library(1L, "Test Library", "Test Address", Collections.emptyList());
        when(libraryRepository.findAll()).thenReturn(List.of(library));

        // When
        List<LibraryDto> result = libraryService.getAllLibraries();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Library");
    }

    @Test
    void getLibraryById_whenExists_shouldReturnDto() {
        // Given
        Library library = new Library(1L, "Test Library", "Test Address", null);
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(library));

        // When
        LibraryDto result = libraryService.getLibraryById(1L);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Test Library");
    }

    @Test
    void getLibraryById_whenNotExists_shouldThrowException() {
        // Given
        when(libraryRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            libraryService.getLibraryById(99L);
        });
    }

    @Test
    void createLibrary_shouldReturnSavedDto() {
        // Given
        LibraryDto dtoToSave = new LibraryDto();
        dtoToSave.setName("New Library");
        dtoToSave.setAddress("New Address");

        Library savedLibrary = new Library(1L, "New Library", "New Address", null);
        when(libraryRepository.save(any(Library.class))).thenReturn(savedLibrary);

        // When
        LibraryDto result = libraryService.createLibrary(dtoToSave);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("New Library");
    }

    @Test
    void updateLibrary_whenExists_shouldReturnUpdatedDto() {
        // Given
        LibraryDto dtoToUpdate = new LibraryDto();
        dtoToUpdate.setName("Updated Name");
        dtoToUpdate.setAddress("Updated Address");

        Library existingLibrary = new Library(1L, "Old Name", "Old Address", null);
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(existingLibrary));
        when(libraryRepository.save(any(Library.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        LibraryDto result = libraryService.updateLibrary(1L, dtoToUpdate);

        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Updated Name");
    }

    @Test
    void updateLibrary_whenNotExists_shouldThrowException() {
        // Given
        LibraryDto dto = new LibraryDto();
        when(libraryRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            libraryService.updateLibrary(99L, dto);
        });
    }

    @Test
    void deleteLibrary_whenExists_shouldCallDelete() {
        // Given
        when(libraryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(libraryRepository).deleteById(1L);

        // When
        libraryService.deleteLibrary(1L);

        // Then
        verify(libraryRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteLibrary_whenNotExists_shouldThrowException() {
        // Given
        when(libraryRepository.existsById(99L)).thenReturn(false);

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            libraryService.deleteLibrary(99L);
        });
    }
}
