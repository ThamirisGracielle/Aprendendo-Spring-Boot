package academy.devdojo.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProducerPostRequest {

    private Long id;
    private String name;
}
