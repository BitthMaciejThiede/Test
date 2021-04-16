package pl.test.test.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.test.test.person.dto.TestPersonDto;

@Slf4j
@Component
public class TestPersonClient {

    @Value("${test.person.uri}")
    private String personUri;

    private final ObjectMapper objectMapper;

    @Autowired
    public TestPersonClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void addPerson(TestPersonDto person) throws JsonProcessingException {
        RestTemplate rt = new RestTemplate();

        String stringPerson = objectMapper.writeValueAsString(person);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(stringPerson, headers);

        try {
            rt.postForEntity(personUri, request, String.class);
        } catch (Exception e) {
            log.error("AddPerson request has failed, Person: {}", stringPerson);
        }
    }
}
