package org.ehcache.server.rest.endpoints;

import net.sf.ehcache.CacheManager;
import org.ehcache.server.rest.pojo.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


/**
 * A resource for a Cache.
 * <p>
 * A Cache Resource permites the following operations:
 * <p>
 * <code>OPTIONS ehcache/{cache}</code>
 * <p>
 * Lists the methods supported by the Cache resource
 * <p>
 * <code>GET ehcache/{cache}</code>
 * <p>
 * Lists the elements in the cache.
 * <p>
 * <code>PUT ehcache/{cache}</code>
 * <p>
 * Creates a Cache using the defaultCache configuration.
 * <p>
 * <code>DELETE ehcache/{cache}</code>
 * <p>
 * Deletes the Cache.
 *
 * @author Greg Luck
 * @author Gregoire Autric (remove jersey API for full JAX-RS API)
 * @author Fabien Sanglier
 */

@Produces("application/json")
public class CacheResource {

  private static final Logger LOG = LoggerFactory.getLogger(CacheResource.class);

  private static final CacheManager MANAGER;

  static {
    MANAGER = CacheManager.getInstance();
  }


  /**
   * The full URI of the resource
   */
  @Context
  private UriInfo uriInfo;

  /**
   * The request
   */
  @Context
  private Request request;

  /**
   * The name of the cache
   */
  private String cache;

  /**
   * Full constructor
   *
   * @param uriInfo
   * @param request
   * @param cache
   */
  CacheResource(UriInfo uriInfo, Request request, String cache) {
    this.uriInfo = uriInfo;
    this.request = request;
    this.cache = cache;
  }


  /**
   * HEAD method implementation
   *
   * @return
   */
  @HEAD
  public Response getCacheHeader() {
    LOG.debug("HEAD Cache {}" + cache);

    net.sf.ehcache.Cache ehcache = MANAGER.getCache(this.cache);
    if (ehcache == null) {
      //404
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return Response.ok().build();
  }

  /**
   * GET method implementation
   *
   * @return
   */
  @GET
  public Cache getCache() {
    LOG.debug("GET Cache {}" + cache);

    net.sf.ehcache.Cache ehcache = MANAGER.getCache(cache);
    if (ehcache == null) {
      //404
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    //The REST API has extra information encoded in the String representation.
    String cacheAsString = ehcache.toString();
    cacheAsString = cacheAsString.substring(0, cacheAsString.length() - 1);
    cacheAsString = cacheAsString + "size = " + ehcache.getSize() + " ]";

    return new Cache(ehcache.getName(), uriInfo.getAbsolutePath().toString(), cacheAsString, ehcache.getCacheConfiguration());
  }

  /**
   * PUT method implementation
   *
   * @return
   */
  @PUT
  public Response putCache() {
    LOG.debug("PUT Cache {}" + cache);

    Response response;

    net.sf.ehcache.Cache ehcache = MANAGER.getCache(cache);
    if (ehcache == null) {
      CacheManager.getInstance().addCache(cache);
      URI uri = uriInfo.getAbsolutePath();
      response = Response.created(uri).build();
      LOG.debug("Created Cache {}" + cache);
    } else {
      //409
      throw new WebApplicationException(Response.Status.CONFLICT);
    }
    return response;
  }

  /**
   * DELETE method implementation
   *
   * @return
   */
  @DELETE
  public Response deleteCache() {
    LOG.debug("DELETE Cache {}" + cache);
    net.sf.ehcache.Cache ehcache = MANAGER.getCache(cache);
    Response response;
    if (ehcache == null) {
      //404
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    } else {
      CacheManager.getInstance().removeCache(cache);
      response = Response.ok().build();
    }
    return response;
  }


  /**
   * Routes the request to an {@link ElementResource}
   * <p>
   * Any extra / are still treated as an element key i.e. limited = false as their is nothing
   * else after element in the URI template.
   *
   * @param element
   * @return
   */
  @Path(value = "{element}")
  public ElementResource getElementResource(@PathParam("element") String element) {
    return new ElementResource(uriInfo, request, cache, element);
  }

}
