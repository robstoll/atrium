package ch.tutteli.atrium.creating

/**
 * Represents a facade to all the different expectation verb functions.
 *
 * @since 1.3.0
 */
interface ExpectationVerbs {

    /**
     * Delegates to a root expectation verb.
     *
     * @since 1.3.0
     */
    fun <T> expect(subject: T): Expect<T>

    /**
     * Delegates to a root expectation verb and appends the expectations the [expectationCreator] creates.
     *
     * @since 1.3.0
     */
    fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Delegates to a root [ExpectGrouping] verb.
     *
     * @param description define the description which shall be used in reporting or `null` in case the default of the
     * verb shall be used.
     *
     * @since 1.3.0
     */
    fun expectGrouped(
        description: String?,
        groupingActions: ExpectGrouping.() -> Unit
    ): ExpectGrouping

    /**
     * Delegates to an expectation verb within an [ExpectGrouping]-lambda and uses the given [expectGrouping] as receiver.
     *
     * @since 1.3.0
     */
    fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T>

    /**
     * Delegates to an expectation verb within an [ExpectGrouping]-lambda and uses the given [expectGrouping]
     * as receiver and appends the expectations the [expectationCreator] creates.
     *
     * @since 1.3.0
     */
    fun <T> expectInExpectGrouped(
        expectGrouping: ExpectGrouping,
        subject: T,
        expectationCreator: Expect<T>.() -> Unit
    ): Expect<T>
}
