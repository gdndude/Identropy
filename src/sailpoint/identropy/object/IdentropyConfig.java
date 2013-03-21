package sailpoint.identropy.object;

import java.util.List;
import java.util.Set;

import sailpoint.object.Application;

public class IdentropyConfig extends IdentropyObject  {

	IdentropyObject systemConfig;
	
	public IdentropyConfig(String name) throws Exception {
		super(name);
        
	}


	public void addApplication(Application application)
	{
		IdentropyObject object = null;
		try {
			object = new IdentropyObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		object.setID(application.getId());
		object.setName(application.getName());
		object.getAttributes().put("application", application);
		object.getReference();
		this.addObject(object);
	}
	
	public void setApplications(List<Application> applications)
	{

	}
	public List<Application> getApplications()
	{
		return null;
	}
}
