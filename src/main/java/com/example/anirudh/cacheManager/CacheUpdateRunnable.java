package com.example.anirudh.cacheManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheUpdateRunnable implements Runnable{

    private final CacheManager cacheManager;

    @Override
    public void run() {
         cacheManager.update();
    }
}
