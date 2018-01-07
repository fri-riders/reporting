package com.fri.rso.fririders.reporting.clients;

import com.fri.rso.fririders.reporting.data.Accommodation;
import com.fri.rso.fririders.reporting.data.Booking;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "accommodations")
public interface AccommodationsClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/accommodations")
    List<Accommodation> getAll();
    @RequestMapping(value = "/v1/accommodations/{id}/bookings", method = RequestMethod.GET)
    List<Booking> getForBooking(@PathVariable(value = "id") Long id);
    @RequestMapping(value = "/v1/accommodations/{id}", method = RequestMethod.GET)
    Accommodation getForId(@PathVariable(value = "id") Long id);
}