[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [DownCastBuilder](index.md) / [cast](.)

# cast

`fun cast(): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TSub>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/DownCastBuilder.kt#L90)

Performs the down-cast if possible; reports a failure otherwise.

Down-Casting is possible if [commonFields](#)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
is not null and its type is [subClass](#) (or a sub-class of [subClass](#)).

In case the down-cast can be performed, it will create an [IAssertionPlant](../-i-assertion-plant/index.md) and use the down-casted
[subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md) of [commonFields](#) as [IAssertionPlant.subject](../-i-assertion-plant-with-common-fields/subject.md)
of the newly created [IAssertionPlant](../-i-assertion-plant/index.md). Furthermore, it will add an [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) (i.a., using the [description](#))
-- which represents the performed down-cast -- to the newly created [IAssertionPlant](../-i-assertion-plant/index.md).

### Exceptions

`AssertionError` - In case the down-cast cannot be performed
    or an additionally specified assertion (using [withLazyAssertions](with-lazy-assertions.md)) does not hold.

`IllegalStateException` - In case reporting a failure does not throw itself.

**Return**
The newly created [IAssertionPlant](../-i-assertion-plant/index.md) for the down-casted
    [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md) of [commonFields](#).

