package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {

    private final List<Producer> producers = new ArrayList<>();

    {

        var mappa = academy.devdojo.domain.Producer.builder().id(1L).name("Mappa").createadAt(LocalDateTime.now()).build();
        var kyotoAnimation = academy.devdojo.domain.Producer.builder().id(2L).name("Kyoto Animation").createadAt(LocalDateTime.now()).build();
        var madhouse = Producer.builder().id(3L).name("MadHouse").createadAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, kyotoAnimation, madhouse));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
