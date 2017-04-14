[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newOnlyFailureReporter](.)

# newOnlyFailureReporter

`fun newOnlyFailureReporter(assertionMessageFormatter: `[`IAssertionMessageFormatter`](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md)`): `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L57)

Overrides [IAtriumFactory.newOnlyFailureReporter](../-i-atrium-factory/new-only-failure-reporter.md)

Creates an [IReporter](../../ch.tutteli.atrium.reporting/-i-reporter/index.md) which reports only failing assertions
and uses the given [assertionMessageFormatter](new-only-failure-reporter.md#ch.tutteli.atrium.AtriumFactory$newOnlyFailureReporter(ch.tutteli.atrium.reporting.IAssertionMessageFormatter)/assertionMessageFormatter) to format assertions and messages.

### Parameters

`assertionMessageFormatter` - The formatter which is used to format [IAssertion](#)s.

**Return**
The newly created reporter.

