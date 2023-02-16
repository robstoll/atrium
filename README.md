<!-- for main -->

[![Download](https://img.shields.io/badge/Download-0.18.0-%23007ec6)](https://search.maven.org/artifact/ch.tutteli.atrium/atrium-fluent-en_GB/0.18.0/jar)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![atrium @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=atrium&color=blue&logo=slack)](https://kotlinlang.slack.com/messages/atrium "See invitation link under section FAQ")
[![Build Status Ubuntu](https://github.com/robstoll/atrium/workflows/Ubuntu/badge.svg?event=push&branch=main)](https://github.com/robstoll/atrium/actions?query=workflow%3AUbuntu+branch%3Amain)
[![Build Status Windows](https://github.com/robstoll/atrium/workflows/Windows/badge.svg?event=push&branch=main)](https://github.com/robstoll/atrium/actions?query=workflow%3AWindows+branch%3Amain)
[![Coverage](https://codecov.io/gh/robstoll/atrium/branch/main/graph/badge.svg)](https://codecov.io/github/robstoll/atrium/branch/main) 
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in slack for help")

<!-- for a specific release -->
<!--
[![Download](https://img.shields.io/badge/Download-0.18.0-%23007ec6)](https://search.maven.org/artifact/ch.tutteli.atrium/atrium-fluent-en_GB/0.18.0/jar)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![atrium @ kotlinlang.slack.com](https://img.shields.io/static/v1?label=kotlinlang&message=atrium&color=blue&logo=slack)](https://kotlinlang.slack.com/messages/C887ZKGCQ "See invitation link under section FAQ")
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in slack for help")
-->

# <img src="https://raw.githubusercontent.com/robstoll/atrium/gh-pages/logo.svg?sanitize=true" alt="Atrium" title="Atrium"/>
Atrium is an open-source multiplatform expectation/assertion library for Kotlin with support for JVM, JS and Android.
It is designed to support multiple [APIs](#api-styles), focuses on helping developers to understand what went wrong and prevents common pitfalls. 
The project was inspired by AssertJ at first but moved on and provides now more 
flexibility, features and hints to its users (so to you 😉).

Atrium is designed to be extensible as well as configurable 
and allows you to extend it with your own expectation functions, customise reporting 
or even replace core components with your own implementation easily.

See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

---
❗ You are taking a *sneak peek* at the next version. It could be that some features you find on this page are not released yet.  
Please have a look at the README of the corresponding release/git tag -- latest version: [README of v0.18.0](https://github.com/robstoll/atrium/tree/v0.18.0/README.md).

---

**Table of Content**
- [Installation](#installation)
  - [JVM](#jvm)
  - [JS](#js)
  - [Android](#android)
  - [Common](#common)
- [Examples](#examples)
  - [Your First Expectation](#your-first-expectation)
  - [Define Single Expectations or an Expectation-Group](#define-single-expectations-or-an-expectation-group)
  - [Expect an Exception](#expect-an-exception)
  - [Feature Extractors](#feature-extractors)
    - [Property and Method](#property-and-methods)
    - [Arbitrary Features](#arbitrary-features)
  - [Type Expectations](#type-expectations)
  - [Nullable Types](#nullable-types)
  - [Collection Expectations](#collection-expectations)
    - [Shortcut Functions](#shortcut-functions)
    - [Sophisticated Expectation Builders](#sophisticated-expectation-builders)
  - [Map Expectations](#map-expectations)
    - [Shortcut Functions](#shortcut-functions-1)
    - [Sophisticated Expectation Builders](#sophisticated-expectation-builders-1)
    - [Others](#others)
  - [Path Expectations](#path-expectations)
  - [Attaching a Reason](#attaching-a-reason)
  - [Data Driven Testing](#data-driven-testing)
  - [Further Examples](#further-examples)  
  - [Sample Projects](#sample-projects)
- [Third-party Extensions](#third-party-extensions)    
- [How is Atrium different from other Expectation/Assertion Libraries](#how-is-atrium-different-from-other-expectationassertion-libraries)
- [Write own Expectation Functions](#write-own-expectation-functions)
    - [Boolean based Expectation Functions](#boolean-based-expectation-functions)
    - [Compose Functions](#compose-expectation-functions)
    - [Enhanced Reporting](#enhanced-reporting)
    - [Own Sophisticated Expectation Builders](#own-sophisticated-expectation-builders)
- [Use own Expectation Verb](#use-own-expectation-verb)
  - [Use own Components](#use-own-components)
- [Internationalization](#internationalization)
- [API Styles](#api-styles)
- [Java Interoperability](#java-interoperability)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [FAQ](#faq)
- [Roadmap](#roadmap)
- [Contributors and contribute](#contributors-and-contribute)
- [Sponsors](#sponsors)
- [License](#license)

# Installation

## JVM
Atrium is published to [mavenCentral](https://search.maven.org/search?q=g:ch.tutteli.atrium). 

*build.gradle.kts*: 
```
repositories {
    mavenCentral()
}
val atriumVersion by extra("0.18.0")
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atriumVersion")
}
```
We have defined a dependency to the bundle `atrium-fluent-en_GB` in the above example 
which provides a pure fluent API (in en_GB) for the JVM platform.   

Have a look at the [JVM sample projects](https://github.com/robstoll/atrium/tree/main/samples/jvm) for a quick setup, or
[Maven sample project](https://github.com/robstoll/atrium/tree/main/samples/maven) if you prefer Maven to Gradle.

We currently provide the following extensions for the JVM platform: 
- kotlin_1_3: expectation functions for Kotlin 1.3 specific types (e.g. for [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)). 

You can enable them as follows:
```
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-api-fluent-en_GB-kotlin_1_3:$atriumVersion")
}
```

Also take a look at [Third-party Extensions](#third-party-extensions) which might come in handy as well.

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
repositories {
    mavenCentral()
}
val atriumVersion by extra("0.18.0")
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-infix-en_GB:$atriumVersion")
}
```

And for the aforementioned extensions:
```
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-api-infix-en_GB-kotlin_1_3:$atriumVersion")
}
```

<hr/>
</details>
<br/>

*maven*:  
Because maven is a bit more verbose than gradle, the example is not listed here but a 
[sample maven project](https://github.com/robstoll/atrium/tree/main/samples/maven)
is provided which shows all necessary setup.

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.

## JS

*build.gradle.kts*:
```
repositories {
    mavenCentral()
}
val atriumVersion by extra("0.18.0")
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:$atriumVersion")
}
```

We have defined a dependency to the bundle `atrium-fluent-en_GB-js` in the above example 
which provides a pure fluent API (in en_GB) for the JS platform.

Have a look at the [JS sample projects](https://github.com/robstoll/atrium/tree/main/samples/jvm) for a quick setup.

Otherwise, you need to setup an explicit dependency on `atrium-fluent-en_GB-js` in your test code in order that you can use Atrium.
This is due to the loosely coupled design of Atrium and dead code elimination performed by the Kotlin compiler for JS.

Atrium itself is using mocha as well 
(see [build.gradle -> createJsTestTask](https://github.com/robstoll/atrium/tree/main/build.gradle#L290))
and has tests written in JS modules 
(see [AdjustStackTest](https://github.com/robstoll/atrium/tree/main/core/atrium-core-js/src/test/kotlin/ch/tutteli/atrium/reporting/erroradjusters/AdjustStackTest.kt))
as well as tests written in common modules (e.g. [SmokeTest](https://github.com/robstoll/atrium/tree/main/bundles/fluent-en_GB/atrium-fluent-en_GB-common/src/test/kotlin/SmokeTest.kt))
which are executed on the JS platform as well 
(actually on all platforms -> JVM uses JUnit for this purpose, see 
[build.gradle -> useJupiter](https://github.com/robstoll/atrium/tree/main/build.gradle#L342)).

We currently provide the following extensions for the JS platform: 
 - kotlin_1_3: expectation functions for Kotlin 1.3 specific types (e.g. for [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)). 

You can enable them as follows:
```
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-api-fluent-en_GB-kotlin_1_3-js:$atriumVersion")
}
```

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
repositories {
    mavenCentral()
}
val atriumVersion by extra("0.18.0")
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-infix-en_GB-js:$atriumVersion")
}
```

and for the aforementioned extensions:
```
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-api-infix-en_GB-kotlin_1_3:$atriumVersion")
}
```
<hr/>
</details>

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.

## Android

Starting with 0.12.0 we no longer deliver a dedicated `-android` jar. Instead, you can use the same setup as shown in [JVM setup](#jvm).
We start adding one again in case we have Android specific expectation functions. 

Also take a look at [Third-party Extensions](#third-party-extensions) which might come in handy as well.

## Common

The setup for using Atrium in a common module of a multiplatform project is basically the same as for the
[JVM setup](#jvm), you only need to suffix the dependency with `-common` in addition. 
For instance `atrium-fluent-en_GB-common` instead of `atrium-fluent-en_GB`.

Have a look at [JVM](#jvm), [JS](#js) or [Android](#android) to see how the setup of a specific platform has to be done.
You might want to have a look at the [Multiplatform sample project](https://github.com/robstoll/atrium/tree/main/samples/multiplatform)
as well for a quick setup.

# Examples
We are using the API provided by the bundle module 
[atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/main/bundles/fluent-en_GB/atrium-fluent-en_GB/build.gradle)
in the following examples. 
It provides a pure fluent API for the JVM platform.
Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/main/apis/differences.md)
to see how the infix API looks like, how they differ respectively.

## Your First Expectation
We start off with a simple example:

<ex-first>

```kotlin
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect

val x = 10
expect(x).toEqual(9)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FirstExampleSpec.kt#L31)</sub> ↓ <sub>[Output](#ex-first)</sub>
<a name="ex-first"></a>
```text
I expected subject: 10        (kotlin.Int <1234789>)
◆ to equal: 9        (kotlin.Int <1234789>)
```
</ex-first>

The statement can be read as "I expect x to equal nine" where an equality check is used (for an identity check, you would have to use `toBeTheSameInstace`). 
Since this is false, an `AssertionError` is thrown with a corresponding message as shown in the [Output](#ex-first)
where on the first line the actual subject  (`10` in the above example) is shown and on following lines which start with, 
`◆ ...`  (here only one) we see the expectations we had about the subject
In this sense the report can be read as `I expected the subject of the expectation, which was 10, to equal 9` 
-- and needlessly to say, this expectation was not met and thus the thrown error.

We are using the bundle [atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/main/bundles/fluent-en_GB/atrium-fluent-en_GB/build.gradle)
and the predefined expectation verb `expect` in the examples. 
Thus, the corresponding `import`s at the beginning of the file in the above example.
We will omit the `import` statements in the remaining examples for brevity. 

**You want to run the examples yourself?**
Have a look at the [Installation](#installation) section which explains how to set up a dependency to Atrium.

The next section shows how you can define multiple expectations for the same subject.

## Define Single Expectations or an Expectation-Group
<ex-single>

```kotlin
// two single expectations, only first evaluated
expect(4 + 6).toBeLessThan(5).toBeGreaterThan(10)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L24)</sub> ↓ <sub>[Output](#ex-single)</sub>
<a name="ex-single"></a>
```text
I expected subject: 10        (kotlin.Int <1234789>)
◆ to be less than: 5        (kotlin.Int <1234789>)
```
</ex-single>

Atrium allows you to chain expectations or in other words
you only need to write the `expect(...)` part once and can state several single expectations for the same subject.
The expression which determines the subject of the expectations (`4 + 6` in the above example) is evaluated only once. 

In this sense we could have written it also as follows (which is only the same because `4 + 6` does not have side effects).

<code-single-explained>

```kotlin
expect(4 + 6).toBeLessThan(5)
expect(4 + 6).toBeGreaterThan(10)
```
</code-single-explained>

The first `expect` statement throws an `AssertionError` as it does not hold. 
In the above example, `toBeLessThan(5)` is already wrong and thus `toBeGreaterThan(10)` was not evaluated at all 
and correspondingly not reported.

<a name="expecation-groups-are-better-soft-assertions"></a>
If you want that both expectations are evaluated together, then use the expectation-group syntax as follows:
 
<ex-group>

```kotlin
// expectation-group with two expectations, both evaluated
expect(4 + 6) {
    toBeLessThan(5)
    toBeGreaterThan(10)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L40)</sub> ↓ <sub>[Output](#ex-group)</sub>
<a name="ex-group"></a>
```text
I expected subject: 10        (kotlin.Int <1234789>)
◆ to be less than: 5        (kotlin.Int <1234789>)
◆ to be greater than: 10        (kotlin.Int <1234789>)
```
</ex-group>

An expectation-group throws an `AssertionError` at the end of its block (i.e. at the closing `}`); 
hence reports that both expectations do not hold.
The reporting can be read as `I expected the subject of the expectation, which was 10, to be less than 5 and to be greater than 10`

This is similar to the concept of soft assertions in AssertJ with the difference that you do not need an extra utility,
and you do not have to repeat the subject.
The above is the equivalent of the following AssertJ example:
```kotlin
assertSoftly {
    assertThat(4 + 6).isLessThan(5)
    assertThat(4 + 6).isGreatThan(10)
}

fun assertSoftly(body: SoftAssertions.() -> Unit) =
    SoftAssertions.assertSoftly(body)
```

Moreover, in contrast to AssertJ, the block syntax is provided at many places and not only on the top-level. 
As an example, the following AssertJ example:
```kotlin
assertSoftly {
    assertThat(mansion.numOfGuests).isEqualTo(7)
    assertThat(mansion.kitchen.stastus).isEqualTo("clean")
    assertThat(mansion.kitchen.numOfTables).isGreaterThan(5).isLessThan(10)
}

fun assertSoftly(body: SoftAssertions.() -> Unit) =
    SoftAssertions.assertSoftly(body)
```
could be written as follows in Atrium (see also [Feature Extractors](#feature-extractors)). 
```kotlin
expect(mansion) {
    its { numOfGuests }.toEqual(7)
    its({ kitchen }) {
        its { status }.toEqual("clean")
        its { numOfTables }.toBeGreaterThan(5).toBeLessThan(10)
    }
}
```

Note that you are free to choose a fail-fast behaviour at any level. For instance, above we have used the single
expectation syntax for `toBeGreaterThan(5).toBeLessThan(10)` and thus `toBeLessThan(10)` will not show up in reporting
if `toBeGreaterThan(5)` already fails.

<hr/>

You can use `and` as filling element between single expectations and expectation-groups:

<code-and>

```kotlin
expect(5).toBeGreaterThan(2).and.toBeLessThan(10)

expect(5) {
    // ...
} and { // if the previous block fails, then this one is not evaluated
    // ...
}
```
</code-and>
 
## Expect an Exception
<ex-toThrow1>

```kotlin
expect {
    // this lambda does something but eventually...
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException>()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L64)</sub> ↓ <sub>[Output](#ex-toThrow1)</sub>
<a name="ex-toThrow1"></a>
```text
I expected subject: () -> kotlin.Nothing        (readme.examples.MostExamplesSpec$1$7$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ to be an instance of type: IllegalStateException (java.lang.IllegalStateException)
    ℹ Properties of the unexpected IllegalArgumentException
      » message: "name is empty"        <1234789>
      » stacktrace: 
        ⚬ readme.examples.MostExamplesSpec$1$7$1.invoke(MostExamplesSpec.kt:67)
        ⚬ readme.examples.MostExamplesSpec$1$7$1.invoke(MostExamplesSpec.kt:22)
        ⚬ readme.examples.MostExamplesSpec$1$7.invoke(MostExamplesSpec.kt:280)
        ⚬ readme.examples.MostExamplesSpec$1$7.invoke(MostExamplesSpec.kt:22)
```
</ex-toThrow1>

You can also pass a lambda to `expect` and then use `toThrow` to state the expectation that 
invoking the lambda throws a certain exception (`IllegalStateException` in the example above).

As with all narrowing functions, there are two overloads:
- the first expects an `assertionCreator`-lambda in which you can define sub-expectations.
    An `assertionCreator`-lambda has always the semantic of an [expectation-group](#define-single-expectations-or-an-expectation-group).
    It has also the benefit, that Atrium can show those sub-expectations in error reporting,
    even if a failure happens before, giving some additional context to a failure.
- the second overload expects all the parameters except the `assertionCreator`-lambda and turns the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.


The following example uses the first overload

<ex-toThrow2>

```kotlin
expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException> {
    message { toStartWith("firstName") }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L71)</sub> ↓ <sub>[Output](#ex-toThrow2)</sub>
<a name="ex-toThrow2"></a>
```text
I expected subject: () -> kotlin.Nothing        (readme.examples.MostExamplesSpec$1$8$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ ▶ message: null
        ◾ to be an instance of type: String (kotlin.String) -- Class: java.lang.String
          » to start with: "firstName"        <1234789>
```
</ex-toThrow2>

And this one uses the second overload; notice the difference in reporting, 
this one does not include what sub-expectations would have been made if the narrowing succeeded

<ex-toThrow3>

```kotlin
expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException>().message.toStartWith("firstName")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L79)</sub> ↓ <sub>[Output](#ex-toThrow3)</sub>
<a name="ex-toThrow3"></a>
```text
I expected subject: () -> kotlin.Nothing        (readme.examples.MostExamplesSpec$1$9$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ ▶ message: null
        ◾ to be an instance of type: String (kotlin.String) -- Class: java.lang.String
```
</ex-toThrow3>

As side notice, `message` is a shortcut for `feature(Throwable::message).notToEqualNull`, 
which creates a feature extractor (see next section) about `Throwable::message`.  

There is also the counterpart of `toThrow` named `notToThrow`:

<ex-notToThrow>

```kotlin
expect {
    // this block does something but eventually...
    throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
}.notToThrow()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L85)</sub> ↓ <sub>[Output](#ex-notToThrow)</sub>
<a name="ex-notToThrow"></a>
```text
I expected subject: () -> kotlin.Nothing        (readme.examples.MostExamplesSpec$1$10$1 <1234789>)
◆ ▶ invoke(): ❗❗ threw java.lang.IllegalArgumentException
    ℹ Properties of the unexpected IllegalArgumentException
      » message: "name is empty"        <1234789>
      » stacktrace: 
        ⚬ readme.examples.MostExamplesSpec$1$10$1.invoke(MostExamplesSpec.kt:88)
        ⚬ readme.examples.MostExamplesSpec$1$10$1.invoke(MostExamplesSpec.kt:22)
        ⚬ readme.examples.MostExamplesSpec$1$10.invoke(MostExamplesSpec.kt:89)
        ⚬ readme.examples.MostExamplesSpec$1$10.invoke(MostExamplesSpec.kt:22)
      » cause: java.lang.RuntimeException
          » message: "a cause"        <1234789>
          » stacktrace: 
            ⚬ readme.examples.MostExamplesSpec$1$10$1.invoke(MostExamplesSpec.kt:88)
```
</ex-notToThrow>

Notice that stacks are filtered so that you only see what is of interest. 
You can [use your own](#use-own-components) 
[AtriumErrorAdjuster](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-atrium-error-adjuster/index.html)
to adjust the filtering.
Stack frames of Atrium and of test runners (Spek, Kotest, and JUnit for JVM, mocha and jasmine for JS) are excluded per default.
[Create a Feature Request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
in case you use a different runner, we can add yours to the list as well. 
 
<a name="property-assertions"></a>
<a name="method-assertions"></a>
<a name="feature-assertions"></a>
## Feature Extractors
Many times you are only interested in certain features of the subject and want to state expectations about them. 

There are different use cases for feature extractors. 
We will start of with properties and method calls and go on with more complicated scenarios.

### Property and Methods
We are using the `data class Person` in the following examples:

<code-Person>

```kotlin
data class Person(val firstName: String, val lastName: String, val isStudent: Boolean) {
    fun fullName() = "$firstName $lastName"
    fun nickname(includeLastName: Boolean) = when (includeLastName) {
        false -> "Mr. $firstName"
        true -> "$firstName aka. $lastName"
    }
}

val myPerson = Person("Robert", "Stoll", false)
```
</code-Person>

The simplest way of defining expectations for a property of an instance or for the return value of a method call is by
using the extension method `its`.

<ex-its-single>

```kotlin
expect(myPerson)
    .its({ isStudent }) { toEqual(true) } // fails, subject still Person afterwards
    .its { fullName() }                   // not evaluated anymore, subject String afterwards
    .toStartWith("rob")                   // not evaluated anymore
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L41)</sub> ↓ <sub>[Output](#ex-its-single)</sub>
<a name="ex-its-single"></a>
```text
I expected subject: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.FeatureExtractorSpec$1$Person <1234789>)
◆ ▶ its.definedIn(FeatureExtractorSpec.kt:43): false
    ◾ to equal: true
```
</ex-its-single>

In the above example we created two expectations, one for the property `isStudent` of `myPerson`
and a second one for the return value of calling `fullName()` on `myPerson`.
A feature extractor is indicated as follows in reporting:
It starts with a `▶` followed by the feature's description and its actual value.
So the above output can be read as 

> I expected the subject of the expectation, which was actually Person(...), 
> respectively its property which was defined in FeatureExtractorSpec.kt on line 43, 
> which was actually `false`, to equal `true`.

The second feature is not shown in reporting as the first expectation about the property `isStudent` already failed, 
and we have chosen to use [single expectations](#define-single-expectations-or-an-expectation-group)
which have fail-fast semantic.

Feature extractors follow the common pattern of having two overloads:
- the first expects an `assertionCreator`-lambda, in which you can define sub-expectations for the feature.
    An `assertionCreator`-lambda has always the semantic of an [expectation-group](#define-single-expectations-or-an-expectation-group)
    or in other words, not-fail fast. It has also the benefit, that Atrium can provide those sub-expectations in error reporting.
    Moreover, the subject stays the same so that subsequent calls are still about the same subject
- the second overload expects all the parameters except the `assertionCreator`-lambda and changes the subject to the feature,
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.

  <ex-its-group>
  
  ```kotlin
  expect(myPerson) { // forms an expectation-group
  
      its({ firstName }) {   // forms an expectation-group
          toStartWith("Pe")  // fails
          toEndWith("er")    // is evaluated nonetheless
      }                      // fails as a whole
  
      // still evaluated, as it is in outer expectation-group
      its { lastName }.toEqual("Dummy")
  }
  ```
  ↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L49)</sub> ↓ <sub>[Output](#ex-its-group)</sub>
  <a name="ex-its-group"></a>
  ```text
  I expected subject: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.FeatureExtractorSpec$1$Person <1234789>)
  ◆ ▶ its.definedIn(FeatureExtractorSpec.kt:52): "Robert"        <1234789>
      ◾ to start with: "Pe"        <1234789>
      ◾ to end with: "er"        <1234789>
  ◆ ▶ its.definedIn(FeatureExtractorSpec.kt:58): "Stoll"        <1234789>
      ◾ to equal: "Dummy"        <1234789>
  ```
  </ex-its-group>

<br/>

One drawback of `its` (which we plan to improve but most likely not before we drop support for Kotlin < 1.5) is that reading the resulting
feature description does not immediately tell us what feature we extracted.

That is where the `feature` function comes into play. It is based on reflection and uses the name of the feature 
as description. Following the first example rewritten to `feature`.

<ex-property-methods-single>

```kotlin
expect(myPerson)
    .feature({ f(it::isStudent) }) { toEqual(true) } // fails, subject still Person afterwards
    .feature { f(it::fullName) }                     // not evaluated anymore, subject String afterwards
    .toStartWith("rob")                              // not evaluated anymore
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L63)</sub> ↓ <sub>[Output](#ex-property-methods-single)</sub>
<a name="ex-property-methods-single"></a>
```text
I expected subject: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.FeatureExtractorSpec$1$Person <1234789>)
◆ ▶ isStudent: false
    ◾ to equal: true
```
</ex-property-methods-single>

The report reads much nicer now: 

> I expected the subject of the expectation, 
> which was actually Person(...), respectively its property `isStudent`, 
> which was actually `false`, to equal `true`

The drawback of `feature` compared to `its` is its syntax. Certainly, one has to get used to it first. Another is that
you might run into [Ambiguity Problems](#ambiguity-problems) due to Kotlin bugs.

`feature` has several overloads, we are looking at the one expecting a lambda in which you have to provide a `MetaFeature`.
Creating a `MetaFeature` is done via the function `f` by passing in a 
[bounded reference](https://kotlinlang.org/docs/reference/reflection.html#bound-function-and-property-references-since-11) 
of the corresponding property or method (including arguments if required).
`it` within the `MetaFeature`-provider-lambda refers to the subject of the expectation (`myPerson` in the above example).

Also `feature` follows the common pattern of having two overloads where the first expects an `assertionCreator`-lambda and
the second has the same parameters except the `assertionCreator`-lambda and changes the subject to the feature,
meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
Following the second example rewritten from `its` to `feature`:

<ex-property-methods-group>

```kotlin
expect(myPerson) { // forms an expectation-group

    feature({ f(it::firstName) }) { // forms an expectation-group
        toStartWith("Pe")           // fails
        toEndWith("er")             // is evaluated nonetheless
    }                               // fails as a whole

    // still evaluated, as it is in outer expectation-group
    feature { f(it::lastName) }.toEqual("Dummy")
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L71)</sub> ↓ <sub>[Output](#ex-property-methods-group)</sub>
<a name="ex-property-methods-group"></a>
```text
I expected subject: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.FeatureExtractorSpec$1$Person <1234789>)
◆ ▶ firstName: "Robert"        <1234789>
    ◾ to start with: "Pe"        <1234789>
    ◾ to end with: "er"        <1234789>
◆ ▶ lastName: "Stoll"        <1234789>
    ◾ to equal: "Dummy"        <1234789>
```
</ex-property-methods-group>

Atrium provides several shortcuts for commonly used properties so that you can use them instead of writing `its { ... }` / `feature(...)` all the time.
For instance, `message` for Throwable (see [Expect an Exception](#expect-an-exception)), `first` and `second` for `Pair` and others.
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) in case you miss a shortcut.

💬 &lt;- _this icon signifies answers/input for advanced users, you might want to skip them if you are new to Atrium._<br/>

<details>
<summary>💬 Provide a feature extractor for each property? </summary>

You might be asking yourself whether it is better to [write an own feature extractor](#write-own-expectation-functions) or use `feature`. 

The only drawback of using an existing property is that a few more keystrokes are required compared to 
[writing an own feature extractor](#write-own-expectation-functions) once and then reuse it (as we did with `message`).
Yet, we do not recommend writing an own feature extractor for every single property.
We think it makes sense to add one if you use it a lot and (preferably) it is a stable API. 
Why not always? Because one quickly forgets to rename the feature extractor 
if the property as such is renamed (e.g., as part of an IDE refactoring). 
As you can see, you would need to keep the property name and the name of the feature extractor in sync to be meaningful 
(otherwise one gets quickly confused or has to remember two names for the same thing). 

Writing feature extractors for method calls is a different story though, especially due to [overload bugs in Kotlin](https://github.com/robstoll/atrium/wiki/Kotlin-Bugs-and-missing-features).
Also, code completion is not yet as good as it should be when it comes to methods. 
Last but not least, in case it is not always safe to call a method (e.g. `List.get` => IndexOutOfBound) then it makes
sense to wrap it into an own feature extractor and use `_logic.extractFeature`.

</details>
  
Last but not least, let us have a look at an example where a method with arguments is used as feature:

<ex-methods-args>

```kotlin
expect(myPerson)
    .feature { f(it::nickname, false) } // subject narrowed to String
    .toEqual("Robert aka. Stoll")       // fails
    .toStartWith("llotS")               // not evaluated anymore
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L85)</sub> ↓ <sub>[Output](#ex-methods-args)</sub>
<a name="ex-methods-args"></a>
```text
I expected subject: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (readme.examples.FeatureExtractorSpec$1$Person <1234789>)
◆ ▶ nickname(false): "Mr. Robert"        <1234789>
    ◾ to equal: "Robert aka. Stoll"        <1234789>
```
</ex-methods-args>

`f` supports methods with up to 5 arguments.

Atrium provides shortcuts for commonly used methods, e.g. `List.get`, `Map.getExisting`, `Optional.toBePresent` or `Result.toBeSuccess` 
where all of them include some additional checking (index bound, existence of the key within the map etc.)
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) 
in case you miss a shortcut. 

<details>
<summary>💬 Write own feature extractors with additional checks.</summary>

Atrium provides a feature extractor which allows to extract in a safe way in case the extraction is only valid for certain subjects.
It is inter alia used for [`List.get`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/impl/DefaultListAssertions.kt#L13)

</details>

### Arbitrary Features
A feature does not necessarily have to be directly related to the subject as properties or method calls do.
Either use `its` or the overload of `feature` which expects a feature description in form of a `String` as first argument.
Following an example using `feature`.

<ex-arbitrary-features>

```kotlin
data class FamilyMember(val name: String)

data class Family(val members: List<FamilyMember>)

val myFamily = Family(listOf(FamilyMember("Robert")))
expect(myFamily)
    .feature("the number of members", { members.size }) { toEqual(1) } // subject still Family afterwards
    .feature("the first member's name") { members.first().name }       // subject narrowed to String
    .toEqual("Peter")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L102)</sub> ↓ <sub>[Output](#ex-arbitrary-features)</sub>
<a name="ex-arbitrary-features"></a>
```text
I expected subject: Family(members=[FamilyMember(name=Robert)])        (readme.examples.FeatureExtractorSpec$1$Family <1234789>)
◆ ▶ the first member's name: "Robert"        <1234789>
    ◾ to equal: "Peter"        <1234789>
```
</ex-arbitrary-features>

Also, this version of `feature` provides two different kind of overloads:
- the first expects a feature description, a feature-provider-lambda and an `assertionCreator`-lambda, in which you can define sub-expectations for the feature.
  An `assertionCreator`-lambda has always the semantic of an [expectation-group](#define-single-expectations-or-an-expectation-group) or in other words, not-fail fast.
  It has also the benefit, that Atrium can provide those sub-expectations in error reporting,
  Moreover, the subject stays the same so that subsequent calls are still about the same subject.
- the second overload expects all the parameters except the `assertionCreator`-lambda and changes the subject to the feature, 
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
  
As you can see, Atrium provides a generic way to postulate expectations about features. 
Yet, if you extract the same feature over and over again or it gets more complicated, 
then it might be worth to [write an own expectation function](#write-own-expectation-functions) where we recommend to 
use `feature` over `its`.

### Within Expectation Functions / Feature Extractors

In case you write an own expectation function, then we discourage two things: 
- using `its` because the reporting reads less nice and it is also less efficient than `feature`
- using `feature` with a `MetaFeature`-provider-lambda (as shown in [Property and Methods](#property-and-methods))

Instead, we encourage you to pass a [class references](https://kotlinlang.org/docs/reference/reflection.html#class-references)
to `feature`.
This has the benefit, that we can always show the feature name, also in case a previous feature extraction or subject
transformation failed.
Following an example: 

<ex-within-expectation-functions>

```kotlin
fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
    feature({ f(it::first) }) { toEqual(expected) }

fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
    feature(Pair<F, *>::first) { toEqual(expected) }

expect(listOf(1 to "a", 2 to "b")).get(10) {
    firstToBeDoneWrong(1)
    firstToBe(1)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/FeatureExtractorSpec.kt#L118)</sub> ↓ <sub>[Output](#ex-within-expectation-functions)</sub>
<a name="ex-within-expectation-functions"></a>
```text
I expected subject: [(1, a), (2, b)]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ get(10): ❗❗ index out of bounds
      » ▶ CANNOT show description as it is based on subject which is not defined: 
          ◾ to equal: 1        (kotlin.Int <1234789>)
      » ▶ first: 
          ◾ to equal: 1        (kotlin.Int <1234789>)
```
</ex-within-expectation-functions>

Also, this version of `feature` provides two kind of overloads, one without and one with `assertionCreator`-lambda.
(see for instance [Arbitrary Features](#arbitrary-features) for more information).

### Ambiguity Problems
Unfortunately there are several Kotlin bugs when it comes to overloading, especially in conjunction with `KFunction`
(see [Kotlin Bugs](https://github.com/robstoll/atrium/wiki/Kotlin-Bugs-and-missing-features) and upvote in case you run into one).
It might happen that you run into such issues using `feature` in conjunction with a `MetaFeature`-provider-lambda (as shown in [Property and Methods](#property-and-methods)).
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
    feature { f1<Boolean, Int>(it::overloaded, true) }.toEqual(1)
}
```
</code-ambiguity-problems>

Notice, that you might run into the situation that Intellij is happy but the compiler is not.
For instance, Intellij will suggest that you can remove the type parameters in the above example. 
Yet, if you do so, then the compiler will fail, mentioning ambiguous overloads. 
Most of the time this problem stems from the reason that Intellij is using a newer Kotlin version to analyse
than the one you compile your project with.

Next to using the alternative functions, you could also use `its` or the overload of `feauture` which expects
a `String` as description (as shown in [arbitrary features](#arbitrary-features).

### Property does not exist

In case you deal with Java code and are using `feature`, then you might run into the problem that a property does not exist. 
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

## Type Expectations

<ex-type-expectations-1>

```kotlin
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
expect(x).toBeAnInstanceOf<SubType2> {
    feature { f(it::word) }.toEqual("goodbye")
    feature { f(it::flag) }.toEqual(false)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L92)</sub> ↓ <sub>[Output](#ex-type-expectations-1)</sub>
<a name="ex-type-expectations-1"></a>
```text
I expected subject: SubType2(word=hello, flag=true)        (readme.examples.SubType2 <1234789>)
◆ ▶ word: "hello"        <1234789>
    ◾ to equal: "goodbye"        <1234789>
◆ ▶ flag: true
    ◾ to equal: false
```
</ex-type-expectations-1>

You can narrow the type of the subject with the `toBeAnInstanceOf` function. 
On one hand it checks that the subject of the current expectation (`x` in the above example) is actually the expected type 
and on the other hand it turns the subject into this type. 
This way you can make specific expectations which are only possible for the corresponding type
-- for instance, considering the above example, `number` is not available on `SuperType` but only on `SubType1`.

<ex-type-expectations-2>

```kotlin
expect(x).toBeAnInstanceOf<SubType1>()
    .feature { f(it::number) }
    .toEqual(2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L99)</sub> ↓ <sub>[Output](#ex-type-expectations-2)</sub>
<a name="ex-type-expectations-2"></a>
```text
I expected subject: SubType2(word=hello, flag=true)        (readme.examples.SubType2 <1234789>)
◆ to be an instance of type: SubType1 (readme.examples.SubType1)
```
</ex-type-expectations-2>

There are two `toBeAnInstanceOf` overloads: 
- the first (shown in the first example) expects an `assertionCreator`-lambda in which you can define sub-expectations.
    An `assertionCreator`-lambda has always the semantic of an [expectation-group](#define-single-expectations-or-an-expectation-group)
    -- as a recapitulation, expectations in an expectation-group are all evaluated and failures are reported at the end of the block.
    It has also the benefit, that Atrium can provide those sub-expectations in error reporting,
    showing some additional context in case of a failure.
- the second overload (shown in the second example) is parameterless and turns only the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.

## Nullable Types
Let us look at the case where the subject of the expectation has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html).

<ex-nullable-1>

```kotlin
val slogan1: String? = "postulating expectations made easy"
expect(slogan1).toEqual(null)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L106)</sub> ↓ <sub>[Output](#ex-nullable-1)</sub>
<a name="ex-nullable-1"></a>
```text
I expected subject: "postulating expectations made easy"        <1234789>
◆ to equal: null
```
</ex-nullable-1>

<ex-nullable-2>

```kotlin
val slogan2: String? = null
expect(slogan2).toEqual("postulating expectations made easy")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L110)</sub> ↓ <sub>[Output](#ex-nullable-2)</sub>
<a name="ex-nullable-2"></a>
```text
I expected subject: null
◆ to equal: "postulating expectations made easy"        <1234789>
```
</ex-nullable-2>

On one hand, you can use `toEqual` and pass the same type -- 
`String?` in the above example, so in other words either `null` as in the first example or a `String` as in the second example.
On the other hand, you can use `notToEqualNull` to turn the subject into its non-null version.
This is a shortcut for `toBeAnInstanceOf<Xy>` where `Xy` is the non-nullable type (see [Type Expectations](#type-expectations)).
Following an example:

<ex-nullable-3>

```kotlin
expect(slogan2)        // subject has type String?
    .notToEqualNull()  // subject is narrowed to String
    .toStartWith("atrium")
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L115)</sub> ↓ <sub>[Output](#ex-nullable-3)</sub>
<a name="ex-nullable-3"></a>
```text
I expected subject: null
◆ to be an instance of type: String (kotlin.String) -- Class: java.lang.String
```
</ex-nullable-3>

Since `notToEqualNull` delegates to `toBeAnInstanceOf`, it also provides two overloads: 
one without (example above) and one with `assertionCreator`-lambda (example below); see 
[Type Expectations](#type-expectations) for more information on the difference of the overloads.

<ex-nullable-4>

```kotlin
expect(slogan2).notToEqualNull { toStartWith("atrium") }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L120)</sub> ↓ <sub>[Output](#ex-nullable-4)</sub>
<a name="ex-nullable-4"></a>
```text
I expected subject: null
◆ to be an instance of type: String (kotlin.String) -- Class: java.lang.String
  » to start with: "atrium"        <1234789>
```
</ex-nullable-4>

Atrium provides one additional function which is intended for [data driven testing](#data-driven-testing) 
involving nullable types and is explained in the corresponding section.

👓 _&lt;- this icon signifies additional information, worth reading in our opinion but if you are only after code examples,
then you can skip now to the next section (otherwise click on the arrow to expand the section)._<br/>

<details>
<summary>👓 dealing a lot with nullable types from Java...</summary>

... in this case we recommend having a look at the [Java Interoperability](#java-interoperability) section.

</details>

## Collection Expectations

Atrium provides expectation builders which allow to state sophisticated `toContain` expectations about `Iterable<T>`.
Such a building process allows you to define very specific expectations, where the process is guided by a fluent builder pattern.
You can either use such an 
[Expectation Builder](#sophisticated-expectation-builders)
to create a specific expectation or use one of the 
[Shortcut Functions](#shortcut-functions) in case you have kind of a common case.
The following sub sections show both use cases by examples.

### Shortcut Functions

<ex-collection-short-1>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain(2, 3)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L124)</sub> ↓ <sub>[Output](#ex-collection-short-1)</sub>
<a name="ex-collection-short-1"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ to contain, in any order: 
  ⚬ an element which equals: 3        (kotlin.Int <1234789>)
      » but no such element was found
```
</ex-collection-short-1>
 
The expectation function `toContain(2, 3)` is a shortcut for using a 
[Sophisticated Expectation Builder](#sophisticated-expectation-builders) -- it actually calls `toContain.inAnyOrder.atLeast(1).values(2, 3)`. 
This is reflected in the output.

<details>
<summary>👓 and what about expected value 2?</summary>

Exactly, what about the expected value `2`, why do we not see anything about it in the output?
The output does not show anything about the expected value `2` because the default reporter reports only failing expectations.

Back to the shortcut functions.
<hr/>
</details>
 
Next to expecting that certain values are contained in or rather returned by an `Iterable`, 
Atrium allows us to use an `assertionCreator`-lambda to identify an element
(an `assertionCreator`-lambda can also be thought of as a matcher / predicate in this context).
An element is considered as identified, if it holds all specified expectations the `assertionCreator` creates.
Following an example:

<ex-collection-short-2>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain(
    { toBeLessThan(0) },
    { toBeGreaterThan(2).toBeLessThan(4) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L128)</sub> ↓ <sub>[Output](#ex-collection-short-2)</sub>
<a name="ex-collection-short-2"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ to contain, in any order: 
  ⚬ an element which needs: 
      » to be less than: 0        (kotlin.Int <1234789>)
      » but no such element was found
  ⚬ an element which needs: 
      » to be greater than: 2        (kotlin.Int <1234789>)
      » to be less than: 4        (kotlin.Int <1234789>)
      » but no such element was found
```
</ex-collection-short-2>

In the above example, neither of the two lambdas matched any elements and thus both are reported as failing (sub) expectations.

Another `toContain` shortcut function which Atrium provides for `Iterable<T>` is kind of 
the opposite of `inAnyOrder.atLeast(1)` and is named `toContainExactly`.
Again, Atrium provides two overloads for it, one for values,
e.g. `toContainExactly(1, 2)` which calls `toContain.inOrder.only.values(1, 2)` 
and a second one which expects one or more `assertionCreator`-lambda, 
e.g. `toContainExactly( { toBeGreaterThan(5) }, { toBeLessThan(10) })` 
which calls `toContain.inOrder.only.elements({ toBeGreaterThan(5) }, { toBeLessThan(10) })`.
We will spare the examples here and show them in the following sections.
Notice that you can pass `null` to `toContainExactly` instead of an `assertionCreator`-lambda to match `null`.
This makes of course only sense if your `Iterable` contains nullable elements.

Atrium provides also a `notToContain` shortcut function. 
Furthermore, it provides aliases for `toContain` and `notToContain` named `toHaveElementsAndAny` and 
`toHaveElementsAndNone`, which might be a better choice if you think in terms of: expect a predicate holds. 
These two are completed with an `toHaveElementsAndAll` expectation function.

Following each in action:

<ex-collection-any>

```kotlin
expect(listOf(1, 2, 3, 4)).toHaveElementsAndAny {
    toBeLessThan(0)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L135)</sub> ↓ <sub>[Output](#ex-collection-any)</sub>
<a name="ex-collection-any"></a>
```text
I expected subject: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ to contain, in any order: 
  ⚬ an element which needs: 
      » to be less than: 0        (kotlin.Int <1234789>)
      » but no such element was found
```
</ex-collection-any>
<hr/>
<ex-collection-none>

```kotlin
expect(listOf(1, 2, 3, 4)).toHaveElementsAndNone {
    toBeGreaterThan(2)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L140)</sub> ↓ <sub>[Output](#ex-collection-none)</sub>
<a name="ex-collection-none"></a>
```text
I expected subject: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ not to contain: 
  ⚬ an element which needs: 
      » to be greater than: 2        (kotlin.Int <1234789>)
      ❗❗ following elements were mismatched: 
         ⚬ index 2: 3        (kotlin.Int <1234789>)
         ⚬ index 3: 4        (kotlin.Int <1234789>)
```
</ex-collection-none>
<hr/>
<ex-collection-all>

```kotlin
expect(listOf(1, 2, 3, 4)).toHaveElementsAndAll {
    toBeGreaterThan(2)
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L145)</sub> ↓ <sub>[Output](#ex-collection-all)</sub>
<a name="ex-collection-all"></a>
```text
I expected subject: [1, 2, 3, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ elements need all: 
    » to be greater than: 2        (kotlin.Int <1234789>)
    ❗❗ following elements were mismatched: 
       ⚬ index 0: 1        (kotlin.Int <1234789>)
       ⚬ index 1: 2        (kotlin.Int <1234789>)
```
</ex-collection-all>


### Sophisticated Expectation Builders

Sophisticated expectation builders implement a fluent builder pattern.
To use the expectation builder for sophisticated `Iterable<T>`-toContain-expectations, you can type `toContain` 
-- as you would when using the [Shortcut Functions](#shortcut-functions) `toContain` -- 
but type `.` as next step (so that you are using the property `toContain` instead of one of the shortcut functions). 
Currently, the builder provides two options, either `inAnyOrder` or `inOrder`. 
In case you are using an IDE, you do not really have to think too much -- use code completion; 
the fluent builders will guide you through your decision-making 😊

Following on the last section we will start with an `inOrder` example:

<ex-collection-builder-1>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain.inOrder.only.entries({ toBeLessThan(3) }, { toBeLessThan(2) })
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L151)</sub> ↓ <sub>[Output](#ex-collection-builder-1)</sub>
<a name="ex-collection-builder-1"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ size: 4        (kotlin.Int <1234789>)
    ◾ to equal: 2        (kotlin.Int <1234789>)
◆ to contain only, in order: 
  ✔ ▶ element 0: 1        (kotlin.Int <1234789>)
      ◾ to be less than: 3        (kotlin.Int <1234789>)
  ✘ ▶ element 1: 2        (kotlin.Int <1234789>)
      ◾ to be less than: 2        (kotlin.Int <1234789>)
    ❗❗ additional elements detected: 
       ⚬ element 2: 2        (kotlin.Int <1234789>)
       ⚬ element 3: 4        (kotlin.Int <1234789>)
```
</ex-collection-builder-1>

Since we have chosen the `only` option, Atrium shows us a summary<sup><a href="#in-order-only-summary">1</a></sup> where we see three things:
- Whether a specified `assertionCreator`-lambda matched (signified by `✔` or `✘`) 
  the corresponding element or not (e.g. `✘ ▶ entry 1:` was `2` and we expected, it `to be less than 2`)
- Whether the expected size was correct or not (`✘ ▶ size:` was `4`, we expected it, `to equal: 2`
- and last but not least, mismatches or additional elements as further clue (`❗❗ additional elements detected`).

😍 We are pretty sure you are going to love this feature as well. 
Please star Atrium if you like using it.

<a name="in-order-only-summary"></a>
<sup>1</sup> Atrium shows a summary up to 10 elements, if the Iterable contains more elements, 
then only failing expectations are shown.

<details>
<summary>💬 Show only failing expectations/elements earlier than 10 expected elements?</summary>

You can use the `report` option to specify when Atrium shall start to show only failing expectations.
Following an example changing the limit to 3 elements by using `showOnlyFailingIfMoreExpectedElementsThan` :

<ex-collection-reportOptions-1>

```kotlin
expect(listOf(1, 2, 2, 4)).toContainExactly(
    { toBeLessThan(3) },
    { toBeLessThan(2) },
    { toBeGreaterThan(1) },
    report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L154)</sub> ↓ <sub>[Output](#ex-collection-reportOptions-1)</sub>
<a name="ex-collection-reportOptions-1"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ size: 4        (kotlin.Int <1234789>)
    ◾ to equal: 3        (kotlin.Int <1234789>)
◆ to contain only, in order: 
  ⚬ ▶ element 1: 2        (kotlin.Int <1234789>)
      ◾ to be less than: 2        (kotlin.Int <1234789>)
    ❗❗ additional elements detected: 
       ⚬ element 3: 4        (kotlin.Int <1234789>)
```
</ex-collection-reportOptions-1>

Likewise, you can use `showOnlyFailing()` to set the limit to 0 and `showAlwaysSummary()` to set the limit to Int.MAX_VALUE

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
expect(listOf(1, 2, 2, 4)).toContain.inOrder.only.values(1, 2, 2, 3, 4)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L162)</sub> ↓ <sub>[Output](#ex-collection-builder-2)</sub>
<a name="ex-collection-builder-2"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ size: 4        (kotlin.Int <1234789>)
    ◾ to equal: 5        (kotlin.Int <1234789>)
◆ to contain only, in order: 
  ✔ ▶ element 0: 1        (kotlin.Int <1234789>)
      ◾ to equal: 1        (kotlin.Int <1234789>)
  ✔ ▶ element 1: 2        (kotlin.Int <1234789>)
      ◾ to equal: 2        (kotlin.Int <1234789>)
  ✔ ▶ element 2: 2        (kotlin.Int <1234789>)
      ◾ to equal: 2        (kotlin.Int <1234789>)
  ✘ ▶ element 3: 4        (kotlin.Int <1234789>)
      ◾ to equal: 3        (kotlin.Int <1234789>)
  ✘ ▶ element 4: ❗❗ hasNext() returned false
        » to equal: 4        (kotlin.Int <1234789>)
```
</ex-collection-builder-2>
<hr/>
<ex-collection-builder-3>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.atLeast(1).butAtMost(2).entries({ toBeLessThan(3) })
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L165)</sub> ↓ <sub>[Output](#ex-collection-builder-3)</sub>
<a name="ex-collection-builder-3"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ to contain, in any order: 
  ⚬ an element which needs: 
      » to be less than: 3        (kotlin.Int <1234789>)
    ⚬ ▶ number of such elements: 3
        ◾ is at most: 2
```
</ex-collection-builder-3>
<hr/>
<ex-collection-builder-4>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.only.values(1, 2, 3, 4)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L168)</sub> ↓ <sub>[Output](#ex-collection-builder-4)</sub>
<a name="ex-collection-builder-4"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ to contain only, in any order: 
  ✔ an element which equals: 1        (kotlin.Int <1234789>)
  ✔ an element which equals: 2        (kotlin.Int <1234789>)
  ✘ an element which equals: 3        (kotlin.Int <1234789>)
  ✔ an element which equals: 4        (kotlin.Int <1234789>)
  ❗❗ following elements were mismatched: 
     ⚬ 2        (kotlin.Int <1234789>)
```
</ex-collection-builder-4>
<hr/>
<ex-collection-builder-5>

```kotlin
expect(listOf(1, 2, 2, 4)).toContain.inAnyOrder.only.values(4, 3, 2, 2, 1)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L171)</sub> ↓ <sub>[Output](#ex-collection-builder-5)</sub>
<a name="ex-collection-builder-5"></a>
```text
I expected subject: [1, 2, 2, 4]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ size: 4        (kotlin.Int <1234789>)
    ◾ to equal: 5        (kotlin.Int <1234789>)
◆ to contain only, in any order: 
  ✔ an element which equals: 4        (kotlin.Int <1234789>)
  ✘ an element which equals: 3        (kotlin.Int <1234789>)
  ✔ an element which equals: 2        (kotlin.Int <1234789>)
  ✔ an element which equals: 2        (kotlin.Int <1234789>)
  ✔ an element which equals: 1        (kotlin.Int <1234789>)
```
</ex-collection-builder-5>


## Map Expectations

Map expectations are kind of very similar to [Collection Expectations](#collection-expectations), also regarding reporting.
That is the reason why we are not going into too much detail here because we assume you are already familiar with it.

We provide again [Shortcut Functions](#shortcut-functions-1) for the most common scenarios
and more [Sophisticated Expectation Builder](#sophisticated-expectation-builders-1) for the other cases.

### Shortcut Functions
<ex-map-1>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContain("c" to 2, "a" to 1, "b" to 1)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L175)</sub> ↓ <sub>[Output](#ex-map-1)</sub>
<a name="ex-map-1"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ to contain, in any order: 
  ⚬ ▶ entry "c": ❗❗ key does not exist
        » to equal: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ to equal: 1        (kotlin.Int <1234789>)
```
</ex-map-1>

Next to postulate expectations based on key-value `Pair`s one can also define sub expectations for the value of 
an entry with the help of the parameter object `KeyValue`:

<ex-map-2>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContain(
    KeyValue("c") { toEqual(2) },
    KeyValue("a") { toBeGreaterThan(2) },
    KeyValue("b") { toBeLessThan(2) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L178)</sub> ↓ <sub>[Output](#ex-map-2)</sub>
<a name="ex-map-2"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ to contain, in any order: 
  ⚬ ▶ entry "c": ❗❗ key does not exist
        » to equal: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "a": 1        (kotlin.Int <1234789>)
      ◾ to be greater than: 2        (kotlin.Int <1234789>)
  ⚬ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ to be less than: 2        (kotlin.Int <1234789>)
```
</ex-map-2>

In case you expect that a map only contains certain entries, then you can use the shortcut `toContainOnly`.
Again both overloads are provided, one for key-value `Pair`s:

<ex-map-only-1>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContainOnly("b" to 2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L186)</sub> ↓ <sub>[Output](#ex-map-only-1)</sub>
<a name="ex-map-only-1"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ ▶ size: 2        (kotlin.Int <1234789>)
    ◾ to equal: 1        (kotlin.Int <1234789>)
◆ to contain only, in any order: 
  ✔ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ to equal: 2        (kotlin.Int <1234789>)
    ❗❗ additional entries detected: 
       ⚬ entry "a": 1        (kotlin.Int <1234789>)
```
</ex-map-only-1>

And the other overload which expects a `KeyValue` and allows defining sub expectations for the value:

<ex-map-only-2>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContainOnly(
    KeyValue("c") { toEqual(2) },
    KeyValue("a") { toBeLessThan(2) },
    KeyValue("b") { toBeLessThan(2) }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L189)</sub> ↓ <sub>[Output](#ex-map-only-2)</sub>
<a name="ex-map-only-2"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ ▶ size: 2        (kotlin.Int <1234789>)
    ◾ to equal: 3        (kotlin.Int <1234789>)
◆ to contain only, in any order: 
  ✘ ▶ entry "c": ❗❗ key does not exist
        » to equal: 2        (kotlin.Int <1234789>)
  ✔ ▶ entry "a": 1        (kotlin.Int <1234789>)
      ◾ to be less than: 2        (kotlin.Int <1234789>)
  ✘ ▶ entry "b": 2        (kotlin.Int <1234789>)
      ◾ to be less than: 2        (kotlin.Int <1234789>)
```
</ex-map-only-2>

### Sophisticated Expectation Builders

Most functionality for `Map.toContain` are provided as shortcut functions but there is a handy one 
in case you deal with ordered Maps: `.toContain.inOrder.only`    
There are multiple methods finalising the building process : `entry`/`entries`/`entriesOf` where `entry` and `entries`
again provide two overloads, one expecting key-value `Pair`s:

<ex-map-builder-1>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContain.inOrder.only.entries("b" to 2, "a" to 1)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L197)</sub> ↓ <sub>[Output](#ex-map-builder-1)</sub>
<a name="ex-map-builder-1"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ to contain only, in order: 
  ✘ ▶ element 0: a=1        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "a"        <1234789>
          ◾ to equal: "b"        <1234789>
      ◾ ▶ value: 1        (kotlin.Int <1234789>)
          ◾ to equal: 2        (kotlin.Int <1234789>)
  ✘ ▶ element 1: b=2        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "b"        <1234789>
          ◾ to equal: "a"        <1234789>
      ◾ ▶ value: 2        (kotlin.Int <1234789>)
          ◾ to equal: 1        (kotlin.Int <1234789>)
```
</ex-map-builder-1>

And the other expecting `KeyValue`s which allow specifying sub expectations for the value

<ex-map-builder-2>

```kotlin
expect(mapOf("a" to 1, "b" to 2)).toContain.inOrder.only.entries(
    KeyValue("a") { toBeLessThan(2) },
    KeyValue("b") { toBeLessThan(2) })
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L200)</sub> ↓ <sub>[Output](#ex-map-builder-2)</sub>
<a name="ex-map-builder-2"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ to contain only, in order: 
  ✔ ▶ element 0: a=1        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "a"        <1234789>
          ◾ to equal: "a"        <1234789>
      ◾ ▶ value: 1        (kotlin.Int <1234789>)
          ◾ to be less than: 2        (kotlin.Int <1234789>)
  ✘ ▶ element 1: b=2        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "b"        <1234789>
          ◾ to equal: "b"        <1234789>
      ◾ ▶ value: 2        (kotlin.Int <1234789>)
          ◾ to be less than: 2        (kotlin.Int <1234789>)
```
</ex-map-builder-2>

### Others

In case you want to postulate an expectation about a value of one particular key, then you can use `getExisting`.
For instance:

<ex-map-3>

```kotlin
data class Person(val firstName: String, val lastName: String, val age: Int)
val bernstein = Person("Leonard", "Bernstein", 50)
expect(mapOf("bernstein" to bernstein))
    .getExisting("bernstein") {
        feature { f(it::firstName) }.toEqual("Leonard")
        feature { f(it::age) }.toEqual(60)
    }
    .getExisting("einstein") {
        feature { f(it::firstName) }.toEqual("Albert")
    }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L210)</sub> ↓ <sub>[Output](#ex-map-3)</sub>
<a name="ex-map-3"></a>
```text
I expected subject: {bernstein=Person(firstName=Leonard, lastName=Bernstein, age=50)}        (java.util.Collections.SingletonMap <1234789>)
◆ ▶ get("bernstein"): Person(firstName=Leonard, lastName=Bernstein, age=50)        (readme.examples.MostExamplesSpec$1$Person <1234789>)
    ◾ ▶ age: 50        (kotlin.Int <1234789>)
        ◾ to equal: 60        (kotlin.Int <1234789>)
```
</ex-map-3>

In case you hvae only expectations about the keys or values of the `Map` then you can use `keys` or `values`:

<ex-map-4>

```kotlin
expect(mapOf("a" to 1, "b" to 2)) {
    keys { toHaveElementsAndAll { toStartWith("a") } }
    values { toHaveElementsAndNone { toBeGreaterThan(1) } }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L222)</sub> ↓ <sub>[Output](#ex-map-4)</sub>
<a name="ex-map-4"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ ▶ keys: [a, b]        (java.util.LinkedHashMap.LinkedKeySet <1234789>)
    ◾ elements need all: 
        » to start with: "a"        <1234789>
        ❗❗ following elements were mismatched: 
           ⚬ index 1: "b"        <1234789>
◆ ▶ values: [1, 2]        (java.util.LinkedHashMap.LinkedValues <1234789>)
    ◾ not to contain: 
      ⚬ an element which needs: 
          » to be greater than: 1        (kotlin.Int <1234789>)
          ❗❗ following elements were mismatched: 
             ⚬ index 1: 2        (kotlin.Int <1234789>)
```
</ex-map-4>

Last but not least, you can use the non-reporting `asEntries()` function which
turns `Expect<Map<K, V>>` into an `Expect<Set<Map.Entry<K, V>>` and thus allows that you can use all the expectation 
functions and sophisticated builders shown in [Collection Expectations](#collection-expectations).

There should seldom be a need for it but in case you want to make also sub expectations for the key, 
then it will come in handy:

<ex-map-5>

```kotlin
expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().toContain.inOrder.only.entries(
    { toEqualKeyValue("a", 1) },
    {
        key.toStartWith("a")
        value.toBeGreaterThan(2)
    }
)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L228)</sub> ↓ <sub>[Output](#ex-map-5)</sub>
<a name="ex-map-5"></a>
```text
I expected subject: {a=1, b=2}        (java.util.LinkedHashMap <1234789>)
◆ to contain only, in order: 
  ✔ ▶ element 0: a=1        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "a"        <1234789>
          ◾ to equal: "a"        <1234789>
      ◾ ▶ value: 1        (kotlin.Int <1234789>)
          ◾ to equal: 1        (kotlin.Int <1234789>)
  ✘ ▶ element 1: b=2        (java.util.LinkedHashMap.Entry <1234789>)
      ◾ ▶ key: "b"        <1234789>
          ◾ to start with: "a"        <1234789>
      ◾ ▶ value: 2        (kotlin.Int <1234789>)
          ◾ to be greater than: 2        (kotlin.Int <1234789>)
```
</ex-map-5>

`toEqualKeyValue` as well as `key` and `value` are expectation functions defined for `Map.Entry<K, V>`.

There are more expectation functions, a full list can be found in 
[KDoc of atrium-api-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html).

## Path Expectations

Atrium’s expectation functions for paths give detailed failure hints explaining what happened on the file system.
For example, `toExist` will explain which entry was the first one missing:

<ex-path-exists>

```kotlin
expect(Paths.get("/usr/bin/noprogram")).toExist()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/PathSpec.kt#L37)</sub> ↓ <sub>[Output](#ex-path-exists)</sub>
<a name="ex-path-exists"></a>
```text
I expected subject: /usr/bin/noprogram        (sun.nio.fs.UnixPath <1234789>)
◆ to: exist
    » the closest existing parent directory is /usr/bin
```
</ex-path-exists>

Atrium will give details about why something cannot be accessed, for example when checking whether a file is writable:

<ex-path-writable>

```kotlin
expect(Paths.get("/root/.ssh/config")).toBeWritable()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/PathSpec.kt#L41)</sub> ↓ <sub>[Output](#ex-path-writable)</sub>
<a name="ex-path-writable"></a>
```text
I expected subject: /root/.ssh/config        (sun.nio.fs.UnixPath <1234789>)
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

expect(filePointer.resolve("subfolder/file")).toBeARegularFile()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/PathSpec.kt#L46)</sub> ↓ <sub>[Output](#ex-path-symlink-and-parent-not-folder)</sub>
<a name="ex-path-symlink-and-parent-not-folder"></a>
```text
I expected subject: /tmp/atrium-path/directory/subfolder/file        (sun.nio.fs.UnixPath <1234789>)
◆ to be: a file
    » followed the symbolic link /tmp/atrium-path/directory to /tmp/atrium-path/file
    » failure at parent path: /tmp/atrium-path/file        (sun.nio.fs.UnixPath <1234789>)
      » was a file instead of a directory
```
</ex-path-symlink-and-parent-not-folder>

## Attaching a Reason

In case you want to add further information to an expectation, e.g. state the reason why you expect it to hold, you can
use `because`:

<ex-because-1>

```kotlin
expect("filename?")
    .because("? is not allowed in file names on Windows") {
        notToContain("?")
    }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L238)</sub> ↓ <sub>[Output](#ex-because-1)</sub>
<a name="ex-because-1"></a>
```text
I expected subject: "filename?"        <1234789>
◆ not to contain: 
  ⚬ value: "?"        <1234789>
    ⚬ ▶ number of matches: 1
        ◾ to equal: 0        (kotlin.Int <1234789>)
ℹ because: ? is not allowed in file names on Windows
```
</ex-because-1>

<details>
<summary>💬 Use <code>because</code> only to give reasons for non-obvious expectations</summary>

`because` can be a useful tool for explaining why there is a certain expectation. 
Sometimes it is not directly obvious why one should expect something. 
In such cases, using `because` can make your code, and your error messages, easier to
understand for other developers (including yourself in three months).

Having said that, you should not use `because` if you are missing a specific predefined expectation function. 
You can use a [feature extractor](#feature-extractors), [write your own expectation function](#write-own-expectation-functions)
or [propose an addition to Atrium](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=Missing%20Expectation%20Function)
in such cases.

Just like code comments, `because` can be valuable, but should not be overused.

</details>

## Data Driven Testing

Atrium is not intended for data driven testing in the narrowed sense in terms that it cannot produce multiple tests.
This is the responsibility of your test runner.
However, Atrium let you define multiple expectations within one test and reports them all if you want.
In this sense it can be used for data driven testing.
This is especially helpful in case your test runner does not support data driven testing (or other mechanisms like hierarchical or dynamic tests).
As an example, Atrium can help you to write data driven tests in a common module of a multiplatform-project.

The trick is to wrap your expectations into an [expectation-group](#define-single-expectations-or-an-expectation-group),
use [Feature Extractors](#feature-extractors) and state expectations about those feautres. Following an example:

<ex-data-driven-1>

```kotlin
fun myFun(i: Int) = (i + 97).toChar()

expect("calling myFun with...") {
    mapOf(
        1 to 'a',
        2 to 'c',
        3 to 'e'
    ).forEach { (arg, result) ->
        feature { f(::myFun, arg) }.toEqual(result)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/DataDrivenSpec.kt#L35)</sub> ↓ <sub>[Output](#ex-data-driven-1)</sub>
<a name="ex-data-driven-1"></a>
```text
I expected subject: "calling myFun with..."        <1234789>
◆ ▶ myFun(1): 'b'
    ◾ to equal: 'a'
◆ ▶ myFun(3): 'd'
    ◾ to equal: 'e'
```
</ex-data-driven-1>

Per default, only failing expectations are shown.
This is also the reason why the call of `myFun(2)` is not listed (as the result is `c` as expected).

Please [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
if you want to see a summary, meaning also successful expectations -- we happily add more functionality if it is of use for someone.

Following another example which involves an `assertionCreator`-lambda and not only a simple `toEqual` check. 
We are going to reuse the `myFun` from above:

<ex-data-driven-2>

```kotlin
import ch.tutteli.atrium.logic.utils.expectLambda

expect("calling myFun with ...") {
    mapOf(
        1 to expectLambda<Char> { toBeLessThan('f') },
        2 to expectLambda { toEqual('c') },
        3 to expectLambda { toBeGreaterThan('e') }
    ).forEach { (arg, assertionCreator) ->
        feature({ f(::myFun, arg) }, assertionCreator)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/DataDrivenSpec.kt#L49)</sub> ↓ <sub>[Output](#ex-data-driven-2)</sub>
<a name="ex-data-driven-2"></a>
```text
I expected subject: "calling myFun with ..."        <1234789>
◆ ▶ myFun(3): 'd'
    ◾ to be greater than: 'e'
```
</ex-data-driven-2>

The example should be self-explanatory.
One detail to note though is the usage of `expectLambda`. 
It is a helper function which circumvents certain [Kotlin type inference bugs](https://github.com/robstoll/atrium/wiki/Kotlin-Bugs-and-missing-features) (upvote them please).
Writing the same as `mapOf<Int, Expect<Char>.() -> Unit>( 1 to { ... } )` would not work as the type for a lambda 
involved in a `Pair` is not (yet) inferred correctly by Kotlin.

There is one last function worth mentioning here which comes in handy in data-driven testing in case the subject has a 
[nullable type]((https://kotlinlang.org/docs/reference/null-safety.html).)

If you wish to make sub expectations on the non-nullable type of the subject, then you can use
`toEqualNullIfNullGivenElse` which accepts an `assertionCreator`-lambda or `null`.
It is short for `if (assertionCreatorOrNull == null) toEqual(null) else notToEqual(assertionCreatorOrNull)`. 
Following another fictional example which illustrates `toEqualNullIfNullGivenElse` (we are reusing `myFun` from above):

<ex-data-driven-3>

```kotlin
fun myNullableFun(i: Int) = if (i > 0) i.toString() else null

expect("calling myNullableFun with ...") {
    mapOf(
        Int.MIN_VALUE to expectLambda<String> { toContain("min") },
        -1 to null,
        0 to null,
        1 to expectLambda { toEqual("1") },
        2 to expectLambda { toEndWith("2") },
        Int.MAX_VALUE to expectLambda { toEqual("max") }
    ).forEach { (arg, assertionCreatorOrNull) ->
        feature { f(::myNullableFun, arg) }.toEqualNullIfNullGivenElse(assertionCreatorOrNull)
    }
}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/DataDrivenSpec.kt#L67)</sub> ↓ <sub>[Output](#ex-data-driven-3)</sub>
<a name="ex-data-driven-3"></a>
```text
I expected subject: "calling myNullableFun with ..."        <1234789>
◆ ▶ myNullableFun(-2147483648): null
      » to contain: 
        ⚬ value: "min"        <1234789>
            » but no match was found
◆ ▶ myNullableFun(2147483647): "2147483647"        <1234789>
    ◾ to equal: "max"        <1234789>
```
</ex-data-driven-3>

## Further Examples

Atrium supports further expectation builders (e.g, for `CharSequence`) 
as well as expectation functions which have not been shown in the examples.
Have a look at [apis/differences.md](https://github.com/robstoll/atrium/tree/main/apis/differences.md) for a few more examples.
This site contains also a list of all APIs with links to their expectation function catalogs.

You can also have a look at the 
[specifications](https://github.com/robstoll/atrium/tree/main/misc/atrium-specs/src/commonMain/kotlin/ch/tutteli/atrium/specs) 
for more examples.

## Sample Projects

Have a look into the [samples](https://github.com/robstoll/atrium/tree/main/samples)
folder, it currently contains sample projects for 
- [js](https://github.com/robstoll/atrium/tree/main/samples/js/)
- [jvm gradle](https://github.com/robstoll/atrium/tree/main/samples/jvm/)
- [jvm maven](https://github.com/robstoll/atrium/tree/main/samples/maven/)
- [multiplatform project](https://github.com/robstoll/atrium/tree/main/samples/multiplatform/)

Are you using a different runner? A PR would be appreciated 😊.

# Third-party Extensions

Following extensions are maintained outside of this repository. 

- [atrium-gradle-testkit](https://github.com/jGleitz/atrium-gradle-testkit): Atrium expectations to test Gradle plugins with TestKit.

# How is Atrium different from other Expectation/Assertion Libraries

The following subsections shall give you a quick overview how Atrium differ from other assertion libraries. 

- [Ready to Help](#ready-to-help)
  - [Fluent API with Code Documentation](#1-fluent-api-with-code-documentation)
  - [Additional Information in Failure Reporting](#2-additional-information-in-failure-reporting)
  - [Prevents you from Pitfalls](#3-prevents-you-from-pitfalls)
- [Flexibility](#flexibility)
- [Migration of Deprecated Functionality](#migration-of-deprecated-functionality)

## Ready to Help
Atrium is designed to help you whenever possible.
We think this is the biggest difference to other expectation libraries and a very handy one indeed.

### 1. Fluent API with Code Documentation
Atrium provides a fluent API where the design focus was put on the interoperability (of the API) 
with the code completion functionality of your IDE. 
Or in other words, you can always use code completion to get direct help from your IDE.
This experience is improved by providing up-to-date [code documentation](#kdoc) (in form of KDoc) 
for all expectation functions, so that you get the extra help needed.

💩 &lt;- _this icon signifies a bug in Kotlin which you might encounter as well. 
We try to provide a workaround whenever possible._

<details>
<summary>💩 There is no KDoc for toEqual</summary>

There is, but IntelliJ will not show it to you due to [this bug](https://youtrack.jetbrains.com/issue/KT-24836) (please upvote it).
You should be able to see the KDoc of other functions without problems. 
But in case, you can also browse the online documentation, e.g. [KDoc of toEqual](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/to-be.html).

</details>

### 2. Additional Information in Failure Reporting
Atrium adds extra information to error messages so that you get quickly a better idea of what went wrong. 
For instance, for the following expectation (which fails):

<exs-add-info-1>

```kotlin
expect(listOf(1, 2, 3)).toContain.inOrder.only.values(1, 3)
```
</exs-add-info-1>

Atrium points out which `values` were found, makes an implicit expectation about the size and 
also states which entries were additionally contained in the list:

<exs-add-info-1-output>

```text
I expected subject: [1, 2, 3]        (java.util.Arrays.ArrayList <1234789>)
◆ ▶ size: 3        (kotlin.Int <1234789>)
    ◾ to equal: 2        (kotlin.Int <1234789>)
◆ to contain only, in order: 
  ✔ ▶ element 0: 1        (kotlin.Int <1234789>)
      ◾ to equal: 1        (kotlin.Int <1234789>)
  ✘ ▶ element 1: 2        (kotlin.Int <1234789>)
      ◾ to equal: 3        (kotlin.Int <1234789>)
    ❗❗ additional elements detected: 
       ⚬ element 2: 3        (kotlin.Int <1234789>)
```
</exs-add-info-1-output>


Let us have a look at another example.

<exs-add-info-2>

```kotlin
expect(9.99f).toEqualWithErrorTolerance(10.0f, 0.01f)
```
</exs-add-info-2>

The above expectation looks good at first sight but actually fails (at least on @robstoll's machine). 
And without some extra information in the output we would believe that there is actually a bug in the expectation library itself.
But Atrium shows where it goes wrong and even gives a possible hint:

<exs-add-info-2-output>

```text
I expected subject: 9.99        (kotlin.Float <1234789>)
◆ to equal (error ± 0.01): 10.0        (kotlin.Float <1234789>)
    » failure might be due to using kotlin.Float, see exact check on the next line
    » exact check was |9.989999771118164 - 10.0| = 0.010000228881835938 ≤ 0.009999999776482582
```
</exs-add-info-2-output>

One last example. This time about formulating an expectation that a certain `Throwable` is thrown but
the expectation fails because it was the wrong one. 
Atrium comes with a very useful hint, it shows the actual exception:

<ex-add-info-3>

```kotlin
expect {
    try {
        throw UnsupportedOperationException("not supported")
    } catch (t: Throwable) {
        throw IllegalArgumentException("no no no...", t)
    }
}.toThrow<IllegalStateException> { messageToContain("no no no") }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L251)</sub> ↓ <sub>[Output](#ex-add-info-3)</sub>
<a name="ex-add-info-3"></a>
```text
I expected subject: () -> kotlin.Nothing        (readme.examples.MostExamplesSpec$1$40$1 <1234789>)
◆ ▶ thrown exception when called: java.lang.IllegalArgumentException
    ◾ to be an instance of type: IllegalStateException (java.lang.IllegalStateException)
      » ▶ message: 
          ◾ to be an instance of type: String (kotlin.String) -- Class: java.lang.String
          ◾ to contain: 
            ⚬ value: "no no no"        <1234789>
                » but no match was found
    ℹ Properties of the unexpected IllegalArgumentException
      » message: "no no no..."        <1234789>
      » stacktrace: 
        ⚬ readme.examples.MostExamplesSpec$1$40$1.invoke(MostExamplesSpec.kt:256)
        ⚬ readme.examples.MostExamplesSpec$1$40$1.invoke(MostExamplesSpec.kt:22)
        ⚬ readme.examples.MostExamplesSpec$1$40.invoke(MostExamplesSpec.kt:280)
        ⚬ readme.examples.MostExamplesSpec$1$40.invoke(MostExamplesSpec.kt:22)
      » cause: java.lang.UnsupportedOperationException
          » message: "not supported"        <1234789>
          » stacktrace: 
            ⚬ readme.examples.MostExamplesSpec$1$40$1.invoke(MostExamplesSpec.kt:254)
```
</ex-add-info-3>


### 3. Prevents you from Pitfalls
But not enough. There are certain pitfalls when it comes to using an expectation library and Atrium tries to prevent you from those.

For instance, an overload of `toEqual` and of `notToEqual` for `BigDecimal` was introduced which are both deprecated and throw a `PleaseUseReplacementException`. 
The reason behind it?
It is very likely that a user actually wants to compare that a certain `BigDecimal` is numerically (not) equal to another `BigDecimal` 
rather than including `BigDecimal.scale` in the comparison.
Accordingly, the deprecation message of `toEqual` (`notToEqual` alike) explains the problem and suggests to either use `toEqualNumerically` or `toEqualIncludingScale`.
And if the user should decide to use `toEqualIncludingScale` and at some point an expectation fails only due to the comparison of `BigDecimal.scale`
then Atrium reminds us of the possible pitfall. For instance:

<ex-pitfall-1>

```kotlin
expect(BigDecimal.TEN).toEqualIncludingScale(BigDecimal("10.0"))
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L261)</sub> ↓ <sub>[Output](#ex-pitfall-1)</sub>
<a name="ex-pitfall-1"></a>
```text
I expected subject: 10        (java.math.BigDecimal <1234789>)
◆ is equal (including scale): 10.0        (java.math.BigDecimal <1234789>)
    💡 notice, if you used toEqualNumerically then the expectation would have been met.
```
</ex-pitfall-1>

Another example are empty `assertionCreator`-lambdas. 
Getting distracted by a working colleague and taking up the work at the wrong position might sound familiar to you. 
For instance:

<ex-pitfall-2>

```kotlin
expect(listOf(1)).get(0) {}
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/MostExamplesSpec.kt#L264)</sub> ↓ <sub>[Output](#ex-pitfall-2)</sub>
<a name="ex-pitfall-2"></a>
```text
I expected subject: [1]        (java.util.Collections.SingletonList <1234789>)
◆ ▶ get(0): 1        (kotlin.Int <1234789>)
    ◾ at least one expectation defined: false
        » You forgot to define expectations in the expectationCreator-lambda
        » Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
```
</ex-pitfall-2>

## Flexibility
Another design goal of Atrium was to give you the flexibility needed but still adhere to a concise design. 
First and most importantly, Atrium does not enforce a certain style on your code base. 
Quite the contrary, it gives you the flexibility to [choose a desired name for the expectation verb](#use-own-expectation-verb), 
it continues by providing the possibility to configure the [reporting style](#use-own-components), 
goes on that you can choose from different [API Styles](#api-styles) 
and ends that you can [replace almost all components](#use-own-components) by other implementations and hook into existing.

So for instance, if you like to use an `infix` API, then use the bundle `atrium-infix-en_GB`. 
You prefer pure fluent and do not even want to see infix style in your code, 
then use `atrium-fluent-en_GB` which provides a pure fluent style API. 

You are free to choose what fits best without introducing ambiguity etc.
You could even mix up different API-styles if needed (but not without losing conciseness -- but hey, it is your decision 😉). 

## Migration of Deprecated Functionality
Atrium follows [Semantic Versioning](https://semver.org/) and tries to be binary backward compatible within a major version (since 0.6.0).
Until 1.0.0 this is only true for the API level, we reserve the right to break things on the logic and core level until then.
Moreover, we follow the principle that a user of Atrium has enough time to migrate its code to new functionality before a next major version.
We provide this in form of `@Deprecated` annotations with a corresponding `ReplaceWith` 
as well as migration guides in the [Release Notes](https://github.com/robstoll/atrium/releases).
This way we hope that we provide a pleasant way to stay up-to-date without the need to migrate everything from one day to the other.

# Write own Expectation Functions

Are you missing an expectation function for a specific type and the generic 
[Feature Extractors](#feature-extractors) are not good enough?

The following subsections will show how you can write your own expectation functions. 
A pull request of your new expectation function is very much appreciated.

## Boolean based Expectation Functions

This is kind of the simplest way of defining expectation functions. Following an example:

<code-own-boolean-1>

```kotlin
import ch.tutteli.atrium.logic._logic

fun Expect<Int>.toBeAMultipleOf(base: Int) =
    _logic.createAndAppend("is multiple of", base) { it % base == 0 }
```
</code-own-boolean-1>

and its usage:

<ex-own-boolean-1>

```kotlin
expect(12).toBeAMultipleOf(5)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationFunctionsSpec.kt#L44)</sub> ↓ <sub>[Output](#ex-own-boolean-1)</sub>
<a name="ex-own-boolean-1"></a>
```text
I expected subject: 12        (kotlin.Int <1234789>)
◆ is multiple of: 5        (kotlin.Int <1234789>)
```
</ex-own-boolean-1>

Let us see how we actually defined `toBeAMultipleOf`. 
1. *Choose the extension point*: in our example we want to provide the expectation function for `Int`s. 
    Hence, we define `toBeAMultipleOf` as [extension function](https://kotlinlang.org/docs/reference/extensions.html) of `Expect<Int>`.

2. *Use the method `_logic.createAndAppend`* which creates and appends 
    the expectation to itself (creating alone is not enough, it needs to be appended in order that it is evaluated). 
    The method `createAndAppend` returns an `Expect` for the current subject, making it easy for you to provide a fluent API as well.
 
    The method [createAndAppend](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.creating/-assertion-container/create-and-append.html)
    expects:
    - either a `String` or a [Translatable](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translatable/index.html)
      as description of your expectation.
    - the representation of the expected value.
    - and the actual check as lambda where you typically use `it` which refers to the subject of the expectation.
     
We used a `String` as description in the above example because we are not bothered with internationalization at this point
(have a look at [Internationalization](#internationalization-1) if you do).

In most cases you probably use the expected value itself as its representation -- so you pass it as second argument.
And finally you specify the test as such in the lambda passed as third argument.

But not all expectation functions require a value which is somehow compared against the subject 
-- some state an expectation about a characteristic of the subject without comparing it against an expected value.
Consider the following expectation function:

<code-own-boolean-2>

```kotlin
import ch.tutteli.atrium.logic._logic

fun Expect<Int>.toBeEven() =
    _logic.createAndAppend("is", Text("an even number")) { it % 2 == 0 }
```
</code-own-boolean-2>

We are using a [Text](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-text/index.html) as 
representation so that `"an even number"` is not treated as a `String` in reporting.
Its usage looks then as follows:

<ex-own-boolean-2>

```kotlin
expect(13).toBeEven()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationFunctionsSpec.kt#L57)</sub> ↓ <sub>[Output](#ex-own-boolean-2)</sub>
<a name="ex-own-boolean-2"></a>
```text
I expected subject: 13        (kotlin.Int <1234789>)
◆ is: an even number
```
</ex-own-boolean-2>

## Compose Expectation Functions

So far, we core contributors ran quickly into the situation where we wanted to compose functions or
reuse existing functions but with different arguments. 
We will show both use cases here, starting off by composing functions. 

Say you want to build a `toBeBetween` expectation function for `java.util.Date`, you could write it as follows:

<code-own-compose-1>

```kotlin
fun <T : Date> Expect<T>.toBeBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    toBeGreaterThanOrEqualTo(lowerBoundInclusive).and.toBeLessThan(upperBoundExclusive)
```
</code-own-compose-1>

Pretty simple, isn't it?
Notice though, that this function fails fast, which means, the upper bound is not evaluated 
if the expectation about the lower bound already fails. 
You need to use an [expectation-group](#define-single-expectations-or-an-expectation-group) 
if you want that both are evaluated:

<code-own-compose-2>

```kotlin
import ch.tutteli.atrium.logic._logic

fun <T : Date> Expect<T>.toBeBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    _logic.appendAsGroup {
        toBeGreaterThanOrEqualTo(lowerBoundInclusive)
        toBeLessThan(upperBoundExclusive)
    }
```
</code-own-compose-2>

Still simple enough.

<details>
<summary>💬 Why is a type parameter used in the above examples?</summary>

That is right, we used a type parameter `T: Date` and not `Expect<Date>` directly. 
You should always do this unless your type is final (not `open`) and does not have type parameters itself - but to have a simple rule, just do it. 
This way the expectation function is also available for sub types. This is because `Expect` is [invariant](https://kotlinlang.org/docs/reference/generics.html#variance). 
Following an example:
```kotlin
interface A { val foo get() = 1 }
class B: A
val Expect<A>.foo get() = feature(A::foo)

expect(B()).foo // does not compile as foo is only available for `Expect<A>`
```
    
</details>


So let's move on to an example which is a bit more complicated. Assume the following data class `Person`

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

Say you want to postulate an expectation about the number of children a person has:

<code-own-compose-3b>

```kotlin
fun Expect<Person>.toHaveNumberOfChildren(number: Int): Expect<Person> =
    feature(Person::children) { toHaveSize(number) }

```
</code-own-compose-3b>

Three things to notice here: 
1. we make use of a [feature extractor with class reference](#within-expectation-functions--feature-extractors).
2. We use the overload which expects an `assertionCreator`-lambda. This way subsequent expectations are still made on `Person` and not on `children`.
 
Its usage is then as follows:

<ex-own-compose-3>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf()))
    .toHaveNumberOfChildren(2)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationFunctionsSpec.kt#L74)</sub> ↓ <sub>[Output](#ex-own-compose-3)</sub>
<a name="ex-own-compose-3"></a>
```text
I expected subject: Person(firstName=Susanne, lastName=Whitley, age=43, children=[])        (readme.examples.Person <1234789>)
◆ ▶ children: []        (kotlin.collections.EmptyList <1234789>)
    ◾ ▶ size: 0        (kotlin.Int <1234789>)
        ◾ to equal: 2        (kotlin.Int <1234789>)
```
</ex-own-compose-3>

Another example: expect the person to have children which are all adults (assuming 18 is the age of majority).

<code-own-compose-4>

```kotlin
fun Expect<Person>.toHaveAdultChildren(): Expect<Person> =
    feature(Person::children) {
        toHaveElementsAndAll {
            feature(Person::age).toBeGreaterThanOrEqualTo(18)
        }
    }

```
</code-own-compose-4>

We once again use `feature` with an [expectation-group](#define-single-expectations-or-an-expectation-group) 
for the same reason as above.
Note how `toHaveElementsAndAll` already checks that there is at least one element. 
I.e. it fails for a `Person` with 0 children, because such a person does not have adult children. 

<ex-own-compose-4>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf()))
    .toHaveAdultChildren()
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationFunctionsSpec.kt#L91)</sub> ↓ <sub>[Output](#ex-own-compose-4)</sub>
<a name="ex-own-compose-4"></a>
```text
I expected subject: Person(firstName=Susanne, lastName=Whitley, age=43, children=[])        (readme.examples.Person <1234789>)
◆ ▶ children: []        (kotlin.collections.EmptyList <1234789>)
    ◾ to have: a next element
      » elements need all: 
          » ▶ age: 
              ◾ to be greater than or equal to: 18        (kotlin.Int <1234789>)
```
</ex-own-compose-4>

If we keep adding expectation functions involving `children` it might be best to provide a shortcut property and function.

<code-own-compose-5>

```kotlin
val Expect<Person>.children: Expect<Collection<Person>> get() = feature(Person::children)

fun Expect<Person>.children(assertionCreator: Expect<Collection<Person>>.() -> Unit): Expect<Person> =
    feature(Person::children, assertionCreator)
```
</code-own-compose-5>

Notice, that we have used a class-reference and not a bounded-reference to refer to `children` which is best practice 
(see [feature extractor within expectation functions]( #within-expectation-functions--feature-extractors)).
With this, we can write things like:

<ex-own-compose-5>

```kotlin
expect(Person("Susanne", "Whitley", 43, listOf(Person("Petra", "Whitley", 12, listOf()))))
    .children { // using the fun -> expectation-group, ergo sub expectations don't fail fast
        toHaveElementsAndNone {
            feature { f(it::firstName) }.toStartWith("Ro")
        }
        toHaveElementsAndAll {
            feature { f(it::lastName) }.toEqual("Whitley")
        }
    } // subject is still Person here
    .children  // using the val -> subsequent expectations are about children and fail fast
        .toHaveSize(2)
        .toHaveElementsAndAny {
            feature { f(it::age) }.toBeGreaterThan(18)
        }
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationFunctionsSpec.kt#L101)</sub> ↓ <sub>[Output](#ex-own-compose-5)</sub>
<a name="ex-own-compose-5"></a>
```text
I expected subject: Person(firstName=Susanne, lastName=Whitley, age=43, children=[Person(firstName=Petra, lastName=Whitley, age=12, children=[])])        (readme.examples.Person <1234789>)
◆ ▶ children: [Person(firstName=Petra, lastName=Whitley, age=12, children=[])]        (java.util.Collections.SingletonList <1234789>)
    ◾ ▶ size: 1        (kotlin.Int <1234789>)
        ◾ to equal: 2        (kotlin.Int <1234789>)
```
</ex-own-compose-5>

<hr/>

Enough of expectation functions for features. Let's move on to an example where we want to reuse an existing function 
but with different arguments. Say we have a function which returns a list of first name / last name `Pair`s. 
We want to assert that the pairs contain only the first name / last name pairs of certain `Person`s in any order.
[Collection Expectations](#collection-expectations) will help us with this. 
However, `toContain.inAnyOrder.values` expects `Pair`s.
So we have to map from `Person` to `Pair` upfront.
As we have a variable length argument list and want to pass it to a variable length argument list, this cannot be done with a simple `map` from Kotlin. 
And it gets worse if we want to use `toContain.inAnyOrder.entries` which expects at least one `assertionCreator`-lambda (`Expect<T>.() -> Unit`)
because Kotlin cannot infer the types automatically.

`mapArguments` to the rescue, you can write the expectation function as follows:

<code-own-compose-6>

```kotlin
import ch.tutteli.atrium.logic.utils.mapArguments

fun <T : List<Pair<String, String>>> Expect<T>.areNamesOf(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
    return toContain.inAnyOrder.only.values(pair, *otherPairs)
}
```
</code-own-compose-6>

As you can see we moved the mapping inside the function so that the consumer of our API can happily use it as follows:
```kotlin
expect(get...WhichReturnsPairs()).areNamesOf(fKafka, eBloch, kTucholsky)
```

Another fictional example, say we expect that the pairs have the same initials as the given persons and in the given order.
Which means, this time we need to use `assertionCreator`-lambdas. This can be written as follows:

<code-own-compose-7>

```kotlin
fun <T : List<Pair<String, String>>> Expect<T>.sameInitialsAs(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (first, others) = mapArguments(person, otherPersons).toExpect<Pair<String, String>> {
        first.toStartWith(it.firstName[0].toString())
        second.toStartWith(it.lastName[0].toString())
    }
    return toContain.inOrder.only.entries(first, *others)
}
```
</code-own-compose-7>

There are a few additional methods which you can call after `mapArguments`.
See [KDoc of ArgumentMapperBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.logic.utils/-argument-mapper-builder/index.html).
In case you want to provide your own implementation, 
it suffices to create an extension function for `ArgumentMapperBuilder`. 

## Enhanced Reporting

[Composing expectation functions](#compose-expectation-functions) give already quite a bit of power to an expectation function writer.
Yet, sometimes we would like to create functions which have a better error reporting than the one we get 
when we compose expectation functions.

[`_logic`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/logic.kt#L21) 
is the entry point to `AssertionContainer` which is the equivalent of `Expect` but on a lower level.

Following a quick overview what extension methods could be useful:
- all expectation functions on the logic level (what you have seen in [Compose expectation functions](#compose-expectation-functions) 
was the API level) so that you can reuse and compose them in other ways.
- `changeSubject` which allows to change the subject either:
   - `unreported`; meaning it does not show up in reporting (e.g. `Expect<Array<out T>>.asList()` uses it, see [arrayAssertions](https://github.com/robstoll/atrium/tree/main/apis/fluent-en_GB/atrium-api-fluent-en_GB/src/main/kotlin/ch/tutteli/atrium/api/fluent/en_GB/arraySubjectChangers.kt#L20))
   - reported, using `reportBuilder`; meaning a subject transformation which is shown in reporting as it incorporates a transformation (e.g. `toBeAnInstanceOf` uses it, see [AnyAssertions](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/impl/DefaultAnyAssertions.kt#L66))
- `collect` which allows to collect expectations - especially helpful in composing expectations (see [mapAssertions](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/impl/DefaultMapAssertions.kt#L49))
- `extractFeature` for feature extraction where it is not always save to extract (see [`List.get`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/impl/DefaultListAssertions.kt#L13))   

Besides, the `assertionBuilder` allows to create different kinds of Assertions 
(see [AssertionBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) for more information)
which can be used to create very specific expectation functions. 

You can find an example in [floatingPointAssertions](https://github.com/robstoll/atrium/blob/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/impl/DefaultFloatingPointAssertions.kt#L72)
which makes use of explanatory assertions as well as providing a failure hint.

Unfortunately we do not have the time to cover all cases, so let us know if you want to know more
-- either by opening an issue or via the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](https://slack.kotlinlang.org/)).

## Own Sophisticated Expectation Builders

Do you want to write an own sophisticated expectation builder (or extend a current one with more options) instead of an expectation function?
Great, we do not provide documentation yet (had only one question about it since 2017). 

We are willing to provide more documentation if you need it (please open an issue). 
In the meantime we might help you via slack, please post your questions in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet).

# Use own Expectation Verb

Atrium offers the expectation verb `expect` out of the box. 

You can also define your own expectation verb if `expect` does not suite you or in case you want to change some default implementation.
In order to create an own expectation verb it is sufficient to:
 1. Copy the file content of [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/main/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch.tutteli.atrium.api.verbs.internal/atriumVerbs.kt)
 2. Create your own atriumVerbs.kt and paste the previously copied content 
    -- notice that you can also use a `String` for the expectation verb in case you do not care about [Internationalization](#internationalization-1)
 3. Adjust package name and `import`s and rename `expect` as desired (you can also leave it that way of course).
 4. exclude `atrium-verbs` from your dependencies. 
    Taking the setup shown in the [Installation](#installation) section for the JVM platform, you would replace the `dependencies` block as follows:
    ```gradle
    dependencies {
        testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atriumVersion") {
            exclude group: 'ch.tutteli.atrium', module: 'atrium-verbs'
        }
    }
    ```

What are the benefits of creating an own expectation verb:
- you can encapsulate the reporting style. <br/>
  This is especially useful if you have multiple projects and want to have a consistent reporting style.  
  For instance, you could change from same-line to multi-line reporting or report not only failing but also successful expectations, change the output language etc.
  
    <details>
    <summary>💬 where should I put the atriumVerbs.kt?</summary>
    
    We suggest you create an adapter project for Atrium where you specify the expectation verb. 
    And most likely you will accumulate them with expectation functions which are so common 
    that they appear in multiple projects -- please share them with us (get in touch with us via issue or slack) if they are not of an internal nature 😉
    
    <hr/>
    </details>

 
What are the drawbacks:
- you have to maintain your expectation verb. That should not be a big deal though
  -- you might have to replace deprecated options by their replacement when you upgrade to a newer Atrium version but that's about it.

## Use own Components

Replacing existing components with your own (or third-party) components can be done when specifying an own expectation verb
via `withOptions`. See for instance [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/main/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch.tutteli.atrium.api.verbs.internal/atriumVerbs.kt#L31)
which is used internally of Atrium in tests and uses a different `AtriumErrorAdjuster`.

Another example, say you prefer multi-line reporting over single-line reporting,
then you can use `withOptions` as follows:

<code-own-expectation-verb>

```kotlin
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build

@OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(subject: T): RootExpect<T> =
    RootExpectBuilder.forSubject(subject)
        .withVerb("expected the subject")
        .withOptions {
            withComponent(TextAssertionPairFormatter::class) { c ->
                TextAssertionPairFormatter.newNextLine(c.build(), c.build())
            }
        }
        .build()
```
</code-own-expectation-verb>

Following an example using the expectation verb

<ex-own-expectation-verb>

```kotlin
expect(10).toEqual(9)
```
↑ <sub>[Example](https://github.com/robstoll/atrium/tree/main/misc/tools/readme-examples/src/main/kotlin/readme/examples/OwnExpectationVerbSpec.kt#L51)</sub> ↓ <sub>[Output](#ex-own-expectation-verb)</sub>
<a name="ex-own-expectation-verb"></a>
```text
expected the subject:
  10        (kotlin.Int <1234789>)
◆ to equal:
  9        (kotlin.Int <1234789>)
```
</ex-own-expectation-verb>

Compare the above output with what we would get per default:
```
expected the subject: 10        (kotlin.Int <1234789>)
◆ to be: 9        (kotlin.Int <1234789>)
```

You prefer another reporting style but Atrium does not yet support it? 
Please let us know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

There are more options to choose from. Take a look at the 
[DefaultComponentFactoryContainer](https://github.com/robstoll/atrium/tree/main/core/atrium-core-common/src/main/kotlin/ch/tutteli/atrium/creating/impl/ComponentFactoryContainerImpl.kt#L121)
to see the default configuration.

# Internationalization

We distinguish between two use cases. 
You might want to generate the [Report](#report) in a different language or/and you might want to use the [API in a different language](#api-in-a-different-language). 

## Report
Following on the example in [Write own Expectation Functions](#write-own-expectation-functions)
we show here how you need to write the `toBeAMultipleOf` function, so that it supports i18n. 
This way the report could be generated in another language.

The difference lies in the first argument passed to `createAndappend`; 
we do no longer use a `String` but a proper `Translatable`. 

<code-i18n-1>

```kotlin
import ch.tutteli.atrium.logic.*

fun Expect<Int>.toBeAMultipleOf(base: Int): Expect<Int> =
    _logic.createAndAppend(DescriptionIntAssertion.TO_BE_A_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertion(override val value: String) : StringBasedTranslatable {
    TO_BE_A_MULTIPLE_OF("to be a multiple of")
}
```
</code-i18n-1>

Typically, you would put `DescriptionIntAssertion` into an own module (jar) 
so that it could be replaced (with zero performance cost) by another language representation.
For instance,
[atrium-fluent-en_GB-common](https://github.com/robstoll/atrium/tree/main/bundles/fluent-en_GB/atrium-fluent-en_GB-common/build.gradle)
uses `atrium-translations-en_GB` whereas 
tests of 
[atrium-infix_en_GB-common](https://github.com/robstoll/atrium/tree/main/bundles/infix-en_GB/atrium-infix-en_GB-common/build.gradle)
uses `atrium-translations-de_CH`.  

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
due to encoding problems and missing implementations on other platforms than JVM.

Notice, Atrium does not yet support the generation of multiple reports in the same test run. 
This might become handy if you want to generate an HTML report in different languages.   
However, Atrium is designed to support this use case -- if you need this feature, then please let us know it by writing a 
[feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

<hr/>
</details><br/>

Let us rewrite the `toBeEven` expectation function from the section [Write own Expectation Functions](#write-own-expectation-functions)
as second example:

<code-i18n-2>

```kotlin
import ch.tutteli.atrium.logic.*

fun Expect<Int>.toBeEven(): Expect<Int> =
    _logic.createAndAppend(DescriptionBasic.TO_BE, DescriptionIntAssertions.EVEN) { it % 2 == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
```
</code-i18n-2>

Once again we have to wrap the text which we want to be able to exchange with another language into a `Translatable`. 
Notice also, that we are reusing a `Translatable` from `DescriptionBasic`.

## API in a different Language

Following on the example in the previous section, 
we want to write `toBeAMultipleOf` in such a way that one cannot only generate a report in a different language
but also that one can use the function itself in a different language. 
Or in other words, provide our API in a different language (the same applies if you want to provide another API style).

We split up the function in two parts: API and logic 
-- whereas the logic creates the expectation and the API provides a function for the user (the API as such) and
merely appends the expectation created by the logic to the `Expect`.
 
Typically, you put the API function in one module (jar) and the logic in another (so that the API can be exchanged).
In the logic module we define an extension method for [AssertionContainer](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.creating/-assertion-container/index.html)

<code-i18n-3a>

```kotlin
import ch.tutteli.atrium.creating.AssertionContainer

fun AssertionContainer<Int>.toBeAMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertion.TO_BE_A_MULTIPLE_OF, base) { it % base == 0 }
```
</code-i18n-3a>

In the above example we created a simple [DescriptiveAssertion](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions/-descriptive-assertion/index.html)
with the help of `createDescriptiveAssertion` defined on AssertionContainer.
We pass in a description (`TO_BE_A_MULTIPLE_OF`), we use `base` as representation of the expectation 
and define a lambda which implements a test to define whether the expectation holds or not.

In the API module we define the extension function and append the expectation to the current `Expect`
by using `logicAppend` and calling the extension function from the logic module within the corresponding lambda. 

<code-i18n-3b>

```kotlin
import ch.tutteli.atrium.logic.*

fun Expect<Int>.toBeAMultipleOf(base: Int): Expect<Int> =
    _logicAppend { toBeAMultipleOf(base) }
```
</code-i18n-3b>

At first, this looks like a recursive call. But as explained, within the `_logicAppend`-lambda 
we are on the logic level and thus call the function we defined above (which just turns out to have the same name).

You are ready to go, creating an API in a different language -- e.g. in German -- is now only a routine piece of work:

<code-i18n-3c>

```kotlin
import ch.tutteli.atrium.logic.*

fun Expect<Int>.istEinVielfachesVon(base: Int): Expect<Int> =
    _logicAppend { toBeAMultipleOf(base) }
```
</code-i18n-3c>

<a name="apis"></a>
# API Styles
Atrium supports currently two API styles: pure `fluent` and `infix`.
Both have their design focus on interoperability with code completion functionality of your IDE 
-- so that you can let your IDE do some of the work.

Atrium is 
[built up by different modules](https://docs.atriumlib.org/latest#/doc/) 
and it is your choice which implementation you want to use. 
However, this is more intended for advanced user with special requirements.
Atrium provides bundle modules which bundle API, logic, core, translation as well as predefined expectation verbs,
so that you just have to have a dependency on one of those bundles (kind a bit like a BOM pom in the maven world):

- [atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/main/bundles/fluent-en_GB/atrium-fluent-en_GB-common/build.gradle)
- [atrium-infix-en_GB](https://github.com/robstoll/atrium/tree/main/bundles/infix-en_GB/atrium-infix-en_GB-common/build.gradle)

Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/main/apis/differences.md)
for more information and to see how the API styles differ.
 

# Java Interoperability
Atrium provides some helper functions in case you have to deal with Java Code where not all types are non-nullable. 
[Platform types](https://kotlinlang.org/docs/java-interop.html#null-safety-and-platform-types)
are turned into a non-nullable version per default (if possible). 

Yet, that might not be what you want, especially if you know that certain functions return potentially `null` 
or in other words, the return type of those functions should be treated as nullable in Kotlin. 
Therefore, you want to turn the platform type into the nullable version. 

You need to use a cast to do this. But depending on your return type this might be cumbersome especially if you deal with type parameters. 
Thus, Atrium provides the following functions to ease dealing with Java Code at least for some standard cases:
- [`nullable`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/utils/nullable.kt#L19)
  turns a type into a nullable type and a return type of a KFunction into a nullable type.
- [`nullableContainer`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/utils/nullable.kt#L40)
  turns an `Iterable` into an iterable with nullable element type, likewise it does the same for `Array`.
- [`nullableKeyMap`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/utils/nullable.kt#L66)
  turns a `Map` into a map with a nullable key type.
- [`nullableValueMap`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/utils/nullable.kt#L79)
  turns a `Map` into a map with a nullable value type.
- [`nullableKeyValueMap`](https://github.com/robstoll/atrium/tree/main/logic/atrium-logic-common/src/main/kotlin/ch/tutteli/atrium/logic/utils/nullable.kt#L92)
  turns a `Map` into a map with a nullable key and nullable value type. 
    
 
# KDoc - Code Documentation
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://docs.atriumlib.org/)

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out-of-the-box expectation functions. 
More functions will follow but only if they are used somewhere by someone. 
So, let us know if you miss something by creating a [feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

# FAQ
You find frequently asked questions below.
If your question is not answered below, then please do not hesitate and ask your question in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ).
In case you do not have an account for kotlinlang.slack.com yet, then please [Invite yourself](https://slack.kotlinlang.org/). 

## Does Atrium provide something like AssertJ's soft assertion?
Of course and even more powerful yet less cumbersome to write in our opinion.
Check out the [comparison of expectation-groups with AssertJ's soft assertions](#expecation-groups-are-better-soft-assertions).

## Are there toContain/toHaveNextAndAll/None/All expectation functions for `Sequence`/`Array`?

Atrium does not provide extension function applicable to `Expect<Sequence<E>>` (or `Array`) directly,
because they would basically duplicate the functions available for `Iterable<E>`.  
However, Atrium provides `asIterable` and `asList` so that you can turn `Expect<Sequence<E>>` 
into `Expect<Iterable<E>>` or `Expect<List<E>>`. An example:

<code-faq-1>

```kotlin
expect(sequenceOf(1, 2, 3)).asIterable().toContain(2)
```
</code-faq-1>

Likewise, you can turn an `Expect<Array<E>>`, `Expect<DoubleArray>` etc. into an `Expect<List<E>>` with `asList`.

Feel free vote for [first class support for Array and Sequence in api-fluent](https://github.com/robstoll/atrium/issues/459).

<details>
<summary>💬 why do I not see anything about the transformation in reporting?</summary>

`asIterable` uses `_logic.changeSubject.unreported` internally which is intended for not showing up in reporting.
If you would like that the transformation is reflected in reporting then you can use a regular feature extractor 
as follows:

<code-faq-2>

```kotlin
expect(sequenceOf(1, 2, 3)).feature { f(it::asIterable) }.toContain(2)
```
</code-faq-2>

</details>

## Where do I find a list of all available functions?

Atrium provides KDoc for all APIs - have a look at their KDoc:
- [atrium-api-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html)
- [atrium-api-infix-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.infix.en_-g-b/index.html)

## Problems in conjunction with `feature`

See [Ambiguity Problems](#ambiguity-problems) and [Property does not exist](#property-does-not-exist).

# Roadmap

The roadmap is maintained at [atrium-roadmap](https://github.com/robstoll/atrium-roadmap).
The milestones give you an overview of the planned (breaking) changes
-- e.g. the changes for the next major version [1.0.0](https://github.com/robstoll/atrium-roadmap/issues?utf8=%E2%9C%93&q=is%3Aissue+milestone%3A1.0.0+)

You are invited to take part in the discussions related to design decisions, upcoming features and more.
Bring in your own wishes and ideas into this process.
  
In case you are missing a particular expectation function in Atrium, then please open a 
[Feature Request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) 
in this repository.

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
- share your expectation functions with the rest of us by creating a pull request (no need for i18n support or the like, we can augment your pull request).
- have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  if you would like to code (ping us on [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) if there are not any).  

Please have a look at 
[CONTRIBUTING.md](https://github.com/robstoll/atrium/tree/main/.github/CONTRIBUTING.md)
for further suggestions and guidelines.

# Sponsors
We would like to thank the following sponsors for their support:
- [Tegonal Cooperative](https://tegonal.com) for sponsoring Support and PR-Review time.

Do you want to become a sponsor as well? Great, have a look at the following GitHub sponsor profiles:
- [robstoll](https://github.com/sponsors/robstoll) (Author and main contributor)

or ping @robstoll in the Slack-Channel if you would like to support the project in another way.

# License
Atrium is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Atrium is using:
- [KBox](https://github.com/robstoll/kbox) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
- [Niok](https://github.com/robstoll/niok) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
