package academy.devdojo.controller;

import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import academy.devdojo.service.ProducerService;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/producers")

public class ProducerController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProducerController.class);
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    private ProducerService service;


    public ProducerController(){
        this.service = new ProducerService();
    }

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all producers, param name '{}", name);

        var producers = service.findAll(name);

        var producerGetResponse = MAPPER.toProducerGetRresponseList(producers);

        return ResponseEntity.ok(producerGetResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Requeste to find by id: {}", id);

        var producer = service.findByIdOrThrowNotFound(id);

        var producerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key=1234")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        var producer = MAPPER.toProducer(producerPostRequest);

        var producerSaved = service.save(producer);

        var producerGetResponseresponse = MAPPER.toProducerGetResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponseresponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete producer by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public <ProducerPutRequest> ResponseEntity<Void> update (@RequestBody ProducerPutRequest request){
        log.debug("Request to update producer{}");

       var producerToUpdate = MAPPER.toProducer(request);
       service.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }
}

