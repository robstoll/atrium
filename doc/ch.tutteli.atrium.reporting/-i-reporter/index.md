[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IReporter](.)

# IReporter

`interface IReporter` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IReporter.kt#L9)

Represents a reporter which reports about [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

### Functions

| [format](format.md) | `abstract fun format(sb: `[`StringBuilder`](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html)`, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit`<br>Reports about the given [assertion](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/assertion), using the given [sb](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/sb) where the actual
implementation will decide whether the given [assertion](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/assertion) is noteworthy to be reported. |

