package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ErrorMessages

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
internal class CollectingExpectImpl<T>(
    maybeSubject: Option<T>,
    override val components: ComponentFactoryContainer
) : BaseExpectImpl<T>(maybeSubject), CollectingExpect<T> {
    private val proofs = mutableListOf<Proof>()

    @Deprecated("Assertion is deprecated, move to Proof", replaceWith = ReplaceWith("this.getCollectedProofs()"))
    override fun getAssertions(): List<Assertion> = proofs.filterIsInstance<Assertion>()

    override fun getCollectedProofs(): List<Proof> = proofs

    override fun append(proof: Proof): Expect<T> = apply { proofs.add(proof) }

    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T> =
        appendAsGroupIndicateIfOneCollected(
            assertionCreator,
            usageHintsOverloadWithoutExpectationCreator = listOf(
                ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED
            )
        ).first

    override fun appendAsGroupIndicateIfOneCollected(
        expectationCreator: Expect<T>.() -> Unit,
        usageHintsOverloadWithoutExpectationCreator: List<Reportable>
    ): Pair<CollectingExpect<T>, Boolean> {
        // in case we run into performance problems, the code below is certainly not ideal
        val allProofs = mutableListOf<Proof>()
        allProofs.addAll(getCollectedProofs())
        proofs.clear()

        //TODO 1.3.0 collect unexpected exceptions, move DefaultExceptoin
        expectationCreator(this)
        val newProofs = getCollectedProofs()
        val noneCollected = newProofs.isNotEmpty()
        proofs.clear()

        if (noneCollected) {
            allProofs.addAll(newProofs)
        } else {
            allProofs.add(
                Proof.fixedClaimGroup(
                    ErrorMessages.AT_LEAST_ONE_EXPECTATION_DEFINED, false,
                    listOf(Proof.simple(Text("to equal"), true, falseProvider)) +
                        usageHintsOverloadWithoutExpectationCreator.ifNotEmpty {
                            listOf(
                                Reportable.usageHintGroup(
                                    listOf(ErrorMessages.FORGOT_DO_DEFINE_EXPECTATION) +
                                        it
                                )
                            )
                        },
                    holds = false,
                ),
            )
        }
        allProofs.forEach { append(it) }

        return Pair(this, noneCollected)
    }
}

//TODO 1.3.0 use method from KBox
@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
inline fun <CollectionT, SuperTypeOfCollectionT> CollectionT.ifNotEmpty(
    transformation: (CollectionT) -> SuperTypeOfCollectionT
): SuperTypeOfCollectionT where CollectionT : Collection<*>,
                                SuperTypeOfCollectionT : Iterable<*>,
                                CollectionT : SuperTypeOfCollectionT {
    return if (this.isNotEmpty()) transformation(this) else this
}
