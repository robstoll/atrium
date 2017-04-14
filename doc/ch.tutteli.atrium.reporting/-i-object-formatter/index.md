[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IObjectFormatter](.)

# IObjectFormatter

`interface IObjectFormatter` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IObjectFormatter.kt#L11)

Represents a formatter for objects.

Typically it formats [IAssertionPlant.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md)s and expected values of [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

### Functions

| [format](format.md) | `abstract fun format(any: Any?): String`<br>Returns a formatted version of the given [any](format.md#ch.tutteli.atrium.reporting.IObjectFormatter$format(kotlin.Any)/any). |

### Inheritors

| [ToStringObjectFormatter](../../ch.tutteli.atrium.test.reporting/-to-string-object-formatter/index.md) | `class ToStringObjectFormatter : IObjectFormatter` |

