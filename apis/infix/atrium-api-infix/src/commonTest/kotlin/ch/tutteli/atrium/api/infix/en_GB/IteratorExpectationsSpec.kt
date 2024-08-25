package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.reflect.KFunction2

class IteratorExpectationsSpec : ch.tutteli.atrium.specs.integration.IteratorExpectationsSpec(
    getToHaveNextPair(),
    getNotToHaveNextPair(),
    getNextFeaturePair(),
    getNextFunPair(),
    getNextFeaturePairNullable().withNullableSuffix(),
    getNextFunPairNullable().withNullableSuffix()
) {
    companion object {
        private val toHave: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::toHave

        private fun getToHaveNextPair() = "${toHave.name} ${next::class.simpleName}" to Companion::toHaveNext
        private fun toHaveNext(expect: Expect<Iterator<Int>>) = expect toHave next

        private val notToHave: KFunction2<Expect<Iterator<Int>>, next, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::notToHave

        private fun getNotToHaveNextPair() = "${notToHave.name} ${next::class.simpleName}" to Companion::notToHaveNext

        private fun notToHaveNext(expect: Expect<Iterator<Int>>) = expect notToHave next

        private val nextFeature: KFunction2<Expect<Iterator<Int>>, o, Expect<Int>> = Expect<Iterator<Int>>::next
        private fun getNextFeaturePair() = "${nextFeature.name} ${next::class.simpleName}" to Companion::nextFeatureNext
        private fun nextFeatureNext(expect: Expect<Iterator<Int>>) = expect next o

        private val nextFun: KFunction2<Expect<Iterator<Int>>, Expect<Int>.() -> Unit, Expect<Iterator<Int>>> =
            Expect<Iterator<Int>>::next

        private fun getNextFunPair() = "${nextFun.name} ${next::class.simpleName}" to Companion::nextFunNext
        private fun nextFunNext(expect: Expect<Iterator<Int>>, assertionCreator: Expect<Int>.() -> Unit) =
            expect next { assertionCreator() }

        private fun getNextFeaturePairNullable() =
            "${nextFeature.name} ${next::class.simpleName}" to Companion::nextFeatureNextNullable

        private fun nextFeatureNextNullable(expect: Expect<Iterator<Int?>>) = expect next o

        private fun getNextFunPairNullable() =
            "${nextFun.name} ${next::class.simpleName}" to Companion::nextFunNextNullable

        private fun nextFunNextNullable(expect: Expect<Iterator<Int?>>, assertionCreator: Expect<Int?>.() -> Unit) =
            expect next { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    private fun ambiguityTest() {
        var a1: Expect<Iterator<Double>> = notImplemented()
        var a2: Expect<Double> = notImplemented()

        a1 = a1 toHave next
        a1 = a1 notToHave next
        a1 = a1 next { }

        a2 = a1 next o
    }
}
