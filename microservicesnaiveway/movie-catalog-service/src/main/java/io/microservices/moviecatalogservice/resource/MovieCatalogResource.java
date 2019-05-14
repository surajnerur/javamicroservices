package io.microservices.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.microservices.moviecatalogservice.model.CatalogItem;
import io.microservices.moviecatalogservice.model.Movie;
import io.microservices.moviecatalogservice.model.Rating;
import io.microservices.moviecatalogservice.model.UserRating;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		/*List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)
			);*/
		UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsData/users/"+userId, UserRating.class);
		
		return userRating.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(),"Test",rating.getRating());
			}).collect(Collectors.toList());
		
		/*return ratings.stream().map(rating -> {
			//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			Movie movie = webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/movies/"+rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();
			return new CatalogItem(movie.getName(),"Test",rating.getRating());
			}).collect(Collectors.toList());*/
		
		
		/*return ratings.stream().map(rating -> {
			//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			Mono<Movie> bodyToMono = webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/movies/"+rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class);
			return bodyToMono.as(r -> new CatalogItem(r.block().getName(),"Test",rating.getRating()));
			//return bodyToMono.doOnSuccess(r -> new CatalogItem(r.getMovieId(),"Test",rating.getRating())).as();
			}).collect(Collectors.toList());*/
		
		/*return ratings.stream().map(rating -> 
			 new CatalogItem("","Test",rating.getRating())
			).collect(Collectors.toList());*/
		//return collect;
		/*return Collections.singletonList(
				new CatalogItem("Transformers","Test",4)
		);*/
	}
}
