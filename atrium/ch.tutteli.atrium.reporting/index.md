[atrium](../index.md) / [ch.tutteli.atrium.reporting](.)

## Package ch.tutteli.atrium.reporting

### Types

| [IAssertionMessageFormatter](-i-assertion-message-formatter/index.md) | `interface IAssertionMessageFormatter`<br>Represents a formatter for an [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) and its [Message](../ch.tutteli.atrium.assertions/-message/index.md)s. |
| [IObjectFormatter](-i-object-formatter/index.md) | `interface IObjectFormatter`<br>Represents a formatter for objects. |
| [IReporter](-i-reporter/index.md) | `interface IReporter`<br>Represents a reporter which reports about [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |
| [RawString](-raw-string/index.md) | `data class RawString`<br>Use this class to represent a [String](#) which should be treated as raw [String](#) in reporting. |
| [ReporterBuilder](-reporter-builder/index.md) | `class ReporterBuilder`<br>A builder to create an [IReporter](-i-reporter/index.md) consisting of an [IObjectFormatter](-i-object-formatter/index.md) which is used by an
[IAssertionMessageFormatter](-i-assertion-message-formatter/index.md) which in turn is used by the created [IReporter](-i-reporter/index.md). |

