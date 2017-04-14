[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlantNullable](.)

# IAssertionPlantNullable

`interface IAssertionPlantNullable<out T> : `[`IAssertionPlantWithCommonFields`](../-i-assertion-plant-with-common-fields/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlantNullable.kt#L14)

Represents an assertion plant for nullable types.

In contrast to a [IAssertionPlant](../-i-assertion-plant/index.md) it does not provide a method to create further [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s
([IAssertionPlant.createAndAddAssertion](../-i-assertion-plant/create-and-add-assertion.md)) nor a method to add assertions ([IAssertionPlant.addAssertion](../-i-assertion-plant/add-assertion.md))
and as consequence no method to check them ([IAssertionPlant.checkAssertions](../-i-assertion-plant/check-assertions.md)).
Yet, it provides one method [isNull](is-null.md) which immediately evaluates if the [subject](../-i-assertion-plant-with-common-fields/subject.md) is `null` as expected.

### Inherited Properties

| [commonFields](../-i-assertion-plant-with-common-fields/common-fields.md) | `abstract val commonFields: `[`CommonFields`](../-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T>`<br>[CommonFields](../-i-assertion-plant-with-common-fields/-common-fields/index.md) of this plant. |
| [subject](../-i-assertion-plant-with-common-fields/subject.md) | `open val subject: T`<br>The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |

### Functions

| [isNull](is-null.md) | `abstract fun isNull(): Unit`<br>Checks whether [subject](../-i-assertion-plant-with-common-fields/subject.md) is `null` and reports an error if it is not. |

### Extension Functions

| [isNotNull](../../ch.tutteli.atrium/is-not-null.md) | `fun <T : Any> IAssertionPlantNullable<T?>.isNotNull(): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<T>`<br>Makes the assertion that [IAssertionPlant.subject](../-i-assertion-plant-with-common-fields/subject.md) is not null.`fun <T : Any> IAssertionPlantNullable<T?>.isNotNull(createAssertions: `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<T>`<br>Makes the assertion that [IAssertionPlant.subject](../-i-assertion-plant-with-common-fields/subject.md) is not null and if so, uses [createAssertions](../../ch.tutteli.atrium/is-not-null.md#ch.tutteli.atrium$isNotNull(ch.tutteli.atrium.creating.IAssertionPlantNullable((ch.tutteli.atrium.isNotNull.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.isNotNull.T)), kotlin.Unit)))/createAssertions)
which could create further assertions which are lazily evaluated at the end. |

