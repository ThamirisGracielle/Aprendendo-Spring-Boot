package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProducerService {

    private ProducerHardCodeRepository repository;

    public ProducerService() {
        this.repository = new ProducerHardCodeRepository();
    }


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
