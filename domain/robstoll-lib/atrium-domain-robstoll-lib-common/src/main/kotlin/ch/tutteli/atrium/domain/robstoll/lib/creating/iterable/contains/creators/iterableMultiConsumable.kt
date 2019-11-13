package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl

@Suppress("DEPRECATION" /* switch to Expect instead of SubjectProvider and remove this annotation with 1.0.0 */)
fun <E> turnSubjectToList(subjectProvider: SubjectProvider<Iterable<E>>): SubjectProvider<List<E>> =
    ExpectImpl.changeSubject(subjectProvider).unreported {
        if (it is List<E>) it
        else it.toList()
    }
