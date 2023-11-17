package com.example.anirudh.cacheManager;

import com.example.anirudh.model.Employee;

import java.util.List;

public interface InMemoryCache {

    public void setAll();
    public void set(Employee employee);
    public List<Employee> getAll();
    public Employee get(Employee employee);
    public void update();

}
