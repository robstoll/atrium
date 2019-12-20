@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.*
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.creating.collectors.DelegateToAssertionCollector
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.AnyDomainNonNullableImpl
import ch.tutteli.atrium.domain.creating.impl.AnyDomainOnlyNullableImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.ErrorMessages
import kotlin.reflect.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Any]`?`.
 */
val <T> Expect<T>._domain: AnyDomain<T> get() = AnyDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Any].
 */
val <T : Any> Expect<T>._domain: AnyDomainNonNullable<T> get() = AnyDomainNonNullableImpl(this, AnyDomainImpl(this))

// we cannot use the same name since:
// - Kotlin has a bug in JS and the same mangled identifier results as for <T: Any> above
// - isA would choose this overload instead of <T>
/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of nullable types extending [Any]?.
 */
val <T : Any> Expect<T?>._domainNullable: AnyDomainOnlyNullable<T> get() = AnyDomainOnlyNullableImpl(this)


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any]?,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomain<T> : EqualityDomain<T>, FeatureDomain<T>, BuilderDomain<T>

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
 *
 * @since 0.9.0
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
        createFeatureSubjectNotDefined(ErrorMessages.DEDSCRIPTION_BASED_ON_SUBJECT)

    private fun <R> createFeatureSubjectNotDefined(description: Translatable): MetaFeature<R> =
        MetaFeature(description, RawString.create(ErrorMessages.REPRESENTATION_BASED_ON_SUBJECT_NOT_DEFINED), None)

    private fun <R> extractFeature(description: String, provider: (T) -> R): ExtractedFeaturePostStep<T, R> =
        genericFeature(createMetaFeature(description, provider))

    private fun <R> createMetaFeature(description: String, provider: (T) -> R): MetaFeature<R> =
        createMetaFeature(Untranslatable(description), provider)

    private fun <R> createMetaFeature(description: Translatable, provider: (T) -> R): MetaFeature<R> =
        expect.maybeSubject.fold(
            { createFeatureSubjectNotDefined(description) },
            { provider(it).let { prop -> MetaFeature(description, prop, Some(prop)) } }
        )

    fun <R> genericFeature(metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R>
}

/**
 * Represents an extracted feature of type [T] defined by the given [maybeSubject] including a [description]
 * and a [representation].
 *
 * @property description Will be used in reporting to describe the feature extraction - e.g. the name of a property,
 *   a method call etc.
 * @property representation The representation of the feature, in most cases the value behind the feature.
 * @property maybeSubject The feature as such where it is [Some] in case the extraction was successful or [None] if it
 *   was not.
 *
 * @since 0.9.0
 */
data class MetaFeature<T>(val description: Translatable, val representation: Any?, val maybeSubject: Option<T>) {
    constructor(description: String, representation: Any?, maybeSubject: Option<T>) :
        this(Untranslatable(description), representation, maybeSubject)

    constructor(description: String, subject: T) : this(description, subject, Some(subject))
}


/**
 * Represents the topic builders of the [AnyDomain].
 *
 * @since 0.9.0
 */
interface BuilderDomain<T> : ExpectDomain<T> {

    /**
     * Returns [SubjectChangerBuilder] - helping you to change the subject of the assertion.
     *
     * In case you want to extract a feature (e.g. get the first element of a `List`) instead of changing the subject
     * into another representation (e.g. down-cast `Person` to `Student`) then you should use
     * [featureExtractor] instead.
     */
    val changeSubject: SubjectChangerBuilder.KindStep<T>
        get() = SubjectChangerBuilder.create(expect)


    /**
     * Starts a feature extraction process with the help of the [FeatureExtractorBuilder].
     *
     * In case you do not want to extract a feature (e.g. get the first element of a `List`)
     * but merely want to transform the subject into another representation
     * (e.g. down-cast `Person` to `Student` or transform a `Sequence` into a `List`) then you should use
     * [changeSubject] instead.
     *
     * Also, if the extraction of the feature is always safe, then you can just use one of the `fN` functions
     * (e.g. [FeatureDomain.f1] for a function expecting 1 argument) or [FeatureDomain.property].
     */
    val featureExtractor: FeatureExtractorBuilder.DescriptionStep<T>
        get() = FeatureExtractorBuilder.create(expect)


    /**
     * Returns [DelegateToAssertionCollector] - helping you to collect feature assertions.
     *
     * [DelegateToAssertionCollector] mainly holds a reference to the [Expect] for which this domain was built
     * so that you do not have to pass it to [AssertionCollector] yourself.
     * In case you want to operate on arbitrary subjects, then use [assertionCollector] directly.
     */
    val collector: DelegateToAssertionCollector<T>
        get() = DelegateToAssertionCollector(expect)

}


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any],
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomainNonNullable<T : Any> : AnyDomain<T>, EqualityNonNullableDomain<T>

/**
 * Represents the topic equality of the [AnyDomainNonNullable].
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

    /**
     * Convenience method for nullable-types which delegates to [AnyDomain.isA].
     */
    fun notToBeNull(subType: KClass<T>) = expect._domain.isA(subType)
}

/**
 * Represents the topic equality of the [AnyDomainOnlyNullable].
 */
interface EqualityOnlyNullableDomain<T : Any> : ExpectDomain<T?> {

    fun toBeNull(): Assertion

    fun toBeNullable(type: KClass<T>, expectedOrNull: T?): Assertion
}
