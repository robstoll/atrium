[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newCheckImmediately](.)

# newCheckImmediately

`abstract fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L83)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which immediately checks added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

It creates a [newThrowingAssertionChecker](new-throwing-assertion-checker.md) based on the given [reporter](new-check-immediately.md#ch.tutteli.atrium.IAtriumFactory$newCheckImmediately(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newCheckImmediately.T, ch.tutteli.atrium.reporting.IReporter)/reporter) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`reporter` - The reporter which will be use for a [newThrowingAssertionChecker](new-throwing-assertion-checker.md).

**Return**
The newly created assertion plant.

`abstract fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L99)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which immediately checks added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

It uses the given [assertionChecker](new-check-immediately.md#ch.tutteli.atrium.IAtriumFactory$newCheckImmediately(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newCheckImmediately.T, ch.tutteli.atrium.checking.IAssertionChecker)/assertionChecker) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`assertionChecker` - The checker which will be used to check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md)).

**Return**
The newly created assertion plant.

`abstract fun <T : Any> newCheckImmediately(commonFields: `[`CommonFields`](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T>): `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L110)

Creates an [IAssertionPlant](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md) which immediately checks added [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.

It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md) of the given [commonFields](new-check-immediately.md#ch.tutteli.atrium.IAtriumFactory$newCheckImmediately(ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields((ch.tutteli.atrium.IAtriumFactory.newCheckImmediately.T)))/commonFields) for assertion checking.

### Parameters

`commonFields` - The commonFields for the new assertion plant.

**Return**
The newly created assertion plant.

