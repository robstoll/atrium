[atrium](../../index.md) / [ch.tutteli.atrium.checking](../index.md) / [IAssertionChecker](.)

# IAssertionChecker

`interface IAssertionChecker` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/checking/IAssertionChecker.kt#L8)

Checks given [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and reports if one of them fails.

### Functions

| [check](check.md) | `abstract fun check(assertionVerb: String, subject: Any, assertions: List<`[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`>): Unit`<br>Checks given [assertions](check.md#ch.tutteli.atrium.checking.IAssertionChecker$check(kotlin.String, kotlin.Any, kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) and reports if one of them fails (does not hold). |
| [fail](fail.md) | `abstract fun fail(assertionVerb: String, subject: Any, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit`<br>Reports that the given [assertion](fail.md#ch.tutteli.atrium.checking.IAssertionChecker$fail(kotlin.String, kotlin.Any, ch.tutteli.atrium.assertions.IAssertion)/assertion) fails (does not hold). |

### Inheritors

| [IAssertionCheckerDelegateFail](../-i-assertion-checker-delegate-fail/index.md) | `interface IAssertionCheckerDelegateFail : IAssertionChecker`<br>Provides a default-implementation for [IAssertionChecker.fail](fail.md) which first checks
that the given [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) fails and then delegates to [IAssertionChecker.check](check.md). |

