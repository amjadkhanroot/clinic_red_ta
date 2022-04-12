    ----Clinic App for demo----

    ----Develped by Angular 13 & Spring boot 2.6.6----

##How to run the app:

- on root dir run `docker-compose up --build`* and you're ready to go!<br>
- Frontend App running on port: `42002`<br>
- Backend API running on port: `8080`<br>
- Database on port: `3306`<br>

######*You must have docker-compose install.

##How to use the app:
    You have two roles (doctor,patient).

###What a Doctor can do:
- Create his/her account.
- Create/Update a profile for a patient who alreay registered in the system.<br>
- Create/Update a record for a patient's profile who alreay have a profile.<br>
- List patients profile.<br>
- List patients record in summary or full details.<br>
- Delete patients record if he/she is the creator.<br>

###What a Patient can do:
- Create his/her account.
- Display his/her profile if alreay created in the system by a doctor.<br>
- Display his/her records in summary or full details.<br>

###Postman Collection:
`https://www.getpostman.com/collections/9ce74f0c92b81fd8e859`

    You can access the application on : `http://localhost:42002` 
    Thanks & regards 
    Amjad!
