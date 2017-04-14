[atrium](../index.md) / [ch.tutteli.atrium.test](.)

## Package ch.tutteli.atrium.test

### Types

| [IAssertionVerbFactory](-i-assertion-verb-factory/index.md) | `interface IAssertionVerbFactory` |

### Functions

| [check](check.md) | `fun <ERROR CLASS>.check(description: String, body: <ERROR CLASS>.() -> Unit): <ERROR CLASS>` |
| [checkGenericNarrowingAssertion](check-generic-narrowing-assertion.md) | `fun <T> <ERROR CLASS>.checkGenericNarrowingAssertion(description: String, act: (T.() -> Unit) -> Unit, immediate: T.() -> Unit, lazy: T.() -> Unit, vararg otherMethods: <ERROR CLASS><String, T.() -> Unit>): Unit` |
| [checkNarrowingAssertion](check-narrowing-assertion.md) | `fun <T : Any> <ERROR CLASS>.checkNarrowingAssertion(description: String, act: (`[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit) -> Unit, immediate: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit, lazy: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit, vararg otherMethods: <ERROR CLASS><String, `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.() -> Unit>): Unit` |
| [checkNarrowingNullableAssertion](check-narrowing-nullable-assertion.md) | `fun <T> <ERROR CLASS>.checkNarrowingNullableAssertion(description: String, act: (`[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>.() -> Unit) -> Unit, immediate: `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>.() -> Unit, lazy: `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>.() -> Unit, vararg otherMethods: <ERROR CLASS><String, `[`IAssertionPlantNullable`](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>.() -> Unit>): Unit` |
| [inCaseOf](in-case-of.md) | `fun <ERROR CLASS>.inCaseOf(description: String, body: <ERROR CLASS>.() -> Unit): <ERROR CLASS>` |
| [setUp](set-up.md) | `fun <ERROR CLASS>.setUp(description: String, body: <ERROR CLASS>.() -> Unit): <ERROR CLASS>` |

