package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Reportable
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
    override fun append(assertion: Assertion): Expect<T> = append(assertion as Proof)

    //TODO rename param to expectationCreator or remove with 2.0.0 at the latest
    @Suppress("DEPRECATION")
    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        replaceWith = ReplaceWith("this.appendAsGroupIndicateIfOneCollected(assertionCreator, listOf(/* .. add a usage hint in case you have an overload which does not expect an expectationCreator */))")
    )
    override fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val proofs = CollectingExpect(maybeSubject, components)
            .appendAsGroup(assertionCreator)
            .getCollectedProofs()
        return appendAsGroup(proofs)
    }

    override fun appendAsGroupIndicateIfNoneCollected(
        expectationCreator: Expect<T>.() -> Unit,
        usageHintsOverloadWithoutExpectationCreator: List<Reportable>
    ): Pair<Expect<T>, Boolean> =
        CollectingExpect(maybeSubject, components)
            .appendAsGroupIndicateIfOneCollected(expectationCreator, usageHintsOverloadWithoutExpectationCreator)
            .let { (collectingExpect, expectationDefined) ->
                appendAsGroup(collectingExpect.getCollectedProofs())
                this to expectationDefined
            }

    protected fun appendAsGroup(proofs: List<Proof>): Expect<T> {
        return when (proofs.size) {
            0 -> this
            1 -> append(proofs.first())
            else -> append(Proof.invisibleGroup(proofs))
        }
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
