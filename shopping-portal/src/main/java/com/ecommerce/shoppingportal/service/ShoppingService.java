package com.ecommerce.shoppingportal.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShoppingService {
    @Autowired
    RestTemplate restTemplate;
    /**
     * call ProductService rest API and get recommended results
     * @param cat
     * @return
     */

    @HystrixCommand(fallbackMethod = "recommendProduct_fallback")
    public String recommendProduct(String cat){
        //RestTemplate
        //localhost:8082/product/recommend/category
        String url = "http://product-service/product/recommend/" + cat;
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    public String recommendProduct_fallback(String cat) { //variable要和上面一致
        return "there is an error recommending product for category:" + cat;
    }

    //Ribbon
    public String getAccount() {
        String url = "http://account-service/accountInfo";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
