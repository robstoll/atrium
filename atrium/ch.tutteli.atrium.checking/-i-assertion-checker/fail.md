[atrium](../../index.md) / [ch.tutteli.atrium.checking](../index.md) / [IAssertionChecker](index.md) / [fail](.)

# fail

`abstract fun fail(assertionVerb: String, subject: Any, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/checking/IAssertionChecker.kt#L30)

Reports that the given [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionChecker$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) fails (does not hold).

### Parameters

`assertionVerb` - The assertion verb which will be used in reporting.

`subject` - The subject for which the [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionChecker$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) has been created.

`assertion` - The [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionChecker$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) which fails.

### Exceptions

`AssertionError` - An implementation might throw [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html).

`IllegalArgumentException` - in case the [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionChecker$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) holds.