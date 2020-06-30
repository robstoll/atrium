package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.toAssertionContainer

internal fun <E> turnSubjectToList(container: AssertionContainer<out Iterable<E>>): AssertionContainer<List<E>> =
    container.maybeSubject.fold(
        {
            @Suppress(
                // if the subject is not present, then we do not have to make it multiple times consumable and
                // can safely cast the type of the subject to List
                "UNCHECKED_CAST"
            )
            container as AssertionContainer<List<E>>
        },
        { subject ->
            if (subject is List<E>) {
                @Suppress(
                    // if the subject is already a list, then we don't have to perform a subject change at all and
                    // can cast safely the subject to List<E>
                    "UNCHECKED_CAST"
                )
                container as AssertionContainer<List<E>>
            } else {
                container.changeSubject.unreported { it.toList() }.toAssertionContainer()
            }
        }
    )


