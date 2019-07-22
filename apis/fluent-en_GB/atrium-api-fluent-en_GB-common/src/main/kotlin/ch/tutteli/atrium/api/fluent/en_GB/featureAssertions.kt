package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.creating.MetaFeature
import kotlin.reflect.*

/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the given [property].
 */
fun <T, R> Expect<T>.feature(property: KProperty1<T, R>): Expect<R> =
    ExpectImpl.feature.property(this, property)

/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, R> Expect<T>.feature(
    property: KProperty1<T, R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.property(this, property, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): Expect<R> =
    ExpectImpl.feature.f0(this, f)

/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, R> Expect<T>.feature(f: KFunction1<T, R>, assertionCreator: Expect<R>.() -> Unit): Expect<T> =
    addAssertion(ExpectImpl.feature.f0(this, f, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1
): Expect<R> = ExpectImpl.feature.f1(this, f, a1)

/**
 * Extracts the value which is returned when calling [f] with argument [a1]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, A1, R> Expect<T>.feature(
    f: KFunction2<T, A1, R>,
    a1: A1,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.f1(this, f, a1, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2
): Expect<R> = ExpectImpl.feature.f2(this, f, a1, a2)

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, A1, A2, R> Expect<T>.feature(
    f: KFunction3<T, A1, A2, R>,
    a1: A1, a2: A2,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.f2(this, f, a1, a2, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3
): Expect<R> = ExpectImpl.feature.f3(this, f, a1, a2, a3)

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, A1, A2, A3, R> Expect<T>.feature(
    f: KFunction4<T, A1, A2, A3, R>,
    a1: A1, a2: A2, a3: A3,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.f3(this, f, a1, a2, a3, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4
): Expect<R> = ExpectImpl.feature.f4(this, f, a1, a2, a3, a4)

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, A1, A2, A3, A4, R> Expect<T>.feature(
    f: KFunction5<T, A1, A2, A3, A4, R>,
    a1: A1, a2: A2, a3: A3, a4: A4,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.f4(this, f, a1, a2, a3, a4, assertionCreator))


/**
 * Extracts the value which is returned when calling [f] with arguments [a1], [a2], [a3], [a4], [a5]
 * on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5
): Expect<R> = ExpectImpl.feature.f5(this, f, a1, a2, a3, a4, a5)

/**
 * Extracts the value which is returned when calling [f] with argument [a1], [a2], [a3], [a4], [a5]
 * on the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, A1, A2, A3, A4, A5, R> Expect<T>.feature(
    f: KFunction6<T, A1, A2, A3, A4, A5, R>,
    a1: A1, a2: A2, a3: A3, a4: A4, a5: A5,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.f5(this, f, a1, a2, a3, a4, a5, assertionCreator))


/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the extracted feature.
 */
fun <T, R> Expect<T>.feature(description: String, provider: T.() -> R): Expect<R> =
    ExpectImpl.feature.manualFeature(this, description, provider)

/**
 * Extracts a feature out of the current subject of the assertion
 * based on the given [provider] and using the given [description],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, R> Expect<T>.feature(
    description: String,
    provider: T.() -> R,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(ExpectImpl.feature.manualFeature(this, description, provider, assertionCreator))


/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the extracted feature.
 */
fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): Expect<R> =
    ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).provider(it) }

/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 */
fun <T, R> Expect<T>.feature(
    provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): Expect<T> = addAssertion(
    ExpectImpl.feature.genericSubjectBasedFeature(this, { MetaFeatureOption(this).provider(it) }, assertionCreator)
)
