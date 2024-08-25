package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

@ExperimentalNewExpectTypes
@OptIn(ExperimentalComponentFactoryContainer::class)
internal class RootExpectImpl<T>(
    maybeSubject: Option<T>,
    private val expectationVerb: Translatable,
    private val representation: Any?,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), RootExpect<T> {

    constructor(
        maybeSubject: Option<T>,
        expectationVerb: Translatable,
        options: RootExpectOptions<T>?
    ) : this(
        maybeSubject,
        options?.expectationVerb ?: expectationVerb,
        determineRepresentation(
            options?.representationInsteadOfSubject,
            maybeSubject
        ),
        DefaultComponentFactoryContainer.merge(options?.componentFactoryContainer)
    )

    constructor(previous: RootExpectImpl<T>, options: RootExpectOptions<T>) : this(
        previous.maybeSubject,
        previous.expectationVerb,
        options.copy(
            representationInsteadOfSubject = options.representationInsteadOfSubject
                ?: previous.representation?.let { r ->
                    val provider: (T) -> Any = { _ -> r }
                    provider
                },
            componentFactoryContainer = previous.components.merge(options.componentFactoryContainer)
        )
    )

    /**
     * All made assertions so far.
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<Assertion> = mutableListOf()

    override fun append(assertion: Assertion): Expect<T> {
        assertions.add(assertion)
        if (!assertion.holds()) {
            val assertionGroup = assertionBuilder.root
                .withDescriptionAndRepresentation(expectationVerb, representation)
                .withAssertions(assertions)
                .build()

            val sb = StringBuilder()
            components.build<Reporter>().format(assertionGroup, sb)

            throw AtriumError.create(sb.toString(), assertionGroup, components.build<AtriumErrorAdjuster>())
        }
        return this
    }


}
