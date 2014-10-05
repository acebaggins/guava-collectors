package com.tyler.collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public abstract class ImmutableSortedCollectionTest {	
	public abstract <T extends Comparable<?>, E extends ImmutableCollection<T>> Collector<T, ?, E > getCollector();
	public <T extends Comparable<?>> Supplier<Collection<T>> getComparisonCollection(){ return TreeSet::new; }
	
	@Test
	public void testCollection_1(){
		final Collection<String> strings = buildStrings().stream().collect( Collectors.toCollection( getComparisonCollection()));		
		final ImmutableCollection<String> collected = strings.stream().collect( getCollector() );
		
		assertEquals( strings, collected );
	}
	
	@Test
	public void testConcurrency_1(){			
		final ImmutableCollection<String> collected = buildStrings().stream()
				.collect( getCollector() );		

		final ImmutableCollection<String> parallel = buildStrings().parallelStream()				
				.collect( getCollector() );
		
		assertEquals( collected, parallel );
		assertTrue( isOrderedCorrectly(collected, parallel));
	}
	
	private boolean isOrderedCorrectly( final Iterable<String> expected, final Iterable<String> actual ){
		for( int i = 0; i < Iterables.size(expected); i++ )
			if( !Iterables.get( expected, i).equals( Iterables.get( actual, i)))
				return false;
		
		return true;
		
	}
	
	private List<String> strings;	
	private List<String> buildStrings(){
		if( strings == null )
			try {
				
				List<String> tempStrings = Files.readAllLines( Paths.get("src", "test", "java", "fullbook.txt" ));
		
				
				this.strings = ImmutableList.<String>builder()
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.addAll( tempStrings )
						.build();
			} catch (IOException e) {
				throw new RuntimeException( e );				
			}
		
		return strings;
	}	
}
