package com.movie.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class MovieUtils {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieUtils.class);

	public static <T> List<T> union(List<T> list1, List<T> list2) {
		Set<T> set = new HashSet<T>();
		if(list1!=null && list1.size()>0)
			set.addAll(list1);

		if(list2!=null && list2.size()>0)
			set.addAll(list2);

		return new ArrayList<T>(set);
	}

	public static <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();

		for (T t : list1) {
			if(list2!=null && list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	/*	public static <K, V extends Comparable<? super V>> Map<K, V> 
	sortByValue( Map<K, V> map )
	{
		List<Map.Entry<K, V>> list =new LinkedList<Map.Entry<K, V>>(map.entrySet());
				//new ArrayList<>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{
				//return ( o1.getValue() ).compareTo( o2.getValue() );
				return ( o2.getValue() ).compareTo( o1.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}*/

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) 
	{
		@SuppressWarnings("unchecked")
		Map.Entry<K,V>[] array = map.entrySet().toArray(new Map.Entry[map.size()]);

		Arrays.sort(array, new Comparator<Map.Entry<K, V>>() 
		{
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) 
			{
				//return e2.getValue().compareTo(e.getValue()); //Ascending
				return e2.getValue().compareTo(e1.getValue());	//Desending
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : array)
			result.put(entry.getKey(), entry.getValue());

		return result;
	}

	public static void main(String[] args) {

		Map<String, Double> unsortMap = new HashMap<String, Double>();
		unsortMap.put("z", 10.0);
		unsortMap.put("b", 53.3);
		unsortMap.put("a", 63.3);
		unsortMap.put("c", 20.4);
		unsortMap.put("d", 20.45);
		unsortMap.put("e", 70.09);
		unsortMap.put("y", 8.90);
		unsortMap.put("n", 99.4);
		unsortMap.put("j", 50.44);
		unsortMap.put("m", 2.43);
		unsortMap.put("f", 9.34);

		LOGGER.info("Unsort Map......");
		printMap(unsortMap);

		LOGGER.info("\nSorted Map......By Value");
		Map<String, Double> sortedMap = sortByValue(unsortMap);
		printMap(sortedMap);

	}
	public static <K, V> void printMap(Map<K, V> map) {
		LOGGER.info("Map value.. ");
		for (Map.Entry<K, V> entry : map.entrySet()) {
			LOGGER.info("Key : " + entry.getKey()
			+ " Value : " + entry.getValue());
		}
	}

}
