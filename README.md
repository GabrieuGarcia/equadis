# About the project

The assessment is to create a "Customer and Bank Account" system solution.
The system should allow the creation of customers and accounts as well as track all
transactions.
A customer is represented by their ID and a name.
An account can be associated with a customer and is created with an initial amount.
An account cannot hold a negative amount.
Design and develop an Enterprise level solution and build the system using Java 11+ platform
using spring framework. 


# Technologies used and Instalation

All this techs used must be downloaded and installed on you local machine.
Bellow is the link for each one of them:

1 - Java 21 by Open JDK:
https://www.oracle.com/pt/java/technologies/downloads/#java21

2 - Maven 4.0.0:
https://maven.apache.org/download.cgi

3 - PostgreSQL:
https://www.postgresql.org/download/windows/

# The Project

This is a miscroservice POC in order to the funcionalities, you will need:

1 - To install PostgreSQL;

2 - Download pgAdmin;

3 - Create a database with the name MS_CUSTOMER;

3.1 - Create a password for this database, you can put: 753df;

  ps: case you putted other passowrd you will have to change later on the properties on the project in:
  
      - application.properties: spring.datasource.password=hereYourPassword

3.1 - Create a database with the name MS_ACCOUNT with the same passowrd;

4 - Download the two services ms-customer and ms-account on you local machine

5 - Use your favorite IDE to put them Up!


# How to use

1 - You will need to create an customer to Have the ID to create an Account an so on:

  You can do this by calling this API:

  POST - http://localhost:8081/customer/save

  and passing a body with the name of to customer like this:

  {
    "customerName": "Gabriel Test"
  }

  This will return someting like:

  {
    "customerId": "8bc74531-2051-467a-b222-3c4068ab90f0",
    "customerName": "Gabriel Test"
  }
  

  ////////////////////////////////////////////////////////////
  

2 - Ok Now that we have a customer we can create an Account:

  You can do this by calling this API:

  POST - http://localhost:8082/account/save
  
  and passing a body like this:

{
    "customerId": "8bc74531-2051-467a-b222-3c4068ab90f0",
    "customerName": "Gabriel Test"
}

This will return someting like:

{
    "accountId": "07d5c728-a877-4bf1-b284-f1299f48c272",
    "customerId": "8bc74531-2051-467a-b222-3c4068ab90f0",
    "accountAmount": 10
}


//////////////////////////////////////////////////////////////


3 - Finally we now may be able to create transactions for an specific Account:

  You can do a deposit for example by calling this API:

  POST - http://localhost:8082/transaction/deposit
  
  and passing a body like this:

{
    "transactionAmount" : 20000,
    "account" : {
        "accountId" : "07d5c728-a877-4bf1-b284-f1299f48c272"
    }
}

This will return someting like:

{
    "transactionId": "1e7ce884-7e49-4244-b6bc-3c13eb620e7f",
    "account": {
        "accountId": "07d5c728-a877-4bf1-b284-f1299f48c272",
        "customerId": "8bc74531-2051-467a-b222-3c4068ab90f0",
        "accountAmount": 20000.00
    },
    "transactionAmount": 20000,
    "transactionDate": "2023-11-06T09:17:24.723+00:00",
    "transactionType": "DEPOSIT"
}


4 - Or we can do a withdraw by calling this API:

POST - http://localhost:8082/transaction/withdraw

{
    "transactionAmount" : 1234,
    "account" : {
        "accountId" : "07d5c728-a877-4bf1-b284-f1299f48c272"
    }
}

This will return someting like:

{
    "transactionId": "be2b627a-555a-4d36-ad9c-09a6f53e44c3",
    "account": {
        "accountId": "07d5c728-a877-4bf1-b284-f1299f48c272",
        "customerId": "8bc74531-2051-467a-b222-3c4068ab90f0",
        "accountAmount": 0.00
    },
    "transactionAmount": 1,
    "transactionDate": "2023-11-05T19:04:30.311+00:00",
    "transactionType": "WITHDRAW"
}
/////////////////////////////////////////////////////////

# Other functionalities

Those services provides another functionalities like searching for specifics Transactions, feel free to tryout:

GET - find All - http://localhost:8082/transaction/search

GET - find All by account Id - http://localhost:8082/transaction/search/accountId?accountId=???????????

GET - find All between dates - http://localhost:8082/transaction/search/dates?firstDate=2023-10-01T00:00:00.000+00:00&secondDate=2024-11-01T00:00:00.000+00:00

GET - find All between amounts - http://localhost:8082/transaction/search/amounts?firstAmount=20000&secondAmount=27000




# Thanks for all guys hope you like it!



