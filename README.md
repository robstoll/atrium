[![Download](https://api.bintray.com/packages/robstoll/tutteli-jars/atrium/images/download.svg)](https://bintray.com/robstoll/tutteli-jars/atrium/_latestVersion "Download from Bintray")
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![atrium @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=atrium&color=blue&logo=slack)](https://kotlinlang.slack.com/messages/C887ZKGCQ "See invitation link under section FAQ")
[![Build Status Travis](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium/branches)
[![Build Status GitHub Actions](https://github.com/robstoll/atrium/workflows/Windows/badge.svg)](https://github.com/robstoll/atrium/actions/)
[![Coverage](https://codecov.io/gh/robstoll/atrium/branch/master/graph/badge.svg)](https://codecov.io/github/robstoll/atrium/branch/master)
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in slack for help")

# <img src="https://raw.githubusercontent.com/robstoll/atrium/gh-pages/logo.svg?sanitize=true" alt="Atrium" title="Atrium"/>
Atrium is an open-source multiplatform assertion library for Kotlin with support for JVM, JS and Android.
It is designed to support multiple [APIs](#api-styles), different error reporting styles and [Internationalization](#internationalization-1) (i18n). 
The project was inspired by AssertJ at first but moved on and provides now more 
flexibility, features and hints to its users (so to you 😉).

Atrium is designed to be extensible as well as configurable 
and allows you to extend it with your own assertion functions, customise reporting 
or even replace core components with your own implementation in an easy way.

Atrium currently provides two [API Styles](#api-styles):
pure fluent and infix where both of them have their design focus on usability in conjunction with code completion functionality provided by your IDE.
See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

----
❗ You are taking a *sneak peek* at the next version. 
Please have a look at the README of the git tag in case you are looking for the documentation of the corresponding version.
For instance, the [README of v0.9.0-alpha](https://github.com/robstoll/atrium/tree/v0.9.0-alpha/README.md).

----

Attention please 😉

----
❗❗ de_CH API users, I drop maintenance for the `cc-de_CH` API with 0.9.0, not introduce `fluent-de_CH` respectively
unless there are voters for [#137](https://github.com/robstoll/atrium/issues/137) in which case I might continue in 0.10.0

----

**Table of Content**
- [Installation](#installation)
  - [JVM](#jvm)
  - [JS](#js)
  - [Android](#android)
  - [Common](#common)
- [Examples](#examples)
  - [Your First Assertion](#your-first-assertion)
  - [Define Single Assertions or Assertion Groups](#define-single-assertions-or-assertion-groups)
  - [Expect an Exception](#expect-an-exception)
  - [Feature Assertions](#feature-assertions)
    - [Property and Method](#property-and-methods)
    - [Arbitrary Features](#arbitrary-features)
  - [Type Assertions](#type-assertions)
  - [Nullable Types](#nullable-types)
  - [Collection Assertions](#collection-assertions)
    - [Shortcut Functions](#shortcut-functions)
    - [Sophisticated Assertion Builders](#sophisticated-assertion-builders)
  - [Map Assertions](#map-assertions)
  - [Path Assertions](#path-assertions)    
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
- [API Styles](#api-styles)
- [Java Interoperability](#java-interoperability)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [FAQ](#faq)
- [Kotlin Bugs](#kotlin-bugs)
- [Roadmap](#roadmap)
- [Contributors and contribute](#contributors-and-contribute)
- [Sponsors](#sponsors)
- [License](#license)

# Installation

## JVM
Atrium is linked to [jcenter](https://bintray.com/bintray/jcenter?filterByPkgName=atrium)
but can also be retrieved directly from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium). 

*gradle*: 
```
buildscript {
    ext { atrium_version='0.9.0-alpha' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "https://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-fluent-en_GB:$atrium_version"
}
```
We have defined a dependency to the bundle `atrium-fluent-en_GB` in the above example 
which provides a pure fluent API (in en_GB) for the JVM platform.   

<details>
<summary>click to see how the setup for the infix API looks like</summary>


The new infix API is not yet available in 0.9.0-alpha. 
[Your help](https://github.com/robstoll/atrium/issues?utf8=%E2%9C%93&q=is%3Aopen+label%3A%22good+first+issue%22++new+infix)
In bringing the new infix API forward is appreciated.

Please use the old API in the meantime. Following an example:

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "https://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-cc-infix-en_GB-robstoll:$atrium_version"
}
```
<hr/>
</details>
<br/>

*maven*:  
Because maven is a bit more verbose than gradle, the example is not listed here but 
a [settings.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/settings.xml) 
is provided to set up the repository as well as an 
[example pom.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/example-pom.xml)
which includes the necessary dependencies.

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.


## JS

```
buildscript {
    ext { atrium_version='0.9.0-alpha' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "https://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:$atrium_version")
}
```

We have defined a dependency to the bundle `atrium-fluent-en_GB-robstoll` in the above example 
which provides a pure fluent API (in en_GB) for the JS platform.

You need to setup an explicit dependency on `atrium-fluent-en_GB-js` in your test code in order that you can use Atrium.
This is due to the loosely coupled design of Atrium and dead code elimination performed by the Kotlin compilerf or JS.
An example of how to setup Atrium in combination with the testing framework mocha is given in 
[samples/js/mocha](https://github.com/robstoll/atrium/tree/master/samples/js/mocha).
It also includes an automated way of establishing the dependency to Atrium.

Atrium itself is using mocha as well 
(see [build.gradle -> createJsTestTask](https://github.com/robstoll/atrium/tree/master/build.gradle#L290))
and has tests written in JS modules 
(see [AdjustStackTest](https://github.com/robstoll/atrium/tree/master/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/ch/tutteli/atrium/core/robstoll/lib/reporting/AdjustStackTest.kt))
as well as tests written in common modules (e.g. [SmokeTest](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB-common/src/test/kotlin/SmokeTest.kt))
which are executed on the JS platform as well 
(actually on all platforms -> JVM uses JUnit for this purpose, see 
[build.gradle -> useJupiter](https://github.com/robstoll/atrium/tree/master/build.gradle#L342)).

Further examples for other test frameworks can be found in the
[kotlin-examples repo](https://github.com/JetBrains/kotlin-examples/blob/master/gradle/js-tests).
Notice though, that they do not include the automated setup of a dependency to a bundle of Atrium.
Or in other words, you should at least create a gradle task similar to 
[establishDependencyToAtrium](https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L85)
or include a [testSetup.kt]((https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L80))
file in your test sources.

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
buildscript {
    ext { atrium_version='0.9.0-alpha' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "https://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-infix-en_GB-js:$atrium_version"
}
```
<hr/>
</details>

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.

## Android

The setup for using Atrium in an Android project is basically the same as for the [JVM setup](#jvm), you only need to
suffix the dependency with `-android` in addition. 
For instance `atrium-fluent-en_GB-android` instead of `atrium-fluent-en_GB`.

## Common

The setup for using Atrium in a common module of a multiplatform project is basically the same as for the
[JVM setup](#jvm), you only need to suffix the dependency with `-common` in addition. 
For instance `atrium-fluent-en_GB-common` instead of `atrium-fluent-en_GB`.

Have a look at [JVM](#jvm), [JS](#js) or [Android](#android) to see how the setup of a specific platform has to be done.


# Examples
We are using the API provided by the bundle module 
[atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB/build.gradle)
in the following examples. 
It provides a pure fluent API for the JVM platform.
Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
to see how the infix API looks like, how they differ respectively.

## Your First Assertion
We start off with a simple example:

<ex-first>

```kotlin
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect

val x = 10
expect(x).toBe(9)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L47)</sub> ↓ <sub>Output</sub>
```text
expect: 10        (kotlin.Int <1234789>)
◆ to be: 9        (kotlin.Int <1234789>)
```
</ex-first>

The statement can be read as "I expect, x to be nine" where an equality check is used (for an identity check, you have to use `isSameAs`). 
Since this is false, an `AssertionError` is thrown with the following message:
```text
expect: 10        (kotlin.Int <934275857>)
◆ to be: 9        (kotlin.Int <1364913072>)
```
where `◆ ...` represents a single assertion for the subject (`10` in the above example) of the assertion.
The examples in the following sections include the error message (the output) in the code example itself as comments.

We are using the API [atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB/build.gradle)
and we have used the predefined assertion verb `expect` in the above example. 
Thus the corresponding imports at the beginning of the file.
We will omit the `import` statements in the remaining examples for brevity. 

**You want to run the example yourself?**
Have a look at the [Installation](#installation) section which explains how to set up a dependency to Atrium.

👓 _&lt;- this icon signifies additional information, worth reading IMO but if you are only after code examples,
then you can skip now to the next section (otherwise click on the arrow to expand the section)._<br/> 

<details>
<summary>👓 further assertion verbs...</summary>

Atrium provides two further assertion verbs next to `expect` out of the box: `assert` and `assertThat`
which you can import with `import ch.tutteli.atrium.api.verbs.assert`, `import ch.tutteli.atrium.api.verbs.assertThat` respectively. 
Yet, you can also [define your own assertion verbs](#use-own-assertion-verbs) if another is your favourite.
<hr/>
</details> 

The next section shows how you can define multiple assertions for the same subject.

## Define Single Assertions or Assertion Groups
<ex-single>

```kotlin
// two single assertions, only first evaluated
expect(4 + 6).isLessThan(5).isGreaterThan(10)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L54)</sub> ↓ <sub>Output</sub>
```text
expect: 10        (kotlin.Int <1234789>)
◆ is less than: 5        (kotlin.Int <1234789>)
```
</ex-single>

Atrium allows you to chain assertions or in other words
you only need to write the `expect(...)` part once and can make several single assertions for the same subject.
The expression which determines the subject of the assertion (`4 + 6` in the above example) is evaluated only once. 

In this sense we could have written it also as follows (which is only the same because `4 + 6` does not have side effects).
```kotlin
expect(4 + 6).isLessThan(5)
expect(4 + 6).isGreaterThan(10)
``` 

Correspondingly, the first `expect` statement (which does not hold) throws an `AssertionError`. 
In the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated at all.

If you want that both assertions are evaluated together, then use the assertion group syntax as follows:
 
<ex-group>

```kotlin
// assertion group with two assertions, both evaluated
expect(4 + 6) {
    isLessThan(5)
    isGreaterThan(10)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L59)</sub> ↓ <sub>Output</sub>
```text
expect: 10        (kotlin.Int <1234789>)
◆ is less than: 5        (kotlin.Int <1234789>)
◆ is greater than: 10        (kotlin.Int <1234789>)
```
</ex-group>

An assertion group throws an `AssertionError` at the end of its block; hence reports that both assertions do not hold.

You can use `and` as filling element between single assertions and assertion group blocks:
```kotlin
expect(4 + 6).isLessThan(5).and.isGreaterThan(10)

expect(4 + 6) { 
    // ... 
} and { // if the previous block fails, then this one is not evaluated
    // ...
}
```
 
## Expect an Exception
<ex-toThrow1>

```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException>()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L67)</sub> ↓ <sub>Output</sub>
```text
expect: () -> kotlin.Nothing        (readme.examples.ReadmeSpec$1$4$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ is instance of type: IllegalStateException (java.lang.IllegalStateException)
      » Properties of the unexpected IllegalArgumentException
        » message: "name is empty"        <1234789>
        » stacktrace: 
          ⚬ readme.examples.ReadmeSpec$1$4$1.invoke(ReadmeSpec.kt:70)
          ⚬ readme.examples.ReadmeSpec$1$4$1.invoke(ReadmeSpec.kt:45)
          ⚬ readme.examples.ReadmeSpec$1$4.invoke(ReadmeSpec.kt:626)
          ⚬ readme.examples.ReadmeSpec$1$4.invoke(ReadmeSpec.kt:45)
```
</ex-toThrow1>

You can define an `expect` block together with the function `toThrow` to make the assertion that the block throws a certain exception 
(`IllegalStateException` in the example above). 

As with all narrowing functions, there are two overloads:
- the first is parameterless and turns only the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.
- the second expects an `assertionCreator` lambda in which you can define sub-assertions. 
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups). 
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  showing some additional context in case of a failure.

The following example uses the first overload

<ex-toThrow2>

```kotlin
expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException>().message.startsWith("firstName")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L74)</sub> ↓ <sub>Output</sub>
```text
expect: () -> kotlin.Nothing        (readme.examples.ReadmeSpec$1$5$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ ▶ message: null
        ◾ is instance of type: String (kotlin.String) -- Class: java.lang.String
```
</ex-toThrow2>

And this one uses the second overload; notice the difference in reporting.

<ex-toThrow3>

```kotlin
expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException> {
    message { startsWith("firstName") }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L80)</sub> ↓ <sub>Output</sub>
```text
expect: () -> kotlin.Nothing        (readme.examples.ReadmeSpec$1$6$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ ▶ message: null
        ◾ is instance of type: String (kotlin.String) -- Class: java.lang.String
          » starts with: "firstName"        <1234789>
```
</ex-toThrow3>

Notice `message` is a shortcut for `feature(Throwable::message).notToBeNull`, which creates a feature assertion (see next section) 
about `Throwable::message`.  

There is also the counterpart to `toThrow` named `notToThrow`:

<ex-notToThrow>

```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
}.notToThrow()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L88)</sub> ↓ <sub>Output</sub>
```text
expect: () -> kotlin.Nothing        (readme.examples.ReadmeSpec$1$7$1 <1234789>)
◆ does not: throw when called
  » Properties of the unexpected IllegalArgumentException
    » message: "name is empty"        <1234789>
    » stacktrace: 
      ⚬ readme.examples.ReadmeSpec$1$7$1.invoke(ReadmeSpec.kt:91)
      ⚬ readme.examples.ReadmeSpec$1$7$1.invoke(ReadmeSpec.kt:45)
      ⚬ readme.examples.ReadmeSpec$1$7.invoke(ReadmeSpec.kt:92)
      ⚬ readme.examples.ReadmeSpec$1$7.invoke(ReadmeSpec.kt:45)
    » cause: java.lang.RuntimeException
        » message: "a cause"        <1234789>
        » stacktrace: 
          ⚬ readme.examples.ReadmeSpec$1$7$1.invoke(ReadmeSpec.kt:91)
```
</ex-notToThrow>

Notice that stacks are filtered so that you only see what is of interest. 
Filtering can be configured via [`ReporterBuilder`](#reporterbuilder) by choosing an appropriate 
[AtriumErrorAdjuster](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-atrium-error-adjuster/index.html). 
Stack frames of Atrium and of test runners (Spek, Kotlintest and JUnit for JVM, mocha for JS) are excluded per default.
[Create a Feature Request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
in case you use a different runner, we can add yours to the list as well. 
 
<a name="property-assertions"></a>
<a name="method-assertions"></a>
## Feature Assertions
Many times you are only interested in certain features of the subject and want to make assertions about them. 

There are different use cases for feature assertions. 
We will start of with properties and method calls and go on with more complicated scenarios.

### Property and Methods
We are using the `data class Person` in the following examples:

<ex-property-methods-single>

```kotlin
data class Person(val firstName: String, val lastName: String, val isStudent: Boolean) {
    fun fullName() = "$firstName $lastName"
    fun nickname(includeLastName: Boolean) = when (includeLastName) {
        false -> "Mr. $firstName"
        true -> "$firstName aka. $lastName"
    }
}

val myPerson = Person("Robert", "Stoll", false)
expect(myPerson)
    .feature({ f(it::isStudent) }) { toBe(true) } // fails, subject still Person afterwards
    .feature { f(it::fullName) }                  // not evaluated anymore, subject String afterwards
    .startsWith("rob")                            // not evaluated anymore
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L107)</sub> ↓ <sub>Output</sub>
```text
expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.ReadmeSpec$1$Person <1234789>)
◆ ▶ isStudent: false
    ◾ to be: true
```
</ex-property-methods-single>

<sub>We are sorry that the syntax is not yet the nicest one. 
We admit that one has to get used to it first and that is a pity. 
Yet, it is due to many [Kotlin Bugs](#kotlin-bugs) standing in the way.</sub>

`feature` has several overloads, we are looking at the one expecting a lambda in which you have to provide a `MetaFeature`.
Creating a `MetaFeature` is done via the function `f` by passing in a 
[bounded reference](https://kotlinlang.org/docs/reference/reflection.html#bound-function-and-property-references-since-11) 
of the corresponding property or method (including arguments if required).
`it` within the `MetaFeature`-provider-lambda refers to the subject of the assertion (`myPerson` in the above example).
Have a look at [Ambiguity Problems](#ambiguity-problems) in case the compiler is not happy (it is most likely due to a Kotlin Bug).

In the above example we created two assertions, one for the property `isStudent` of `myPerson` 
and a second one for the return value of calling `fullName()` on `myPerson`.
A feature assertion is indicated as follows in reporting. 
It starts with a `▶` followed by the feature's name and its actual value.
So the above output can be read as "I expect, Person's property `isStudent` (which is actually `false`) to be `true`. 
The second feature is not shown in reporting as the first already failed and we have chosen to use [single assertions](#define-single-assertions-or-assertion-groups) 
which have fail-fast semantic.

Feature assertions follow the common pattern of having two overloads:
- the first expects only the `MetaFeature`-provider-lambda. 
  This overload narrows the subject to the feature, 
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
  
- the second expects an `assertionCreator` lambda in addition, in which you can define sub-assertions for the feature.
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) or in other words, not-fail fast.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  Moreover, the subject stays the same so that subsequent calls are still about the same subject.
  
  <ex-property-methods-group>
  
  ```kotlin
  expect(myPerson) { // forms an assertion group block
  
      feature({ f(it::firstName) }) { // forms an assertion group block
          startsWith("Pe")            // fails
          endsWith("er")              // is evaluated nonetheless
      }                               // fails as a whole
  
      // still evaluated, as it is in outer assertion group block
      feature { f(it::lastName) }.toBe("Dummy")
  }
  ```
  ↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L116)</sub> ↓ <sub>Output</sub>
  ```text
  expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.ReadmeSpec$1$Person <1234789>)
  ◆ ▶ firstName: "Robert"        <1234789>
      ◾ starts with: "Pe"        <1234789>
      ◾ ends with: "er"        <1234789>
  ◆ ▶ lastName: "Stoll"        <1234789>
      ◾ to be: "Dummy"        <1234789>
  ```
  </ex-property-methods-group>

<br/>

Atrium provides several shortcuts for commonly used properties so that you can use them instead of writing `feature(...)` all the time.
For instance, `message` for Throwable (see [Expect an Exception](#expect-an-exception)), `first` and `second` for `Pair` and others.
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) in case you miss a shortcut.

💬 &lt;- _this icon signifies answers/input for advanced users, you might want to skip them if you are new to Atrium._<br/>

<details>
<summary>💬 Wrap each property into an assertion function? </summary>

You might be asking yourself whether it is better to [write an own assertion function](#write-own-assertion-functions) or use `feature`. 

The only drawback of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it (as we did with `message`).
Yet, we do not recommend to write an own assertion function for every single property.
We think it makes sense to add one if you use it a lot and (preferably) it is a stable API. 
Why not always? Because one quickly forgets to rename the assertion function 
if the property as such is renamed (e.g., as part of an IDE refactoring). 
As you can see, you would need to keep the property name and the name of the assertion function in sync to be meaningful 
(otherwise one gets quickly confused or has to remember two names for the same thing). 

Writing assertion functions for methods is a different story though, especially due to [overload bugs in Kotlin](#kotlin-bugs).
Also, code completion is not yet as good as it should be when it comes to methods. 
Last but not least, in case it is not always safe to call a method (e.g. `List.get` => IndexOutOfBound) then it makes
sense to wrap it into an assertion function and use `ExpectImpl.feature.extractor` instead.

</details>
  
Last but not least, let us have a look at an example where a method with arguments is used as feature:

<ex-methods-args>

```kotlin
expect(myPerson)
    .feature { f(it::nickname, false) } // subject narrowed to String
    .toBe("Robert aka. Stoll")  // fails
    .startsWith("llotS")         // not evaluated anymore
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L130)</sub> ↓ <sub>Output</sub>
```text
expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.ReadmeSpec$1$Person <1234789>)
◆ ▶ nickname(false): "Mr. Robert"        <1234789>
    ◾ to be: "Robert aka. Stoll"        <1234789>
```
</ex-methods-args>

`f` supports methods with up to 5 arguments.

<details>
<summary>💬 Why only overloads for up to 5 parameters</summary>
 
You might be asking yourself why we stopped at 5 Pprameters.
You could go on and create further overloads for 6 and more parameters, but... uh... can you smell it 😜.
In case you have a function with 6 or more parameters and you do not want or cannot to get rid of it, 
then we suggest that you [write a specific assertion function](#write-own-assertion-functions) for it.

</details>


Atrium provides shortcuts for commonly used methods - so far only: `List.get` and `Map.getExisting` 
where both include some additional checking (index bound and existence of the key within the map)
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) 
in case you miss a shortcut. 

<details>
<summary>💬 Write own feature assertion functions with additional checks.</summary>

Atrium provides a feature extractor which allows to make feature assertions in a safe way in case they are only valid for certain input.
See `ExpectImpl.feature.extractor`. It is for instance used for [`List.get`](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt)

</details>

### Arbitrary Features
A feature does not necessarily have to be directly related to the subject as properties or method calls do.
You can use the overload which expects a feature description in form of a `String` as first argument instead.
Following an example:

<ex-arbitrary-features>

```kotlin
data class FamilyMember(val name: String)

data class Family(val members: List<FamilyMember>)

val myFamily = Family(listOf(FamilyMember("Robert")))
expect(myFamily)
    .feature("number of members", { members.size }) { toBe(1) } // subject still Family afterwards
    .feature("first member's name") { members.first().name }    // subject narrowed to String
    .toBe("Peter")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L147)</sub> ↓ <sub>Output</sub>
```text
expect: Family(members=[FamilyMember(name=Robert)])        (readme.examples.ReadmeSpec$1$Family <1234789>)
◆ ▶ first member's name: "Robert"        <1234789>
    ◾ to be: "Peter"        <1234789>
```
</ex-arbitrary-features>

Also this version of `feature` provides two different kind of overloads:
- the first expects a feature description and a feature-provider-lambda
  This overload narrows the subject to the feature, 
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
  
- the second expects an `assertionCreator` lambda in addition, in which you can define sub-assertions for the feature.
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) or in other words, not-fail fast.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  Moreover, the subject stays the same so that subsequent calls are still about the same subject.

As you can see, Atrium provides a generic way to postulate assertions about features. 
Yet, if you use such feature assertion often or it gets more complicated, 
then it might be worth to [write an own assertion function](#write-own-assertion-functions).

### Within Assertion Functions

In case you write an own assertion function, then we discourage to use the `MetaFeature`-provider-lambda 
(as shown in [Property and Methods](#property-and-methods))
but encourage to pass a [class references](https://kotlinlang.org/docs/reference/reflection.html#class-references)
to `feature` instead.
This has the benefit, that we can always show the feature name, also in case a previous feature extraction or subject
transformation failed.
Following an example: 

<ex-within-assertion-functions>

```kotlin
fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
    feature({ f(it::first) }) { toBe(expected) }

fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
    feature(Pair<F, *>::first) { toBe(expected) }

expect(listOf(1 to "a", 2 to "b")).get(10) {
    firstToBeDoneWrong(1)
    firstToBe(1)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L163)</sub> ↓ <sub>Output</sub>
```text
expect: [(1, a), (2, b)]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ get(10): ❗❗ index out of bounds
      » ▶ CANNOT show description as it is based on subject which is not defined: CANNOT evaluate representation as it is based on subject which is not defined.
            » to be: 1        (kotlin.Int <1234789>)
      » ▶ first: CANNOT evaluate representation as it is based on subject which is not defined.
            » to be: 1        (kotlin.Int <1234789>)
```
</ex-within-assertion-functions>

Also this version of `feature` provides to kind of overloads, one without and and with `assertionCreator`-lambda.
(see for instance [Arbitrary Features](#arbitrary-features) for more information).

### Ambiguity Problems
Unfortunately there are several Kotlin bugs when it comes to overloading, especially in conjunction with `KFunction`
(see [Kotlin Bugs](#kotlin-bugs) and upvote in case you run into one).
However, Atrium provides alternative functions next to `f` within the `MetaFeature`-provider-lambda to disambiguate the situation.
Use `p` for properties and `f0` to `f5` for methods. 
Likely you need to specify the type parameters manually as Kotlin is not able to infer them correctly.

<code-ambiguity-problems>

```kotlin
class WorstCase {
    val propAndFun: Int = 1
    fun propAndFun(): Int = 1

    fun overloaded(): Int = 1
    fun overloaded(b: Boolean): Int = 1
}

expect(WorstCase()) {
    feature { p<Int>(it::propAndFun) }
    feature { f0<Int>(it::propAndFun) }
    feature { f0<Int>(it::overloaded) }
    feature { f1<Boolean, Int>(it::overloaded, true) }.toBe(1)
}
```
</code-ambiguity-problems>

Notice, that you might run into the situation that Intellij is happy but the compiler is not.
For instance, Intellij will suggest that you can remove the type parameters in the above example. 
Yet, if you do so, then the compiler will fail, mentioning ambiguous overloads. 
Most of the time this problem stems from the reason that the new type inference algorithm is used per default within Intellij 
(see File | Settings | Build, Execution, Deployment | Compiler | Kotlin Compiler => Enable new type inference...)

Next to using the alternative functions, you could also use the syntax for [arbitrary features](#arbitrary-features) 
to circumvent the problem -- up to you.

### Property does not exist

In case you deal with Java code, then you might run into the problem that a property does not exist. 
This is due to the fact that Kotlin only provides syntactic sugar to access a getter via property syntax. 
In such a case, use the `get...` method instead. For instance:
```java
// java
class A { 
    public String getFoo() { return "bar"; } 
}
```
```kotlin
// kotlin
val a = A()
a.foo // syntactic sugar, accesses getFoo via property
expect(a)
    // feature{ f(it::foo) }    // would result in a compile error
    .feature { f(it::getFoo) }  // works
    .startsWith(...)
``` 

## Type Assertions

<ex-type-assertions-1>

```kotlin
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
expect(x).isA<SubType1>()
    .feature { f(it::number) }
    .toBe(2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L196)</sub> ↓ <sub>Output</sub>
```text
expect: SubType2(word=hello, flag=true)        (readme.examples.SubType2 <1234789>)
◆ is instance of type: SubType1 (readme.examples.SubType1)
```
</ex-type-assertions-1>

You can narrow a type with the `isA` function. 
On one hand it checks that the subject of the current assertion (`x` in the above example) is actually the expected type 
and on the other hand it turns the subject into this type. 
This way you can make specific assertions which are only possible for the corresponding type
-- for instance, considering the above example, `number` is not available on `SuperType` but only on `SubType1`.

<ex-type-assertions-2>

```kotlin
expect(x).isA<SubType2> {
    feature { f(it::word) }.toBe("goodbye")
    feature { f(it::flag) }.toBe(false)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L202)</sub> ↓ <sub>Output</sub>
```text
expect: SubType2(word=hello, flag=true)        (readme.examples.SubType2 <1234789>)
◆ ▶ word: "hello"        <1234789>
    ◾ to be: "goodbye"        <1234789>
◆ ▶ flag: true
    ◾ to be: false
```
</ex-type-assertions-2>

There are two `isA` overloads: 
- the first (shown in the first example) is parameterless and turns only the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.
- the second (shown in the second example) expects an `assertionCreator` lambda in which you can define sub-assertions. 
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) 
  -- as a recapitulation, assertions in an assertion group block are all evaluated and failures are reported at the end of the block.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  showing some additional context in case of a failure.

<details>
<summary>💬 How to make arbitrary type transformations?</summary>

Atrium provides the possibility to make arbitrary subject transformations 
as long as you can provide a checking function which can tell whether the transformation is safe or not 
and a transformation function which performs the transformation as such.
For an example, have a look at the [EitherSpec](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/test/kotlin/ch/tutteli/atrium/domain/builders/creating/EitherSpec.kt).

</details>

## Nullable Types
Let us look at the case where the subject of the assertion has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html).

<ex-nullable-1>

```kotlin
val slogan1: String? = "postulating assertions made easy"
expect(slogan1).toBe(null)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L228)</sub> ↓ <sub>Output</sub>
```text
expect: "postulating assertions made easy"        <1234789>
◆ to be: null
```
</ex-nullable-1>

<ex-nullable-2>

```kotlin
val slogan2: String? = null
expect(slogan2).toBe("postulating assertions made easy")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L232)</sub> ↓ <sub>Output</sub>
```text
expect: null
◆ to be: "postulating assertions made easy"        <1234789>
```
</ex-nullable-2>

On one hand, you can use `toBe` and pass the same type -- 
`String?` in the above example, so in other words either `null` as in the first example or a `String` as in the second example.
On the other hand, you can use `notToBeNull` to turn the subject into its non-null version.
This is a shortcut for `isA<Xy>` where `Xy` is the non-nullable type (see [Type Assertions](#type-assertions)).
Following an example:

<ex-nullable-3>

```kotlin
expect(slogan2)     // subject has type String?
    .notToBeNull()  // subject narrowed to String
    .startsWith("atrium")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L237)</sub> ↓ <sub>Output</sub>
```text
expect: null
◆ is instance of type: String (kotlin.String) -- Class: java.lang.String
```
</ex-nullable-3>

Since `notToBeNull` delegates to `isA` it also provides two overloads, 
one without (example above) and one with `assertionCreator`-lambda (example below); see 
[Type Assertions](#type-assertions) for more information on the difference of the overloads.

<ex-nullable-4>

```kotlin
expect(slogan2).notToBeNull { startsWith("atrium") }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L242)</sub> ↓ <sub>Output</sub>
```text
expect: null
◆ is instance of type: String (kotlin.String) -- Class: java.lang.String
  » starts with: "atrium"        <1234789>
```
</ex-nullable-4>

Atrium provides one additional function which is intended for [data driven testing](#data-driven-testing) 
involving nullable types and is explained in the corresponding section.

<details>
<summary>👓 dealing a lot with nullable types from Java...</summary>

... in this case we recommend to have a look at the [Java Interoperability](#java-interoperability) section.

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

<ex-collection-short-1>

```kotlin
expect(listOf(1, 2, 2, 4)).contains(2, 3)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L246)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains, in any order: 
  ⚬ an entry which is: 3        (kotlin.Int <1234789>)
    ⚬ ▶ number of occurrences: 0
        ◾ is at least: 1
```
</ex-collection-short-1>
 
The assertion function `contains(2, 3)` is a shortcut for using a 
[Sophisticated Assertion Builder](#sophisticated-assertion-builders) -- it actually calls `contains.inAnyOrder.atLeast(1).values(2, 3)`. 
This is reflected in the output, which tells us that we expected that the `number of occurrences` of `3` (which is actually `0`) `is at least: 1`.

<details>
<summary>👓 and what about expected value 2?</summary>

Exactly, what about the expected value `2`, why do we not see anything about it in the output?
The output does not show anything about the expected value `2` because the predefined assertion verbs have configured [`ReporterBuilder`](#reporterbuilder) 
to use an [Only Failure Reporter](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.core/-core-factory/new-only-failure-reporter.html) 
which shows us only assertions (or sub assertions) which failed.

Back to the shortcut functions.
<hr/>
</details>
 
Next to expecting that certain values are contained in or rather returned by an `Iterable`, 
Atrium allows us to use an `assertionCreator`-lambda to identify an entry
(`assertionCreator`-lambda can also be thought of as matcher / predicate in this context).
An entry is considered as identified, if it holds all specified assertions.
Following an example:

<ex-collection-short-2>

```kotlin
expect(listOf(1, 2, 2, 4)).contains(
    { isLessThan(0) },
    { isGreaterThan(2).isLessThan(4) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L250)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains, in any order: 
  ⚬ an entry which: 
      » is less than: 0        (kotlin.Int <1234789>)
    ⚬ ▶ number of occurrences: 0
        ◾ is at least: 1
  ⚬ an entry which: 
      » is greater than: 2        (kotlin.Int <1234789>)
      » is less than: 4        (kotlin.Int <1234789>)
    ⚬ ▶ number of occurrences: 0
        ◾ is at least: 1
```
</ex-collection-short-2>

In the above example, neither of the two lambdas matched any entries and thus both are reported as failing (sub) assertions.

Another `contains` shortcut function which Atrium provides for `Iterable<T>` is kind of 
the opposite of `inAnyOrder.atLeast(1)` and is named `containsExactly`.
Again, Atrium provides two overloads for it, one for values,
e.g. `containsExactly(1, 2)` which calls `contains.inOrder.only.values(1, 2)` 
and a second one which expects one or more `assertionCreator`-lambda or `null`, 
e.g. `containsExactly({ isLessThan(0) }, { isGreaterThan(5) })` 
and effectively calls `contains.inOrder.only.entries({ isLessThan(2) }, { isGreaterThan(5) })`.
We will spare the examples here and show them in the following sections.
Notice that passing `null` to `containsExactly` makes only sense if your `Iterable` contains nullable entries.

Atrium provides also a `containsNot` shortcut function. 
Furthermore, it provides aliases for `contains` and `containsNot` named `any` and `none`,  which might be a better 
choice if you think in terms of: expect a predicate holds. These two are completed with an `all` assertion function. 
Following each in action:

<ex-collection-any>

```kotlin
expect(listOf(1, 2, 3, 4)).any { isLessThan(0) }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L257)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains, in any order: 
  ⚬ an entry which: 
      » is less than: 0        (kotlin.Int <1234789>)
    ⚬ ▶ number of occurrences: 0
        ◾ is at least: 1
```
</ex-collection-any>
<hr/>
<ex-collection-none>

```kotlin
expect(listOf(1, 2, 3, 4)).none { isGreaterThan(2) }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L260)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ does not contain: 
  ⚬ an entry which: 
      » is greater than: 2        (kotlin.Int <1234789>)
    ✘ ▶ number of occurrences: 2
        ◾ is: 0        (kotlin.Int <1234789>)
    ✔ ▶ has at least one element: true
        ◾ is: true
```
</ex-collection-none>
<hr/>
<ex-collection-all>

```kotlin
expect(listOf(1, 2, 3, 4)).all { isGreaterThan(2) }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L263)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ all entries: 
    » is greater than: 2        (kotlin.Int <1234789>)
    ❗❗ following entries were mismatched: 
       ⚬ index 0: 1        (kotlin.Int <1234789>)
       ⚬ index 1: 2        (kotlin.Int <1234789>)
```
</ex-collection-all>


### Sophisticated Assertion Builders

Sophisticated assertion builders implement a fluent builder pattern.
To use the assertion builder for sophisticated `Iterable<T>`-contains-assertions, you can type `contains` 
-- as you would when using the [Shortcut Functions](#shortcut-functions) `contains` -- 
but type `.` as next step (so that you are using the property `contains` instead of one of the shortcut functions). 
Currently, the builder provides two options, either `inAnyOrder` or `inOrder`. 
In case you are using an IDE, you do not really have to think too much -- use code completion; 
the fluent builders will guide you through your decision making 😊

Following on the last section we will start with an `inOrder` example:

<ex-collection-builder-1>

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L267)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains only, in order: 
  ✔ ▶ entry 0: 1        (kotlin.Int <1234789>)
      ◾ an entry which: 
          » is less than: 3        (kotlin.Int <1234789>)
  ✘ ▶ entry 1: 2        (kotlin.Int <1234789>)
      ◾ an entry which: 
          » is less than: 2        (kotlin.Int <1234789>)
  ✘ ▶ size: 4        (kotlin.Int <1234789>)
      ◾ to be: 2        (kotlin.Int <1234789>)
        ❗❗ additional entries detected: 
           ⚬ entry 2: 2        (kotlin.Int <1234789>)
           ⚬ entry 3: 4        (kotlin.Int <1234789>)
```
</ex-collection-builder-1>

Since we have chosen the `only` option, Atrium shows us a summary where we see three things:
- Whether a specified `assertionCreator`-lambda matched (signified by `✔` or `✘`) 
  the corresponding entry or not (e.g. `✘ ▶ entry 1:` was `2` and we expected, it `is less than 2`)
- Whether the expected size was correct or not (`✘ ▶ size:` was `4`, we expected it, `to be: 2` -- see also [Property Assertions](#property-assertions))
- and last but not least, mismatches or additional entries as further clue (`❗❗ additional entries detected`).

😍 We are pretty sure you are going to love this feature as well. 
Please star Atrium if you like using it.

<details>
<summary>💬 too verbose?</summary>

As side notice, in case you are dealing with large `Iterable` and do not want such a verbose output, 
then let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]). 
So far the verbose output was always handy for me but you might have other test cases than me.
Also notice, that Atrium cannot yet deal with infinite `Iterable`s.
If you have to, then please open a feature request as well. In the meantime, you can of course `take(100)` or the like.
<hr/>
</details>

Following one more example for `inOrder` as well as a few examples for `inAnyOrder`. 
We think explanations are no longer required at this stage.
In case you have a question (no matter about which section), then please turn up in the 
[atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet) 
and we happily answer your question there. 

<ex-collection-builder-2>

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L270)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains only, in order: 
  ✔ ▶ entry 0: 1        (kotlin.Int <1234789>)
      ◾ to be: 1        (kotlin.Int <1234789>)
  ✔ ▶ entry 1: 2        (kotlin.Int <1234789>)
      ◾ to be: 2        (kotlin.Int <1234789>)
  ✔ ▶ entry 2: 2        (kotlin.Int <1234789>)
      ◾ to be: 2        (kotlin.Int <1234789>)
  ✘ ▶ entry 3: 4        (kotlin.Int <1234789>)
      ◾ to be: 3        (kotlin.Int <1234789>)
  ✘ ▶ entry 4: ❗❗ hasNext() returned false
      ◾ to be: 4        (kotlin.Int <1234789>)
  ✘ ▶ size: 4        (kotlin.Int <1234789>)
      ◾ to be: 5        (kotlin.Int <1234789>)
```
</ex-collection-builder-2>
<hr/>
<ex-collection-builder-3>

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L273)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains, in any order: 
  ⚬ an entry which: 
      » is less than: 3        (kotlin.Int <1234789>)
    ⚬ ▶ number of occurrences: 3
        ◾ is at most: 2
```
</ex-collection-builder-3>
<hr/>
<ex-collection-builder-4>

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L276)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains only, in any order: 
  ✔ an entry which is: 1        (kotlin.Int <1234789>)
  ✔ an entry which is: 2        (kotlin.Int <1234789>)
  ✘ an entry which is: 3        (kotlin.Int <1234789>)
  ✔ an entry which is: 4        (kotlin.Int <1234789>)
  ✔ ▶ size: 4
      ◾ to be: 4
  ❗❗ following entries were mismatched: 
     ⚬ 2        (kotlin.Int <1234789>)
```
</ex-collection-builder-4>
<hr/>
<ex-collection-builder-5>

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L279)</sub> ↓ <sub>Output</sub>
```text
expect: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ contains only, in any order: 
  ✔ an entry which is: 4        (kotlin.Int <1234789>)
  ✘ an entry which is: 3        (kotlin.Int <1234789>)
  ✔ an entry which is: 2        (kotlin.Int <1234789>)
  ✔ an entry which is: 2        (kotlin.Int <1234789>)
  ✔ an entry which is: 1        (kotlin.Int <1234789>)
  ✘ ▶ size: 4
      ◾ to be: 5
```
</ex-collection-builder-5>


## Map Assertions

<ex-map-1>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains("c" to 2, "a" to 1, "b" to 1)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L283)</sub> ↓ <sub>Output</sub>
```text
expect: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ contains, in any order: 
  ⚬ ▶ entry "c": ❗❗ key does not exist
        » to be: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ to be: 1        (kotlin.Int <1234789>)
```
</ex-map-1>
 
Map assertions are kind of very similar to [Collection Assertions](#collection-assertions), also regarding reporting. 
That is the reason why we are not going into too much detail here because we assume you are already familiar with it.

Next to making assertions based on key value pairs one can also define sub assertions for the value of an entry with 
the help of the parameter object `KeyValue`:

<ex-map-2>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains(
    KeyValue("c") { toBe(2) },
    KeyValue("a") { isGreaterThan(2) },
    KeyValue("b") { isLessThan(2) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L286)</sub> ↓ <sub>Output</sub>
```text
expect: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ contains, in any order: 
  ⚬ ▶ entry "c": ❗❗ key does not exist
        » is instance of type: Int (kotlin.Int) -- Class: java.lang.Integer
        » to be: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "a": 1        (kotlin.Int <1234789>)
      ◾ is greater than: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ is less than: 2        (kotlin.Int <1234789>)
```
</ex-map-2>

In case you want to postulate an assertion about a value of one particular key, then you can use `getExisting`.
For instance:

<ex-map-3>

```kotlin
data class Person(val firstName: String, val lastName: String, val age: Int)
val bernstein = Person("Leonard", "Bernstein", 50)
expect(mapOf("bernstein" to bernstein))
    .getExisting("bernstein") {
        feature { f(it::firstName) }.toBe("Leonard")
        feature { f(it::age) }.toBe(60)
    }
    .getExisting("einstein") {
        feature { f(it::firstName) }.toBe("Albert")
    }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L298)</sub> ↓ <sub>Output</sub>
```text
expect: {bernstein=Person(firstName=Leonard, lastName=Bernstein, age=50)}        (java.util.Collections.SingletonMap <1234789>)
◆ ▶ get("bernstein"): Person(firstName=Leonard, lastName=Bernstein, age=50)        (readme.examples.ReadmeSpec2$1$Person <1234789>)
    ◾ ▶ age: 50        (kotlin.Int <1234789>)
        ◾ to be: 60        (kotlin.Int <1234789>)
```
</ex-map-3>

In case you want to make an assertion only about the keys or values of the `Map` then you can use `keys` or `values`:

<ex-map-4>

```kotlin
expect(mapOf("a" to 1, "b" to 2)) {
    keys { all { startsWith("a") } }
    values { none { isGreaterThan(1) } }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L310)</sub> ↓ <sub>Output</sub>
```text
expect: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ ▶ keys: [a, b]        (java.util.LinkedHashMap.LinkedKeySet <1234789>)
    ◾ all entries: 
        » starts with: "a"        <1234789>
        ❗❗ following entries were mismatched: 
           ⚬ index 1: "b"        <1234789>
◆ ▶ values: [1, 2]        (java.util.LinkedHashMap.LinkedValues <1234789>)
    ◾ does not contain: 
      ⚬ an entry which: 
          » is greater than: 1        (kotlin.Int <1234789>)
        ✘ ▶ number of occurrences: 1
            ◾ is: 0        (kotlin.Int <1234789>)
        ✔ ▶ has at least one element: true
            ◾ is: true
```
</ex-map-4>

Last but not least, you can use the non-reporting `asEntries()` function which
turns `Expect<Map<K, V>>` into an `Expect<Set<Map.Entry<K, V>>` and thus allows that you can use all the assertion 
functions and sophisticated builders shown in [Collection Assertions](#collection-assertions).

For instance, say you have a `LinkedHashMap` and want to be sure that the order is correct:

<ex-map-5>

```kotlin
expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().contains.inOrder.only.entries(
    { isKeyValue("a", 1) },
    {
        key.startsWith("a")
        value.isGreaterThan(2)
    }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L316)</sub> ↓ <sub>Output</sub>
```text
expect: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ contains only, in order: 
  ✔ ▶ entry 0: a=1        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ an entry which: 
          » ▶ key: "a"        <1234789>
              ◾ to be: "a"        <1234789>
          » ▶ value: 1        (kotlin.Int <1234789>)
              ◾ to be: 1        (kotlin.Int <1234789>)
  ✘ ▶ entry 1: b=2        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ an entry which: 
          » ▶ key: "a"        <1234789>
              ◾ starts with: "a"        <1234789>
          » ▶ value: 1        (kotlin.Int <1234789>)
              ◾ is greater than: 2        (kotlin.Int <1234789>)
  ✔ ▶ size: 2        (kotlin.Int <1234789>)
      ◾ to be: 2        (kotlin.Int <1234789>)
```
</ex-map-5>

`isKeyValue` as well as `key` and `value` are assertion functions defined for `Map.Entry<K, V>`.

There are more assertion functions, a full list can be found in 
[KDoc of atrium-api-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html).

In case you should miss an assertion function, then please 
[open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
For instance, you might want to upvote [containsInAnyOrderOnly](https://github.com/robstoll/atrium/issues/68)
in case you want this shortcut function as well.

## Path Assertions

Atrium’s assertions for paths give detailed explanations about what is going on on the file system.
For example, `exists` will explain which entry was the first one missing:

<ex-path-exsits>

```kotlin
expect(Paths.get("/usr/bin/noprogram")).exists()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L326)</sub> ↓ <sub>Output</sub>
```text
expect: /usr/bin/noprogram        (sun.nio.fs.UnixPath <1234789>)
◆ to: exist
    » the closest existing parent directory is /usr/bin
```
</ex-path-exsits>

Atrium will give details about why something cannot be accessed, for example when checking whether a file is writable:

<ex-path-writable>

```kotlin
expect(Paths.get("/root/.ssh/config")).isWritable()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L330)</sub> ↓ <sub>Output</sub>
```text
expect: /root/.ssh/config        (sun.nio.fs.UnixPath <1234789>)
◆ to be: writable
    » failure at parent path: /root        (sun.nio.fs.UnixPath <1234789>)
      » access was denied
      » the owner is root, the group is root
      » the permissions are u=rwx g= o=
```
</ex-path-writable>

Even in more complicated scenarios, Atrium explains step by step what happened:

<ex-path-symlink-and-parent-not-folder>

```kotlin
val directory = Files.createDirectory(tmpdir.resolve("atrium-path"))
val file = Files.createFile(directory.resolve("file"))
val filePointer = Files.createSymbolicLink(directory.resolve("directory"), file)

expect(filePointer.resolve("subfolder/file")).isRegularFile()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L335)</sub> ↓ <sub>Output</sub>
```text
expect: /tmp/atrium-path/directory/subfolder/file        (sun.nio.fs.UnixPath <1234789>)
◆ to be: a file
    » followed the symbolic link /tmp/atrium-path/directory to /tmp/atrium-path/file
    » failure at parent path: /tmp/atrium-path/file        (sun.nio.fs.UnixPath <1234789>)
      » was a file instead of a directory
```
</ex-path-symlink-and-parent-not-folder>

## Data Driven Testing

Atrium is not intended for data driven testing in the narrowed sense in terms that it cannot produce multiple tests.
This is the responsibility of your test runner.
However, Atrium let you define multiple assertions within one test and reports them all if you want.
In this sense it can be used for data driven testing.
This is especially helpful in case your test runner does not support data driven testing (or other mechanisms like hierarchical or dynamic tests).
As an example, Atrium can help you writing data driven tests in a common module of a multiplatform-project.

The trick is to wrap your assertions into an [assertion group block](#define-single-assertions-or-assertion-groups)
and create [Feature Assertions](#feature-assertions). Following an example:

<ex-data-driven-1>

```kotlin
fun myFun(i: Int) = (i + 97).toChar()

expect("calling myFun with...") {
    mapOf(
        1 to 'a',
        2 to 'c',
        3 to 'e'
    ).forEach { (arg, result) ->
        feature { f(::myFun, arg) }.toBe(result)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L348)</sub> ↓ <sub>Output</sub>
```text
expect: "calling myFun with..."        <1234789>
◆ ▶ myFun(1): 'b'
    ◾ to be: 'a'
◆ ▶ myFun(3): 'd'
    ◾ to be: 'e'
```
</ex-data-driven-1>

Depending on the chosen [reporting style](#reporterbuilder) it will only show the failing cases (default behaviour).
This is also the reason why the call of `myFun(2)` is not listed (as the result is `c` as expected).

Please [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
if you want to see a summary, meaning also successful assertions --we happily add more functionality if it is of use for someone.

Following another example which involves an assertion creator lambda and not only a simple `toBe` check. 
We are going to reuse the `myFun` from above

<ex-data-driven-2>

```kotlin
import ch.tutteli.atrium.domain.builders.utils.subExpect

expect("calling myFun with ...") {
    mapOf(
        1 to subExpect<Char> { isLessThan('f') },
        2 to subExpect { toBe('c') },
        3 to subExpect { isGreaterThan('e') }
    ).forEach { (arg, assertionCreator) ->
        feature({ f(::myFun, arg) }, assertionCreator)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L362)</sub> ↓ <sub>Output</sub>
```text
expect: "calling myFun with ..."        <1234789>
◆ ▶ myFun(3): 'd'
    ◾ is greater than: 'e'
```
</ex-data-driven-2>

The example should be self explanatory.
One detail to note though is the usage of `subExpect`. 
It is a helper function which circumvents certain [Kotlin type inference bugs](#kotlin-bugs) (upvote them please).
Writing the same as `mapOf<Int, Expect<Char>.() -> Unit>( 1 to { ... } )` would not work as the type for a lambda 
involved in a Pair is not (yet) inferred correctly.

There is one last function worth mentioning here which comes in handy in data-driven testing in case the subject has a 
[nullable type]((https://kotlinlang.org/docs/reference/null-safety.html).)

If you wish to make sub-assertions on the non-nullable type of the subject, then you can use
`toBeNullIfNullGivenElse` which accepts an assertion creator or `null`.
It is short for `if (assertionCreatorOrNull == null) toBe(null) else notToBeNull(assertionCreatorOrNull)`. 
Following another fictional example which illustrates `toBeNullIfNullGivenElse` (we are reusing `myFun` from above):

<ex-data-driven-3>

```kotlin
fun myNullableFun(i: Int) = if (i > 0) i.toString() else null

expect("calling myNullableFun with ...") {
    mapOf(
        Int.MIN_VALUE to subExpect<String> { contains("min") },
        -1 to null,
        0 to null,
        1 to subExpect { toBe("1") },
        2 to subExpect { endsWith("2") },
        Int.MAX_VALUE to subExpect { toBe("max") }
    ).forEach { (arg, assertionCreatorOrNull) ->
        feature { f(::myNullableFun, arg) }.toBeNullIfNullGivenElse(assertionCreatorOrNull)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L380)</sub> ↓ <sub>Output</sub>
```text
expect: "calling myNullableFun with ..."        <1234789>
◆ ▶ myNullableFun(-2147483648): null
    ◾ is instance of type: String (kotlin.String) -- Class: java.lang.String
      » contains: 
        ⚬ value: "min"        <1234789>
          ⚬ ▶ number of occurrences: -1
              ◾ is at least: 1
◆ ▶ myNullableFun(2147483647): "2147483647"        <1234789>
    ◾ to be: "max"        <1234789>
```
</ex-data-driven-3>

## Further Examples

Atrium supports further assertion builders (e.g, for `CharSequence`) 
as well as assertion functions which have not been shown in the examples.
Have a look at [apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md) for a few more examples.
This site contains also a list of all APIs with links to their assertion function catalogs.

You can also have a look at the 
[specifications](https://github.com/robstoll/atrium/tree/master/misc/specs/atrium-specs-common/src/main/kotlin/ch/tutteli/atrium/specs) 
for more examples.

# How is Atrium different from other Assertion Libraries

The following subsections shall give you a quick overview how Atrium differ from other assertion libraries. 

- [Ready to Help](#ready-to-help)
  - [Fluent API with Code Documentation](#1-fluent-api-with-code-documentation)
  - [Additional Information in Failure Reporting](#2-additional-information-in-failure-reporting)
  - [Prevents you from Pitfalls](#3-prevents-you-from-pitfalls)
- [Flexibility](#flexibility)
- [Migration of Deprecated Functionality](#migration-of-deprecated-functionality)
- [Internationalization](#internationalization)

## Ready to Help
Atrium is designed to help you whenever possible.
We think this is the biggest difference to other assertion libraries and a very handy one indeed.

### 1. Fluent API with Code Documentation
Atrium provides a fluent API where the design focus was put on the interoperability (of the API) 
with the code completion functionality of your IDE. 
Or in other words, you can always use code completion to get direct help from your IDE.
This experience is improved by providing up-to-date [code documentation](#kdoc) (in form of KDoc) for all assertion functions, 
so that you get the extra help needed.

💩 &lt;- _this icon signifies a bug in Kotlin which you might encounter as well. 
We try to provide a workaround whenever possible._

<details>
<summary>💩 There is no KDoc for toBe</summary>

There is, but IntelliJ will not show it to you due to [this bug](https://youtrack.jetbrains.com/issue/KT-24836) (please upvote it).
You should be able to see the KDoc of other functions without problems. 
But in case, you can also browse the online documentation, e.g. [KDoc of toBe](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/to-be.html).

</details>

### 2. Additional Information in Failure Reporting
Atrium adds extra information to error messages so that you get quickly a better idea of what went wrong. 
For instance, for the following assertion (which fails):

<exs-add-info-1>

```kotlin
expect(listOf(1, 2, 3)).contains.inOrder.only.values(1, 3)
```
</exs-add-info-1>

Atrium points out which `values` were found, makes an implicit assertion about the size and 
also states which entries were additionally contained in the list:

<exs-add-info-1-output>

```text
expect: [1, 2, 3]        (java.util.Arrays.ArrayList <1234789>)
◆ contains only, in order: 
  ✔ ▶ entry 0: 1        (kotlin.Int <1234789>)
      ◾ to be: 1        (kotlin.Int <1234789>)
  ✘ ▶ entry 1: 2        (kotlin.Int <1234789>)
      ◾ to be: 3        (kotlin.Int <1234789>)
  ✘ ▶ size: 3        (kotlin.Int <1234789>)
      ◾ to be: 2        (kotlin.Int <1234789>)
        ❗❗ additional entries detected: 
           ⚬ entry 2: 3        (kotlin.Int <1234789>)
```
</exs-add-info-1-output>


Let us have a look at another example.

<exs-add-info-2>

```kotlin
expect(9.99f).toBeWithErrorTolerance(10.0f, 0.01f)
```
</exs-add-info-2>

The above assertion looks good at first sight but actually fails (at least on my machine). 
And without some extra information in the output we would believe that there is actually a bug in the assertion library itself.
But Atrium shows where it goes wrong and even gives a possible hint:

<exs-add-info-2-output>

```text
expect: 9.99        (kotlin.Float <1234789>)
◆ to be (error ± 0.01): 10.0        (kotlin.Float <1234789>)
    » failure might be due to using kotlin.Float, see exact check on the next line
    » exact check is |9.989999771118164 - 10.0| = 0.010000228881835938 ≤ 0.009999999776482582
```
</exs-add-info-2-output>

One last example. This time about making an assertion that a certain `Throwable` is thrown but the assertion fails 
because it was the wrong one. 
Atrium comes with a very useful hint, it shows the actual exception:

<ex-add-info-3>

```kotlin
expect {
    try {
        throw UnsupportedOperationException("not supported")
    } catch (t: Throwable) {
        throw IllegalArgumentException("no no no...", t)
    }
}.toThrow<IllegalStateException> { messageContains("no no no") }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L404)</sub> ↓ <sub>Output</sub>
```text
expect: () -> kotlin.Nothing        (readme.examples.ReadmeSpec2$1$31$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ is instance of type: IllegalStateException (java.lang.IllegalStateException)
      » ▶ message: CANNOT evaluate representation as it is based on subject which is not defined.
            » is instance of type: String (kotlin.String) -- Class: java.lang.String
            » contains: 
              ⚬ value: "no no no"        <1234789>
                ⚬ ▶ number of occurrences: -1
                    ◾ is at least: 1
      » Properties of the unexpected IllegalArgumentException
        » message: "no no no..."        <1234789>
        » stacktrace: 
          ⚬ readme.examples.ReadmeSpec2$1$31$1.invoke(ReadmeSpec.kt:409)
          ⚬ readme.examples.ReadmeSpec2$1$31$1.invoke(ReadmeSpec.kt:221)
          ⚬ readme.examples.ReadmeSpec2$1$31.invoke(ReadmeSpec.kt:626)
          ⚬ readme.examples.ReadmeSpec2$1$31.invoke(ReadmeSpec.kt:221)
        » cause: java.lang.UnsupportedOperationException
            » message: "not supported"        <1234789>
            » stacktrace: 
              ⚬ readme.examples.ReadmeSpec2$1$31$1.invoke(ReadmeSpec.kt:407)
```
</ex-add-info-3>


### 3. Prevents you from Pitfalls
But not enough. There are certain pitfalls when it comes to using an assertion library and Atrium tries to prevent you from those.

For instance, an overload of `toBe` and of `notToBe` for `BigDecimal` was introduced which are both deprecated and throw a `PleaseReplaceException`. 
The reason behind it?
It is very likely that a user actually wants to compare that a certain `BigDecimal` is numerically (not) equal to another `BigDecimal` 
rather than including `BigDecimal.scale` in the comparison.
Accordingly, the deprecation message of `toBe` (`notToBe` alike) explains the problem and suggests to either use `isNumericallyEqualTo` or `isEqualIncludingScale`.
And if the user should decide to use `isEqualIncludingScale` and at some point an assertion fails only due to the comparison of `BigDecimal.scale`
then Atrium reminds us of the possible pitfall. For instance:

<ex-pitfall-1>

```kotlin
expect(BigDecimal.TEN).isEqualIncludingScale(BigDecimal("10.0"))
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L414)</sub> ↓ <sub>Output</sub>
```text
expect: 10        (java.math.BigDecimal <1234789>)
◆ is equal (including scale): 10.0        (java.math.BigDecimal <1234789>)
    » notice, if you used isNumericallyEqualTo then the assertion would have hold.
```
</ex-pitfall-1>

Another example are empty `assertionCreator`-lambdas. 
Getting distracted by a working colleague and taking up the work at the wrong position might sound familiar to you. 
For instance:

<ex-pitfall-2>

```kotlin
expect(listOf(1)).get(0) {}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L417)</sub> ↓ <sub>Output</sub>
```text
expect: [1]        (java.util.Collections.SingletonList <1234789>)
◆ ▶ get(0): 1        (kotlin.Int <1234789>)
    ◾ at least one assertion defined: false
        » You forgot to define assertions in the assertionCreator-lambda
        » Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
```
</ex-pitfall-2>

## Flexibility
Another design goal of Atrium was to give you the flexibility needed but still adhere to a concise design. 
First and most importantly, Atrium does not enforce a certain style on your code base. 
Quite the contrary, it gives you the flexibility to [choose a desired name for the assertion verbs](#use-own-assertion-verbs), 
it continues by providing the possibility to configure the [reporting style](#reporterbuilder), 
goes on that you can chose from different [API Styles](#api-styles) 
and ends that you can replace almost all components by other implementations and hook into existing.

So for instance, if you like to use an `infix` API, then use the bundle `atrium-infix-en_GB`. 
You prefer pure fluent and do not even want to see infix style in your code, 
then use `atrium-fluent-en_GB` which provides a pure fluent style API. 

You are free to choose what fits best without introducing ambiguity etc.
You could even mix up different API-Styles if needed (but not without losing conciseness -- but hey, it is your decision 😉). 

## Migration of Deprecated Functionality
Atrium follows [Semantic Versioning](https://semver.org/) and tries to be binary backward compatible within a major version (since 0.6.0).
Until 1.0.0 this is only true for the API level, we reserve the right to break things on the domain and core level until then.
Moreover, we follow the principle that a user of Atrium has enough time to migrate its code to new functionality before a next major version.
We provide this in form of `@Deprecated` annotations with a corresponding `ReplaceWith` 
as well as migration guides in the [Release Notes](https://github.com/robstoll/atrium/releases).
This way we hope that we provide a pleasant way to stay up-to-date without the need to migrate everything from one day to the other.

## Internationalization
The last difference is not yet fully-blown implemented but the design of Atrium has everything needed to go down the planed [Roadmap](#roadmap).
Might well be that this topic is not really a concern of yours; unless...  

- you are using domain-driven-design and would like to adopt the ubiquitous language also to your test code.
- you want to document the results of your defined assertions (in different languages) 

Atrium already supports APIs in two languages, and it is an easy task to translate an API to another language (hello DDD-people 👋 you are good to go).
Moreover, it is already possible to generate the output in a different language than the used API (e.g. code in English but report in German).

Together with the HTML-Report feature (currently missing but will follow) you will be able to generate reports in different languages.
Already the HTML-Report feature as such might be of your interest. 
You can use it to document your user stories etc (almost) for free.
In case you have clients who speak different languages then the HTML-Report together with the i18n feature will be especially helpful. 
We should not go on here, the HTML-Report feature is not yet implemented, but you can see what kind of road we plan to go down to.

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic 
[Feature Assertions](#feature-assertions) are not good enough?

The following sub sections will show how you can write your own assertion functions. 
A pull request of your new assertion function is very much appreciated.

## Boolean based Assertions

This is kind of the simplest way of defining assertion functions. Following an example:

<code-own-boolean-1>

```kotlin
fun Expect<Int>.isMultipleOf(base: Int) =
    createAndAddAssertion("is multiple of", base) { it % base == 0 }
```
</code-own-boolean-1>

and its usage:

<ex-own-boolean-1>

```kotlin
expect(12).isMultipleOf(5)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L429)</sub> ↓ <sub>Output</sub>
```text
expect: 12        (kotlin.Int <1234789>)
◆ is multiple of: 5        (kotlin.Int <1234789>)
```
</ex-own-boolean-1>

Let us see how we actually defined `isMultipleOf`. 
1. *Choose the extension point*: in our example we want to provide the assertion function for `Int`s. 
    Hence we define `isMultipleOf` as [extension function](https://kotlinlang.org/docs/reference/extensions.html) of `Expect<Int>`.

2. *Use the method `createAndAddAssertion`* (provided by `Expect`)  which creates and adds 
    the assertion to itself (creating alone is not enough, it needs to be added in order that it is evaluated). 
    The method `createAndAddAssertion` returns itself (the same `Expect`) making it easy for you to provide a fluent API as well.
 
    The method [createAndAddAssertion](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.creating/-assertion-plant/create-and-add-assertion.html)
    expects:
    - a either a `String` or a [Translatable](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translatable/index.html)
      as description of your assertion.
    - the representation of the expected value.
    - and the actual check as lambda where you typically use `it` which refers to the subject of the assertion.
     
We used a `String` as description in the above example because we are not bothered with internationalization at this point
(have a look at [Internationalization](#internationalization-1) if you are).

In most cases you probably use the expected value itself as its representation -- so you pass it as second argument.
And finally you specify the test as such in the lambda passed as third argument.

But not all assertion functions require a value which is somehow compared against the subject 
-- some make an assertion about a characteristic of the subject without comparing it against an expected value.
Consider the following assertion function:

<code-own-boolean-2>

```kotlin
fun Expect<Int>.isEven() =
    createAndAddAssertion("is", RawString.create("an even number")) { it % 2 == 0 }
```
</code-own-boolean-2>

We are using a [RawString](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-raw-string/index.html)
here so that `"an even number"` is not treated as a `String` in reporting.
Its usage looks then as follows:

<ex-own-boolean-2>

```kotlin
expect(13).isEven()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L440)</sub> ↓ <sub>Output</sub>
```text
expect: 13        (kotlin.Int <1234789>)
◆ is: an even number
```
</ex-own-boolean-2>

## Compose Assertion Functions

So far we ran quickly into the situation where we wanted to compose functions or
reuse existing functions but with different arguments. 
We will show both use cases here, starting off by composing functions. 

Say you want to build a `isBetween` assertion function for `java.util.Date`, you could write it as follows:

<code-own-compose-1>

```kotlin
fun <T : Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    isGreaterThanOrEqual(lowerBoundInclusive).and.isLessThan(upperBoundExclusive)
```
</code-own-compose-1>

Pretty simply isn't it. 
Notice though, that this function fails fast, which means, the upper bound is not evaluated 
if the assertion about the lower bound already fails. 
You need to use an [assertion group block](#define-single-assertions-or-assertion-groups) 
if you want that both are evaluated:

<code-own-compose-2>

```kotlin
fun <T : Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    addAssertionsCreatedBy {
        isGreaterThanOrEqual(lowerBoundInclusive)
        isLessThan(upperBoundExclusive)
    }
```
</code-own-compose-2>

Still simple enough.

<details>
<summary>💬 Why is a type parameter used in the above examples?</summary>

That is right, we used a type parameter `T: Date` and not `Expect<Date>` directly. 
You should always do this unless your type is final (not `open`) and does not have type parameters itself. 
This way the assertion function is also available for subtypes. This is because `Expect` is invariant. 
Following an example:
```kotlin
interface A { val foo get() = 1 }
class B: A
val Expect<A>.foo get() = feature(A::foo)

expect(B()).foo // does not compile as foo is only available for `Expect<A>`
```

In case your assertion function should also be available for an `Expect<out MyClass>` where `MyClass` is a final class
then you have to use a type parameter as well -- thus easiest way, always use a type parameter.
    
</details>


So lets move on to an example which is a bit more complicated. Assume the following data class `Person`

<code-own-compose-3a>

```kotlin
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val children: Collection<Person>
    // ...  and others
)
```
</code-own-compose-3a>

Say you want to make an assertion about the number of children a person has:

<code-own-compose-3b>

```kotlin
fun Expect<Person>.hasNumberOfChildren(number: Int): Expect<Person> = apply {
    feature(Person::children) { hasSize(number) }
}
```
</code-own-compose-3b>

Three things to notice here: 
1. we make use of a [feature assertion with class reference](#within-assertion-functions)
2. We use `apply` so that subsequent assertions are still made on `Person` and not on `children` 
   (you could also a block and `return this` instead of `apply`)
 
Its usage is then as follows:

<ex-own-compose-3>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf()))
    .hasNumberOfChildren(2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L479)</sub> ↓ <sub>Output</sub>
```text
expect: Person(firstName=Susanne, lastName=Whitley, age=43, children=[])        (readme.examples.Person <1234789>)
◆ ▶ children: []        (kotlin.collections.EmptyList <1234789>)
    ◾ ▶ size: 0        (kotlin.Int <1234789>)
        ◾ to be: 2        (kotlin.Int <1234789>)
```
</ex-own-compose-3>

Another example: assert the person has children which are all adults (assuming 18 is the age of majority).

<code-own-compose-4>

```kotlin
fun Expect<Person>.hasAdultChildren(): Expect<Person> = apply {
    feature(Person::children) {
        all { feature(Person::age).isGreaterThanOrEqual(18) }
    }
}
```
</code-own-compose-4>

We also use `apply` here for the same reason as above.
We might be tempted to add an additional size check -- because a Person with 0 children does not have adult children --
but we don't have to, as `all` already checks that there is at least one element. 

<ex-own-compose-4>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf()))
    .hasAdultChildren()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L494)</sub> ↓ <sub>Output</sub>
```text
expect: Person(firstName=Susanne, lastName=Whitley, age=43, children=[])        (readme.examples.Person <1234789>)
◆ ▶ children: []        (kotlin.collections.EmptyList <1234789>)
    ◾ ▶ has at least one element: false
        ◾ is: true
```
</ex-own-compose-4>

If we keep adding assertion functions involving `children` it might be best to provide a shortcut property and function
(assuming the API of Person is stable enough).

<code-own-compose-5>

```kotlin
val Expect<Person>.children: Expect<Collection<Person>> get() = feature(Person::children)

fun Expect<Person>.children(assertionCreator: Expect<Collection<Person>>.() -> Unit): Expect<Person> =
    feature(Person::children, assertionCreator)
```
</code-own-compose-5>

Notice, that we have used a class-reference and not a bounded-reference to refer to `children` which is best practice 
(see [feature assertions within assertion functions](#within-assertion-functions)).
With this, we can write things like:

<ex-own-compose-5>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf(Person("Petra", "Whitley", 12, listOf()))))
    .children { // using the fun -> assertion group, ergo sub-assertions don't fail fast
        none { feature { f(it::firstName) }.startsWith("Ro") }
        all { feature { f(it::lastName) }.toBe("Whitley") }
    } // subject is still Person here
    .apply { // only evaluated because the previous assertion group holds
        children  // using the val -> subsequent assertions are about children and fail fast
            .hasSize(2)
            .any { feature { f(it::age) }.isGreaterThan(18) }
    } // subject is still Person here due to the `apply`
    .children // using the val -> subsequent assertions are about children and fail fast
    .hasSize(2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/master/samples/readme-examples/src/main/kotlin/readme/examples/ReadmeSpec.kt#L504)</sub> ↓ <sub>Output</sub>
```text
expect: Person(firstName=Susanne, lastName=Whitley, age=43, children=[Person(firstName=Petra, lastName=Whitley, age=12, children=[])])        (readme.examples.Person <1234789>)
◆ ▶ children: [Person(firstName=Petra, lastName=Whitley, age=12, children=[])]        (java.util.Collections.SingletonList <1234789>)
    ◾ ▶ size: 1        (kotlin.Int <1234789>)
        ◾ to be: 2        (kotlin.Int <1234789>)
```
</ex-own-compose-5>

<hr/>

Enough of feature assertions. Let's move on to an example where we want to reuse an existing function but with different
arguments. Say we have a function which returns a list of first name / last name `Pair`s. 
We want to assert that the pairs contain only the first name / last name pairs of certain `Person` in any order.
[Collection Assertions](#collection-assertions) will help us with this. 
However, `contains.inAnyOrder.values` expects `Pair`s.
So we have to map from `Person` to `Pair` upfront.
As we have a variable length argument list and want to pass it to a variable length argument list, this cannot be done with a simple `map` from Kotlin. 
And it gets worse if we want to use `contains.inAnyOrder.entries` which expects at least one `assertionCreator`-lambda (`Expect<T>.() -> Unit`)
because Kotlin cannot infer the types automatically.

`mapArguments` to the rescue, you can write the assertion function as follows:

<code-own-compose-6>

```kotlin
import ch.tutteli.atrium.domain.builders.utils.mapArguments

fun <T : List<Pair<String, String>>> Expect<T>.areNamesOf(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
    return contains.inAnyOrder.only.values(pair, *otherPairs)
}
```
</code-own-compose-6>

As you can see we moved the mapping inside the function so that the consumer of our API can happily use it as follows:
```kotlin
expect(get...WhichReturnsPairs()).areNamesOf(fKafka, eBloch, kTucholsky)
```

Another fictional example, say we want to assert that the pairs have the same initials as the given persons and in the given order.
Which means, this time we need to use `assertionCreator`-lambdas. This can be written as follows:

<code-own-compose-7>

```kotlin
fun <T : List<Pair<String, String>>> Expect<T>.sameInitialsAs(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (first, others) = mapArguments(person, otherPersons).toExpect<Pair<String, String>> {
        first.startsWith(it.firstName[0].toString())
        second.startsWith(it.lastName[0].toString())
    }
    return contains.inOrder.only.entries(first, *others)
}
```
</code-own-compose-7>

There are a few additional methods which you can call after `mapArguments`.
See [KDoc of ArgumentMapperBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders.utils/-argument-mapper-builder/index.html).
In case you want to provide your own implementation it suffices to create an 
extension function for `ArgumentMapperBuilder`. 

## Enhanced Reporting

[Composing assertion functions](#compose-assertion-functions) give already quite a bit of power to an assertion function writer.
Yet, sometimes we would like to create functions which have a better error reporting than the one we get 
when we compose assertion functions.

[`ExpectImpl`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders/-expect-impl/index.html) 
is the entry point in this case.
Its a builder and thus lets you find the functions you need via code completion.

Following a quick overview what it provides:
- all assertion functions on the domain level (what you have seen in [Compose-assertion-functions](#compose-assertion-functions) 
was the API level) so that you can reuse and compose them in other ways.
- `ExpectImpl.builder` to create different kinds of assertions (see [AssertionBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) for more information).
- `ExpectImpl.changeSubject` which allows to change the subject either:
   - `unreported`; meaning it does not show up in reporting (e.g. `Expect<Array<out T>>.asIterable()` uses it, see [arrayAssertions](https://github.com/robstoll/atrium/tree/master/apis/fluent-en_GB/atrium-api-fluent-en_GB-common/src/main/kotlin/ch/tutteli/atrium/api/cc/en_GB/arrayAssertions.kt#L17))
   - reported, using `reportBuilder`; meaning a subject transformation which is shown in reporting as it incorporates a transformation (e.g. `isA` uses it, see [anyAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/anyAssertions.kt#L62))
- `ExpectImpl.collector` which allows to collect assertions - especially helpful in creating explanatory assertions (see [mapAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/mapAssertions.kt#L41))
- `ExpectImpl.feature.extractor` for feature assertions which are not always save to extract (see [`List.get`](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt))   

You can find an example in [floatingPointAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/floatingPointAssertions.kt#L33)
which makes use of explanatory assertions as well as providing a failure hint.

Unfortunately we do not have the time to cover all cases, so let me know  if you want to know more
-- either by opening an issue or via the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](https://slack.kotlinlang.org/)).

## Own Sophisticated Assertion Builders

Do you want to write an own sophisticated assertion builder (or extend a current with more options) instead of an assertion function?
Great, we do not provide hands on documentation yet (had only one question about it so far). 
Therefore, please have a look at the implementation, for instance how the sophisticated assertion builders for `Iterable<T>` are defined:
[ch.tutteli.atrium.creating.iterable.contains](https://github.com/robstoll/atrium/tree/master/domain/api/atrium-domain-api-common/src/main/kotlin/ch/tutteli/atrium/domain/creating/iterable/contains).
Notice that the implementation supports [Internationalization](#internationalization-1).

We are willing to provide more documentation if you need it (please open an issue). 
In the meantime we might help you via slack, please post your questions in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet).


# Use own Assertion Verbs

Atrium offers three assertion verbs out of the box: `expect`, `assert` and `assertThat`. 

But you can also define your own set of assertion verbs if they do not suite you or if you do not want that all of them are available in your classpath.
In order to create an own assertion verb it is sufficient to:
 1. Copy the file content of [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/master/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch/tutteli/atrium/api/verbs/internal/atriumVerbs.kt)
 2. Create your own atriumVerbs.kt and paste the previously copied content 
    -- notice that you can also use a `String` for the assertion verb in case you do not care about [Internationalization](#internationalization-1)
 3. Adjust package name and `import`s and rename `expect` as desired (you can also leave it that way of course).
 4. exclude `atrium-verbs` from your dependencies. 
    Taking the setup shown in the [Installation](#installation) section, you would replace the `dependencies` block as follows:
    ```
    dependencies {
        testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atrium_version") {
            exclude group: 'ch.tutteli.atrium', module: 'atrium-verbs'
        }
    }
    ```

You could also choose to have different verbs for the three functions.
For instance, you could use `expect` to postulate assertions about thrown `Throwable`s and `assert` for other assertions.

What are the benefits of creating own assertion verbs:
- you can limit the set of available assertion verbs. <br/>
  Say you want that everyone uses `expect` but not `assert` nor `assertThat`, removing them is surely a better option than using a linter.
- you can encapsulate the reporting style. <br/>
  This is especially useful if you have multiple projects and want to have a consistent reporting style.  
  For instance, you could change from same-line to multi-line reporting or report not only failing but also successful assertions, change the output language etc.
  
    <details>
    <summary>💬 where should I put the atriumVerbs.kt?</summary>
    
    We suggest you create an adapter project for Atrium where you specify the assertion verbs. 
    And most likely you will accumulate them with assertion functions which are so common 
    that they appear in multiple projects -- please share them with us (get in touch with us via issue or slack) if they are not of an internal nature 😉
    
    <hr/>
    </details>
 
 
What are the drawbacks:
- you have to maintain your assertion verbs. That should not be a big deal 
  -- you might have to replace deprecated options by their replacement when you upgrade to a newer Atrium version but that's about it.


## ReporterBuilder

The `ReporterBuilder` lets you choose among different options to configure the style of the reporting.
For instance, in case you are not happy with the predefined bullet points, then you can change them via the `ReporterBuilder`.
Have a look at [atriumVerbs.kt of atrium-api-infix-en_GB](https://github.com/robstoll/atrium/tree/master/apis/infix-en_GB/atrium-api-infix-en_GB-jvm/src/test/kotlin/ch/tutteli/atrium/api/infix/en_GB/testutils/AsciiBulletPointReporterFactory.kt)
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
  10        (kotlin.Int <934275857>)
◆ to be: 
  9        (kotlin.Int <1364913072>)
```
instead of:
```
expect: 10        (kotlin.Int <934275857>)
◆ to be: 9        (kotlin.Int <1364913072>)
```

You prefer another reporting style but Atrium does not yet support it? 
Please let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).


There are more options to choose from. 
It does not matter if you use your [own assertion verb](#use-own-assertion-verbs) or a predefined one.
You can provide your custom configured `Reporter` by providing a `ReporterFactory`.
This is done via [ServiceLoader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html) -mechanism on JVM 
and by calling `registerService` on JS where the call has to be before your tests run.  
An example for JVM is given in [atriumVerbs.kt of atrium-api-infix-en_GB](https://github.com/robstoll/atrium/tree/master/apis/infix-en_GB/atrium-api-infix-en_GB-jvm/src/test/kotlin/ch/tutteli/atrium/api/infix/en_GB/testutils/AsciiBulletPointReporterFactory.kt).
An example of how you can make sure your code is called earlier than the tests run is given in [testSetup.kt of atrium-core-robstoll-lib](https://github.com/robstoll/atrium/tree/master/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/testSetup.kt).

# Internationalization

We distinguish between two use cases. 
You might want to generate the [Report](#report) in a different language or/and you might want to use the [API in a different language](#api-in-a-different-language). 

## Report
Following on the example in [Write own Assertion Functions](#write-own-assertion-functions)
we show here how you need to write the `isMultipleOf` function, so that it supports i18n. 
This way the report could be generated in another language.

The difference lies in the first argument passed to `createAndAddAssertion`; 
we do no longer use a `String` but a proper `Translatable`. 

<code-i18n-1>

```kotlin
fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
    createAndAddAssertion(DescriptionIntAssertion.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertion(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
```
</code-i18n-1>

Typically you would put `DescriptionIntAssertion` into an own module (jar) 
so that it could be replaced (with zero performance cost) by another language representation.
For instance,
[atrium-fluent-en_GB-common](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB-common/build.gradle)
uses `atrium-translations-en_GB-common` whereas 
tests of 
[atrium-infix_en_GB-common](https://github.com/robstoll/atrium/tree/master/bundles/infix-en_GB/atrium-infix-en_GB-common/build.gradle)
uses `atrium-translations-de_CH-common`.  

<details>
<summary>💬 Using a TranslationSupplier</summary>

Next to providing translations via code you can also use a 
[TranslationSupplier](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translation-supplier/index.html)
based [Translator](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html)
by configuring the [`ReporterBuilder`](#reporterbuilder) accordingly (e.g. use `withDefaultTranslationSupplier` instead of `withoutTranslations`). 
Atrium supports a properties files based `TranslationSupplier` for JVM (a supplier for JS will follow) which is more or less what
[ResourceBundle](https://docs.oracle.com/javase/tutorial/i18n/resbundle/propfile.html)
provides out of the box. 
Yet, a `Translator` uses a more enhanced fallback mechanism compared to a `ResourceBundle`. 
For further technical information have a look at the KDoc of [Translator](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html).
Notice though, that we plan to move away from the `ResourceBundle`-inspired approach
 due to enconding problems and missing implementations on other platforms than JVM.

Notice, Atrium does not yet support the generation of multiple reports in the same test run. 
This might become handy if you want to generate an HTML report in different languages.   
However, Atrium is designed to support this use case -- if you need this feature, then please let me know it by writing a 
[feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

<hr/>
</details><br/>

Let us rewrite the `isEven` assertion function from the section [Write own Assertion Functions](#write-own-assertion-functions)
as second example:

<code-i18n-2>

```kotlin
fun Expect<Int>.isEven(): Expect<Int> =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create(DescriptionIntAssertions.EVEN)) { it % 2 == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
```
</code-i18n-2>

Once again we have to wrap the text which we want to be able to exchange with another language into a `Translatable`. 
Since we want that the translation as such is treated as a raw string in reporting, we wrap it into a `RawString` as we did before. 
Notice also, that we are reusing a `Translatable` from `DescriptionBasic`.

## API in a different Language

Following on the example in the previous section, 
we want to write `isMultipleOf` in such a way that one cannot only generate a report in a different language
but also that one can use the function itself in a different language. 
Or in other words, provide our API in a different language (the same applies if you want to provide another API style).

We split up the function in two parts: API and implementation 
-- whereas the implementation creates the assertion and the API provides a function for the user (the API as such) and
merely adds the assertion created by the implementation to the `Expect`.
 
Typically you put the API function in one module (jar) and the implementation in another (so that the API can be exchanged).
In the implementation module we define, what we will call hereafter an impl-function.
We follow the convention that impl-functions are prefixed with `_` 
-- this way the chance that it shows up in code completion, e.g. when a developer starts to type `is`, is very low):

<code-i18n-3a>

```kotlin
fun _isMultipleOf(container: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(container, DescriptionIntAssertion.IS_MULTIPLE_OF, base) {
        it % base == 0
    }
```
</code-i18n-3a>

Notice that the impl-function is not an extension function as before 
because we do not want to pollute the API of `Expect<Int>` with this function.
In the above example we created a simple [DescriptiveAssertion](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions/-descriptive-assertion/index.html)
(`createAndAddAssertion` does the same under the hood)
with a test which defines whether the assertion holds as well as a description (`IS_MULTIPLE_OF`) and a representation (`base`).

[`ExpectImpl`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders/-expect-impl/index.html)
helps you in writing own assertion functions. 
We suggest you use it as entry point (rather than memorizing different class names), 
it guides you to existing assertion function implementations for different types 
as well as to other builders such as the [`AssertionBuilder`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html)
which in turn helps you with creating assertions.

In the API module we define the extension function and call the impl-function:

<code-i18n-3b>

```kotlin
fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
    addAssertion(_isMultipleOf(this, base))
```
</code-i18n-3b>

```kotlin


```
We do no longer have to create the assertion as in the example of
[Write own Assertion Functions](#write-own-assertion-functions).
Therefore we use the `addAssertion` method and call the impl-function which will create the assertion for us.

You are ready to go, creating an API in a different language -- e.g. in German -- is now only a routine piece of work:

<code-i18n-3c>

```kotlin
fun Expect<Int>.istVielfachesVon(base: Int): Expect<Int> =
    addAssertion(_isMultipleOf(this, base))
```
</code-i18n-3c>

<details>
<summary>💬 Atrium has more layers</summary>

If you have a look at existing assertion functions and try to reach the impl-function from the API
then you will see that they use `ExpectImpl` and that a few more indirections were introduced into Atrium. 
An API call looks more or less as follows: <br/>
`API -> ExpectImpl -> ServiceLoader -> Service -> Implementation`

The reasons behind this are simple, you could exchange a `Service` with another service if you want.
A service could also reuse parts of the `Implementation` 
(that is why the `Service` delegates to the `Implementation` rather than implementing it itself).

</details>

<a name="apis"></a>
# API Styles
Atrium supports currently two API styles: pure `fluent` and `infix`.
Both have their design focus on interoperability with code completion functionality of your IDE 
-- so that you can let your IDE do some of the work.

Atrium is 
[built up by different modules](https://docs.atriumlib.org/latest#/doc/) 
and it is your choice which implementation you want to use. 
However, this is more intended for advanced user with special requirements.
Atrium provides bundle modules which bundle API, translation, domain and core as well as predefined assertion verbs,
so that you just have to have a dependency on one of those bundles (kind a bit like a BOM pom in the maven world):

- [atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB-common/build.gradle)
- [atrium-infix-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/infix-en_GB/atrium-infix-en_GB-common/build.gradle)

Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
for more information and to see how the API styles differ.
 

# Java Interoperability
Atrium provides some helper functions in case you have to deal with Java Code where not all types are non-nullable. 
[Platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
are turned into a non-nullable version per default (if possible). 

Yet, that might not be what you want, especially if you know that certain functions return potentially `null` 
or in other words, the return type of those functions should be treated as nullable in Kotlin. 
Therefore you want to turn the platform type into the nullable version. 

You need to use a cast to do this. But depending on your return type this might be cumbersome especially if you deal with type parameters. 
Thus, Atrium provides the following functions to ease dealing with Java Code at least for some standard cases:
- [`nullable`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L19)
  turns a type into a nullable type.
- [`nullableContainer`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#40)
  turns an `Iterable` into an iterable with nullable element type, likewise it does the same for `Array`.
- [`nullableKeyMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L66)
  turns a `Map` into a map with a nullable key type.
- [`nullableValueMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L79)
  turns a `Map` into a map with a nullable value type.
- [`nullableKeyValueMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L92)
  turns a `Map` into a map with a nullable key and nullable value type. 
    
 
# KDoc - Code Documentation
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://docs.atriumlib.org/)

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out-of-the-box assertion functions. 
More functions will follow but only if they are used somewhere by someone. 
So, let me know if you miss something by creating a [feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
Some assertion functions which we miss ourselfs will follow in the next version. 
They are listed in the [Roadmap](#roadmap) below.

Atrium does especially not support (yet):
- specific JSON assertion functions (yet, everything is there as soon as you parse the JSON into a Map/Object)

# FAQ
You find frequently asked questions below.
If your question is not answered below, then please do not hesitate and ask your question in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ).
In case you do not have an account for kotlinlang.slack.com yet, then please [Invite yourself](https://slack.kotlinlang.org/). 

## Are there contains/any/none/all assertions for `Sequence`/`Array`?

Atrium does not provide extension function applicable to `Expect<Sequence<E>>` (or `Array`) directly,
because they would basically duplicate the functions available for `Iterable<E>`.  
However, Atrium provides `asIterable` so that you can turn `Expect<Sequence<E>>` 
into `Expect<Iterable<E>>`. An example:

<code-faq-1>

```kotlin
expect(sequenceOf(1, 2, 3)).asIterable().contains(2)
```
</code-faq-1>

Likewise you can turn an `Expect<Array<E>>`, `Expect<DoubleArray>` etc. into an `Expect<Iterable<E>>`.

<details>
<summary>💬 why do I not see anything about the transformation in reporting?</summary>

`asIterable` uses `ExpectImpl.changeSubject.unreported` internally which is intended for not showing up in reporting.
If you would like that the transformation is reflected in reporting then you can use a regular feature assertion 
as follows:

<code-faq-2>

```kotlin
expect(sequenceOf(1, 2, 3)).feature { f(it::asIterable) }.contains(2)
```
</code-faq-2>

</details>

## Where do I find a list of all available functions?

Atrium provides KDoc for all APIs - have a look at their KDoc:
- [atrium-api-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html)
- [atrium-api-infix-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.infix.en_-g-b/index.html)

Deprecated APIs:
- [atrium-api-cc-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/index.html)
- [atrium-api-cc-de_CH](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.de_-d-e/index.html)
- [atrium-api-cc-infix-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.infix.en_-g-b/index.html)

## Problems in conjunction with `feature`

See [Ambiguity Problems](#ambiguity-problems) and [Property does not exist](#property-does-not-exist).

# Kotlin Bugs
The following issues hinder Atrium to progress in certain areas or they are the reason that we cannot use Atrium as intended in all cases. 
Please upvote them (especially if you encounter them yourself):
- [Symbol is declared in unnamed module](https://youtrack.jetbrains.com/issue/KT-35343)
- [Gradle runtimeOnly bug](https://youtrack.jetbrains.com/issue/KT-21685) (reason that you see functions from package cc.en_GB when using cc.infix.en_GB)
- [navigate to source or show KDoc for overloaded extension function](https://youtrack.jetbrains.com/issue/KT-24836)
- [Lower bounds](https://youtrack.jetbrains.com/issue/KT-209), i.a. that functions intended for nullable subject do not show up on non-nullable subjects.
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
- [Type inference unable to infer primitive type](https://youtrack.jetbrains.com/issue/KT-33290)
- [Overload resolution null bug](https://youtrack.jetbrains.com/issue/KT-6591) (reason why you need to specify what type `null` is in the infix API when using `assert(listOf(...)) contains null`)
- [Extension resolution null as receiver bug](https://youtrack.jetbrains.com/issue/KT-30496) (reason why you need to define that `null to null` is a Pair in the infix API)
- [Overload resolution nullable bug](https://youtrack.jetbrains.com/issue/KT-23768)
- [Overload resolution primitive type bug](https://youtrack.jetbrains.com/issue/KT-24230)
- [Overload resolution function type bug](https://youtrack.jetbrains.com/issue/KT-23883)
- [Overload resolution generic upper bound bug](https://youtrack.jetbrains.com/issue/KT-30235)
- [Overload ambiguity between val and fun](https://youtrack.jetbrains.com/issue/KT-32958)
- [false positive: remove explicit type arguments](https://youtrack.jetbrains.com/issue/KT-32869)
- [Wrong JS generated in case of name clash](https://youtrack.jetbrains.com/issue/KT-33294)
- [forbid function types as substitute of reified types ](https://youtrack.jetbrains.com/issue/KT-27846)
- [forbid parameterised types as substitute of reified types](https://youtrack.jetbrains.com/issue/KT-27826)
- [ReplaceWith does not add type parameter](https://youtrack.jetbrains.com/issue/KT-33685)
- [Wrong warning about predetermined type parameter](https://youtrack.jetbrains.com/issue/KT-34257)

And some features which would be handy
- [hide function with deprecation level error in code completion](https://youtrack.jetbrains.com/issue/KT-25263)
- [Method reference without `this`](https://youtrack.jetbrains.com/issue/KT-22920)
- [Infix function call with type parameters](https://youtrack.jetbrains.com/issue/KT-21593)
- [Extensibility for infix API](https://youtrack.jetbrains.com/issue/KT-27659)
- [Summarising overloads in code completion](https://youtrack.jetbrains.com/issue/KT-25079) 
- [vararg for lambdas](https://youtrack.jetbrains.com/issue/KT-24287)
- [delegate with inline modifier](https://youtrack.jetbrains.com/issue/KT-23241)


# Roadmap

We plan that Atrium is going to support certain features in the future. Following a rough plan (no guarantees).
A more detailed backlog can be found at [atrium-roadmap](https://github.com/robstoll/atrium-roadmap) -- you are invited to take part in the discussions related to design decisions, upcoming features and more.

## 0.9.0
- introduce `Expect<T>` with an invariant `T` (see [#56](https://github.com/robstoll/atrium/issues/56), the current solution with `Assert<out T>` will be deprecated and removed with 1.0.0) 
- introduce `feature` instead of `property` and `returnValueOf` (see [#40](https://github.com/robstoll/atrium/issues/40))
- optionally, introduce jdk8 specific assertion functions, e.g. for `Optional` or `Path`
- ❗❗ drop the `de_CH` API, I might take it up again if there are votes for [#137](https://github.com/robstoll/atrium/issues/137)

## 0.10.0
- add assertion functions specific to Kotlin 1.3 in a separate API modules (compatiblity with 1.2 will stay until 1.0.0)
- move away from ResourceBundle/Properties-based translation to something more MPP friendly (e.g. gettext).
- refactor core and domain architecture - most likely we are going to move away from ServiceLoader and replace `-robstoll` and `-robstoll-lib` modules with one `-impl` module
- fix verbosity issues in conjunction with feature assertions and explanatory groups.
- provide an easy way to create failure hints.

## 0.11.0
- Json assertions (state your wishes in [#45](https://github.com/robstoll/atrium/issues/45))
  
## 0.12.0  
- see if we can further improve error reporting in the IDE with the help of opentest4j exceptions.
- Generating testing reports in html.
  - generate multiple reports in the same test run. 
  
## 1.0.0
- drop support for Kotlin 1.2
- include assertion functions specific to Kotlin 1.3 into normal API
  
## Sometime in the future
- extension for Spek so that reporting includes the `describe`, `it` etc.
- Inclusion of mockk's verify (so that it appears in the report as well).
    
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
  so that we better understand where Atrium needs to improve.
- write a blog post about Atrium (e.g. about a feature you like) or a tutorial (let us know we happily link to your page)
- share your assertion functions with the rest of us by creating a pull request (no need for i18n support or the like, we can augment your pull request).
- have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  if you would like to code (ping me on [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) if there are not any).  

Please have a look at 
[CONTRIBUTING.md](https://github.com/robstoll/atrium/tree/master/.github/CONTRIBUTING.md)
for further suggestions and guidelines.

# Sponsors
We like to thank the following sponsors for their support:
- [Tegonal GmbH](https://tegonal.com) for sponsoring Support and PR-Review time.

Want to become a sponsor as well? Great, have a look at the following GitHub sponsor profiles:
- [robstoll](https://github.com/sponsors/robstoll) (Author and main contributor)

or ping @robstoll in the Slack-Channel if you would like to support the project in another way.

# License
Atrium is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Atrium is using:
- [KBox](https://github.com/robstoll/kbox) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
- [Niok](https://github.com/robstoll/niok) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
