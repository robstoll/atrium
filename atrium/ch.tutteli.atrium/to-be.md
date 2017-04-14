[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [toBe](.)

# toBe

`fun <T : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.toBe(expected: T): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/anyAssertions.kt#L24)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) is (equals) [expected](to-be.md#ch.tutteli.atrium$toBe(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.toBe.T)), ch.tutteli.atrium.toBe.T)/expected).

This method might enforce in the future, that [expected](to-be.md#ch.tutteli.atrium$toBe(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.toBe.T)), ch.tutteli.atrium.toBe.T)/expected) has to be the same type as [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md).
Currently the following is possible: `assert(1).toBe(1.0)`

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

