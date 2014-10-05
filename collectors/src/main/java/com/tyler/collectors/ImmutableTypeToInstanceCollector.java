package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.reflect.ImmutableTypeToInstanceMap;
import com.google.common.reflect.ImmutableTypeToInstanceMap.Builder;
import com.google.common.reflect.TypeToken;

class ImmutableTypeToInstanceCollector<T, K extends T> implements Collector<K, Builder<T>, ImmutableTypeToInstanceMap<T>> {
		
	public Supplier<Builder<T>> supplier() {		
		return ImmutableTypeToInstanceMap::builder;
	}

	@SuppressWarnings("unchecked")
	public BiConsumer<Builder<T>, K> accumulator() {				
		return (builder,toAdd) -> builder.put( (Class<K>)toAdd.getClass(), toAdd );
	}

	@SuppressWarnings("unchecked")
	public BinaryOperator<Builder<T>> combiner() {
		return (builderToAdd, builderToBeAdded) -> {
			builderToBeAdded.build().entrySet().stream().forEach( 
					entry -> builderToAdd.put( (TypeToken<T>)entry.getKey(), entry.getValue()));
			
			return builderToBeAdded;
		};
	}

	public Function<Builder<T>, ImmutableTypeToInstanceMap<T>> finisher() {
		return builder -> (ImmutableTypeToInstanceMap<T>)builder.build();
	}

	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return GuavaCollectors.EMPTY;
	}
}
