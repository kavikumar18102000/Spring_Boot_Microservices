package io.arya.moviecatalogservice.Controller;

import io.arya.moviecatalogservice.Model.Movie;
import io.arya.moviecatalogservice.Model.Rating;
import io.arya.moviecatalogservice.Model.UserRating;
import io.arya.moviecatalogservice.Model.catalogItem;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.lang.ref.Reference;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webclient;

    @Autowired
    private DiscoveryClient discoveryClient; //gives the service instances by passing the service ID.

    @GetMapping("/{userId}")
    public List<catalogItem> getCatalog(@PathVariable("userId") String userid){

//        RestTemplate restTemplate = new RestTemplate(); //make sure you are creating the bean globally not internally


        //Movie movie = restTemplate.getForObject("http://localhost:9092/movie/foo", Movie.class); --> standard
        // signature of using the restemplate to call the services. It might any services other than the microservices.

        //get all the movie id
//        List<Rating> ratings = Arrays.asList(
//                new Rating("123",4),
//                new Rating("567",5)
//        );

        /*
        List<Rating> listRating = restTemplate.getForObject("http://localhost:9093/rating/user/" + id,
                ParameterizedTypeReference< AddDefaultCharsetFilter.ResponseWrapper<Rating> >);

         Note : Avoid returning the list in the API, rather use objects so, it might be not a big problem in-future.
         To know how to do, view userRating in the rating-data-service

         This done as shown in the below code

       UserRating listUserRating = restTemplate.getForObject("http://localhost:9093/rating/user/" + userid,
               UserRating.class);

        return listUserRating.getUserRating().stream().map(rating -> { //iterating over a list of user ratings
            Movie movie = restTemplate.getForObject("http://localhost:9092/movie/" + rating.getMovieId(), Movie.class);
            return new catalogItem(movie.getMovieId(),"test",rating.getRating());
        }).collect(Collectors.toList());

         */

        /*
        The above code can be done through using the discovery server. Because hard coding the URL is not the way to
        call the service every time.

        Hard coding the URL is not allowed in the production, because we are going to deploy it.

        And also be standardized on the application name, as if the application name changes, then the service
        discovery won't work.

        We are providing the service name not the URL. Because Eureka server doesn't know about the URL, it can only
        take the service name. The name of the service are provided in the application.properties/yaml file.
         */

        UserRating listUserRating = restTemplate.getForObject("http://rating-data-service/rating/user/" + userid,
                UserRating.class);


        return listUserRating.getUserRating().stream().map(rating -> { //iterating over a list of user ratings.
            //for each movie id call the info service
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(),
                    Movie.class);

            //put them all together

            return new catalogItem(movie.getMovieId(),"test",rating.getRating());
        }).collect(Collectors.toList());




       //return ratings.stream().map(rating -> new catalogItem("Oppenheimer","atomic test",5)).collect(Collectors.toList());

        //the above return statement can be done using the rest template like this given below
//        return ratings.stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:9092/movie/" + rating.getMovieId(), Movie.class);
//            return new catalogItem(movie.getMovieId(),"test",rating.getRating());
//            }).collect(Collectors.toList());




        //the another way of performing the operation, we use the webclient instead of the rest template as follows.
        /*

        return ratings.stream().map(rating -> {
            Movie movie = webclient.build() //--> to perform the build operation
                    .get()  //--> type of the request we are sending
                    .uri("http://localhost:9092/movie/" + rating.getMovieId())
                    .retrieve()  //--> fetching the data
                    .bodyToMono(Movie.class) //converting the data to type Movie class
                    .block(); //waits until the operation is completed
            return new catalogItem(movie.getMovieId(),"web client test",rating.getRating());
        }).collect(Collectors.toList());

         */
    }

    @GetMapping("/home")
    public String home(){
        return "Hello I am catalog Service..";
    }
}
