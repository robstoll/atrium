[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlant](index.md) / [addAssertion](.)

# addAssertion

`abstract fun addAssertion(assertion: `[`IAssertion`](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): `[`IAssertionPlant`](index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlant.kt#L37)

Adds the given [assertion](add-assertion.md#ch.tutteli.atrium.creating.IAssertionPlant$addAssertion(ch.tutteli.atrium.assertions.IAssertion)/assertion) to the plant.

### Parameters

`assertion` - The assertion which will be added to this plant.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s are immediately evaluated.

**Return**
This plant to support a fluent-style API.

