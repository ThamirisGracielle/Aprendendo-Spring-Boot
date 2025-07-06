package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerHardCodeRepositoryTest {

    @InjectMocks
    private ProducerHardCodeRepository repository;
    @Mock
    private ProducerData producerData;
    private final List<Producer> producerList = new ArrayList<>();
    @BeforeEach

    void init(){
        var ufotable = academy.devdojo.domain.Producer.builder().id(1L).name("ufotable").createadAt(LocalDateTime.now()).build();
        var witStudio = academy.devdojo.domain.Producer.builder().id(2L).name("witStudio").createadAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(3L).name("studioGhibli").createadAt(LocalDateTime.now()).build();
        producerList.addAll(List.of(ufotable, witStudio, studioGhibli));

    }


    @Test
    @DisplayName("findALL returns a list with all producers")
    @Order(1)
    void findAll_ReturnProducers_WhenSucessful(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producerList);

    }

    @Test
    @DisplayName("findById returns a producer with given id")
    @Order(2)
    void findById_ReturnsProducerById_WhenSucessful(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var expectedProducer = producerList.getFirst();
        var producers = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producers).isNotPresent().contains(expectedProducer);

    }


   @Test
    @DisplayName("findByName returns empty list when name is null")
   @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producers = repository.findByName(null);
        Assertions.assertThat(producers).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("findByName returns list with found when name exists")
    @Order(4)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
        var expectedProducer = producerList.getFirst();

        var producers = repository.findByName(expectedProducer.getName());
        Assertions.assertThat(producers).contains(expectedProducer);

    }

    @Test
    @DisplayName("Save creats a producer")
    @Order(5)
    void save_CreatesProducer_WhenSucessful(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToSave = Producer.builder().id(99L).name("MAPPA").createadAt(LocalDateTime.now()).build();
        var producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave).hasAllNullFieldsOrProperties();

        var producerSavedOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(producerSavedOptional).isPresent().contains(producerToSave);
    }

    @Test
    @DisplayName("delete removes a producer")
    @Order(6)
    void delete_RemoveProducer_WhenSucessful(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToDelete = producerList.getFirst();
        repository.delete(producerToDelete);

        var producers = repository.findAll();

        Assertions.assertThat(producers).isNotEmpty().doesNotContain(producerToDelete);
    }

    @Test
    @DisplayName("update updates a producer")
    @Order(7)
    void update_UpdatesProducer_WhenSucessful(){
        BDDMockito.when(producerData.getProducers()).thenReturn(producerList);

        var producerToUpdate = this.producerList.getFirst();
        producerToUpdate.setName("Aniplex");

        repository.update(producerToUpdate);

        Assertions.assertThat(this.producerList).contains(producerToUpdate);

        var producerUpdateOptional = repository.findById(producerToUpdate.getId());

        Assertions.assertThat(producerUpdateOptional).isPresent();
        Assertions.assertThat(producerUpdateOptional.get().getName()).isEqualTo(producerToUpdate.getName());

    }
}