package ch.tutteli.atrium.assertions.iterable.contains

import ch.tutteli.atrium.assertions.basic.contains.IContains

/**
 * Defines the contract for sophisticated [Iterable] contains assertions.
 */
interface IIterableContains {

    interface IDecorator : IContains.IDecorator

    interface ICreator<in T1 : Iterable<*>, in T2> : IContains.ICreator<T1, T2>

    interface IChecker : IContains.IChecker
}
