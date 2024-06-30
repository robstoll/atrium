// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.impl.DefaultDocumentationUtils
import ch.tutteli.atrium.reporting.reportables.InlineElement


fun <T> ProofContainer<T>.because(reason: InlineElement, expectationCreator: Expect<T>.() -> Unit): Proof =
    impl.because(this, reason, expectationCreator)


@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: DocumentationUtils
    get() = getImpl(DocumentationUtils::class) { DefaultDocumentationUtils() }
