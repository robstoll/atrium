[![Apache license](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](http://opensource.org/licenses/Apache2.0)
[![Build Status](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium)
[![Coverage](https://codecov.io/github/robstoll/atrium/coverage.svg?branch=master)](https://codecov.io/github/robstoll/atrium?branch=master)

# Atrium
Atrium is an open-source assertion framework for Kotlin with a fluent API and supports different styles 
(immediate vs lazy evaluation) which can be mixed and matched freely (see [Examples](#examples) below).

**Table of Content**
- [Installation](#installation)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
- [Examples](#examples)
- [Write own Assertion Functions](#write-own-assertion-functions)
- [Reusing an Existing Property as Assertion](#reusing-an-existing-property-as-assertion)
- [Reusing an Existing Method as Assertion](#reusing-an-existing-method-as-assertion)
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

Atrium_offers three assertion verbs for the impatient: `assert`, `assertThat` and `expect`. 
However, I suggest that you **use your own assertion verbs** even in the case you name them
`assert`, `assertThat` or `expect`. The benefit will be that you are able to change the 
reporting style in the future without modifying existing code. 
For instance, you could change from same-line to multi-line reporting or 
report not only failing but also successful assertions etc.

In order to create an own assertion verb it suffices to copy the file content of 
[atriumVerbs.kt](./tree/master/atrium-impl-robstoll/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
paste it in your own atriumVerbs.kt, rename `assert` and `expect` as desired and rename the package to reflect yours.

As you can see, it is up to you if you use the same name for all assertions or not 
(Atrium itself uses `expect` to postulate assertions about thrown exceptions and `assert` for other assertions).

## Out of the Box Assertion Verbs
If you still insist of using the provided assertion verbs, then add the following dependency to your project.

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

```kotlin
 // immediate evaluation
assert(1).isSmallerThan(5).isGreaterThan(10)
    // assert: 5        (java.lang.Integer<199640888>)
    // is smaller than: 5        (java.lang.Integer<199640888>)

// lazy evaluation
assert(10) {
    isSmallerThan(5)
    isGreaterThan(0)
}
    // assert: 5        (java.lang.Integer<199640888>)
    // is smaller than: 5        (java.lang.Integer<199640888>)
    // is greater than: 5        (java.lang.Integer<199640888>)
    
    
// feature evaluation
data class Person(val firstName: String, val lastName: String)
val person : Person?  = Person("Robert", "Stoll") 
assert(person).isNotNull {
    its(subject::firstName).toBe("treboR")
    its(subject::lastName).toBe("llotS")
}
    // assert: Person(firstName=Robert, lastName=Stoll)        (ch.tutteli.atrium.FeatureSpec$1$1$3$Person<893192050>)
    // -> firstName: "Robert"        <644345897>
    //    to be: "treboR"        <1738674023>
    // -> lastName: "Stoll"        <1472682156>
    //    to be: "llotS"        <178049969>


// exception checking
expect {
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException> {
    message.contains("name")
}
    // expect the thrown exception: IllegalStateException (java.lang.IllegalStateException)
    // is a: IllegalArgumentException (java.lang.IllegalArgumentException)
```

Have a look at the [specifications](./tree/master/atrium-spec/src/main/kotlin/ch/tutteli/atrium/spec) for more examples.

# Write own Assertion Functions

Are you missing an assertion function for a specific type? 
Writing one is very simple and a pull request of your new assertion function is welcome.
Following an example:

```kotlin
fun IAssertionPlant<Int>.isEven(expected: Int)
    = createAndAddAssertion("is even", expected, { subject % 2 == 0 })
```

# Reusing an Existing Property as Assertion
A `Boolean` property can be reused as assertion without the need to write 
an assertion function for it. Following an example:

```kotlin
data class Person(val isSingle:Boolean)
assert(Person(false)){
    genericCheck(subject::isSingle)

}
    // assert: Person(isSingle=false)
    // generic check isSingle: true
``` 

The only drawback IMO of using an existing property is that the error message is not as nice as 
when you would [write your own assertion functions](#write-own-assertion-functions).

# Reusing an Existing Method as Assertion

Due to [a bug in Kotlin 1.1.x](https://youtrack.jetbrains.com/oauth?state=%2Fissue%2FKT-16695) 
I am currently not able to offer a `genericCheck` for methods. 
As soon as the bug is fixed, I will provide one in the similar style as for properties. Its usage will look as follows:
 
```kotlin
assert("hello") {
    genericCheck(subject::isEmpty)
}
    // assert: "hello"   <441001942>
    // generic check isEmpty: false
```

# KDoc
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://robstoll.github.io/atrium/)

# Contribute
You are very welcome to contribute:
- [open an issue or create a feature request](issues/new)
- [fork the repository](#fork-destination-box) and make a pull request

# Known Limitations
As mentioned above, due to [a bug in Kotlin 1.1.x](https://youtrack.jetbrains.com/oauth?state=%2Fissue%2FKT-16695) 
I am currently not able to offer a `genericCheck` for methods nor _feature evaluation_ (`its` see [Examples](#examples) above)
for methods as it is possible for properties.

# Roadmap
I plan that Atrium will support in the future:
- Generating Testing Reports
    - Support multi-language assertions, e.g. output report in German
    
Feature Requests are very welcome.    

# License
Atrium is published under [Apache 2.0](http://opensource.org/licenses/Apache2.0). 