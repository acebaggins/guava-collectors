guava-collectors
================

#### Note (4/15/2017)
This library has been made obsolete by [Guava 21.0.](https://github.com/google/guava/wiki/Release21)

If you're using Guava 21.0 then it is suggested to use their method that is part of the immutable collection classes.






### What?
Collectors for Guava collections that mirror Java's Collectors class.

### Why?
I'm a big fan of the extended collection in Guava and the new features released with Java 8. 
I didn't want to be forced into using only Java's collections because Collectors.toList() is easier to use than 
writing a collector so I wrote them here (and tested them).

### How?
If you're comfortable using the Collectors class then this shouldn't be anything new.

As an example,

```java
List<String> mutableList = getStream().map(Object::toString).collect( Collectors.toList() );
  
  
List<String> immutableList = getStream()
                             .map(Object::toString)
                             .collect(GuavaCollectors.toImmutableList());
```

Suppliers are used when you want a specific child class of a Guava collection. These two methods are equivalent.

```java
BiMap<String,String> hashBiMap = getStream()
                                .collect(GuavaCollectors.toBiMap(
                                    SomeObject::keyFunction, 
                                    SomeObject::valueFunction );
                                    
                                
BiMap<String,String> anotherHashBiMap = getStream()
                                        .collect(GuavaCollectors.toBiMap( 
                                            HashBiMap::create,
                                            SomeObject::keyFunction, 
                                            SomeObject::valueFunction );
```

##### Currently supported
* ImmutableList
* ImmutableSet
* ImmutableMultiSet/MultiSet
* ImmutableSortedSet
* ImmutableMap
* ImmutableBiMap/BiMap
* ImmutableTable/Table
* Multimap

### Who? 
Built by me for the wonderful Guava library. 

### Where? 
That's a weird question.

### When?
Also weird.
