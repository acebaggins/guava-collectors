package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableSortedSet.Builder;

class ImmutableSortedSetCollector<T extends Comparable<?>> implements Collector<T,Builder<T>, ImmutableSortedSet<T>>{
	private final Supplier<Builder<T>> supplier;
	
	ImmutableSortedSetCollector( final Supplier<Builder<T>> supplier ) {
		this.supplier = supplier;
	}
		
	@Override
	public Supplier<Builder<T>> supplier() {		
		return supplier;
	}

	@Override
	public BiConsumer<Builder<T>, T> accumulator() {
		return (builder, toAdd) -> builder.add( toAdd );
	}

	@Override
	public BinaryOperator<Builder<T>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.addAll( builderToBeAdded.build() );
	}

	@Override
	public Function<Builder<T>, ImmutableSortedSet<T>> finisher() {
		return Builder::build;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.UNORDERED_ONLY;
	}
}
