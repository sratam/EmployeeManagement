package com.birdi.EmployeeBackend.Repo;

import com.birdi.EmployeeBackend.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    public List<Employee> findByNameContaining(String query);
    public List<Employee> findByStatus(String status);
}
