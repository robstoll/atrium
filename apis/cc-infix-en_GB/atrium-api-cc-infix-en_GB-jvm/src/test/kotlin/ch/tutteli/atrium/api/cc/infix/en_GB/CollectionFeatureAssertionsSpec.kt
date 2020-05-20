// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.size
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CollectionFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionFeatureAssertionsSpec(
    AssertionVerbFactory,
    sizeVal.name to sizeVal,
    sizeFun.name to Companion::size
) {
    companion object {
        private val sizeVal: KProperty1<Assert<Collection<String>>, Assert<Int>> = Assert<Collection<String>>::size
        private val sizeFun: KFunction2<Assert<Collection<String>>, Assert<Int>.() -> Unit, Assert<Collection<String>>> =
            Assert<Collection<String>>::size

        fun size(plant: Assert<Collection<String>>, assertionCreator: Assert<Int>.() -> Unit) =
            plant.apply { asExpect().size.asAssert({ assertionCreator() }) }
    }
}
