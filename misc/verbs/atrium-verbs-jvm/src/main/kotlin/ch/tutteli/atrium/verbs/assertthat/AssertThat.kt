package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.creating.Assert

@Deprecated("use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject)"))
fun <T : Any> assertThat(subject: T)
    = ch.tutteli.atrium.verbs.assertThat(subject)

@Deprecated("use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject, assertionCreator)"))
fun <T : Any> assertThat(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = ch.tutteli.atrium.verbs.assertThat(subject, assertionCreator)

@Deprecated("use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(subject)"))
fun <T : Any?> assertThat(subject: T)
    = ch.tutteli.atrium.verbs.assertThat(subject)

@Deprecated("use assertThat from package verbs; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.verbs.assertThat(act)"))
fun assertThat(act: () -> Unit)
    = ch.tutteli.atrium.verbs.assertThat(act)
