package sailpoint.identropy.object;

import java.util.List;
import java.util.Set;

import sailpoint.object.Application;

public class IdentropyConfig extends IdentropyObject  {

	IdentropyObject systemConfig;
	
	public IdentropyConfig(String name) throws Exception {
		super(name);
		setType("_config");
        
	}


	public IdentropyConfig() throws Exception {
		setType("_config");
	}


	public void addApplication(IdentropyApplication application)
	{
		this.addReference(application.getReference());
	}
	
	public void setApplications(List<Application> applications)
	{

	}
	public List<Application> getApplications()
	{
		return null;
	}
}
