package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.Multimap;

class MultimapCollector<T, K, V, M extends Multimap<K,V>> implements Collector<T, M, M>{	
	private final Supplier<M> supplier;
	private final BiConsumer<M,T> accumulator;

	MultimapCollector( 
			final Supplier<M> supplier,
			final BiConsumer<M,T> accumulator ){
		this.supplier = supplier;
		this.accumulator = accumulator;
	}
	
	@Override
	public Supplier<M> supplier() {		
		return supplier;
	}

	@Override
	public BiConsumer<M, T> accumulator() {
		return accumulator;
	}

	@Override
	public BinaryOperator<M> combiner() {
		return (mapToAddTo, mapToBeAdded) -> { 
			mapToAddTo.putAll( mapToBeAdded ); 
			return mapToAddTo;
		};
	}

	@Override
	public Function<M, M> finisher() {
		return multiMap -> multiMap;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.IDENTITY_ONLY;
	}
}
