# PhoneBook REST application

## Requirements:
 - Java8
 - Maven3

Build with integration tests exec:  
`mvn clean verify`

Launch app within embedded tomcat:  
`mvn tomcat7:run-war`

Shutdown embedded tomcat:  
`mvn tomcat7:shutdown`  

In case shutdown could not be performed (as to buggy plugin) - exec `jcmd`, remember procID of `tomcat7:run-war` and `kill -9 {procID}`

## API

GET `/batch/jobs/phonebook-csv-dataload` - perform initial dataload from CSV file which declared by property `batch.csv.dataload.file.path`  
GET `/batch/jobs/phonebook-csv-dataload?filePath=/home/user/file.csv` - perform initial dataload from CSV file which declared by absolute system path  
GET `/api/v1/customers` - return all customers  
GET `/api/v1/customers/{name}` - return specific customer  
POST `/api/v1/customers` - create specific customer  
Payload example: `{"name":"Jill","phone":"+13975554201"}`
* in case customer already exist - number added to him  
* in case number already assigned to existing customer - 500 code returned

PUT `/api/v1/customers/{name}` - update specific customer's telephone number  
    Payload example: `{phone":"+13975554201"}`
* in case customer does not exist - 500 code returned
* in case number already assigned to target customer - rewrite performed
* in case number already assigned to another customer - 500 code returned

DELETE `/api/v1/customers/{name}` - delete specific customer  
* in case customer does not exist - 500 code returned
 
GET `/api/v1/customers/reset` - cleanup in-memory customers storage

Assumptions:
* Assumed that customers names are single-worded.
* Assumed that at startup there are no duplicated phone numbers under different customers.