package com.tyler.collectors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tyler.collectors.ImmutableSortedSetCollectorTest.FromComparator;
import com.tyler.collectors.ImmutableSortedSetCollectorTest.NaturalOrder;
import com.tyler.collectors.ImmutableSortedSetCollectorTest.ReverseOrder;

@RunWith(Suite.class)
@SuiteClasses({ 
	FromComparator.class, 
	ImmutableListCollectorTest.class,
	ImmutableSetCollectorTest.class, 
	NaturalOrder.class, 
	ReverseOrder.class })
public class AllTests {

}
