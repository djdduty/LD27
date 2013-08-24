package engine.JWolf2D.resource;

import java.util.HashMap;
import java.util.Set;

public abstract class Assets<T> {
	private HashMap<String,T> map;
	private HashMap<String,String> paths;
	
	public Assets() {
		map = new HashMap<String,T>();
		paths = new HashMap<String,String>();
	}
	
	public abstract T extract(String path);
	
	public T add(String name, String path) {
		if(map.containsKey(name)) {
			if(!(paths.containsKey(name) && paths.containsValue(path)))
				throw new RuntimeException("EXTREMELY STRANGE BUG, FIX NOW!");
			
			return map.get(name);
		}
		
		T t = add(name, extract(path));
		if(t != null)
			paths.put(name,path);
		return t;
	}
	
	public T add(String name, T t) {
		if(t == null)
			return null;
		
		map.put(name, t);
		
		return t;
	}
	
	public T get(String name) {
		return map.get(name);
	}
	
	public String getPath(String name) {
		return paths.get(name);
	}
	
	public Set<String> getList() {
		return map.keySet();
	}
	
	public String getName(T t) {
		for(String s : map.keySet())
			if(map.get(s) == t)
				return s;
		return null;
	}
	
	public T remove(String name) {
		return map.remove(name);
	}
}
