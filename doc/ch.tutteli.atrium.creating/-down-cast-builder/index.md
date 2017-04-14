[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [DownCastBuilder](.)

# DownCastBuilder

`class DownCastBuilder<T : Any, TSub : T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/DownCastBuilder.kt#L32)

A builder for creating a down-casting assertion.

Or in other words, helps to make an assertion about [IAssertionPlant.subject](../-i-assertion-plant-with-common-fields/subject.md) that it can be
down-casted to [subClass](#).

### Constructors

| [&lt;init&gt;](-init-.md) | `DownCastBuilder(description: String, subClass: KClass<TSub>, commonFields: `[`CommonFields`](../-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T?>)` |

### Functions

| [cast](cast.md) | `fun cast(): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TSub>`<br>Performs the down-cast if possible; reports a failure otherwise. |
| [withLazyAssertions](with-lazy-assertions.md) | `fun withLazyAssertions(createAssertions: `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TSub>.() -> Unit): DownCastBuilder<T, TSub>`<br>Use this method if you want to add several assertions which are checked lazily after the down cast is performed. |
| [withNullRepresentation](with-null-representation.md) | `fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub>`<br>Use this method if you want to use your own `null` representation in error reporting
(default is [RawString.NULL](../../ch.tutteli.atrium.reporting/-raw-string/-n-u-l-l.md)). |

