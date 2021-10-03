package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.feature.*
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.feature.ExperimentalFeatureInfo
import ch.tutteli.atrium.creating.feature.FeatureInfo
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.feature.MetaFeature
import ch.tutteli.atrium.reporting.MethodCallFormatter
import kotlin.reflect.*

/**
 * Extracts a feature out of the current subject of `this` expectation with the help of the given [extractor],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.16.0
 */

infix fun <T, R> Expect<T>.its(extractor: T.() -> R): FeatureExpect<T, R> =
    itsInternal(extractor).transform()

/**
 * Extracts a feature out  of the current subject of `this` expectation with the help of the given
 * [extractorWithCreator.extractor][ExtractorWithCreator.extractor],
 * creates a new [Expect] for it, applies an assertion group based on the given
 * [extractorWithCreator.assertionCreator][ExtractorWithCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.16.0
 */
infix fun <T, R> Expect<T>.its(extractorWithCreator: ExtractorWithCreator<T, R>): Expect<T> =
    itsInternal(extractorWithCreator.extractor).collectAndAppend(extractorWithCreator.assertionCreator)

/**
 * Helper function to create an [ExtractorWithCreator]
 */
fun <T, R> feature(extractor: T.() -> R, assertionCreator: Expect<R>.() -> Unit): ExtractorWithCreator<T, R> =
    ExtractorWithCreator(extractor, assertionCreator)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class, ExperimentalFeatureInfo::class)
private fun <R, T> Expect<T>.itsInternal(extractor: T.() -> R) =
    _logic.manualFeature(_logic.components.build<FeatureInfo>().determine(extractor, stacksToDrop = 2), extractor)

/**
 * Extracts the [property] out of the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return The newly created [Expect] for the given [property].
 *
 * @since 0.12.0
 */
infix fun <T, R> Expect<T>.feature(property: KProperty1<in T, R>): FeatureExpect<T, R> =
    _logic.property(property).transform()

/**
 * Extracts the value which is returned when calling the method [f] on the current subject of `this` expectation,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * Use `feature of(...)` in case the method requires parameters or in case you want to define
 * an assertion group block for it.
 *
 * @return The newly created [Expect] for the return value of calling the method [f]
 *   on the current subject of `this` expectation.
 *
 * @since 0.12.0
 */
infix fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): FeatureExpect<T, R> =
    _logic.f0(f).transform()

/**
 * Extracts a feature out of the current subject of `this` expectation using the given [Feature.extractor],
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * Use `of(K..., ...)` to create a [Feature] where the first argument is the extractor in form of a
 *   [KProperty1] or a `KFunctionX` and potentially the required arguments for a `KFunctionX` where `X` > 1.
 *
 * Note, [Feature] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 *
 * @param of Use `of(K..., ...)` to create a [Feature] where the first argument is the extractor in form of a
 *   [KProperty1] or a `KFunctionX` and potentially the required arguments for a `KFunctionX` where `X` > 1.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
//TODO remove `in` with Kotlin 1.4 (most likely with Atrium 1.0.0)
infix fun <T, R> Expect<T>.feature(of: Feature<in T, R>): FeatureExpect<T, R> =
    _logic.manualFeature(
        of.descriptionProvider(
            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
            @UseExperimental(ExperimentalComponentFactoryContainer::class)
            _logic.components
        ),
        of.extractor
    ).transform()

/**
 * Extracts a feature out of the current subject of `this` expectation using the given [FeatureWithCreator.extractor],
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [FeatureWithCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * Use `of(K..., ...) { ... }` to create a [FeatureWithCreator] where the first argument is the extractor in
 *   form of a [KProperty1] or a `KFunctionX`, the last an `assertionCreator`-lambda and the remaining arguments
 *   in-between the required arguments in case of a `KFunctionX` where `X` > 1.
 *
 * Note, [FeatureWithCreator] will be made invariant once Kotlin 1.4 is out and Atrium depends on it (most likely with 1.0.0)
 *
 * @param of Use `of(K..., ...) { ... }` to create a [FeatureWithCreator] where the first argument is the extractor in
 *   form of a [KProperty1] or a `KFunctionX`, the last an `assertionCreator`-lambda and the remaining arguments
 *   in-between the required arguments in case of a `KFunctionX` where `X` > 1.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
//TODO remove `in` with Kotlin 1.4 (most likely with Atrium 1.0.0)
infix fun <T, R> Expect<T>.feature(of: FeatureWithCreator<in T, R>): Expect<T> =
    _logic.manualFeature(
        of.descriptionProvider(
            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
            @UseExperimental(ExperimentalComponentFactoryContainer::class)
            _logic.components
        ),
        of.extractor
    ).collectAndAppend(of.assertionCreator)


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
 * @since 0.12.0
 */
infix fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): FeatureExpect<T, R> =
    _logic.genericSubjectBasedFeature { MetaFeatureOption(this).provider(it) }.transform()

/**
 * Extracts a feature out of the current subject of `this` expectation,
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
 * import ch.tutteli.atrium.api.infix.en_GB.workaround.it
 * expect(person) feature { f(it::age) } it { this toEqual 20 }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of({ f(it::age) }) { this toEqual 20 }
 * ```
 *
 * @param of Use the function `of({ ... }) { ... }` to create the [MetaFeatureOptionWithCreator] where the first
 *   argument is a lambda with a [MetaFeatureOption] as receiver which has to create a [MetaFeature]
 *   where the subject of `this` expectation is available via implicit parameter `it`.
 *   Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature of({ f(it::size) }) { it toEqual 3 }`
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
infix fun <T, R> Expect<T>.feature(of: MetaFeatureOptionWithCreator<T, R>): Expect<T> =
    _logic.genericSubjectBasedFeature {
        MetaFeatureOption(this).(of.provider)(it)
    }.collectAndAppend(of.assertionCreator)

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
 * Note that you can use `feature of("first underage child") { children.first { it < 18 } }` with the new type inference
 * enabled (e.g. if you use Kotlin 1.4 or above).
 * This method will most likely be removed once Kotlin 1.4 is out (probably with Atrium 1.0)
 *
 * @return The newly created [MetaFeature].
 */
@Suppress("unused" /* unused receiver, but that's fine */)
fun <T, R> MetaFeatureOption<T>.f(description: String, provider: R): MetaFeature<R> =
    MetaFeature(description, provider)

//@formatter:off
/**
 * Helper function to create a [Feature] based on a [KFunction2] + arguments.
 *
 * @return The newly created [Feature].
 */
fun <T, A1, R> of(f: KFunction2<T, A1, R>, a1: A1): Feature<T, R> =
    Feature(formatMethodCall(f, a1)) { f.invoke(it, a1) }

/**
 * Helper function to create a [Feature] based on a [KFunction3] + arguments.
 *
 * @return The newly created [Feature].
 */
fun <T, A1, A2, R > of(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): Feature<T, R> =
     Feature(formatMethodCall(f, a1, a2)) { f.invoke(it, a1, a2) }

/**
 * Helper function to create a [Feature] based on a [KFunction4] + arguments.
 *
 * @return The newly created [Feature].
 */
fun <T, A1, A2, A3, R> of(f:  KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): Feature<T, R> =
     Feature(formatMethodCall(f, a1, a2, a3)) { f.invoke(it, a1, a2, a3) }

/**
 * Helper function to create a [Feature] based on a [KFunction5] + arguments.
 *
 * @return The newly created [Feature].
 */
fun <T, A1, A2, A3, A4, R> of(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): Feature<T, R> =
     Feature(formatMethodCall(f, a1, a2, a3, a4)) { f.invoke(it, a1, a2, a3, a4) }

/**
 * Helper function to create a [Feature] based on a [KFunction6] + arguments.
 *
 * @return The newly created [Feature].
 */
fun <T, A1, A2, A3, A4, A5, R> of(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): Feature<T, R> =
    Feature(formatMethodCall(f, a1, a2, a3, a4, a5)) { f.invoke(it, a1, a2, a3, a4, a5) }

/**
 * Helper function to create a [FeatureWithCreator] based on a [KProperty1] + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, R> of(property: KProperty1<in T, R>, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(property.name, { property.invoke(it) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction1] + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, R> of(f: KFunction1<T, R>, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(formatMethodCall(f), { f.invoke(it) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction2] + arguments + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, A1, R> of(f: KFunction2<T, A1, R>, a1: A1, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(formatMethodCall(f, a1), { f.invoke(it, a1) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction3] + arguments + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, A1, A2, R > of(f: KFunction3<T, A1, A2, R>, a1: A1, a2: A2, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(formatMethodCall(f, a1, a2), { f.invoke(it, a1, a2) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction4] + arguments + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, A1, A2, A3, R> of(f: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(formatMethodCall(f, a1, a2, a3), { f.invoke(it, a1, a2, a3) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction5] + arguments + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, A1, A2, A3, A4, R> of(f: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
     FeatureWithCreator(formatMethodCall(f, a1, a2, a3, a4), { f.invoke(it, a1, a2, a3, a4) }, assertionCreator)

/**
 * Helper function to create a [FeatureWithCreator] based on a [KFunction6] + arguments + [assertionCreator].
 *
 * @return The newly created [FeatureWithCreator].
 */
fun <T, A1, A2, A3, A4, A5, R> of(f: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, assertionCreator: Expect<R>.() -> Unit): FeatureWithCreator<T, R> =
    FeatureWithCreator(formatMethodCall(f, a1, a2, a3, a4, a5), { f.invoke(it, a1, a2, a3, a4, a5) }, assertionCreator)
//@formatter:on

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class)
private fun formatMethodCall(k: KCallable<*>, vararg args: Any?): (ComponentFactoryContainer) -> String =
    { c -> c.build<MethodCallFormatter>().formatCall(k.name, args) }

/**
 * Helper function to create a [MetaFeatureOptionWithCreator] based on a lambda with
 * [MetaFeatureOption] receiver (has to return a [MetaFeature])  and an [assertionCreator].
 */
fun <T, R> of(
    provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): MetaFeatureOptionWithCreator<T, R> =
    MetaFeatureOptionWithCreator(
        provider,
        assertionCreator
    )

/**
 * Creates a [Feature] using the given [extractor] and [description].
 *
 * This can be used to create complex features with a custom description or as workaround where Kotlin is not able to
 * infer the types properly.
 *
 * For instance:
 * ```
 * expect(person) feature of("first underage child") { children.first { it < 18 } }
 * ```
 *
 * Note, you need to enable the new type inference of Kotlin (or use Kotlin 1.4 and above) in order that Kotlin
 * is able to infer the types.
 * As workaround you can use [feature] with the overload which expects `MetaFeatureOption<T>.(T) -> MetaFeature<R>`.
 * For instance:
 * ```
 * // use
 * expect(person) feature { f("first underage child", it.children.first { it < 18 }) }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of("first underage child") { children.first { it < 18 }
 * ```
 *
 * @return The newly created [Feature].
 */
fun <T, R> of(description: String, extractor: T.() -> R): Feature<T, R> =
    Feature(description, extractor)

/**
 * Creates a [Feature] using the given [extractor] and [description].
 *
 * This can be used to create complex features with a custom description or as workaround where Kotlin is not able to
 * infer the types properly.
 *
 * For instance:
 * ```
 * expect(person) feature of("first underage child", { children.first { it < 18 }) { name.toEqual("robert) }
 * ```
 *
 * Note, you need to enable the new type inference of Kotlin (or use Kotlin 1.4 and above) in order that Kotlin
 * is able to infer the types.
 * As workaround you can use [feature] with the overload which expects `MetaFeatureOption<T>.(T) -> MetaFeature<R>`.
 * and use `it` after the call (import from the package workaround). For instance:
 * ```
 * // use
 * import ch.tutteli.atrium.api.infix.en_GB.workaround.it
 * expect(person) feature { f(it::age) } it { this toEqual 20 }
 *
 * // instead of (which causes problems with Kotlin < 1.4)
 * expect(person) feature of({ f(it::age) }) { this toEqual 20 }
 * ```
 *
 * @return The newly created [Feature].
 */
fun <T, R> of(
    description: String,
    extractor: T.() -> R,
    assertionCreator: Expect<R>.() -> Unit
): FeatureWithCreator<T, R> = FeatureWithCreator(description, extractor, assertionCreator)
