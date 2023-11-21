package com.example.anirudh.cache.cacheUpdate;

import com.example.anirudh.cache.caches.impl.EmployeeCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateRunnable implements Runnable{

    private final EmployeeCache cacheManager;

    @Override
    public void run() {
        log.info("Cache Update Begin");
        cacheManager.update();
        log.info("Cache Update successfully completed");
    }
}
