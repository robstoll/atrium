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

## Empty CharSequence

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