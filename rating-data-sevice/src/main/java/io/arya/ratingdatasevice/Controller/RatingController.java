package io.arya.ratingdatasevice.Controller;

import io.arya.ratingdatasevice.Model.Rating;
import io.arya.ratingdatasevice.Model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating("123",4);
    }

@RequestMapping("user/{userID}")
    public UserRating getUserRating(@PathVariable("userID") String userId){

//        List<Rating> ratings = Arrays.asList(
//                new Rating("123",4),
//                new Rating("567",5)
//        );

        UserRating userRating = new UserRating();

        userRating.setUserRating(Arrays.asList(
                new Rating("343",4),
                new Rating("876",2)
        ));

        return userRating;
    }

}
