package net.sf.ehcache.server;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import net.sf.ehcache.server.endpoints.CacheManagerResource;
import net.sf.ehcache.server.endpoints.CacheResource;
import net.sf.ehcache.server.endpoints.ElementResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by fabien.sanglier on 4/25/17.
 */
@Component
public class JerseyConfig extends ResourceConfig {

  @Value("${spring.jersey.application-path:/}")
  private String apiPath;

  @PostConstruct
  public void init() {
    configureResources();
    configureSwagger();
  }

  private void configureResources() {
    //Configure Endpoints
    register(CacheManagerResource.class);
    register(CacheResource.class);
    register(ElementResource.class);
  }

  // Available at localhost:port/swagger.json
  private void configureSwagger() {
    register(ApiListingResource.class);
    register(SwaggerSerializers.class);

    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setTitle("Ehcache REST Server");
    beanConfig.setVersion("v1.0.0");
    beanConfig.setSchemes(new String[]{"http", "https"});
    beanConfig.setBasePath(apiPath);
    beanConfig.setResourcePackage("net.sf.ehcache.server.endpoints");
    beanConfig.setPrettyPrint(true);
    beanConfig.setScan(true);
  }
}
