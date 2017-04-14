[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlantWithCommonFields](.)

# IAssertionPlantWithCommonFields

`interface IAssertionPlantWithCommonFields<out T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlantWithCommonFields.kt#L11)

An assertion plant which has [CommonFields](-common-fields/index.md); provides the property [subject](subject.md) for ease of use.

### Types

| [CommonFields](-common-fields/index.md) | `data class CommonFields<out T>`<br>Common fields of an assertion plant. |

### Properties

| [commonFields](common-fields.md) | `abstract val commonFields: `[`CommonFields`](-common-fields/index.md)`<T>`<br>[CommonFields](-common-fields/index.md) of this plant. |
| [subject](subject.md) | `open val subject: T`<br>The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s. |

### Inheritors

| [IAssertionPlant](../-i-assertion-plant/index.md) | `interface IAssertionPlant<out T : Any> : IAssertionPlantWithCommonFields<T>`<br>Represents a plant for [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and offers the possibility to check all the added assertions. |
| [IAssertionPlantNullable](../-i-assertion-plant-nullable/index.md) | `interface IAssertionPlantNullable<out T> : IAssertionPlantWithCommonFields<T>`<br>Represents an assertion plant for nullable types. |

