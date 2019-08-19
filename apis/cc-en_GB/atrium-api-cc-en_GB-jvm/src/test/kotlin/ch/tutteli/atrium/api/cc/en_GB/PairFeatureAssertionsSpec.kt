@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

class PairFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.PairFeatureAssertionsSpec(
    AssertionVerbFactory,
    firstVal.name to firstVal,
    firstFun.name to firstFun,
    secondVal.name to secondVal,
    secondFun.name to secondFun,
    nullableFirstVal.name to nullableFirstVal,
    nullableSecondVal.name to nullableSecondVal
){
    companion object {
        private val firstVal: KProperty1<Assert<Pair<String, Int>>, Assert<String>> = Assert<Pair<String, Int>>::first
        private val firstFun: KFunction2<Assert<Pair<String, Int>>, Assert<String>.() -> Unit, Assert<Pair<String, Int>>> = Assert<Pair<String, Int>>::first
        private val secondVal: KProperty1<Assert<Pair<String, Int>>, Assert<Int>> = Assert<Pair<String, Int>>::second
        private val secondFun: KFunction2<Assert<Pair<String, Int>>, Assert<Int>.() -> Unit, Assert<Pair<String, Int>>> = Assert<Pair<String, Int>>::second
        private val nullableFirstVal: KProperty1<Assert<Pair<String?, Int?>>, AssertionPlantNullable<String?>> = Assert<Pair<String?, Int?>>::first
        private val nullableSecondVal: KProperty1<Assert<Pair<String?, Int?>>, AssertionPlantNullable<Int?>> = Assert<Pair<String?, Int?>>::second
    }
}
