package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataBulid {
	
	public AddPlace addPlacePayLoad(String name,String language,String address)
	{
		AddPlace p=new AddPlace();
	     p.setAccuracy(50); 
	     p.setAddress(address);
	     p.setLanguage(language);
	     p.setPhone_number("(+91) 983 893 3937");
	     p.setWebsite("https://rahulshettyacademy.com");
	     p.setName(name);
	     
	     
	     List<String> myList=new ArrayList<String>();//Setting  vlaues for Json with array
	     myList.add("shoe park");
	     myList.add("shop");
	     p.setTypes(myList);
	     
	     Location l=new Location();//Setting values for sub-Json
	     l.setLat(-38.383494);
	     l.setLng(33.427362);
	     p.setLocation(l);
	     return p;
	}

}
