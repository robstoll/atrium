package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.ErrorMessages
import kotlin.reflect.KClass

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
abstract class BaseExpectImpl<T>(
    override val maybeSubject: Option<T>
) : ExpectInternal<T> {

    // TODO 1.3.0 not every expect should have an own implFactories but only the root,
    // maybe also FeatureExpect but surely not DelegatingExpect or CollectingExpect
    private val implFactories: MutableMap<KClass<*>, (() -> Nothing) -> () -> Any> = mutableMapOf()

    override fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I {
        @Suppress("UNCHECKED_CAST")
        val implFactory = implFactories[kClass] as ((() -> I) -> () -> Any)?
        return if (implFactory != null) {
            val impl = implFactory(defaultFactory)()
            kClass.cast(impl)
        } else defaultFactory()
    }

    @PublishedApi
    internal fun <I : Any> registerImpl(kClass: KClass<I>, implFactory: (oldFactory: () -> I) -> () -> I) {
        implFactories[kClass] = implFactory
    }

    //TODO 1.3.0 move to RootExpectOptions?
    inline fun <reified I : Any> withImplFactory(noinline implFactory: (oldFactory: () -> I) -> () -> I) {
        registerImpl(I::class, implFactory)
    }

    //TODO remove with 2.0.0 at the latest
    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun append(assertion: ch.tutteli.atrium.assertions.Assertion): Expect<T> =
        append(assertion as Proof)

    //TODO rename param to expectationCreator or remove with 2.0.0 at the latest
    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        ReplaceWith(
            "this.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints<T>(assertionCreator, listOf(/* .. add a custom usage hint in case you have an overload which does not expect an expectationCreator or use the generic */ ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED))).first",
            "ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints",
            "ch.tutteli.atrium.reporting.reportables.ErrorMessages"
        )
    )
    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): Expect<T> =
        appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsOverloadWithoutExpectationCreator = listOf(
                    ErrorMessages.DEFAULT_HINT_AT_LEAST_ONE_EXPECTATION_DEFINED
                ),
                assertionCreator,
            )
        ).first

    override fun appendAsGroupIndicateIfOneCollected(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<T>
    ): Pair<Expect<T>, Boolean> =
        CollectingExpect(maybeSubject, components)
            .appendAsGroupIndicateIfOneCollected(expectationCreatorWithUsageHints)
            .let { (collectingExpect, expectationDefined) ->
                appendAsGroup(collectingExpect.getCollectedProofs()) to expectationDefined
            }

    protected fun appendAsGroup(proofs: List<Proof>): Expect<T> =
        when (proofs.size) {
            0 -> this
            else -> append(Proof.invisibleGroupOrSingleChildIfProof(proofs))
        }

    companion object {
        @ExperimentalNewExpectTypes
        fun <R> determineRepresentation(representationInsteadOfFeature: ((R) -> Any)?, maybeSubject: Option<R>): Any? =
            representationInsteadOfFeature?.let { provider ->
                maybeSubject.fold({ null }) { provider(it) }
            } ?: maybeSubject.getOrElse {
                // a RootExpect without a defined subject is almost certainly a bug
                Text(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
            }
    }
}
