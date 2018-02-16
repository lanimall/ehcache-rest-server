package org.ehcache.server.rest.endpoints;

import org.ehcache.server.rest.EhcacheRestServerApp;
import org.ehcache.server.rest.pojo.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by fabien.sanglier on 2/15/18.
 */
public class CacheManagerResourceTest {

    @Test
    public void testGetCaches() {
        try {

            Set<String> cacheNames = new HashSet<String>(Arrays.asList(new String[]{"smallTest1","smallTest2","smallTest3","smallTest4"}));

            List<Cache> caches = new CacheManagerResource().getCaches();
            for(Cache cache : caches){
                assertTrue(true == cacheNames.remove(cache.getName()));
            }
            assertTrue(cacheNames.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected Failure." + e.getMessage());
        }
    }
}
