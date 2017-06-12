[![Apache license](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](http://opensource.org/licenses/Apache2.0)
[![Build Status](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium)
[![Coverage](https://codecov.io/github/robstoll/atrium/coverage.svg?branch=master)](https://codecov.io/github/robstoll/atrium?branch=master)

# Atrium
Atrium is an open-source assertion framework for Kotlin with a fluent API and supports different styles 
(immediate vs lazy evaluation) which can be mixed and matched freely together (see [Examples](#examples) below).

**Table of Content**
- [Installation](#installation)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
- [Examples](#examples)
  - [Immediate vs. Lazy Evaluation](#immediate-vs-lazy-evaluation)
  - [Nullable Variables](#nullable-variables)
  - [Expect an Exception](#expect-an-exception)
  - [Property Assertions](#property-assertions)
  - [Method Assertions](#method-assertions)
- [Write own Assertion Functions](#write-own-assertion-functions)
- [Contribute](#contribute)
- [KDoc - Code Documentation](#kdoc)
- [Known Limitations](#known-limitations)
- [Roadmap](#roadmap)
- [License](#license)

# Installation
Atrium can be retrieved from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium).

gradle: 
```
buildscript {
    ext { atrium_version='0.2.0' }
}

dependencies {
    testCompile "ch.tutteli:atrium-api:$atrium_version"
    testCompile "ch.tutteli:atrium-impl-robstoll:$atrium_version"
    testCompile "ch.tutteli:atrium-assertions:$atrium_version"
}
```

maven:
```
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-api</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-impl-robstoll</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-assertions</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
```

# Use own Assertion Verbs

Atrium offers three assertion verbs for the impatient: `assert`, `assertThat` and `expect`. 
However, I suggest that you **use your own assertion verbs** even in the case you name them
`assert`, `assertThat` or `expect`. The benefit will be that you are able to change the
reporting style in the future without modifying existing code. 
For instance, you could change from same-line to multi-line reporting or 
report not only failing but also successful assertions etc.

In order to create an own assertion verb it suffices to copy the file content of 
[atriumVerbs.kt](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
paste it in your own atriumVerbs.kt, rename `assert` and `expect` as desired and rename the package to reflect yours.

As you can see, it is up to you if you use the same name for all assertion functions or not 
(Atrium itself uses `expect` to postulate assertions about thrown exceptions and `assert` for other assertions).

## Out of the Box Assertion Verbs
If you still insist of using the provided assertion verbs, then add the following dependencies to your project.

gradle:
```
buildscript {
    ext { atrium_version='0.2.0' }
}

dependencies {
    testCompile "ch.tutteli:atrium-api:$atrium_version"
    testCompile "ch.tutteli:atrium-impl-robstoll:$atrium_version"
    testCompile "ch.tutteli:atrium-assertions:$atrium_version"
    testCompile "ch.tutteli:atrium-verbs:$atrium_version"
}
```

maven:
```
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-api</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-impl-robstoll</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-assertions</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>ch.tutteli</groupId>
  <artifactId>atrium-verbs</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
```

# Examples

## Immediate vs. Lazy Evaluation

```kotlin
 // immediate evaluation
assert(1).isLessThan(5).isGreaterThan(10)
    // assert: 5        (java.lang.Integer<199640888>)
    // is less than: 5        (java.lang.Integer<199640888>)

// lazy evaluation
assert(10) {
    isLessThan(5)
    isGreaterThan(0)
}
    // assert: 5        (java.lang.Integer<199640888>)
    // is less than: 5        (java.lang.Integer<199640888>)
    // is greater than: 5        (java.lang.Integer<199640888>)
```

Immediate evaluation throws an `AssertionError` as soon as an assertion is not correct 
-- in the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated. 
In contrast to lazy evaluation which throws an `AssertionError` at the end of the block 
and hence reports that both assertions do not hold.
 
## Nullable Variables
```kotlin
val subtitle : String? = "postulating assertions made easy"
assert(subtitle).isNull()
    // assert: "postulating assertions made easy"        <1574749319>
    // to be: null

assert(subtitle).isNotNull().and.contains("atrium")
    // assert: "postulating assertions made easy"        <1574749319>
    // contains: "atrium"        <225909961>
```
In case a variable has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html) then 
you need to define first, whether you expect it to be `null` or not. As you can see, you can optionally 
use `and` to separate assertions in the [immediate evaluation](#immediate-vs-lazy-evaluation) style.
 
## Expect an Exception
```kotlin
expect {
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException> {
    message.contains("name")
}
    // expect the thrown exception: IllegalStateException (java.lang.IllegalStateException)
    // is a: IllegalArgumentException (java.lang.IllegalArgumentException)
```
The method `toThrow` allows to make the assertion that a given lambda throws a certain exception 
(`IllegalStateException` in the example above). In more detail, the function `expect` 
(part of the 4 [assertion verbs](#use-own-assertion-verbs)) wraps the lambda into a ThrowableFluent and 
ThrowableFluent provides the method `toThrow`.
Notice `message` in the [lazy block](#immediate-vs-lazy-evaluation). It is a shortcut for 
`its(subject::message).isNotNull()`, which prepares a feature assertion about `Throwable::message`.
 
## Property Assertions
```kotlin
data class Person(val name: String, val isStudent: Boolean)
val person = Person("Robert", false) 
assert(person) {
    property(subject::isStudent).isTrue()
    its(subject::name).toBe("Peter")
}
    // assert: Person(isStudent=false)
    // -> isStudent: false
    //    to be: true
    // -> name: "Robert"        <1021436681>
    //    to be: "Peter"        <1790585734>
```
There are two assertion functions which allow to make assertions about a particular property or feature 
of a class: `property` and `its`. I recommend to use `property` for properties of type `Boolean` and 
`its` for the rest. `subject` inside a [lazy block](#immediate-vs-lazy-evaluation) refers to the subject
 of the assertion (`person` in the example above).

The only drawback IMO of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it 
(see `message` in [Expect an Exception](#expect-an-exception) for instance). Yet, I do not recommend to 
write an own assertion function for every single property because renaming this property then should include 
renaming the assertion function. Hence, only write an own assertion function in case you use it a lot.

As side notice, the function `its` is mainly an alternative for `property` and was introduced to allow 
a more natural flow in expressing assertions about particular features of a class. 
I could have introduce `his`, `her`, `their` as well but decided against it in order that 
you do not have to think to about gender or whe singular or plural.

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
    returnValueOf(subject::name) { contains("treboR", "llotS") }
    returnValueOf(subject::nickname, false).toBe("Robert aka. Stoll")
}
    // Person(firstName=Robert, lastName=Stoll)        (ch.tutteli.atrium.A$1$2$Person <360936478>)
    // -> name(): "Robert Stoll"        <1209770703>
    //    contains: "treboR"        <1186339926>
    //    contains: "llotS"        <776484396>
    // -> nickname(false): "Mr. Robert"        <519979933>
    //    to be: "Robert aka. Stoll"        <199657303>
```

Feature assertions about the return value of a method call on the subject of the assertion can be made 
with the help of `returnValueOf`. There are overloads for up to 5 parameters. You could go on and create 
further overloads for 6 and more parameters but I suggest that you 
[write a specific assertion function](#write-own-assertion-functions) for such a use case instead.
    

Have a look at the [specifications](https://github.com/robstoll/atrium/tree/master/atrium-spec/src/main/kotlin/ch/tutteli/atrium/spec) for more examples.

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic functions 
[property](#property-assertions) and [returnValueOf](#method-assertions) 
are not good enough?

Writing one is very simple and a pull request of your new assertion function is welcome.
Following an example:

```kotlin
fun IAssertionPlant<Int>.isEven(expected: Int)
    = createAndAddAssertion(AssertionDescriptions.IS_EVEN, expected, { subject % 2 == 0 })
    
enum class AssertionDescriptions(override val value: String) : ISimpleTranslatable {
    IS_EVEN("is even"),
}    
```

# KDoc
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://robstoll.github.io/atrium/)

# Contribute
You are very welcome to contribute:
- [open an issue or create a feature request](https://github.com/robstoll/atrium/issues/new)
- fork the repository and make a pull request

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out of the box assertion functions. More functions will follow 
but only if they are used somewhere by someone.
 
Some assertion functions which I miss myself and will follow in the next version are listed in 
the [Roadmap](#roadmap).

# Roadmap
I plan that Atrium will support in the future:
- Assertion functions for Collections: contains, in order vs. any order etc.
- Generating Testing Reports in html 
- Inclusion of mockito's verify (so that it appears in the report as well)
    
Feature Requests are very welcome.

# License
Atrium is published under [Apache 2.0](http://opensource.org/licenses/Apache2.0). 