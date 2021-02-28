@mysql
Feature: General functionality

  @selenium
  @delete_posted_comment_from_db_after_test
  Scenario: Leave a comment as a guest
    Given Open "Test Post" page
    And Leave new comment as a guest
    Then New comment data should present in database

  @selenium
  @obtain_rest_api_token
  @delete_created_post_via_rest_api_after_test
  Scenario: Created post via REST API is visible on front-end as a first in the list
    Given Creating new post using rest api
    And Open the home page
    Then Created post should present as first in the list

  @obtain_rest_api_token
  @delete_created_post_via_rest_api_after_test
  Scenario: Created post via REST API present in database
    Given Creating new post using rest api
    Then New post data should present in database
