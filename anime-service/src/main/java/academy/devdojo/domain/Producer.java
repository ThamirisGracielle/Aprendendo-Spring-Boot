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
}
