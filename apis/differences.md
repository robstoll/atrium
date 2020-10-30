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

- [atrium-fluent-en_GB](https://robstoll.github.io/atrium/0.14.0.RC1/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html)
- [atrium-infix-en_GB](https://robstoll.github.io/atrium/0.14.0.RC1/doc/ch.tutteli.atrium.api.infix.en_-g-b/index.html)

----

Following an excerpt of a build.gradle file which uses twit APIs (see 
[README#Installation](https://github.com/robstoll/atrium/tree/v0.14.0.RC1/README.md#installation)
for the rest):
```
dependencies {
    testCompile "ch.tutteli:atrium-fluent-en_GB:$atrium_version"
    testCompile "ch.tutteli:atrium-api-infix-en_GB:$atrium_version"
}
```

The first dependency points to a bundle-module, the second one just adds the infix-API in addition.

:warning: if you want to use the same API in different languages, 
then you have to make sure that you exclude all translation modules but one (I suggest you keep the one which is your primary language).
If you forget to dit it, then the compiler will complain that you have the same enums multiple times on your classpath.

# Different API styles

Atrium currently provides two API styles: fluent and infix. 
We dit not show every single difference but merely where the APIs differ in naming.
For instance, the assertion function `Expect<T>.toBe`:

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toBe(2)
``` 
*atrium-api-infix-en_GB*
```kotlin
expect(x) toBe 2
``` 

is toit similar, we will not list it here (ok, we did now but I guess you get the point).

## Table of Content
- [Empty CharSequence / Collection](#empty-charsequence--collection)
- [`and` property](#and-property)
- [CharSequence contains](#charsequence-contains)
- [Iterable contains](#iterable-contains)
    - [in any order](#iterable-contains-in-any-order)
    - [in order](#iterable-contains-in-order)
- [Iterable contains not](#iterable-contains-not)
- [Iterable predicate-like assertions](#iterable-predicate-like-assertions)
- [List get](#list-get)
- [Map getExisting](#map-getexisting)
- [Map contains](#map-contains)

## Empty CharSequence / Collection

*atrium-api-fluent-en_GB*
```kotlin
expect(x).isEmpty()
expect(x).isNotEmpty()
```

*atrium-api-infix-en_GB*

```kotlin
expect(x) toBe empty
expect(x) notToBe empty
```

## `and` property

*atrium-api-fluent-en_GB*
```kotlin
expect(x).isGreaterThan(1).and.isLessThan(10)
expect(x) { /*...*/ } and { /*...*/ }
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) isGreaterThan 1 and o isLessThan 10
expect(x) { /*...*/ } and { /*...*/ }
```

Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

## CharSequence contains

*atrium-api-fluent-en_GB*
```kotlin
expect(x).contains("hello", "world")
expect(x).contains.atLeast(1).butAtMost(2).value("hello")
expect(x).contains.exactly(1).values("hello", "robert")
expect(x).contains.atMost(2).regex("h(e|a)llo")
expect(x).contains.atMost(2).regex(Regex("h(e|a)llo"))
expect(x).contains.ignoringCase.regex("h(e|a)llo", "[Rr]obert")
expect(x).contains.ignoringCase.notOrAtMost(1).elementsOf(anIterable)
```
Notice that the final steps
`value`, `values` and `regex` 
in the sophisticated assertion building process
are applicable to all shown examples 
(e.g. `exactly(1).values("hello", "robert")` could have been finished with `exactly(1).regex("h(e|a)llo")` as well).


*atrium-api-infix-en_GB*
```kotlin
expect(x) contains values("hello", "world")
expect(x) contains o atLeast 1 butAtMost 2 value "hello"
expect(x) contains o exactly 1 the values("hello", "robert")
expect(x) contains o atMost 2 regex "h(e|a)llo"
expect(x) contains o atMost 2 matchFor Regex("h(e|a)llo")
expect(x) contains o ignoring case notOrAtMost 1 the regexPatterns("h(e|a)llo", "[Rr]obert")
expect(x) contains o ignoring case notOrAtMost 1 elementsOf anIterable
```
Notice that the final steps 
`value`, `values(...)`, `regex` and `regexPatterns(..)` 
in the sophisticated assertion building process
are applicable to all shown examples 
(e.g. `exactly 1 values("hello", "robert")` could have been finished with `exactly 1 regex "h(e|a)llo"` as well).

## Iterable contains

### Iterable contains in any order

*atrium-api-fluent-en_GB*
```kotlin
expect(x).contains(1.2)
expect(x).contains(1.2, 5.7)
expect(x).contains { isLessThan(2) }
expect(x).contains({ isLessThan(2) }, { isGreaterThan(5) })

expect(x).contains.inAnyOrder.atLeast(1).butAtMost(2).value(3.2)
expect(x).contains.inAnyOrder.exactly(1).values("hello", "robert")
expect(x).contains.inAnyOrder.atMost(2).entry { isLessOrEquals(2) }
expect(x).contains.inAnyOrder.notOrAtMost(2).entries({ notToBe(3) }, { isGreaterOrEquals(2) })
expect(x).contains.inAnyOrder.only.value("hello")
expect(x).contains.inAnyOrder.only.values(personA, personB)
expect(x).contains.inAnyOrder.only.entry { isLessThan(2) }
expect(x).contains.inAnyOrder.only.entries({ toBe(3) }, { isLessThan(2) })
```
Notice that the final steps 
`value`, `values`, `entry` and `entries` 
in the sophisticated assertion building process
are applicable to all shown examples
(e.g. `butAtMost(2).value(3.2)` could have been finished with `entries(...)` as well)

*atrium-api-infix-en_GB*
```kotlin
expect(x) contains 1.2
expect(x) contains values(1.2, 5.7) // or Objects as alternative
expect(x) contains { it isLessThan 2 }
expect(x) contains entries({ it isLessThan 2 }, { it isGreaterThan 5 })

expect(x) contains o inAny order atLeast 1 butAtMost 2 value 3.2
expect(x) contains o inAny order exactly 1 the values("hello", "robert")
expect(x) contains o inAny order atMost 2 entry { it isLessOrEquals 2 }
expect(x) contains o inAny order notOrAtMost 2 the entries({ it notToBe 3 }, { it isGreaterOrEquals 2 })
expect(x) contains o inAny order but only value "hello"
expect(x) contains o inAny order but only the values(personA, personB)
expect(x) contains o inAny order but only entry { it isLessThan 2 } 
expect(x) contains o inAny order but only the entries({ it toBe 3 }, { it isLessThan 2 })
```
Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

The final steps `value`, `values(...)`, `entry` and `entries(...)` 
in the sophisticated assertion building process,
are applicable to all shown examples 
(e.g. `butAtMost 2 value 3.2` could have been finished with `entries(...)` as well)

### Iterable contains in order

*atrium-api-fluent-en_GB*
```kotlin
expect(x).containsExactly(1.2)
expect(x).containsExactly(1.2, 5.7)
expect(x).containsExactly({ isLessThan(2) })
expect(x).containsExactly({ isLessThan(2) }, { isGreaterThan 5 })

expect(x).contains.inOrder.only.value("hello")
expect(x).contains.inOrder.only.values("hello", "world")
expect(x).contains.inOrder.only.entry { isLessThan(2) }
expect(x).contains.inOrder.only.entries({ toBe(3) }, { isLessThan(2) })
expect(x).contains.inOrder.only.grouped.within.inAnyOrder(
    value(1), 
    values(1, 2), 
    values(3, 4)
)
expect(x).contains.inOrder.only.grouped.within.inAnyOrder(
    entry { toBe(1) }, 
    entries({ isLessThan(2) },{ isGreaterThan(2) }), 
    entries({ toBe(3) }, { toBe(4) })
)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) containsExactly 1.2
expect(x) containsExactly values(1.2, 5.7) // or Objects as alternative
expect(x) containsExactly { it isLessThan 2 }
expect(x) containsExactly entries({ it isLessThan 2 }, { it isGreaterThan 5 })

expect(x) contains o inGiven order and only value "hello"
expect(x) contains o inGiven order and only the values("hello", "world")
expect(x) contains o inGiven order and only entry { it isLessThan 2 }
expect(x) contains o inGiven order and only the entries({ it toBe 3 }, { it isLessThan 2 })
expect(x) contains o inGiven order and only grouped entries within group inAny Order(
    value(1), 
    values(1, 2), 
    values(3, 4)
)
expect(x) contains o inGiven order and only grouped entries within group inAny Order(
    entry { it toBe 1 }, 
    entries({ it isLessThan 2 },{ it isGreaterThan 2 }), 
    entries({ it toBe 3 }, { it toBe 4 })
)
```

Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

## Iterable contains not

*atrium-api-fluent-en_GB*
```kotlin
expect(x).containsNot(1.2)
expect(x).containsNot(1.2, 5.7)

expect(x).containsNot.value(null)
expect(x).containsNot.values(null, 1)
expect(x).containsNot.entry { isLessThan(2) }
expect(x).containsNot.entries(null, { isLessThan(2) }, { isGreaterThan 5 })
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) containsNot 1.2
expect(x) containsNot values(1.2, 5.7)

expect(x) containsNot o value null
expect(x) containsNot o the values(null, 1)
expect(x) containsNot o entry { it isLessThan 2 }
expect(x) containsNot o the entries(null, { it isLessThan 2 }, { it isGreaterThan 5 })
```

# Iterable predicate-like assertions
For more sophisticated assertions such as "there should be two matches", use the sophisticated assertion builder `contains.inAnyOrder` 
-&gt; see [Iterable contains in any order](#iterable-contains-in-any-order) for more information 

*atrium-api-fluent-en_GB*
```kotlin
expect(x).any { startsWith("hello") }
expect(x).none { endsWith(".") }
expect(x).all { isNumericallyEqualTo(12.2) }

expect(x).any(null)
expect(x).none(null)
expect(x).all(null)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) any { it startsWith "hello" }
expect(x) none { it endsWith "." }
expect(x) all { it isNumericallyEqualTo 12.2 }

expect(x) any null
expect(x) none null
expect(x) all null
```

# List get
*atrium-api-fluent-en_GB*
```kotlin
expect(x).get(0).isLessThan(1)
expect(x).get(0) { isGreaterThan(1) }

//in case of a nullable element type
expect(x).get(0).toBe(null)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) get 0 isLessThan 1
expect(x) get index(0) { it isGreaterThan 1 }

//in case of a nullable element type
expect(x) get 0 toBe null
```

# Map getExisting
*atrium-api-fluent-en_GB*
```kotlin
expect(x).getExisting("a").isLessThan(1)
expect(x).getExisting("a") { isGreaterThan(1) }

//in case of a nullable value type
expect(x).getExisting("a").notToBeNull { isGreaterThan(1) }
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) getExisting "a" isLessThan 1
expect(x) getExisting key("a") { it isGreaterThan 1 }

//in case of a nullable value type
expect(x) getExisting "a" notToBeNull { it isGreaterThan 1 }
```

# Map contains
*atrium-api-fluent-en_GB*
```kotlin
expect(x).contains("a" to 1)
expect(x).contains("a" to 1, "b" to 2)
expect(x).contains(KeyValue("a") { isGreaterThan(3).and.isLessThan(10) })
expect(x).contains(KeyValue("a") { toBe(2) }, KeyValue("b") { isLessThan(3) })

//in case of a nullable value type
expect(x).contains("a" to null)
expect(x).contains("a" to null, "b" to 2)
expect(x).contains(KeyValue("a", null))
expect(x).contains(
  KeyValue("a", null) 
  KeyValue("b") { isLessThan(2) }
)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) contains ("a" to 1)
expect(x) contains pairs("a" to 1, "b" to 2)
expect(x) contains keyValue("a") { 
  it isGreaterThan 3
  it isLessThan 10 
}
expect(x) contains all(keyValue("a") { it toBe 2 }, keyValue("b") { it isLessThan 3 })

//in case of a nullable value type
expect(x) contains ("a" to null)
expect(x) contains pairs("a" to null, "b" to 2)
expect(x) contains keyValue("a", null)
expect(x) contains all(
  keyValue("a", null), 
  keyValue("b") { it isLessThan 2 }
)
```
