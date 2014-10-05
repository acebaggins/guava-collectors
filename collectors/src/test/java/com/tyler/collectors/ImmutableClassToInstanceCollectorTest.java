package com.tyler.collectors;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.Lists;

public class ImmutableClassToInstanceCollectorTest {

	@Test
	public void test_1(){
		List<Number> numbers = Lists.newArrayList(
				new Integer(0),
				new Double(1.1),
				new Long(2));
		
		ImmutableClassToInstanceMap<Number> map = numbers.stream().collect( GuavaCollectors.immutableClassToInstanceMap()	);
		System.out.println( map.get( Integer.class ));
		
		System.out.println( map.get( Double.class ));
		System.out.println( map.get( Long.class ));
		
	}
	
}
