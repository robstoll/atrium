package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.ResultFeatureAssertionsSpec
import ch.tutteli.atrium.specs.notImplemented

class ResultFeatureAssertionsSpec : ResultFeatureAssertionsSpec(


    feature1<Result<Int>, Int, Int>(Expect<Result<Int>>::isSuccess),
    fun2<Result<Int>, Int, Expect<Int>.() -> Unit>(Expect<Result<Int>>::isSuccess)
) {
    companion object {
        @Suppress("unused", "UNUSED_VALUE")
        private fun ambiguityTest() {
            var a1: Expect<Result<Int>> = notImplemented()
            var a2: Expect<out Result<Int>> = notImplemented()
            a1.isSuccess()
            a1 = a1.isSuccess { }
            a2.isSuccess();
            a2 = a2.isSuccess { }
        }
    }
}
