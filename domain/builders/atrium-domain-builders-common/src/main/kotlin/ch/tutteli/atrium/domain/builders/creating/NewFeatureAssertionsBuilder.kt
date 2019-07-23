@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.domain.creating.newFeatureAssertions
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.translations.ErrorMessages
import kotlin.reflect.*


/**
 * Delegates inter alia to the implementation of [NewFeatureAssertions].
 * In detail, it implements [NewFeatureAssertions] by delegating to [newFeatureAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 *
 * Will be renamed to FeatureAssertionsBuilder with 1.0.0
 */
object NewFeatureAssertionsBuilder : NewFeatureAssertions {

    /**
     *  Start a feature extraction with the help of the [FeatureExtractorBuilder].
     *
     * In case you do not want to extract a feature (e.g. get the first element of a `List`)
     * but merely want to transform the subject into another representation
     * (e.g. down-cast `Person` to `Student` or transform a `Sequence` into a `List`) then you should use
     * [ExpectImpl.changeSubject] instead.
     *
     * Also, if the extraction of the feature is always safe, then you can just use one of the fN functions
     * (e.g. [f1] for a function expecting 1 argument) or [property].
     */
    inline fun <T> extractor(originalAssertionContainer: Expect<T>) =
        FeatureExtractorBuilder.builder(originalAssertionContainer)


    //@formatter:off
    fun <T, TProperty> property(assertionContainer: Expect<T>, property: KProperty1<in T, TProperty>): Expect<TProperty> =
        extractProperty(assertionContainer, property).withoutSubAssertions()

    fun <T, TProperty> property(assertionContainer: Expect<T>, property: KProperty1<in T, TProperty>, assertionCreator: Expect<TProperty>.() -> Unit): Assertion =
        extractProperty(assertionContainer, property).withAssertions(assertionCreator)

    private inline fun <T, TProperty> extractProperty(assertionContainer: Expect<T>, property: KProperty1<in T, TProperty>): FeatureCall<T, TProperty> =
        FeatureCall(assertionContainer, property.name, property::get)


    fun <T, R> f0(assertionContainer: Expect<T>, f: KFunction1<T, R>): Expect<R> =
        extractF0(assertionContainer, f).withoutSubAssertions()

    fun <T, R> f0(assertionContainer: Expect<T>, f: KFunction1<T, R>, assertionCreator: Expect<R>.() -> Unit): Assertion =
        extractF0(assertionContainer, f).withAssertions(assertionCreator)

    private inline fun <T, R> extractF0(assertionContainer: Expect<T>, f: KFunction1<T, R>): FeatureCall<T, R> =
        FeatureCall(assertionContainer, f.name + "()", f::invoke)


    fun <T, A1, R> f1(assertionContainer: Expect<T>, f: KFunction2<T, A1, R>, a1: A1): Expect<R> =
        extractF1(assertionContainer, f, a1).withoutSubAssertions()

    fun <T, A1, R> f1(assertionContainer: Expect<T>, f: KFunction2<T, A1, R>, a1: A1, assertionCreator: Expect<R>.() -> Unit): Assertion
        = extractF1(assertionContainer, f, a1).withAssertions(assertionCreator)

    private inline fun <T, A1, R> extractF1(assertionContainer: Expect<T>, f: KFunction2<T, A1, R>, a1: A1): FeatureCall<T, R> =
        FeatureCall(assertionContainer, coreFactory.newMethodCallFormatter().format(f.name, arrayOf<Any?>(a1))()) { f(it, a1) }


    fun <T, A1, A2, R> f2(assertionContainer: Expect<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): Expect<R> =
        extractF2(assertionContainer, f, a1, a2).withoutSubAssertions()

    fun <T, A1, A2, R> f2(assertionContainer: Expect<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2, assertionCreator: Expect<R>.() -> Unit): Assertion
        = extractF2(assertionContainer, f, a1, a2).withAssertions(assertionCreator)

    private inline fun <T, A1, A2, R> extractF2(assertionContainer: Expect<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): FeatureCall<T, R> =
        FeatureCall(assertionContainer ,coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2))()) { f(it, a1, a2) }


    fun <T, A1, A2, A3, R> f3(assertionContainer: Expect<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): Expect<R> =
        extractF3(assertionContainer, f, a1, a2, a3).withoutSubAssertions()

    fun <T, A1, A2, A3, R> f3(assertionContainer: Expect<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3, assertionCreator: Expect<R>.() -> Unit): Assertion
        = extractF3(assertionContainer, f, a1, a2, a3).withAssertions(assertionCreator)

    private inline fun <T, A1, A2, A3, R> extractF3(assertionContainer: Expect<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): FeatureCall<T, R> =
        FeatureCall(assertionContainer, coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3))()) { f(it, a1, a2, a3) }


    fun <T, A1, A2, A3, A4, R> f4(assertionContainer: Expect<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): Expect<R> =
        extractF4(assertionContainer, f, a1, a2, a3, a4).withoutSubAssertions()

    fun <T, A1, A2, A3, A4, R> f4(assertionContainer: Expect<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4, assertionCreator: Expect<R>.() -> Unit): Assertion
        = extractF4(assertionContainer, f, a1, a2, a3, a4).withAssertions(assertionCreator)

    private inline fun <T, A1, A2, A3, A4, R> extractF4(assertionContainer: Expect<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): FeatureCall<T, R> =
        FeatureCall(assertionContainer, coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3, a4))()) { f(it, a1, a2, a3, a4) }


    fun <T, A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): Expect<R> =
        extractF5(assertionContainer, f, a1, a2, a3, a4, a5).withoutSubAssertions()

    fun <T, A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, assertionCreator: Expect<R>.() -> Unit): Assertion
        = extractF5(assertionContainer, f, a1, a2, a3, a4, a5).withAssertions(assertionCreator)

    private inline fun <T, A1, A2, A3, A4, A5, R> extractF5(assertionContainer: Expect<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): FeatureCall<T, R> =
        FeatureCall(assertionContainer, coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3, a4, a5))()) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    fun <T, R> manualFeature(
        assertionContainer: Expect<T>,
        name: String,
        provider: T.() -> R
    ): Expect<R> = FeatureCall(assertionContainer, name, provider).withoutSubAssertions()

    fun <T, R> manualFeature(
        assertionContainer: Expect<T>,
        name: String,
        provider: T.() -> R,
        assertionCreator: Expect<R>.() -> Unit
    ): Assertion = FeatureCall(assertionContainer, name, provider).withAssertions(assertionCreator)

    fun <T, R> genericSubjectBasedFeature(
        assertionContainer: Expect<T>,
        provider: (T) -> MetaFeature<R>
    ): Expect<R> = ExpectImpl.feature.genericFeature(
        assertionContainer,
        assertionContainer.maybeSubject.fold(this::createFeatureSubjectNotDefined) { provider(it) }
    )

    fun <T, R> genericSubjectBasedFeature(
        assertionContainer: Expect<T>,
        provider: (T) -> MetaFeature<R>,
        assertionCreator: Expect<R>.() -> Unit
    ): Assertion = ExpectImpl.feature.genericFeature(
        assertionContainer,
        assertionContainer.maybeSubject.fold(this::createFeatureSubjectNotDefined) { provider(it) },
        assertionCreator
    )

    private fun <R> createFeatureSubjectNotDefined(): MetaFeature<R> =
        MetaFeature(
            ErrorMessages.DEDSCRIPTION_BASED_ON_SUBJECT,
            RawString.create(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED),
            None
        )

    override inline fun <T, R> genericFeature(
        assertionContainer: Expect<T>,
        metaFeature: MetaFeature<R>
    ): Expect<R> = newFeatureAssertions.genericFeature(assertionContainer, metaFeature)

    override inline fun <T, R> genericFeature(
        assertionContainer: Expect<T>,
        metaFeature: MetaFeature<R>,
        noinline assertionCreator: Expect<R>.() -> Unit
    ): Assertion = newFeatureAssertions.genericFeature(assertionContainer, metaFeature, assertionCreator)


    private data class FeatureCall<T, R>(
        val assertionContainer: Expect<T>,
        val name: String,
        val provider: (T) -> R
    ) {
        fun withoutSubAssertions(): Expect<R> =
            genericFeature(assertionContainer, createFeature(assertionContainer, name, provider))

        fun withAssertions(assertionCreator: Expect<R>.() -> Unit): Assertion =
            genericFeature(assertionContainer, createFeature(assertionContainer, name, provider), assertionCreator)

        private fun <T, R> createFeature(
            assertionContainer: Expect<T>,
            name: String,
            provider: (T) -> R
        ): MetaFeature<R> {
            return assertionContainer.maybeSubject.fold({
                MetaFeature(
                    name,
                    RawString.create(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED),
                    None
                )
            }) {
                val prop = provider(it)
                MetaFeature(name, prop, Some(prop))
            }
        }
    }

    /**
     * Returns [MetaFeatureBuilder] which allows helps to create a [MetaFeature].
     */
    inline val meta get() = MetaFeatureBuilder
}

/**
 * Helper class to circumvent overload bugs and KFunction bugs incorporated in Kotlin -- use [f] and in case you run
 * into an overload ambiguity, then either [p] (for property) or one of the `fN` functions (e.g. [f2] a function which
 * expects 2 arguments).
 */
class MetaFeatureOption<T>(private val expect: Expect<T>) {

    /**
     * Use the overload which expects a [KProperty1] in case you define it in an assertion function where it is fine to
     * be a bit more verbose. This way we are always able to report the property, even if the subject is not defined (which
     * means a previous transformation of the subject could not be carried out).
     */
    fun <R> f(p: KProperty0<R>): MetaFeature<R> = p(p)

    //@formatter:off
    fun <R> f(f: KFunction0<R>): MetaFeature<R> =
        f0(f)

    fun <A1, R> f(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        f1(f, a1)

    fun <A1, A2, R> f(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        f2(f, a1, a2)

    fun <A1, A2, A3, R> f(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        f3(f, a1, a2, a3)

    fun <A1, A2, A3, A4, R> f(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        f4(f, a1, a2, a3, a4)

    fun <A1, A2, A3, A4, A5, R> f(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        f5(f, a1, a2, a3, a4, a5)

    //used to distinguish property/functions

    fun <R> p(p: KProperty0<R>): MetaFeature<R> =
        ExpectImpl.feature.meta.property(p)

    fun <R> f0(f: KFunction0<R>): MetaFeature<R> =
        ExpectImpl.feature.meta.f0(expect, f)

    fun <A1, R> f1(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        ExpectImpl.feature.meta.f1(expect, f, a1)

    fun <A1, A2, R> f2(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        ExpectImpl.feature.meta.f2(expect, f, a1, a2)

    fun <A1, A2, A3, R> f3(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        ExpectImpl.feature.meta.f3(expect, f, a1, a2, a3)

    fun <A1, A2, A3, A4, R> f4(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        ExpectImpl.feature.meta.f4(expect, f, a1, a2, a3, a4)

    fun <A1, A2, A3, A4, A5, R> f5(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        ExpectImpl.feature.meta.f5(expect, f, a1, a2, a3, a4, a5)

    //@formatter:on
}

/**
 * Builder which helps to create [MetaFeature] inter alia by using the [MethodCallFormatter] defined initially by the
 * [Reporter]
 */
@Suppress("UNUSED_PARAMETER" /* we will need it as soon as methodCallFormatter is taken from the specified Reporter */)
object MetaFeatureBuilder {

    fun <TProperty> property(property: KProperty0<TProperty>) = MetaFeature(property.name, property.invoke())

    //@formatter:off
    fun <R> f0(assertionContainer: Expect<*>, f: KFunction0<R>) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf())(), f.invoke())

    fun <A1, R> f1(assertionContainer: Expect<*>, f: KFunction1<A1, R>, a1: A1) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf<Any?>(a1)).invoke(), f.invoke(a1))

    fun <A1, A2, R> f2(assertionContainer: Expect<*>, f: KFunction2<A1, A2, R>, a1: A1, a2: A2) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2)).invoke(), f.invoke(a1, a2))

    fun <A1, A2, A3, R> f3(assertionContainer: Expect<*>, f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3)).invoke(), f.invoke(a1, a2, a3))

    fun <A1, A2, A3, A4, R> f4(assertionContainer: Expect<*>, f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3, a4)).invoke(), f.invoke(a1, a2, a3, a4))

    fun <A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<*>, f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5) =
        MetaFeature(coreFactory.newMethodCallFormatter().format(f.name, arrayOf(a1, a2, a3, a4, a5)).invoke(), f.invoke(a1, a2, a3, a4, a5))
    //@formatter:on
}
