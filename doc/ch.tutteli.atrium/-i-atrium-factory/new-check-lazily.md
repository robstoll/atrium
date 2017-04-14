[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newCheckLazily](.)

# newCheckLazily

`abstract fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L38)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It creates a [newThrowingAssertionChecker](new-throwing-assertion-checker.md) based on the given [reporter](new-check-lazily.md#ch.tutteli.atrium.IAtriumFactory$newCheckLazily(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newCheckLazily.T, ch.tutteli.atrium.reporting.IReporter)/reporter) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`reporter` - The reporter which will be use for a [newThrowingAssertionChecker](new-throwing-assertion-checker.md).

**Return**
The newly created assertion plant.

`abstract fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L55)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It uses the given [assertionChecker](new-check-lazily.md#ch.tutteli.atrium.IAtriumFactory$newCheckLazily(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newCheckLazily.T, ch.tutteli.atrium.checking.IAssertionChecker)/assertionChecker) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`assertionChecker` - The checker which will be used to check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md)).

**Return**
The newly created assertion plant.

`abstract fun <T : Any> newCheckLazily(commonFields: `[`CommonFields`](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T>): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L67)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which does not check the created or
added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s until one calls [IAssertionPlant.checkAssertions](../../ch.tutteli.atrium.creating/-i-assertion-plant/check-assertions.md).

It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md) of the given [commonFields](new-check-lazily.md#ch.tutteli.atrium.IAtriumFactory$newCheckLazily(ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields((ch.tutteli.atrium.IAtriumFactory.newCheckLazily.T)))/commonFields) for assertion checking.

### Parameters

`commonFields` - The commonFields for the new assertion plant.

**Return**
The newly created assertion plant.

