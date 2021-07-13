# List of APIs
Atrium provides different APIs where the API differ in its style and the language in which it is written.
You have the choice which one(s) you want to use. 
Hence, it is up to you if you want to mix and match different styles or enforce just one style.

Atrium provides so called bundle-modules which merely bundle dependencies (they do not provide additional functionality).
These modules bundle:
- an API module
- a translation module (the language used in reporting)
- predefined expectation verbs.

Following a list of the available bundle-modules. 
The links point to the KDoc of their included API where you find an overview of all available expectation functions of the API.

- [atrium-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html)
- [atrium-infix-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.infix.en_-g-b/index.html)

----

Following an excerpt of a build.gradle file which uses twit APIs (see 
[README#Installation](https://github.com/robstoll/atrium/tree/master/README.md#installation)
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
We will not show every single difference but merely where the APIs differ in naming.
For instance, the expectation function `Expect<T>.toEqual`:

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toEqual(2)
``` 
*atrium-api-infix-en_GB*
```kotlin
expect(x) toEqual 2
``` 

is so similar, we will not list it here (ok, we did now, but I guess you get the point).

## Table of Content
- [Empty CharSequence / Collection](#empty-charsequence--collection)
- [`and` property](#and-property)
- [CharSequence contains](#charsequence-contains)
- [Iterable contains](#iterable-contains)
    - [in any order](#iterable-contains-in-any-order)
    - [in order](#iterable-contains-in-order)
- [Iterable contains not](#iterable-contains-not)
- [Iterable predicate-like expectations](#iterable-predicate-like-expectations)
- [List get](#list-get)
- [Map getExisting](#map-getexisting)
- [Map contains](#map-contains)

## Empty CharSequence / Collection

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toBeEmpty()
expect(x).notToBeEmpty()
```

*atrium-api-infix-en_GB*

```kotlin
expect(x) toBe empty
expect(x) notToBe empty
```

## `and` property

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toBeGreaterThan(1).and.toBeLessThan(10)
expect(x) { /*...*/ } and { /*...*/ }
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) toBeGreaterThan 1 and o toBeLessThan 10
expect(x) { /*...*/ } and { /*...*/ }
```

Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

## CharSequence contains

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toContain("hello", "world")
expect(x).toContain.atLeast(1).butAtMost(2).value("hello")
expect(x).toContain.exactly(1).values("hello", "robert")
expect(x).toContain.atMost(2).regex("h(e|a)llo")
expect(x).toContain.atMost(2).regex(Regex("h(e|a)llo"))
expect(x).toContain.ignoringCase.regex("h(e|a)llo", "[Rr]obert")
expect(x).toContain.ignoringCase.notOrAtMost(1).elementsOf(anIterable)
```
Notice that the final steps
`value`, `values` and `regex` 
in the sophisticated expectation building process
are applicable to all shown examples 
(e.g. `exactly(1).values("hello", "robert")` could have been finished with `exactly(1).regex("h(e|a)llo")` as well).


*atrium-api-infix-en_GB*
```kotlin
expect(x) toContain values("hello", "world")
expect(x) toContain o atLeast 1 butAtMost 2 value "hello"
expect(x) toContain o exactly 1 the values("hello", "robert")
expect(x) toContain o atMost 2 regex "h(e|a)llo"
expect(x) toContain o atMost 2 matchFor Regex("h(e|a)llo")
expect(x) toContain o ignoring case notOrAtMost 1 the regexPatterns("h(e|a)llo", "[Rr]obert")
expect(x) toContain o ignoring case notOrAtMost 1 elementsOf anIterable
```
Notice that the final steps 
`value`, `values(...)`, `regex` and `regexPatterns(..)` 
in the sophisticated expectation building process
are applicable to all shown examples 
(e.g. `exactly 1 values("hello", "robert")` could have been finished with `exactly 1 regex "h(e|a)llo"` as well).

## Iterable contains

### Iterable contains in any order

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toContain(1.2)
expect(x).toContain(1.2, 5.7)
expect(x).toContain { toBeLessThan(2) }
expect(x).toContain({ toBeLessThan(2) }, { toBeGreaterThan(5) })

expect(x).toContain.inAnyOrder.atLeast(1).butAtMost(2).value(3.2)
expect(x).toContain.inAnyOrder.exactly(1).values("hello", "robert")
expect(x).toContain.inAnyOrder.atMost(2).entry { toBeLessOrEquals(2) }
expect(x).toContain.inAnyOrder.notOrAtMost(2).entries({ notToEqual(3) }, { toBeGreaterOrEquals(2) })
expect(x).toContain.inAnyOrder.only.value("hello")
expect(x).toContain.inAnyOrder.only.values(personA, personB)
expect(x).toContain.inAnyOrder.only.entry { toBeLessThan(2) }
expect(x).toContain.inAnyOrder.only.entries({ toEqual(3) }, { toBeLessThan(2) })
```
Notice that the final steps 
`value`, `values`, `entry` and `entries` 
in the sophisticated expectation building process
are applicable to all shown examples
(e.g. `butAtMost(2).value(3.2)` could have been finished with `entries(...)` as well)

*atrium-api-infix-en_GB*
```kotlin
expect(x) toContain 1.2
expect(x) toContain values(1.2, 5.7) // or Objects as alternative
expect(x) toContain { it toBeLessThan 2 }
expect(x) toContain entries({ it toBeLessThan 2 }, { it toBeGreaterThan 5 })

expect(x) toContain o inAny order atLeast 1 butAtMost 2 value 3.2
expect(x) toContain o inAny order exactly 1 the values("hello", "robert")
expect(x) toContain o inAny order atMost 2 entry { it toBeLessOrEquals 2 }
expect(x) toContain o inAny order notOrAtMost 2 the entries({ it notToEqual 3 }, { it toBeGreaterOrEquals 2 })
expect(x) toContain o inAny order but only value "hello"
expect(x) toContain o inAny order but only the values(personA, personB)
expect(x) toContain o inAny order but only entry { it toBeLessThan 2 } 
expect(x) toContain o inAny order but only the entries({ it toEqual 3 }, { it toBeLessThan 2 })
```
Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

The final steps `value`, `values(...)`, `entry` and `entries(...)` 
in the sophisticated expectation building process,
are applicable to all shown examples 
(e.g. `butAtMost 2 value 3.2` could have been finished with `entries(...)` as well)

### Iterable contains in order

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toContainExactly(1.2)
expect(x).toContainExactly(1.2, 5.7)
expect(x).toContainExactly({ toBeLessThan(2) })
expect(x).toContainExactly({ toBeLessThan(2) }, { toBeGreaterThan 5 })

expect(x).toContain.inOrder.only.value("hello")
expect(x).toContain.inOrder.only.values("hello", "world")
expect(x).toContain.inOrder.only.entry { toBeLessThan(2) }
expect(x).toContain.inOrder.only.entries({ toBe(3) }, { toBeLessThan(2) })
expect(x).toContain.inOrder.only.grouped.within.inAnyOrder(
    value(1), 
    values(1, 2), 
    values(3, 4)
)
expect(x).toContain.inOrder.only.grouped.within.inAnyOrder(
    entry { toEqual(1) }, 
    entries({ toBeLessThan(2) },{ toBeGreaterThan(2) }), 
    entries({ toEqual(3) }, { toEqual(4) })
)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) toContainExactly 1.2
expect(x) toContainExactly values(1.2, 5.7) // or Objects as alternative
expect(x) toContainExactly { it toBeLessThan 2 }
expect(x) toContainExactly entries({ it isLessThan 2 }, { it toBeGreaterThan 5 })

expect(x) toContain o inGiven order and only value "hello"
expect(x) toContain o inGiven order and only the values("hello", "world")
expect(x) toContain o inGiven order and only entry { it toBeLessThan 2 }
expect(x) toContain o inGiven order and only the entries({ it toBe 3 }, { it toBeLessThan 2 })
expect(x) toContain o inGiven order and only grouped entries within group inAny Order(
    value(1), 
    values(1, 2), 
    values(3, 4)
)
expect(x) toContain o inGiven order and only grouped entries within group inAny Order(
    entry { it toEqual 1 }, 
    entries({ it toBeLessThan 2 },{ it toBeGreaterThan 2 }), 
    entries({ it toEqual 3 }, { it toEqual 4 })
)
```

Note that `o` is a filler object which is only there so that we can turn extension methods without parameters into 
a method with one parameter and thus make it available as infix method.

## Iterable contains not

*atrium-api-fluent-en_GB*
```kotlin
expect(x).notToContain(1.2)
expect(x).notToContain(1.2, 5.7)

expect(x).notToContain.value(null)
expect(x).notToContain.values(null, 1)
expect(x).notToContain.entry { toBeLessThan(2) }
expect(x).notToContain.entries(null, { toBeLessThan(2) }, { toBeGreaterThan 5 })
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) notToContain 1.2
expect(x) notToContain values(1.2, 5.7)

expect(x) notToContain o value null
expect(x) notToContain o the values(null, 1)
expect(x) notToContain o entry { it toBeLessThan 2 }
expect(x) notToContain o the entries(null, { it toBeLessThan 2 }, { it toBeGreaterThan 5 })
```

# Iterable predicate-like expectations
For more sophisticated expectations such as "there should be two matches", use the sophisticated expectation builder `contains.inAnyOrder` 
-&gt; see [Iterable contains in any order](#iterable-contains-in-any-order) for more information 

*atrium-api-fluent-en_GB*
```kotlin
expect(x).toHaveElementsAndAny { toStartWith("hello") }
expect(x).toHaveElementsAndNone { toEndWith(".") }
expect(x).toHaveElementsAndAll { toEqualNumerically(12.2) }

expect(x).toHaveElementsAndAny(null)
expect(x).toHaveElementsAndNone(null)
expect(x).toHaveElementsAndAll(null)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) toHaveElementsAndAny { it toStartWith "hello" }
expect(x) toHaveElementsAndNone { it toEndWith "." }
expect(x) toHaveElementsAndAll { it toEqualNumerically 12.2 }

expect(x) toHaveElementsAndAny null
expect(x) toHaveElementsAndNone null
expect(x) toHaveElementsAndAll null
```

# List get
*atrium-api-fluent-en_GB*
```kotlin
expect(x).get(0).toBesLessThan(1)
expect(x).get(0) { toBeGreaterThan(1) }

//in case of a nullable element type
expect(x).get(0).toEqual(null)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) get 0 toBeLessThan 1
expect(x) get index(0) { it toBeGreaterThan 1 }

//in case of a nullable element type
expect(x) get 0 toEqual null
```

# Map getExisting
*atrium-api-fluent-en_GB*
```kotlin
expect(x).getExisting("a").toBeLessThan(1)
expect(x).getExisting("a") { toBeGreaterThan(1) }

//in case of a nullable value type
expect(x).getExisting("a").notToBeNull { toBeGreaterThan(1) }
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) getExisting "a" toBeLessThan 1
expect(x) getExisting key("a") { it toBeGreaterThan 1 }

//in case of a nullable value type
expect(x) getExisting "a" notToBeNull { it toBeGreaterThan 1 }
```

# Map contains
*atrium-api-fluent-en_GB*
```kotlin
expect(x).toContain("a" to 1)
expect(x).toContain("a" to 1, "b" to 2)
expect(x).toContain(KeyValue("a") { toBeGreaterThan(3).and.toBeLessThan(10) })
expect(x).toContain(KeyValue("a") { toEqual(2) }, KeyValue("b") { toBeLessThan(3) })

//in case of a nullable value type
expect(x).toContain("a" to null)
expect(x).toContain("a" to null, "b" to 2)
expect(x).toContain(KeyValue("a", null))
expect(x).toContain(
  KeyValue("a", null) 
  KeyValue("b") { toBeLessThan(2) }
)
```

*atrium-api-infix-en_GB*
```kotlin
expect(x) toContain ("a" to 1)
expect(x) toContain pairs("a" to 1, "b" to 2)
expect(x) ctoCntain keyValue("a") { 
  it toBeGreaterThan 3
  it toBeLessThan 10 
}
expect(x) toContain keyValues(keyValue("a") { it toEqual 2 }, keyValue("b") { it toBeLessThan 3 })

//in case of a nullable value type
expect(x) toContain ("a" to null)
expect(x) toContain pairs("a" to null, "b" to 2)
expect(x) toContain keyValue("a", null)
expect(x) toContain keyValues(
  keyValue("a", null), 
  keyValue("b") { it toBeLessThan 2 }
)
```
