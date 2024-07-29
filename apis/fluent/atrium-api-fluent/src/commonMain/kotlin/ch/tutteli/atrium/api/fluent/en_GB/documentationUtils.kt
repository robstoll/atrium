//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.kbox.glue
import kotlin.reflect.KClass

/**
 * Allows to state a reason for one or multiple assertions for the current subject.
 *
 * @param reason The explanation for the assertion(s) created by [assertionCreator].
 * @param assertionCreator The group of assertions to make.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.DocumentationUtilSamples.because
 *
 * @since 0.15.0
 */
fun <T> Expect<T>.because(reason: String, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    _logicAppend { because(reason, assertionCreator) }
