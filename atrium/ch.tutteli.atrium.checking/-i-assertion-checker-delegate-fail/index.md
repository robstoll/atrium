[atrium](../../index.md) / [ch.tutteli.atrium.checking](../index.md) / [IAssertionCheckerDelegateFail](.)

# IAssertionCheckerDelegateFail

`interface IAssertionCheckerDelegateFail : `[`IAssertionChecker`](../-i-assertion-checker/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/checking/IAssertionCheckerDelegateFail.kt#L9)

Provides a default-implementation for [IAssertionChecker.fail](../-i-assertion-checker/fail.md) which first checks
that the given [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) fails and then delegates to [IAssertionChecker.check](../-i-assertion-checker/check.md).

### Functions

| [fail](fail.md) | `open fun fail(assertionVerb: String, subject: Any, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit`<br>Delegates to [check](../-i-assertion-checker/check.md) if the assertion fails. |

### Inherited Functions

| [check](../-i-assertion-checker/check.md) | `abstract fun check(assertionVerb: String, subject: Any, assertions: List<`[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`>): Unit`<br>Checks given [assertions](../-i-assertion-checker/check.md#ch.tutteli.atrium.checking.IAssertionChecker$check(kotlin.String, kotlin.Any, kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) and reports if one of them fails (does not hold). |

