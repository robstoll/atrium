//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.logic.FeatureKt
import ch.tutteli.atrium.scala2.logic._

class FeatureLogic[T, TProperty](container: AssertionContainer[T]) {

    //@formatter:off
    def property(property: Function1[T, TProperty]): ExtractedFeaturePostStep[T, TProperty] = FeatureKt.property(container, property)

    def f0[R](f: Function1[T, R]): ExtractedFeaturePostStep[T, R] = FeatureKt.f0(container, f)

    def f1[A1, R](f: Function2[T, A1, R], a1: A1): ExtractedFeaturePostStep[T, R] =
        FeatureKt.f1(container, f, a1)

    def f2[A1, A2, R](f: Function3[T, A1, A2, R], a1: A1, a2: A2): ExtractedFeaturePostStep[T, R] =
        FeatureKt.f2(container, f, a1, a2)

    def f3[A1, A2, A3, R](f: Function4[T, A1, A2, A3, R], a1: A1, a2: A2, a3: A3): ExtractedFeaturePostStep[T, R] =
        FeatureKt.f3(container, f, a1, a2, a3)

    def f4[A1, A2, A3, A4, R](f: Function5[T, A1, A2, A3, A4, R], a1: A1, a2: A2, a3: A3, a4: A4): ExtractedFeaturePostStep[T, R] =
        FeatureKt.f4(container, f, a1, a2, a3, a4)

    def f5[A1, A2, A3, A4, A5, R](f: Function6[T, A1, A2, A3, A4, A5, R], a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): ExtractedFeaturePostStep[T, R] =
        FeatureKt.f5(container, f, a1, a2, a3, a4, a5)
    //@formatter:on

    def manualFeature[R](description: String, provider: T => R): ExtractedFeaturePostStep[T, R] =
        FeatureKt.manualFeature(container, description, provider)

    def manualFeature[R](description: Translatable, provider: T => R): ExtractedFeaturePostStep[T, R] =
        FeatureKt.manualFeature(container, description, provider)

    def genericSubjectBasedFeature[R](provider: (T) => MetaFeature[R]): ExtractedFeaturePostStep[T, R] = FeatureKt.genericSubjectBasedFeature(container, provider)

    def genericFeature[R](metaFeature: MetaFeature[R]): ExtractedFeaturePostStep[T, R] = FeatureKt.genericFeature(container, metaFeature)
}
