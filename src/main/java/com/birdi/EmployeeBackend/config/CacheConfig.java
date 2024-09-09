package com.birdi.EmployeeBackend.config;


import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;
import java.time.Duration;


@Configuration
public class CacheConfig {

    @Value("${cache.timetoexpire}")
    private Long CacheTime;

    @Bean
    public CacheManager anotherCacheManager() throws URISyntaxException {
        System.out.println("This is the time to expire "+CacheTime);
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        CacheConfiguration<Object, Object> employeeCacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
               Object.class, Object.class, ResourcePoolsBuilder.heap(50)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(CacheTime))).build();
        javax.cache.configuration.Configuration<Object, Object> cacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(employeeCacheConfiguration);

        cacheManager.createCache("employees",cacheConfiguration);
        return cacheManager;
    }
}
