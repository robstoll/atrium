package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.feature.MetaFeatureOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.logic.creating.feature.MetaFeature
import kotlin.reflect.*

/**
 * Extracts a feature out of the current subject of `this` expectation with the help of the given [extractor],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.itsFeature
 *
 * @since 0.16.0
 */

fun <T, R> Expect<T>.its(extractor: T.() -> R): FeatureExpect<T, R> =
    itsInternal(extractor).transform()

/**
 * Extracts a feature out  of the current subject of `this` expectation with the help of the given [extractor],
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.its
 *
 * @since 0.16.0
 */
fun <T, R> Expect<T>.its(extractor: T.() -> R, assertionCreator: Expect<R>.() -> Unit): Expect<T>  =
    itsInternal(extractor).collectAndAppend(assertionCreator)

@OptIn(ExperimentalComponentFactoryContainer::class, ExperimentalFeatureInfo::class)
private fun <R, T> Expect<T>.itsInternal(extractor: T.() -> R) =
    _logic.manualFeature(_logic.components.build<FeatureInfo>().determine(extractor, stacksToDrop = 2), extractor)

/**
 * Extracts the [property] out of the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the given [property].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromPropertyFeature
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(property: KProperty1<in T, R>): FeatureExpect<T, R> =
    _logic.property(property).transform()

/**
 * Extracts the [property] out of the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromProperty
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    property: KProperty1<in T, R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.property(property).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionFeature
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): FeatureExpect<T, R> =
    _logic.f0(f).transform()

/**
 * Extracts the value which is returned when calling [f] on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunction
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    f: KFunction1<T, R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f0(f).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithArgumentFeature
 *
 * @since 0.9.0
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1
): FeatureExpect<T, R> = _logic.f1(f, a1).transform()

/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithArgument
 *
 * @since 0.9.0
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f1(f, a1).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithTwoArgumentsFeature
 *
 * @since 0.9.0
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2
): FeatureExpect<T, R> = _logic.f2(f, a1, a2).transform()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithTwoArguments
 *
 * @since 0.9.0
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f2(f, a1, a2).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithThreeArgumentsFeature
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3
): FeatureExpect<T, R> = _logic.f3(f, a1, a2, a3).transform()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithThreeArguments
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f3(f, a1, a2, a3).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithFourArgumentsFeature
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4
): FeatureExpect<T, R> = _logic.f4(f, a1, a2, a3, a4).transform()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithFourArguments
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f4(f, a1, a2, a3, a4).collectAndAppend(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4], [a5]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly [Expect] for the return value of calling [f] on the current subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithFiveArgumentsFeature
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5
): FeatureExpect<T, R> = _logic.f5(f, a1, a2, a3, a4, a5).transform()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4], [a5]
 * on the current subject of `this` expectation,
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureFromFunctionWithFiveArguments
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.f5(f, a1, a2, a3, a4, a5).collectAndAppend(assertionCreator)


/**
 * Extracts a feature out of the current subject of `this` expectation
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @param provider Extracts the feature where the subject of `this` expectation is available via
 *   implicit parameter `it`.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureWithDescriptionFeature
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(description: String, provider: T.() -> R): FeatureExpect<T, R> =
    _logic.manualFeature(description, provider).transform()

/**
 * Extracts a feature out of the current subject of `this` expectation
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @param provider Extracts the feature where the subject of `this` expectation is available via
 *   implicit parameter `it`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureWithDescription
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    description: String,
    provider: T.() -> R,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _logic.manualFeature(description, provider).collectAndAppend(assertionCreator)


/**
 * Extracts a feature out of the current subject of `this` expectation,
 * based on the given [provider],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @param provider Creates a [MetaFeature] where the subject of `this` expectation is available via
 *   implicit parameter `it`. Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature { f(it::size) }`
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureWithMetaFeatureProviderFeature
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): FeatureExpect<T, R> =
    extractFeature(provider).transform()

/**
 * Extracts a feature out of the current subject of `this` expectation,
 * based on the given [provider],
 * creates a new [Expect] for it,
 * applies an expectation-group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @param provider You need to create a [MetaFeature] where the subject of `this` expectation is available via
 *   implicit parameter `it`. Usually you use [MetaFeatureOption.f] to create a [MetaFeature], e.g. `f(it::size)`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FeatureExtractorSamples.featureWithMetaFeatureProvider
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = extractFeature(provider).collectAndAppend(assertionCreator)

private fun <R, T> Expect<T>.extractFeature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>) =
    _logic.genericSubjectBasedFeature { MetaFeatureOption(this).provider(it) }
