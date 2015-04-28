package com.tyler.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class TestHelper {
	public static final List<String> LINES = buildLines();
	public static final List<String> STRINGS = buildStrings();
	public static final Map<Integer,String> MAP = buildMap();
	public static final Table<Integer, String, String> TABLE = buildImmutableTable();

	public static final List<String> buildLines(){
		try {
			return Files.readAllLines( Paths.get("src", "test", "java", "fullbook.txt" ));

		} catch (IOException e) {
			throw new RuntimeException( e );
		}
	}
	public static final List<String> buildStrings(){
		final Splitter splitter = Splitter.on( CharMatcher.WHITESPACE ).omitEmptyStrings();

		return LINES.stream()
				.map( splitter::splitToList )
				.flatMap( lines -> lines.stream() ).collect( Collectors.toList() );
	}

	public static final Map<Integer,String> buildMap(){		
		final AtomicInteger index = new AtomicInteger(0);
		final Set<String> unique = Sets.newHashSet( LINES );
		return unique.stream().filter( string -> !Strings.isNullOrEmpty( string )).collect( Collectors.toMap( string -> index.incrementAndGet(), string -> string ) );		
	}
	
	public static final Multimap<Integer, String> buildMultimap( final Supplier<Multimap<Integer,String>> supplier ){
		final Multimap<Integer, String> multimap = supplier.get();
		
		buildMap().entrySet().stream().forEach( entry -> multimap.put( entry.getKey() % 1500, entry.getValue()));
		
		return multimap;
	}
	
	public static final Table<Integer, String, String> buildImmutableTable(){
		ImmutableTable.Builder<Integer, String, String> builder = ImmutableTable.builder();
		
		MAP.entrySet().stream().forEach( entry -> builder.put( entry.getKey(), entry.getKey() + "a", entry.getValue() ));
		return builder.build();		
	}
	
	public static final Table<Integer, String, String> buildImmutableTable( final Supplier<Table<Integer,String,String>> supplier ){
		final Table<Integer, String, String> table = supplier.get();
		
		MAP.entrySet().stream().forEach( entry -> table.put( entry.getKey(), entry.getKey() + "a", entry.getValue() ));
		return table;	
	}
}
