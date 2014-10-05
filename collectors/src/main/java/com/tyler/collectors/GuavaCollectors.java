package com.tyler.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableSortedSet.Builder;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Multimap;

public class GuavaCollectors {	
	static final Set<Characteristics> EMPTY = ImmutableSet.of();
	static final Set<Characteristics> UNORDERED_ONLY = ImmutableSet.of(Characteristics.UNORDERED);
	static final Set<Characteristics> IDENTITY_ONLY = ImmutableSet.of( Characteristics.IDENTITY_FINISH );
		
	public static <T> Collector<T, ?, ImmutableList<T>> immutableList(){
		return new ImmutableListCollector<T>();
	}
	
	public static <T> Collector<T, ?, ImmutableSet<T>> immutableSet(){
		return new ImmutableSetCollector<T>();
	}
	
	public static <T extends Comparable<?>> Collector<T, ?, ImmutableSortedSet<T>> immutableSortedSet( final Supplier<Builder<T>> supplier ){
		return new ImmutableSortedSetCollector<T>( supplier );
	}
	
	public static <T, K, V> Collector<T,?,ImmutableMap<K,V>> immutableMap(
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction	){
		final BiConsumer<ImmutableMap.Builder<K, V>, T> accumulator = (builder, value ) -> {
			builder.put( keyFunction.apply( value ), valueFunction.apply( value ));
		};
		
		return new ImmutableMapCollector<T, K, V>( accumulator );
	}
		
	public static <T, K, V> Collector<T,?,ImmutableBiMap<K,V>> immutableBiMap(
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction	){
		final BiConsumer<ImmutableBiMap.Builder<K, V>, T> accumulator = (builder, value ) -> {
			builder.put( keyFunction.apply( value ), valueFunction.apply( value ));
		};
		
		return new ImmutableBiMapCollector<T, K, V>( accumulator );
	}
	
	public static <T, R, C, V> Collector<T,?,ImmutableTable<R,C,V>> immutableTable( 
			final Function<T,R> rowFunction,
			final Function<T,C> columnFunction,
			final Function<T,V> valueFunction	){
		final BiConsumer<ImmutableTable.Builder<R,C,V>, T> accumulator = (builder, value ) -> {
			builder.put( 
					rowFunction.apply( value ),
					columnFunction.apply( value ), 
					valueFunction.apply( value ));
		};
		
		return new ImmutableTableCollector<T,R,C,V>( accumulator );
	}
		
	public static <T, M extends Multimap<K,V>, K, V> Collector<T, ?, M> multimap(
			final Supplier<M> supplier,
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){
		final BiConsumer<M,T> consumer = (map, value) -> map.put( 
				keyFunction.apply( value), 
				valueFunction.apply( value ));
		
		return new MultimapCollector<T ,K, V, M>( supplier, consumer );		
	}
	
	public static <T, K, V, M extends Multimap<K,V>> Collector<T,?,M> iterableMultimap(
			final Supplier<M> supplier,
			final Function<T,K> keyFunction, 
			final Function<T, ? extends Iterable<V>> valueFunction ){
		final BiConsumer<M,T> consumer = (map, value) -> map.putAll( 
				keyFunction.apply( value ), 
				valueFunction.apply( value ));

		return new MultimapCollector<T, K, V, M>( supplier, consumer );
	}
}
