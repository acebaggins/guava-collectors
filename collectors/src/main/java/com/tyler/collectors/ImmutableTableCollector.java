package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableTable.Builder;
import com.google.common.collect.ImmutableTable;

class ImmutableTableCollector<T, R, C, V> implements Collector<T,Builder<R,C,V>, ImmutableTable<R,C,V>> {
	private final BiConsumer<Builder<R,C,V>, T> accumulator;
	
	ImmutableTableCollector( final BiConsumer<Builder<R,C,V>, T> accumulator ){
		this.accumulator = accumulator;		
	}
		
	public Supplier<Builder<R,C,V>> supplier() {
		return ImmutableTable::builder;
	}

	public BiConsumer<Builder<R,C,V>, T> accumulator() {		
		return accumulator;
	}

	public BinaryOperator<Builder<R,C,V>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.putAll( builderToBeAdded.build() );
	}

	public Function<Builder<R,C,V>, ImmutableTable<R,C,V>> finisher() {
		return Builder::build;
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
