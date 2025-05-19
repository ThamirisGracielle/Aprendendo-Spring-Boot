package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("animes")
public class AnimeController {

    private static final Logger log = LoggerFactory.getLogger(AnimeController.class);
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}'", name);
        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList
                .stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find by id: {}", id);

        var animeGetResponse = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .map(MAPPER::toAnimeResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));


        return animeGetResponse == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: {}", request);
        var anime = MAPPER.toAnime(request);
        Anime.getAnimes().add(anime);
        var response = MAPPER.toAnimePostResponse(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        var animeToDelete = Anime.getAnimes()
                .stream().filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        Anime.getAnimes().remove(animeToDelete);

        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<Void> update (@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime {}",request);

        Optional<Anime> found = Optional.empty();
        for (Anime anime : Anime.getAnimes()) {
            if (anime.getId().equals(request.getId())) {
                found = Optional.of(anime);
                break;
            }
        }
        var animeToRemove = found
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        var animeUpdate = MAPPER.toAnime(request);
        Anime.getAnimes().remove(animeToRemove);
        Anime.getAnimes().add(animeUpdate);

        return ResponseEntity.noContent().build();
    }
}