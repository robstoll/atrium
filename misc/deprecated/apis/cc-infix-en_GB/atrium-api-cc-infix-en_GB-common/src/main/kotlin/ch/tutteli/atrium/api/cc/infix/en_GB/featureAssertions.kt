// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import kotlin.reflect.*
import kotlin.jvm.JvmName

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(property) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = throw PleaseUseReplacementException("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(property).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty1<T, TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)


/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of<T, TProperty>({ f(property) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = throw PleaseUseReplacementException("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(property, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty1<T, TProperty>, assertionCreator: Assert<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(property) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, TProperty : Any?> CollectingAssertionPlant<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = throw PleaseUseReplacementException("Use the overload with KProperty1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(property).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty1<T, TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)


// Arg 0 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction0<R>): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(method).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction1<T, R>): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction0<R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, R : Any> Assert<T>.returnValueOf(method: KFunction1<T, R>, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, R : Any?> Assert<T>.returnValueOf(method: KFunction0<R>): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf0(this, method)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction0<R>): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction1 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(method).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, R : Any?> Assert<T>.returnValueOf(method: KFunction1<T, R>): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf0(this, method)


// Arg 1 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method, arg1) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, R : Any> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, R : Any?> Assert<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction1<T1, R>, arg1: T1): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction2 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, R : Any?> Assert<T>.returnValueOf(method: KFunction2<T, T1, R>, arg1: T1): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)


// Arg 2 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method, arg1, arg2) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, R : Any> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, R : Any?> Assert<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction3 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, R : Any?> Assert<T>.returnValueOf(method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)


// Arg 3 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method, arg1, arg2, arg3) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, R : Any> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, R : Any?> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction4 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, R : Any?> Assert<T>.returnValueOf(method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)


// Arg 4 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3, arg4) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method, arg1, arg2, arg3, arg4) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3, arg4) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any?> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction5 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, R : Any?> Assert<T>.returnValueOf(method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)


// Arg 5 ----------------------------------------------------------------------------

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3, arg4, arg5) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4, arg5)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of({ f(method, arg1, arg2, arg3, arg4, arg5) }, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4, arg5, { asAssert(assertionCreator) })).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: Assert<R>.() -> Unit): AssertionPlant<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)


/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature { f(method, arg1, arg2, arg3, arg4, arg5) }.asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

@Suppress("DeprecatedCallableAddReplaceWith", "unused", "UNUSED_PARAMETER")
@Deprecated("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> CollectingAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = throw PleaseUseReplacementException("Use the overload with KFunction6 instead, this way you do not access `subject` too early. Use `YourClass::property` instead of `subject::property`")

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@JvmName("safeReturnValueOf")
@Deprecated(
    "Switch from Assert to Expect and use feature instead; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.asExpect().feature(of(method, arg1, arg2, arg3, arg4, arg5)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.infix.en_GB.feature",
        "ch.tutteli.atrium.api.infix.en_GB.of"
    )
)
fun <T : Any, T1, T2, T3, T4, T5, R : Any?> Assert<T>.returnValueOf(method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)
