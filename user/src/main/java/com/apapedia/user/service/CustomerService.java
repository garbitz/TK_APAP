package com.apapedia.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.apapedia.user.dto.response.CartResponse;
import com.apapedia.user.model.Customer;
import com.apapedia.user.repository.CustomerDb;
import com.apapedia.user.setting.Setting;

import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final WebClient webClient;

    @Autowired
    CustomerDb customerDb;

    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.CLIENT_ORDER_SERVICE).build();
    }

    public Customer connectCart(Customer customer) {
        // Make a request to create a new cart
        String url = "/cart/create-cart/" + customer.getId();
        var cartResponse = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(CartResponse.class)
                .block();

        if (cartResponse != null) {
            customer.setCartId(cartResponse.getId());
            customerDb.save(customer);
        }

        return customer;
    }
}
