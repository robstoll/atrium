package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.toAssertionContainer

fun <E, T> turnSubjectToList(
    container: AssertionContainer<T>,
    converter: (T) -> Iterable<E>
): AssertionContainer<List<E>> =
    container.maybeSubject.fold(
        {
            @Suppress(
                // if the subject is not present, then we do not have to make it multiple times consumable and
                // can safely cast the subject to List<E>
                "UNCHECKED_CAST"
            )
            container as AssertionContainer<List<E>>
        },
        { subject ->
            val convertedSubject = converter(subject)
            container.changeSubject.unreported {
                when (convertedSubject) {
                    is List<E> -> convertedSubject
                    else -> convertedSubject.toList()
                }
            }.toAssertionContainer()
        }
    )
