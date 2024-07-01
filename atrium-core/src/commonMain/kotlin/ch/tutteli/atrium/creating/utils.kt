package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [ProofContainer].
 */
val <T> ProofContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)
