package ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.builders

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.basic.contains.Contains

abstract class ContainsBuilder<out T : Any, out S : Contains.SearchBehaviour>(
    override val subjectProvider: SubjectProvider<T>,
    override val searchBehaviour: S
) : Contains.Builder<T, S>
