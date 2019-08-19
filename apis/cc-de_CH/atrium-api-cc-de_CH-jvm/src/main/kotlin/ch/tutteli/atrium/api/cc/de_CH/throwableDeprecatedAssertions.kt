@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("ThrowableAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected].
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the assertion
 *   (a [Throwable] was thrown and is of type [TExpected]) holds or not.
 * If you want to define subsequent assertions on the down-casted [Throwable], then use the overload which expects a
 * lambda (where you can define subsequent assertions).
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Does not add enough to be a valid alternative to the overload with assertionCreator; will be removed with 1.0.0", ReplaceWith("wirft {}"))
inline fun <reified TExpected : Throwable> ThrowableThrown.Builder.wirft() {
    wirft<TExpected> {}
}
