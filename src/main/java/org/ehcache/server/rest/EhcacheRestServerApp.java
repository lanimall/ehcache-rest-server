/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ehcache.server.rest;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class EhcacheRestServerApp {
  @Autowired
  ResourceLoader resourceLoader;

  public static void main(String[] args) {
    SpringApplication.run(EhcacheRestServerApp.class, args);
  }

  @Bean(name = "cacheManager")
  public FactoryBean<CacheManager> cacheManager() {
    String ehcacheConfigPath = System.getProperty("ehcache.config.path", "classpath:ehcache.xml");
    EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
    bean.setConfigLocation(resourceLoader.getResource(ehcacheConfigPath));
    return bean;
  }
}
