[atrium](../../index.md) / [ch.tutteli.atrium.checking](../index.md) / [IAssertionChecker](index.md) / [check](.)

# check

`abstract fun check(assertionVerb: String, subject: Any, assertions: List<`[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`>): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/checking/IAssertionChecker.kt#L18)

Checks given [assertions](check.md#ch.tutteli.atrium.checking.IAssertionChecker$check(kotlin.String, kotlin.Any, kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) and reports if one of them fails (does not hold).

### Parameters

`assertionVerb` - The assertion verb which will be used in error reporting.

`subject` - The subject for which the [assertions](check.md#ch.tutteli.atrium.checking.IAssertionChecker$check(kotlin.String, kotlin.Any, kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) have been created.

`assertions` - The [assertions](check.md#ch.tutteli.atrium.checking.IAssertionChecker$check(kotlin.String, kotlin.Any, kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) which are checked.

### Exceptions

`AssertionError` - An implementation is allowed to throw [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if an assertion fails.