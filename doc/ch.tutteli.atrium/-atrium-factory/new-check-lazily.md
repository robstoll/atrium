[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newCheckLazily](.)

# newCheckLazily

`fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L23)

Overrides [IAtriumFactory.newCheckLazily](../-i-atrium-factory/new-check-lazily.md)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](#)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It creates a [newThrowingAssertionChecker](new-throwing-assertion-checker.md) based on the given [reporter](new-check-lazily.md#ch.tutteli.atrium.AtriumFactory$newCheckLazily(kotlin.String, ch.tutteli.atrium.AtriumFactory.newCheckLazily.T, ch.tutteli.atrium.reporting.IReporter)/reporter) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](#)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`reporter` - The reporter which will be use for a [newThrowingAssertionChecker](new-throwing-assertion-checker.md).

**Return**
The newly created assertion plant.

`fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L26)

Overrides [IAtriumFactory.newCheckLazily](../-i-atrium-factory/new-check-lazily.md)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](#)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It uses the given [assertionChecker](new-check-lazily.md#ch.tutteli.atrium.AtriumFactory$newCheckLazily(kotlin.String, ch.tutteli.atrium.AtriumFactory.newCheckLazily.T, ch.tutteli.atrium.checking.IAssertionChecker)/assertionChecker) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](#)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`assertionChecker` - The checker which will be used to check [IAssertion](#)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md)).

**Return**
The newly created assertion plant.

`fun <T : Any> newCheckLazily(commonFields: `[`CommonFields`](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T>): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L29)

Overrides [IAtriumFactory.newCheckLazily](../-i-atrium-factory/new-check-lazily.md)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](#)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md) of the given [commonFields](new-check-lazily.md#ch.tutteli.atrium.AtriumFactory$newCheckLazily(ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields((ch.tutteli.atrium.AtriumFactory.newCheckLazily.T)))/commonFields) for assertion checking.

### Parameters

`commonFields` - The commonFields for the new assertion plant.

**Return**
The newly created assertion plant.

