package com.birdi.EmployeeBackend.Services;

import com.birdi.EmployeeBackend.Model.Employee;
import com.birdi.EmployeeBackend.Model.User;
import com.birdi.EmployeeBackend.Repo.EmployeeRepo;
import com.birdi.EmployeeBackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private UserService userservice;


    public List<Employee> getActiveEmployees(){
            return repo.findByStatus("active");
    }

    public List<Employee> getAllEmployees(){
        return repo.findAll();
    }

    public Employee createEmployee(Employee newEmployee){
        User user = new User();
        user.setUsername(newEmployee.getName());
        user.setPassword("1234");
        userservice.createUser(user);
        return repo.save(newEmployee);
    }

    @Cacheable(value = "employees", key="#id")
    public Employee getEmployeeByID(Long id){
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Invalid ID"));
    }

    @Cacheable(value = "employees", key = "#page + '-' + #size")
    public HashMap<String,Object> getEmployeesByPage(int page, int size){
        System.out.println("Fetching Movies from the database");
        Pageable pageable = PageRequest.of(page,size);
        Page<Employee> PagedResult= repo.findAll(pageable);
        HashMap<String,Object> result = new HashMap<>();
        result.put("content", PagedResult.getContent());
        result.put("total_pages",PagedResult.getTotalPages());
        return result;
    }

    @CachePut(value = "employees", key = "#id")
    public Employee updateEmployee(Long id,Employee newEmployee){
        Employee oldEmployee = repo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not found"));
        if(newEmployee.getName() != null){
            oldEmployee.setName(newEmployee.getName());
        }
        if(newEmployee.getRole() != null){
            oldEmployee.setRole(newEmployee.getRole());
        }

        if(newEmployee.getEmail() != null){
            oldEmployee.setEmail(newEmployee.getEmail());
        }

        if(oldEmployee.getStatus() != null){
            oldEmployee.setStatus(newEmployee.getStatus());
        }

        if(oldEmployee.getSalary() != null){
            oldEmployee.setSalary(newEmployee.getSalary());
        }

        System.out.println(oldEmployee.toString());
        return repo.save(oldEmployee);
    }

    public void deleteEmployee(Long id){
        Employee employee = repo.findById(id).orElseThrow(()-> new RuntimeException("Employee Not Found"));
        repo.delete(employee);
    }

    public List<Employee> searchByName(String s){
        if(s==null) return repo.findAll();
        return repo.findByNameContaining(s);
    }

}
