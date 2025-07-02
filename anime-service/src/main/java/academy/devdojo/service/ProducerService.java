package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProducerService {


    /*Não utilizar @Autowired em atributos, utilizar em construtor
    Pq utilizar Autowired em construtor?
     Imutabilidade(final) - Ao injetar dependências via construtor, os campos podem ser
     declarados como final, garantindo que não sejam alterados após a criação do objeto,
     teste - pode passar mocks ao instanciar a classe em testes:
      checagem de null
    */

    private final ProducerHardCodeRepository repository;


    public List<Producer> findAll (String name){
        return name == null ? repository.findAll (): repository.findByName(name);
    }

    public Producer findByIdOrThrowNotFound(Long id){
       return repository. findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not found"));
    }

    public Producer save (Producer producer){
        return repository.save(producer);
    }

    public void delete (Long id){
        var producer = findByIdOrThrowNotFound(id);
        repository.delete(producer);
    }

    public void update (Producer producerToUpdate){
        var producer = findByIdOrThrowNotFound(producerToUpdate.getId());
        producerToUpdate.setCreateadAt(producer.getCreateadAt());
        repository.update(producerToUpdate);

    }
}
