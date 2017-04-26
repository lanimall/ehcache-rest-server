# ehcache-rest-server

An implementation that makes Ehcache Operations accessible via REST/JSON
Note: Currently adapted/modernized from former ehcache-server version -- http://www.ehcache.org/documentation/2.7/modules/cache-server.html.

Leverages Spring-Boot, JAX-RS (Jersey2), SwaggerUI and provides Docker capability for ease of testing.

(work in progress still...)

## Running ehcache-rest-server locally
```
	git clone https://github.com/lanimall/ehcache-rest-server.git
	cd ehcache-rest-server
	mvn spring-boot:run
```

You can then access ehcache-rest-server swagger UI here: 
http://localhost:8080/

which will allow you to explore the REST commands available and test them out.

## Running with custom caches

The app has a a couple of sample caches defined in the embedded ehcache.xml (smallExpiryCache, smallCache, largeCache, eternalCache)

But of course, you'll want to create your own caches.

To do so, simply specify the path to your own Ehcache configuration file at application startup in system property "ehcache.config.path".

For example, if you have a custom "ehcache-rest-server.xml" file in your application server user $HOME, the start commands to run the server locally would become:

```
	git clone https://github.com/lanimall/ehcache-rest-server.git
	cd ehcache-rest-server
  mvn spring-boot:run -Drun.jvmArguments="-Dehcache.config.path=$HOME/ehcache-rest-server.xml"
```

## Running ehcache-rest-server with Docker

TBD...