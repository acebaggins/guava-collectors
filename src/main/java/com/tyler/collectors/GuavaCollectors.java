/*
 * 	Copyright 2015 Tyler Russell
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tyler.collectors;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableSortedSet.Builder;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

public class GuavaCollectors {	

	public static <T> Collector<T, ?, ImmutableList<T>> asImmutableList() {

		return Collector.of(
				ImmutableList.Builder<T>::new, 
				ImmutableList.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableList.Builder<T>::build);
	}

	public static <T> Collector<T, ?, ImmutableSet<T>> asImmutableSet() {

		return Collector.of(
				ImmutableSet.Builder<T>::new, 
				ImmutableSet.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableSet.Builder<T>::build);
	}

	public static <T> Collector<T, ?, ImmutableMultiset<T>> asImmutableMultiSet() {

		return Collector.of(
				ImmutableMultiset.Builder<T>::new, 
				ImmutableMultiset.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableMultiset.Builder<T>::build);
	}

	public static <T> Collector<T, ?, Multiset<T>> asMultiSet(){		

		return asMultiSet( HashMultiset::create );
	}

	public static <T> Collector<T, ?, Multiset<T>> asMultiSet( final Supplier<Multiset<T>> supplier){		

		return Collector.of(
				supplier,
				( set, t ) -> set.add( t ),
				( l, r ) -> { l.addAll( r ); return l; },
				l -> l,
				Characteristics.IDENTITY_FINISH );
	}


	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> asImmutableSortedSet() {		

		return Collector.of(
				ImmutableSortedSet::<T> naturalOrder,
				ImmutableSortedSet.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableSortedSet.Builder<T>::build );
	}	

	public static <T extends Comparable<?>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> asImmutableSortedSetReversed() {		

		return Collector.of(
				ImmutableSortedSet::<T> reverseOrder,
				ImmutableSortedSet.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableSortedSet.Builder<T>::build,
				Characteristics.UNORDERED);
	}		

	public static <T> Collector<T, ?, ImmutableSortedSet<T>> asImmutableSortedSet( final Supplier<Builder<T>> supplier ){

		return Collector.of(
				supplier,
				ImmutableSortedSet.Builder<T>::add,
				(l, r) -> l.addAll(r.build()), 
				ImmutableSortedSet.Builder<T>::build,
				Characteristics.UNORDERED);
	}

	public static <T> Collector<T, ?, ImmutableSortedSet<T>> asImmutableSortedSet( final Comparator<T> supplier ){;

	return Collector.of(
			() -> ImmutableSortedSet.orderedBy( supplier ),
			ImmutableSortedSet.Builder<T>::add,
			(l, r) -> l.addAll(r.build()), 
			ImmutableSortedSet.Builder<T>::build,
			Characteristics.UNORDERED);	
	}

	public static <T, K, V> Collector<T,?,ImmutableMap<K,V>> asImmutableMap(
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				ImmutableMap::<K,V>builder, 
				( builder, value ) -> builder.put( keyFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> l.putAll( r.build() ),
				ImmutableMap.Builder<K,V>::build);
	}

	public static <T, K, V> Collector<T,?,ImmutableBiMap<K,V>> asImmutableBiMap(
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				ImmutableBiMap::<K,V> builder,
				(builder, value) -> builder.put( keyFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> l.putAll( r.build() ),
				ImmutableBiMap.Builder<K,V>::build);

	}

	public static <T, M extends BiMap<K,V>, K, V> Collector<T,?,BiMap<K,V>> asBiMap(			
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){

		return asBiMap( HashBiMap::<K,V>create, keyFunction, valueFunction );		
	}

	public static <T, K, V> Collector<T,?,BiMap<K,V>> asBiMap(
			final Supplier<BiMap<K,V>> supplier,
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				supplier,
				(map, value) -> map.put( keyFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> { l.putAll( r ); return l;},
				map -> map,
				Characteristics.IDENTITY_FINISH	);		
	}

	public static <T, R, C, V> Collector<T,?,ImmutableTable<R,C,V>> asImmutableTable( 
			final Function<T,R> rowFunction,
			final Function<T,C> columnFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				ImmutableTable::<R,C,V> builder,
				(builder, value ) -> builder.put( rowFunction.apply( value ), columnFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> l.putAll( r.build() ),
				ImmutableTable.Builder<R,C,V>::build);		
	}

	public static <T, R, C, V> Collector<T,?,Table<R,C,V>> asTable( 
			final Function<T,R> rowFunction,
			final Function<T,C> columnFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				HashBasedTable::<R,C,V> create,
				(table, value ) -> table.put( rowFunction.apply( value ), columnFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> { l.putAll( r ); return l; },
				table -> table,
				Characteristics.IDENTITY_FINISH);		
	}

	public static <T, R, C, V> Collector<T,?,Table<R,C,V>> asTable( 
			final Supplier<Table<R,C,V>> supplier,
			final Function<T,R> rowFunction,
			final Function<T,C> columnFunction,
			final Function<T,V> valueFunction ){

		return Collector.of( 
				supplier,
				(table, value ) -> table.put( rowFunction.apply( value ), columnFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> { l.putAll( r ); return l; },
				table -> table,
				Characteristics.IDENTITY_FINISH);		
	}

	public static <T, M extends Multimap<K,V>, K, V> Collector<T, ?, M> asMultimap(
			final Supplier<M> supplier,
			final Function<T,K> keyFunction,
			final Function<T,V> valueFunction ){

		return Collector.of(
				supplier,	
				(map, value) -> map.put( keyFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> { l.putAll( r ); return l; }, 
				map -> map, 
				Characteristics.IDENTITY_FINISH	 );
	}

	public static <T, K, V, M extends Multimap<K,V>> Collector<T,?,M> asMultimapFromIterable(
			final Supplier<M> supplier,
			final Function<T,K> keyFunction, 
			final Function<T, ? extends Iterable<V>> valueFunction ){

		return Collector.of(
				supplier,	
				(map, value) -> map.putAll( keyFunction.apply( value ), valueFunction.apply( value )),
				(l, r) -> { l.putAll( r ); return l; }, 
				map -> map, 
				Characteristics.IDENTITY_FINISH	 );
	}
}
