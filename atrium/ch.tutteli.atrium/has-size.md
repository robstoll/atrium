[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [hasSize](.)

# hasSize

`fun <T : Collection<*>> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.hasSize(size: Int): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/collectionAssertions.kt#L11)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md)'s [Collection.size](#) is [size](has-size.md#ch.tutteli.atrium$hasSize(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.hasSize.T)), kotlin.Int)/size).

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

