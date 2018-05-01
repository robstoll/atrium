package ch.tutteli.atrium.verbs.internal

/**
 * You need to add atrium-spec to your dependencies in order to be able to reuse the ch.tutteli.atrium.verbs.internal.VerbSpec.
 */
internal object VerbSpec : ch.tutteli.atrium.spec.verbs.VerbSpec(
    "assert" to { subject -> assert(subject) },
    "assert" to { subject, assertionCreator -> assert(subject, assertionCreator) },
    "assert" to { subject -> assert(subject) },
    "expect" to { act -> expect { act() } }
)
