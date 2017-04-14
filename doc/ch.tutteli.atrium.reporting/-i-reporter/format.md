[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IReporter](index.md) / [format](.)

# format

`abstract fun format(sb: `[`StringBuilder`](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html)`, assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IReporter.kt#L20)

Reports about the given [assertion](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/assertion), using the given [sb](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/sb) where the actual
implementation will decide whether the given [assertion](format.md#ch.tutteli.atrium.reporting.IReporter$format(java.lang.StringBuilder, ch.tutteli.atrium.assertions.IAssertion)/assertion) is noteworthy to be reported.

For instance, [IAtriumFactory.newOnlyFailureReporter](../../ch.tutteli.atrium/-i-atrium-factory/new-only-failure-reporter.md) will only report failing [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

### Parameters

`sb` - The [StringBuilder](http://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html) which can be used for reporting.

`assertion` - The assertion which should be considered for reporting.