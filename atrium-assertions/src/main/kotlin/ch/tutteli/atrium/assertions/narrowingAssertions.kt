package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.assertions.any.narrow.DownCaster
import ch.tutteli.atrium.assertions.any.narrow.failurehandler.ExplanatoryDownCastFailureHandler
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

inline fun <reified T : Any> _isNotNull(plant: IAssertionPlantNullable<T?>, noinline assertionCreator: IAssertionPlant<T>.() -> Unit) {
    _downCast(IS_A, T::class, plant, assertionCreator)
}

inline fun <reified TSub : Any> _isA(plant: IAssertionPlant<Any>, noinline assertionCreator: IAssertionPlant<TSub>.() -> Unit) {
    _downCast(IS_A, TSub::class, plant, assertionCreator)
}

fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: IBaseAssertionPlant<T?, *>,
    assertionCreator: IAssertionPlant<TSub>.() -> Unit
) {
    DownCaster<T, TSub>(ExplanatoryDownCastFailureHandler())
        .downCast(description, subType, subjectPlant, assertionCreator)
}
