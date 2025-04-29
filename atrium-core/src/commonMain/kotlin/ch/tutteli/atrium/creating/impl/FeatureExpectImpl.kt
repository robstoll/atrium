package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.InlineElement

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class FeatureExpectImpl<T, R>(
    previousExpect: Expect<T>,
    maybeSubject: Option<R>,
    private val description: InlineElement,
    private val representation: Any?,
    proofs: List<Proof>
) : BaseExpectImpl<R>(maybeSubject), FeatureExpect<T, R> {

    private val previousProofContainer = previousExpect.toProofContainer()

    constructor(
        previousExpect: Expect<T>,
        maybeSubject: Option<R>,
        description: InlineElement,
        proofs: List<Proof>,
        options: FeatureExpectOptions<R>
    ) : this(
        previousExpect,
        maybeSubject,
        options.description ?: description,
        //TODO 1.4.0 this is smelly, determineRepresentation was written for RootExpect, is subject really always
        // defined at that place?
        determineRepresentation(
            options.representationInsteadOfFeature,
            maybeSubject
        ),
        proofs
    )

    constructor(previous: FeatureExpectImpl<T, R>, options: FeatureExpectOptions<R>) : this(
        previous.previousProofContainer.toExpect(),
        previous.maybeSubject,
        previous.description,
        previous.proofs,
        options
    )

    /**
     * All appended reportables so far.
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val proofs: MutableList<Proof> = mutableListOf()

    init {
        appendAsGroup(proofs)
    }

    override val components: ComponentFactoryContainer
        get() = previousProofContainer.components

    override fun append(proof: Proof): Expect<R> {
        proofs.add(proof)
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
        previousProofContainer.append(
            Proof.featureGroup(description, representation, proofs.toList())
        )
        proofs.clear()
        return this
    }
}
