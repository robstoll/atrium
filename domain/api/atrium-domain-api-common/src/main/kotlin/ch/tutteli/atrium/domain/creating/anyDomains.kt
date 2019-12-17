package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.ErrorMessages
import kotlin.reflect.*


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any]?,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomain<T> : EqualityDomain<T>, FeatureDomain<T>

/**
 * Represents the topic equality/identity of the [AnyDomain].
 *
 * Note though, that `toBe` is defined in [EqualityNonNullableDomain.toBe] and [EqualityOnlyNullableDomain.toBeNullable].
 */
interface EqualityDomain<T> : ExpectDomain<T> {

    fun notToBe(expected: T): Assertion
    fun isSame(expected: T): Assertion
    fun isNotSame(expected: T): Assertion

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
    fun <TSub : Any> isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub>
}

/**
 * Represents the topic feature of the [AnyDomain].
 */
interface FeatureDomain<T> : ExpectDomain<T> {

    //@formatter:off
   fun <TProperty> property(property: KProperty1<in T, TProperty>): ExtractedFeaturePostStep<T, TProperty> =
        extractFeature(property.name, property::get)

    fun <R> f0(f: KFunction1<T, R>): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf()), f::invoke)

    fun <A1, R> f1(f: KFunction2<T, A1, R>, a1: A1): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf<Any?>(a1))) { f(it, a1) }

    fun <A1, A2, R> f2(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2))) { f(it, a1, a2) }

    fun <A1, A2, A3, R> f3(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3))) { f(it, a1, a2, a3) }

    fun <A1, A2, A3, A4, R> f4(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4))) { f(it, a1, a2, a3, a4) }

    fun <A1, A2, A3, A4, A5, R> f5(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep<T, R> =
        extractFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4, a5))) { f(it, a1, a2, a3, a4, a5) }
    //@formatter:on

    fun <R> manualFeature(description: String, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
        extractFeature(description, provider)

    fun <R> manualFeature(description: Translatable, provider: T.() -> R): ExtractedFeaturePostStep<T, R> =
        genericFeature(createMetaFeature(description, provider))

    fun <R> genericSubjectBasedFeature(
        provider: (T) -> MetaFeature<R>
    ): ExtractedFeaturePostStep<T, R> = genericFeature(
        expect.maybeSubject.fold(this::createFeatureSubjectNotDefined) { provider(it) }
    )

    private fun <R> createFeatureSubjectNotDefined(): MetaFeature<R> =
        MetaFeature(
            ErrorMessages.DEDSCRIPTION_BASED_ON_SUBJECT,
            RawString.create(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED),
            None
        )

    private fun <R> extractFeature(description: String, provider: (T) -> R): ExtractedFeaturePostStep<T, R> =
        genericFeature(createMetaFeature(description, provider))

    private fun <R> createMetaFeature(description: String, provider: (T) -> R): MetaFeature<R> =
        createMetaFeature(Untranslatable(description), provider)

    private fun <R> createMetaFeature(description: Translatable, provider: (T) -> R): MetaFeature<R> =
        expect.maybeSubject.fold({
            MetaFeature(
                description,
                RawString.create(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED),
                None
            )
        }) {
            val prop = provider(it)
            MetaFeature(description, prop, Some(prop))
        }

    fun <R> genericFeature(metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R>
}



/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any],
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomainNonNullable<T : Any> : AnyDomain<T>, EqualityNonNullableDomain<T>

/**
 * Represents the topic equality of the [AnyDomainNonNullable].
 *
 * Interface segregation for better extensibility (less methods, smaller classes).
 */
interface EqualityNonNullableDomain<T : Any> : ExpectDomain<T> {

    fun toBe(expected: T): Assertion
}



/**
 * Defines the minimum set of assertion functions and builders applicable to nullable types extending [Any]?,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomainOnlyNullable<T : Any> : EqualityOnlyNullableDomain<T> {

    fun toBeNullIfNullGivenElse(type: KClass<T>, assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion
}

/**
 * Represents the topic equality of the [AnyDomainOnlyNullable].
 *
 * Interface segregation for better extensibility (less methods, smaller classes).
 */
interface EqualityOnlyNullableDomain<T : Any> : ExpectDomain<T?> {

    fun toBeNull(): Assertion

    fun toBeNullable(type: KClass<T>, expectedOrNull: T?): Assertion
}
