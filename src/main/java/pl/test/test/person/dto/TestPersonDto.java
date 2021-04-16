package pl.test.test.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestPersonDto {

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Email")
    private String email;

    private List<String> param1;
    private List<String> param2;
    private List<String> param3;
    private List<String> param4;
    private List<String> param5;
    private List<String> param6;
    private List<String> param7;
    private List<String> param8;
    private List<String> param0;
}
