package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Sole purpose of this interface is to hide [AssertionContainer] from newcomers which usually don't have to deal with
 * this.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface ExpectInternal<T>: Expect<T>, AssertionContainer<T>

abstract class BaseExpectImpl<T>(
    override val maybeSubject: Option<T>
) : ExpectInternal<T> {

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

    //TODO move to ExpectOptions
    inline fun <reified I : Any> withImplFactory(noinline implFactory: (oldFactory: () -> I) -> () -> I) {
        registerImpl(I::class, implFactory)
    }


    @Deprecated(
        "Do not access subject as it might break reporting. In contexts where it is safe to access the subject, it is passed by parameter and can be accessed via `it`. See KDoc for migration hints; will be removed with 1.0.0",
        ReplaceWith("it")
    )
    final override val subject: T by lazy {
        maybeSubject.getOrElse {
            @Suppress("DEPRECATION")
            throw ch.tutteli.atrium.creating.PlantHasNoSubjectException()
        }
    }

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T> {
        val assertions = coreFactory.newCollectingAssertionContainer(maybeSubject)
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
        fun <R> determineRepresentation(representationInsteadOfFeature: ((R) -> Any)?, maybeSubject: Option<R>) =
            representationInsteadOfFeature?.let { provider ->
                maybeSubject.fold({ null }) { provider(it) }
            } ?: maybeSubject.getOrElse {
                // a RootExpect without a defined subject is almost certain a bug
                Text(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
            }
    }
}

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

@ExperimentalNewExpectTypes
internal class FeatureExpectImpl<T, R>(
    private val previousExpect: Expect<T>,
    maybeSubject: Option<R>,
    private val description: Translatable,
    private val representation: Any?,
    assertions: List<Assertion>
) : BaseExpectImpl<R>(maybeSubject), FeatureExpect<T, R> {

    constructor(
        previousExpect: Expect<T>,
        maybeSubject: Option<R>,
        description: Translatable,
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
        addAssertions(assertions)
    }

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalExpectConfig::class)
    override fun addAssertion(assertion: Assertion): Expect<R> {
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
        //for CollectingAssertionContainer
        previousExpect.addAssertion(
            assertionBuilder.feature
                .withDescriptionAndRepresentation(description, representation)
                .withAssertions(ArrayList(assertions))
                .build()
        )
        assertions.clear()
        return this
    }
}

internal class DelegatingExpectImpl<T>(private val assertionHolder: AssertionHolder, maybeSubject: Option<T>) :
    BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {
    override fun addAssertion(assertion: Assertion): Expect<T> {
        assertionHolder.addAssertion(assertion)
        return this
    }
}
