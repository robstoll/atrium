package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.ExceptionThrownAssertion
import ch.tutteli.atrium.checking.IAssertionChecker

class ThrowableFluent(val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>) {

    constructor(assertionVerb: String, throwable: Throwable?, assertionChecker: IAssertionChecker)
        : this(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, throwable, assertionChecker))

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder<TExpected, Throwable>(commonFields, ExceptionThrownAssertion(commonFields.subject, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .cast()

    inline fun <reified TExpected : Throwable> toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder<TExpected, Throwable>(commonFields, ExceptionThrownAssertion(commonFields.subject, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .withLazyAssertions(createAssertions)
        .cast()

    companion object {
        val NO_EXCEPTION_OCCURRED = "no exception occurred"

        fun create(assertionVerb: String, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch(t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
