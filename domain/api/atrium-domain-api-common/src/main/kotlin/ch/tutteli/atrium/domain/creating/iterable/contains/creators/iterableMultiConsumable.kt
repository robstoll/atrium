package ch.tutteli.atrium.domain.creating.iterable.contains.creators

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating._domain


/**
 * Changes the subject to a list in case it is defined and not already one.
 * @since 0.9.0
 */
@Suppress("DEPRECATION" /* TODO switch to Expect instead of SubjectProvider and remove this annotation with 1.0.0 */)
fun <E> turnSubjectToList(subjectProvider: SubjectProvider<Iterable<E>>): SubjectProvider<List<E>> =
    subjectProvider.maybeSubject.fold(
        {
            @Suppress(
                // if the subject is not present, then we do not have to make it multiple times consumable and
                // can safely cast the type of the subject to List
                "UNCHECKED_CAST"
            )
            subjectProvider as SubjectProvider<List<E>>
        },
        { subject ->
            if (subject is List<E>) {
                @Suppress(
                    // if the subject is already a list, then we don't have to perform a subject change at all and
                    // can cast safely the subject to List<E>
                    "UNCHECKED_CAST"
                )
                subjectProvider as SubjectProvider<List<E>>
            } else {
                subjectProvider._domain.changeSubject.unreported { it.toList() }
            }
        }
    )
