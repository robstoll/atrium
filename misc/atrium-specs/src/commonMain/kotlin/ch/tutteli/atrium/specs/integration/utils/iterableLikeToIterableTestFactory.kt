package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import ch.tutteli.kbox.glue

fun <SubjectT> iterableLikeToIterableTestFactory(
    subject: SubjectT,
    expectationFun: SpecPair<Expect<SubjectT>.(IterableLike) -> Expect<SubjectT>>,
    vararg otherExpectationFuns: SpecPair<Expect<SubjectT>.(IterableLike) -> Expect<SubjectT>>,
) = testFactory {
    (expectationFun glue otherExpectationFuns).forEach { (name, expectationFunAcceptingIterableLike) ->
        describe(name) {
            emptyIterableLikeTypes.forEach { (type, input) ->
                it("passing an empty $type throws an IllegalArgumentException") {
                    expect {
                        expect(subject).expectationFunAcceptingIterableLike(input)
                    }.toThrow<IllegalArgumentException> {
                        messageToContain("IterableLike without elements are not allowed")
                    }
                }
            }

            it("passing a String instead of an IterableLike throws an IllegalArgumentException") {
                expect {
                    expect(subject).expectationFunAcceptingIterableLike("test")
                }.toThrow<IllegalArgumentException> {
                    messageToContain(
                        "IterableLikeToIterableTransformer accepts arguments of types:",
                        "Iterable<*>, Sequence<*>, Array<*>, CharArray, ByteArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray and BooleanArray",
                        "given:",
                        "String"
                    )
                }
            }
        }
    }
}
