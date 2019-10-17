package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.ResultFeatureAssertionsSpec

class ResultFeatureAssertionsSpec : ResultFeatureAssertionsSpec(
    feature0<Result<Int>, Int>(Expect<Result<Int>>::isSuccess),
    fun1<Result<Int>, Expect<Int>.() -> Unit>(Expect<Result<Int>>::isSuccess)
) {
    companion object {
        @Suppress("unused", "UNUSED_VALUE")
        private fun ambiguityTest() {
            var a1: Expect<Result<Int>> = notImplemented()
            var a2: Expect<out Result<Int>> = notImplemented()
            var a3: Expect<*> = notImplemented()
            a1.isSuccess()
            a1 = a1.isSuccess { }
            a2.isSuccess();
            a2 = a2.isSuccess { }
        }
    }
}
