[atrium](../../../index.md) / [ch.tutteli.atrium.creating](../../index.md) / [IAssertionPlantWithCommonFields](../index.md) / [CommonFields](index.md) / [check](.)

# check

`fun check(assertions: List<`[`IAssertion`](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`>): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlantWithCommonFields.kt#L44)

Uses [assertionChecker](assertion-checker.md) to check the given [assertions](check.md#ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields$check(kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) (see [IAssertionChecker.check](../../../ch.tutteli.atrium.checking/-i-assertion-checker/check.md)).

### Parameters

`assertions` - The assertions which shall be checked.

### Exceptions

`AssertionError` - Might throw an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) if any of the [assertions](check.md#ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields$check(kotlin.collections.List((ch.tutteli.atrium.assertions.IAssertion)))/assertions) does not hold.