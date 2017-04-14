[atrium](../index.md) / [ch.tutteli.atrium](index.md) / [contains](.)

# contains

`fun <T : CharSequence> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.contains(expected: CharSequence): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/charSequenceAssertions.kt#L12)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) contains the [expected](#).

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

`fun <T : CharSequence> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.contains(expected: CharSequence, vararg otherExpected: CharSequence): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/charSequenceAssertions.kt#L22)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) contains the [expected](#)
and [otherExpected](#)s (if defined).

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

`fun <T : CharSequence> `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>.contains(expected: Any, vararg otherExpected: Any): `[`IAssertionPlant`](../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/charSequenceAssertions.kt#L34)

Makes the assertion that [IAssertionPlant.subject](../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/subject.md) contains [expected](contains.md#ch.tutteli.atrium$contains(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.contains.T)), kotlin.Any, kotlin.Array((kotlin.Any)))/expected)'s [toString](#) representation.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if the made assertion does not hold.

**Return**
This plant to support a fluent-style API.

