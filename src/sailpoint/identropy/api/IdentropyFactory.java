package sailpoint.identropy.api;

import java.util.List;
import java.util.UUID;

import sailpoint.identropy.object.IdentropyObject;
import sailpoint.object.Custom;



public interface IdentropyFactory {

	
	public void initialize();
	
	public Object load(IdentropyObject object);
	
	public Object getObjectByName(String name);
	
	public Object getObjectByUUID(String id);
	
    public List<IdentropyObject> getObjects();

	void save(IdentropyObject object);
	
}
