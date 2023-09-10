package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.toAssertionContainer

@Deprecated(
    "Use container.mapSubjectToList instead, will be removed with 1.2.0",
    ReplaceWith("container.mapSubjectToList(converter)")
)
fun <E, T> turnSubjectToList(
    container: AssertionContainer<T>,
    converter: (T) -> Iterable<E>
): AssertionContainer<List<E>> = container.mapSubjectToList(converter)

fun <E, T> AssertionContainer<T>.mapSubjectToList(
    converter: (T) -> Iterable<E>
): AssertionContainer<List<E>> =
    this.maybeSubject.fold(
        {
            @Suppress(
                // if the subject is not present, then we do not have to make it multiple times consumable and
                // can safely cast the subject to List<E>
                "UNCHECKED_CAST"
            )
            this as AssertionContainer<List<E>>
        },
        { subject ->
            val convertedSubject = converter(subject)
            this.changeSubject.unreported {
                when (convertedSubject) {
                    is List<E> -> convertedSubject
                    else -> convertedSubject.toList()
                }
            }.toAssertionContainer()
        }
    )


fun <E, T : IterableLike> AssertionContainer<T>.extractSubjectTurnToList(converter: (T) -> Iterable<E>): Option<List<E>> {
    return maybeSubject.map {
        when (val iterable = converter(it)) {
            is List -> iterable
            else -> iterable.toList()
        }
    }
}
