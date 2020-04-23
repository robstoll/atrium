// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")


package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.creating.Assert

@Deprecated("Use assert from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject)"))
fun <T : Any> assert(subject: T)
    = ch.tutteli.atrium.verbs.assert(subject)

@Deprecated("Use assert from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject, assertionCreator)"))
fun <T : Any> assert(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = ch.tutteli.atrium.verbs.assert(subject, assertionCreator)

@Deprecated("Use assert from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject)"))
fun <T : Any?> assert(subject: T)
    = ch.tutteli.atrium.verbs.assert(subject)

@Deprecated("Use assert from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(act)"))
fun assert(act: () -> Unit)
    = ch.tutteli.atrium.verbs.assert(act)
