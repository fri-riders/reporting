package com.fri.rso.fririders.reporting.clients;

import com.fri.rso.fririders.reporting.data.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("rsousers")
public interface UsersClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/users/{userId}")
    User getUser(@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/users/")
    List<User> getUsers();
}