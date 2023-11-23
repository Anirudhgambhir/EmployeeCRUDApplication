package com.example.anirudh.cache.cacheUpdate;

import com.example.anirudh.cache.caches.impl.EmployeeCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateTask {

    private final EmployeeCache employeeCache;
    private final ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);

    public void setupCacheUpdate() {
        threadPoolExecutor.scheduleAtFixedRate(
                new CacheUpdateRunnable(employeeCache), 300, 600, TimeUnit.SECONDS);
    }
}
