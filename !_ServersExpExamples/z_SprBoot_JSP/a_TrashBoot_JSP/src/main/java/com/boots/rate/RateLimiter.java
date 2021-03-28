package com.boots.rate;

public interface RateLimiter {
    void increment(String guid);
    boolean isAllowed(String guid);
    int getTotalUserCount();
    int getMaxGuidCount();
}
