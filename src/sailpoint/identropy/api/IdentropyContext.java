package sailpoint.identropy.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import sailpoint.api.SailPointContext;
import sailpoint.api.SailPointFactory;
import sailpoint.identropy.object.IdentropyConfig;
import sailpoint.identropy.object.IdentropyObject;
import sailpoint.identropy.task.Tester;
import sailpoint.object.Custom;
import sailpoint.object.Filter;
import sailpoint.object.QueryOptions;
import sailpoint.tools.GeneralException;

public class IdentropyContext implements IdentropyFactory {

	static Logger log = Logger.getLogger(IdentropyContext.class);
	SailPointContext context;
	Custom _custom;
	
	public IdentropyContext() {
		initialize();
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		try {
			context = SailPointFactory.getCurrentContext();
			_custom = new Custom();
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

	@Override
	public void save(IdentropyObject object) {

		_custom = new Custom();
		try {	
			if (object.getRefId() == null)
			{
				context.saveObject(_custom);
			}
			object.setRefId(_custom.getId());
			object.getReference();
			_custom.setAttributes(object.getAttributes());
			_custom.setName(object.getName());
			context.saveObject(_custom);
			context.commitTransaction();
		} catch (GeneralException e) {
			log.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	public IdentropyObject load(Custom custom)  {
		IdentropyObject object = null;
		try {
			//object = new IdentropyObject();
			log.debug("Found custom : " + custom.toString());
			if (custom.getString("_type").contentEquals("_config"))
			{
			IdentropyConfig config = new IdentropyConfig();	
			object = config;
			}
			object.addAll(custom.getAttributes());
			object.setId(custom.getString("_id"));
			object.setName(custom.getName());
			object.setRefId(custom.getId());
			log.debug("Marshall in object : " + object.toString());
			return object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return null;
	}

	@Override
	public IdentropyObject getObjectByName(String name)  {

		try {
			
			return load(context.getObjectByName(Custom.class, name));
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return null;
	}

	public IdentropyObject getObjectByUUID(String id) {
         QueryOptions options = new QueryOptions();
         
         options.addFilter(Filter.eq("_id", id));
         options.addFilter(Filter.eq("_type", "IdentropyObject"));
         Iterator iterator = null;
		try {
			iterator = context.search(Custom.class, options);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		 if (iterator.hasNext())
		 {
			 return load((Custom) iterator.next());
		 }
		 return null;
	}

	@Override
	public List<IdentropyObject> getObjects() {
        QueryOptions options = new QueryOptions();
        
        options.addFilter(Filter.eq("_type", "IdentropyObject"));
        Iterator iterator = null;
        List<IdentropyObject> objects = new ArrayList<IdentropyObject>();
		try {
			iterator = context.search(Custom.class, options);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		 while (iterator.hasNext())
		 {
			 objects.add(load((Custom) iterator.next()));
		 }
		 return objects;
	}
	@Override
	public IdentropyObject load(IdentropyObject object) {
		// TODO Auto-generated method stub
		return null;
	}


}
