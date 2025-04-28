package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/animes")

public class AnimeController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AnimeController.class);
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}", name);
        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetRresponseList(animes);
        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList
                .stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Requeste to find by id: {}", id);

        var animeGetResponse =  Anime.getAnimes()
                .stream().filter(anime -> anime.getId().equals(id))
                .findFirst(). map(MAPPER :: toAnimeResponse)
                .orElse(null);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping()
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: {}", request);
        var anime = MAPPER.toAnime((request));

        Anime.getAnimes().add(anime);

        var response = MAPPER.toAnimePostResponse(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

