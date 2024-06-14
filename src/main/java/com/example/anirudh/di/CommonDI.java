package com.example.anirudh.di;

import com.example.anirudh.accessor.EmployeeInformationAccessor;
import com.example.anirudh.accessor.EmployeeInformationCacheAccessor;
import com.example.anirudh.accessor.EmployeeInformationDBAccessor;
import com.example.anirudh.accessor.dao.EmployeeDAO;
import com.example.anirudh.cache.CacheManager;
import com.example.anirudh.cache.cacheLoader.EmployeeCacheLoader;
import com.example.anirudh.cache.cacheUpdate.CacheUpdateRunnable;
import com.example.anirudh.cache.cacheUpdate.CacheUpdateTask;
import com.example.anirudh.cache.caches.InMemoryCache;
import com.example.anirudh.cache.caches.impl.EmployeeCache;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public InMemoryCache employeeCache(EmployeeDAO employeeDAO, LoadingCache<Integer, Employee> employeeCache) {
        return new EmployeeCache(employeeDAO, employeeCache);
    }

    @Bean
    public CacheUpdateTask cacheUpdateTask(CacheUpdateRunnable cacheUpdateRunnable) {
        return new CacheUpdateTask(cacheUpdateRunnable);
    }

    @Bean
    public CacheManager cacheManager(InMemoryCache employeeCache, CacheUpdateTask cacheUpdateTask) {
        return new CacheManager(employeeCache, cacheUpdateTask);
    }

    @Bean
    @Qualifier("EmployeeInformationCacheAccessor")
    public EmployeeInformationAccessor getEmployeeInformationCacheAccessor(EmployeeDAO employeeDAO, CacheManager cacheManager) {
        return new EmployeeInformationCacheAccessor(employeeDAO, cacheManager);
    }

    @Bean
    @Qualifier("EmployeeInformationDBAccessor")
    public EmployeeInformationAccessor getEmployeeInformationDBAccessor(EmployeeDAO employeeDAO) {
        return new EmployeeInformationDBAccessor(employeeDAO);
    }

    @Bean
    public CacheUpdateRunnable getCacheUpdateRunnable(InMemoryCache inMemoryCache) {
        return new CacheUpdateRunnable(inMemoryCache);
    }
}
