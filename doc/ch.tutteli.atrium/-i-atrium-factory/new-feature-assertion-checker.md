[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newFeatureAssertionChecker](.)

# newFeatureAssertionChecker

`abstract fun <T : Any> newFeatureAssertionChecker(subjectPlant: `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>): `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L203)

Creates an [IAssertionChecker](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) which creates [IFeatureAssertionGroup](../../ch.tutteli.atrium.assertions/-i-feature-assertion-group/index.md) instead of checking assertions
and delegates this task to the given [subjectPlant](new-feature-assertion-checker.md#ch.tutteli.atrium.IAtriumFactory$newFeatureAssertionChecker(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.IAtriumFactory.newFeatureAssertionChecker.T)))/subjectPlant) by adding (see [IAssertionPlant.addAssertion](../../ch.tutteli.atrium.creating/-i-assertion-plant/add-assertion.md)
the created [IFeatureAssertionGroup](../../ch.tutteli.atrium.assertions/-i-feature-assertion-group/index.md) to it.

### Parameters

`subjectPlant` - The assertion plant to which the created [IFeatureAssertionGroup](../../ch.tutteli.atrium.assertions/-i-feature-assertion-group/index.md)
    will be [added](../../ch.tutteli.atrium.creating/-i-assertion-plant/add-assertion.md).

**Return**
The newly created assertion checker.

