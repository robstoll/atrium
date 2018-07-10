package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.creating.Assert

@Deprecated("use assert from package verbs, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject)"))
fun <T : Any> assert(subject: T)
    = ch.tutteli.atrium.verbs.assert(subject)

@Deprecated("use assert from package verbs, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject, assertionCreator)"))
fun <T : Any> assert(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = ch.tutteli.atrium.verbs.assert(subject, assertionCreator)

@Deprecated("use assert from package verbs, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(subject)"))
fun <T : Any?> assert(subject: T)
    = ch.tutteli.atrium.verbs.assert(subject)

@Deprecated("use assert from package verbs, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assert(act)"))
fun assert(act: () -> Unit)
    = ch.tutteli.atrium.verbs.assert(act)
