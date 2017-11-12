package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.assertions.any.narrow.DownCaster
import ch.tutteli.atrium.assertions.any.narrow.failurehandler.ExplanatoryDownCastFailureHandler
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

inline fun <reified T : Any> _isNotNull(plant: IAssertionPlantNullable<T?>, noinline createAssertions: IAssertionPlant<T>.() -> Unit) {
    _downCast(IS_A, T::class, plant, createAssertions)
}

inline fun <reified TSub : Any> _isA(plant: IAssertionPlant<Any>, noinline createAssertions: IAssertionPlant<TSub>.() -> Unit) {
    _downCast(IS_A, TSub::class, plant, createAssertions)
}

fun <T : Any, TSub : T> _downCast(
    description: ITranslatable,
    subType: KClass<TSub>,
    subjectPlant: IBaseAssertionPlant<T?, *>,
    createAssertions: IAssertionPlant<TSub>.() -> Unit
) {
    DownCaster<T, TSub>(ExplanatoryDownCastFailureHandler())
        .downCast(description, subType, subjectPlant, createAssertions)
}
