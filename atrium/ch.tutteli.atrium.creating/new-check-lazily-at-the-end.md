[atrium](../index.md) / [ch.tutteli.atrium.creating](index.md) / [newCheckLazilyAtTheEnd](.)

# newCheckLazilyAtTheEnd

`inline fun <T : Any> `[`IAtriumFactory`](../ch.tutteli.atrium/-i-atrium-factory/index.md)`.newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, reporter: `[`IReporter`](../ch.tutteli.atrium.reporting/-i-reporter/index.md)`, createAssertions: `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>.() -> Unit): `[`IAssertionPlant`](-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/creating/IAtriumFactoryExtensions.kt#L26)

Use this function to create a custom *assertion verb* which lazy evaluates assertions
(see [AtriumFactory.newCheckLazily](../ch.tutteli.atrium/-atrium-factory/new-check-lazily.md)).

This function will create an [IAssertionPlant](-i-assertion-plant/index.md) which does not check the created assertions until one
calls [IAssertionPlant.checkAssertions](-i-assertion-plant/check-assertions.md).
However, it uses the given [createAssertions](new-check-lazily-at-the-end.md#ch.tutteli.atrium.creating$newCheckLazilyAtTheEnd(ch.tutteli.atrium.IAtriumFactory, kotlin.String, ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd.T, ch.tutteli.atrium.reporting.IReporter, kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd.T)), kotlin.Unit)))/createAssertions) function immediately after creating the [IAssertionPlant](-i-assertion-plant/index.md)
which might add some assertions and it then calls [IAssertionPlant.checkAssertions](-i-assertion-plant/check-assertions.md).
In case all assertions added so far hold, then it will not evaluate further added assertions until
one calls [IAssertionPlant.checkAssertions](-i-assertion-plant/check-assertions.md) again.

It creates a [IAtriumFactory.newThrowingAssertionChecker](../ch.tutteli.atrium/-i-atrium-factory/new-throwing-assertion-checker.md) based on the given [reporter](new-check-lazily-at-the-end.md#ch.tutteli.atrium.creating$newCheckLazilyAtTheEnd(ch.tutteli.atrium.IAtriumFactory, kotlin.String, ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd.T, ch.tutteli.atrium.reporting.IReporter, kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd.T)), kotlin.Unit)))/reporter) for assertion checking.

### Exceptions

`AssertionError` - The newly created [IAssertionPlant](-i-assertion-plant/index.md) might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) in case a
    created [IAssertion](../ch.tutteli.atrium.assertions/-i-assertion/index.md) does not hold.

**Return**
The newly created [IAssertionPlant](-i-assertion-plant/index.md) which can be used to postulate further assertions.

