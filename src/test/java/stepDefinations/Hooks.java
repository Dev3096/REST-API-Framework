package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// write a code that will give place id 
		// execute this code only if place id is null
		
		StepDefinition m = new StepDefinition();
		if (StepDefinition.place_id == null) {
			m.add_place_payload_with("Roh", "Spanish", "USA");
			m.user_calls_with_post_http_request("addplaceAPI", "Post");
			m.verify_place_id_created_maps_to_using("Roh", "getPlaceAPI");
		}
		
	}

}
