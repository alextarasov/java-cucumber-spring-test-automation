# java-cucumber-spring-test-automation

This solution was tested on Windows 10 with Docker running on WSL Ubuntu.
## Requirements
* Pull https://hub.docker.com/_/wordpress  
 Additional information on setup:  
 https://docs.docker.com/compose/wordpress/  
 Note: the following line should be added to the db service of the docker-compose.yml from the link above (tests using port 8083 for connection to DB):  
  ```
  ports:
    - 127.0.0.1:8083:3306
  ```
  So it will be:  
  ```
  version: '3.3'  
  
  services:
     db:
       image: mysql:5.7
       ports:
         - 127.0.0.1:8083:3306
  ...rest of the configuration file.
  ```

* WordPress configuration:
  - Apply "Twenty Nineteen" Theme.  
  (Admin page -> Appearance-> Themes)  
  
  - Change "Permalink Settings" to "Day and name".  
  (Admin page -> Settings -> Permalinks -> Common Settings)  
  
  - Install token plugin https://wordpress.org/plugins/jwt-authentication-for-wp-rest-api/  
  For additional configuration after plugin isntallation, the following commands could be used inside "wordpress:latest" container:  

    To add JWT_AUTH_SECRET_KEY to wp-config.php:  
    ```
    sed -i '/^require_once/i define('JWT_AUTH_SECRET_KEY', 'some-secret-key');' wp-config.php
    ```
    .htaccess change:
    ```
    sed -i '/^# END WordPress/i SetEnvIf Authorization "(.*)" HTTP_AUTHORIZATION=$1' .htaccess
    ```

* Create new test post page. This could be done using REST API(example below):  
  - Obtain token.  
  Method: POST  
  uri: http://localhost:8000/wp-json/jwt-auth/v1/token  
  body("form-data" in Postman application):  
    ```
    username:wordpress_admin_username
    password:wordpress_admin_password
    ``` 
  - Create test post page.  
  Method: POST  
  Headers requires token obtained on the previouse step.  
  Headers format:  
    ```
    Authorization: Bearer obtained_token
    ```
    uri: http://localhost:8000/wp-json/wp/v2/posts  
    body:  
      ```
      {
          "title": "test-post",
          "status": "publish",
          "content": "New test page"
      }
      ```
* Download Chromedriver and put it to some folder(e.g. C:/chromedriver_win32/chromedriver.exe).  
https://sites.google.com/a/chromium.org/chromedriver/  

## Files changes
* Update "chromedriver.path" in "src/test/resources/selenium.configuration.properties" if your path to "chromedriver.exe" is different.
* Update wordpress admin credentials in "src/test/resources/wordpress.credentials.properties" if your credentials are different.
* Replace "/2021/02/06/test-post/" in the "openTestPostPage()" method of the file "src/test/java/pages/TestPostPage.java" with a part after the http://localhost:8000 of your "test-post" page.
* Change full path to the test file in the step defention "i_upload_a_csv_file() " of the file: "src/test/java/cucumbergeneralfunctionality/stepdefinitions/SeleniumStepDefinitions.java". 

## To run tests using Maven
Run the following command:
```
mvn test 
```
## Jenkins
Install Cucumber-JVM Reports plugin for Jenkins:
https://github.com/jenkinsci/cucumber-reports-plugin/wiki/Detailed-Configuration

Jenkins job configuration for running the tests with a report:
* Build  
  Add the following command to the "Execute Windows batch command":  
  ```
  mvn test -f full_path_to_the_project_root_with_a_pom
  ```
* JSON Report Location
  - JSON Reports Path:  
  Full path to the target/cucumber folder  
  - File Include Pattern:  
  cucumber.json  

