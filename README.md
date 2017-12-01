[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium/branches)
[![Coverage](https://codecov.io/github/robstoll/atrium/coverage.svg?branch=master)](https://codecov.io/github/robstoll/atrium?branch=master)

# Atrium
Atrium is an open-source assertion library for Kotlin with a fluent API.

It is designed to support different [APIs](#apis) and different reporting styles.
The core of Atrium as well as the builders to create sophisticated assertions are designed to be extensible and 
thus allow you to extend or replace components easily.  

Atrium currently provides two [APIs](#apis) focusing on usability in conjunction with code completion functionality provided by your IDE.
See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

**Table of Content**
- [Installation](#installation)
- [Examples](#examples)
  - [Single Assertions vs. Assertion Groups](#single-assertions-vs-assertion-groups)
  - [Nullable Variables](#nullable-variables)
  - [Expect an Exception](#expect-an-exception)
  - [Property Assertions](#property-assertions)
  - [Method Assertions](#method-assertions)
  - [Collection Assertions](#collection-assertions)
  - [Further Examples](#further-examples)
- [Write own Assertion Functions](#write-own-assertion-functions)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
- [APIs](#apis)
- [Contribute](#contribute)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [Roadmap](#roadmap)
- [License](#license)

# Installation
Atrium is linked to [jcenter](https://bintray.com/bintray/jcenter?filterByPkgName=atrium)
but can also be retrieved directly from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium). 

gradle: 
```
buildscript {
    ext { atrium_version='0.4.0' }
}
repositories {
    jcenter()
}
dependencies {
    testCompile "ch.tutteli:atrium-cc-en_UK-robstoll:$atrium_version"
}
```

maven:  
Because maven is a bit more verbose than gradle, the example is not listed here but 
an [settings.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/settings.xml) 
is provided to set up the repository as well as an 
[example pom.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/example-pom.xml)
which includes the necessary dependencies.

Next to specifying a dependency to a predefined [API](#apis) you have to [setup your assertion verbs](#use-own-assertion-verbs)
(recommended way) or use the [predefined assertion verbs](#out-of-the-box-assertion-verbs).

That is all, you are all set. The next section shows you how to use Atrium.

# Examples

## Single Assertions vs. Assertion Groups

```kotlin
 // two single assertions
assert(10).isLessThan(5).isGreaterThan(10)
    // assert: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)

// assertion groups
assert(10) {
    isLessThan(5)
    isGreaterThan(10)
}
    // assert: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)
    // ◆ is greater than: 10        (java.lang.Integer <1841396611>)
```

Using the fluent API allows to write the `assert(...)` part only once and to make several single assertions.
So the first statement could also be written as follows:
```kotlin
assert(1).isLessThan(5)
assert(1).isGreaterThan(10)
``` 
Correspondingly, the first assert statement (which does not hold) throws an `AssertionError`. 
In the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated. 

You can use the assertion group syntax which throws an `AssertionError` at the end of the block; 
hence reports that both assertions do not hold.
 
## Nullable Variables
```kotlin
val subtitle : String? = "postulating assertions made easy"
assert(subtitle).isNull()
    // assert: "postulating assertions made easy"        <22600334>
    // ◆ to be: null

assert(subtitle).isNotNull{ contains("atrium") }
    //assert: "postulating assertions made easy"        <651100072>
    //◆ starts with: "atrium"        <222427158>
```
In case a variable has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html) then 
you need to define first, whether you expect it to be `null` or not.
 
## Expect an Exception
```kotlin
expect {
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException> {
    message.contains("name")
}
    // expect the thrown exception: java.lang.IllegalArgumentException: name is empty        (java.lang.IllegalArgumentException <2087885397>)
    // ◆ is a: IllegalStateException (java.lang.IllegalStateException)
    //     ❗❗ Could not evaluate the defined assertion(s) -- the down-cast to java.lang.IllegalStateException failed.
```
The method `toThrow` allows to make the assertion that a given lambda throws a certain exception 
(`IllegalStateException` is expected in the example above). Notice `message` in the 
[assertion group block](#single-assertions-vs-assertion-groups). It is a shortcut for 
`property(it::message).isNotNull()`, which prepares a property assertion (see next section) about `Throwable::message`.
 
## Property Assertions
```kotlin
data class Person(val name: String, val isStudent: Boolean)
val person = Person("Robert", false) 

assert(person) {
    its(subject::name).toBe("Peter")
    property(it::isStudent).isTrue()
}
    // assert: Person(name=Robert, isStudent=false)        (Person <1841396611>)
    // ◆ ▶ name: "Robert"        <1577592551>
    //     ◾ to be: "Peter"        <854587510>
    // ◆ ▶ isStudent: false
    //     ◾ to be: true    
```
There are two assertion functions which allow to make assertions about a particular property or feature 
of a class: `property` and `its`. I recommend to use `property` for properties of type `Boolean` and 
`its` for the rest (`its` is actually just a delegate to `property`). `subject` inside a [assertion group block](#single-assertions-vs-assertion-groups) 
refers to the subject of the assertion (in the example above `subject` refers to `person`).
You can also use `it` is an alias for `subject`. 

The only drawback IMO of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it 
(see `message` in [Expect an Exception](#expect-an-exception) for instance). Yet, I do not recommend to 
write an own assertion function for every single property because renaming this property then should include 
renaming the assertion function. Hence, only write an own assertion function in case you use it a lot.

## Method Assertions
```kotlin
data class Person(val firstName: String, val lastName: String) {
    fun name() = "$firstName $lastName"
    fun nickname(includeLastName: Boolean) = when(includeLastName){
        false -> "Mr. $firstName"
        true -> "$firstName aka. $lastName"
    }
}
val person: Person = Person("Robert", "Stoll")

assert(person) {
    returnValueOf(subject::name).contains("treboR", "llotS")
    returnValueOf(it::nickname, false).toBe("Robert aka. Stoll")
}
    // assert: Person(firstName=Robert, lastName=Stoll)        (Person <1536031937>)
    // ◆ ▶ name(): "Robert Stoll"        <798981583>
    //     ◾ contains: "treboR"        <1954406292>
    //       ⚬ ▶ number of occurrences: 0
    //           ◾ is at least: 1
    //     ◾ contains: "llotS"        <904058452>
    //       ⚬ ▶ number of occurrences: 0
    //           ◾ is at least: 1
    // ◆ ▶ nickname(false): "Mr. Robert"        <29183965>
    //     ◾ to be: "Robert aka. Stoll"        <1427651360>
```

Feature assertions about the return value of a method call on the subject of the assertion can be made 
with the help of `returnValueOf`. There are overloads for up to 5 parameters. You could go on and create 
further overloads for 6 and more parameters but I suggest that you 
[write a specific assertion function](#write-own-assertion-functions) for such a use case instead.
You can use `it` as alternative for `subject` as in [Property Assertions](#property-assertions). 

## Collection Assertions
Atrium provides assertion builders which allow to make sophisticated `contains` 
assertions (the builders actually support `Iterable<T>`).

Following a few examples:

```kotlin
assert(listOf(1, 2, 2, 3)).contains.inAnyOrder.atLeast(2).butAtMost(3).value(3)

    // assert: [1, 2, 2, 3]        (java.util.Arrays$ArrayList <1841396611>) 
    // ◆ contains, in any order: 3        (java.lang.Integer <1577592551>)
    //   ⚬ ▶ number of occurrences: 1
    //       ◾ is at least: 2


assert(listOf(1, 2, 2, 3)).contains.inAnyOrder.only.values(3, 2)    

    // assert: [1, 2, 2, 3]        (java.util.Arrays$ArrayList <1790585734>)
    // ◆ contains only, in any order: 
    //   ✘ an entry which is: 5        (java.lang.Integer <22600334>)
    //   ✔ an entry which is: 2        (java.lang.Integer <1961173763>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 2
    //   ❗❗ mismatches and additional entries detected: 
    //      ⚬ 1        (java.lang.Integer <1202683709>)
    //      ⚬ 2        (java.lang.Integer <1961173763>)
    //      ⚬ 3        (java.lang.Integer <1577592551>)
    
    
assert(listOf(1, 2, 2, 3)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })

    // assert: [1, 2, 2, 3]        (java.util.Arrays$ArrayList <2087885397>)
    // ◆ contains only, in order:  
    //   ✔ ▶ entry 0: 1        (java.lang.Integer <1961173763>)
    //       ◾ an entry which: 
    //         ⚬ is less than: 3        (java.lang.Integer <1577592551>)
    //   ✘ ▶ entry 1: 2        (java.lang.Integer <22600334>)
    //       ◾ an entry which: 
    //         ⚬ is less than: 2        (java.lang.Integer <22600334>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 2
    //         ❗❗ additional entries: 
    //            ⚬ entry 2: 2        (java.lang.Integer <22600334>)
    //            ⚬ entry 3: 3        (java.lang.Integer <1577592551>) 
```  
    
## Further Examples

Atrium supports further assertion builders (e.g, for `CharSequence`) and assertion functions.
Have a look at the [specifications](https://github.com/robstoll/atrium/tree/master/atrium-spec/src/main/kotlin/ch/tutteli/atrium/spec) 
for more examples.
A [catalog of the available assertion functions](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium/index.html) 
can be found in the [KDoc](#kdoc). 

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic functions 
[property](#property-assertions) and [returnValueOf](#method-assertions) 
are not good enough?

Writing one is very simple and a pull request of your new assertion function is welcome.
Following an example:

```kotlin
fun IAssertionPlant<Int>.isEven() = createAndAddAssertion(
    DescriptionBasic.IS, TranslatableRawString(DescriptionIntAssertions.EVEN), { subject % 2 == 0 })

enum class DescriptionIntAssertions(override val value: String) : ISimpleTranslatable {
    EVEN("an even number")
}
```

and its usage:

```kotlin
assert(13).isEven()
    // assert: 13        (java.lang.Integer <1841396611>)
    // ◆ is: an even number
```

# Use own Assertion Verbs

Atrium offers three assertion verbs for the impatient: `assert`, `assertThat` and `expect`. 
However , I suggest that you **use your own assertion verbs** even in the case you name them
`assert`, `assertThat` or `expect`. The benefit will be that you are able to change the
reporting style in the future without modifying existing test code. 
For instance, you could change from same-line to multi-line reporting or 
report not only failing but also successful assertions, change the output language etc.

In order to create an own assertion verb it is sufficient to copy the file content of 
[atriumVerbs.kt](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
paste it in your own atriumVerbs.kt, rename `assert` and `expect` as desired and rename the package to reflect yours.

As you can see, it is up to you if you use the same name for all assertion functions or not 
(Atrium itself uses `expect` to postulate assertions about thrown `Throwable`s and `assert` for other assertions).

## Out of the Box Assertion Verbs
If you still insist of using the provided assertion verbs, then add the following dependency 
to your project in addition (see [Installation](#installation) for the rest of the gradle script).

gradle:
```
dependencies {
    //... see other dependency in the example above
    testCompile "ch.tutteli:atrium-verbs:$atrium_version"
}
```

maven:  
Have a look at the [example pom.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/example-pom.xml).    

# APIs
Atrium supports currently two APIs, one in English and one in German. 
Both have their design focus on interoperability with code completion functionality of your IDE 
-- so that you can just type `.` and let your IDE do some of the work.

Atrium is 
[built up by different modules](https://robstoll.github.io/atrium/latest#/doc/) 
and it is your chose which implementation you want to use. 
Atrium provides two dependencies which bundle implementations so that you just have to have a dependency on that one bundle:

- [atrium-cc-en_UK-robstoll](https://github.com/robstoll/atrium/blob/master/atrium-cc-en_UK-robstoll/build.gradle)
- [atrium-cc-de_CH-robstoll](https://github.com/robstoll/atrium/blob/master/atrium-cc-de_CH-robstoll/build.gradle)
 

# Contribute
You are very welcome to contribute:
- [open an issue](https://github.com/robstoll/atrium/issues/new) or [create a feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature])
- fork the repository and make a pull request
- [ask a question](https://join.slack.com/t/atrium-kotlin/shared_invite/enQtMTk4NTkyODg2OTI5LTVlNjEzNmExN2QyNDIxZWQ4YWNlYTdlNWVhYjNkNzliN2I1OTEzZTA2YzNlYmFlNDg0NGU4MmZhYWE2OWUzMWM)
  so that I better understand where Atrium needs to improve.

# KDoc - Code Documentation
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://robstoll.github.io/atrium/)

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out of the box assertion functions. More functions will follow 
but only if they are used somewhere by someone. So, let me know if you miss something by creating 
a [feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature]).
 
Some assertion functions which I miss myself will follow in the next version. 
They are listed in the [Roadmap](#roadmap) below.

Atrium does especially not support yet:
- assertion functions for floating point numbers (where precision matters)
- infinite `Iterable`s
- assertion functions for `Sequence` (you can use `asIterable` in the meantime)

# Roadmap
I plan that Atrium will support in the future:
- Assertion functions for `Iterable` with nullable elements
- Generating testing reports in html  
- Inclusion of mockito's verify (so that it appears in the report as well)
    
[Feature Requests](https://github.com/robstoll/atrium/issues/new?title=[Feature])
and are very welcome.

# License
Atrium is published under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).