package academy.devdojo.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Anime {

    private Long id;
    private String name;


    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public static List<Anime> getAnimes(){

        var naruto = new Anime(1L, "Naruto");
        var fairyTail = new Anime(2L, "Fairy Tail");
        var hunterHunter = new Anime(3L, "Hunter x Hunter");
        return List.of(naruto, fairyTail, hunterHunter );

    }
}
