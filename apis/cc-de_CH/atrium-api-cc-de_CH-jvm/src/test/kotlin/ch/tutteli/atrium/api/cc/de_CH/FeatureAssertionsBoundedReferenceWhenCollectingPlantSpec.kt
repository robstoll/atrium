@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.spec.integration.TestData

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
        val propertyImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description).enthaelt("hello") }
        val propertyLazy: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::description) { enthaelt("hello") } }
        val return0ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return0).enthaelt("hello") }
        val return1ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return1, "a").enthaelt("hello") }
        val return2ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return2, "a", 1).enthaelt("hello") }
        val return3ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return3, "a", 1, true).enthaelt("hello") }
        val return4ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return4, "a", 1, true, 1.2).enthaelt("hello") }
        val return5ValueImmediate: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return5, "a", 1, true, 1.2, 'b').enthaelt("hello") }
        val return0ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return0) { enthaelt("hello") } }
        val return1ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return1, "a") { enthaelt("hello") } }
        val return2ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return2, "a", 1) { enthaelt("hello") } }
        val return3ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return3, "a", 1, true) { enthaelt("hello") } }
        val return4ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return4, "a", 1, true, 1.2) { enthaelt("hello") } }
        val return5ValueLazy: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::return5, "a", 1, true, 1.2, 'b') { enthaelt("hello") } }

        val propertyNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { property(subject::nullableValue).istNichtNull {} }
        val return0ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable0).istNichtNull {} }
        val return1ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable1, "a").istNichtNull {} }
        val return2ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable2, "a", 1).istNichtNull {} }
        val return3ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable3, "a", 1, true).istNichtNull {} }
        val return4ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable4, "a", 1, true, 1.2).istNichtNull {} }
        val return5ValueNullableHolds: CollectingAssertionPlant<TestData>.() -> Unit = { rueckgabewertVon(subject::returnNullable5, "a", 1, true, 1.2, 'b').istNichtNull {} }
    }
}

