package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.creating.MetaFeature
import kotlin.reflect.*

/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the given [property].
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(property: KProperty1<T, R>): FeatureExpect<T, R> =
    ExpectImpl.feature.property(this, property).getExpectOfFeature()

/**
 * Extracts the value which is returned when calling the method [f] on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * Use `feature of(...)` in case the method requires parameters or in case you want to define
 * an assertion group block for it.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of the assertion.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): FeatureExpect<T, R> =
    ExpectImpl.feature.f0(this, f).getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion using the given [Feature.extractor],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * Use `of(K..., ...)` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX` and potentially the required arguments for a `KFunctionX` where `X` > 1.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(of: Feature<T, R>): FeatureExpect<T, R> =
    ExpectImpl.feature.manualFeature(this, of.description, of.extractor).getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion using the given [FeatureWithCreator.extractor],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [FeatureWithCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX`, the last an `assertionCreator`-lambda and the remaining arguments in-between the
 * required arguments in case of a `KFunctionX` where `X` > 1.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(of: FeatureWithCreator<T, R>): Expect<T> =
    ExpectImpl.feature.manualFeature(this, of.description, of.extractor).addToInitial(of.assertionCreator)


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
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): FeatureExpect<T, R> =
    ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).provider(it) }.getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [MetaFeatureOptionWithCreator]
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [MetaFeatureOptionWithCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * Note that you need to enable the new type inference of Kotlin (or use Kotlin 1.4 and above) in order that Kotlin
 * is able to infer the types.
 * As workaround you can use the overload which expects `MetaFeatureOption<T>.(T) -> MetaFeature<R>`
 * and use `it` after the call (import from the package workaround). For instance:
 *
 * ```
 * // use
 * expect(person) feature { f(it::age) } it { o toBe 20 }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of({ f(it::age) }) { o toBe 20 }
 * ```
 *
 * @param of Use the function `of({ ... }) { ... }` to create the [MetaFeatureOptionWithCreator] where the first
 *   argument is a lambda with a [MetaFeatureOption] as receiver which has to create a [MetaFeature]
 *   where the subject of the assertion is available via implicit parameter `it`.
 *   Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature of({ f(it::size) }) { o toBe 3 }`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(of: MetaFeatureOptionWithCreator<T, R>): Expect<T> =
    ExpectImpl.feature.genericSubjectBasedFeature(this) {
        MetaFeatureOption(this).(of.provider)(it)
    }.addToInitial(of.assertionCreator)

/**
 * Creates a [MetaFeature] using the given [provider] and [description].
 *
 * This can be used to create complex features with a custom description or as workaround where Kotlin is not able to
 * infer the types properly.
 *
 * For instance:
 * ```
 * expect(person) feature { f("first underage child", it.children.first { it < 18 }) }
 * ```
 *
 * @return The newly created [MetaFeature].
 */
@Suppress("unused" /* unused receiver, but that's fine */)
fun <T, R> MetaFeatureOption<T>.f(description: String, provider: R): MetaFeature<R> =
    MetaFeature(description, provider)

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX` and the remaining arguments are the required arguments in case of a `KFunctionX`
 * where `X` > 1.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.

 * @since 0.10.0
 */
data class Feature<T, R>(val description: String, val extractor: (T) -> R)

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion + an [assertionCreator]
 * which defines assertions for the feature.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX`, the last an [assertionCreator]-lambda and the remaining arguments in-between the
 * required arguments in case of a `KFunctionX` where `X` > 1.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.
 * @property assertionCreator The `assertionCreator`-lambda which defines assertions for the feature.
 *
 * @since 0.10.0
 */
data class FeatureWithCreator<T, R>(
    val description: String,
    val extractor: (T) -> R,
    val assertionCreator: Expect<R>.() -> Unit
)


//@formatter:off
/**
 * Helper function to create a [Feature] based on a [KFunction2] + arguments.
 */
fun <T, A1, R> of(f: KFunction2<T, A1, R>, a1: A1): Feature<T, R> =
    Feature(f.name) { f.invoke(it, a1) }

/**
 * Helper function to create a [Feature] based on a [KFunction3] + arguments.
 */
fun <T, A1, A2, R > of(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): Feature<T, R> =
     Feature(f.name) { f.invoke(it, a1, a2) }

/**
 * Helper function to create a [Feature] based on a [KFunction4] + arguments.
 */
fun <T, A1, A2, A3, R> of(f:  KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): Feature<T, R> =
     Feature(f.name) { f.invoke(it, a1, a2, a3) }

/**
 * Helper function to create a [Feature] based on a [KFunction5] + arguments.
 */
fun <T, A1, A2, A3, A4, R> of(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): Feature<T, R> =
     Feature(f.name) { f.invoke(it, a1, a2, a3, a4) }

/**
 * Helper function to create a [Feature] based on a [KFunction5] + arguments.
 */
fun <T, A1, A2, A3, A4, A5, R> of(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): Feature<T, R> =
    Feature(f.name) { f.invoke(it, a1, a2, a3, a4, a5) }

/**
 * Helper function to create a [FeatureWithCreator] based on a [KProperty1] + [assertionCreator].
 */
fun <T, R> of(property: KProperty1<T, R>, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(property.name, { property.invoke(it) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction1] + [assertionCreator].
 */
fun <T, R> of(f: KFunction1<T, R>, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(f.name, { f.invoke(it) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction2] + arguments + [assertionCreator].
 */
fun <T, A1, R> of(f: KFunction2<T, A1, R>, a1: A1, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(f.name, { f.invoke(it, a1) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction3] + arguments + [assertionCreator].
 */
fun <T, A1, A2, R > of(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(f.name, { f.invoke(it, a1, a2) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction4] + arguments + [assertionCreator].
 */
fun <T, A1, A2, A3, R> of(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(f.name, { f.invoke(it, a1, a2, a3) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction5] + arguments + [assertionCreator].
 */
fun <T, A1, A2, A3, A4, R> of(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(f.name, { f.invoke(it, a1, a2, a3, a4) }, assertionCreator)
//@formatter:on


/**
 * Parameter object which combines a lambda with a [MetaFeatureOption] receiver (called [provider])
 * and an [assertionCreator].
 *
 * Use the function `of({ ... }) { ... }` to create this representation where the first
 * argument is a lambda with a [MetaFeatureOption] as receiver which has to create a [MetaFeature]
 * where the subject of the assertion is available via implicit parameter `it`.
 * Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 * e.g. `feature of({ f(it::size) }) { o toBe 3 }`
 *
 * @since 0.10.0
 */
data class MetaFeatureOptionWithCreator<T, R>(
    val provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    val assertionCreator: Expect<R>.() -> Unit
)

/**
 * Helper function to create a [MetaFeatureOptionWithCreator] based on a lambda with
 * [MetaFeatureOption] receiver (has to return a [MetaFeature])  and an [assertionCreator].
 */
fun <T, R> of(
    provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): MetaFeatureOptionWithCreator<T, R> = MetaFeatureOptionWithCreator(provider, assertionCreator)
