package io.microservices.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservices.moviecatalogservice.model.CatalogItem;
import io.microservices.moviecatalogservice.model.Movie;
import io.microservices.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)
			);
		
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(),"Test",rating.getRating());
			}).collect(Collectors.toList());
		//return collect;
		/*return Collections.singletonList(
				new CatalogItem("Transformers","Test",4)
		);*/
	}
}
