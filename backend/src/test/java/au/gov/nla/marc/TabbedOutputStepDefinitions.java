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
    String orderedFile = "";

    int expectedNumberOfRows = 146;

    TabbedResultTable tabbedResultTable;

    @Given("A valid input file")
    public void a_valid_input_file() {
        fileName = "src/test/resources/data/multipleRecordInput.txt";
    }

    @Given("A large input file")
    public void a_large_input_file() {
        largeFile = "src/test/resources/data/manyRecordInput.txt";
    }

    @Given("ordered input")
    public void ordered_input() { orderedFile = "src/test/resources/data/testResultRowOrder.txt"; }

    @When("the file is processed")
    public void the_file_is_processed() {

        tabbedResultTable = marcTextService.transFormToTabbedOutPut(fileName);
    }

    @When("the large file is processed")
    public void the_large_file_is_processed() {
        tabbedResultTable = marcTextService.transFormToTabbedOutPut(largeFile);
    }

    @When("the ordered input is processed")
    public void the_ordered_input_is_processed() {
        tabbedResultTable = marcTextService.transFormToTabbedOutPut(orderedFile);
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

    @Then("each row is transposed into the correct position")
    public void each_row_is_transposed_into_the_correct_position() {
        String row4_650Tag1 = tabbedResultTable.getResultRows().get(2).getPrintRow().get(2);
        Assert.assertEquals("$a Epitaphs $z Malaysia $z Pulau Pinang (State)", row4_650Tag1);
        ;
    }


}
