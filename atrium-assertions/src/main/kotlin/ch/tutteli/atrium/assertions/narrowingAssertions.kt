package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.newDownCastBuilder

inline fun <reified T : Any> _isNotNull(plant: IAssertionPlantNullable<T?>, noinline createAssertions: IAssertionPlant<T>.() -> Unit) {
    AtriumFactory.newDownCastBuilder(IS_A, plant, createAssertions).cast()
}

inline fun <reified TSub : Any> _isA(plant: IAssertionPlant<Any>, noinline createAssertions: IAssertionPlant<TSub>.() -> Unit) {
    AtriumFactory.newDownCastBuilder(IS_A, plant, createAssertions).cast()
}
