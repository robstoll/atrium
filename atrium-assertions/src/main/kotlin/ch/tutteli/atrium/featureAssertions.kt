package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.createAssertionsAndCheckThem
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.kbox.appendToStringBuilder
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0
import kotlin.reflect.KProperty0

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the given [property] as
 * [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(property))

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the given [property] as
 * [subject][IAssertionPlant.subject].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>, createAssertions: IAssertionPlant<TProperty>.() -> Unit): IAssertionPlant<TProperty>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(property))
    .createAssertionsAndCheckThem(createAssertions)


/**
 * Creates an [IAssertionPlantNullable] using the given [property] as [subject][IAssertionPlantNullable.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TProperty : Any?> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(property))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the given [feature] as
 * [subject][IAssertionPlant.subject].
 *
 * Delegates to [property].
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlant<TFeature>
    = property(feature)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the given [feature] as
 * [subject][IAssertionPlant.subject].
 *
 * Delegates to [property], more details are given there.
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>, createAssertions: IAssertionPlant<TFeature>.() -> Unit)
    = property(feature, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the given [feature] as [subject][IAssertionPlantNullable.subject].
 *
 * Delegates to [property].
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TFeature : Any?> IAssertionPlant<T>.its(feature: KProperty0<TFeature>)
    = property(feature)

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), AtriumFactory.newFeatureAssertionChecker(this))


/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method))

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the given [property] as [subject][IAssertionPlantNullable.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method))

private fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(method: KFunction<TReturnValue>, vararg arguments: Any?) =
    IAssertionPlantWithCommonFields.CommonFields(
        Untranslatable(createFeatureNameForMethod(method, arguments)),
        method.call(*arguments),
        AtriumFactory.newFeatureAssertionChecker(this))

private fun createFeatureNameForMethod(method: KFunction<*>, vararg arguments: Any?): () -> String = {
    val sb = StringBuilder(method.name).append("(")
    arguments.asList().appendToStringBuilder(sb, ", ") { it, sb ->
        sb.append(it?.toString() ?: RawString.NULL.string)
    }
    sb.append(")").toString()
}
