[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [IAssertionPlant](index.md) / [checkAssertions](.)

# checkAssertions

`abstract fun checkAssertions(): Unit` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/creating/IAssertionPlant.kt#L50)

Checks the so far [added](add-assertion.md) [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s and reports if one of them fails.

Calling this method more than once should not re-report previously failing assertions.
This method will typically use an [IAssertionChecker](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) for checking and an [IReporter](../../ch.tutteli.atrium.reporting/-i-reporter/index.md) for error reporting.

### Exceptions

`AssertionError` - Reporting a failing assertion might cause that an [AssertionError](http://docs.oracle.com/javase/6/docs/api/java/lang/AssertionError.html) is thrown.

**See Also**

[IAssertionChecker](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)

[IReporter](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)

