Feature: Produce Tabbed Output File from text input file


  Scenario: Record Id is loaded
    Given A valid input file
    When the file is processed
    Then the record id is populated

  Scenario: Tag Rows are loaded
    Given A valid input file
    When the file is processed
    Then tags and contents are added to the record

    Scenario: All rows are transposed
      Given A large input file
      When  the large file is processed
      Then all rows have been transposed
      And the number of headings is correct
