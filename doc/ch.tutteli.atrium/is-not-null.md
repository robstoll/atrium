[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [isNotNull](.)

# isNotNull

`inline fun <reified T : Any> `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T?>.isNotNull(): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L15)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) is not null.

**Return**
This plant to support a fluent-style API.

`inline fun <reified T : Any> `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T?>.isNotNull(noinline createAssertions: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L25)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) is not null and if so, uses [createAssertions](is-not-null.md#ch.tutteli.atrium$isNotNull(ch.tutteli.atrium.creating.IAssertionPlantNullable((ch.tutteli.atrium.isNotNull.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.isNotNull.T)), kotlin.Unit)))/createAssertions)
which could create further assertions which are lazily evaluated at the end.

**Return**
This plant to support a fluent-style API.

