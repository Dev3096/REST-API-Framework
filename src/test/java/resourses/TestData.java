package resourses;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestData {
	
	public AddPlace addPlacePayLoad(String name, String language, String address) {
		 
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("23432423233");
		p.setWebsite("google.com");
		p.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("Shopark");
		myList.add("abcd");
		p.setTypes(myList);
		location l = new location();
		l.setLat(-30.56);
		l.setLng(86.54);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlace(String placeId) {
		
		return "{\r\n"
				+ "		    \"place_id\":\""+placeId+"\"\r\n"
				+ "		}";
		
	}

}
