[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IAssertionMessageFormatter](.)

# IAssertionMessageFormatter

`interface IAssertionMessageFormatter` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IAssertionMessageFormatter.kt#L10)

Represents a formatter for an [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) and its [Message](../../ch.tutteli.atrium.assertions/-message/index.md)s.

### Functions

| [format](format.md) | `abstract fun format(sb: `[`StringBuilder`](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html)`, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`, assertionFilter: (`[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`) -> Boolean, messageFilter: (`[`Message`](../../ch.tutteli.atrium.assertions/-message/index.md)`) -> Boolean): Unit`<br>Formats the given [assertion](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/assertion) and appends the result to the given [sb](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/sb). |

