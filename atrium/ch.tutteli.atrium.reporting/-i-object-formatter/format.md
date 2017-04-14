[atrium](../../index.md) / [ch.tutteli.atrium.reporting](../index.md) / [IObjectFormatter](index.md) / [format](.)

# format

`abstract fun format(any: Any?): String` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/reporting/IObjectFormatter.kt#L20)

Returns a formatted version of the given [any](format.md#ch.tutteli.atrium.reporting.IObjectFormatter$format(kotlin.Any)/any).

It will return [RawString.string](../-raw-string/string.md) in case [any](format.md#ch.tutteli.atrium.reporting.IObjectFormatter$format(kotlin.Any)/any) is a [RawString](../-raw-string/index.md).

**Return**
The formatted [any](format.md#ch.tutteli.atrium.reporting.IObjectFormatter$format(kotlin.Any)/any).

