package ch.tutteli.atrium.domain

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.domain.creating.transformers.SubjectChangerBuilder

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [ProofContainer].
 */
val <T> ProofContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)
