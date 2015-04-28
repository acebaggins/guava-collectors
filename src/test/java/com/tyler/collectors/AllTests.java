package com.tyler.collectors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tyler.collectors.ImmutableSortedSetCollectorTest.FromComparator;
import com.tyler.collectors.ImmutableSortedSetCollectorTest.NaturalOrder;
import com.tyler.collectors.ImmutableSortedSetCollectorTest.ReverseOrder;
import com.tyler.collectors.ImmutableSortedSetCollectorTest.ReverseOrder2;

@RunWith(Suite.class)
@SuiteClasses({ 
	GuavaCollectorsTest.class,
	FromComparator.class, 
	ImmutableListCollectorTest.class,
	ImmutableSetCollectorTest.class, 
	NaturalOrder.class, 
	ReverseOrder.class,
	ReverseOrder2.class,
	BiMapCollectorTest.class,})
public class AllTests {

}
