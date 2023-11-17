package com.example.anirudh.rest;

import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRestController {
    private static final List<String> firstNames = Arrays.asList("Liam", "Noah", "Oliver", "James", "Elijah", "William", "Henry",
            "Lucas", "Benjamin", "Theodore", "Mateo", "Levi", "Sebastian", "Daniel", "Jack", "Michael", "Alexander", "Owen",
            "Asher", "Samuel", "Ethan", "Leo", "Jackson", "Mason", "Ezra", "John", "Hudson", "Luca", "Aiden", "Joseph", "David",
            "Jacob", "Logan", "Luke", "Julian", "Gabriel", "Grayson", "Wyatt", "Matthew", "Maverick", "Dylan", "Isaac", "Elias",
            "Anthony", "Thomas", "Jayden", "Carter", "Santiago", "Ezekiel", "Charles", "Josiah", "Caleb", "Cooper", "Lincoln",
            "Miles", "Christopher", "Nathan", "Isaiah", "Kai", "Joshua", "Andrew", "Angel", "Adrian", "Cameron", "Nolan", "Waylon",
            "Jaxon", "Roman", "Eli", "Wesley", "Aaron", "Ian", "Christian", "Ryan", "Leonardo", "Brooks", "Axel", "Walker", "Jonathan",
            "Easton", "Everett", "Weston", "Bennett", "Robert", "Jameson", "Landon", "Silas", "Jose", "Beau", "Micah", "Colton", "Jordan",
            "Jeremiah", "Parker", "Greyson", "Rowan", "Adam", "Nicholas", "Theo", "Xavier", "Hunter", "Dominic", "Jace", "Gael", "River",
            "Thiago", "Kayden", "Damian", "August", "Carson", "Austin", "Myles", "Amir", "Declan", "Emmett", "Ryder", "Luka", "Connor",
            "Jaxson", "Milo", "Enzo", "Giovanni", "Vincent", "Diego", "Luis", "Archer", "Harrison", "Kingston", "Atlas", "Jasper",
            "Sawyer", "Legend", "Lorenzo", "Evan", "Jonah", "Chase", "Bryson", "Adriel", "Nathaniel", "Arthur", "Juan", "George",
            "Cole", "Zion", "Jason", "Ashton", "Carlos", "Calvin", "Brayden", "Elliot", "Rhett", "Emiliano", "Ace", "Jayce",
            "Graham", "Max", "Braxton", "Leon", "Ivan", "Hayden", "Jude", "Malachi", "Dean", "Tyler", "Jesus", "Zachary",
            "Kaiden", "Elliott", "Arlo", "Emmanuel", "Ayden", "Bentley", "Maxwell", "Amari", "Ryker", "Finn", "Antonio",
            "Charlie", "Maddox", "Justin", "Judah", "Kevin", "Dawson", "Matteo", "Miguel", "Zayden", "Camden", "Messiah",
            "Alan", "Alex", "Nicolas", "Felix", "Alejandro", "Jesse", "Beckett", "Matias", "Tucker", "Emilio", "Xander",
            "Knox", "Oscar", "Beckham", "Timothy", "Abraham", "Andres", "Gavin", "Brody", "Barrett", "Hayes", "Jett",
            "Brandon", "Joel", "Victor", "Peter");
    private static final List<String> lastName = Arrays.asList("sharma", "verma", "tiwari", "gupta", "gambhir", "arora", "khandelwal", "kanwar", "malik", "doe", "gill", "garg");
    private static final List<String> companies = Arrays.asList("amazon", "apple", "microsoft", "google", "tcs", "infosys", "zaggle", "bain");

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int randomFirstName = random.nextInt(214);
            int randomLastName = random.nextInt(12);
            int randomCompany = random.nextInt(8);
            String email = firstNames.get(randomFirstName) + "-" + lastName.get(randomLastName) + "@" + companies.get(randomCompany) + ".com";
            Employee e = new Employee(firstNames.get(randomFirstName), lastName.get(randomLastName), email, companies.get(randomCompany));
            employeeService.saveEmployee(e);
        }
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) throws JsonProcessingException {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/companies/{companyName}")
    public List<Employee> getEmployeesByCompanyName(@PathVariable String companyName) {
        return employeeService.getEmployeesByCompanyName(companyName);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee newEmployee) throws JsonProcessingException {
        newEmployee.setId(0);
        return employeeService.saveEmployee(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee newEmployee) throws JsonProcessingException {
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}
