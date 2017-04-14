[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [DownCastBuilder](index.md) / [withLazyAssertions](.)

# withLazyAssertions

`fun withLazyAssertions(createAssertions: `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TSub>.() -> Unit): `[`DownCastBuilder`](index.md)`<T, TSub>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/DownCastBuilder.kt#L66)

Use this method if you want to add several assertions which are checked lazily after the down cast is performed.

Or in other words, the given [createAssertions](with-lazy-assertions.md#ch.tutteli.atrium.creating.DownCastBuilder$withLazyAssertions(kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.DownCastBuilder.TSub)), kotlin.Unit)))/createAssertions) function will be called,
which might add additional [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s to the newly created [IAssertionPlant](../-i-assertion-plant/index.md), which are then lazily checked
in [cast](cast.md) after the down-cast was performed and before the newly created [IAssertionPlant](../-i-assertion-plant/index.md) is returned.

**Return**
This builder to support a fluent-style API.

