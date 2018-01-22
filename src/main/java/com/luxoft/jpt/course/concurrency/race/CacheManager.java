
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.concurrency.race;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.luxoft.jpt.course.concurrency.race.CacheManager.CACHE_ENTRIES;
import static com.luxoft.jpt.course.concurrency.race.CacheManager.KEY_PREFIX;
import static com.luxoft.jpt.course.concurrency.race.CacheManager.STALE;
import static com.luxoft.jpt.course.concurrency.race.CacheManager.VALID;


/**
 * Deadlock with ReentrantReadWriteLock
 */
public class CacheManager {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static final String STALE = "STALE";
    public static final String VALID = "VALID";
    public static final String KEY_PREFIX = "Key-";
    public static final int CACHE_ENTRIES = 5;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        CacheHolder cachedData = new CacheHolder();

        CacheWriter runner1 = new CacheWriter("CacheWriter", cachedData);
        CacheReader runner2 = new CacheReader("CacheReader", cachedData);

        Thread t1 = new Thread(runner1, "CacheWriter");
        Thread t2 = new Thread(runner2, "CacheReader");

        System.out.printf("Adding [%d] entries inside cache ... \n", CACHE_ENTRIES);

        t1.start();
        t1.join();

        System.out.println("done adding \n");

        System.out.printf("Reading [%d] cache entries ... \n", CACHE_ENTRIES);

        t2.start();
        t2.join();

        System.out.println("done reading");
    }
}

class CacheReader implements Runnable {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private String name;
    private CacheHolder cacheData;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public CacheReader(String name, CacheHolder cacheData) {
        this.name = name;
        this.cacheData = cacheData;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {
        int i = 0;

        while (i++ < CACHE_ENTRIES) {
            String key = KEY_PREFIX + i;
            String value = cacheData.getData(key);

            System.out.printf("[%s] retrieved key [%s] with value [%s] \n", name, key, value);
        }
    }
}

class CacheWriter implements Runnable {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private String name;
    private CacheHolder cacheData;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public CacheWriter(String name, CacheHolder cacheData) {
        this.name = name;
        this.cacheData = cacheData;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {
        Random random = new Random();
        int i = 0;

        while (i++ < CACHE_ENTRIES) {
            String key = KEY_PREFIX + i;
            String value = random.nextBoolean() ? STALE : VALID;

            cacheData.addData(key, value);

            System.out.printf("[%s] added key [%s] with value [%s] \n", name, key, value);
        }
    }
}

class CacheHolder {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private Map<String, String> cache = new HashMap<String, String>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String getData(String key) {

        lock.readLock().lock();

        try {

            String value = cache.get(key);
            if ((STALE.equals(value)) || (null == value)) {
                System.out.printf("Found Key [%s] contains stale data, trying to update it ... \n", key);
                addData(key, VALID);
                System.out.printf("done \n");
            }

            return cache.get(key);

        } finally {
            lock.readLock().unlock();
        }
    }

    public void addData(String key, String object) {

        lock.writeLock().lock();

        try {

            cache.put(key, object);

        } finally {
            lock.writeLock().unlock();
        }
    }
}
