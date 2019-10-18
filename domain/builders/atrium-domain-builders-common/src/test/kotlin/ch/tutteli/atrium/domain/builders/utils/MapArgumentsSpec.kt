package ch.tutteli.atrium.domain.builders.utils

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ch.tutteli.atrium.verbs.internal.assert as deprecatedAssert

object MapArgumentsSpec : Spek({

    describe("mapArguments") {

        context("T") {
            it("without second step") {
                fun it(i: String, vararg iX: String) = mapArguments(i, iX) { "$it." }

                expect(it("a", "b", "c"))
                    .first { toBe("a.") }
                    .second.asList().containsExactly("b.", "c.")
            }

            it("with second step `to`") {
                fun it(i: String, vararg iX: String) = mapArguments(i, iX).to { "$it." }

                expect(it("a", "b", "c"))
                    .first { toBe("a.") }
                    .second.asList().containsExactly("b.", "c.")
            }

            it("toExpect") {
                fun it(i: String, vararg iX: String) =
                    mapArguments(i, iX).toExpect<String> { startsWith(it) }

                val (first, others) = it("a", "b", "c")
                expect("apple").first()
                others[0](expect("banana"))
                others[1](expect("caramel"))
            }

            it("toAssert") {
                @Suppress("DEPRECATION")
                fun it(i: String, vararg iX: String) =
                    mapArguments(i, iX).toAssert<String> { asExpect().startsWith(it) }

                val (first, others) = it("a", "b", "c")
                deprecatedAssert("apple").first()
                others[0](deprecatedAssert("banana"))
                others[1](deprecatedAssert("caramel"))
            }

            it("toAssertNullable") {
                @Suppress("DEPRECATION")
                fun it(i: String, vararg iX: String) =
                    mapArguments(i, iX).toAssertionPlantNullable<String?> { asExpect().notToBeNull { startsWith(it) } }

                val (first, others) = it("a", "b", "c")
                deprecatedAssert("apple" as String?).first()
                others[0](deprecatedAssert("banana" as String?))
                others[1](deprecatedAssert("caramel" as String?))
            }

            context("toNullOr...") {

                it("toExpect") {
                    fun it(i: String?, vararg iX: String?) =
                        mapArguments(i, iX).toNullOr().toExpect<String> { startsWith(it) }

                    val (first, others) = it(null, "b", "c")
                    expect(first).toBe(null)
                    expect(others[0]).notToBeNull {
                        maybeSubject.map { assertionCreator ->
                            ExpectImpl.changeSubject(this).unreported { "banana" }.assertionCreator()
                        }
                    }
                    deprecatedAssert(others[1]).asExpect().notToBeNull {
                        maybeSubject.map { assertionCreator ->
                            ExpectImpl.changeSubject(this).unreported { "caramel" }.assertionCreator()
                        }
                    }
                }

                it("toAssert") {
                    @Suppress("DEPRECATION")
                    fun it(i: String?, vararg iX: String?) =
                        mapArguments(i, iX).toNullOr().toAssert<String> { asExpect().startsWith(it) }

                    val (first, others) = it(null, "b", "c")
                    deprecatedAssert(first).asExpect().toBe(null)
                    deprecatedAssert(others[0]).asExpect().notToBeNull {
                        maybeSubject.map { assertionCreator ->
                            @Suppress("DEPRECATION")
                            ExpectImpl.changeSubject(this).unreported { "banana" }.asAssert().assertionCreator()
                        }
                    }
                    deprecatedAssert(others[1]).asExpect().notToBeNull {
                        maybeSubject.map { assertionCreator ->
                            @Suppress("DEPRECATION")
                            ExpectImpl.changeSubject(this).unreported { "caramel" }.asAssert().assertionCreator()
                        }
                    }
                }

                it("on non-nullable arguments") {
                    @Suppress("DEPRECATION")
                    fun it(i: String, vararg iX: String): Nothing = mapArguments(i, iX).toNullOr()

                    expect {
                        it("a", "b", "c")
                    }.toThrow<PleaseUseReplacementException>()
                }
            }
        }

        context("Byte") {
            it("without second step") {
                fun it(i: Byte, vararg iX: Byte) = mapArguments(i, iX) { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }

            it("with second step") {
                fun it(i: Byte, vararg iX: Byte) = mapArguments(i, iX).to { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
        }
        context("Char") {
            it("without second step") {
                fun it(i: Char, vararg iX: Char) = mapArguments(i, iX) { it + 1 }

                expect(it('a', 'b', 'c'))
                    .first { toBe('b') }
                    .second.asList().containsExactly('c', 'd')
            }
            it("with second step") {
                fun it(i: Char, vararg iX: Char) = mapArguments(i, iX).to { it + 1 }

                expect(it('a', 'b', 'c'))
                    .first { toBe('b') }
                    .second.asList().containsExactly('c', 'd')
            }
        }
        context("Short") {
            it("without second step") {
                fun it(i: Short, vararg iX: Short) = mapArguments(i, iX) { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
            it("with second step") {
                fun it(i: Short, vararg iX: Short) = mapArguments(i, iX).to { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
        }
        context("Int") {
            it("without second step") {
                fun it(i: Int, vararg iX: Int) = mapArguments(i, iX) { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
            it("with second step") {
                fun it(i: Int, vararg iX: Int) = mapArguments(i, iX).to { it + 1 }

                expect(it(1, 2, 3, 4))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
        }
        context("Long") {
            it("without second step") {
                fun it(i: Long, vararg iX: Long) = mapArguments(i, iX) { it + 1 }

                expect(it(1L, 2L, 3L, 4L))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
            it("with second step") {
                fun it(i: Long, vararg iX: Long) = mapArguments(i, iX).to { it + 1 }

                expect(it(1L, 2L, 3L, 4L))
                    .first { toBe(2) }
                    .second.asList().containsExactly(3, 4, 5)
            }
        }
        context("Float") {
            it("without second step") {
                fun it(i: Float, vararg iX: Float) = mapArguments(i, iX) { it + 1 }

                expect(it(1f, 2f, 3f, 4f))
                    .first { toBe(2f) }
                    .second.asList().containsExactly(3f, 4f, 5f)
            }
            it("with second step") {
                fun it(i: Float, vararg iX: Float) = mapArguments(i, iX).to { it + 1 }

                expect(it(1f, 2f, 3f, 4f))
                    .first { toBe(2f) }
                    .second.asList().containsExactly(3f, 4f, 5f)
            }
        }
        context("Double") {
            it("without second step") {
                fun it(i: Double, vararg iX: Double) = mapArguments(i, iX) { it + 1 }

                expect(it(1.0, 2.0, 3.0, 4.0))
                    .first { toBe(2.0) }
                    .second.asList().containsExactly(3.0, 4.0, 5.0)
            }
            it("with second step") {
                fun it(i: Double, vararg iX: Double) = mapArguments(i, iX).to { it + 1 }

                expect(it(1.0, 2.0, 3.0, 4.0))
                    .first { toBe(2.0) }
                    .second.asList().containsExactly(3.0, 4.0, 5.0)
            }
        }
        context("Boolean") {
            it("without second step") {
                fun it(i: Boolean, vararg iX: Boolean) = mapArguments(i, iX) { !it }

                expect(it(true, false, true))
                    .first { toBe(false) }
                    .second.asList().containsExactly(true, false)
            }
            it("with second step") {
                fun it(i: Boolean, vararg iX: Boolean) = mapArguments(i, iX).to { if (it) "a" else "b" }

                expect(it(true, false, false))
                    .first { toBe("a") }
                    .second.asList().containsExactly("b", "b")
            }
        }
    }
})
