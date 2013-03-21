package sailpoint.identropy.object;

import java.util.List;
import java.util.Set;

import sailpoint.object.Application;

public class IdentropyConfig extends IdentropyObject  {

	IdentropyObject applications;
	List<IdentropyObject> workgroups;
	IdentropyObject systemConfig;
	
	public IdentropyConfig(String name) throws Exception {
		super(name);
        
	}


	public void addApplication(Application application)
	{
		this.addObject(application.getName(), application);
	}
	
	public void setApplications(List<Application> applications)
	{
		for (Application application:applications)
		{
			this.applications.addObject(application.getName(), application);
		}
	}
	public List<Application> getApplications()
	{
		Set<String> keys = applications.getAll().keySet();
		List<Application> applications = null;
		for (String key: keys)
		{
			applications.add((Application) this.applications.getObject(key));
		}
		return applications;
	}
}
