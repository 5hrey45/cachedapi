package orbitor.bionic.cachedapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CachedapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachedapiApplication.class, args);
	}

}
