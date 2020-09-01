package ch.tutteli.atrium.logic.creating.basic.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.Contains

abstract class EntryPointStepImpl<T : Any, out S : Contains.SearchBehaviour>(
    override val container: AssertionContainer<T>,
    override val searchBehaviour: S
) : Contains.EntryPointStepLogic<T, S>
