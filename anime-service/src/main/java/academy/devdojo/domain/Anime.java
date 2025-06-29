package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Anime {

    private Long id;
    private String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {

        var naruto = new Anime(1L, "Naruto");
        var fairyTail = new Anime(2L, "Fairy Tail");
        var hunterHunter = new Anime(3L, "Hunter x Hunter");
        animes.addAll(List.of(naruto, fairyTail, hunterHunter));
    }
}
