[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [genericCheck](.)

# genericCheck

`fun <T : Any> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.genericCheck(feature: KProperty0<Boolean>): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/anyAssertions.kt#L12)

Use this function to create a generic check based on an existing property of the [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md).

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

