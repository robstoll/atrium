# List of APIs
Atrium provides different APIs where the API differ in its style and the language in which it is written.
You have the choice which one(s) you want to use. 
Hence it is up to you if you want to mix and match different styles or enforce just one style.

Atrium provides so called bundle-modules which merely bundle dependencies (they do not provide additional functionality).
These modules bundle:
- an API module
- a translation module (the language used in reporting)
- an implementation of the domain of Atrium
- an implementation of the core of Atrium


Following a list of the available bundle-modules. 
The links point to the KDoc of their included API where you find an overview of all available assertion functions of the API.

- [atrium-cc-de_CH-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.de_-c-h/index.html)
- [atrium-cc-en_GB-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/index.html)
- [atrium-cc-infix-en_GB-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.infix.en_-g-b/index.html)

----

Following an excerpt of a build.gradle file which uses two APIs (see 
[README#Installation](https://github.com/robstoll/atrium/tree/master/README.md#installation)
for the rest):
```
dependencies {
    testCompile "ch.tutteli:atrium-cc-en_GB-robstoll:$atrium_version"
    testCompile "ch.tutteli:atrium-api-cc-infix-en_GB:$atrium_version"
}
```

The first dependency points to a bundle-module, the second one just adds the infix-API in addition.

:warning: if you want to use the same API in different languages, 
then you have to make sure that you exclude all translation modules but one (I suggest you keep the one which is your primary language).
If you forget to do it, then the compiler will complain that you have the same enums multiple times on your classpath.

# Different API styles

Atrium provides different APIs where the API differ in its style and the language in which it is written.
This site focuses on the different styles of APIs and compares their en_GB versions. 
We do not show every single difference but merely where the APIs differ in naming.
For instance, the assertion function `AssertionPlant<Any>.toBe`:

*atrium-api-cc-en_GB*
```kotlin
assert(x).toBe(2)
``` 
*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) toBe 2
``` 

is too similar, we will not list it here (ok, we did now but I guess you get the point).

## Table of Content
- [Empty CharSequence / Collection](#empty-charsequence--collection)
- [`and` property](#and-property)
- [CharSequence contains](#charsequence-contains)
- [Iterable contains in any order](#iterable-contains-in-any-order)
- [Iterable contains in any order with nullable elements](#iterable-contains-in-any-order-with-nullable-elements)
- [Iterable contains in order](#iterable-contains-in-order)
- [Iterable contains in order with nullable elements](#iterable-contains-in-order-with-nullable-elements)
- [Iterable contains not](#iterable-contains-not)

## Empty CharSequence / Collection

*atrium-api-cc-en_GB*
```kotlin
assert(x).isEmpty()
assert(x).isNotEmpty()
```

*atrium-api-cc-infix-en_GB*

```kotlin
assert(x) toBe Empty
assert(x) notToBe Empty
```

## `and` property

*atrium-api-cc-en_GB*
```kotlin
assert(x).isGreaterThan(1).and.isLessThan(10)
assert(x) { /*...*/ } and { /*...*/ }
```

*atrium-api-cc-infix-en_GB*
```kotlin
// does only support the group syntax
assert(x) { /*...*/ } and { /*...*/ }
```

## CharSequence contains

*atrium-api-cc-en_GB*
```kotlin
assert(x).contains("hello", "world")
assert(x).contains.atLeast(1).butAtMost(2).value("hello")
assert(x).contains.exactly(1).values("hello", "robert")
assert(x).contains.atMost(2).regex("h(e|a)llo")
assert(x).contains.ignoringCase.notOrAtMost(1).regex("h(e|a)llo", "[Rr]obert")
assert(x).containsNot.defaultTranslationOf(DescriptionBasic.IS_NOT)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) contains Values("hello", "world")
assert(x) to contain atLeast 1 butAtMost 2 value "hello"
assert(x) to contain exactly 1 the Values("hello", "robert")
assert(x) to contain atMost 2 regex "h(e|a)llo"
assert(x) to contain ignoring case notOrAtMost 1 the RegularPatterns("h(e|a)llo", "[Rr]obert")
assert(x) notTo contain defaultTranslationOf DescriptionBasic.IS_NOT
```

## Iterable contains in any order

*atrium-api-cc-en_GB*
```kotlin
assert(x).contains(1.2)
assert(x).contains(1.2, 5.7)
assert(x).contains({ isLessThan(2) })
assert(x).contains({ isLessThan(2) }, { isGreaterThan 5 })
assert(x).contains.inAnyOrder.atLeast(1).butAtMost(2).value(3.2)
assert(x).contains.inAnyOrder.exactly(1).values("hello", "robert")
assert(x).contains.inAnyOrder.atMost(2).value(y)
assert(x).contains.inAnyOrder.notOrAtMost(2).values(y, z)
assert(x).contains.inAnyOrder.only.value("hello")
assert(x).contains.inAnyOrder.only.values(personA, personB)
assert(x).contains.inAnyOrder.only.entry { isLessThan(2) }
assert(x).contains.inAnyOrder.only.entries({ toBe(3) }, { isLessThan(2) })
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) contains 1.2
assert(x) contains Values(1.2, 5.7) // or Objects as alternative
assert(x) contains { this isLessThan 2 }
assert(x) contains Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
assert(x) to contain inAny order atLeast 1 butAtMost 2 value 3.2
assert(x) to contain inAny order exactly 1 the Values("hello", "robert")
assert(x) to contain inAny order atMost 2 value y
assert(x) to contain inAny order notOrAtMost 2 the Values(y, z)
assert(x) to contain inAny order but only value "hello")
assert(x) to contain inAny order but only the Values(personA, personB)
assert(x) to contain inAny order but only entry { this isLessThan 2 } 
assert(x) to contain inAny order but only the Entries({ this toBe 3 }, { this isLessThan 2 })
```

## Iterable contains in any order with nullable elements

*atrium-api-cc-en_GB*
```kotlin
assert(listOf(null, 1)).containsNullableValue(null)
assert(listOf(null, 1)).containsNullableValues(null, 1)
assert(listOf(null, 1)).containsNullableEntry(null)
assert(listOf(null, 1)).containsNullableEntries(null,  { isLessThan(2) })
assert(listOf(null, 1)).contains.inAnyOrder.atLeast(1).nullableValue(null)
assert(listOf(null, 1)).contains.inAnyOrder.atLeast(1).nullableValues(null, 1)
assert(listOf(null, 1)).contains.inAnyOrder.atLeast(1).nullableEntry(null)
assert(listOf(null, 1)).contains.inAnyOrder.atLeast(1).nullableEntries(null, { isLessThan(2) })
//see above for more `inAnyOrder` options
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(listOf(null, 1)) contains NullableValue(null)
assert(listOf(null, 1)) contains NullableValues(null, 1)
assert(listOf(null, 1)) contains NullableEntry(null)
assert(listOf(null, 1)) contains NullableEntries(null,  { isLessThan(2) })
assert(listOf(null, 1)) to contain inAny order atLeast 1 nullableValue null
assert(listOf(null, 1)) to contain inAny order atLeast 1 the NullableValues(null, 1)
assert(listOf(null, 1)) to contain inAny order atLeast 1 nullableEntry null
assert(listOf(null, 1)) to contain inAny order atLeast 1 the NullableEntries(null, { this isLessThan 2 })
//see above for more `inAny order` options
```


## Iterable contains in order

*atrium-api-cc-en_GB*
```kotlin
assert(x).containsStrictly(1.2)
assert(x).containsStrictly(1.2, 5.7)
assert(x).containsStrictly({ isLessThan(2) })
assert(x).containsStrictly({ isLessThan(2) }, { isGreaterThan 5 })
assert(x).contains.inOrder.only.value("hello")
assert(x).contains.inOrder.only.values("hello", "world")
assert(x).contains.inOrder.only.entry { isLessThan(2) }
assert(x).contains.inOrder.only.entries({ toBe(3) }, { isLessThan(2) })
assert(x).contains.inOrder.only.grouped.within.inAnyOrder(
    Value(1), 
    Values(1, 2), 
    Values(3, 4)
)
assert(x).contains.inOrder.only.grouped.within.inAnyOrder(
    Entry({ toBe(1) }), 
    Entries({ isLessThan(2) },{ isGreaterThan(2) }), 
    Entries({ toBe(3) }, { toBe(4) })
)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) containsStrictly 1.2
assert(x) containsStrictly Values(1.2, 5.7) // or Objects as alternative
assert(x) containsStrictly { this isLessThan 2 }
assert(x) containsStrictly Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
assert(x) contains inGiven order and only value "hello"
assert(x) contains inGiven order and only the Values("hello", "world")
assert(x) contains inGiven order and only entry { this isLessThan 2 }
assert(x) contains inGiven order and only the Entries({ this toBe 3 }, { this isLessThan 2 })
assert(x) contains inGiven order and only grouped entries within group inAny Order(
    Value(1), 
    Values(1, 2), 
    Values(3, 4)
)
assert(x) contains inGiven order and only grouped entries within group inAny Order(
    Entry({ this toBe(1) }), 
    Entries({ this isLessThan(2) },{ this isGreaterThan(2) }), 
    Entries({ this toBe(3) }, { this toBe(4) })
)
```

## Iterable contains in order with nullable elements

*atrium-api-cc-en_GB*
```kotlin
assert(listOf(null, 1)).containsStrictlyNullableValue(null)
assert(listOf(null, 1)).containsStrictlyNullableValues(null, 1)
assert(listOf(null, 1)).containsStrictlyNullableEntry(null)
assert(listOf(null, 1)).containsStrictlyNullableEntries(null,  { isLessThan(2) })
assert(listOf(null, 1)).contains.inOrder.only.nullableValue(null)
assert(listOf(null, 1)).contains.inOrder.only.nullableValues(null, 1)
assert(listOf(null, 1)).contains.inOrder.only.nullableEntry(null)
assert(listOf(null, 1)).contains.inOrder.only.nullableEntries(null, { isLessThan(2) })
assert(listOf(null, 1)).contains.inOrder.only.grouped.within.inAnyOrder(
    NullableValue(1), 
    NullableValues(1, 2), 
    NullableValues(3, 4)
)
assert(listOf(null, 1)).contains.inOrder.only.grouped.within.inAnyOrder(
    NullableEntry({ toBe(1) }), 
    NullableEntries({ isLessThan(2) },{ isGreaterThan(2) }), 
    NullableEntries({ toBe(3) }, { toBe(4) })
)
//see above for more `inOrder` options
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(listOf(null, 1)) containsStrictly NullableValue(null)
assert(listOf(null, 1)) containsStrictly NullableValues(null, 1)
assert(listOf(null, 1)) containsStrictly NullableEntry(null)
assert(listOf(null, 1)) containsStrictly NullableEntries(null,  { isLessThan(2) })
assert(listOf(null, 1)) to contain inGiven order and only nullableValue null
assert(listOf(null, 1)) to contain inGiven order and only the NullableValues(null, 1)
assert(listOf(null, 1)) to contain inGiven order and only nullableEntry null
assert(listOf(null, 1)) to contain inGiven order and only the NullableEntries(null, { this isLessThan 2 })
assert(listOf(null, 1)) contains inGiven order and only grouped entries within group inAny Order(
    NullableValue(1), 
    NullableValues(1, 2), 
    NullableValues(3, 4)
)
assert(listOf(null, 1)) contains inGiven order and only grouped entries within group inAny Order(
    NullableEntry({ this toBe(1) }), 
    NullableEntries({ this isLessThan(2) },{ this isGreaterThan(2) }), 
    NullableEntries({ this toBe(3) }, { this toBe(4) })
)
//see above for more `inGiven order` options
```

## Iterable contains not

*atrium-api-cc-en_GB*
```kotlin
assert(x).containsNot(1.2)
assert(x).containsNot(1.2, 5.7)
assert(x).containsNot.entry { isLessThan(2) }
assert(x).containsNot.entries({ isLessThan(2) }, { isGreaterThan 5 })
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) containsNot 1.2
assert(x) containsNot Values(1.2, 5.7)
assert(x) notTo contain entry { this isLessThan 2 }
assert(x) notTo contain the Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
```

## Iterable contains not with nullable elements

*atrium-api-cc-en_GB*
```kotlin
assert(x).containsNot.nullableValue(null)
assert(x).containsNot.nullableValues(null, 1)
assert(x).containsNot.nullableEntry(null)
assert(x).containsNot.nullableEntries(null, { isLessThan(2) })
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) notTo contain nullableValue null
assert(x) notTo contain the NullableValues(null, 1)
assert(x) notTo contain nullableEntry null
assert(x) notTo contain the NullableEntries(null, { this isLessThan 2 })
```