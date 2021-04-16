package pl.test.test.person;

import pl.test.test.person.dto.TestPersonDto;

import java.util.Arrays;
import java.util.List;

public class TestPersonMapper {

    private TestPersonMapper() {
        //
    }

    public static TestPersonDto mapStringToTestPersonDto(String s) {
        String rowReplaced = s.replaceAll("[\"'\\[\\]]|\\s", "");
        String[] split = rowReplaced.split(";");

        TestPersonDto dto = new TestPersonDto();
        dto.setFirstName(split[0]);
        dto.setLastName(split[1]);
        dto.setEmail(split[2]);
        dto.setParam0(mapPersonParamStringToStringArrayList(split[3]));
        dto.setParam1(mapPersonParamStringToStringArrayList(split[4]));
        dto.setParam2(mapPersonParamStringToStringArrayList(split[5]));
        dto.setParam3(mapPersonParamStringToStringArrayList(split[6]));
        dto.setParam4(mapPersonParamStringToStringArrayList(split[7]));
        dto.setParam5(mapPersonParamStringToStringArrayList(split[8]));
        dto.setParam6(mapPersonParamStringToStringArrayList(split[9]));
        dto.setParam7(mapPersonParamStringToStringArrayList(split[10]));
        dto.setParam8(mapPersonParamStringToStringArrayList(split[11]));

        return dto;
    }

    private static List<String> mapPersonParamStringToStringArrayList(String s) {
        return Arrays.asList(s.split(","));
    }
}
