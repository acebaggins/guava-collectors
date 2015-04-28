package com.tyler.collectors;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Sets;

public class ImmutableSetCollectorTest extends ImmutableCollectionTest {

	@SuppressWarnings("unchecked")
	@Override
	public <T, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
		return (Collector<T, ?, E>) GuavaCollectors.asImmutableSet();
	}

	@Override
	public <T> Supplier<Collection<T>> getComparisonCollection() {
		 return () -> Sets.newHashSet();
	}
}
