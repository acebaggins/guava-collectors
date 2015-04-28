guava-collectors
================

###What?
Collectors for Guava collections that mirror Java's Collectors class.

####Why?
I'm a big fan of the extended collection in Guava and the new features released with Java 8. 
I didn't want to be forced into using only Java's collections because Collectors.toList() is easier to use than 
writing a collector so I wrote them here (and tested them).

###How?
If you're comfortable using the Collectors class then this shouldn't be anything new.

As an example,

```java
final List<String> mutableList = getSomeCollection().stream().collect( Collectors.toList( object -> object.toString() );
final List<String> betterList = getSomeCollection().stream().collect( GuavaCollectors.toImmutableList( object -> object.toString() );
```

Parallel streams are also supported so there is no need for performance to suffer to get a Guava collection.

```java
final List<String> immutableList = getSomeCollection().stream().collect( GuavaCollectors.toImmutableList( object -> object.toString() );
final List<String> fasterList = getSomeCollection().parallelStream().collect( GuavaCollectors.toImmutableList( object -> object.toString() );
```

Suppliers are used when you want a specific child class of a Guava collection. These two methods are equivalent.

```java
    final BiMap<String,String> hashBiMap = getSomeCollection().stream().collect( GuavaCollectors.toBiMap(keyFunction(), valueFunction() );
    final BiMap<String,String> anotherHashBiMap = getSomeCollection().stream().collect( GuavaCollectors.toBiMap( ()-> HashBiMap.create(), keyFunction(), valueFunction() );
```

#####Currently supported
* ImmutableList
* ImmutableSet
* ImmutableMultiSet/MultiSet
* ImmutableSortedSet
* ImmutableMap
* ImmutableBiMap/BiMap
* ImmutableTable/Table
* Multimap

###Who? 
Built by me for the wonderful Guava library. 

###Where? 
That's a weird question.

###When?
Also weird.
