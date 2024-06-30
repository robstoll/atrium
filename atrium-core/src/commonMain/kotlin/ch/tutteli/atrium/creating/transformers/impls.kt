package ch.tutteli.atrium.creating.transformers

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.impl.DefaultFeatureExtractor
import ch.tutteli.atrium.creating.transformers.impl.DefaultSubjectChanger

@OptIn(ExperimentalNewExpectTypes::class)
val <T> ProofContainer<T>.subjectChanger: SubjectChanger
    get() = getImpl(SubjectChanger::class) { DefaultSubjectChanger() }

@OptIn(ExperimentalNewExpectTypes::class)
val <T> ProofContainer<T>.featureExtractor: FeatureExtractor
    get() = getImpl(FeatureExtractor::class) { DefaultFeatureExtractor() }
