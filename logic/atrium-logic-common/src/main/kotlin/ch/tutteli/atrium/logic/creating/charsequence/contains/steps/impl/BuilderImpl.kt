package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.basic.contains.steps.impl.ContainsBuilderLogic

internal class BuilderImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    override val container: AssertionContainer<T>,
    searchBehaviour: S
) : ContainsBuilderLogic<T, S>(container, searchBehaviour), CharSequenceContains.BuilderInternal<T, S>

