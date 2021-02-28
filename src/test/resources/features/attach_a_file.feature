@selenium
@delete_uploaded_file_after_test
Feature: Attach a file on the admin media page

  Scenario: Attach csv file
    Given Login to the admin page
    And Open admin media page
    And Upload a csv file
    Then File name on the page should match uploaded
