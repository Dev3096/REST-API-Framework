Feature: Validating place API's

@AddPlace
Scenario Outline: Verify if place is being succesfully added using addplaceAPI
	Given AddPlace payload with "<name>" "<language>" "<address>"
	When user calls "addplaceAPI" with "Post" http request
	Then the API call is successfull with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name  		| language		| address 			|
	|Dev		  | English			| Cross Center 	|  
#	|House		| French			| BB center			|
	
@DeletePlace
Scenario: Verify if Delete place functionality is working
	Given Delete place API
	When user calls "deletePlaceAPI" with "Post" http request
	Then the API call is successfull with status code 200
	And "status" in response body is "OK" 
	