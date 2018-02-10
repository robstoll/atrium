@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.creators.IThrowableThrownAssertions
import ch.tutteli.atrium.creating.throwable.thrown.creators.ThrowableThrownAssertions
import kotlin.reflect.KClass

object ThrowableAssertionsBuilder {

    /**
     * Delegates to [ThrowableThrownAssertions].
     */
    inline val thrown get() = ThrowableThrownAssertionsBuilder
}


object ThrowableThrownAssertionsBuilder: IThrowableThrownAssertions {

    override inline fun <TExpected : Throwable> toBe(throwableThrownBuilder: ThrowableThrownBuilder, expectedType: KClass<TExpected>, noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit)
        = ThrowableThrownAssertions.toBe(throwableThrownBuilder, expectedType, assertionCreator)

}
