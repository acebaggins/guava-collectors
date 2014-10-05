package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;

class ImmutableBiMapCollector<T, K, V> implements Collector<T,Builder<K,V>, ImmutableBiMap<K,V>> {
	private final BiConsumer<Builder<K,V>, T> accumulator;
	
	ImmutableBiMapCollector( final BiConsumer<Builder<K,V>, T> accumulator ){
		this.accumulator = accumulator;		
	}

	public Supplier<Builder<K,V>> supplier() {
		return ImmutableBiMap::builder;
	}

	public BiConsumer<Builder<K,V>, T> accumulator() {		
		return accumulator;
	}

	public BinaryOperator<Builder<K,V>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.putAll( builderToBeAdded.build() );
	}

	public Function<Builder<K,V>, ImmutableBiMap<K,V>> finisher() {
		return Builder::build;
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
