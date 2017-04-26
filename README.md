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


NOTE: With the built-in ehcache configuration, there's only 1 cache defined ("stringCache")

## Running ehcache-rest-server with Docker

TBD...