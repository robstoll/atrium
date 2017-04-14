[atrium](../index.md) / [ch.tutteli.atrium.verbs.assert](index.md) / [assert](.)

# assert

`fun <T : Any> assert(subject: T): <ERROR CLASS>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-verbs/src/main/kotlin/ch/tutteli/atrium/verbs/assert/Assert.kt#L18)

Creates an [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) for [subject](assert.md#ch.tutteli.atrium.verbs.assert$assert(ch.tutteli.atrium.verbs.assert.assert.T)/subject) which immediately evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

**Return**
The newly created plant.

**See Also**

[AtriumFactory.newCheckImmediately](../ch.tutteli.atrium/-atrium-factory/new-check-immediately.md)

`fun <T> assert(subject: T): <ERROR CLASS>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-verbs/src/main/kotlin/ch/tutteli/atrium/verbs/assert/Assert.kt#L28)

Creates an [IAssertionPlantNullable](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md) for [subject](assert.md#ch.tutteli.atrium.verbs.assert$assert(ch.tutteli.atrium.verbs.assert.assert.T)/subject).

**Return**
The newly created plant.

**See Also**

[AtriumFactory.newNullable](../ch.tutteli.atrium/-atrium-factory/new-nullable.md)

`inline fun <T : Any> assert(subject: T, createAssertions: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-verbs/src/main/kotlin/ch/tutteli/atrium/verbs/assert/Assert.kt#L38)

Creates an [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) for [subject](assert.md#ch.tutteli.atrium.verbs.assert$assert(ch.tutteli.atrium.verbs.assert.assert.T, kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.verbs.assert.assert.T)), kotlin.Unit)))/subject) which lazily evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

**Return**
The newly created plant.

**See Also**

[newCheckLazilyAtTheEnd](../ch.tutteli.atrium.creating/new-check-lazily-at-the-end.md)

`fun assert(act: () -> Unit): `[`ThrowableFluent`](../ch.tutteli.atrium.creating/-throwable-fluent/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-verbs/src/main/kotlin/ch/tutteli/atrium/verbs/assert/Assert.kt#L46)

Creates an [ThrowableFluent](../ch.tutteli.atrium.creating/-throwable-fluent/index.md) for the given function [act](assert.md#ch.tutteli.atrium.verbs.assert$assert(kotlin.Function0((kotlin.Unit)))/act).

**Return**
The newly created [ThrowableFluent](../ch.tutteli.atrium.creating/-throwable-fluent/index.md).

