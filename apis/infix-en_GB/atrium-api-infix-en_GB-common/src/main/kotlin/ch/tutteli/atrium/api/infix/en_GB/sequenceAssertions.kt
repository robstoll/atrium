import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl.changeSubject

/**
 * Turns `Expect<E, T : Sequence<E>>` into `Expect<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(Sequence::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <E, T : Sequence<E>> Expect<T>.asIterable(): Expect<Iterable<E>>
    = changeSubject(this).unreported { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Sequence::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return This assertion container to support a fluent API.
 */
infix fun <E, T : Sequence<E>> Expect<T>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<T> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }
