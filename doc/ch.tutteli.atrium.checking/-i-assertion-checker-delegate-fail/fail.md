[atrium](../../index.md) / [ch.tutteli.atrium.checking](../index.md) / [IAssertionCheckerDelegateFail](index.md) / [fail](.)

# fail

`open fun fail(assertionVerb: String, subject: Any, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/checking/IAssertionCheckerDelegateFail.kt#L15)

Overrides [IAssertionChecker.fail](../-i-assertion-checker/fail.md)

Delegates to [check](../-i-assertion-checker/check.md) if the assertion fails.

### Exceptions

`IllegalArgumentException` - in case the given [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionCheckerDelegateFail$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) holds.