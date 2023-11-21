package com.example.anirudh.di;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.cache.CacheManager;
import com.example.anirudh.cache.cacheUpdate.CacheUpdateTask;
import com.example.anirudh.cache.caches.impl.EmployeeCache;
import com.example.anirudh.cache.cacheLoader.EmployeeCacheLoader;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CommonDI {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LoadingCache<Integer, Employee> EmployeeCache(EmployeeDAO employeeDAO) {
        return CacheBuilder.newBuilder().refreshAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(1500).build(new EmployeeCacheLoader(employeeDAO));
    }

    @Bean
    public EmployeeCache employeeCache(EmployeeDAO employeeDAO, LoadingCache<Integer, Employee> employeeCache){
        return new EmployeeCache(employeeDAO, employeeCache);
    }

    @Bean
    public CacheUpdateTask cacheUpdateTask(EmployeeCache employeeCache) {
        return new CacheUpdateTask(employeeCache);
    }

    @Bean
    public CacheManager cacheManager(EmployeeCache employeeCache, CacheUpdateTask cacheUpdateTask) {
        return new CacheManager(employeeCache, cacheUpdateTask);
    }
}
