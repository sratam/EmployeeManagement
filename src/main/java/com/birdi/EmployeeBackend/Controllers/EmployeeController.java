package com.birdi.EmployeeBackend.Controllers;


import com.birdi.EmployeeBackend.Model.Employee;
import com.birdi.EmployeeBackend.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getActiveEmployees(),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getallemployees(){
            return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<HashMap<String,Object>> getEmployeesByPage(
            @RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "10") int size
    ){
        return new ResponseEntity<>(employeeService.getEmployeesByPage(page, size),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeService.getEmployeeByID(id),HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable("name") String s){
        return new ResponseEntity<>(employeeService.searchByName(s),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        System.out.println("In the patching method");
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
