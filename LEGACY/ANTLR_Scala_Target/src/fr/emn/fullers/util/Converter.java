package fr.emn.fullers.util;

import java.util.*;

public class Converter {

	public static List removeType(List<Object> somList){
		List newList = new ArrayList();
		for (Object elem : somList){
			newList.add(elem);
		}
		
		return newList;
	}
}
