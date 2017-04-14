[atrium](../../../index.md) / [ch.tutteli.atrium.creating](../../index.md) / [IAssertionPlantWithCommonFields](../index.md) / [CommonFields](index.md) / [fail](.)

# fail

`fun fail(assertion: `[`IAssertion`](../../../ch.tutteli.atrium.assertions/-i-assertion/index.md)`): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlantWithCommonFields.kt#L56)

Uses [assertionChecker](assertion-checker.md) to report a failing [assertion](fail.md#ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields$fail(ch.tutteli.atrium.assertions.IAssertion)/assertion) (see [IAssertionChecker.fail](../../../ch.tutteli.atrium.checking/-i-assertion-checker/fail.md)).

In case [subject](subject.md) is null, then [RawString.NULL](../../../ch.tutteli.atrium.reporting/-raw-string/-n-u-l-l.md) will be used as representation.

### Parameters

`assertion` - The failing assertion.

### Exceptions

`AssertionError` - Typically throws an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) or another [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html).