package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.providers._translatableBased
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import kotlin.reflect.KClass

fun <TExpected : Throwable> _toBe(throwableThrownBuilder: ThrowableThrown.Builder, expectedType: KClass<TExpected>, assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    val provider = _translatableBased(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider)
        .executeActAndCreateAssertion(throwableThrownBuilder, IS_A, expectedType, assertionCreator)
}

fun _nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder){
    val provider = _translatableBased(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<Throwable>(provider).executeActAssertNothingThrown(throwableThrownBuilder)
}
