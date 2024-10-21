package win.those.aegiru.chirper;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class ChirperGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChirperGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
		RouteLocatorBuilder builder,
		@Value("${chirper.user.url}") String userUrl,
		@Value("${chirper.post.url}") String postUrl,
		@Value("${chirper.gateway.host}") String host
	) {
		return builder
				.routes()
				.route("users", route -> route
						.host(host)
						.and()
						.path(
								"/api/users",
								"/api/users/{uuid}",
								"/api/users/{uuid}/profilePicture"
						)
						.uri(userUrl)
				)
				.route("posts", route -> route
						.host(host)
						.and()
						.path(
								"/api/posts",
								"/api/posts/**",
								"/api/users/{uuid}/posts"
						)
						.uri(postUrl)
				)
				.build();
	}

}
