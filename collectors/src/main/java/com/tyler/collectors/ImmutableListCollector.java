package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

class ImmutableListCollector<T> implements Collector<T,Builder<T>, ImmutableList<T>> {
		
	public Supplier<Builder<T>> supplier() {
		return ImmutableList::builder;
	}

	public BiConsumer<Builder<T>, T> accumulator() {		
		return (builder,toAdd) -> builder.add( toAdd );
	}

	public BinaryOperator<Builder<T>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.addAll( builderToBeAdded.build() );
	}

	public Function<Builder<T>, ImmutableList<T>> finisher() {
		return Builder::build;
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
