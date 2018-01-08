package com.equals;

import java.util.HashMap;
import java.util.Map;

public class Check {
	public static void main(String[] args) {
		Map<Emp, String>  map= new HashMap<Emp, String>();
		map.put(new Emp(1,"ABC"),"ABD" );
		map.put(new Emp(1,"ABC"),"ABD" );
		map.put(new Emp(1,"AB;;"),"ABD" );
		map.put(new Emp(1,"ABC"),"ABD" );
		System.out.println(map.size());

	}

}
class Emp{
	int id;
	String name;
	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
