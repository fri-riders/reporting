package com.fri.rso.fririders.reporting.clients;

import com.fri.rso.fririders.reporting.data.Accommodation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "accommodations")
public interface AccommodationsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/accommodations")
    List<Accommodation> getAll();
}