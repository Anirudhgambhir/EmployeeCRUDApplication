package com.example.anirudh.cacheManager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateTask {

    private final CacheManager cacheManager;

    private final ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);

    public void setupCacheUpdate() {
        threadPoolExecutor.scheduleAtFixedRate(
                new CacheUpdateRunnable(cacheManager), 1800, 300, TimeUnit.SECONDS);
    }
}
