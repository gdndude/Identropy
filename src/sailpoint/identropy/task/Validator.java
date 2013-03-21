package sailpoint.identropy.task;

import java.util.List;

import sailpoint.api.*;
import sailpoint.object.Application;
import sailpoint.object.Attributes;
import sailpoint.object.Custom;
import sailpoint.object.SailPointObject;
import sailpoint.object.TaskResult;
import sailpoint.object.TaskSchedule;
import sailpoint.task.*;
import sailpoint.tools.GeneralException;

public class Validator extends AbstractTaskExecutor {

	SailPointContext ctx;
	Custom custom;
	Boolean rebase = false;
	@Override
	public void execute(SailPointContext ctx, TaskSchedule arg1,
			TaskResult arg2, Attributes<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		this.ctx=ctx;
	}

	@Override
	public boolean terminate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void checkSum(Object obj)
	{
		
	}
	
	public void rebaseApp(Application application)
	{
		String appName = application.getName();
	}
	public void misMatch(SailPointObject spObject, String name)
	{
		
	}
	@SuppressWarnings("unchecked")
	public void checkApplications() {
		try {
			List<Application> apps = ctx.getObjects(Application.class);
			List<String> appNames = custom.getAttributes().getStringList("applications");
			for (String appName:appNames)
			{
				Application app = ctx.getObjectByName(Application.class, appName);
				if (app == null)
				{
					//do something cause this doesn't exist
				}
				else
				{
					//retrieve the hashcode of the serialized object
					int hashCode = ((Attributes<String,Object>)(custom.getAttributes().get(appName))).getInt("hashCode");
					// TODO add date modification validation to show it hasn't been modified
					int appHashCode = app.toXml().hashCode();
					if (appHashCode != hashCode)
					{
						//The application has changed
						if (rebase)
						{
							rebaseApp(app);
						}
					}
					
				}
			}
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
