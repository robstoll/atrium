package ch.tutteli.atrium.logic.utils

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBeAnInstanceOf
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CollectAndAppendLogicTest {

    @Test
    fun workAsExpected() {
        data class Dummy(val l: List<Any>)

        expect(Dummy(listOf(1, "hello"))).toBeAnInstanceOf<Dummy> {
            feature { f(it::l) }.toContainExactly(
                { toBeAnInstanceOf<Int>() },
                { toBeAnInstanceOf<String>() }
            )
        }
    }
}
