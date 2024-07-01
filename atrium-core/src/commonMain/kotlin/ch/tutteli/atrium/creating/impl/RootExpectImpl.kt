package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reportables.InlineElement

@ExperimentalNewExpectTypes
@OptIn(ExperimentalComponentFactoryContainer::class)
internal class RootExpectImpl<T>(
    maybeSubject: Option<T>,
    private val expectationVerb: InlineElement,
    private val representation: Any?,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), RootExpect<T> {

    constructor(
        maybeSubject: Option<T>,
        expectationVerb: InlineElement,
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
    private val proofs: MutableList<Proof> = mutableListOf()

    @Suppress("DEPRECATION")
    override fun append(assertion: ch.tutteli.atrium.assertions.Assertion): Expect<T> = append(assertion as Proof)

    override fun append(proof: Proof): Expect<T> {
        proofs.add(proof)
        if (!proof.holds()) {
            val root = Proof.rootGroup(expectationVerb, representation, proofs)

            val sb = StringBuilder()

            //TODO 1.3.0 this fails at runtime as root is not an Assertion
            // switch to new proof based reporter and remove suppress
            @Suppress("DEPRECATION")
            components.build<Reporter>().format(root as ch.tutteli.atrium.assertions.Assertion, sb)

            throw AtriumError.create(sb.toString(), components.build<AtriumErrorAdjuster>())
        }
        return this
    }


}
