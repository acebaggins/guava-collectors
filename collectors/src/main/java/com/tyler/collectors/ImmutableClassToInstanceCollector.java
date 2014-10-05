package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap.Builder;

class ImmutableClassToInstanceCollector<T, K extends T> implements Collector<K, Builder<T>, ImmutableClassToInstanceMap<T>> {
		
	public Supplier<Builder<T>> supplier() {
		return ImmutableClassToInstanceMap::builder;
	}

	@SuppressWarnings("unchecked")
	public BiConsumer<Builder<T>, K> accumulator() {		
		
		return (builder,toAdd) -> builder.put( (Class<K>)toAdd.getClass(), toAdd );
	}

	public BinaryOperator<Builder<T>> combiner() {
		return (builderToAdd, builderToBeAdded) -> builderToAdd.putAll( builderToBeAdded.build() );
	}

	public Function<Builder<T>, ImmutableClassToInstanceMap<T>> finisher() {
		return builder -> (ImmutableClassToInstanceMap<T>)builder.build();
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
