package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.ImmutableSet;

class ImmutableSetCollector<T> implements Collector<T,Builder<T>, ImmutableSet<T>>{
	
	public Supplier<Builder<T>> supplier() {
		return ImmutableSet::builder;
	}

	public BiConsumer<Builder<T>, T> accumulator() {		
		return (builder,toAdd) ->	builder.add( toAdd );	
	}

	public BinaryOperator<Builder<T>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.addAll( builderToBeAdded.build() );
	}

	public Function<Builder<T>, ImmutableSet<T>> finisher() {
		return Builder::build;
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
