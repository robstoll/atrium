[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlant](index.md) / [createAndAddAssertion](.)

# createAndAddAssertion

`abstract fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): `[`IAssertionPlant`](index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlant.kt#L26)

Creates an [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) based on [description](create-and-add-assertion.md#ch.tutteli.atrium.creating.IAssertionPlant$createAndAddAssertion(kotlin.String, kotlin.Any, kotlin.Function0((kotlin.Boolean)))/description), [expected](create-and-add-assertion.md#ch.tutteli.atrium.creating.IAssertionPlant$createAndAddAssertion(kotlin.String, kotlin.Any, kotlin.Function0((kotlin.Boolean)))/expected) and [test](create-and-add-assertion.md#ch.tutteli.atrium.creating.IAssertionPlant$createAndAddAssertion(kotlin.String, kotlin.Any, kotlin.Function0((kotlin.Boolean)))/test) and [adds](add-assertion.md) it
to the plant.

### Parameters

`description` - The description of the assertion, e.g., `is smaller than`.

`expected` - The expected value, e.g., `5`

`test` - Indicates whether the assertion holds or fails.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s are immediately evaluated.

**Return**
This plant to support a fluent-style API.

