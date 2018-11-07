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

    TabbedResultTable tabbedResultTable;

    @Given("A valid input file")
    public void a_valid_input_file() {
        fileName = "src/test/resources/data/multipleRecordInput.txt";
    }

    @When("the file is processed")
    public void the_file_is_processed() {
        tabbedResultTable = marcTextService.transFormToTabbedOutPut(fileName);
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


}
