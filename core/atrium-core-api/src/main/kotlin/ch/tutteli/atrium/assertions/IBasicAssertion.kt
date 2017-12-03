package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * The base interface for [IAssertion]s which only consists of the [expected] result with a complementary [description].
 */
interface IBasicAssertion : IAssertion {
    /**
     * The expected result.
     */
    val expected: Any

    /**
     * The complementary description to the [expected] result such as `contains`, `is not` etc.
     */
    val description: ITranslatable
}
