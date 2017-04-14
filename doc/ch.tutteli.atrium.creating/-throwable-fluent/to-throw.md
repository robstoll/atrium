[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [ThrowableFluent](index.md) / [toThrow](.)

# toThrow

`inline fun <reified TExpected : Throwable> toThrow(): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/ThrowableFluent.kt#L37)

Makes an assertion about the [commonFields](common-fields.md)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
that it is of the expected type [TExpected](#) and reports an error if subject is `null` or another type
than the expected one.

### Exceptions

`AssertionError` - In case the made assertion fails.

`IllegalStateException` - In case reporting a failure does not throw itself.

**Return**
This builder to support a fluent-style API.

`inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>.() -> Unit): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/ThrowableFluent.kt#L52)

Makes an assertion about the [commonFields](common-fields.md)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
that it is of the expected type [TExpected](#) and reports an error if subject is null or another type
than the expected one -- furthermore it [createAssertions](to-throw.md#ch.tutteli.atrium.creating.ThrowableFluent$toThrow(kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.ThrowableFluent.toThrow.TExpected)), kotlin.Unit)))/createAssertions) which are checked additionally as well.

### Exceptions

`AssertionError` - In case the made assertion fails.

`IllegalStateException` - In case reporting a failure does not throw itself.

**Return**
This builder to support a fluent-style API.

