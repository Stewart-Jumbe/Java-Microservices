package com.stewartjumbe.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringExampleController {
	
	@GetMapping("/filtering") // localhost:8080/filtering
	public MappingJacksonValue filtering() {
		//Applying dynamic filtering
		UserDetails userDetails = new UserDetails("Clark", "Clark@gmail.com", "Pluto29");
		
		//allows me to add serialisation logic
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDetails);
		
		//Filtering out password
		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("name","email");
		
		FilterProvider filters = enterFilterProviders(filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList(){
		List<UserDetails>userDetailsList = Arrays.asList(new UserDetails("Ann","Ann@gmail.com","Password12"), new UserDetails("Jon","SomeEmail@gmail.com","Alright12"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDetailsList);
		
		//filtering out email
		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("name","password");
		
		FilterProvider filters = enterFilterProviders(filter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}

	private FilterProvider enterFilterProviders(SimpleBeanPropertyFilter filter) {
		FilterProvider filters = new SimpleFilterProvider().addFilter("Dynamicfilter",filter);
		return filters;
	}
	
	
}
