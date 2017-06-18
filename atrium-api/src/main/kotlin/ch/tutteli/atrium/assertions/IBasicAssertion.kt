package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * The base interface for [IAssertion]s which only have a [description] and a [representation] of the expected result.
 */
interface IBasicAssertion : IAssertion {
    /**
     * The description of the assertion
     */
    val description: ITranslatable
    /**
     * The representation of the expected result.
     */
    val representation: Any
}
