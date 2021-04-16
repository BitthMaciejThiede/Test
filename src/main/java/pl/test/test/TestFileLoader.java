package pl.test.test;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.test.test.person.dto.TestPersonDto;
import pl.test.test.person.TestPersonMapper;
import pl.test.test.person.TestPersonService;

import javax.annotation.PostConstruct;
import java.io.*;

@Slf4j
@Component
public class TestFileLoader {


    @Value("${test.person.file.name}")
    private String personFileName;

    @Value("${test.file.password}")
    private String filePassword;

    @Value("classpath:file.zip")
    private File zipFile;

    private String absPath;

    private BufferedReader csvReader;

    private final TestPersonService personService;

    @Autowired
    public TestFileLoader(TestPersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    private void loadFile() throws IOException {
        unzipTestFile();
        loadPersonFile();
        readPersonFileAndAddPerson();
    }

    private void unzipTestFile() {
        try {
            absPath = zipFile.getParentFile().getAbsolutePath();
            ZipFile zip4jFile = new ZipFile(zipFile.getAbsoluteFile());

            if (zip4jFile.isEncrypted()) {
                zip4jFile.setPassword(filePassword.toCharArray());
            }

            zip4jFile.extractAll(absPath);
        } catch (ZipException e) {
            log.error("Error while unzipping file, Exception: {}", e.getMessage());
        }
    }

    private void loadPersonFile() {
        try {
            csvReader = new BufferedReader(new FileReader(absPath + "/" + personFileName));
        } catch (FileNotFoundException e) {
            log.error("Error while reading person file, Exception: {}", e.getMessage());
        }
    }

    private void readPersonFileAndAddPerson() throws IOException {
        String row;
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            TestPersonDto person = TestPersonMapper.mapStringToTestPersonDto(row);
            personService.addPerson(person);
        }
    }


}
