package net.sf.ehcache.server.pojo;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * @author Greg Luck
 */
public class Cache {
  private String name;

  private String uri;

  private String description;

  /**
   * Empty Constructor
   */
  public Cache() {
  }

  /**
   * Constructs a Cache Representation from a core Ehcache
   *
   * @param ehcache
   */
  public Cache(Ehcache ehcache) {
    this.name = ehcache.getName();
    this.uri = "rest/" + name;
    this.description = ehcache.toString();

  }

  /**
   * Full Constructor
   */
  public Cache(String name, String uri, String description, CacheConfiguration cacheConfiguration) {
    setName(name);
    setUri(uri);
    setDescription(description);

    //set some configurations if needed here
    if (null != cacheConfiguration) {

    }
  }


  /**
   * @return The cache name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the cache name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return The full URI of the resource
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri the full URI of the resource
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * Gets the description of the cache.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the cache.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Clones the resource using a deep copy.
   */
  public Cache clone() throws CloneNotSupportedException {
    Cache cache = (Cache) super.clone();
    cache.setName(name);
    cache.setUri(uri);
    return cache;
  }

}