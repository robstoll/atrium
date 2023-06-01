package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.impl.DefaultFeatureExtractor
import ch.tutteli.atrium.logic.creating.transformers.impl.DefaultSubjectChanger

@OptIn(ExperimentalNewExpectTypes::class)
val <T> AssertionContainer<T>.subjectChanger: SubjectChanger
    get() = getImpl(SubjectChanger::class) { DefaultSubjectChanger() }

@OptIn(ExperimentalNewExpectTypes::class)
val <T> AssertionContainer<T>.featureExtractor: FeatureExtractor
    get() = getImpl(FeatureExtractor::class) { DefaultFeatureExtractor() }
