package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.feature.FeatureStep
import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.ListGetStep
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.reflect.KFunction1

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]),
 * extracts the corresponding element and
 * and creates a [FeatureStep] which allows to define what should be done with it.
 *
 * @return [FeatureStep] the next step in the building process.
 *
 * @since 0.10.0
 */
infix fun <E, T : List<E>> Expect<T>.get(index: Int): FeatureExpect<T, E> =
//    FeatureStep(ExpectImpl.list.get(this, index))
    ExpectImpl.list.get(this, index).getExpectOfFeature()

infix fun <E, T : List<E>> Expect<T>.get(withAssertionCreator: WithAssertionCreator<Int, E>): Expect<T> =
    ExpectImpl.list.get(this, withAssertionCreator.value).addToInitial(withAssertionCreator.assertionCreator)
