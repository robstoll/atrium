[atrium](../../index.md) / [ch.tutteli.atrium.test](../index.md) / [IAssertionVerbFactory](.)

# IAssertionVerbFactory

`interface IAssertionVerbFactory` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-test/src/main/kotlin/ch/tutteli/atrium/test/IAssertionVerbFactory.kt#L7)

### Functions

| [checkException](check-exception.md) | `abstract fun checkException(act: () -> Unit): `[`ThrowableFluent`](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md) |
| [checkImmediately](check-immediately.md) | `abstract fun <T : Any> checkImmediately(subject: T): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` |
| [checkLazily](check-lazily.md) | `abstract fun <T : Any> checkLazily(subject: T, createAssertions: `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` |
| [checkNullable](check-nullable.md) | `abstract fun <T> checkNullable(subject: T): `[`IAssertionPlantNullable`](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>` |

