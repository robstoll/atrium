[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newDownCastBuilder](.)

# newDownCastBuilder

`inline fun <reified TSub : T, T : Any> newDownCastBuilder(description: String, commonFields: `[`CommonFields`](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T?>): `[`DownCastBuilder`](../../ch.tutteli.atrium.creating/-down-cast-builder/index.md)`<T, TSub>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L97)

Prepares a down cast; use [DownCastBuilder.cast](../../ch.tutteli.atrium.creating/-down-cast-builder/cast.md) to perform the down cast.

Call [DownCastBuilder.withLazyAssertions](../../ch.tutteli.atrium.creating/-down-cast-builder/with-lazy-assertions.md)/[DownCastBuilder.withNullRepresentation](../../ch.tutteli.atrium.creating/-down-cast-builder/with-null-representation.md) to specialise the down-cast.

### Parameters

`description` - The description of the down-cast.

`commonFields` - The commonFields which will be used to create a [DownCastBuilder](../../ch.tutteli.atrium.creating/-down-cast-builder/index.md).

**Return**
The newly created [DownCastBuilder](../../ch.tutteli.atrium.creating/-down-cast-builder/index.md).

**See Also**

[DownCastBuilder](../../ch.tutteli.atrium.creating/-down-cast-builder/index.md)

