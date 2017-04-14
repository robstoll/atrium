[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [message](.)

# message

`val <T : Throwable> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.message: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<String>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/throwableAssertions.kt#L14)

Creates an [IAssertionPlantNullable](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md) for the [message](#) of the plant's
[subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) (which is an [Throwable](#)) and makes the assertion that message [isNotNull](is-not-null.md).

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case [message](#) is `null`.

**Return**
An [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which immediately evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s (see [IAtriumFactory.newCheckImmediately](-i-atrium-factory/new-check-immediately.md)).

`fun <T : Throwable> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.message(createAssertions: `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<String>.() -> Unit): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<String>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/throwableAssertions.kt#L25)

Creates an [IAssertionPlantNullable](../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md) for the [message](#) of the plant's
[subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) (which is an [Throwable](#)) and makes the assertion that message [isNotNull](is-not-null.md)
and uses [createAssertions](message.md#ch.tutteli.atrium$message(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.message.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((kotlin.String)), kotlin.Unit)))/createAssertions) which might create further [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s which are lazily evaluated at the end.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case [message](#) is `null`
    or if an additionally created [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s (by calling [createAssertions](message.md#ch.tutteli.atrium$message(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.message.T)), kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((kotlin.String)), kotlin.Unit)))/createAssertions)) does not hold.

**Return**
An [IAssertionPlant](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which lazily evaluates [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md)s (see [IAtriumFactory.newCheckLazily](-i-atrium-factory/new-check-lazily.md)).

