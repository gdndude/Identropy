package sailpoint.identropy.task;

import org.apache.log4j.Logger;

import sailpoint.api.SailPointContext;
import sailpoint.identropy.api.IdentropyContext;
import sailpoint.identropy.object.IdentropyApplication;
import sailpoint.identropy.object.IdentropyConfig;
import sailpoint.identropy.object.IdentropyObject;
import sailpoint.object.Application;
import sailpoint.object.Attributes;
import sailpoint.object.TaskResult;
import sailpoint.object.TaskSchedule;
import sailpoint.task.AbstractTaskExecutor;

public class Tester extends AbstractTaskExecutor {

	static Logger log = Logger.getLogger(Tester.class);
	IdentropyConfig identropyConfig = null;
	IdentropyContext identropyContext = new IdentropyContext();
	SailPointContext context;

	@Override
	public void execute(SailPointContext arg0, TaskSchedule arg1,
			TaskResult arg2, Attributes<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		//identropyContext.initialize();
		context=arg0;
		testSave();
		testLoad();
		
	}

	public void testSave()
	{
		try {
			identropyConfig = new IdentropyConfig("TestConfig");
			Application application = context.getObjectByName(Application.class, "AD");
			IdentropyApplication identropyApp = new IdentropyApplication();
			identropyApp.setApplication(application);
			identropyContext.save(identropyApp);
			identropyConfig.addApplication(identropyApp);
			log.info("Save TestApplication : " + identropyApp.toString());
		} catch (Exception e) {
			
			log.error(e);
		}
		//identropyObject.setName("TestObject");
		log.info("Save TestObject : " + identropyConfig.toString());
		identropyContext.save(identropyConfig);
	}
	
	public void testLoad()
	{
		identropyConfig= (IdentropyConfig) identropyContext.getObjectByName("TestConfig");
		log.info("Load TestObject : " + identropyConfig.toString());
		
	}
	@Override
	public boolean terminate() {
		// TODO Auto-generated method stub
		return false;
	}
}
