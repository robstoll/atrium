[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [isA](.)

# isA

`inline fun <reified TSub : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<Any>.isA(): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TSub>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L35)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) *is a* [TSub](#) (the same type or a sub-type).

**Return**
This plant to support a fluent-style API.

`inline fun <reified TSub : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<Any>.isA(noinline createAssertions: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TSub>.() -> Unit): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TSub>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L45)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) *is a* [TSub](#) (the same type or a sub-type) and if so,
uses [createAssertions](is-a.md#ch.tutteli.atrium$isA(ch.tutteli.atrium.creating.IAssertionPlant((kotlin.Any)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.isA.TSub)), kotlin.Unit)))/createAssertions) which could create further assertions which are lazily evaluated at the end.

**Return**
This plant to support a fluent-style API.

