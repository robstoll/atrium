package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context

object MapArgumentsSpec : Spek({

    context("T") {
        test("without second step") {
            fun test(i: String, vararg iX: String) = mapArguments(i, iX) { "$it." }

            assert(test("a", "b", "c"))
                .first { toBe("a.") }
                .second.asIterable().containsExactly("b.", "c.")
        }

        test("with second step `to`") {
            fun test(i: String, vararg iX: String) = mapArguments(i, iX).to { "$it." }

            assert(test("a", "b", "c"))
                .first { toBe("a.") }
                .second.asIterable().containsExactly("b.", "c.")
        }

        test("toAssert") {
            fun test(i: String, vararg iX: String) = mapArguments(i, iX).toAssert<String> { startsWith(it) }

            val (first, others) = test("a", "b", "c")
            assert("apple").first()
            others[0](assert("banana"))
            others[1](assert("caramel"))
        }

        test("toAssertNullable") {
            fun test(i: String, vararg iX: String) =
                mapArguments(i, iX).toAssertionPlantNullable<String?> { notToBeNull { startsWith(it) } }

            val (first, others) = test("a", "b", "c")
            assert("apple" as String?).first()
            others[0](assert("banana" as String?))
            others[1](assert("caramel" as String?))
        }

        test("toNullOr.toAssert") {
            fun test(i: String?, vararg iX: String?) = mapArguments(i, iX).toNullOr().toAssert<String> {
                startsWith(it)
            }

            val (first, others) = test(null, "b", "c")
            assert(first).toBe(null)
            assert(others[0]).notToBeNull {
                subject(assert("banana"))
            }
            assert(others[1]).notToBeNull {
                subject(assert("caramel"))
            }
        }

        test("toNullOr on non-nullable arguments") {
            @Suppress("DEPRECATION")
            fun test(i: String, vararg iX: String): Nothing = mapArguments(i, iX).toNullOr()

            expect {
                test("a", "b", "c")
            }.toThrow<PleaseUseReplacementException> {  }
        }
    }

    context("Byte") {
        test("without second step") {
            fun test(i: Byte, vararg iX: Byte) = mapArguments(i, iX) { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }

        test("with second step") {
            fun test(i: Byte, vararg iX: Byte) = mapArguments(i, iX).to { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
    }
    context("Char") {
        test("without second step") {
            fun test(i: Char, vararg iX: Char) = mapArguments(i, iX) { it + 1 }

            assert(test('a', 'b', 'c'))
                .first { toBe('b') }
                .second.asIterable().containsExactly('c', 'd')
        }
        test("with second step") {
            fun test(i: Char, vararg iX: Char) = mapArguments(i, iX).to { it + 1 }

            assert(test('a', 'b', 'c'))
                .first { toBe('b') }
                .second.asIterable().containsExactly('c', 'd')
        }
    }
    context("Short") {
        test("without second step") {
            fun test(i: Short, vararg iX: Short) = mapArguments(i, iX) { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
        test("with second step") {
            fun test(i: Short, vararg iX: Short) = mapArguments(i, iX).to { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
    }
    context("Int") {
        test("without second step") {
            fun test(i: Int, vararg iX: Int) = mapArguments(i, iX) { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
        test("with second step") {
            fun test(i: Int, vararg iX: Int) = mapArguments(i, iX).to { it + 1 }

            assert(test(1, 2, 3, 4))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
    }
    context("Long") {
        test("without second step") {
            fun test(i: Long, vararg iX: Long) = mapArguments(i, iX) { it + 1 }

            assert(test(1L, 2L, 3L, 4L))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
        test("with second step") {
            fun test(i: Long, vararg iX: Long) = mapArguments(i, iX).to { it + 1 }

            assert(test(1L, 2L, 3L, 4L))
                .first { toBe(2) }
                .second.asIterable().containsExactly(3, 4, 5)
        }
    }
    context("Float") {
        test("without second step") {
            fun test(i: Float, vararg iX: Float) = mapArguments(i, iX) { it + 1 }

            assert(test(1f, 2f, 3f, 4f))
                .first { toBe(2f) }
                .second.asIterable().containsExactly(3f, 4f, 5f)
        }
        test("with second step") {
            fun test(i: Float, vararg iX: Float) = mapArguments(i, iX).to { it + 1 }

            assert(test(1f, 2f, 3f, 4f))
                .first { toBe(2f) }
                .second.asIterable().containsExactly(3f, 4f, 5f)
        }
    }
    context("Double") {
        test("without second step") {
            fun test(i: Double, vararg iX: Double) = mapArguments(i, iX) { it + 1 }

            assert(test(1.0, 2.0, 3.0, 4.0))
                .first { toBe(2.0) }
                .second.asIterable().containsExactly(3.0, 4.0, 5.0)
        }
        test("with second step") {
            fun test(i: Double, vararg iX: Double) = mapArguments(i, iX).to { it + 1 }

            assert(test(1.0, 2.0, 3.0, 4.0))
                .first { toBe(2.0) }
                .second.asIterable().containsExactly(3.0, 4.0, 5.0)
        }
    }
    context("Boolean") {
        test("without second step") {
            fun test(i: Boolean, vararg iX: Boolean) = mapArguments(i, iX) { !it }

            assert(test(true, false, true))
                .first { toBe(false) }
                .second.asIterable().containsExactly(true, false)
        }
        test("with second step") {
            fun test(i: Boolean, vararg iX: Boolean) = mapArguments(i, iX).to { if (it) "a" else "b" }

            assert(test(true, false, false))
                .first { toBe("a") }
                .second.asIterable().containsExactly("b", "b")
        }
    }
})
