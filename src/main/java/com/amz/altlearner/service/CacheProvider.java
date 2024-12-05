package com.amz.altlearner.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheProvider {

    public final Map<String, Map> cache = new ConcurrentHashMap<>();

    public <X> Map<String, X> getCache(final String type) {
        return cache.computeIfAbsent(type, t -> new HashMap<String, X>());
    }

}
