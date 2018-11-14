package au.gov.nla.marc;

import au.gov.nla.marc.domain.output.TabbedResultTable;
import au.gov.nla.marc.service.FileReaderService;
import au.gov.nla.marc.service.MarcTextService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class TabbedOutputStepDefinitions {

    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    MarcTextService marcTextService;

    String fileName = "";
    String largeFile = "";

    int expectedNumberOfRows = 145;

    TabbedResultTable tabbedResultTable;

    @Given("A valid input file")
    public void a_valid_input_file() {
        fileName = "src/test/resources/data/multipleRecordInput.txt";
    }

    @Given("A large input file")
    public void a_large_input_file() {
        largeFile = "src/test/resources/data/manyRecordInput.txt";
    }

    @When("the file is processed")
    public void the_file_is_processed() {
        tabbedResultTable = marcTextService.transFormToTabbedOutPut(fileName);
    }

    @When("the large file is processed")
    public void the_large_file_is_processed() {
        tabbedResultTable = marcTextService.transFormToTabbedOutPut(largeFile);
    }

    @Then("the record id is populated")
    public void the_record_id_is_populated() {
        String expected = "7461413";
        Assert.assertEquals(tabbedResultTable.getResultRows().get(0).getRecordId(), expected);
    }

    @Then("tags and contents are added to the record")
    public void tags_and_contents_are_added_to_the_record() {
        Assert.assertEquals(3, tabbedResultTable.getResultRows().size());
    }

    @Then("all rows have been transposed")
    public void all_rows_have_been_transposed() {
        Assert.assertEquals(expectedNumberOfRows, tabbedResultTable.getResultRows().size());
    }

    @Then("the number of headings is correct")
    public void the_number_of_headings_is_correct() {
        Assert.assertEquals(13, tabbedResultTable.getHeaderRow().getColumnHeadings().size());
    }


}
