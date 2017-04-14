[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlant](index.md) / [and](.)

# and

`open val and: `[`IAssertionPlant`](index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlant.kt#L59)

Can be used to separate assertions when using the fluent-style API.

For instance, `assert(1).isSmallerThan(2).and.isGreaterThan(0)`

**Return**
This plant to support a fluent-style API.

