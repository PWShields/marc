Feature: Data can be read from text input file

  Scenario: Read Input File
    Given A valid text file
    When the file is read
    Then we can read the contents

  Scenario: Record Id is read
    Given A valid text file
    When the file is read
    And the row starts with 001
    Then the record id is read

  Scenario: Tag Rows are read
    Given A valid text file
    When the file is read
    And the row starts with tag number
    Then tags and contents are read
