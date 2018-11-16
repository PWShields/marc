# marc converter

### About

This is a utility application for manipulating Marc records.

##### Features

1. Loads a text file and transposes it into a tab delimited CSV file suitable for loading into Excel.

The input file must be in the form:

```text
001 7461413
245 1 0 $a Epitaph : $b the Northam Road Protestant Cemetery : George Town, Penang / $c Marcus Langdon.
650   0 $a Epitaphs $z Malaysia $z Pulau Pinang (State)
650   0 $a Inscriptions $z Malaysia $z Pulau Pinang (State)
650   0 $a Christian cemeteries $z Malaysia $z Pulau Pinang (State)

001 3093657
245 1 0 $a Rites of belonging : $b memory, modernity, and identity in a Malaysian Chinese community / $c Jean DeBernardi.
650   0 $a Chinese $z Malaysia $z Pinang $x Ethnic identity.
650   0 $a Chinese $z Malaysia $z Pinang $x Rites and ceremonies.
650   0 $a Chinese $z Malaysia $z Pinang $x Societies, etc.

001 3416787
245 0 0 $a Penyata Juru Odit Negara. $p Negeri Pulau Pinang.
650   0 $a Finance, Public $z Malaysia $z Pinang $x Accounting $x Periodicals.
```
This is the ouput from the related application MarcGrep application.

The output will be in the form:

```text
001	245.1	650.1	650.2	650.3	650.4	650.5	650.6	650.7	650.8	650.9	650.10	650.11
3416787	$a Penyata Juru Odit Negara. $p Negeri Pulau Pinang.	$a Finance, Public $z Malaysia $z Pinang $x Accounting $x Periodicals.										
7461413	$a Epitaph : $b the Northam Road Protestant Cemetery : George Town, Penang / $c Marcus Langdon.	$a Epitaphs $z Malaysia $z Pulau Pinang (State)			$a Inscriptions $z Malaysia $z Pulau Pinang (State)	$a Christian cemeteries $z Malaysia $z Pulau Pinang (State)	
```
This is suitable for importing into Excel.

(Note the underlining assumptions are that the first value in the input row is a Marc Tag, the next values are Marc indicators which we through away and anything else(following the first $ sign) on each row is the content. Tag numbers 002 to 009 are treated slightly differently however should be processable (they have no $ sign)).

### Run Locally

##### Run Combined Application

```
mvn clean install
```
This will build the frontend module, copy the frontend output into the backend module, and builds the backend - that now represents the entire application.

You may need to comment out the modules first time to install the parent pom, which the modules look for, and won't be there the first time.

```
mvn --projects backend spring-boot:run

```
Runs the application that includes the compiled front and backend code as built by the maven install.

Changes in the backend code may be picked up courtesy of Spring Dev Tools. Changes to front end won't be picked up until you run maven install again. (Unless you are running the frontend via the npm server that is).

You can then access the application at localhost:8081.

##### Run Backend Separately

You can build/run the backend as a normal maven project; i.e mvn clean, mvn package, or run MarcApplication class as Spring boot fom IDE.

Note though that you may need to do more mvn clean and mvn packages than usual to pick up new code changes. Also be aware that the backend will have the latest frontend code packaged prior to the latest mvn clean, so if you go to 8080 you will potentially see old backend code.

##### Run Frontend Separately

```bash
cd frontend
npm run serve
```

This starts the front end, useful when you want instant feedback on frontend only changes. It will be running on port 8080 unless the backend is already running in which case the frontend automatically switches up to 8081.

##### Run both independently

I normally run the backend separately and then the frontend while I am developing as the two can still communicate even though the app hasn't been packaged into one jar.


### Tech Stack

 - Spring - Boot
 - Java 8
 - Maven
 - VueJS
 - Vuetify
 - Cucumber
 - JUnit 4
 - Lombok
 
 Lombok provides annotations to remove boiler plate code, specifically getters and setters, equals, hashCode and toString. Any of these can be manually overwritten if required. Note that Lombok requires annotation processing to be turned on in your IDE. For Intellij this can be done by:
 
 ```
 Install the lombok plugin
 Turn annotation processing on
    - Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing
 Make this your default for new projects with
    - File -> Other Settings -> Preferences For New Projects -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing

```

We are using the Single-File Components (.vue) approach with VueJS 2.

#### Testing

##### Manual

You can post suitably formatted input data to:
```text
http://localhost:8080/download/csv
```
Headers:
```text
Content-type    multipart/form-data
```
Body:
```text
Parameter name      Content         Type
file                filename.txt    File
```

Or you can run the application locally.

#### Automatic

There are 2 Cucumber features testing multiple scenarios which are run whenever mvn test is run.

### Creating a similar project from scratch

This project contains both backend Spring Boot code and a frontend VUEJS app. They are bound together as maven modules so that all code can be built into one jar and run as one application.

It is based on the instructions in the README of this project: 

https://github.com/jonashackt/spring-boot-vuejs

A summary of the steps are including Intellij and Github are:

1. Manually create an empty directory representing the root folder of the project
2. Change into this folder
3. Manually create a backend folder
4. Use https://start.spring.io to initialise a maven based Spring Boot App with the components you would like (Web and Actuator would do for starters), 
5. Unzip the resultant Spring Boot app into the backend folder.
6. From Intellij open the app (so that you can edit the files more easily).
7. Add spring-boot-maven-plugin and maven-resources-plugin configured to copy the frontend apps output across to the backend app as per the spring-boot-vuejs (SBV) README, to the backend POM's build section.
8. Change back to the root folder of the project
9. Run vue create frontend --no-git to created the frontend directory and initialise the frontend app, follow the web version of the SBV README as a guide to answering the prompts that this process generates.
10. Manually create a POM for the frontend app and add the frontend-maven-plugin to it's build section configured to build the frontend app
11. Create a vue.config.js file level with package.json and add the module export output and asset directories as per the SBV README.

You now have the skeleton for your combined application and can follow the steps in "Run Locally" above to start the application.

NB the above requires that you have all the relevant technologies already installed including Java, Maven, node and vue cli.

Additional steps to use Vuetify (a library with front end components already there):

1. run vue add vuetify

(alternatively run vue init vuetifyjs/webpack frontend --no-git instead of step 9 above) 
