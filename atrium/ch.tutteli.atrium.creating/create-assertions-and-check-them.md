[atrium](../index.md) / [ch.tutteli.atrium.creating](index.md) / [createAssertionsAndCheckThem](.)

# createAssertionsAndCheckThem

`inline fun <T : Any> `[`IAtriumFactory`](../ch.tutteli.atrium/-i-atrium-factory/index.md)`.createAssertionsAndCheckThem(plant: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>, createAssertions: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/creating/IAtriumFactoryExtensions.kt#L37)

Uses the given [plant](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/plant) as receiver of the given [createAssertions](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/createAssertions) function and
then calls [IAssertionPlant.checkAssertions](-i-assertion-plant/check-assertions.md).

### Exceptions

`AssertionError` - the [plant](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/plant) might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case a created [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) does not hold.

**Return**
The given [plant](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/plant).

