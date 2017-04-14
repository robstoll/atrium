[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newNullable](.)

# newNullable

`abstract fun <T> newNullable(assertionVerb: String, subject: T, reporter: `[`IReporter`](../../ch.tutteli.atrium.reporting/-i-reporter/index.md)`): `[`IAssertionPlantNullable`](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L126)

Creates an [IAssertionPlantNullable](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md).

It creates a [newThrowingAssertionChecker](new-throwing-assertion-checker.md) based on the given [reporter](new-nullable.md#ch.tutteli.atrium.IAtriumFactory$newNullable(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newNullable.T, ch.tutteli.atrium.reporting.IReporter)/reporter) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`reporter` - The reporter which will be use for a [newThrowingAssertionChecker](new-throwing-assertion-checker.md).

**Return**
The newly created assertion plant.

`abstract fun <T> newNullable(assertionVerb: String, subject: T, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): `[`IAssertionPlantNullable`](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L142)

Creates an [IAssertionPlantNullable](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md).

It uses the given [assertionChecker](new-nullable.md#ch.tutteli.atrium.IAtriumFactory$newNullable(kotlin.String, ch.tutteli.atrium.IAtriumFactory.newNullable.T, ch.tutteli.atrium.checking.IAssertionChecker)/assertionChecker) for assertion checking.

### Parameters

`assertionVerb` - The assertion verb which will be used inter alia in reporting
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-verb.md)).

`subject` - The subject for which this plant will create/check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.subject](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/subject.md)).

`assertionChecker` - The checker which will be used to check [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md)s.
    (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md)).

**Return**
The newly created assertion plant.

`abstract fun <T> newNullable(commonFields: `[`CommonFields`](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T>): `[`IAssertionPlantNullable`](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md)`<T>` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L153)

Creates an [IAssertionPlantNullable](../../ch.tutteli.atrium.creating/-i-assertion-plant-nullable/index.md).

It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker](../../ch.tutteli.atrium.creating/-i-assertion-plant-with-common-fields/-common-fields/assertion-checker.md) of the given [commonFields](new-nullable.md#ch.tutteli.atrium.IAtriumFactory$newNullable(ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields((ch.tutteli.atrium.IAtriumFactory.newNullable.T)))/commonFields) for assertion checking.

### Parameters

`commonFields` - The commonFields for the new assertion plant.

**Return**
The newly created assertion plant.

