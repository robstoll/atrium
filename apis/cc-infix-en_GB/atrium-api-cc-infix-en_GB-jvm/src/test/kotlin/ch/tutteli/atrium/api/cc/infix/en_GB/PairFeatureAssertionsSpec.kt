package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

class PairFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.PairFeatureAssertionsSpec(
    AssertionVerbFactory,
    firstVal.name to firstVal,
    firstFun.name to Companion::first,
    secondVal.name to secondVal,
    secondFun.name to Companion::second,
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

        fun first(plant: Assert<Pair<String, Int>>, assertionCreator: Assert<String>.() -> Unit)
            = plant first { assertionCreator() }

        fun second(plant: Assert<Pair<String, Int>>, assertionCreator: Assert<Int>.() -> Unit)
            = plant second { assertionCreator() }
    }
}
