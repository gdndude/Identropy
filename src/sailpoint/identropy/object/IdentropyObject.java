package sailpoint.identropy.object;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.XML;


import sailpoint.object.Attributes;
import sailpoint.object.Custom;
import sailpoint.object.SailPointObject;


public class IdentropyObject extends SailPointObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3993477927680121316L;
	private Attributes<String,Object> _reference = new Attributes<String,Object>();
	private List<Attributes<String,Object>> _references = new ArrayList<Attributes<String,Object>>();
	private Attributes<String,Object> _attributes = new Attributes<String,Object>();;
	//protected SailPointContext context;
	
	
	public IdentropyObject() throws Exception
	{
		setUUID();

	}
	public IdentropyObject(String name) throws Exception
	{
		setUUID();
		this.setName(name);
	}
	public void setRefId(String _refId)
	{
		_attributes.put("_refId", _refId);
	}


	public Attributes<String,Object> getAttributes()
	{
		return _attributes;
	}
	public void addReference(Attributes<String, Object> attributes)
	{
		_references.add(attributes);	
		_attributes.put("_references", _references);
	}
	
	public List<Attributes<String,Object>> getReferences()
	{
		return _references;
	}
	public Attributes<String,Object> getReference()
	{
		_reference.put("_type", getObject("_type"));
		_reference.put("_refId", getObject("_refId"));
		_reference.put("_name", getObject("_name"));
		_reference.put("_id", getObject("_id"));
		_attributes.put("_reference", _reference);
		return this._reference;
	}
	
	public void addAll(Map<String,Object> map)
	{
		_attributes.putAll(map);
	}
	
	public void addObject(String key, Object object)
	{
		_attributes.put(key,object);
	}
	
	
	public Object getObject(String key)
	{
		return _attributes.get(key);
	}
	
	public Map<String,Object> getAll()
	{
		return _attributes;
	}
	public String getRefId()
	{	
		return (String)getObject("_refId");
	}
	
	public String getName()
	{
		return (String)getObject("_name");
	}
	
	public String getId()
	{
		return (String)getObject("_id");
	}
	
	public void setName(String name)
	{
		_attributes.put("_name", name);
	}
	public void setType(String type)
	{
		_attributes.put("_type", type);
	}
	public String getType()
	{
		return (String)getObject("_type");
	}
	
	private void setUUID() throws Exception
	{
		String _id = null;
		if (getObject("_id") == null){	
			_id = UUID.randomUUID().toString();
		}
		else throw new Exception("UUID already exists");
		_attributes.put("_id", _id);
	}
	
	public void setID(String id)
	{
		_attributes.put("_id", id);
	}

}
