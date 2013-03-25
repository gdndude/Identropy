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
	private String _name = null;
	private String _id = null;
	private String _refId = null;
	private String _type = null;
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
	public void setRefId(String refId)
	{
		_refId = refId;
		_attributes.put("_refId", _refId);
		//addObject("_refId", _refId);
	}
	public String getRefId()
	{
		return _refId;
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
		_reference.put("_type", _type);
		_reference.put("_refId", _refId);
		_reference.put("_name", _name);
		_reference.put("_id", _id);
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
	
	
	public String getName()
	{
		return _name;
	}
	
	public String getId()
	{
		return _id;
	}
	
	public void setName(String name)
	{
		_name=name;
		_attributes.put("_name", _name);
	}
	public void setType(String type)
	{
		_type = type;
		_attributes.put("_type", _type);
	}
	public String getType()
	{
		return _type;
	}
	
	private void setUUID() throws Exception
	{
		if (_id == null){	
			_id = UUID.randomUUID().toString();
		}
		else throw new Exception("UUID already exists");
		_attributes.put("_id", _id);
	}
	
	public void setID(String id)
	{
		_id=id;
		_attributes.put("_id", _id);
	}

}
