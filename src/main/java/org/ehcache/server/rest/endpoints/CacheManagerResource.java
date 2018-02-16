package org.ehcache.server.rest.endpoints;

import io.swagger.annotations.Api;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.ehcache.server.rest.pojo.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * A CacheManagerResource which permits the following operations:
 * <p>
 * <code>
 * GET /
 * <p>
 * Lists the Caches in the CacheManager.
 * </code>
 * <p>
 * e.g. <code>http://localhost:9090/ehcache/rest/</code>
 *
 * @author Greg Luck
 * @author Fabien Sanglier
 */

@Path("/caches")
@Api("/caches")
@Produces("application/json")
public class CacheManagerResource {

  private static final Logger LOG = LoggerFactory.getLogger(CacheManagerResource.class);

  private static final CacheManager MANAGER;

  static {
    MANAGER = CacheManager.getInstance();
  }


  /**
   * The full URI for the resource.
   * <p>
   * e.g. <code>//http://localhost:9090/ehcache/rest/testCache</code>
   */
  @Context
  private UriInfo uriInfo;

  /**
   * The HTTP request
   */
  @Context
  private Request request;


  /**
   * Routes the request to a {@link CacheResource} if the path is <code>/ehcache/rest/{cache}</code>
   *
   * @param cache
   * @return
   */
  @Path("{cache}")
  public CacheResource getCacheResource(@PathParam("cache") String cache) {
    return new CacheResource(uriInfo, request, cache);
  }

  /**
   * GET method implementation. Lists the available caches
   *
   * @return
   */
  @GET
  public List<Cache> getCaches() {
    LOG.debug("GET Caches");

    String[] cacheNames = MANAGER.getCacheNames();

    List<Cache> cacheList = new ArrayList<Cache>();

    for (String cacheName : cacheNames) {
      Ehcache ehcache = MANAGER.getCache(cacheName);
      URI cacheUri = null;
      if(null != uriInfo)
        cacheUri = uriInfo.getAbsolutePathBuilder().path(cacheName).build().normalize();

      Cache cache = new Cache(cacheName, (null != cacheUri)?cacheUri.toString():"", ehcache.toString(), ehcache.getCacheConfiguration());
      cacheList.add(cache);
    }

    return cacheList;
  }

}
