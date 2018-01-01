# List of APIs
Atrium provides different APIs where the API differ in its style and the language in which it is written.
You have the choice which one(s) you want to use. 
Hence it is up to you if you want to mix and match different styles or enforce just one style.

Atrium provides so called bundle-modules which merely bundle dependencies (they do not provide additional functionality).
These modules bundle:
- an API module
- a translation module (the language used in reporting)
- an implementation of the core of Atrium


Following a list of the available bundle-modules. 
The links point to the KDoc of their included API where you find an overview of all available assertion functions of the API.

- [atrium-cc-de_CH-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.de_-c-h/index.html)
- [atrium-cc-en_UK-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.en_-u-k/index.html)
- [atrium-cc-infix-en_UK-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.infix.en_-u-k/index.html)

----

Following an excerpt of a build.gradle file which uses two APIs (see 
[README#Installation](https://github.com/robstoll/atrium/tree/master/README.md#installation)
for the rest):
```
dependencies {
    testCompile "ch.tutteli:atrium-cc-en_UK-robstoll:$atrium_version"
    testCompile "ch.tutteli:atrium-api-cc-infix-en_UK:$atrium_version"
}
```

The first dependency points to a bundle-module, the second one just adds the infix-API in addition.

:warning: if you want to use the same API in different languages, 
then you have to make sure that you exclude all translation modules but one (I suggest you keep the one which is your primary language).
If you forget to do it, then the compiler will complain that you have the same enums multiple times on your classpath.

# Different API styles

Atrium provides different APIs where the API differ in its style and the language in which it is written.
This site focuses on the different styles of APIs and compares their en_UK versions. 
We do not show every single difference but merely where the APIs differ in naming.
For instance, the assertion function `AssertionPlant<Any>.toBe`:

*atrium-api-cc-en_UK*
```kotlin
assert(x).toBe(2)
``` 
*atrium-api-cc-infix-en_UK*
```kotlin
assert(x) toBe 2
``` 

is too similar, we will not list it here (ok, we did now but I guess you get the point).

## Nullable Types

*atrium-api-cc-en_UK*
```kotlin
assert(x).isNull()
assert(x).isNotNull { isLessThan(1) }
```

*atrium-api-cc-infix-en_UK*

```kotlin
assert(x) toBe null
assert(x) notToBeNull { isLessThan(1) }
```

## Empty CharSequence / Collection

*atrium-api-cc-en_UK*
```kotlin
assert(x).isEmpty()
assert(x).isNotEmpty()
```

*atrium-api-cc-infix-en_UK*

```kotlin
assert(x) toBe Empty
assert(x) notToBe Empty
```

## `and` property

*atrium-api-cc-en_UK*
```kotlin
assert(x).isGreaterThan(1).and.isLessThan(10)
assert(x) { /*...*/ } and { /*...*/ }
```

*atrium-api-cc-infix-en_UK*
```kotlin
// does only support the group syntax
assert(x) { /*...*/ } and { /*...*/ }
```

## CharSequence contains

*atrium-api-cc-en_UK*
```kotlin
assert(x).contains("hello", "world")
assert(x).contains.atLeast(1).butAtMost(2).value("hello")
assert(x).contains.exactly(1).values("hello", "robert")
assert(x).contains.atMost(2).regex("h(e|a)llo")
assert(x).contains.ignoringCase.notOrAtMost(1).regex("h(e|a)llo", "[Rr]obert")
assert(x).containsNot.defaultTranslationOf(DescriptionBasic.IS_NOT)
```

*atrium-api-cc-infix-en_UK*
```kotlin
assert(x) contains Values("hello", "world")
assert(x) to contain atLeast 1 butAtMost 2 value "hello"
assert(x) to contain exactly 1 the Values("hello", "robert")
assert(x) to contain atMost 2 regex "h(e|a)llo"
assert(x) to contain ignoring case notOrAtMost 1 the RegularPatterns("h(e|a)llo", "[Rr]obert")
assert(x) notTo contain defaultTranslationOf DescriptionBasic.IS_NOT
```

## Iterable contains in any order

*atrium-api-cc-en_UK*
```kotlin
assert(x).contains(1.2)
assert(x).contains(1.2, 5.7)
assert(x).contains({ isLessThan(2) })
assert(x).contains({ isLessThan(2) }, { isGreaterThan 5 })
assert(x).contains.inAnyOrder.atLeast(1).butAtMost(2).value(3.2)
assert(x).contains.inAnyOrder.exactly(1).values("hello", "robert")
assert(x).contains.inAnyOrder.atMost(2).`object`(y)
assert(x).contains.inAnyOrder.notOrAtMost(2).objects(y, z)
assert(x).contains.inAnyOrder.only.value("hello")
assert(x).contains.inAnyOrder.only.values("hello", "world")
assert(x).contains.inAnyOrder.only.`object`(personA)
assert(x).contains.inAnyOrder.only.objects(personA, personB)
assert(x).contains.inAnyOrder.only.entry { isLessThan(2) }
assert(x).contains.inAnyOrder.only.entries({ toBe(3) }, { isLessThan(2) })
```

*atrium-api-cc-infix-en_UK*
```kotlin
assert(x) contains 1.2
assert(x) contains Values(1.2, 5.7) // or Objects as alternative
assert(x) contains { this isLessThan 2 }
assert(x) contains Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
assert(x) to contain inAny order atLeast 1 butAtMost 2 value 3.2
assert(x) to contain inAny order exactly 1 the Values("hello", "robert")
assert(x) to contain inAny order atMost 2 `object` y
assert(x) to contain inAny order notOrAtMost 2 the Objects(y, z)
assert(x) to contain inAny order but only value "hello")
assert(x) to contain inAny order but only the Values("hello", "world")
assert(x) to contain inAny order but only `object` personA
assert(x) to contain inAny order but only the Objects(personA, personB)
assert(x) to contain inAny order but only entry { this isLessThan 2 } 
assert(x) to contain inAny order but only the Entries({ this toBe 3 }, { this isLessThan 2 })
```

## Iterable contains in any order

*atrium-api-cc-en_UK*
```kotlin
assert(x).containsStrictly(1.2)
assert(x).containsStrictly(1.2, 5.7)
assert(x).containsStrictly({ isLessThan(2) })
assert(x).containsStrictly({ isLessThan(2) }, { isGreaterThan 5 })
assert(x).contains.inOrder.only.value("hello")
assert(x).contains.inOrder.only.values("hello", "world")
assert(x).contains.inOrder.only.`object`(personA)
assert(x).contains.inOrder.only.objects(personA, personB)
assert(x).contains.inOrder.only.entry { isLessThan(2) }
assert(x).contains.inOrder.only.entries({ toBe(3) }, { isLessThan(2) })
```

*atrium-api-cc-infix-en_UK*
```kotlin
assert(x) containsStrictly 1.2
assert(x) containsStrictly Values(1.2, 5.7) // or Objects as alternative
assert(x) containsStrictly { this isLessThan 2 }
assert(x) containsStrictly Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
assert(x) contains inGiven order but only value "hello"
assert(x) contains inGiven order but only the Values("hello", "world")
assert(x) contains inGiven order but only `object` personA
assert(x) contains inGiven order but only the Objects(personA, personB)
assert(x) contains inGiven order but only entry { this isLessThan 2 }
assert(x) contains inGiven order but only the Entries({ this toBe 3 }, { this isLessThan 2 })
```

## Iterable contains not

*atrium-api-cc-en_UK*
```kotlin
assert(x).containsNot(1.2)
assert(x).containsNot(1.2, 5.7)
assert(x).containsNot.entry { isLessThan(2) }
assert(x).containsNot.entries({ isLessThan(2) }, { isGreaterThan 5 })
```

*atrium-api-cc-infix-en_UK*
```kotlin
assert(x) containsNot 1.2
assert(x) containsNot Values(1.2, 5.7) // or Objects as alternative
assert(x) notTo contain entry { this isLessThan 2 }
assert(x) notTo contain the Entries({ this isLessThan 2 }, { this isGreaterThan 5 })
```