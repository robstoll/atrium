[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IAssertionMessageFormatter](index.md) / [format](.)

# format

`abstract fun format(sb: `[`StringBuilder`](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html)`, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`, assertionFilter: (`[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`) -> Boolean, messageFilter: (`[`Message`](../../ch.tutteli.atrium.assertions/-message/index.md)`) -> Boolean): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IAssertionMessageFormatter.kt#L22)

Formats the given [assertion](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/assertion) and appends the result to the given [sb](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/sb).

One can define an [assertionFilter](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/assertionFilter) and a [messageFilter](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/messageFilter) to filter out [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s, [Message](../../ch.tutteli.atrium.assertions/-message/index.md)s respectively
(for instance, filter out assertions which hold --&gt; see [IAtriumFactory.newOnlyFailureReporter](../../ch.tutteli.atrium/-i-atrium-factory/new-only-failure-reporter.md)).

### Parameters

`sb` - The [StringBuilder](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html) to which the formatted [assertion](format.md#ch.tutteli.atrium.reporting.IAssertionMessageFormatter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion, kotlin.Function1((ch.tutteli.atrium.assertions.IAssertion, kotlin.Boolean)), kotlin.Function1((ch.tutteli.atrium.assertions.Message, kotlin.Boolean)))/assertion) will be appended.

`assertion` - The assertion which should be formatted

`assertionFilter` - Can be used used to filter out [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s which should not be formatted.

`messageFilter` - Can be used to filter out [Message](../../ch.tutteli.atrium.assertions/-message/index.md)s which should not be formatted.