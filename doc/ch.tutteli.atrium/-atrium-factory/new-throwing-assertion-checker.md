[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newThrowingAssertionChecker](.)

# newThrowingAssertionChecker

`fun newThrowingAssertionChecker(reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L60)

Overrides [IAtriumFactory.newThrowingAssertionChecker](../-i-atrium-factory/new-throwing-assertion-checker.md)

Creates an [IAssertionChecker](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) which throws [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html)s in case an assertion fails
and uses the given [reporter](new-throwing-assertion-checker.md#ch.tutteli.atrium.AtriumFactory$newThrowingAssertionChecker(ch.tutteli.atrium.reporting.IReporter)/reporter) for reporting.

### Parameters

`reporter` - The reporter which is used to report [IAssertion](#)s.

**Return**
The newly created assertion checker.

