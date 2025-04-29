package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {

    @EqualsAndHashCode.Exclude
    private Long id;
    @JsonProperty("full_name")
    private String name;
    private LocalDateTime createadAt;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {

        var mappa = Producer.builder().id(1L).name("Mappa").createadAt(LocalDateTime.now()).build();
        var kyotoAnimation = Producer.builder().id(2L).name("Kyoto Animation").createadAt(LocalDateTime.now()).build();
        var madhouse = Producer.builder().id(3L).name("MadHouse").createadAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, kyotoAnimation, madhouse));
    }
}
