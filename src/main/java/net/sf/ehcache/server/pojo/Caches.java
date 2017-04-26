package net.sf.ehcache.server.pojo;

import java.util.List;

/**
 * A collection of Cache representations available from a CacheManager
 *
 * @author Greg Luck
 */
public class Caches {

  private List<Cache> caches;

  /**
   * Empty Constructor
   */
  public Caches() {
  }

  /**
   * Constructor using a list of cache representations
   *
   * @param caches a list of caches that belong in this CacheManager
   */
  public Caches(List<Cache> caches) {
    setCaches(caches);
  }

  /**
   * Gets the list of caches
   *
   * @return a list of Cache representations
   */
  public List<Cache> getCaches() {
    return caches;
  }

  /**
   * Sets the list of caches
   *
   * @param caches a list of cache representations
   */
  public void setCaches(List<Cache> caches) {
    this.caches = caches;
  }

}
