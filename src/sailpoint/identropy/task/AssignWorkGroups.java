package sailpoint.identropy.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import sailpoint.api.ObjectUtil;
import sailpoint.api.SailPointContext;
import sailpoint.object.Attributes;
import sailpoint.object.Custom;
import sailpoint.object.Identity;
import sailpoint.object.QueryOptions;
import sailpoint.object.TaskResult;
import sailpoint.object.TaskSchedule;
import sailpoint.task.AbstractTaskExecutor;

public class AssignWorkGroups extends AbstractTaskExecutor{


	static Logger log = Logger.getLogger(AssignWorkGroups.class);
	private Boolean stop = Boolean.FALSE;

	@Override
	public void execute(SailPointContext context, TaskSchedule arg1,
			TaskResult result, Attributes<String, Object> arg3) throws Exception {

		String customObjName = "workgroupMemberships";
		log.debug("workgroupMemberships name : " + customObjName);

		 
	    Custom customObject = context.getObjectByName(Custom.class, customObjName);
	    if (customObject == null)
	    {
	    	
	    	customObject = new Custom();
	    	customObject.setName(customObjName);
	    }
	    else
	    {
	    	log.debug ("Found existing custom object : " + customObject.toString());
	    }

		Attributes<String,Object> workgroupMembers = new Attributes<String, Object>();

		QueryOptions queryWGOptions = new QueryOptions();
		queryWGOptions.addFilter(sailpoint.object.Filter.eq("workgroup", Boolean.TRUE));

		List<Identity> workGroupsIIQList = context.getObjects(Identity.class, queryWGOptions);
		

		for (Identity workGroupIIQ : workGroupsIIQList)
		{
			String wrkgrpName = workGroupIIQ.getName();
			List<String> members = new ArrayList<String>();
			workgroupMembers.put(wrkgrpName, members);
			log.debug ("Adding workgroup to attributes map with zarro members : " + wrkgrpName);
			context.decache(workGroupIIQ);

		}
		workGroupsIIQList.clear();
		
		QueryOptions queryIDOptions = new QueryOptions();
		queryIDOptions.addFilter(sailpoint.object.Filter.eq("workgroup", Boolean.FALSE));
		List<Identity> identities = context.getObjects(Identity.class, queryIDOptions);
		
		for (Identity identity : identities)
		{
			List<Identity> workgroups = identity.getWorkgroups();
			log.debug ("Working on Identity : " + identity.getName() + " With workgroups : " + workgroups.toString());
			for (Identity workgroup : workgroups)
			{
		
				log.debug("Fetching members for workgroup : "+ workgroup.getName());
				@SuppressWarnings("unchecked")
				List<String> members = (List<String>) workgroupMembers.get(workgroup.getName());
				log.debug("Members Object : " + members.toString());
				members.add(identity.getName());
				workgroupMembers.put(workgroup.getName(), members);
				log.debug("Adding workgroup : " + workgroup.getName() + " With " + members.size() + " members");
			}
			context.decache(identity);
			
			//Holy crap we screwed the data.
			stop = true;
			
		}
		
		customObject.setAttributes(workgroupMembers);
		
		//get workgroup names
		
		List<String> workGroupNames = customObject.getAttributes().getKeys();
		
		List<String> memberLists = customObject.getAttributes().getStringList("Test Workgroup");
		
		context.saveObject(customObject);
		context.commitTransaction();
	}

	public boolean terminate() {
		// TODO Auto-generated method stub
		return stop;
	}


	
}
