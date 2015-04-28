package com.tyler.collectors;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.MultimapBuilder.ListMultimapBuilder;


public class MultimapCollectorTest {
	
	@Test
	public void test_1(){		
//		MultimapCollector<String, Integer, String, ListMultimap<Integer, String>> collector = 
//				new MultimapCollector<String, Integer, String, ListMultimap<Integer,String>>
//		( (()->ListMultimapBuilder.linkedHashKeys().arrayListValues().build()), 
//				string -> Integer.valueOf(string.split("-")[0]), 
//				string -> string.split("-")[1]);
		
		
		
		final List<String> strings = Lists.newArrayList( "1-a", "2-b", "2-a", "3-c" );
		Multimap<Integer, String> expected = MultimapBuilder.linkedHashKeys().arrayListValues().build();
		expected.put(1, "a");
		expected.put(2, "b");
		expected.put(2, "a");
		expected.put(3, "c");
		
		final ListMultimap<Integer,String> actual = strings.stream().collect( GuavaCollectors.asMultimap(
				(()-> ListMultimapBuilder.linkedHashKeys().arrayListValues().build()), 
				string -> Integer.valueOf( string.split("-")[0]),
				string -> string.split("-")[1]));
		
		assertEquals( expected, actual);
	}
}
