package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2

class ListFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
    fun2<List<Int>, Int, Expect<Int>.() -> Unit>(Expect<List<Int>>::get),
    feature1(Expect<List<Int?>>::get),
    "get for nullable not implement in this API" to Companion::getNullable
) {
    companion object {

        fun getNullable(
            plant: Expect<List<Int?>>,
            index: Int,
            assertionCreator: Expect<Int?>.() -> Unit
        ): Expect<List<Int?>> = plant.addAssertion(ExpectImpl.list.get(plant, index, assertionCreator))
    }
}
