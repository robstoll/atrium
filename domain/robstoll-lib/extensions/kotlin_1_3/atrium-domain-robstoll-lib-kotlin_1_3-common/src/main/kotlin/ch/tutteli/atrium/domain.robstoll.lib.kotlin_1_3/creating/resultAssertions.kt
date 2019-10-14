@file:JvmMultifileClass
@file:JvmName("ResultAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


fun  <E, T : Result<E>>_isSuccess( assertionContainer: Expect<T>) : ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(assertionContainer)

