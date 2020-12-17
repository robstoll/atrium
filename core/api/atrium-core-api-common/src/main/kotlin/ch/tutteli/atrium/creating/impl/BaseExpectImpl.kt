package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.Text
import kotlin.reflect.KClass

@ExperimentalNewExpectTypes
abstract class BaseExpectImpl<T>(
    override val maybeSubject: Option<T>
) : ExpectInternal<T> {

    // TODO 0.16.0 not every expect should have an own implFactories but only the root,
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

    //TODO 0.16.0 move to ExpectOptions
    inline fun <reified I : Any> withImplFactory(noinline implFactory: (oldFactory: () -> I) -> () -> I) {
        registerImpl(I::class, implFactory)
    }


    //TODO remove with 0.16.0
    @Deprecated(
        "Do not access subject as it might break reporting. In contexts where it is safe to access the subject, it is passed by parameter and can be accessed via `it`. See KDoc for migration hints; will be removed with 1.0.0",
        ReplaceWith("it")
    )
    final override val subject: T by lazy {
        maybeSubject.getOrElse {
            @Suppress("DEPRECATION")
            throw PlantHasNoSubjectException()
        }
    }

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val assertions = CollectingExpect(maybeSubject)
            .addAssertionsCreatedBy(assertionCreator)
            .getAssertions()
        return addAssertions(assertions)
    }

    protected fun addAssertions(assertions: List<Assertion>): Expect<T> {
        return when (assertions.size) {
            0 -> this
            1 -> addAssertion(assertions.first())
            else -> addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
        }
    }

    companion object {
        @ExperimentalNewExpectTypes
        fun <R> determineRepresentation(representationInsteadOfFeature: ((R) -> Any)?, maybeSubject: Option<R>): Any? =
            representationInsteadOfFeature?.let { provider ->
                maybeSubject.fold({ null }) { provider(it) }
            } ?: maybeSubject.getOrElse {
                // a RootExpect without a defined subject is almost certain a bug
                Text(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
            }
    }
}
