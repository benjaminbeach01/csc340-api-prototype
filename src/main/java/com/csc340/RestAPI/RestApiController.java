package com.csc340.RestAPI;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * CSC 340
 * code is based on the provided example: https://github.com/sunnyntini/csc340-rest-api-demo
 * @author benjamin beach
 */
@RestController
public class RestApiController {

        @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    /**
     * get a random movie from the moviehut api and make it available at this endpoint.
     *
     * @return
     */
    @GetMapping("/movie")
    public Object getMovie() {
        String url = "https://k2maan-moviehut.herokuapp.com/api/random";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonMovie = restTemplate.getForObject(url, Object.class);

        //print everything from the api into the console
        String movie = restTemplate.getForObject(url, String.class);
        //parse the most important parts and print them into the console
        JSONObject jo = new JSONObject(movie);
        System.out.println(jo.toString());
        String movieName = jo.getString("name");
        String movieDirector = jo.getString("director");
        String movieOverview = jo.getString("overview");
        System.out.println(movieName + ", " + movieDirector);
        System.out.println(movieOverview);

        return jSonMovie;
    }

}
