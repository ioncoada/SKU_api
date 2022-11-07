#SKU API interview coding task 

#Used tools and frameworks
Rest-Assured

Maven repository

JUnit

jackson-databind


# Description 
Maven based testing project that runs HTTP calls to manage Stock Keeping Unit identifiers. Framework is built using rest-assured libraries,junit-jupiter and jackson-databind. 
Api-test package includes two test classes that run positive and negative edge-cases scenarios. Base sku page contain some basic parameters used in tests. 
Utils package has Configuration reader that is used to retrieve information from properties file. 


# To run the project from the command line 
$mvn test 

After build is finished, it will present results of how many tests passed, failed and skipped 

