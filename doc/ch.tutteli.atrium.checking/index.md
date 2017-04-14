[atrium](../index.md) / [ch.tutteli.atrium.checking](.)

## Package ch.tutteli.atrium.checking

### Types

| [IAssertionChecker](-i-assertion-checker/index.md) | `interface IAssertionChecker`<br>Checks given [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and reports if one of them fails. |
| [IAssertionCheckerDelegateFail](-i-assertion-checker-delegate-fail/index.md) | `interface IAssertionCheckerDelegateFail : `[`IAssertionChecker`](-i-assertion-checker/index.md)<br>Provides a default-implementation for [IAssertionChecker.fail](-i-assertion-checker/fail.md) which first checks
that the given [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) fails and then delegates to [IAssertionChecker.check](-i-assertion-checker/check.md). |

