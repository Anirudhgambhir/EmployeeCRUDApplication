package com.example.anirudh.cache.cacheUpdate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateTask {

    private final CacheUpdateRunnable cacheUpdateRunnable;
    private final ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);

    public void setupCacheUpdate() {
        threadPoolExecutor.scheduleAtFixedRate(
                cacheUpdateRunnable, 300, 600, TimeUnit.SECONDS);
    }
}
