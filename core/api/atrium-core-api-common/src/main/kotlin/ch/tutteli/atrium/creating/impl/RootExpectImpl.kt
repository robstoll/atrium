package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

@ExperimentalNewExpectTypes
internal class RootExpectImpl<T>(
    maybeSubject: Option<T>,
    private val assertionVerb: Translatable,
    private val representation: Any?,
    private val reporter: Reporter
) : BaseExpectImpl<T>(maybeSubject), RootExpect<T> {

    constructor(
        maybeSubject: Option<T>,
        assertionVerb: Translatable,
        options: RootExpectOptions<T>?
    ) : this(
        maybeSubject,
        options?.assertionVerb ?: assertionVerb,
        determineRepresentation(
            options?.representationInsteadOfSubject,
            maybeSubject
        ),
        options?.reporter ?: ch.tutteli.atrium.reporting.reporter
    )

    constructor(previous: RootExpectImpl<T>, options: RootExpectOptions<T>) : this(
        previous.maybeSubject,
        previous.assertionVerb,
        options
    )

    /**
     * All made assertions so far.
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<Assertion> = mutableListOf()

    override fun addAssertion(assertion: Assertion): Expect<T> {
        assertions.add(assertion)
        if (!assertion.holds()) {
            val assertionGroup = assertionBuilder.root
                .withDescriptionAndRepresentation(assertionVerb, representation)
                .withAssertion(assertion)
                .build()

            val sb = StringBuilder()
            reporter.format(assertionGroup, sb)
            throw AtriumError.create(sb.toString(), reporter.atriumErrorAdjuster)
        }
        return this
    }

}
