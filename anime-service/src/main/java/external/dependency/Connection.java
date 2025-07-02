package external.dependency;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@AllArgsConstructor
@Component
public class Connection {

    private String host;
    private String username;
    private String password;
}
