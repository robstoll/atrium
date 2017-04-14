[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newFeatureAssertionChecker](.)

# newFeatureAssertionChecker

`fun <T : Any> newFeatureAssertionChecker(subjectPlant: `[`IAssertionPlant`](../../ch.tutteli.atrium.creating/-i-assertion-plant/index.md)`<T>): `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L63)

Overrides [IAtriumFactory.newFeatureAssertionChecker](../-i-atrium-factory/new-feature-assertion-checker.md)

Creates an [IAssertionChecker](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md) which creates [IFeatureAssertionGroup](#) instead of checking assertions
and delegates this task to the given [subjectPlant](new-feature-assertion-checker.md#ch.tutteli.atrium.AtriumFactory$newFeatureAssertionChecker(ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.AtriumFactory.newFeatureAssertionChecker.T)))/subjectPlant) by adding (see [IAssertionPlant.addAssertion](../../ch.tutteli.atrium.creating/-i-assertion-plant/add-assertion.md)
the created [IFeatureAssertionGroup](#) to it.

### Parameters

`subjectPlant` - The assertion plant to which the created [IFeatureAssertionGroup](#)
    will be [added](../../ch.tutteli.atrium.creating/-i-assertion-plant/add-assertion.md).

**Return**
The newly created assertion checker.

