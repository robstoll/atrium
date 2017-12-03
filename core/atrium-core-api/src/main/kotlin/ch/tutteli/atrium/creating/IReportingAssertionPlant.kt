package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a plant for [IAssertion]s and offers the possibility to [check][checkAssertions] all
 * the [added][addAssertion] assertions which includes error reporting.
 *
 * You can think of it as an [IAssertion] factory which does more than just factoring
 * but also provides quality assurance capabilities.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IReportingAssertionPlant<out T : Any> : IAssertionPlant<T>, IBaseReportingAssertionPlant<T, IAssertionPlant<T>>
