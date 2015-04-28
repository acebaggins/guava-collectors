package com.tyler.collectors;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.junit.Test;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ImmutableListCollectorTest extends ImmutableCollectionTest {

	@SuppressWarnings("unchecked")
	@Override
	public <T, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
		return (Collector<T, ?, E>) GuavaCollectors.toImmutableList();		
	}

	@Override
	public <T> Supplier<Collection<T>> getComparisonCollection() {
		return () -> Lists.<T>newArrayList();
	}
	
	@Test
	public void testSingleThread_1(){
		final List<String> getStrings = buildStrings();		
		final ImmutableList<String> list = getStrings.stream().collect( GuavaCollectors.toImmutableList() );
		
		assertEquals( getStrings, list );
	}
	
	@Test
	public void testConcurrent_1(){
		final List<String> getStrings = buildStrings();		
		final ImmutableList<String> list = getStrings.parallelStream().collect( GuavaCollectors.toImmutableList() );
		
		assertEquals( getStrings, list );
	}
}
