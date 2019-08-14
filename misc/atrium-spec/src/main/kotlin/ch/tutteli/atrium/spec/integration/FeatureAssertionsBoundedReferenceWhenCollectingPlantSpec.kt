@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.Spek

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class FeatureAssertionsBoundedReferenceWhenCollectingPlantSpec(
    verbs: AssertionVerbFactory,

    propertyImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    propertyLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return0ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return1ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return2ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return3ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return4ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return5ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit,
    return0ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return1ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return2ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return3ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return4ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,
    return5ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit,

    propertyNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return0ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return1ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return2ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return3ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return4ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,
    return5ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit,

    describePrefix: String = "[Atrium] "
) : Spek({

    action("$describePrefix feature assertion should throw a ${PleaseUseReplacementException::class.simpleName}") {
        @Suppress("DEPRECATION")
        val plant = coreFactory.newCollectingPlant { TestData("hello robert", 1) }
        mapOf(
            "`property` immediate" to propertyImmediate,
            "`property` lazy" to propertyLazy,
            "`returnValueOf` without arguments and immediate" to return0ValueImmediate,
            "`returnValueOf` with 1 argument and immediate" to return1ValueImmediate,
            "`returnValueOf` with 2 arguments and immediate" to return2ValueImmediate,
            "`returnValueOf` with 3 arguments and immediate" to return3ValueImmediate,
            "`returnValueOf` with 4 arguments and immediate" to return4ValueImmediate,
            "`returnValueOf` with 5 arguments and immediate" to return5ValueImmediate,
            "`returnValueOf` without arguments arguments and lazy" to return0ValueLazy,
            "`returnValueOf` with 1  argument and lazy" to return1ValueLazy,
            "`returnValueOf` with 2  arguments and lazy" to return2ValueLazy,
            "`returnValueOf` with 3  arguments and lazy" to return3ValueLazy,
            "`returnValueOf` with 4  arguments and lazy" to return4ValueLazy,
            "`returnValueOf` with 5  arguments and lazy" to return5ValueLazy,

            "`property` toBe(null)" to propertyNullableHolds,
            "`returnValueOf` without argument and toBe(null)" to return0ValueNullableHolds,
            "`returnValueOf` with 1 argument and toBe(null)" to return1ValueNullableHolds,
            "`returnValueOf` with 2 arguments and toBe(null)" to return2ValueNullableHolds,
            "`returnValueOf` with 3 arguments and toBe(null)" to return3ValueNullableHolds,
            "`returnValueOf` with 4 arguments and toBe(null)" to return4ValueNullableHolds,
            "`returnValueOf` with 5 arguments and toBe(null)" to return5ValueNullableHolds

            ).forEach { (description, featureFun) ->
            test(description) {
                verbs.checkException {
                    plant.featureFun()
                }.toThrow<PleaseUseReplacementException> { }
            }
        }
    }
})
