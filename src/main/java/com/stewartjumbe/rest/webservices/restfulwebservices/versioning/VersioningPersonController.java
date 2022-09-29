package com.stewartjumbe.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	//Versioning is useful when you need to implement a breaking change .e.g splitting name to firstName LastName
	// https://www.mnot.net/blog/2011/10/25/web_api_versioning_smackdown
	/** 
	 * http://urthen.github.io/2013/05/09/ways-to-version-your-api/
	 * http://stackoverflow.com/questions/389169/best-practices-for-api-versioning
	 * http://www.lexicalscope.com/blog/2012/03/12/how-are-rest-apis-versioned/
	 * https://www.3scale.net/2016/06/api-versioning-methods-a-brief-reference/
	 */
		
		
	
	@GetMapping("/v1/person")
	public PersonV1 getPersonNameV1() {
		return new PersonV1("Bob Jones");
	}
	
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonNameV2() {
		return new PersonV2(new Name("Eddy","Long"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
