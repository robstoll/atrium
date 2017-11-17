package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Defines the contract for sophisticated [Iterable] contains assertions.
 */
interface IIterableContains {

    interface IDecorator : IContains.IDecorator

    interface ICreator<in T : Iterable<*>, in T2> {
        fun createAssertionGroup(plant: IAssertionPlant<T>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup
    }

    interface IChecker : IContains.IChecker
}
