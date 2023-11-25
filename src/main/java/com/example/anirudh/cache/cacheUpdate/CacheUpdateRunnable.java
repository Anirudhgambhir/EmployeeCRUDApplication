package com.example.anirudh.cache.cacheUpdate;

import com.example.anirudh.cache.caches.InMemoryCache;
import com.example.anirudh.cache.caches.impl.EmployeeCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateRunnable implements Runnable{

    private final InMemoryCache cacheManager;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        log.info("Cache Update Begin");
        cacheManager.update();
        log.info("Cache Update successfully completed and took {} ms to complete", System.currentTimeMillis() - startTime);
    }
}
