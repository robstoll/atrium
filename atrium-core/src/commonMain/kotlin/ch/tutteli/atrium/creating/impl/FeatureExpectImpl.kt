package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class FeatureExpectImpl<T, R>(
    private val previousExpect: Expect<T>,
    maybeSubject: Option<R>,
    //TODO 1.3.0 replace Translatable with InlineElement
    @Suppress("DEPRECATION")
    private val description: ch.tutteli.atrium.reporting.translating.Translatable,
    private val representation: Any?,
    assertions: List<Assertion>
) : BaseExpectImpl<R>(maybeSubject), FeatureExpect<T, R> {

    constructor(
        previousExpect: Expect<T>,
        maybeSubject: Option<R>,
        //TODO 1.3.0 replace Translatable with InlineElement
        @Suppress("DEPRECATION")
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        assertions: List<Assertion>,
        options: FeatureExpectOptions<R>
    ) : this(
        previousExpect,
        maybeSubject,
        options.description ?: description,
        determineRepresentation(
            options.representationInsteadOfFeature,
            maybeSubject
        ),
        assertions
    )

    constructor(previous: FeatureExpectImpl<T, R>, options: FeatureExpectOptions<R>) : this(
        previous.previousExpect,
        previous.maybeSubject,
        previous.description,
        previous.assertions,
        options
    )

    /**
     * All made assertions so far.
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<Assertion> = mutableListOf()

    init {
        appendAsGroup(assertions)
    }

    override val components: ComponentFactoryContainer
        // TODO 1.3.0 the function to turn an Expect into a ProofContainer should be located in core
        get() = (previousExpect as AssertionContainer<*>).components

    override fun append(assertion: Assertion): Expect<R> {
        assertions.add(assertion)
        //Would be nice if we don't have to add it immediately to the previousExpect but only:
        //if (!assertion.holds()) {
        //this way we could show chained features as one in reporting, i.e. message.contains("a").contains("b") would be:
        //* > message
        //    * contains: "a"
        //    " contains: "b"
        //and not
        //* > message
        //    * contains: "a"
        //* > message
        //    " contains: "b"
        //
        //However, for this to work we would need to know when no more assertion is defined. This would be possible
        //for CollectingExpectImpl
        (previousExpect as AssertionContainer<*>).append(
            assertionBuilder.feature
                .withDescriptionAndRepresentation(description, representation)
                .withAssertions(assertions.toList())
                .build()
        )
        assertions.clear()
        return this
    }
}
