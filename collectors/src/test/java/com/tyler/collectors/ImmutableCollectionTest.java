package com.tyler.collectors;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public abstract class ImmutableCollectionTest {	
	public abstract <T, E extends ImmutableCollection<T>> Collector<T, ?, E > getCollector();
	public abstract <T> Supplier<Collection<T>> getComparisonCollection();
	
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
