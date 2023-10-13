package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect

class Test2 : FooTest() {

    @TestFactory
    fun subjectLessTest() = testFactories(
        subjectLessTestFactory(
            "Int --",
            fun1(Expect<Int>::toEqual).forSubjectLess(1),
        ),
        subjectLessTestFactory(
            "Int? --",
            fun1(Expect<Int?>::toEqual).withNullableSuffix().forSubjectLess(1),
        )
    )

    @TestFactory
    fun a() = testFactory2 {
        describe("blabla") {
            it("bla") {
                expect(1).toEqual(2)
            }
        }
    }
}
