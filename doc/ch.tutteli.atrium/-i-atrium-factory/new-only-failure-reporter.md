[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newOnlyFailureReporter](.)

# newOnlyFailureReporter

`abstract fun newOnlyFailureReporter(assertionMessageFormatter: `[`IAssertionMessageFormatter`](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md)`): `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L181)

Creates an [IReporter](../../ch.tutteli.atrium.reporting/-i-reporter/index.md) which reports only failing assertions
and uses the given [assertionMessageFormatter](new-only-failure-reporter.md#ch.tutteli.atrium.IAtriumFactory$newOnlyFailureReporter(ch.tutteli.atrium.reporting.IAssertionMessageFormatter)/assertionMessageFormatter) to format assertions and messages.

### Parameters

`assertionMessageFormatter` - The formatter which is used to format [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

**Return**
The newly created reporter.

