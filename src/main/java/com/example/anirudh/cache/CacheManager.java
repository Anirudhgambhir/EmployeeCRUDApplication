package com.example.anirudh.cache;

import com.example.anirudh.cache.cacheUpdate.CacheUpdateTask;
import com.example.anirudh.cache.caches.impl.EmployeeCache;
import com.example.anirudh.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CacheManager {

    private final EmployeeCache employeeCache;

    private final CacheUpdateTask cacheUpdateTask;

    @Autowired
    public CacheManager(EmployeeCache employeeCache, CacheUpdateTask cacheUpdateTask) {
        this.employeeCache = employeeCache;
        this.cacheUpdateTask = cacheUpdateTask;
        buildCache();
        cacheUpdateTask.setupCacheUpdate();
    }

    private void buildCache() {
        log.info("building cache");
        initCache();
        log.info("cache building completed");
    }

    private void initCache() {
        log.info("building employee Cache");
        final long startTime = System.currentTimeMillis();
        employeeCache.setAll();
        log.info("employee cache building completed in {} ms", System.currentTimeMillis() - startTime);
    }

    public void setEmployee(Employee employee) {
        employeeCache.set(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeCache.getAll();
    }

    public Employee getEmployee(int employeeId) {
        return employeeCache.get(employeeId);
    }
}
