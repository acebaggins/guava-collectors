package com.tyler.collectors;

import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.primitives.Ints;

public class ImmutableSortedSetCollectorTest {
	
	public static class NaturalOrder extends ImmutableSortedCollectionTest {

		@SuppressWarnings("unchecked")
		@Override
		public <T extends Comparable<?>, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
			return (Collector<T, ?, E>) new ImmutableSortedSetCollector<T>( () -> ImmutableSortedSet.naturalOrder() );
		}

		@Override
		public <T extends Comparable<?>> Supplier<Collection<T>> getComparisonCollection() {
			return TreeSet::new;
		} 		
	}
	
	public static class ReverseOrder extends ImmutableSortedCollectionTest {

		@SuppressWarnings("unchecked")
		@Override
		public <T extends Comparable<?>, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
			return (Collector<T, ?, E>) new ImmutableSortedSetCollector<T>( () -> ImmutableSortedSet.reverseOrder() );
		}
	}
	
	public static class FromComparator extends ImmutableSortedCollectionTest {

		@SuppressWarnings("unchecked")
		@Override
		public <T extends Comparable<?>, E extends ImmutableCollection<T>> Collector<T, ?, E> getCollector() {
			return (Collector<T, ?, E>) new ImmutableSortedSetCollector<T> ( ()-> 
			ImmutableSortedSet.orderedBy( (t1, t2) -> Ints.compare(t1.hashCode(), t2.hashCode() )));
		} 		
	}
}
