package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.builders.ContainsBuilder

class CharSequenceContainsBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    subjectProvider: SubjectProvider<T>, searchBehaviour: S
) : ContainsBuilder<T, S>(subjectProvider, searchBehaviour), CharSequenceContains.Builder<T, S>

