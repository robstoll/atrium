[atrium](../../../index.md) / [ch.tutteli.atrium.creating](../../index.md) / [IAssertionPlantWithCommonFields](../index.md) / [CommonFields](.)

# CommonFields

`data class CommonFields<out T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlantWithCommonFields.kt#L35)

Common fields of an assertion plant.

### Constructors

| [&lt;init&gt;](-init-.md) | `CommonFields(assertionVerb: String, subject: T, assertionChecker: `[`IAssertionChecker`](../../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`)` |

### Properties

| [assertionChecker](assertion-checker.md) | `val assertionChecker: `[`IAssertionChecker`](../../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)<br>The checker which will be used to check [IAssertion](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |
| [assertionVerb](assertion-verb.md) | `val assertionVerb: String`<br>The assertion verb which will be used inter alia in error reporting. |
| [subject](subject.md) | `val subject: T`<br>The subject for which this plant will create/check [IAssertion](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |

### Functions

| [check](check.md) | `fun check(assertions: List<`[`IAssertion`](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`>): Unit`<br>Uses [assertionChecker](assertion-checker.md) to check the given [assertions](check.md#ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields$check(kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) (see [IAssertionChecker.check](../../../ch.tutteli.atrium.checking/-i-assertion-checker/check.md)). |
| [fail](fail.md) | `fun fail(assertion: `[`IAssertion`](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit`<br>Uses [assertionChecker](assertion-checker.md) to report a failing [assertion](fail.md#ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields$fail(ch.tutteli.atrium.assertions.IAssertion)/assertion) (see [IAssertionChecker.fail](../../../ch.tutteli.atrium.checking/-i-assertion-checker/fail.md)). |

