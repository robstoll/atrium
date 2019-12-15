package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.builders.creating._domain
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
fun <T, R> Expect<T>.feature(property: KProperty1<in T, R>): FeatureExpect<T, R> =
    _domain.property(property).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.property(property).addToInitial(assertionCreator)


/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): FeatureExpect<T, R> =
    _domain.f0(f).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f0(f).addToInitial(assertionCreator)


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
    a1: A1
): FeatureExpect<T, R> = _domain.f1(f, a1).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f1(f, a1).addToInitial(assertionCreator)


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
    a1: A1, a2: A2
): FeatureExpect<T, R> = _domain.f2(f, a1, a2).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f2(f, a1, a2).addToInitial(assertionCreator)


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
    a1: A1, a2: A2, a3: A3
): FeatureExpect<T, R> = _domain.f3(f, a1, a2, a3).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f3(f, a1, a2, a3).addToInitial(assertionCreator)


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
    a1: A1, a2: A2, a3: A3, a4: A4
): FeatureExpect<T, R> = _domain.f4(f, a1, a2, a3, a4).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f4(f, a1, a2, a3, a4).addToInitial(assertionCreator)


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
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5
): FeatureExpect<T, R> = _domain.f5(f, a1, a2, a3, a4, a5).getExpectOfFeature()

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
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.f5(f, a1, a2, a3, a4, a5).addToInitial(assertionCreator)


/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @param provider Extracts the feature where the subject of the assertion is available via
 *   implicit parameter `it`.
 *
 * @return An [Expect] for the extracted feature.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(description: String, provider: T.() -> R): FeatureExpect<T, R> =
    _domain.manualFeature(description, provider).getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @param provider Extracts the feature where the subject of the assertion is available via
 *   implicit parameter `it`.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.9.0
 */
fun <T, R> Expect<T>.feature(
    description: String,
    provider: T.() -> R,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = _domain.manualFeature(description, provider).addToInitial(assertionCreator)


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
fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): FeatureExpect<T, R> =
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
    _domain.genericSubjectBasedFeature { MetaFeatureOption(this).provider(it) }
