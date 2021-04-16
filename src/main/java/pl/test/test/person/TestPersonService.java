package pl.test.test.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.test.test.exception.OperationException;
import pl.test.test.person.dto.TestPersonDto;

@Slf4j
@Service
public class TestPersonService {

    private final TestPersonClient personClient;

    @Autowired
    public TestPersonService(TestPersonClient personClient) {
        this.personClient = personClient;
    }

    public void addPerson(TestPersonDto person) {
        try {
            checkIfPersonIsValid(person);
            personClient.addPerson(person);
        } catch (Exception e) {
            log.debug("Add Person was unsuccessful, Exception: {}", e.getMessage());
        }
    }

    private void checkIfPersonIsValid(TestPersonDto person) {
        if (person == null)
            throw new OperationException("TestPersonDto is null");

        String email = person.getEmail();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        String[] split = email.split("[.@]");

        if (!firstName.equals(split[0]) || !lastName.equals(split[1]))
            throw new OperationException("TestPersonDto is invalid");
    }

}
