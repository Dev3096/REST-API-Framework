package resourses;

// enum is special class in java which has collections of constants or methods
public enum APIResourses {
	
	
	addplaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resourse;
	
	APIResourses(String resourse)
	
	{
		this.resourse = resourse;
	}
	
	public String getResourse() {
		return resourse;
	}
	

}
