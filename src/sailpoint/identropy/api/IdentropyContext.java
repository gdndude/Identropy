package sailpoint.identropy.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import sailpoint.api.SailPointContext;
import sailpoint.api.SailPointFactory;
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

		try {
			//_custom.setName(object.getName());
			//_custom.put("_attributes", object.getAll());
			//_custom.put("_id", object.getId());
			//_custom.put("_type", "IdentropyObject");
			_custom.setAttributes(object.)
			_custom = (Custom)object;
			context.saveObject(_custom);
			context.commitTransaction();
		} catch (GeneralException e) {
			log.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Object load(Custom custom)  {
		IdentropyObject object;
		try {
			object = new IdentropyObject();
			object.addAll((Map<String, Object>) custom.get("_attributes"));
			object.setID(custom.getString("_id"));
			return (Object)object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return null;
	}

	@Override
	public Object getObjectByName(String name)  {

		try {
			return load(context.getObjectByName(Custom.class, name));
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return null;
	}

	public Object getObjectByUUID(String id) {
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
	public List<Object> getObjects() {
        QueryOptions options = new QueryOptions();
        
        options.addFilter(Filter.eq("_type", "IdentropyObject"));
        Iterator iterator = null;
        List<Object> objects = new ArrayList<Object>();
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


}
