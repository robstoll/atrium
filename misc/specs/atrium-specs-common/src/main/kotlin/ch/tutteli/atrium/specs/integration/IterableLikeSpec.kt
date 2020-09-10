package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.specs.emptyIterableLikeTypes
import org.spekframework.spek2.style.specification.describe

abstract class IterableLikeSpec<T>(
    description: String,
    subject: T,
    funIterableLike: Expect<T>.(IterableLike) -> Expect<T>,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    describe(describePrefix + description) {
        context("context: expecting an Iterable") {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(subject).funIterableLike(listOf<T>())
                }.toThrow<IllegalArgumentException>()
            }
        }

        context("context: expecting an IterableLike") {
            emptyIterableLikeTypes.forEach { (type, input) ->
                it("passing an empty $type throws an IllegalArgumentException") {
                    expect {
                        expect(subject).funIterableLike(input)
                    }.toThrow<IllegalArgumentException>()
                }
            }
            it("passing a String instead of an IterableLike throws an IllegalArgumentException") {
                expect {
                    expect(subject).funIterableLike("test")
                }.toThrow<IllegalArgumentException> {
                    messageContains("iterableLikeToIterable accepts arguments of types Iterable, Sequence, Array")
                }
            }
        }
    }
})
