# Employee Data Management System
## Employee Management System in Java and Spring Boot.

### APIs
1. GetAllEmployees --> Returns all the Employees data from Cache.
2. GetEmployeeById --> Return Empoyee Information based on the EmployeeId provided. Fetches Data from Cache, Incase of Cache Miss fetches data from DB.
3. SaveEmployee --> Save a new Employee Entry to the DB.
4. DeleteEmployee --> Delete a Employee Entry from the DB.
5. GetEmployeeByCompany --> Returns a List of Employees working in the Same company.
----
### Other Features
1. Implemented Cache and Cache Refresh of 5 mins.
2. Warmup calls to prevent server cold start
3. Custom Exceptions to provide better message.
----
#### Note:- SQL query to create DB is provided in src/main/resources/Employee.sql



