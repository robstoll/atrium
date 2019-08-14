@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.spec.integration.TestData
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class FeatureAssertionsBoundedReferenceWhenCollectingPlantSpec : ch.tutteli.atrium.spec.integration.FeatureAssertionsBoundedReferenceWhenCollectingPlantSpec(
    AssertionVerbFactory,
    propertyImmediate,
    propertyLazy,
    return0ValueImmediate,
    return1ValueImmediate,
    return2ValueImmediate,
    return3ValueImmediate,
    return4ValueImmediate,
    return5ValueImmediate,
    return0ValueLazy,
    return1ValueLazy,
    return2ValueLazy,
    return3ValueLazy,
    return4ValueLazy,
    return5ValueLazy,

    propertyNullableHolds,
    return0ValueNullableHolds,
    return1ValueNullableHolds,
    return2ValueNullableHolds,
    return3ValueNullableHolds,
    return4ValueNullableHolds,
    return5ValueNullableHolds
) {

    @Suppress("DEPRECATION" /* feature mechanism shown here is obsolete and will be removed with 1.0.0 */)
    companion object {
        val propertyImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description).contains("hello") }
        val propertyLazy: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description) { contains("hello") } }
        val return0ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return0).contains("hello") }
        val return1ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return1, "a").contains("hello") }
        val return2ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return2, "a", 1).contains("hello") }
        val return3ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return3, "a", 1, true).contains("hello") }
        val return4ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return4, "a", 1, true, 1.2).contains("hello") }
        val return5ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b').contains("hello") }
        val return0ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return0) { contains("hello") } }
        val return1ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return1, "a") { contains("hello") } }
        val return2ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return2, "a", 1) { contains("hello") } }
        val return3ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return3, "a", 1, true) { contains("hello") } }
        val return4ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return4, "a", 1, true, 1.2) { contains("hello") } }
        val return5ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') { contains("hello") } }

        val propertyNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).notToBeNull {} }
        val return0ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable0).notToBeNull {} }
        val return1ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable1, "a").notToBeNull {} }
        val return2ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable2, "a", 1).notToBeNull {} }
        val return3ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable3, "a", 1, true).notToBeNull {} }
        val return4ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2).notToBeNull {} }
        val return5ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b').notToBeNull {} }
    }
}
