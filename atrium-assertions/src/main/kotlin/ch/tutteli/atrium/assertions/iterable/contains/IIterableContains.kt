package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Defines the contract for sophisticated [Iterable] contains assertions.
 */
interface IIterableContains {

    interface IDecorator {
        fun decorateDescription(description: ITranslatable): ITranslatable
    }

    interface ICreator<in T : Iterable<*>, in T2> {
        fun createAssertionGroup(plant: IAssertionPlant<T>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup
    }

    interface IChecker {
        fun createAssertion(foundNumberOfTimes: Int): IAssertion
    }

}
