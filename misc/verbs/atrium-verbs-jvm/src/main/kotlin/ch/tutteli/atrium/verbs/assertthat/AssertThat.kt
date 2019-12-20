@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

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
