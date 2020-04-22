// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")


package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.creating.Assert

@Deprecated("Use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject)"))
fun <T : Any> assertThat(subject: T)
    = ch.tutteli.atrium.verbs.assertThat(subject)

@Deprecated("Use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject, assertionCreator)"))
fun <T : Any> assertThat(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = ch.tutteli.atrium.verbs.assertThat(subject, assertionCreator)

@Deprecated("Use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject)"))
fun <T : Any?> assertThat(subject: T)
    = ch.tutteli.atrium.verbs.assertThat(subject)

@Deprecated("Use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(act)"))
fun assertThat(act: () -> Unit)
    = ch.tutteli.atrium.verbs.assertThat(act)
