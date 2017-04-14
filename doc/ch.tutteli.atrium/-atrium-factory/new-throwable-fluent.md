[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newThrowableFluent](.)

# newThrowableFluent

`fun newThrowableFluent(assertionVerb: String, act: () -> Unit, reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`ThrowableFluent`](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L82)

Creates a [ThrowableFluent](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md) based on the given [assertionVerb](new-throwable-fluent.md#ch.tutteli.atrium.AtriumFactory$newThrowableFluent(kotlin.String, kotlin.Function0((kotlin.Unit)), ch.tutteli.atrium.reporting.IReporter)/assertionVerb) and the [act](new-throwable-fluent.md#ch.tutteli.atrium.AtriumFactory$newThrowableFluent(kotlin.String, kotlin.Function0((kotlin.Unit)), ch.tutteli.atrium.reporting.IReporter)/act) function.

It uses the given [reporter](new-throwable-fluent.md#ch.tutteli.atrium.AtriumFactory$newThrowableFluent(kotlin.String, kotlin.Function0((kotlin.Unit)), ch.tutteli.atrium.reporting.IReporter)/reporter) for reporting.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`act` - The function which is expected to throw a [Throwable](#) which in turn will be used as subject
    for postulated [IAssertion](#)s (see [ThrowableFluent](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md) and
    [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`reporter` - The reporter used to create a [newThrowingAssertionChecker](new-throwing-assertion-checker.md) and used for failure reporting.

**Return**
The newly created [ThrowableFluent](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md).

**See Also**

[ThrowableFluent](../../ch.tutteli.atrium.creating/-throwable-fluent/index.md)

