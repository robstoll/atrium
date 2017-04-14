[atrium](../index.md) / [ch.tutteli.atrium.creating](.)

## Package ch.tutteli.atrium.creating

### Types

| [DownCastBuilder](-down-cast-builder/index.md) | `class DownCastBuilder<T : Any, TSub : T>`<br>A builder for creating a down-casting assertion. |
| [IAssertionPlant](-i-assertion-plant/index.md) | `interface IAssertionPlant<out T : Any> : `[`IAssertionPlantWithCommonFields`](-i-assertion-plant-with-common-fields/index.md)`<T>`<br>Represents a plant for [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and offers the possibility to check all the added assertions. |
| [IAssertionPlantNullable](-i-assertion-plant-nullable/index.md) | `interface IAssertionPlantNullable<out T> : `[`IAssertionPlantWithCommonFields`](-i-assertion-plant-with-common-fields/index.md)`<T>`<br>Represents an assertion plant for nullable types. |
| [IAssertionPlantWithCommonFields](-i-assertion-plant-with-common-fields/index.md) | `interface IAssertionPlantWithCommonFields<out T>`<br>An assertion plant which has [CommonFields](-i-assertion-plant-with-common-fields/-common-fields/index.md); provides the property [subject](-i-assertion-plant-with-common-fields/subject.md) for ease of use. |
| [ThrowableFluent](-throwable-fluent/index.md) | `class ThrowableFluent`<br>Provides [toThrow](-throwable-fluent/to-throw.md) methods for making assertions about a [Throwable](#)
which one expects was thrown. |

### Functions

| [createAssertionsAndCheckThem](create-assertions-and-check-them.md) | `fun <T : Any> `[`IAtriumFactory`](../ch.tutteli.atrium/-i-atrium-factory/index.md)`.createAssertionsAndCheckThem(plant: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>, createAssertions: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>`<br>Uses the given [plant](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/plant) as receiver of the given [createAssertions](create-assertions-and-check-them.md#ch.tutteli.atrium.creating$createAssertionsAndCheckThem(ch.tutteli.atrium.IAtriumFactory, ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.createAssertionsAndCheckThem.T)), kotlin.Unit)))/createAssertions) function and
then calls [IAssertionPlant.checkAssertions](-i-assertion-plant/check-assertions.md). |
| [newCheckLazilyAtTheEnd](new-check-lazily-at-the-end.md) | `fun <T : Any> `[`IAtriumFactory`](../ch.tutteli.atrium/-i-atrium-factory/index.md)`.newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, reporter: `[`IReporter`](../ch.tutteli.atrium.reporting/-i-reporter/index.md)`, createAssertions: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>`<br>Use this function to create a custom *assertion verb* which lazy evaluates assertions
(see [AtriumFactory.newCheckLazily](../ch.tutteli.atrium/-atrium-factory/new-check-lazily.md)). |

