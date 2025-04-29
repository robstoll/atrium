package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.changeSubject
import ch.tutteli.atrium.reporting.reportables.useAlternativeUsageHint
import kotlin.jvm.JvmName

/**
 * Turns `Expect<Array<E>>` into `Expect<List<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.asListFeature
 *
 * @since 0.9.0
 */
fun <E> Expect<out Array<out E>>.asList(): Expect<List<E>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.asList
 *
 * @since 0.9.0
 */
fun <E> Expect<Array<E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<E>> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Array<out E>::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.asListEOut
 *
 * @since 0.9.0
 */
@JvmName("asListEOut")
fun <E> Expect<Array<out E>>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<Array<out E>> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }

/**
 * Turns `Expect<CharArray>` into `Expect<List<Byte>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.byteArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("byteArrAsList")
fun Expect<ByteArray>.asList(): Expect<List<Byte>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ByteArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.byteArrAsList
 *
 * @since 0.9.0
 */
@JvmName("byteArrAsList")
fun Expect<ByteArray>.asList(assertionCreator: Expect<List<Byte>>.() -> Unit): Expect<ByteArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<CharArray>` into `Expect<List<Char>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.charArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("charArrAsList")
fun Expect<CharArray>.asList(): Expect<List<Char>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(CharArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.charArrAsList
 *
 * @since 0.9.0
 */
@JvmName("charArrAsList")
fun Expect<CharArray>.asList(assertionCreator: Expect<List<Char>>.() -> Unit): Expect<CharArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<ShortArray>` into `Expect<List<Short>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.shortArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("shortArrAsList")
fun Expect<ShortArray>.asList(): Expect<List<Short>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(ShortArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.shortArrAsList
 *
 * @since 0.9.0
 */
@JvmName("shortArrAsList")
fun Expect<ShortArray>.asList(assertionCreator: Expect<List<Short>>.() -> Unit): Expect<ShortArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<IntArray>` into `Expect<List<Int>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.intArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("intArrAsList")
fun Expect<IntArray>.asList(): Expect<List<Int>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(IntArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.intArrAsList
 *
 * @since 0.9.0
 */
@JvmName("intArrAsList")
fun Expect<IntArray>.asList(assertionCreator: Expect<List<Int>>.() -> Unit): Expect<IntArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<LongArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.longArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("longArrAsList")
fun Expect<LongArray>.asList(): Expect<List<Long>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(LongArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.longArrAsList
 *
 * @since 0.9.0
 */
@JvmName("longArrAsList")
fun Expect<LongArray>.asList(assertionCreator: Expect<List<Long>>.() -> Unit): Expect<LongArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<FloatArray>` into `Expect<List<Float>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.floatArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("floatArrAsList")
fun Expect<FloatArray>.asList(): Expect<List<Float>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(FloatArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.floatArrAsList
 *
 * @since 0.9.0
 */
@JvmName("floatArrAsList")
fun Expect<FloatArray>.asList(assertionCreator: Expect<List<Float>>.() -> Unit): Expect<FloatArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<DoubleArray>` into `Expect<List<Double>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.doubleArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("doubleArrAsList")
fun Expect<DoubleArray>.asList(): Expect<List<Double>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(DoubleArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.doubleArrAsList
 *
 * @since 0.9.0
 */
@JvmName("doubleArrAsList")
fun Expect<DoubleArray>.asList(assertionCreator: Expect<List<Double>>.() -> Unit): Expect<DoubleArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }


/**
 * Turns `Expect<BooleanArray>` into `Expect<List<Boolean>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asList)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.booleanArrAsListFeature
 *
 * @since 0.9.0
 */
@JvmName("boolArrAsList")
fun Expect<BooleanArray>.asList(): Expect<List<Boolean>> =
    _core.changeSubject.unreported { it.asList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(BooleanArray::asList, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ArraySubjectChangerSamples.booleanArrAsList
 *
 * @since 0.9.0
 */
@JvmName("boolArrAsList")
fun Expect<BooleanArray>.asList(assertionCreator: Expect<List<Boolean>>.() -> Unit): Expect<BooleanArray> =
    apply {
        asList()._core.appendAsGroupIndicateIfOneCollected(
            ExpectationCreatorWithUsageHints(
                usageHintsAlternativeWithoutExpectationCreator = hintsAtLeastOneExpectationDefined,
                expectationCreator = assertionCreator
            )
        ).first
    }

private val hintsAtLeastOneExpectationDefined =
    useAlternativeUsageHint("asList()")
