[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [its](.)

# its

`fun <T : Any, TFeature : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.its(feature: KProperty0<TFeature>): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TFeature>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L58)

Creates an [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which immediately evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s using the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)))/feature) as
[subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md).

**Return**
An [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) for the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)))/feature), using an [AtriumFactory.newFeatureAssertionChecker](-atrium-factory/new-feature-assertion-checker.md).

**See Also**

[IAtriumFactory.newCheckImmediately](-i-atrium-factory/new-check-immediately.md)

`fun <T : Any, TFeature : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.its(feature: KProperty0<TFeature>, createAssertions: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TFeature>.() -> Unit): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<TFeature>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L76)

Creates an [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which lazily evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s using the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/feature) as
[subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md).

The given [createAssertions](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/createAssertions) function is called after the plant has been created. It could create
[IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s for the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/feature) which are lazily evaluated by the newly created [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)
after the call to [createAssertions](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/createAssertions) is made.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if an additionally created [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s
    (by calling [createAssertions](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/createAssertions)) does not hold.

**Return**
An [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) for the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.TFeature)), kotlin.Unit)))/feature), using an [AtriumFactory.newFeatureAssertionChecker](-atrium-factory/new-feature-assertion-checker.md).

**See Also**

[IAtriumFactory.newCheckLazily](-i-atrium-factory/new-check-lazily.md)

`fun <T : Any, TFeature> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.its(feature: KProperty0<TFeature>): `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<TFeature>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/narrowingAssertions.kt#L86)

Creates an [IAssertionPlantNullable](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md) using the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)))/feature) as [subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md).

**Return**
An [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) for the given [feature](its.md#ch.tutteli.atrium$its(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.its.T)), kotlin.reflect.KProperty0((ch.tutteli.atrium.its.TFeature)))/feature), using an [AtriumFactory.newNullable](-atrium-factory/new-nullable.md).

