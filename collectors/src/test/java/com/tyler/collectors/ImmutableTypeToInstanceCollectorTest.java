package com.tyler.collectors;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.reflect.ImmutableTypeToInstanceMap;
import com.google.common.reflect.TypeToken;

public class ImmutableTypeToInstanceCollectorTest {

	@Test
	public void test_1(){
		final List<Number> numbers = Lists.newArrayList(new Integer(0),	new Double(1.1), new Long(2));
		
		ImmutableTypeToInstanceMap<Number> map = numbers.stream().collect( GuavaCollectors.immutableTypeToInstanceMap()	);
		
		assertEquals( new Integer(0), map.getInstance( new TypeToken<Integer>(){}));
		assertEquals( new Integer(0), map.getInstance( Integer.class ));
		
		assertEquals( new Double(1.1), map.getInstance( new TypeToken<Double>(){}));
		assertEquals( new Double(1.1), map.getInstance( Double.class ));
		
		assertEquals( new Long(2), map.getInstance( new TypeToken<Long>(){}));
		assertEquals( new Long(2), map.getInstance( Long.class ));
		
		assertNull( map.getInstance( new TypeToken<Number>(){}));
		assertNull( map.getInstance( Number.class ));		
		
	}	
}
