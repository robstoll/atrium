package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import kotlin.reflect.KClass


/**
 * This interface is mainly here to simplify the KDoc generation (no need to define it twice,
 * once in atrium-api-late-binding and once in atrium-impl-robstoll). Once Kotlin supports inline functions
 * in interfaces, we will consolidate it to a proper API and get rid of ThrowableFluent in atrium-api-late-binding.
 */
interface IThrowableFluent {
    val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>

    /**
     * Use the overload with reified type parameter whenever possible.
     *
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is `null` or another type
     * than the expected one.
     *
     * @param expectedType The expected type of the thrown [Throwable].
     *
     * @return This builder to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] if the assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>): IAssertionPlant<TExpected>

    /**
     * Use the overload with reified type parameter whenever possible.
     *
     * Makes an assertion about the [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * that it is of the expected type [TExpected] and reports an error if subject is null or another type
     * than the expected one -- furthermore it [createAssertions] which are checked additionally as well.
     *
     * @return This builder to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] if an assertion fails.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>

    /**
     * Use these [ISimpleTranslatable]s in the implementation to create corresponding [IAssertion]s.
     */
    enum class AssertionDescription(override val value: String) : ISimpleTranslatable {
        IS_A("is a"),
        NO_EXCEPTION_OCCURRED("no exception occurred"),
    }
}
