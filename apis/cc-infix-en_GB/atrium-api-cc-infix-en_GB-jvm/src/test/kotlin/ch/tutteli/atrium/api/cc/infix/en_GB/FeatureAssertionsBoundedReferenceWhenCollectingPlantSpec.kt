@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

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

    @Suppress("DEPRECATION" /* TODO #40 creating feature assertions will change anyway, thus not fixing the usages of `subject` */)
    companion object {
        val propertyImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description) contains "hello" }
        val propertyLazy: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description) { o contains "hello" } }
        val return0ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return0) contains Values("hello") }
        val return1ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return1, "a") contains Values("hello") }
        val return2ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return2, "a", 1) contains Values("hello") }
        val return3ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return3, "a", 1, true) contains Values("hello") }
        val return4ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return4, "a", 1, true, 1.2) contains Values("hello") }
        val return5ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') contains Values("hello") }
        val return0ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return0) { o contains Values("hello") } }
        val return1ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return1, "a") { o contains Values("hello") } }
        val return2ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return2, "a", 1) { o contains Values("hello") } }
        val return3ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return3, "a", 1, true) { o contains Values("hello") } }
        val return4ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return4, "a", 1, true, 1.2) { o contains Values("hello") } }
        val return5ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::return5, "a", 1, true, 1.2, 'b') { o contains Values("hello") } }

        val propertyNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue) notToBeNull {} }
        val return0ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable0) notToBeNull {} }
        val return1ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable1, "a") notToBeNull {} }
        val return2ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable2, "a", 1) notToBeNull {} }
        val return3ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable3, "a", 1, true) notToBeNull {} }
        val return4ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable4, "a", 1, true, 1.2) notToBeNull {} }
        val return5ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { returnValueOf(subject::returnNullable5, "a", 1, true, 1.2, 'b') notToBeNull {} }
    }
}
