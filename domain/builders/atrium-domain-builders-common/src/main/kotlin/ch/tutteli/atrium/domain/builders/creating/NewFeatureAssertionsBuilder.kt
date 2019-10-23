@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.newFeatureAssertions
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
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
        FeatureExtractorBuilder.create(originalAssertionContainer)


    //@formatter:off
   fun <T, TProperty> property(assertionContainer: Expect<T>, property: KProperty1<in T, TProperty>): ExtractedFeaturePostStep<T, TProperty> =
        extractFeature(assertionContainer, property.name, property::get)

    fun <T, R> f0(assertionContainer: Expect<T>, f: KFunction1<T, R>): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf()), f::invoke)

    fun <T, A1, R> f1(assertionContainer: Expect<T>, f: KFunction2<T, A1, R>, a1: A1): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf<Any?>(a1))) { f(it, a1) }

    fun <T, A1, A2, R> f2(assertionContainer: Expect<T>, f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer ,coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2))) { f(it, a1, a2) }

    fun <T, A1, A2, A3, R> f3(assertionContainer: Expect<T>, f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3))) { f(it, a1, a2, a3) }

    fun <T, A1, A2, A3, A4, R> f4(assertionContainer: Expect<T>, f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4))) { f(it, a1, a2, a3, a4) }

    fun <T, A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<T>, f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep<T, R> =
        extractFeature(assertionContainer, coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4, a5))) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    fun <T, R> manualFeature(
        assertionContainer: Expect<T>,
        name: String,
        provider: T.() -> R
    ): ExtractedFeaturePostStep<T, R> = extractFeature(assertionContainer, name, provider)

    fun <T, R> manualFeature(
        assertionContainer: Expect<T>,
        name: Translatable,
        provider: T.() -> R
    ): ExtractedFeaturePostStep<T, R> =
        genericFeature(assertionContainer, createMetaFeature(assertionContainer, name, provider))

    fun <T, R> genericSubjectBasedFeature(
        assertionContainer: Expect<T>,
        provider: (T) -> MetaFeature<R>
    ): ExtractedFeaturePostStep<T, R> = ExpectImpl.feature.genericFeature(
        assertionContainer,
        assertionContainer.maybeSubject.fold(this::createFeatureSubjectNotDefined) { provider(it) }
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
    ): ExtractedFeaturePostStep<T, R> = newFeatureAssertions.genericFeature(assertionContainer, metaFeature)

    private fun <T, R> extractFeature(
        assertionContainer: Expect<T>,
        name: String,
        provider: (T) -> R
    ): ExtractedFeaturePostStep<T, R> =
        genericFeature(assertionContainer, createMetaFeature(assertionContainer, name, provider))

    private fun <T, R> createMetaFeature(
        assertionContainer: Expect<T>,
        name: String,
        provider: (T) -> R
    ): MetaFeature<R> = createMetaFeature(assertionContainer, Untranslatable(name), provider)

    private fun <T, R> createMetaFeature(
        assertionContainer: Expect<T>,
        name: Translatable,
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

    /**
     * Returns [MetaFeatureBuilder] which helps to create a [MetaFeature].
     */
    inline val meta get() = MetaFeatureBuilder
}

/**
 * Helper class to circumvent overload bugs and KFunction bugs incorporated in Kotlin -- use [f] and in case you run
 * into an overload ambiguity, then either [p] (for property) or one of the `fN` functions (e.g. [f2] for
 * a function which expects 2 arguments).
 */
class MetaFeatureOption<T>(private val expect: Expect<T>) {

    /**
     * Creates a [MetaFeature] for the given [property] => use [p] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(property: KProperty0<R>): MetaFeature<R> = p(property)

    //@formatter:off
    /**
     * Creates a [MetaFeature] for the given function [f] without arguments => use [f0] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(f: KFunction0<R>): MetaFeature<R> =
        f0(f)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument => use [f1] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        f1(f, a1)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments => use [f2] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        f2(f, a1, a2)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments => use [f3] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        f3(f, a1, a2, a3)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments => use [f4] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        f4(f, a1, a2, a3, a4)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments => use [f5] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, A5, R> f(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        f5(f, a1, a2, a3, a4, a5)

    //used to distinguish property/functions

    /**
     * Creates a [MetaFeature] for the given property [property].
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> p(property: KProperty0<R>): MetaFeature<R> =
        ExpectImpl.feature.meta.property(property)

    /**
     * Creates a [MetaFeature] for the given function [f] without arguments.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f0(f: KFunction0<R>): MetaFeature<R> =
        ExpectImpl.feature.meta.f0(expect, f)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f1(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        ExpectImpl.feature.meta.f1(expect, f, a1)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f2(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        ExpectImpl.feature.meta.f2(expect, f, a1, a2)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f3(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        ExpectImpl.feature.meta.f3(expect, f, a1, a2, a3)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f4(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        ExpectImpl.feature.meta.f4(expect, f, a1, a2, a3, a4)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments.
     *
     * Notice for assertion function writers: you should use [ExpectImpl].[feature][ExpectImpl.feature] and pass a
     * class reference instead of using this convenience function (e.g. `ExpectImpl.feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
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
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf()), f.invoke())

    fun <A1, R> f1(assertionContainer: Expect<*>, f: KFunction1<A1, R>, a1: A1) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf<Any?>(a1)), f.invoke(a1))

    fun <A1, A2, R> f2(assertionContainer: Expect<*>, f: KFunction2<A1, A2, R>, a1: A1, a2: A2) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2)), f.invoke(a1, a2))

    fun <A1, A2, A3, R> f3(assertionContainer: Expect<*>, f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3)), f.invoke(a1, a2, a3))

    fun <A1, A2, A3, A4, R> f4(assertionContainer: Expect<*>, f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4)), f.invoke(a1, a2, a3, a4))

    fun <A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<*>, f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4, a5)), f.invoke(a1, a2, a3, a4, a5))
    //@formatter:on
}
