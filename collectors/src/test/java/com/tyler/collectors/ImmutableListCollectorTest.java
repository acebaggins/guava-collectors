package com.tyler.collectors;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Lists;

public class ImmutableListCollectorTest extends ImmutableCollectionTest {

	@SuppressWarnings("unchecked")
	@Override
	public <T, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
		return (Collector<T, ?, E>) GuavaCollectors.immutableList();
//		return (Collector<T, ?, E>) new ImmutableListCollector<T>();		
	}

	@Override
	public <T> Supplier<Collection<T>> getComparisonCollection() {
		return () -> Lists.<T>newArrayList();
	}
}
