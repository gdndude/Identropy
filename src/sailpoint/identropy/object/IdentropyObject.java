package sailpoint.identropy.object;


import java.util.Map;
import java.util.UUID;


import sailpoint.object.Attributes;
import sailpoint.object.Custom;


public class IdentropyObject {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3993477927680121316L;
	private String _name = null;
	private String _id = null;
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

	public Attributes<String,Object> getAttributes()
	{
		return _attributes;
	}
	public void addObject(String key, Object object)
	{
		_attributes.put(key, object);
	}
	
	public void addAll(Map<String,Object> map)
	{
		_attributes.putAll(map);
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
	}
	
	private void setUUID() throws Exception
	{
		if (_id == null){	
			_id = UUID.randomUUID().toString();
		}
		else throw new Exception("UUID already exists");
	}
	
	public void setID(String id)
	{
		_id=id;
	}

}
