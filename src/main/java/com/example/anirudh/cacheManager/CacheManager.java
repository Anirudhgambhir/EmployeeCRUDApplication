package com.example.anirudh.cacheManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
}
