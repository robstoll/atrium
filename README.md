[![Download](https://api.bintray.com/packages/robstoll/tutteli-jars/atrium/images/download.svg)](https://bintray.com/robstoll/tutteli-jars/atrium/_latestVersion)
[![Slack](https://img.shields.io/badge/Slack-atrium@kotlinlang-blue.svg)](https://kotlinlang.slack.com/messages/C887ZKGCQ)
[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status](https://travis-ci.org/robstoll/atrium.svg?tag=v0.8.0)](https://travis-ci.org/robstoll/atrium/branches)
[![Coverage](https://codecov.io/github/robstoll/atrium/coverage.svg?tag=v0.8.0)](https://codecov.io/github/robstoll/atrium?tag=v0.8.0)
[![security status](https://www.meterian.com/badge/gh/robstoll/atrium/security?tag=v0.8.0)](https://www.meterian.com/report/gh/robstoll/atrium?tag=v0.8.0)
[![stability status](https://www.meterian.com/badge/gh/robstoll/atrium/stability?tag=v0.8.0)](https://www.meterian.com/report/gh/robstoll/atrium?tag=v0.8.0)

# Atrium
Atrium is an open-source multiplatform assertion library for Kotlin with support for JVM, JS and Android.
It is designed to support different [APIs](#apis), different reporting styles and [Internationalization](#internationalization-1) (i18n). 
The project was inspired by AssertJ at first (and was named AssertK) but it moved on and provides now more 
flexibility, features and hints to its users (so to you :wink:).

Atrium is designed to be extensible as well as configurable 
and allows you to extend it with your own assertion functions, customise reporting 
or even replace core components with your own implementation in an easy way.

Atrium currently provides two [API](#apis) styles:
pure fluent and infix where both of them have their design focus on usability in conjunction with code completion functionality provided by your IDE.
See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

**Table of Content**
- [Installation](#installation)
  - [JVM](#jvm)
  - [JS](#js)
  - [Android](#android)
  - [Common](#common)
- [Examples](#examples)
  - [Your First Assertion](#your-first-assertion)
  - [Define Single Assertions or Assertion Groups](#define-single-assertions-or-assertion-groups)
  - [Nullable Types](#nullable-types)
  - [Expect an Exception](#expect-an-exception)
  - [Property Assertions](#property-assertions)
  - [Method Assertions](#method-assertions)
  - [Type Assertions](#type-assertions)
  - [Collection Assertions](#collection-assertions)
    - [Shortcut Functions](#shortcut-functions)
    - [Sophisticated Assertion Builders](#sophisticated-assertion-builders)
  - [Map Assertions](#map-assertions)    
  - [Data Driven Testing](#data-driven-testing)
  - [Further Examples](#further-examples)  
- [How is Atrium different from other Assertion Libraries](#how-is-atrium-different-from-other-assertion-libraries)
- [Write own Assertion Functions](#write-own-assertion-functions)
    - [Boolean based Assertions](#boolean-based-assertions)
    - [Compose Functions](#compose-assertion-functions)
    - [Enhanced Reporting](#enhanced-reporting)
    - [Own Sophisticated Assertion Builders](#own-sophisticated-assertion-builders)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
  - [ReporterBuilder](#reporterbuilder)
- [Internationalization](#internationalization-1)
- [APIs](#apis)
- [Java Interoperability](#java-interoperability)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [FAQ](#faq)
- [Kotlin Bugs](#kotlin-bugs)
- [Roadmap](#roadmap)
- [Contributors and contribute](#contributors-and-contribute)
- [License](#license)

# Installation

## JVM
Atrium is linked to [jcenter](https://bintray.com/bintray/jcenter?filterByPkgName=atrium)
but can also be retrieved directly from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium). 

*gradle*: 
```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testCompile "ch.tutteli.atrium:atrium-cc-en_GB-robstoll:$atrium_version"
}
```
We have defined a dependency to the bundle `atrium-cc-en_GB-robstoll` in the above example 
which provides a pure fluent API (in en_GB) for the JVM platform. 
You have to add `-android` as suffix if you want to use it for an Android project.  

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testCompile("ch.tutteli.atrium:atrium-cc-infix-en_GB-robstoll:$atrium_version") { 
        exclude group: 'ch.tutteli.atrium', module: 'atrium-api-cc-en_GB' 
    }
    testRuntimeOnly("ch.tutteli.atrium:atrium-api-cc-en_GB-robstoll:$atrium_version")
}
```

We have to define an `exclude` due to a [missing feature in gradle](https://guides.gradle.org/migrating-from-maven/#bills_of_materials_boms)
(or you could call it a bug) so that maven dependencies defined with `<scope>runtime</scope>` are treated as compile nonetheless.
If you are using gradle > 4.6, then you can put `enableFeaturePreview("IMPROVED_POM_SUPPORT")` in your settings.gradle
and simplify the dependencies section to the following: 
```
dependencies {
    testCompile "ch.tutteli.atrium:atrium-cc-infix-en_GB-robstoll:$atrium_version" 
}
```

As mentioned above, add `-android` as suffix if you want to use it for an Android project.

<hr/>
</details>

<details>
<summary>click to see how the setup for the fluent API in German looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testCompile("ch.tutteli.atrium:atrium-cc-de_CH-robstoll:$atrium_version") { 
        exclude group: 'ch.tutteli.atrium', module: 'atrium-api-cc-en_GB' 
    }
    testRuntimeOnly("ch.tutteli.atrium:atrium-api-cc-en_GB-robstoll:$atrium_version")
}
```

We have to define an `exclude` due to a [missing feature in gradle](https://guides.gradle.org/migrating-from-maven/#bills_of_materials_boms)
(or you could call it a bug) so that maven dependencies defined with `<scope>runtime</scope>` are treated as compile nonetheless.
If you are using gradle > 4.6, then you can put `enableFeaturePreview("IMPROVED_POM_SUPPORT")` in your settings.gradle
and simplify the dependencies section to the following: 
```
dependencies {
    testCompile "ch.tutteli.atrium:atrium-cc-de_CH-robstoll:$atrium_version" 
}
```

As mentioned above, add `-android` as suffix if you want to use it for an Android project.

<hr/>
</details>
<br/>

*maven*:  
Because maven is a bit more verbose than gradle, the example is not listed here but 
a [settings.xml](https://github.com/robstoll/atrium/tree/v0.8.0/misc/maven/settings.xml) 
is provided to set up the repository as well as an 
[example pom.xml](https://github.com/robstoll/atrium/tree/v0.8.0/misc/maven/example-pom.xml)
which includes the necessary dependencies.

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.


## JS

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testCompile("ch.tutteli.atrium:atrium-cc-en_GB-robstoll-js:$atrium_version")
}
```

We have defined a dependency to the bundle `atrium-cc-en_GB-robstoll-js` in the above example 
which provides a pure fluent API (in en_GB) for the JS platform.

You need to setup an explicit dependency on `atrium-cc-en_GB-robstoll-js` in your test code in order that you can use Atrium.
This is due to the loosely coupled design of Atrium and dead code elimination performed by the Kotlin compiler.
An example of how to setup Atrium in combination with the testing framework mocha is given in 
[misc/examples/js/mocha](https://github.com/robstoll/atrium/tree/v0.0.8-beta/misc/examples/js/mocha).
It also includes an automated way of establishing the dependency to Atrium.

Atrium itself is using mocha as well 
(see [build.gradle -> createJsTestTask](https://github.com/robstoll/atrium/tree/v0.0.8-beta/build.gradle#L290))
and has tests written in JS modules 
(see [AdjustStackTest](https://github.com/robstoll/atrium/tree/v0.8.0/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/ch/tutteli/atrium/core/robstoll/lib/reporting/AdjustStackTest.kt))
as well as tests written in common modules (e.g. [SmokeTest](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-en_GB-robstoll/atrium-cc-en_GB-robstoll-common/src/test/kotlin/SmokeTest.kt))
which are executed on the JS platform as well 
(actually on all platforms -> JVM uses JUnit for this purpose, see 
[build.gradle -> useJupiter](https://github.com/robstoll/atrium/tree/v0.0.8-beta/build.gradle#L342)).

Further examples for other test frameworks can be found in the
[kotlin-examples repo](https://github.com/JetBrains/kotlin-examples/blob/master/gradle/js-tests).
Notice though, that they do not include the automated setup of a dependency to a bundle of Atrium.
Or in other words, you should at least create a gradle task similar to 
[establishDependencyToAtrium](https://github.com/robstoll/atrium/tree/v0.0.8-beta/misc/examples/js/mocha/build.gradle#L85)
or include a [testSetup.kt]((https://github.com/robstoll/atrium/tree/v0.0.8-beta/misc/examples/js/mocha/build.gradle#L80))
file in your test sources.

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testCompile("ch.tutteli.atrium:atrium-cc-infix-en_GB-robstoll-js:$atrium_version") { 
        exclude group: 'ch.tutteli.atrium', module: 'atrium-api-cc-en_GB-js' 
    }
    testRuntimeOnly("ch.tutteli.atrium:atrium-api-cc-en_GB-robstoll-js:$atrium_version")
}
```

We have to define an `exclude` due to a [missing feature in gradle](https://guides.gradle.org/migrating-from-maven/#bills_of_materials_boms)
(or you could call it a bug) so that maven dependencies defined with `<scope>runtime</scope>` are treated as compile nonetheless.
If you are using gradle > 4.6, then you can put `enableFeaturePreview("IMPROVED_POM_SUPPORT")` in your settings.gradle
and simplify the dependencies section to the following: 
```
dependencies {
    testCompile "ch.tutteli.atrium:atrium-cc-infix-en_GB-robstoll-js:$atrium_version" 
}
```

<hr/>
</details>

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.

## Android

The setup for using Atrium in an Android project is basically the same as for the [JVM setup](#jvm), you only need to
suffix the dependency with `-android` in addition. 
For instance `atrium-cc-en_GB-robstoll-android` instead of `atrium-cc-en_GB-robstoll`.

## Common

The setup for using Atrium in a common module of a multiplatform project is basically the same as for the
[JVM setup](#jvm), you only need to suffix the dependency with `-common` in addition. 
For instance `atrium-cc-en_GB-robstoll-common` instead of `atrium-cc-en_GB-robstoll`.

Have a look at [JVM](#jvm), [JS](#js) or [Android](#android) to see how the setup of a specific platform has to be done.


# Examples
We are using the API provided by the bundle module 
[atrium-cc-en_GB-robstoll](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-en_GB-robstoll/atrium-cc-en_GB-robstoll-jvm/build.gradle)
in the following examples. 
It provides a pure fluent API for the JVM platform.
Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/v0.8.0/apis/differences.md)
to see how the infix API looks like, how they differ respectively.

## Your First Assertion
We start off with a simple example:
```kotlin
import ch.tutteli.atrium.verbs.expect
val x = 10
expect(x).toBe(9)
``` 
The statement can be read as "I expect, x to be nine" where an equality check is used (for an identity check, you have to use `isSameAs`). 
Since this is false, an `AssertionError` is thrown with the following message:
```text
expect: 10        (java.lang.Integer <934275857>)
◆ to be: 9        (java.lang.Integer <1364913072>)
```
where `◆ ...` represents a single assertion for the subject (`10` in the above example) of the assertion.
The examples in the following sections include the error message (the output) in the code example itself as comments.

We have used the predefined assertion verb `expect` in the above example which we had to import first. 
We will omit the `import` statement in the remaining examples for brevity. 

**You want to run the example yourself?**
Have a look at the [Installation](#installation) section which explains how to set up a dependency to Atrium.

:information_source: _&lt;- this icon signifies additional information, worth reading IMO but if you are only after code examples,
then you can skip now to the next section (otherwise click on the arrow to expand the section)._<br/> 

<details>
<summary>:information_source: further assertion verbs...</summary>

Atrium provides two further assertion verbs next to `expect` out of the box: `assert` and `assertThat`
which you can import with `import ch.tutteli.atrium.verbs.assert`, `import ch.tutteli.atrium.verbs.assertThat` respectively. 
Yet, you can also define your [own assertion verbs](#use-own-assertion-verbs) if another is your favourite.
<hr/>
</details> 

The next section shows how you can define multiple assertions for the same subject.

## Define Single Assertions or Assertion Groups

```kotlin
 // two single assertions
 
expect(4 + 6).isLessThan(5).isGreaterThan(10)
    // expect: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)
```


Using the fluent API allows you to write the `expect(...)` part only once but making several single assertions for the same 
[subject](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.creating/-base-assertion-plant/subject.html).
The expression which determines the subject of the assertion (`4 + 6` in the above example) is evaluated only once. 

In this sense we could have written it also as follows (which is only the same because `4 + 6` does not have side effects).
```kotlin
expect(4 + 6).isLessThan(5)
expect(4 + 6).isGreaterThan(10)
``` 

Correspondingly, the first `expect` statement (which does not hold) throws an `AssertionError`. 
In the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated at all.

If you want that both assertions are evaluated together, then use the assertion group syntax as follows: 

```kotlin
// assertion group with two assertions

expect(4 + 6) {
    isLessThan(5)
    isGreaterThan(10)
}
    // expect: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)
    // ◆ is greater than: 10        (java.lang.Integer <1841396611>)
```

An assertion group throws an `AssertionError` at the end of its block; hence reports that both assertions do not hold.

You can use `and` as filling element between single assertions and assertion group blocks:
```kotlin
expect(4 + 6).isLessThan(5).and.isGreaterThan(10)

expect(4 + 6) { 
    // ... 
} and { 
    // ...
}
```

<details>
<summary>:information_source: assertion group block techniqually speaking...</summary>
 
An assertion group block is actually nothing else than a [lambda with a receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver)
of type `Assert` (code-ish speaking `Assert<T>.() -> Unit`).
The only thing you need to know about `Assert` at the moment is, that `expect(4 + 6)` creates an `Assert<Int>` 
and that all assertion functions are defined as [extension function](https://kotlinlang.org/docs/reference/extensions.html)
of `Assert`.
Have a look at [writing an own assertion function](#write-own-assertion-functions) to get more information about `Assert`.

</details>

 
## Nullable Types
Let us look at the case where the subject of the assertion has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html).
```kotlin
val slogan1 : String? = "postulating assertions made easy"
expect(slogan1).toBe(null)
    // expect: "postulating assertions made easy"        <22600334>
    // ◆ to be: null
    
val slogan2 : String? = null    
expect(slogan2).toBe("postulating assertions made easy")
    // expect: null
    // ◆ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //   » to be: "postulating assertions made easy"        <461160828>
    
expect(slogan1).notToBeNull { startsWith("atrium") }
    // expect: "postulating assertions made easy"        <651100072>
    // ◆ starts with: "atrium"        <222427158>    
```
On one hand, you can use `toBe` and pass the same type (`String?` in the above example, so in other words either `null` or a `String`).
On the other hand, you can use `notToBeNull` to turn the subject into its non-null version (`String` in the above example)
and then define sub-assertions in the corresponding [assertion group block](#define-single-assertions-or-assertion-groups) 
-- `{ startsWith("atrium") }` in the above example.


Atrium provides one additional function which is intended for [data driven testing](#data-driven-testing) 
involving nullable types.
In case you want to make only simple is-equals-assertions, then you can use `toBe`:
```kotlin
fun myFun(i: Int) = if (i > 0) i.toString() else null

expect("calling myFun with ...") {
    mapOf(
        Int.MIN_VALUE to null,
        -1 to null, 
        0 to null, 
        1 to "1", 
        2 to "2", 
        Int.MAX_VALUE to Int.MAX_VALUE.toString()
    ).forEach { arg, result ->
        returnValueOf(::myFun, arg).toBe(result)
    }
}

    // expect: "calling myFun with ..."        <472654579>
    // ◆ ▶ myFun(-2147483648): null
    //     ◾ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)        
    //       » to be: "min"        <1282788025>
    // ◆ ▶ myFun(0): null
    //     ◾ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //       » to be: "zero"        <519569038>
    // ◆ ▶ myFun(2147483647): "2147483647"        <97730845>
    //     ◾ to be: "max"        <611437735>
```

Yet, if you wish to make sub-assertions on the non-nullable type of the subject, then you can use
`toBeNullIfNullGivenElse` which accepts an assertion creator or `null`.
It is short for `if (assertionCreatorOrNull == null) toBe(null) else notToBeNull(assertionCreatorOrNull)`. 
Following another fictional example which illustrates `toBeNullIfNullGivenElse` (we are reusing `myFun` from above):
```kotlin
expect("calling myFun with ...") {
    mapOf(
        Int.MIN_VALUE to subAssert<String> { contains("min") },
        -1 to null,
        0 to null,
        1 to subAssert { toBe("1") },
        2 to subAssert { endsWith("2") },
        Int.MAX_VALUE to  subAssert { toBe("max") }
    ).forEach { arg, assertionCreatorOrNull ->
        returnValueOf(::myFun, arg).toBeNullIfNullGivenElse(assertionCreatorOrNull)
    }
}

    // expect: "calling myFun with ..."        <1989972246>
    // ◆ ▶ myFun(-2147483648): null
    //     ◾ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //         ❗❗ Could not evaluate the defined assertion(s) -- the down-cast to kotlin.String failed.
    // Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions
    // ◆ ▶ myFun(0): null
    //     ◾ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //       » starts with: "zero"        <1543727556>
    // ◆ ▶ myFun(2147483647): "2147483647"        <401424608>
    //     ◾ to be: "max"        <1348949648>
``` 

<details>
<summary>:information_source: dealing a lot with nullable types from Java...</summary>

... in this case I recommend to have a look at the [Java Interoperability](#java-interoperability) section.

</details>
 
## Expect an Exception
```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException>{}

    // expect the thrown exception: java.lang.IllegalArgumentException: name is empty        (java.lang.IllegalArgumentException <1364913072>)
    // ◆ is a: IllegalStateException (java.lang.IllegalStateException)
```
You can define an `expect` block together with the function `toThrow` to make the assertion that the block throws a certain exception 
(`IllegalStateException` in the example above). 

Moreover, you can define one or more subsequent assertions in the same assertion statement with the help of an 
[assertion group block](#define-single-assertions-or-assertion-groups). 
The subsequent assertions are evaluated in case the expected `Throwable` is thrown and is of the same type as the expected one (or a subtype).
For instance:
```kotlin
expect {
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalArgumentException> {
    message { startsWith("firstName") }
}
    // expect the thrown exception: java.lang.IllegalArgumentException
    // ◆ is a: IllegalStateException (java.lang.IllegalStateException)
    //   » Properties of the unexpected IllegalArgumentException
    //     » message: "name is empty"        <1448061896>
    //     » stacktrace: 
    //       ⚬ TestKt$main$2.invoke(test.kt:23)
    //       ⚬ TestKt$main$2.invoke(test.kt)
    //       ⚬ TestKt.main(test.kt:24)
```
Notice `message` in the 
[assertion group block](#define-single-assertions-or-assertion-groups) 
is a shortcut for `property(subject::message).notToBeNull { ... }`, which creates a property assertion (see next section) 
about `Throwable::message`.  

There is also the counterpart to `toThrow` named `notToThrow`:
```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
}.notToThrow()

    //  expect the thrown exception: java.lang.IllegalArgumentException
    //  ◆ is: not thrown at all
    //    » Properties of the unexpected IllegalArgumentException
    //      » message: "name is empty"        <401424608>
    //      » stacktrace: 
    //        ⚬ TestKt$main$2.invoke(test.kt:23)
    //        ⚬ TestKt$main$2.invoke(test.kt)
    //        ⚬ TestKt.main(test.kt:24)
    //      » cause: java.lang.RuntimeException
    //          » message: "a cause"        <1348949648>
    //          » stacktrace: 
    //            ⚬ TestKt$main$2.invoke(test.kt:23)
    //  	at TestKt.main(test.kt:24)
```
Notice that stacks are filtered so that you only see what is of interest. 
Filtering can be configured via [`ReporterBuilder`](#reporterbuilder) by choosing an appropriate [AtriumErrorAdjuster](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting/-atrium-error-adjuster/index.html). 
Stack frames of Atrium and of test runners (Spek, Kotlintest and JUnit for JVM, mocha for JS) are excluded per default.
[Create a Feature Request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
in case you use a different runner, we can add yours to the list as well. 
 
## Property Assertions
```kotlin
data class Person(val name: String, val isStudent: Boolean)
val myPerson = Person("Robert", false) 

expect(myPerson)
    .property(Person::isStudent)
    .toBe(true) // fails
    .toBe(true) // not evaluated anymore
  
    // expect: Person(name=Robert, isStudent=false)        (Person <1841396611>)
    // ◆ ▶ isStudent: false
    //     ◾ to be: true
```  
You can make assertions about properties of the subject (`myPerson` in the above example) by using `property` and pass a reference of the property to it

In the above example we created two assertions, both for the property `isStudent` of `myPerson`.
A property assertion (more general a feature assertion) is indicated as follows in the output. It starts with a `▶` followed by the feature's name and its actual value.
So the above output can be read as "I expect, Person's property `isStudent` (which is actually `false`) to be `true`. 
The second `toBe(true)` is not evaluated as the first already fails.

You can pass an [assertion group block](#define-single-assertions-or-assertion-groups) as second argument to `property`.
This way assertions within the block get all evaluated 
(see [single assertions vs assertion group blocks](#define-single-assertions-or-assertion-groups) for more information).
Following an example:
```kotlin
expect(myPerson) {
    property(subject::name) {
        startsWith("Pe") // fails
        endsWith("er")   // is evaluated nonetheless
    }
}   
    // expect: Person(name=Robert, isStudent=false)        (Person <1841396611>)
    // ◆ ▶ name: "Robert"        <1818544933>
    //     ◾ starts with: "Pe"        <1793436274>
    //     ◾ ends with: "er"        <572868060>
```
There is another difference we would like to point out in the two examples.
If you use `property` within an [assertion group block](#define-single-assertions-or-assertion-groups) 
-- regardless if you pass an assertion group block to `property` or not --
then you can use `subject` in combination with a [bounded reference](https://kotlinlang.org/docs/reference/reflection.html#bound-function-and-property-references-since-11)
to pass a reference of a property (`subject::name` in the above example) to `property`.
Outside of a block you have to pass a reference in another way, e.g. by passing a class bounded reference
(`Person::isStudent` in the first example).
 
 Atrium provides several shortcuts for commonly used properties so that you can use them instead of writing `property(...)` all the time.
For instance, `message` for Throwable (see [Expect an Exception](#expect-an-exception)), `first` and `second` for `Pair` and others.
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) in case you miss a shortcut.
 
:interrobang: &lt;- _this icon signifies answers/input for advanced users, you might want to skip them if you are new to Atrium._<br/>

<details>
<summary>:interrobang: Wrap each property into an assertion function? </summary>

You might be asking yourself whether it is better to [write an own assertion function](#write-own-assertion-functions) or use `property`. 

The only drawback IMO of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it (as I did with `message`).
Yet, I do not recommend to write an own assertion function for every single property.
I think it makes sense to add one if you use it a lot and (preferably) it is a stable API, because one quickly forgets to 
rename the assertion function if the property as such is renamed (e.g., as part of an IDE refactoring). 
As you can see, you would need to keep the property name and the name of the assertion function in sync to be meaningful 
(otherwise one gets quickly confused or has to remember two names for the same thing). 

</details>

## Method Assertions
```kotlin
data class Person(val firstName: String, val lastName: String) {
    fun fullName() = "$firstName $lastName"
    fun nickname(includeLastName: Boolean) = when(includeLastName){
        false -> "Mr. $firstName"
        true -> "$firstName aka. $lastName"
    }
}
val person = Person("Robert", "Stoll")

expect(person) {
    returnValueOf(subject::fullName) {
        contains("treboR")        // fails
        startsWith("llotS")       // is evaluated nonetheless
    }
    returnValueOf(subject::nickname, false)
      .toBe("Robert aka. Stoll")  // fails
      startsWith("llotS")         // not evaluated anymore
}
    // expect: Person(firstName=Robert, lastName=Stoll)        (Person <168907708>)
    // ◆ ▶ fullName(): "Robert Stoll"        <820537534>
    //     ◾ contains: 
    //       ⚬ value: "treboR"        <1724457619>
    //         ⚬ ▶ number of occurrences: 0
    //             ◾ is at least: 1
    //     ◾ starts with: "llotS"        <858232531>
    // ◆ ▶ nickname(false): "Mr. Robert"        <1325465767>
    //     ◾ to be: "Robert aka. Stoll"        <1021258849>
```
You can make an assertion about a method of the subject (`person` in the above example) 
or rather about the value which is returned when calling the method with some specified arguments. 
Such feature assertions can be made with the help of the assertion function `returnValueOf`. 
There are overloads to support methods with up to 5 parameters (notice, `fullName` has none and `nickname` has one parameter in the above example).

Along the line of [Property Assertions](#property-assertions), you can optionally pass an 
[assertion group block](#define-single-assertions-or-assertion-groups) to `returnvalueOf` (as for `subject::fullName` in the above example) 
so that all assertions within the block are evaluated  
(see [single assertions vs assertion group blocks](#define-single-assertions-or-assertion-groups) for more information).

Atrium provides only a few shortcuts for commonly used methods so far: `List.get` and `Map.getExisting` 
where both include some additional checking (index bound and existence of the key within the map)
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) in case you miss a shortcut. 

<details>
<summary>:interrobang: Write own feature assertion functions with additional checks.</summary>

Atrium provides a feature extractor which allows to make feature assertions in a safe way in case they are only valid for certain input.
In case it is always safe to extract the feature then you can just wrap `returnValueOf` (or `property`) into your custom function.
For instance:
```kotlin
fun Assert<File>.exists() = returnValueOf(File::exists)
```
If not, then use `AssertImpl.feature.extractor`. It is for instance used for [`List.get`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt)

</details>

:poop: &lt;- _this icon signifies a bug in Kotlin which you might encounter as well. 
We try to provide a workaround whenever possible._

<details>
<summary>:poop: using <code>returnValueOf</code> results in an overload ambigouity</summary> 

Unfortunately, due to a [bug in Kotlin](https://youtrack.jetbrains.com/issue/KT-17340)
(please upvote it) you wont be able to use `returnValueOf` for a method which has overloads in certain situations. 
As workaround you can use the domain function `returnValueOfX` where `X` needs to be replaced by the number of arguments expected.
Hopefully you never encounter the bug but in case... following an example:
```kotlin
import ch.tutteli.atrium.domain.builders.AssertImpl
expect(person) {
    AssertImpl.feature.returnValueOf1(this, Person::nickname, arg1= false).toBe("Robert aka. Stoll")
}
```
The output is the same as above. 

In other cases type inference will not be good enough to infer `T` of `Assert<T>.() -> Unit` 
([this bug](https://youtrack.jetbrains.com/issue/KT-24230)).
You can use the helper function `subAssert` in such cases which is merely an identity function. 
As an example, have a look at [FeatureAssertionsClassReferenceSpec](https://github.com/robstoll/atrium/tree/v0.8.0/apis/cc-en_GB/atrium-api-cc-en_GB-jvm/src/test/kotlin/ch/tutteli/atrium/api/cc/en_GB/FeatureAssertionsClassReferenceSpec.kt#L54)

</details> <br/>

<details>
<summary>:interrobang: Why only overloads for up to 5 parameters</summary>
 
You might be asking yourself why I stopped at 5 Parameters.
You could go on and create further overloads for 6 and more parameters, but... uh... can you smell it :stuck_out_tongue_winking_eye:.
In case you have a function with 6 or more parameters and you do not want or cannot to get rid of it, 
then I suggest that you [write a specific assertion function](#write-own-assertion-functions) for it.

</details>

## Type Assertions
```kotlin
interface SuperType
data class SubType1(val number: Int): SuperType
data class SubType2(val word: String): SuperType

val x: SuperType = SubType2("hello")
expect(x).isA<SubType1> {
    property(subject::number).toBe(2)
}
    // expect: SubType2(s=hello)        (ch.tutteli.atrium.SubType2 <2134607032>)
    // ◆ is type or sub-type of: SubType1 (ch.tutteli.atrium.SubType1)
    //    ❗❗ Could not evaluate the defined assertion(s) -- the down-cast to ch.tutteli.atrium.SubType1 failed.
```
You can narrow a type with the `isA` function. 
On one hand it checks that the `subject` of the current assertion (`x` in the above example) is actually the expected type 
and on the other hand it turns the `subject` into this type. 
This way you can make specific assertions which are only possible for the corresponding type
-- for instance, considering the above example, `number` is not available on `SuperType` but only on `SubType1`.

<details>
<summary>:interrobang: How to make arbitrary type transformations?</summary>

Atrium provides the possibility to make arbitrary type transformations 
as long as you can provide a checking function which can tell whether the transformation is safe or not 
and a transformation function which performs the transformation as such.
For an example, have a look at the [TypeTransformationAssertionCreatorSpec](https://github.com/robstoll/atrium/tree/v0.8.0/domain/robstoll-lib/atrium-domain-robstoll-lib-jvm/src/test/kotlin/ch/tutteli/atrium/creating/any/typetransformation/creators/TypeTransformationAssertionCreatorSpec.kt).

Also have a look at feature extraction

</details>


## Collection Assertions

Atrium provides assertion builders which allow to make sophisticated `contains` assertions for `Iterable<T>`.
Such a building process allows you to define very specific assertions, where the process is guided by a fluent builder pattern.
You can either use such an 
[Assertion Builder](#sophisticated-assertion-builders)
to create a specific assertion or one of the 
[Shortcut Functions](#shortcut-functions) in case you have kind of a common case.
The following sub sections show both use cases by examples.

### Shortcut Functions
```kotlin
expect(listOf(1, 2, 2, 4)).contains(2, 3)        

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1448525331>) 
    // ◆ contains, in any order: 3        (java.lang.Integer <1108924067>)
    //   ⚬ ▶ number of occurrences: 0
    //       ◾ is at least: 1
```
 
The assertion function `contains(2, 3)` is a shortcut for using a 
[Sophisticated Assertion Builder](#sophisticated-assertion-builders) -- it actually calls `contains.inAnyOrder.atLeast(1).values(2, 3)`. 
This is reflected in the output, which tells us that we expected that the `number of occurrences` of `3` (which is actually `0`) `is at least: 1`.

<details>
<summary>:information_source: and what about expected value 2?</summary

Exactly, what about the expected value `2`, why do we not see anything about it in the output?
The output does not show anything about the expected value `2` because the predefined assertion verbs have configured [`ReporterBuilder`](#reporterbuilder) 
to use an [Only Failure Reporter](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.core/-core-factory/new-only-failure-reporter.html) 
which shows us only assertions (or sub assertions) which failed.

Back to the shortcut functions.
<hr/>
</details>
 
Next to expecting that certain values are contained in or rather returned by an `Iterable`, 
Atrium allows us to write identification lambdas in form of [assertion group blocks](#define-single-assertions-or-assertion-groups)
(they can also be thought of as matchers / predicates, I prefer the term identification lambdas when we talk about `contains`).
An entry is considered as identified, if it holds all specified assertions of such a block.
Following an example:
```kotlin
expect(listOf(1, 2, 2, 4)).contains({ isLessThan(0) }, { isGreaterThan(2); isLessThan(4) })

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1144068272>) 
    // ◆ contains, in any order:   
    //   ⚬ an entry which:   
    //       » is less than: 0        (java.lang.Integer <1985836631>) 
    //     ⚬ ▶ number of occurrences: 0 
    //         ◾ is at least: 1 
    //   ⚬ an entry which:    
    //       » is greater than: 2        (java.lang.Integer <1948471365>)
    //       » is less than: 4        (java.lang.Integer <1636506029>) 
    //     ⚬ ▶ number of occurrences: 0
    //         ◾ is at least: 1
```
In the above example neither of the two identification lambdas matched any entries and thus both are reported as failing (sub) assertions.

Another `contains` shortcut function which Atrium provides for `Iterable<T>` is kind of the opposite of `inAnyOrder.atLeast(1)` and is named `containsExactly`.
Again, Atrium provides two overloads for it, one for values, e.g. `containsExactly(1, 2)` which calls `contains.inOrder.only.values(1, 2)` and
a second one which expects one or more identification lambdas, e.g. `containsExactly({ isLessThan(0) }, { isGreaterThan(5) })` 
and effectively calls `contains.inOrder.only.entries({ isLessThan(2) }, { isGreaterThan(5) })`.
We will spare the examples here and show them in the following sections.

Atrium provides also a `containsNot` shortcut function. 
Furthermore, it provides aliases for `contains` and `containsNot` named `any` and `none`,  which might be a better 
choice if you think in terms of asserting a predicate holds. These two are completed with an `all` assertion function:
```kotlin
expect(listOf(1, 2, 3, 4)).any { isLessThan(0) }
expect(listOf(1, 2, 3, 4)).none { isGreaterThan(2) }
expect(listOf(1, 2, 3, 4)).all { isGreatherThan(2) }
```

### Sophisticated Assertion Builders

Sophisticated assertion builders implement a fluent builder pattern.
To use the assertion builder for sophisticated `Iterable<T>`-contains-assertions, you can type `contains` 
-- as you would when using the [Shortcut Functions](#shortcut-functions) `contains` -- 
but type `.` as next step (so that you are using the property `contains` instead of one of the shortcut functions). 
Currently, the builder provides two options, either `inAnyOrder` or `inOrder`. 
In case you are using an IDE, you do not really have to think too much -- use code completion; 
the fluent builders will guide you through your decision making :relaxed:

Following on the last section we will start with an `inOrder` example:
```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
 
    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <817978763>)
    // ◆ contains only, in order:     
    //   ✔ ▶ entry 0: 1        (java.lang.Integer <1578009262>)
    //       ◾ an entry which:    
    //         ⚬ is less than: 3        (java.lang.Integer <1108924067>)
    //   ✘ ▶ entry 1: 2        (java.lang.Integer <1948471365>)
    //       ◾ an entry which:    
    //         ⚬ is less than: 2        (java.lang.Integer <1948471365>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 2
    //         ❗❗ additional entries detected:    
    //            ⚬ entry 2: 2        (java.lang.Integer <1948471365>)
    //            ⚬ entry 3: 4        (java.lang.Integer <1636506029>) 
```  

Since we have chosen the `only` option, Atrium shows us a summary where we see three things:
- Whether a specified identification lambda matched (signified by `✔` or `✘`) 
  the corresponding entry or not (e.g. `✘ ▶ entry 1:` was `2` and we expected, it `is less than 2`)
- Whether the expected size was correct or not (`✘ ▶ size:` was `4`, we expected it, `to be: 2` -- see also [Property Assertions](#property-assertions))
- and last but not least, mismatches or additional entries as further clue (`❗❗ additional entries detected`).

:heart_eyes: I am pretty sure you are going to love this feature as well. 
Please star Atrium if you like using it.

<details>
<summary>:interrobang: too verbose?</summary>

As side notice, in case you are dealing with large `Iterable` and do not want such a verbose output, 
then let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]). 
So far the verbose output was always handy for me but you might have other test cases than me.
Also notice, that Atrium cannot yet deal with infinite `Iterable`s.
If you have to, then please open a feature request as well.
<hr/>
</details>

Following one more example for `inOrder` as well as a few examples for `inAnyOrder`. 
I think explanations are no longer required at this stage.
In case you have a question (no matter about which section), then please turn up in the 
[atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet) 
and I happily answer your question there. 

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1362728240>)
    // ◆ contains only, in order:   
    //   ✔ ▶ entry 0: 1        (java.lang.Integer <1578009262>)
    //       ◾ to be: 1        (java.lang.Integer <1578009262>)
    //   ✔ ▶ entry 1: 2        (java.lang.Integer <1948471365>)
    //       ◾ to be: 2        (java.lang.Integer <1948471365>)
    //   ✔ ▶ entry 2: 2        (java.lang.Integer <1948471365>)
    //       ◾ to be: 2        (java.lang.Integer <1948471365>)
    //   ✘ ▶ entry 3: 4        (java.lang.Integer <1636506029>)
    //       ◾ to be: 3        (java.lang.Integer <1108924067>)
    //   ✘ ▶ entry 4: ❗❗ hasNext() returned false
    //       ◾ to be: 4        (java.lang.Integer <1636506029>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 5

expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1092572064>)
    // ◆ contains, in any order:   
    //   ⚬ an entry which:   
    //       » is less than: 3        (java.lang.Integer <1108924067>)
    //     ⚬ ▶ number of occurrences: 3
    //         ◾ is at most: 2


expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
            
    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <922511709>)
    // ◆ contains only, in any order:    
    //   ✔ an entry which is: 1        (java.lang.Integer <1578009262>) 
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>) 
    //   ✘ an entry which is: 3        (java.lang.Integer <1108924067>)  
    //   ✔ an entry which is: 4        (java.lang.Integer <1636506029>) 
    //   ✔ ▶ size: 4  
    //       ◾ to be: 4
    //   ❗❗ following entries were mismatched:    
    //      ⚬ 2        (java.lang.Integer <1948471365>)
    
expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <331994761>)
    // ◆ contains only, in any order:   
    //   ✔ an entry which is: 4        (java.lang.Integer <1636506029>)
    //   ✘ an entry which is: 3        (java.lang.Integer <1108924067>)
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>)
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>)
    //   ✔ an entry which is: 1        (java.lang.Integer <1578009262>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 5
```     

## Map Assertions

```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains("c" to 2, "a" to 1, "b" to 1)

    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains, in any order: 
    //   ⚬ ▶ entry "c": ❗❗ key does not exist
    //         » to be: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "b": 2        (kotlin.Int <970865974>)
    //       ◾ to be: 1        (kotlin.Int <1827171553>)
```
 
Map assertions are kind of very similar to [Collection Assertions](#collection-assertions), also regarding reporting. 
That is the reason why we are not going into too much detail here because we assume you are already familiar with it.

Next to making assertions based on key value pairs one can also define sub assertions for the value of an entry with 
the help of the parameter object `KeyValue`:
```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains(
    KeyValue("c") { toBe(2) },
    KeyValue("a") { isGreaterThan(2) },
    KeyValue("b") { isLessThan(2) }
)   
 
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains, in any order: 
    //   ⚬ ▶ entry "c": ❗❗ key does not exist
    //         » to be: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "a": 1        (kotlin.Int <1827171553>)
    //       ◾ is greater than: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "b": 2        (kotlin.Int <970865974>)
    //       ◾ is less than: 2        (kotlin.Int <970865974>)
```

In case you want to postulate an assertion about a value of one particular key, then you can use `getExisting`.
For instance:
```kotlin
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val children: Collection<Person>
    // ...  and others 
)
val bernstein = Person("Leonard", "Bernstein", 50, children=listOf(/*...*/))

expect(mapOf("bernstein" to bernstein))
    .getExisting("bernstein") { 
        property(subject::firstName).toBe("Leonard")
        property(subject::age).toBe(60) 
    }
    .getExisting("einstein") { 
        property(subject::firstName).toBe("Albert") 
    }
    
    // expect: {bernstein=Person(firstName=Leonard, lastName=Bernstein, age=50, children=[])}        (java.util.Collections.SingletonMap <1389647288>)
    // ◆ ▶ get("bernstein"): Person(firstName=Leonard, lastName=Bernstein, age=50, children=[])        (Person <12209492>)
    //     ◾ ▶ age: 50        (kotlin.Int <314337396>)
    //         ◾ to be: 60        (kotlin.Int <232824863>)
    // ◆ ▶ get("einstein"): ❗❗ key does not exist
    //         ❗❗ Could not evaluate the defined assertion(s) -- given key does not exist.
    // Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions
```


In case you want to make an assertion only about the keys or values of the `Map` then you can use `keys` or `values`:
```kotlin
expect(mapOf("a" to 1, "b" to 2)) {
    keys { all { startsWith("a") } }
    values { none { isGreaterThan(1) } }
}
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <1690101810>)
    // ◆ ▶ keys: [a, b]        (java.util.LinkedHashMap.LinkedKeySet <1502335674>)
    //     ◾ all entries: 
    //         » starts with: "a"        <1517640897>
    //         ❗❗ following entries were mismatched: 
    //            ⚬ index 1: "b"        <2061774051>
    // ◆ ▶ values: [1, 2]        (java.util.LinkedHashMap.LinkedValues <240630125>)
    //     ◾ does not contain: 
    //       ⚬ an entry which: 
    //           » is greater than: 1        (kotlin.Int <851912430>)
    //         ✘ ▶ number of occurrences: 1
    //             ◾ is: 0        (kotlin.Int <586358252>)
    //         ✔ ▶ has at least one element: true
    //             ◾ is: true
``` 

Last but not least, you can use the non-reporting `asEntries()` function which
turns `Assert<Map<K, V>>` into an `Assert<Set<Map.Entry<K, V>>` and thus allows that you can use all the assertion 
functions and sophisticated builders shown in [Collection Assertions](#collection-assertions).

For instance, say you have a `LinkedHashMap` and want to be sure that the order is correct:
```kotlin
expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().contains.inOrder.only.entries(
    { isKeyValue("a", 1) },
    {
        key { startsWith("a") }
        value { isGreaterThan(2) }
    }
)
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains only, in order: 
    //   ✔ ▶ entry 0: a=1        (java.util.LinkedHashMap.Entry <970865974>)
    //       ◾ an entry which: 
    //           » ▶ key: "a"        <1827171553>
    //               ◾ to be: "a"        <1827171553>
    //           » ▶ value: 1        (kotlin.Int <1424482154>)
    //               ◾ to be: 1        (kotlin.Int <1424482154>)
    //   ✘ ▶ entry 1: b=2        (java.util.LinkedHashMap.Entry <1072506992>)
    //       ◾ an entry which: 
    //           » ▶ key: "a"        <1827171553>
    //               ◾ starts with: "a"        <1827171553>
    //           » ▶ value: 1        (kotlin.Int <1424482154>)
    //               ◾ is greater than: 2        (kotlin.Int <1997702454>)
    //   ✔ ▶ size: 2        (kotlin.Int <1997702454>)
    //       ◾ to be: 2        (kotlin.Int <1997702454>)
```
`isKeyValue` as well as `key {}` and `value {}` are assertion functions defined for `Map.Entry<K, V>`.


Following a non-exhaustive list of further functions: `containsKey`/`containsNotKey`, `isEmpty`, `hasSize` ...  
More examples are given at [apis/differences.md](https://github.com/robstoll/atrium/tree/v0.8.0/apis/differences.md)

And in case you should miss an assertion function, then please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
For instance, you might want to upvote [containsInAnyOrderOnly](https://github.com/robstoll/atrium/issues/68)
in case you want this shortcut function as well.

## Data Driven Testing

Atrium is not intended for data driven testing in the narrowed sense in terms that it cannot produce multiple tests.
This is the responsibility of your test runner.
However, Atrium let you define multiple assertions within one test and reports them all if you want.
In this sense it can be used for data driven testing.
This is especially helpful in case your test runner does not support data driven testing (or other mechanisms like hierarchical or dynamic tests).
As an example, Atrium can help you writing data driven tests in a common module of a multi-platform-project.

The trick is to wrap your assertions into an [assertion group block](#define-single-assertions-or-assertion-groups)
and create [Method Assertions](#method-assertions). Following an example:

```kotlin
fun myFun(i: Int) = (i + 97).toChar()

@Test
fun myFun_happyCases() {
    expect("calling myFun with...") {
        mapOf(
            1 to 'a',
            2 to 'c',
            3 to 'e'
        ).forEach { (arg, result) ->
            returnValueOf(::myFun, arg).toBe(result)
        }
    }
}

    // expect: "calling myFun with..."        <1608446010>
    // ◆ ▶ myFun(1): 'b'
    //     ◾ to be: 'a'
    // ◆ ▶ myFun(3): 'd'
    //     ◾ to be: 'e'
```
Depending on the chosen [reporting style](#reporterbuilder) it will only show the failing cases (default behaviour).
That is also the reason why the call of `myFun(2)` is not listed (as the result is `c` as expected).

Please [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
if you want to see a summary, meaning also successful assertions -- I happily add more functionality if it is of use for someone.

Following another example which involves an assertion creator lambda and not only a simple `toBe` check. 
We are going to reuse the `myFun` from above
```kotlin
expect("calling myFun with ...") {
    mapOf(
        1 to subAssert<Char> { isLessThan('f') },
        2 to subAssert { toBe('c') } ,
        3 to subAssert { isGreaterThan('e') }
    ).forEach { (arg, assertionCreator) ->
        returnValueOf(::myFun, arg, assertionCreator)
    }
}
    
    // expect: "calling myFun with ..."        <537548559>
    // ◆ ▶ myFun(3): 'd'
    //     ◾ is greater than: 'e'
```
The example should be self explanatory.
One detail to note though is the usage of `subAssert`. 
It is a helper function which circumvents certain [Kotlin type inference bugs](#kotlin-bugs) (upvote them please).
Writing the same as `mapOf<Int, Assert<Char>.() -> Unit>( 1 to { ... } )` would not work as the type for a lambda 
involved in a Pair is not inferred correctly.

## Further Examples

Atrium supports further assertion builders (e.g, for `CharSequence`) as well as assertion functions which have not been shown in the examples.
Have a look at [apis/differences.md](https://github.com/robstoll/atrium/tree/v0.8.0/apis/differences.md) for a few more examples.
This site contains also a list of all APIs with links to their assertion function catalogs.

You can also have a look at the 
[specifications](https://github.com/robstoll/atrium/tree/v0.8.0/misc/atrium-spec/src/main/kotlin/ch/tutteli/atrium/spec) 
for more examples.

# How is Atrium different from other Assertion Libraries

The following subsections shall give you a quick overview how Atrium differ from other assertion libraries. 

- [Ready to Help](#ready-to-help)
  - [Fluent API with Code Documentation](#1--fluent-api-with-code-documentation)
  - [Additional Information in Failure Reporting](#2--additional-information-in-failure-reporting)
  - [Prevents you from Pitfalls](#3--prevents-you-from-pitfalls)
- [Flexibility](#flexibility)
- [Internationalization](#internationalization)

## Ready to Help
Atrium is designed to help you whenever possible.
I think this is the biggest difference to other assertion libraries and a very handy one indeed.

### 1. Fluent API with Code Documentation
Atrium provides a fluent API where the design focus was put on the interoperability (of the API) with the code completion functionality of your IDE. 
Or in other words, you can always use code completion to get direct help from your IDE.
This experience is improved by providing up-to-date [code documentation](#kdoc) (in form of KDoc) for all assertion functions, 
so that you get the extra help needed.

<details>
<summary>:poop: There is no KDoc for toBe</summary>

There is, but IntelliJ will not show it to you due to [this bug](https://youtrack.jetbrains.com/issue/KT-24836) (please upvote it).
You should be able to see the KDoc of other functions without problems. 
But in case, you can also browse the online documentation, e.g. [KDoc of toBe](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.api.cc.en_-g-b/to-be.html).

</details>

### 2. Additional Information in Failure Reporting
Atrium adds extra information to error messages so that you get quickly a better idea of what went wrong. 
For instance, for the following assertion (which fails) 
```kotlin
expect(listOf(1, 2, 3)).contains.inOrder.only.values(1, 3)
```
Atrium points out which `values` were found, makes an implicit assertion about the size and also states which entries were additionally contained in the list:

```text
expect: [1, 2, 3]        (java.util.Arrays$ArrayList <1287934450>)
◆ contains only, in order: 
  ✔ ▶ entry 0: 1        (java.lang.Integer <6519275>)
      ◾ to be: 1        (java.lang.Integer <6519275>)
  ✘ ▶ entry 1: 2        (java.lang.Integer <692331943>)
      ◾ to be: 3        (java.lang.Integer <692331943>)
  ✘ ▶ size: 3
      ◾ to be: 2
        ❗❗ additional entries detected: 
           ⚬ entry 2: 3        (java.lang.Integer <1741979653>)
```    

Let us have a look at another example.
```kotlin
expect(9.99f).toBeWithErrorTolerance(10.0f, 0.01f)
```
The above assertion looks good at first sight but actually fails (at least on my machine). 
And without some extra information in the output we would believe that there is actually a bug in the assertion library itself.
But Atrium shows where it goes wrong and even gives a possible hint:
```text
expect: 9.99        (java.lang.Float <1287934450>)
◆ to be (error ± 0.01): 10.0        (java.lang.Float <6519275>)
    » failure might be due to using java.lang.Float, see exact check on the next line
    » exact check is |9.989999771118164 - 10.0| = 0.010000228881835938 ≤ 0.009999999776482582
```

One last example. This time about making an assertion that a certain `Throwable` is thrown but the assertion fails because it was the wrong one. 
Atrium comes with a very useful hint, it shows the actual exception. For instance, for:
```kotlin
expect {
  try {
      throw UnsupportedOperationException("not supported")
  } catch(t: Throwable) {
      throw IllegalArgumentException("no no no...", t)
  }
}.toThrow<IllegalStateException> { messageContains("no no no") }
```
the error reporting look as follows:
```text
expect the thrown exception: java.lang.IllegalArgumentException
◆ is a: IllegalStateException (java.lang.IllegalStateException)
  » Properties of the unexpected IllegalArgumentException
    » message: "no no no..."        <834133664>
    » stacktrace: 
      ⚬ TestKt$main$1.invoke(test.kt:12)
      ⚬ TestKt$main$1.invoke(test.kt)
      ⚬ TestKt.main(test.kt:72)
    » cause: java.lang.UnsupportedOperationException
        » message: "not supported"        <985934102>
        » stacktrace: 
          ⚬ TestKt$main$1.invoke(test.kt:10)
```

### 3. Prevents you from Pitfalls
But not enough. There are certain pitfalls when it comes to using an assertion library and Atrium tries to prevent you from those.

For instance, an overload of `toBe` and of `notToBe` for `BigDecimal` was introduced which are both deprecated and throw a `PleaseReplaceException`. 
The reason behind it?
It is very likely that a user actually wants to compare that a certain `BigDecimal` is numerically (not) equal to another `BigDecimal` 
rather than including `BigDecimal.scale` in the comparison.
Accordingly, the deprecation message of `toBe` (`notToBe` alike) explains the problem and suggests to either use `isNumericallyEqualTo` or `isEqualIncludingScale`.
And if the user should decide to use `isEqualIncludingScale` and at some point an assertion fails only due to the comparison of `BigDecimal.scale`
then Atrium reminds us of the possible pitfall. For instance:
```text
expect: 10        (java.math.BigDecimal <1287934450>)
◆ is equal (including scale): 10.0        (java.math.BigDecimal <6519275>)
    » notice, if you used isNumericallyEqualTo then the assertion would have hold.
```

## Flexibility
Another design goal of Atrium was to give you the flexibility needed but still adhere to a concise design. 
First and most importantly, Atrium does not enforce a certain style on your code base. 
Quite the contrary, it gives you the flexibility to [choose a desired name for the assertion verbs](#use-own-assertion-verbs), 
it continues by providing the possibility to configure the [reporting style](#reporterbuilder), 
goes on that you can chose from different [API styles](#apis) 
and ends that you can replace almost all components by other implementations and hook into existing.

So for instance, if you like to use an `infix` API, then use the bundle `atrium-cc-infix-en_GB-robstoll`. 
You prefer pure fluent and do not even want to see infix style in your code, 
then use `atrium-cc-en_GB-robstoll` which provides a pure fluent style API. 

You are free to choose what fits best without introducing ambiguity etc.
You could even mix up different APIs if needed (but not without losing conciseness I guess -- but hey, it is your decision :wink:). 

## Internationalization
The last difference is not yet fully-blown implemented but the design of Atrium has everything needed to go down the planed [Roadmap](#roadmap).
Might well be that this topic is not really a concern of yours; unless...  

- you are using domain-driven-design and would like to adopt the ubiquitous language also to your test code.
- you want to document the results of your defined assertions (in different languages) 

Atrium already supports APIs in two languages, and it is an easy task to translate an API to another language (hello DDD-people :wave: you are good to go).
Moreover, it is already possible to generate the output in a different language than the used API (e.g. code in English but report in German).

Together with the HTML-Report feature (currently missing but will follow) you will be able to generate reports in different languages.
Already the HTML-Report feature as such might be of your interest. 
You can use it to document your user stories etc (almost) for free.
In case you have clients who speak different languages then the HTML-Report together with the i18n feature will be especially helpful. 
I should not go on here, the HTML-Report feature is not yet implemented, but you can see what kind of road I plan to go down to.

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic functions 
[property](#property-assertions) and [returnValueOf](#method-assertions) 
are not good enough?

The following sub sections will show how you can write your own assertion functions. 
A pull request of your new assertion function is very much appreciated.

## Boolean based Assertions

This is kind of the simplest way of defining assertion functions. Following an example:

```kotlin
fun Assert<Int>.isMultipleOf(base: Int) = createAndAddAssertion(
    Untranslatable("is multiple of"), base, { subject % base == 0 })
```
and its usage:

```kotlin
expect(12).isMultipleOf(5)
    // expect: 12        (java.lang.Integer <934275857>)
    // ◆ is multiple of: 5        (java.lang.Integer <1364913072>)
```

Let us see how we actually defined `isMultipleOf`. 
1. *Choose the extension point*: in our example we want to provide the assertion function for `Int`s. 
    Hence we define `isMultipleOf` as [extension function](https://kotlinlang.org/docs/reference/extensions.html) of `Assert<Int>`.

2. *Use the method `createAndAddAssertion`* (which is provided by `Assert`) to create the assertion 
    and add it to `Assert` (so that is also evaluated, creating alone is not enough). 
    The method `createAndAddAssertion` returns the `Assert` making it easy for you to provide a fluent API as well.
 
    The method [createAndAddAssertion](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.creating/-assertion-plant/create-and-add-assertion.html)
    expects:
    - a [Translatable](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting.translating/-translatable/index.html)
      as  description of your assertion.
    - the representation of the expected value.
    - and the actual check as lambda where you typically use the `subject` of the assertion.
     
We used an `Untranslatable` in the above example as first argument because we are not bothered with internationalization at this point
(have a look at [Internationalization](#internationalization-1)).

In most cases you probably use the expected value itself as its representation -- so you pass it as second argument.
And finally you specify the test as such in the lambda passed as third argument.

<details>
<summary>:interrobang: do not access subject expect in the third argument.<br/></summary>

if you do not access `subject` other than in the lambda passed as third argument, 
then you have what I call a `subjectless reporting function`.
This is a good property because it means your function can be used in [explanation groups](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/explanatory-group.html)
without breaking reporting.
For instance, `expect(listOf(1, 2, 5, 8,9)).all { isMultipleOf(2) }` would blow up in the middle of error reporting if we did not adhere to the `subjectless reporting` property.

It is recommended to verify your assertion functions against two abstract specs which are contained in `atrium-spec`.
I will provide more information if someone is interested, please open an issue or contact me on slack.
<hr/>
</details>
<br/>

But not all assertion functions require a value which is somehow compared against the subject 
-- some make an assertion about a property of the `subject` without comparing it against an expected value.
Consider the following assertion function:

```kotlin
fun Assert<Int>.isEven() = createAndAddAssertion(
    DescriptionBasic.IS, RawString.create("an even number"), { subject % 2 == 0 })
```
We are using a [RawString](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting/-raw-string/index.html)
here so that `"an even number"` is not treated as a `String` in reporting.
Also notice, that we are reusing a common description (`DescriptionBasic.IS`) as first argument.
Its usage looks then as follows:

```kotlin
expect(13).isEven()
    // expect: 13        (java.lang.Integer <1841396611>)
    // ◆ is: an even number
```

## Compose Assertion Functions

So far I ran quickly into the situation where I want to compose functions or 
reuse existing functions but with different arguments. 
We will show both use cases here, starting off by composing functions. 

Say you want to build a `isBetween` assertion function for `java.util.Date`, you could write it as follows:
```kotlin
fun Assert<Date>.isBetween(lowerBoundInlc: Date, upperBoundExclusive: Date) = 
    isGreaterOrEquals(lowerBoundInlc).and.isLessThan(upperBoundExclusive)
```
Pretty simply isn't it. 
Notice though, that this function fails fast, which means, the upper bound is not evaluated 
if the assertion about the lower bound already fails. 
You need to use an [assertion group block](#define-single-assertions-or-assertion-groups) 
if you want that both are evaluated:
```kotlin
fun Assert<Date>.isBetween(lowerBoundInclusive: Date, upperBoundExclusive: Date) = and {
    isGreaterOrEquals(lowerBoundInclusive)
    isLessThan(upperBoundExclusive)
}
``` 
Still simple enough.
(As side notice, Atrium tends to use `addAssertionsCreatedBy` internally instead of `and` as `and` delegates to `addAssertionsCreatedBy`).   

So lets move on to an example which is a bit more complicated. Assume the following data class `Person`

```kotlin
data class Person(
  val firstName: String, 
  val lastName: String, 
  val age: Int,
  val children: Collection<Person> 
  // ...  and others 
)
```

Say you want to make an assertion about the number of children a person has:
```kotlin
fun Assert<Person>.hasNumberOfChildren(number: Int) = 
    apply { property(Person::children).hasSize(number) }
```
Two things to notice here: 
1. we make use of a [property assertion]((#property-assertions))
2. We use `apply` so that subsequent assertions are still made on Person and not on `children` 
   (you could also use `return this` instead of `apply`)
 
Its usage is then as follows:
```kotlin
expect(Person("Susanne", "Whitley", listOf()))
  .hasNumberOfChildren(2) 

    // expect: Person(firstName=Susanne, lastName=Whitley, children=[])        (Person <692342133>)
    // ◆ ▶ children: []        (kotlin.collections.EmptyList <654845766>)
    //     ◾ ▶ size: 0        (kotlin.Int <1712536284>)
    //         ◾ to be: 2        (kotlin.Int <2080166188>)    
```
Another example: assert the person has children which are all adults (assuming 18 is the age of majority).
```kotlin
fun Assert<Person>.hasAdultChildren() = apply {
    property(Person::children)
        .all { property(Person::age).isGreaterOrEquals(18) }
}
```
We also use `apply` here for the same reason as above.
We might be tempted to add an additional size check -- because a Person with 0 children does not have adult children --
but we don't have to, as `all` already checks that there is at least one element. 
```
expect: Person(firstName=Susanne, lastName=Whitley, children=[], age=30)        (Person <1870252780>)
◆ ▶ children: []        (kotlin.collections.EmptyList <761960786>)
    ◾ ▶ has at least one element: false
        ◾ is: true
```

If we keep adding assertion functions involving `children` it might be best to provide a shortcut property and function
(assuming the API of Person is stable enough).
```kotlin
val Assert<Person>.children get(): Assert<Collection<Person>> = property(Person::children)
fun Assert<Person>.children(assertionCreator: Assert<Collection<Person>>.() -> Unit): Assert<Person>
    = apply { children.addAssertionsCreatedBy(assertionCreator) }
```
With this, we can write things like:
```kotlin
expect(person)
  .children { // using the fun -> sub-assertions don't fail fast
    none { property(Person::firstName).startsWith("Ro") }
    all { property(Person::lastName).toBe("Stoll") }
  } // subject is still Person here
  .apply {
    children  // using the val -> subsequent assertions are about children and fail fast
      .hasSize(2)
      .any { property(Person::age).isGreaterThan(18) }
  } // subject is still Person here
  .children // using the val -> subsequent assertions are about children and fail fast
      .hasSize(2)
      .contains(...)
```

<hr/>

Enough of feature assertions. Let's move on to an example where we want to reuse an existing function but with different
arguments. Say we have a function which returns a list of first name / last name `Pair`s. 
We want to assert that the pairs contain only the first name / last name pairs of certain `Person` in any order.
[Collection Assertions](#collection-assertions) will help us with this. 
However, `contains.inAnyOrder.values` expects `Pair`s.
So we have to map from `Person` to `Pair` upfront.
As we have a variable length argument list and want to pass it to a variable length argument list, this cannot be done with a simple `map` from Kotlin. 
And it gets worse if we want to use `contains.inAnyOrder.entries` which expects at least one identification lambda (`Assert<T>.() -> Unit`)
because Kotlin cannot infer the types automatically.

`mapArguments` to the rescue, you can write the assertion function as follows:
```kotlin
fun Assert<List<Pair<String, String>>>.areNamesOf(
    person: Person, vararg otherPersons: Person
): Assert<List<Pair<String, String>>> {
    val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
    return contains.inAnyOrder.only.values(pair, *otherPairs)
}
```
As you can see we moved the mapping inside the function so that the consumer of our API can happily use it as follows:
```kotlin
expect(get...WhichReturnsPairs()).areNamesOf(fKafka, eBloch, kTucholsky)
```

Another fictional example, say we want to assert that the pairs have the same initials as the given persons and in the given order.
Which means, this time we need to use identification lambdas. This can be written as follows:
```kotlin
fun Assert<List<Pair<String, String>>>.sameInitialsAs(
    person: Person, vararg otherPersons: Person
): Assert<List<Pair<String, String>>> {
    val (first, others) = mapArguments(person, otherPersons).toAssert<Pair<String, String>> {
        first.startsWith(it.firstName[0].toString())
        second.startsWith(it.lastName[0].toString())
    }
    return contains.inOrder.only.entries(first, *others)
}
``` 

There are a few additional methods which you can call after `mapArguments`.
In case you want to provide your own implementation it suffices to create an 
extension function for [ArgumentMapperBuilder](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.domain.builders.utils/-argument-mapper-builder/index.html). 

## Enhanced Reporting

[Composing assertion functions](#compose-assertion-functions) give already quite a bit of power to an assertion function writer.
Yet, sometimes we would like to create functions which have a better error reporting than the one we get when we compose assertion functions.

[`AssertImpl`](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.domain.builders/-assert-impl/index.html) 
is the entry point in this case.
Its a builder and thus lets you find the functions you need via code completion.

Following a quick overview what it provides:
- all assertion functions on the domain level (what you have seen in [Compose-assertion-functions](#compose-assertion-functions) 
was the API level) so that you can reuse them (e.g. `AssertImpl.collection.hasSize(...)`).
- `AssertImpl.builder` to create different kinds of assertions (see [AssertionBuilder](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) for more information).
- `AssertImpl.changeSubject` which allows to change the subject silently, 
   meaning it does not show up in reporting (e.g. `Assert<Array<out T>>.asIterable()` uses it, see [arrayAssertions](https://github.com/robstoll/atrium/tree/v0.8.0/apis/cc-en_GB/atrium-api-cc-en_GB-common/src/main/kotlin/ch/tutteli/atrium/api/cc/en_GB/arrayAssertions.kt#L17))
- `AssertImpl.collector` which allows to collect assertions - especially helpful in creating explanatory assertions (see [mapAssertions](https://github.com/robstoll/atrium/tree/v0.8.0/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/mapAssertions.kt#L41))
- `AssertImpl.feature.extractor` for feature assertions which are not always save to extract (see [`List.get`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt))   

You can find an example in [floatingPointAssertions](https://github.com/robstoll/atrium/tree/v0.8.0/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/floatingPointAssertions.kt#L33)
which makes use of explanatory assertions as well as providing a failure hint.

Unfortunately I do not have the time to cover all cases, so let me know (e.g. via the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](http://slack.kotlinlang.org/)) if you want to know more.

## Own Sophisticated Assertion Builders

Do you want to write an own sophisticated assertion builder (or extend a current with more options) instead of an assertion function?
Great, I do not provide hands on documentation yet (had only one question about it so far). 
Therefore, please have a look at the implementation, for instance how the sophisticated assertion builders for `Iterable<T>` are defined:
[ch.tutteli.atrium.creating.iterable.contains](https://github.com/robstoll/atrium/tree/v0.8.0/domain/api/atrium-domain-api-common/src/main/kotlin/ch/tutteli/atrium/domain/creating/iterable/contains).
Notice that the implementation supports [Internationalization](#internationalization-1).

I am willing to provide more documentation if you need it (please open an issue). 
In the meantime I might help you via slack, please post your questions in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet).


# Use own Assertion Verbs

Atrium offers three assertion verbs out of the box: `assert`, `assertThat` and `expect`. 

But you can also define your own set of assertion verbs if they do not suite you or if you do not want that all of them are available in your classpath.
In order to create an own assertion verb it is sufficient to:
 1. Copy the file content of [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/v0.8.0/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch/tutteli/atrium/verbs/internal/atriumVerbs.kt)
 2. Create your own atriumVerbs.kt and paste the previously copied content.
 3. Adjust package name and `import`s and rename `assert` and `expect` as desired (you can also leave it that way of course).
 4. Most probably you can remove `AssertionVerbFactory` at the bottom of the file
 5. exclude `atrium-verbs` from your dependencies. 
    Taking the setup shown in the [Installation](#installation) section, you would replace the `dependencies` block as follows:
    ```
    dependencies {
        testCompile("ch.tutteli.atrium:atrium-cc-en_GB-robstoll:$atrium_version") {
            exclude group: 'ch.tutteli.atrium', module: 'atrium-verbs'
        }
    }
    ```

As you can see in [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/v0.8.0/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch/tutteli/atrium/verbs/internal/atriumVerbs.kt), 
it is up to you if you use the same name for all assertion functions or not 
(Atrium itself uses `expect` to postulate assertions about thrown `Throwable`s and `assert` for other assertions).

What are the benefits of creating own assertion verbs:
- you can limit the set of available assertion verbs. <br/>
  Say you want that everyone uses `expect` but not `assertThat`, removing `assertThat` is surely a better option than using a linter.
- you can encapsulate the reporting style. <br/>
  This is especially useful if you have multiple projects and want to have a consistent reporting style.  
  For instance, you could change from same-line to multi-line reporting or report not only failing but also successful assertions, change the output language etc.
  
    <details>
    <summary>:interrobang: where should I put the atriumVerbs.kt?</summary>
    
    I suggest you create an adapter project for Atrium where you specify the assertion verbs. 
    And most likely you will accumulate them with assertion functions which are so common 
    that they appear in multiple projects -- please share them with us (get in touch with us via issue or slack) if they are not of an internal nature :wink:
    
    <hr/>
    </details>
 
 
What are the drawbacks:
- you have to maintain your assertion verbs. That should not be a big deal 
  -- you might have to replace deprecated options by their replacement when you upgrade to a newer Atrium version but that's about it.


## ReporterBuilder

The `ReporterBuilder` lets you choose among different options to configure the style of the reporting.
For instance, in case you are not happy with the predefined bullet points, then you can change them via the `ReporterBuilder`.
Have a look at [atriumVerbs.kt of atrium-api-cc-de_CH](https://github.com/robstoll/atrium/tree/v0.8.0/apis/cc-de_CH/atrium-api-cc-de_CH-jvm/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
where you can find an example.

Or if you prefer multi-line reporting over single-line reporting,
then you can configure `ReporterBuilder` as follows.
Instead of using `.withTextSameLineAssertionPairFormatter()` you choose `withTextNextLineAssertionPairFormatter()`.
The output looks then as follows:
```kotlin
expect(x).toBe(9)
```
Would then look as follows:
```text
expect: 
  10        (java.lang.Integer <934275857>)
◆ to be: 
  9        (java.lang.Integer <1364913072>)
```
instead of:
```
expect: 10        (java.lang.Integer <934275857>)
◆ to be: 9        (java.lang.Integer <1364913072>)
```

You prefer another reporting style but Atrium does not yet support it? 
Please let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).


There are more options to choose from. 
It does not matter if you use your [own assertion verb](#use-own-assertion-verbs) or a predefined one.
You can provide your custom configured `Reporter` by providing a `ReporterFactory`.
This is done via [ServiceLoader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html) -mechanism on JVM 
and by calling `registerService` on JS where the call has to be before your tests run.  
An example for JVM is given in [atriumVerbs.kt of atrium-api-cc-de_CH](https://github.com/robstoll/atrium/tree/v0.8.0/apis/cc-de_CH/atrium-api-cc-de_CH-jvm/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt).
An example of how you can make sure your code is called earlier than the tests run is given in [testSetup.kt of atrium-core-robstoll-lib](https://github.com/robstoll/atrium/tree/v0.8.0/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/testSetup.kt).

# Internationalization

We distinguish between two use cases. 
You might want to generate the [Report](#report) in a different language or/and you might want to use the [API in a different language](#api-in-a-different-language). 

## Report
Following on the example in [Write own Assertion Functions](#write-own-assertion-functions)
we show here how you need to write the `isMultipleOf` function, so that it supports i18n. 
This way the report could be generated in another language.

The difference lies in the first argument passed to `createAndAddAssertion`; 
we do no longer use an `Untranslatable` but a proper `Translatable`. 

```kotlin
fun Assert<Int>.isMultipleOf(base: Int) = createAndAddAssertion(
    DescriptionIntAssertions.IS_MULTIPLE_OF, base, { subject % base == 0 })
    
enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}    
```


Typically you would put `DescriptionIntAssertions` into an own module (jar) 
so that it could be replaced (with zero performance cost) by another language representation.
For instance,
[atrium-cc-en_GB-robstoll-common](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-en_GB-robstoll/atrium-cc-en_GB-robstoll-common/build.gradle)
uses `atrium-translations-en_GB-common` whereas 
[atrium-cc-de_CH-robstoll-common](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-de_CH-robstoll/atrium-cc-de_CH-robstoll-common/build.gradle)
uses `atrium-translations-de_CH-common`.  

<details>
<summary>:interrobang: Using a TranslationSupplier</summary>

Next to providing translations via code you can also use a 
[TranslationSupplier](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting.translating/-translation-supplier/index.html)
based [Translator](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html)
by configuring the [`ReporterBuilder`](#reporterbuilder) accordingly (e.g. use `withDefaultTranslationSupplier` instead of `withoutTranslations`). 
Atrium supports a properties files based `TranslationSupplier` which is more or less what
[ResourceBundle](https://docs.oracle.com/javase/tutorial/i18n/resbundle/propfile.html)
provides out of the box. 
Yet, a `Translator` uses a more enhanced fallback mechanism compared to a `ResourceBundle`. 
For further technical information have a look at the KDoc of [Translator](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html).

Notice, Atrium does not yet support the generation of multiple reports in the same test run. 
This might become handy if you want to generate an HTML report in different languages.   
However, Atrium is designed to support this use case -- if you need this feature, then please let me know it by writing a 
[feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

<hr/>
</details><br/>

Let us rewrite the `isEven` assertion function from the section [Write own Assertion Functions](#write-own-assertion-functions)
as second example:
```kotlin
fun Assert<Int>.isEven() = createAndAddAssertion(
    DescriptionCommon.IS, RawString.create(DescriptionIntAssertions.EVEN), { subject % 2 == 0 })

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
```
Once again we have to wrap the text which we want to be able to exchange with another language into a `Translatable`. 
Since we want that the translation as such is treated as a raw string in reporting, we wrap it into a `RawString` as we did before. 

## API in a different Language

Following on the example in the previous section, 
we want to write `isMultipleOf` in such a way that one cannot only generate a report in a different language
but also that one can use the function itself in a different language. 
Or in other words, provide our API in a different language (the same applies if you want to provide another API style).

We split up the function in two parts: API and implementation 
-- whereas the implementation creates the assertion and the API provides a function for the user (the API as such) and
merely adds the assertion created by the implementation to the `AssertionPlant`.
 
Typically you put the API function in one module (jar) and the implementation in another (so that the API can be exchanged).
In the implementation module we define, what we will call hereafter an impl-function.
We follow the convention that impl-functions are prefixed with `_` 
-- this way the chance that it shows up in code completion, e.g. when a developer starts to type `is`, is very low):
```kotlin
fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion 
    = AssertImpl.builder.createDescriptive(DescriptionIntAssertions.IS_MULTIPLE_OF, base, { plant.subject % base == 0 })
```
Notice that the impl-function is not an extension function as before 
because we do not want to pollute the API of `AssertionPlant<Int>` (of `Assert<Int>` respectively) with this function.
In the above example we created a simple [DescriptiveAssertion](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.assertions/-descriptive-assertion/index.html)
(`createAndAddAssertion` does the same under the hood)
with a test which defines whether the assertion holds as well as a description (`IS_MULTIPLE_OF`) and a representation (`base`).

[`AssertImpl`](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.domain.builders/-assert-impl/index.html)
helps you in writing own assertion functions. 
I suggest you use it as entry point (rather than memorizing different class names), 
it guides you to existing assertion function implementations for different types 
as well as to other builders such as the [`AssertionBuilder`](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html)
which in turn helps you with creating assertions.

In the API module we define the extension function and call the impl-function:
```kotlin
fun Assert<Int>.isMultipleOf(base: Int)
    = addAssertion(_isMultipleOf(this, base))
```
We do no longer have to create the assertion as in the example of
[Write own Assertion Functions](#write-own-assertion-functions).
Therefore we use the `addAssertion` method and call the impl-function which will create the assertion for us.

You are ready to go, creating an API in a different language -- e.g. in German -- is now only a routine piece of work:
```kotlin
fun Assert<Int>.istVielfachesVon(base: Int)
    = addAssertion(_isMultipleOf(this, base))
```

<details>
<summary>:interrobang: Atrium has more layers</summary>

If you have a look at existing assertion functions and try to reach the impl-function from the API
then you will see that they use `AssertImpl` and that a few more indirections were introduced into Atrium. 
An API call looks more or less as follows: <br/>
`API -> AssertImpl -> ServiceLoader -> Service -> Implementation`

The reasons behind this are simple, you could exchange a `Service` with another service if you want.
A service could also reuse parts of the `Implementation` 
(that is why the `Service` delegates to the `Implementation` rather than implementing it itself).

</details>

# APIs
Atrium supports currently two API styles: pure fluent (`cc`) and infix (`cc-infix`) 
where `cc` exists in English and German; `cc-infix` only in English.
All have their design focus on interoperability with code completion (thus `cc`) functionality of your IDE 
-- so that you can let your IDE do some of the work.

Atrium is 
[built up by different modules](https://docs.atriumlib.org/0.8.0/doc/) 
and it is your choice which implementation you want to use. 
Atrium provides three modules which bundle API, translation, domain and core as well as predefined assertion verbs,
so that you just have to have a dependency on that one bundle (kind a bit like a BOM pom in the maven world):

- [atrium-cc-en_GB-robstoll](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-en_GB-robstoll/atrium-cc-en_GB-robstoll-common/build.gradle)
- [atrium-cc-de_CH-robstoll](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-de_CH-robstoll/atrium-cc-de_CH-robstoll-common/build.gradle)
- [atrium-cc-infix-en_GB-robstoll](https://github.com/robstoll/atrium/tree/v0.8.0/bundles/cc-infix-en_GB-robstoll/atrium-cc-infix-en_GB-robstoll-common/build.gradle)

Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/v0.8.0/apis/differences.md)
for more information and to see how the API styles differ.
 

# Java Interoperability
Atrium provides some helper functions in case you have to deal with Java Code where not all types are non-nullable. 
[Platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
are turned into a non-nullable version per default (if possible). 

Yet, that might not be what you want, especially if you know that certain functions return potentially null 
or in other words, the return type of those functions should be treated as nullable in Kotlin. 
Therefore you want to turn the platform type into the nullable version. 

You need to use a cast to do this. But depending on your return type this might be cumbersome especially if you deal with generics. 
Thus, Atrium provides the following functions to ease dealing with Java Code at least for some standard cases:
- [`nullable`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L19)
  turns a type into a nullable type.
- [`nullableContainer`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#40)
  turns an `Iterable` into an iterable with nullable element type, likewise it does the same for `Array`.
- [`nullableKeyMap`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L66)
  turns a `Map` into a map with a nullable key type.
- [`nullableValueMap`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L79)
  turns a `Map` into a map with a nullable value type.
- [`nullableKeyValueMap`](https://github.com/robstoll/atrium/tree/v0.8.0/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L92)
  turns a `Map` into a map with a nullable key and nullable value type. 
 
# KDoc - Code Documentation
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://docs.atriumlib.org/)

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out-of-the-box assertion functions. 
More functions will follow but only if they are used somewhere by someone. 
So, let me know if you miss something by creating a [feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
Some assertion functions which I miss myself will follow in the next version. 
They are listed in the [Roadmap](#roadmap) below.

Atrium does not support (yet):
- infinite `Iterable`s

# FAQ
You find frequently asked questions below.
If your question is not answered below, then please do not hesitate and ask your question in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ).
In case you do not have an account for kotlinlang.slack.com yet, then please [Invite yourself](http://slack.kotlinlang.org/). 

## Are there contains/any/none/all assertions for `Sequence`/`Array`?

Atrium does not provide extension function applicable to `Assert<Sequence<E>>` (or `Array`) directly,
because they would basically duplicate the functions available for `Iterable<E>`.  
However, Atrium provides `asIterable` so that you can turn `Assert<Sequence<E>>` 
into `Assert<Iterable<E>>`. An example:
```kotlin
expect(sequenceOf(1, 2, 3)).asIterable().contains(2)
```
Likewise you can turn an `Assert<Array<E>>`, `Assert<DoubleArray>` etc. into an `Assert<Iterable<E>>`.

<details>
<summary>:interrobang: why do I not see anything about the transformation in reporting?</summary>

`asIterable` uses `AssertImpl.changeSubject` internally which is intended for not showing up in reporting.
If you would like that the transformation is reflected in reporting then you can use a regular feature assertion 
as follows:
```
expect(sequenceOf(1, 2, 3)).returnValueOf(Sequence::asIterable).contains(2)
```

</details>

## Where do I find a list of all available functions?

Atrium provides KDoc for all APIs - have a look at their KDoc:
- [atrium-cc-de_CH-robstoll](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.api.cc.de_-c-h/index.html)
- [atrium-cc-en_GB-robstoll](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.api.cc.en_-g-b/index.html)
- [atrium-cc-infix-en_GB-robstoll](https://docs.atriumlib.org/0.8.0/doc/ch.tutteli.atrium.api.cc.infix.en_-g-b/index.html)

# Kotlin Bugs
The following issues hinder Atrium to progress in certain areas or they are the reason that we cannot use Atrium as intended in all cases. 
Please upvote them (especially if you encouter them yourself):
- [Lower bounds](https://youtrack.jetbrains.com/issue/KT-209), i.a. that functions intended for nullable subject do not show up on non-nullable subjects
- [CTRL+P shows extension functions of unrelated type](https://youtrack.jetbrains.com/issue/KT-29133)
- [Expose @OnlyInputTypes to restrict e.g. toBe](https://youtrack.jetbrains.com/issue/KT-13198)
- [Type inference KFunction overload bug 1](https://youtrack.jetbrains.com/issue/KT-17340)
- [Type inference KFunction overload bug 2](https://youtrack.jetbrains.com/issue/KT-19884)
- [Type inference KProperty/KFunction ambiguity bug](https://youtrack.jetbrains.com/issue/KT-17341)
- [Type inference fails to infer T of KFunction0 for most types](https://youtrack.jetbrains.com/issue/KT-29515)
- [Type inference type parameter bug](https://youtrack.jetbrains.com/issue/KT-12963)
- [Type inference return type bug](https://youtrack.jetbrains.com/issue/KT-24918)
- [Type inference out type parameter bug](https://youtrack.jetbrains.com/issue/KT-18401)
- [Type inference explicit type and overloads](https://youtrack.jetbrains.com/issue/KT-23791)
- [Type inference Pair with receiver type](https://youtrack.jetbrains.com/issue/KT-29129)
- [Overload resolution null bug](https://youtrack.jetbrains.com/issue/KT-6591) (reason why you need to specify what type `null` is in the infix API when using `assert(listOf(...)) contains null`)
- [Extension resolution null as receiver bug](https://youtrack.jetbrains.com/issue/KT-30496) (reason why you need to define that `null to null` is a Pair in the infix API)
- [Overload resolution nullable bug](https://youtrack.jetbrains.com/issue/KT-23768)
- [Overload resolution primitive type bug](https://youtrack.jetbrains.com/issue/KT-24230)
- [Overload resolution function type bug](https://youtrack.jetbrains.com/issue/KT-23883)
- [Overload resolution generic upper bound bug](https://youtrack.jetbrains.com/issue/KT-30235)
- [Gradle runtimeOnly bug](https://youtrack.jetbrains.com/issue/KT-21685) (reason that you see functions from package cc.en_GB when using cc.infix.en_GB)
- [hide function with deprecation level error in code completion](https://youtrack.jetbrains.com/issue/KT-25263)
- [navigate to source or show KDoc for overloaded extension function](https://youtrack.jetbrains.com/issue/KT-24836)

And some features which would be handy
- [Method reference without `this`](https://youtrack.jetbrains.com/issue/KT-22920)
- [Infix function call with type parameters](https://youtrack.jetbrains.com/issue/KT-21593)
- [Extensibility for infix API](https://youtrack.jetbrains.com/issue/KT-27659)
- [Summarising overloads in code completion](https://youtrack.jetbrains.com/issue/KT-25079) 
- [vararg for lambdas](https://youtrack.jetbrains.com/issue/KT-24287)
- [delegate with inline modifier](https://youtrack.jetbrains.com/issue/KT-23241)


# Roadmap

I plan that Atrium is going to support certain features in the future. Following a rough plan (no guarantees).

## 0.9.0
- Prepare the transition to an `Assert<T>` with an invariant `T` (see #56, the current solution with `Assert<out T>` will be deprecated with 1.0.0) 
- Prepare the transition to `feature` instead of `property` and `returnValueOf` (see #40)
- fix verbosity issues in conjunction with feature assertions and explanatory assertion groups.
- Json assertions (state your wishes in #45)
  
## 0.10.0  
- see if we can further improve error reporting in the IDE with the help of opentest4j exceptions.
- Generating testing reports in html.
  - generate multiple reports in the same test run.
  - extension for Spek so that reporting includes the `describe`, `it` etc. 
  
## 0.11.0 (or 1.0.0)  
- Inclusion of mockito's verify (so that it appears in the report as well).
    
Are you missing something else? 
[Feature Requests](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
are very welcome.

# Contributors and contribute

Our thanks go to [code contributors](https://github.com/robstoll/atrium/graphs/contributors) 
as well as other contributors (see acknowledgements in the [release notes](https://github.com/robstoll/atrium/releases)).

You are more than welcome to contribute as well:
- star Atrium if you like it
- [open a bug](https://github.com/robstoll/atrium/issues/new?template=bug_report.md) or [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
- share your ideas via [issue](https://github.com/robstoll/atrium/issues/new) or [slack](https://kotlinlang.slack.com/messages/C887ZKGCQ)
- [ask a question](https://kotlinlang.slack.com/messages/C887ZKGCQ)
  so that I better understand where Atrium needs to improve.
- write a blog post about Atrium (e.g. about a feature you like) or a tutorial (let us know we happily link to your page)
- share your assertion functions with the rest of us by creating a pull request (no need for i18n support or the like, we can augment your pull request).
- have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  if you would like to code (ping me on [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) if there are not any).  

Please have a look at 
[CONTRIBUTING.md](https://github.com/robstoll/atrium/tree/v0.8.0/.github/CONTRIBUTING.md)
for further suggestions and guidelines.

# License
Atrium is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).
