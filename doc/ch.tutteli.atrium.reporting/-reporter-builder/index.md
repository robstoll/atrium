[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [ReporterBuilder](.)

# ReporterBuilder

`class ReporterBuilder` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/reporting/ReporterBuilder.kt#L9)

A builder to create an [IReporter](../-i-reporter/index.md) consisting of an [IObjectFormatter](../-i-object-formatter/index.md) which is used by an
[IAssertionMessageFormatter](../-i-assertion-message-formatter/index.md) which in turn is used by the created [IReporter](../-i-reporter/index.md).

### Types

| [AssertionMessageBuilder](-assertion-message-builder/index.md) | `class AssertionMessageBuilder` |

### Constructors

| [&lt;init&gt;](-init-.md) | `ReporterBuilder(assertionMessageFormatter: `[`IAssertionMessageFormatter`](../-i-assertion-message-formatter/index.md)`)`<br>A builder to create an [IReporter](../-i-reporter/index.md) consisting of an [IObjectFormatter](../-i-object-formatter/index.md) which is used by an
[IAssertionMessageFormatter](../-i-assertion-message-formatter/index.md) which in turn is used by the created [IReporter](../-i-reporter/index.md). |

### Functions

| [buildOnlyFailureReporting](build-only-failure-reporting.md) | `fun buildOnlyFailureReporting(): `[`IReporter`](../-i-reporter/index.md)<br>Uses [AtriumFactory.newOnlyFailureReporter](../../ch.tutteli.atrium/-atrium-factory/new-only-failure-reporter.md) as [IReporter](../-i-reporter/index.md). |

### Companion Object Functions

| [withDetailedObjectFormatter](with-detailed-object-formatter.md) | `fun withDetailedObjectFormatter(): `[`AssertionMessageBuilder`](-assertion-message-builder/index.md)<br>Uses [AtriumFactory.newDetailedObjectFormatter](../../ch.tutteli.atrium/-atrium-factory/new-detailed-object-formatter.md) as [IObjectFormatter](../-i-object-formatter/index.md). |

