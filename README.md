[![Slack](https://img.shields.io/badge/Slack-atrium@kotlinlang-blue.svg)](https://kotlinlang.slack.com/messages/C887ZKGCQ)
[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium/branches)
[![Coverage](https://codecov.io/github/robstoll/atrium/coverage.svg?branch=master)](https://codecov.io/github/robstoll/atrium?branch=master)

# Atrium
Atrium is an open-source assertion library for Kotlin with a fluent API.
The project was inspired by AssertJ at first (and was therefore named AssertK) but it moved on and provides now more 
flexibility and features to its users (so to you :wink:).

----
:warning: You are taking a *sneak peek* at the next version. 
Please have a look at the README of the git tag in case you are looking for the documentation of the corresponding version.
For instance, the [REAMDE of v0.5.0](https://github.com/robstoll/atrium/tree/v0.5.0/README.md).

----

Atrium is designed to support different [APIs](#apis), different reporting styles and [Internationalization](#internationalization) (i18n).
The core of Atrium as well as the builders to create sophisticated assertions are designed to be extensible and 
thus allow you to extend or replace components easily.

Atrium currently provides two [APIs](#apis) styles:
pure fluent and infix where both of them have their design focus on usability in conjunction with code completion functionality provided by your IDE.
See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

**Table of Content**
- [Installation](#installation)
- [Examples](#examples)
  - [Your First Assertion](#your-first-assertion)
  - [Define Single Assertions or Assertion Groups](#define-single-assertions-or-assertion-groups)
  - [Nullable Types](#nullable-types)
  - [Expect an Exception](#expect-an-exception)
  - [Property Assertions](#property-assertions)
  - [Method Assertions](#method-assertions)
  - [Collection Assertions](#collection-assertions)
    - [Shortcut Functions](#shortcut-functions)
    - [Sophisticated Assertion Builders](#sophisticated-assertion-builders)
  - [Further Examples](#further-examples)  
- [Write own Assertion Functions](#write-own-assertion-functions)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
  - [ReporterBuilder](#reporterbuilder)
- [Internationalization](#internationalization)
- [APIs](#apis)
- [Contribute](#contribute)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [FAQ](#faq)
- [Roadmap](#roadmap)
- [License](#license)

# Installation
Atrium is linked to [jcenter](https://bintray.com/bintray/jcenter?filterByPkgName=atrium)
but can also be retrieved directly from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium). 

gradle: 
```
buildscript {
    ext { atrium_version='0.5.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository below
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
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
We are using the API provided by the bundle module 
[atrium-cc-en_UK-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-en_UK-robstoll/build.gradle)
in the following examples. It provides a pure fluent API. 
Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
to see how the infix API looks like.

## Your First Assertion
We start off with a simple example:
```kotlin
val x = 10
assert(x).toBe(9)
``` 
The statement can be read as "I assert, x to be nine" where an equality check is used (use `isSame` for an identity check). 
Since this is false an `AssertionError` is thrown with the following message:
```text
assert: 10        (java.lang.Integer <934275857>)
◆ to be: 9        (java.lang.Integer <1364913072>)
```
where `◆ ...` represents a single assertion for the subject (`10` in the above example) of the assertion.
The examples in the following sections include the error message (the output) in the code example itself as comments.

Atrium lets you choose the assertion verb (`assert` in the above example). 
Regardless whether you prefer `expect`, `assertThat` or yet another assertion verb/phrase
you can [define your own assertion verbs](#use-own-assertion-verbs) which suit your coding style.
In the following examples we will use `assert` for regular assertions 
and `expect` to postulate that we [Expect an Exception](#expect-an-exception).

:information_source: An [own assertion verb](#use-own-assertion-verbs) lets you configure inter alia the reporting style by using the [`ReporterBuilder`](#reporterbuilder). 
For instance, in case you prefer multi-line reporting over single-line reporting,
then you can configure `ReporterBuilder` as follows.
Instead of using `.withTextSameLineAssertionPairFormatter()` you use the following:
```
.withTextAssertionPairFormatter { objectFormatter, translator ->
    TextNextLineAssertionPairFormatter(objectFormatter, translator)
}
```
The shown output above would then look as follows:
```text
assert: 
  10        (java.lang.Integer <934275857>)
◆ to be: 
  9        (java.lang.Integer <1364913072>)
```

There are more options to choose from if you use your [own assertion verb](#use-own-assertion-verbs). 
But back to the examples. 
The next section shows how you can define multiple assertions for the same subject.   

## Define Single Assertions or Assertion Groups

```kotlin
 // two single assertions
 
assert(4 + 6).isLessThan(5).isGreaterThan(10)
    // assert: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)
```


Using the fluent API allows you to write the `assert(...)` part only once but making several single assertions for the same 
[subject](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.creating/-base-assertion-plant/subject.html).
The expression which determines the subject of the assertion (`4 + 6` in the above example) is evaluated only once. 

So the first statement could also be written as follows (unless the expression determining the subject has side effects).
```kotlin
assert(4 + 6).isLessThan(5)
assert(4 + 6).isGreaterThan(10)
``` 

Correspondingly, the first `assert` statement (which does not hold) throws an `AssertionError`. 
In the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated at all.

If you want that both assertions are evaluated together, then use the assertion group syntax as follows: 

```kotlin
// assertion group

assert(4 + 6) {
    isLessThan(5)
    isGreaterThan(10)
}
    // assert: 10        (java.lang.Integer <1841396611>)
    // ◆ is less than: 5        (java.lang.Integer <1577592551>)
    // ◆ is greater than: 10        (java.lang.Integer <1841396611>)
```

An assertion group throws an `AssertionError` at the end of its block; hence reports that both assertions do not hold.

Such a block is actually nothing else than a [lambda with a receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver)
of type `AssertionPlant` (code-ish speaking `AssertionPlant<T>.() -> Unit`).
The only thing you need to know about `AssertionPlant` at the moment is, that `assert(4 + 6)` creates an `AssertionPlant<Int>` 
and that all assertion functions are defined as [extension function](https://kotlinlang.org/docs/reference/extensions.html)
of `AssertionPlant`.
Have a look at [writing an own assertion function](#write-own-assertion-functions) to get more information about `AssertionPlant`.

:information_source: You can use `and` as filling element between single assertions and assertion group blocks:
```kotlin
assert(4 + 6).isLessThan(5).and.isGreaterThan(10)

assert(4 + 6) { 
    // ... 
} and { 
    // ...
}
```
 
## Nullable Types
```kotlin
val subtitle : String? = "postulating assertions made easy"
assert(subtitle).isNull()
    // assert: "postulating assertions made easy"        <22600334>
    // ◆ to be: null

assert(subtitle).isNotNull{ startsWith("atrium") }
    //assert: "postulating assertions made easy"        <651100072>
    //◆ starts with: "atrium"        <222427158>
```
If the subject of the assertion has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html) then 
you need to define first, whether you expect it to be `null` or not. 
In case you expect that it `isNotNull` you can define one or more subsequent assertions 
for the subject as if it had a non-nullable type  (`String` in the above example) by defining an 
[assertion group block](#define-single-assertions-or-assertion-groups) 
-- `{ startsWith("atrium") }` in the above example.
 
## Expect an Exception
```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException>()

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
    // expect the thrown exception: java.lang.IllegalArgumentException: name is empty        (java.lang.IllegalArgumentException <371800738>)
    // ◆ ▶ message: "name is empty"        <1364767791>
    //     ◾ starts with: "firstName"        <1499136125>
```
Notice `message` in the 
[assertion group block](#define-single-assertions-or-assertion-groups) 
is a shortcut for `property(subject::message).isNotNull()`, which creates a property assertion (see next section) 
about `Throwable::message`.  
 
## Property Assertions
```kotlin
data class Person(val name: String, val isStudent: Boolean)
val myPerson = Person("Robert", false) 

assert(myPerson) {
    property(subject::name).toBe("Peter")
    property(subject::isStudent).isTrue()
}
    // assert: Person(name=Robert, isStudent=false)        (Person <1841396611>)
    // ◆ ▶ name: "Robert"        <1577592551>
    //     ◾ to be: "Peter"        <854587510>
    // ◆ ▶ isStudent: false
    //     ◾ to be: true    
```
You can also make assertions about one or several properties of the subject using `property` in an [assertion group block](#define-single-assertions-or-assertion-groups)
-- general speaking, it allows you to create feature assertions without the need of own assertion functions. 

`subject` within an assertion group block refers to the subject of the assertion (`myPerson` in the above example). 
As a reminder, an assertion group block is a lambda with the `AssertionPlant` created by `assert(...)` as receiver (see [assertion group block](#define-single-assertions-or-assertion-groups) for further details). 
You probably have already guessed it, `subject` is actually a property of `AssertionPlant`. 

Back to property assertions. In the above example we created two feature assertions: one for the property `name` and the other for the property `isStudent` of `myPerson`.
A feature assertion is indicated as follows in the output. It starts with a `▶` followed by the feature's name and its actual value.
So the above output can be read as "I assert, Person's name (which is actually `"Robert"`) to be `"Peter"` and its property `isStudent` (which is actually `false`) to be `true`". 

:interrobang: You might be asking yourself why I bothered providing the assertion function `message` shown in the example 
[Expect an Exception](#expect-an-exception) and whether it is better to [write an own assertion function](#write-own-assertion-functions) or use `property`: 


The only drawback IMO of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it (as I did with `message`).
Yet, I do not recommend to write an own assertion function for every single property, because one quickly forgets to 
rename the assertion function if the property as such is renamed (e.g., as part of an IDE refactoring). 
As you can see, you would need to keep the property and the assertion function in sync. 

Hence, only write an own assertion function in case you use it a lot and you need to apply an `isNotNull` assertion first 
-- which is the case when the property has a nullable type (as it is the case for `Throwable::message`, see [Nullable Types](#nullable-types) for further information). 
In such a case I would find it too cumbersome to write `property(subject::xY).isNotNull { ... }` all the time and would prefer `xY { ... }` and make some extra effort when the property is renamed. 

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

assert(person) {
    returnValueOf(subject::fullName).contains("treboR", "llotS")
    returnValueOf(subject::nickname, false).toBe("Robert aka. Stoll")
}
    // assert: Person(firstName=Robert, lastName=Stoll)        (ch.tutteli.atrium.api.cc.en_UK.IterableContainsInOrderOnlyEntriesSpec$1$2$Person <168907708>)
    // ◆ ▶ fullName(): "Robert Stoll"        <447718425>
    //     ◾ contains: "treboR"        <1206569586>
    //       ⚬ ▶ number of occurrences: 0
    //           ◾ is at least: 1
    //     ◾ contains: "llotS"        <1427381743>
    //       ⚬ ▶ number of occurrences: 0
    //           ◾ is at least: 1
    // ◆ ▶ nickname(false): "Mr. Robert"        <1427646530>
    //     ◾ to be: "Robert aka. Stoll"        <846254484>
```
You can also make an assertion about a method of the subject or rather about the value which is returned when calling the method with some specified arguments. 
Such feature assertions can be made with the help of the assertion function `returnValueOf`. 
There are overloads to support methods with up to 5 parameters (notice, `fullName` has none and `nickname` has one parameter in the above example).

The error message shows also another nice feature of Atrium. 
It provides builders to create more sophisticated assertions.
Using `contains("treboR", "llotS")` is actually a shortcut for calling a sophisticated assertion builder for `CharSequence`. 
In this example it calls `contains.atLeast(1).values("treboR", "llotS")` which is reflected in the output. 
Have a look at the [KDoc of the CharSequence contains Builders](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.de_-c-h.assertions.charsequence.contains.builders/index.html)
to see more options.

:poop: Unfortunately, due to a [bug in Kotlin](https://youtrack.jetbrains.com/issue/KT-17340)
(please upvote it) you wont be able to use `returnValueOf` for a method which has overloads. 
As workaround you can use the following [impl-function](#api-in-a-different-language)
`ch.tutteli.atrium.assertions._methods` (which is not part of an API and might change in the 
future) as follows:
```kotlin
import ch.tutteli.atrium.assertions._methods
assert(person) {
    _method(this, "nickname", subject::nickname, false).toBe("Robert aka. Stoll")
}
```
The output is the same as above.

:interrobang: You might be asking yourself why I stopped at 5 Parameters.
You could go on and create further overloads for 6 and more parameters, but... uh... can you smell it :stuck_out_tongue_winking_eye:.
In case you have a function with 6 or more parameters and you do not want or cannot to get rid of it, 
then I suggest that you [write a specific assertion function](#write-own-assertion-functions) for it.

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
assert(listOf(1, 2, 2, 4)).contains(2, 3)        

    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1448525331>) 
    // ◆ contains, in any order: 3        (java.lang.Integer <1108924067>)
    //   ⚬ ▶ number of occurrences: 0
    //       ◾ is at least: 1
```
 
The assertion function `contains(2, 3)` is a shortcut for using a 
[Sophisticated Assertion Builder](#sophisticated-assertion-builders) -- it actually calls `contains.inAnyOrder.atLeast(1).values(2, 3)`. 
This is reflected in the output, which tells us that we expected that the `number of occurrences` of `3` (which is actually `0`) `is at least: 1`.
And what about the expected value `2`, why do we not see anything about it in the output?
The output does not show anything about the expected value `2` because we defined an 
[Only Failure Reporter](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium/-i-atrium-factory/new-only-failure-reporter.html) 
which shows us only assertions (or sub assertions) which failed.

Back to the shortcut functions Atrium provides for common `contains` assertions. 
Next to expecting that certain values (or objects) are contained in or rather returned by an `Iterable`, 
Atrium allows us to write identification lambdas in form of [assertion group blocks](#define-single-assertions-or-assertion-groups).
An entry is considered as identified if it holds all specified assertions of such a block.
Following an example:
```kotlin
assert(listOf(1, 2, 2, 4)).contains({ isLessThan(0) }, { isGreaterThan(2); isLessThan(4) })

    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1144068272>) 
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

The last two `contains` shortcut functions which Atrium provides for `Iterable<T>` are kind of the opposite of `inAnyOrder.atLeast(1)` and are named `containsStrictly`.
Again Atrium provides two overloads, one for values/objects, e.g. `containsStrictly(1, 2)` which calls `contains.inOrder.only.values(1, 2)` and
a second one which expects one or more identification lambdas, e.g. `containsStriclty({ isLessThan(0) }, { isGreaterThan(5) })` 
and effectively calls `contains.inOrder.only.entries({ isLessThan(2) }, { isGreaterThan(5) })`.
We will spare the examples here and show them in the following sections.

### Sophisticated Assertion Builders

The sophisticated assertion builders Atrium provides, implement a fluent builder pattern.
To use the assertion builder for sophisticated `Iterable<T>`-contains-assertions, you can type `contains` 
-- as you would when using the [Shortcut Functions](#shortcut-functions) -- 
but type `.` as next step (thus you are using the property `contains` instead of one of the shortcut functions). 
Currently, the builder provides two options, either `inAnyOrder` or `inOrder`. 
In case you are using an IDE, you do not really have to think too much; the fluent builders will guide you through your decision making :relaxed:

Following on the last section we will start with an `inOrder` example:
```kotlin
assert(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
 
    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <817978763>)
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

:heart_eyes: I am pretty sure you are going to love this feature as well
-- in case you are dealing with large `Iterable` and do not want such a verbose output, 
then let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature]). 
Also notice, that Atrium cannot yet deal with infinite `Iterable`s.

Following one more example for `inOrder` as well as a few examples for `inAnyOrder`. 
I think explanations are no longer required at this stage.
In case you have a question (no matter about which section), then please turn up in the 
[atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet) 
and I happily answer your question there. 

```kotlin
assert(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)

    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1362728240>)
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

assert(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })

    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1092572064>)
    // ◆ contains, in any order:   
    //   ⚬ an entry which:   
    //       » is less than: 3        (java.lang.Integer <1108924067>)
    //     ⚬ ▶ number of occurrences: 3
    //         ◾ is at most: 2


assert(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
            
    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <922511709>)
    // ◆ contains only, in any order:    
    //   ✔ an entry which is: 1        (java.lang.Integer <1578009262>) 
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>) 
    //   ✘ an entry which is: 3        (java.lang.Integer <1108924067>)  
    //   ✔ an entry which is: 4        (java.lang.Integer <1636506029>) 
    //   ✔ ▶ size: 4  
    //       ◾ to be: 4
    //   ❗❗ following entries were mismatched:    
    //      ⚬ 2        (java.lang.Integer <1948471365>)
    
assert(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)

    // assert: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <331994761>)
    // ◆ contains only, in any order:   
    //   ✔ an entry which is: 4        (java.lang.Integer <1636506029>)
    //   ✘ an entry which is: 3        (java.lang.Integer <1108924067>)
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>)
    //   ✔ an entry which is: 2        (java.lang.Integer <1948471365>)
    //   ✔ an entry which is: 1        (java.lang.Integer <1578009262>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 5
```     


## Further Examples

Atrium supports further assertion builders (e.g, for `CharSequence`) as well as assertion functions which have not been shown in the examples.
Have a look at the 
[specifications](https://github.com/robstoll/atrium/tree/master/atrium-spec/src/main/kotlin/ch/tutteli/atrium/spec) 
for more examples above.

Have a look at [apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md) for a few more examples.
This site contains also a list of all APIs with links to their assertion function catalogs.

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic functions 
[property](#property-assertions) and [returnValueOf](#method-assertions) 
are not good enough?

Writing one is very simple and a pull request of your new assertion function is very much appreciated.
Following an example:

```kotlin
fun Assert<Int>.isMultipleOf(base: Int) = createAndAddAssertion(
    Untranslatable("is multiple of"), base, { subject % base == 0 })
```
and its usage:

```kotlin
assert(12).isMultipleOf(5)
    // assert: 12        (java.lang.Integer <934275857>)
    // ◆ is multiple of: 5        (java.lang.Integer <1364913072>)
```

Let's see how we actually defined `isMultipleOf`. 
First of all, you need to know that `Assert` is a typealias for `AssertionPlant<T>` which in turn is the entry point for assertion functions.
We get an `AssertionPlant<Int>` when calling `assert(12)` and an `AssertionPlant<String>` for `assert("hello")`.
In our case we want to define the assertion function only for `subject`s of type `Int` 
hence we define `isMultipleOf` as 
[extension function](https://kotlinlang.org/docs/reference/extensions.html)
of `Assert<Int>`. You could have written the extension for `AssertionPlant<Int>` which is exactly the same. 
Whether you prefer `Assert` or `AssertionPlant` is up to you, 
though we recommend to use `Assert` for API functions and `AssertionPlant` in other cases 
(such as impl-functions, see [API in a different Language](#api-in-a-different-language) for instance). 
We then use the method `createAndAddAssertion` (which is provided by `AssertionPlant`) to create the assertion, 
add it to the plant itself 
and return the plant to support a fluent API. 
The method [createAndAddAssertion](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.creating/-assertion-plant/create-and-add-assertion.html) expects:
- a [Translatable](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.reporting.translating/-translatable/index.html)
  as  description of your assertion.
- the representation of the expected value.
- and the actual check as lambda where you typically use the `subject` of the assertion.
 
We use an `Untranslatable` as first argument here because we are not bothered with internationalization.
In case you want to report in a different language, then have a look at [Internationalization](#internationalization).
Typically you use the expected value itself as its representation -- so you pass it as second argument.

But not all assertion functions require a value which is somehow compared against the subject 
-- some make an assertion about a property of the `subject` without comparing it against an expected value.
Consider the following assertion function:

```kotlin
fun Assert<Int>.isEven() = createAndAddAssertion(
    DescriptionBasic.IS, RawString.create("an even number"), { subject % 2 == 0 })
```
We are using a [RawString](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.reporting/-raw-string/index.html)
here so that `"an even number"` is not treated as a `String` in reporting.
Also notice, that we are reusing a common description (`DescriptionBasic.IS`) as first argument.
Its usage looks then as follows:

```kotlin
assert(13).isEven()
    // assert: 13        (java.lang.Integer <1841396611>)
    // ◆ is: an even number
```

Do you want to write an own sophisticated assertion builder instead of an assertion function? 
Have a look at the implementation, for instance how the sophisticated assertion builders for `Iterable<T>` are defined:
[ch.tutteli.atrium.assertions.iterable.contains](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/assertions/iterable/contains).
Notice that the implementation supports [Internationalization](#internationalization).
If you have a question, then please post it in the 
[atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet)
and I will try to help you.

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

## ReporterBuilder

The `ReporterBuilder` lets you choose among different options to configure the style of the reporting.
For instance, in case you are not happy with the predefined bullet points, then you can change them via the `ReporterBuilder`.
Have a look at [atriumVerbs.kt of atrium-api-cc-de_CH](https://github.com/robstoll/atrium/tree/master/apis/atrium-api-cc-de_CH/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
where you can find an example.

You would like to switch to multi-line reporting, have a look at [Your First Assertion](#your-first-assertion) where the configuration of the `ReporterBuilder` is presented.

You prefer another reporting style but Atrium does not yet support it? 
Please let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature]).

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

# Internationalization

We distinguish between two use cases. 
You might want to generate the [Report](#report) in a different language or/and you might want to use the [API in a different language](#api-in-a-different-language). 

## Report
Following on the example in [Write own Assertion Functions](#write-own-assertion-functions)
we show here how you write the function, so that it supports i18n. 
This way the report could be generated in another language.

The difference lies in the first argument passed to `createAndAddAssertion`; we do no longer use an `Untranslatable` but a proper 
`Translatable`. 

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
[atrium-cc-en_UK-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-en_UK-robstoll/build.gradle)
uses `atrium-translations-en_UK` whereas 
[atrium-cc-de_CH-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-de_CH-robstoll/build.gradle)
uses `atrium-translations-de_CH`.  

But you can also use a 
[TranslationSupplier](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.reporting.translating/-translation-supplier/index.html)
based [Translator](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html)
by configuring the [`ReporterBuilder`](#reporterbuilder) accordingly (e.g. use `withDefaultTranslationSupplier` instead of `withoutTranslations`). 
Atrium supports a properties files based `TranslationSupplier` which is more or less what
[ResourceBundle](https://docs.oracle.com/javase/tutorial/i18n/resbundle/propfile.html)
provides out of the box. 
Yet, a `Translator` uses a more enhanced fallback mechanism compared to a `ResourceBundle`. 
For further technical information have a look at the KDoc of [Translator](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html).

Notice, Atrium does not yet support generating multiple reports (e.g., in different languages) in the same test run 
-- but Atrium is designed to support this use case. 
Hence, if you need this feature, then please let me know it by writing a 
[feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature]).

Let us rewrite the `isEven` assertion function from the section [Write own Assertion Functions](#write-own-assertion-functions)
as second example:
```kotlin
fun Assert<Int>.isEven() = createAndAddAssertion(
    DescriptionCommon.IS, RawString.create(DescriptionIntAssertions.EVEN), { subject % 2 == 0 })

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
```
Once again we have to wrap the text which we want to be able to exchange with another language into an `Translatable`. 
Since we want that the translation as such is treated as a raw string in reporting, we wrap it into a `RawString` as we did before. 

## API in a different Language

Following on the example in the previous section, 
we want to write `isMultipleOf` such that one cannot only generate a report in a different language
but also that one can use the function itself in a different language. 
Or in other words, provide our API in a different language.

We split up the function in two parts: API and implementation (well yes, its that simple). 
Moreover we put the API function in one module (jar) and the implementation in another.
In the implementation module we define, what we will call hereafter an impl-function 
-- we follow the convention that impl-functions are prefixed with `_`):
```kotlin
fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion =
    BasicDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base, { plant.subject % base == 0 })
```
Notice that it is not an extension function as before 
because we do not want to pollute the API of `AssertionPlant<Int>` (of `Assert<Int>` respectively) with this function.
We typically use `AssertionPlant` for impl-functions and `Assert` for API functions.  

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


# APIs
Atrium supports currently two API styles: pure fluent (`cc`) and infix (`cc-infix`) 
where `cc` exists in English and German; `cc-infix` only in English.
All have their design focus on interoperability with code completion (thus `cc`) functionality of your IDE 
-- so that you can let your IDE do some of the work.

Atrium is 
[built up by different modules](https://robstoll.github.io/atrium/latest#/doc/) 
and it is your chose which implementation you want to use. 
Atrium provides three modules which bundle API, translation and implementation, so that you just have to have a dependency on that one bundle:

- [atrium-cc-en_UK-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-en_UK-robstoll/build.gradle)
- [atrium-cc-de_CH-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-de_CH-robstoll/build.gradle)
- [atrium-cc-infix-en_UK-robstoll](https://github.com/robstoll/atrium/tree/master/bundles/atrium-cc-infix-en_UK-robstoll/build.gradle)

Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
for more information and to see how the API styles differ.
 
# Contribute
You are very welcome to contribute:
- [open an issue](https://github.com/robstoll/atrium/issues/new) or [create a feature request](https://github.com/robstoll/atrium/issues/new?title=[Feature])
- fork the repository and make a pull request
- [ask a question](https://kotlinlang.slack.com/messages/C887ZKGCQ)
  so that I better understand where Atrium needs to improve.

Please have a look at 
[CONTRIBUTING.md](https://github.com/robstoll/atrium/tree/master/.github/CONTRIBUTING.md)
for further suggestions and guidelines.

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
- assertion functions for floating point numbers (where precision matters; 
  please open a [Feature Requests](https://github.com/robstoll/atrium/issues/new?title=[Feature]) if you need them) 
- infinite `Iterable`s
- assertion functions for `Sequence` (you can use `asIterable` in the meantime)

# FAQ
So far there have not been frequently asked questions but you are invited to ask your question
in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ).
In case you do not have an account for kotlinlang.slack.com yet, then please [Invite yourself](http://slack.kotlinlang.org/). 

# Roadmap
I plan that Atrium will support in the future:
- Assertion functions for `Iterable` with nullable elements
- Generating testing reports in html
  - generate multiple reports in the same test run
  - extension for Spek so that reporting includes the `describe`, `it` etc. 
- Inclusion of mockito's verify (so that it appears in the report as well)
    
[Feature Requests](https://github.com/robstoll/atrium/issues/new?title=[Feature])
and are very welcome.

# License
Atrium is published under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).