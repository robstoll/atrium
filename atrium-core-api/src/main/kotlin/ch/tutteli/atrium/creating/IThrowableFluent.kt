package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

/**
 * This interface is mainly here to simplify the KDoc generation (no need to define it twice,
 * once in atrium-core-api-late-binding and once in atrium-core-impl-robstoll). Once Kotlin supports inline functions
 * in interfaces, we will consolidate it to a proper API and get rid of ThrowableFluent in atrium-api-late-binding.
 */
interface IThrowableFluent {

    /**
     * Use the overload with reified type parameter whenever possible.
     *
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is null or another type
     * than the expected one -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @param expectedType The expected type of the thrown [Throwable].
     * @param description The [ITranslatable] used in reporting to state `is a` in `is a: Exception`.
     * @param nullRepresentation The [ITranslatable] used to represent the case that no exception was thrown.
     * @param createAssertions The factory-lambda which is called with the thrown [Throwable] as receiver.
     *
     * @return This builder to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] if an assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, description: ITranslatable, nullRepresentation: ITranslatable, createAssertions: IAssertionPlant<TExpected>.() -> Unit)
}
