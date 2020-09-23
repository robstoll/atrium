package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.impl.DefaultSubjectChanger

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
val <T> AssertionContainer<T>.subjectChanger: SubjectChanger
    get() = getImpl(SubjectChanger::class) { DefaultSubjectChanger() }
