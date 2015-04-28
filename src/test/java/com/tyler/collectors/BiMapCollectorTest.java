package com.tyler.collectors;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BiMapCollectorTest {
	private static final Function<String,String> KEY_FUNCTION = string -> string + "Key";
	private static final Function<String,String> VALUE_FUNCTION = string -> string + "Value";
	
	
	@Test
	public void testEmpty(){
		final List<String> list = Lists.newArrayList();
		
		final BiMap<String, String> expected = HashBiMap.create();
		final BiMap<String, String> actual = list.stream().collect( GuavaCollectors.toBiMap(KEY_FUNCTION, VALUE_FUNCTION ));
	
		assertEquals( expected, actual );
	}

	@Test
	public void testNotEmpty(){
		final List<String> list = Lists.newArrayList("one","two");
		final BiMap<String, String> expected = HashBiMap.create();
		expected.put("oneKey", "oneValue");
		expected.put("twoKey", "twoValue");		
		
		final BiMap<String, String> actual = list.stream().collect( GuavaCollectors.toBiMap( KEY_FUNCTION, VALUE_FUNCTION ));
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testSupplier(){
		final List<String> list = Lists.newArrayList("one","two");
		final BiMap<String, String> expected = list.stream().collect( GuavaCollectors.toBiMap(KEY_FUNCTION, VALUE_FUNCTION));
		final BiMap<String, String> actual = list.stream().collect( GuavaCollectors.toBiMap(() -> HashBiMap.create(), KEY_FUNCTION, VALUE_FUNCTION));
	
		assertEquals( expected, actual );
		assertEquals( HashBiMap.class, actual.getClass());		
	}
	
	@Test
	public void testParallel(){
		final Set<String> list = Sets.newHashSet(TestHelper.LINES);
		final BiMap<String, String> expected = list.stream().collect( GuavaCollectors.toBiMap(KEY_FUNCTION, VALUE_FUNCTION));
		final BiMap<String, String> actual = list.parallelStream().collect( GuavaCollectors.toBiMap(() -> HashBiMap.create(), KEY_FUNCTION, VALUE_FUNCTION));
	
		assertEquals( expected, actual );
		assertEquals( HashBiMap.class, actual.getClass());	
	}
}
