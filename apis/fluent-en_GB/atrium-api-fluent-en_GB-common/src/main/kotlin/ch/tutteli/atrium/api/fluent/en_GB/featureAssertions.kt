package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureOptions
import ch.tutteli.atrium.domain.creating.MetaFeature
import kotlin.reflect.*

/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the given [property].
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(property: KProperty1<in T, R>, featureOptions: FeatureOptions<R>? = null): Expect<R> =
    ExpectImpl.feature.property(this, property, featureOptions).getExpectOfFeature()

/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    property: KProperty1<in T, R>,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.property(this, property, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(f: KFunction1<T, R>, featureOptions: FeatureOptions<R>? = null): Expect<R> =
    ExpectImpl.feature.f0(this, f, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    f: KFunction1<T, R>,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f0(this, f, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1,
    featureOptions: FeatureOptions<R>? = null
): Expect<R> = ExpectImpl.feature.f1(this, f, a1, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f1(this, f, a1, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2,
    featureOptions: FeatureOptions<R>? = null
): Expect<R> = ExpectImpl.feature.f2(this, f, a1, a2, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f2(this, f, a1, a2, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3,
    featureOptions: FeatureOptions<R>? = null
): Expect<R> = ExpectImpl.feature.f3(this, f, a1, a2, a3, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f3(this, f, a1, a2, a3, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4,
    featureOptions: FeatureOptions<R>? = null
): Expect<R> = ExpectImpl.feature.f4(this, f, a1, a2, a3, a4, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f4(this, f, a1, a2, a3, a4, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4], [a5]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5,
    featureOptions: FeatureOptions<R>? = null
): Expect<R> = ExpectImpl.feature.f5(this, f, a1, a2, a3, a4, a5, featureOptions).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4], [a5]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5,
    featureOptions: FeatureOptions<R>? = null,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = ExpectImpl.feature.f5(this, f, a1, a2, a3, a4, a5, featureOptions).addToInitial(assertionCreator)


/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [featureProvider] and using the given [description],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @param featureProvider Extracts the feature where the subject of the assertion is available via
 *   implicit parameter `it`.
 *
 * @return An [Expect] for the extracted feature.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    description: String,
    featureOptions: FeatureOptions<R>? = null,
    featureProvider: T.() -> R
): Expect<R> =
    ExpectImpl.feature.manualFeature(this, description, featureOptions, featureProvider).getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [featureProvider] and using the given [description],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @param featureProvider Extracts the feature where the subject of the assertion is available via
 *   implicit parameter `it`.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    description: String,
    featureOptions: FeatureOptions<R>? = null,
    featureProvider: T.() -> R,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> =
    ExpectImpl.feature.manualFeature(this, description, featureOptions, featureProvider).addToInitial(assertionCreator)


/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @param provider Creates a [MetaFeature] where the subject of the assertion is available via
 *   implicit parameter `it`. Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature { f(it::size) }`
 *
 * @return An [Expect] for the extracted feature.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): Expect<R> =
    extractFeature(provider).getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @param provider You need to create a [MetaFeature] where the subject of the assertion is available via
 *   implicit parameter `it`. Usually you use [MetaFeatureOption.f] to create a [MetaFeature], e.g. `f(it::size)`
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = extractFeature(provider).addToInitial(assertionCreator)

private fun <R, T> Expect<T>.extractFeature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>) =
    ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).provider(it) }
