package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import external.dependency.Connection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProducerHardCodeRepository {

    private final Connection connection;
    private final ProducerData producerData;


    public List<Producer> findAll(){
        return producerData.getProducers();
    }

    public Optional<Producer> findById(Long id){
        return producerData.getProducers().stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }

    public List<Producer> findByName(String name){
        log.debug("Connection: {}", connection);
        return producerData.getProducers().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }

    public Producer save (Producer producer){
        producerData.getProducers().add(producer);
        return producer;
    }

    public void  delete(Producer producer){
        producerData.getProducers().remove(producer);

    }

    public void update (Producer producer){
        delete(producer);
        save(producer);
    }
}

