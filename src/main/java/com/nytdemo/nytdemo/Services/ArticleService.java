package com.nytdemo.nytdemo.Services;

import com.nytdemo.nytdemo.Models.Article;
import com.nytdemo.nytdemo.Models.Media;
import com.nytdemo.nytdemo.Models.NytResponse;

import com.nytdemo.nytdemo.Models.Thumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Value("${api_key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Autowired
    RestTemplate restTemplate;


    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(mostPopularUrl + "api-key=" + apikey, NytResponse.class);
        List<Article> results = new ArrayList<>();

        if (response != null && response.getStatus().equals("OK")) {
            results = response.getResults(); // Assign the response results to the "results" list

            for (Article article : results) {
                List<Media> mediaList = article.getMedia();
                if (mediaList != null && !mediaList.isEmpty()) {
                    Media media = mediaList.get(0);
                    List<Thumbnail> thumbnailList = media.getMediaMetadata();
                    if (thumbnailList != null && !thumbnailList.isEmpty()) {
                        Thumbnail thumbnail = thumbnailList.get(0);
                        String imageUrl = thumbnail.getUrl();
                        article.setImageUrl(imageUrl);
                    }
                }
            }
        }

        return results;
    }

}