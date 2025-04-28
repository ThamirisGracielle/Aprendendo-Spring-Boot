package academy.devdojo.response;

import academy.devdojo.domain.Anime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimeGetResponse {

    private Long id;
    private String name;
}
