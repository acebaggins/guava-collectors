package com.tyler.collectors;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultiset;
import com.google.common.primitives.Ints;

public class GuavaCollectorsTest {

	@Test
	public void testImmutableList_1() {
		List<String> expected = Lists.newArrayList( TestHelper.LINES );
		List<String> actual = TestHelper.LINES.stream().collect( GuavaCollectors.asImmutableList() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableList_2() {
		List<String> expected = Lists.newArrayList( TestHelper.LINES );
		List<String> actual = TestHelper.LINES.parallelStream().collect( GuavaCollectors.asImmutableList() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableSet_1() {
		Set<String> expected = Sets.newHashSet( TestHelper.LINES );
		Set<String> actual = TestHelper.LINES.stream().collect( GuavaCollectors.asImmutableSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableSet_2() {
		Set<String> expected = Sets.newHashSet( TestHelper.LINES );
		Set<String> actual = TestHelper.LINES.parallelStream().collect( GuavaCollectors.asImmutableSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testMultiSet_1() {
		final Multiset<String> expected = HashMultiset.create();
		expected.addAll( TestHelper.STRINGS );
		
		final Multiset<String> actual = TestHelper.STRINGS.stream().collect( GuavaCollectors.asMultiSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testMultiSet_2() {
		final Multiset<String> expected = HashMultiset.create();
		expected.addAll( TestHelper.STRINGS );
		
		final Multiset<String> actual = TestHelper.STRINGS.parallelStream().collect( GuavaCollectors.asMultiSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testMultiSet_3() {
		final Multiset<String> expected = TreeMultiset.create();
		expected.addAll( TestHelper.STRINGS );
			
		final Multiset<String> actual = TestHelper.STRINGS.parallelStream().collect( GuavaCollectors.asMultiSet( TreeMultiset::create ) );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableMultiSet_1() {
		final Multiset<String> expected = HashMultiset.create();
		expected.addAll( TestHelper.STRINGS );
		
		final Multiset<String> actual = TestHelper.STRINGS.stream().collect( GuavaCollectors.asImmutableMultiSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableMultiSet_2() {
		final Multiset<String> expected = HashMultiset.create();
		expected.addAll( TestHelper.STRINGS );
		
		final Multiset<String> actual = TestHelper.STRINGS.parallelStream().collect( GuavaCollectors.asImmutableMultiSet() );
		
		assertEquals( expected, actual );
	}

	@Test
	public void testImmutableSortedSetReversed_1() {
		final ImmutableSortedSet<String> expected = ImmutableSortedSet.<String>reverseOrder().addAll( TestHelper.LINES ).build();
	
		final ImmutableSortedSet<String> actual = TestHelper.LINES.stream().collect( GuavaCollectors.asImmutableSortedSet() );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableSortedSetReversed_2() {
		final ImmutableSortedSet<String> expected = ImmutableSortedSet.<String>reverseOrder().addAll( TestHelper.LINES ).build();
		
		final ImmutableSortedSet<String> actual = TestHelper.LINES.parallelStream().collect( GuavaCollectors.asImmutableSortedSet() );
		
		assertEquals( expected, actual );
	}
		
	@Test
	public void testImmutableSortedSetSupplierOfBuilderOfT_1() {
		final Comparator<String> comparator = (one, two) -> Ints.compare( one.hashCode(), two.hashCode() );
		
		final ImmutableSortedSet<String> expected = ImmutableSortedSet.orderedBy( comparator ).addAll( TestHelper.LINES ).build();
		
		final ImmutableSortedSet<String> actual = TestHelper.LINES.stream().collect( GuavaCollectors.asImmutableSortedSet( comparator ) );
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableSortedSetSupplierOfBuilderOfT_2() {
		final Comparator<String> comparator = (one, two) -> Ints.compare( one.hashCode(), two.hashCode() );
		
		final ImmutableSortedSet<String> expected = ImmutableSortedSet.orderedBy( comparator ).addAll( TestHelper.LINES ).build();
		
		final ImmutableSortedSet<String> actual = TestHelper.LINES.parallelStream().collect( GuavaCollectors.asImmutableSortedSet( comparator ) );
		
		assertEquals( expected, actual );
	}

	@Test
	public void testImmutableMap_1() {
		final ImmutableMap<Integer,String> expected = ImmutableMap.<Integer,String>builder().putAll( TestHelper.MAP ).build();
		
		final ImmutableMap<Integer, String> actual = TestHelper.MAP.entrySet().stream().collect( GuavaCollectors.asImmutableMap( Entry::getKey, Entry::getValue));
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableMap_2() {
		final ImmutableMap<Integer,String> expected = ImmutableMap.<Integer,String>builder().putAll( TestHelper.MAP ).build();
		
		final ImmutableMap<Integer, String> actual = TestHelper.MAP.entrySet().parallelStream().collect( GuavaCollectors.asImmutableMap( Entry::getKey, Entry::getValue));
		
		assertEquals( expected, actual );
	}

	@Test
	public void testImmutableBiMap_1() {
		final ImmutableBiMap<Integer,String> expected = ImmutableBiMap.<Integer,String>builder().putAll( TestHelper.MAP ).build();
		
		final ImmutableBiMap<Integer, String> actual = TestHelper.MAP.entrySet().stream().collect( GuavaCollectors.asImmutableBiMap( Entry::getKey, Entry::getValue));
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableBiMap_2() {
		final ImmutableBiMap<Integer,String> expected = ImmutableBiMap.<Integer,String>builder().putAll( TestHelper.MAP ).build();
		
		final ImmutableBiMap<Integer, String> actual = TestHelper.MAP.entrySet().parallelStream().collect( GuavaCollectors.asImmutableBiMap( Entry::getKey, Entry::getValue));
		
		assertEquals( expected, actual );
	}

	@Test
	public void testImmutableTable_1() {
		final ImmutableTable<Integer, String, String> expected = ImmutableTable.<Integer,String,String>builder().putAll(TestHelper.TABLE).build();
		
		final ImmutableTable<Integer, String, String> actual = TestHelper.MAP.entrySet().stream().collect( GuavaCollectors.asImmutableTable( 
				Entry::getKey, 
				entry -> entry.getKey() + "a",
				Entry::getValue));
		
		assertEquals( expected, actual );
	}
	
	@Test
	public void testImmutableTable_2() {
		final ImmutableTable<Integer, String, String> expected = ImmutableTable.<Integer,String,String>builder().putAll(TestHelper.TABLE).build();
		
		final ImmutableTable<Integer, String, String> actual = TestHelper.MAP.entrySet().parallelStream().collect( GuavaCollectors.asImmutableTable( 
				Entry::getKey, 
				entry -> entry.getKey() + "a",
				Entry::getValue));
		
		assertEquals( expected, actual );
	}

	@Test
	public void testMultimap() {
		final Supplier<Multimap<Integer,String>> supplier = () -> MultimapBuilder.hashKeys().linkedListValues().build();
		
		final Multimap<Integer, String> expected = TestHelper.buildMultimap( supplier );		
		final Multimap<Integer, String> actual = TestHelper.MAP.entrySet().stream().collect( GuavaCollectors.asMultimap( supplier, entry -> entry.getKey() % 1500, Entry::getValue));
		
		assertEquals( expected, actual );
	}

	@Test
	public void testIterableMultimap() {
		final Supplier<Multimap<Integer,String>> supplier = () -> MultimapBuilder.hashKeys().linkedListValues().build();
		
		final Multimap<Integer, String> expected = TestHelper.buildMultimap( supplier );		
		final Multimap<Integer, String> actual = expected.asMap().entrySet().stream().collect( GuavaCollectors.asMultimapFromIterable( supplier, entry -> entry.getKey() % 1500, Entry::getValue));
		
		assertEquals( expected, actual );
	}

}
