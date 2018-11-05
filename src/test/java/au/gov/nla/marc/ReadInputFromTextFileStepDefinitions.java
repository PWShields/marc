package au.gov.nla.marc;

import au.gov.nla.marc.service.FileReaderService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;


public class ReadInputFromTextFileStepDefinitions extends SpringBootBaseIntegrationTest {

    @Autowired
    FileReaderService fileReaderService;

    String fileName;
    List<String> fileByLine;
    String firstLine;
    String secondLine;


    @Given("A valid text file")
    public void a_valid_text_file() {
        fileName = "src/test/resources/data/oneRecordInput.txt";
    }

    @When("the file is read")
    public void the_file_is_read() {
        fileByLine = fileReaderService.readFile(fileName);
    }


    @When("the row starts with 001")
    public void the_row_starts_with_001() {
        String expected = "001";
        Assert.assertThat(fileByLine.get(0), containsString(expected));
    }


    @When("the row starts with tag number")
    public void the_row_starts_with_tag_number() {
        String expected = "245";
        secondLine = fileByLine.get(1);

    }


    @Then("we can read the contents")
    public void we_can_read_it() {
        String expected = "Lapuran Juru";
        Assert.assertThat(fileByLine.get(1), containsString(expected));
    }

    @Then("the record id is read")
    public void the_record_id_is_read() {
        String expected = "3416808";
        Assert.assertThat(fileByLine.get(0), containsString(expected));
    }

    @Then("tags and contents are read")
    public void tags_and_contents_are_read() {
        String firstTag = "245";
        String secondTag = "650";
        Assert.assertThat(secondLine, containsString(firstTag));
        Assert.assertThat(fileByLine.get(2), containsString(secondTag));
    }


}
