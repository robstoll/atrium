package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.basic.contains.IContains

/**
 * Defines the contract for sophisticated [Iterable] `contains` assertions.
 */
interface IIterableContains {

    interface ISearchBehaviour : IContains.ISearchBehaviour

    interface ICreator<in T : Iterable<*>, in S> : IContains.ICreator<T, S>

    interface IChecker : IContains.IChecker
}
