package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class FeatureExtractorSamples {

    data class Person(
        val name: String,
        val age: Int
    )

    @Test
    fun itsFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .its { age } // subject is now Int
            .toBeLessThan(30)
            .toBeGreaterThan(20)


        fails {
            expect(person)
                .its { age }         // subject is now Int
                .toBeLessThan(20)    // fails, subject still Person afterwards
                .toBeGreaterThan(30) // not evaluated anymore
        }
    }

    @Test
    fun its() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .its({ age }) { // subject within this block is of type Int
                toBeGreaterThan(18)
                toBeLessThan(35)
            } // subject here is back to type Person

        fails {
            expect(person)
                .its({ age }) {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toBeGreaterThan(40) // fails
                    toBeLessThan(50)    // still evaluated, use `.its.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromPropertyFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature(Person::name) // subject is now String
            .toStartWith("John")
            .toEndWith("Smith")

        fails {
            expect(person)
                .feature(Person::name)
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromProperty() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature(Person::name) { // subject is now String
                toStartWith("John")
                toEndWith("Smith")
            } // subject is now Person

        fails {
            expect(person)
                .feature(Person::name) {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }

    }

    @Test
    fun featureFromFunctionFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person)
            .feature(::f) // subject is now String, after function [f] is applied
            .toStartWith("John")
            .toEndWith("Smith")

        fails {
            expect(person)
                .feature(::f)
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunction() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person)
            .feature(::f) { // subject is now String, after function [f] is applied
                toStartWith("John")
                toEndWith("Smith")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f) {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromFunctionWithArgumentFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person)
            .feature(::f, "Dr.") // subject is now String, function [f] is applied with argument
            .toStartWith("Dr. John")
            .toEndWith("Smith")

        fails {
            expect(person)
                .feature(::f, "Dr.")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunctionWithArgument() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person)
            .feature(::f, "Dr.") { // subject is now String, function [f] is applied with argument
                toStartWith("Dr. John")
                toEndWith("Smith")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f, "Dr.") {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromFunctionWithTwoArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person)
            .feature(::f, "Dr.", "PMP") // subject is now String, function [f] is applied with two arguments
            .toStartWith("Dr. John")
            .toEndWith("Smith, PMP")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunctionWithTwoArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person)
            .feature(::f, "Dr.", "PMP") { // subject is now String, function [f] is applied with two arguments
                toStartWith("Dr. John")
                toEndWith("Smith, PMP")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP") {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromFunctionWithThreeArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native") // subject is now String, function [f] is applied with three arguments
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toEndWith("English level: Native")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunctionWithThreeArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native") { // subject is now String, function [f] is applied with three arguments
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toEndWith("English level: Native")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native") {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromFunctionWithFourArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native", "C1") // subject is now String, function [f] is applied with four arguments
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toContain("English level: Native")
            .toEndWith("German level: C1")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native", "C1")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunctionWithFourArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native", "C1") { // subject is now String, function [f] is applied with four arguments
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toContain("English level: Native")
                toEndWith("German level: C1")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native", "C1") {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureFromFunctionWithFiveArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native", "C1", "B2") // subject is now String, function [f] is applied with five arguments
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toContain("English level: Native")
            .toContain("German level: C1")
            .toEndWith("Spanish level: B2")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native", "C1", "B2")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated
        }
    }

    @Test
    fun featureFromFunctionWithFiveArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person)
            .feature(::f, "Dr.", "PMP", "Native", "C1", "B2") { // subject is now String, function [f] is applied with five arguments
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toContain("English level: Native")
                toContain("German level: C1")
                toEndWith("Spanish level: B2")
            } // subject is now Person

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native", "C1", "B2") {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toStartWith("Kevin") // fails
                    toEndWith("Bacon")   // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureWithDescriptionFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature("Actual age", Person::age) // subject is Int
            .toBeLessThan(30)
            .toBeGreaterThan(20)

        fails {
            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   Actual age: 25
            //      is less than: 30
            expect(person)
                .feature("Actual age", Person::age)
                .toBeLessThan(20)    // fails
                .toBeGreaterThan(30) // not evaluated
        }
    }

    @Test
    fun featureWithDescription() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature("Actual age", Person::age) { // subject is now Int
                toBeLessThan(30)
                toBeGreaterThan(20)
            } // subject is now Person

        fails {
            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   Actual age: 25
            //      is less than: 20
            //      is greater than: 30

            expect(person)
                .feature("Actual age", Person::age) {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toBeLessThan(20)    // fails
                    toBeGreaterThan(30) // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }

    @Test
    fun featureWithMetaFeatureProviderFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature { f(it::age) } // it refers to `person`, resulting subject is Int
            .toBeGreaterThan(20)
            .toBeLessThan(30)

        fails {
            expect(person)
                .feature { f(it::age) }
                .toBeGreaterThan(30) // fails
                .toBeLessThan(20)    // not evaluated
        }
    }

    @Test
    fun featureWithMetaFeatureProvider() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature({ f(it::age) }) { // it refers to `person`, resulting subject is Int
                toBeGreaterThan(20)
                toBeLessThan(30)
            } // subject is back to Person

        fails {
            expect(person)
                .feature({ f(it::age) }) {
                    // introduces an expectation group block
                    // all expectations are evaluated inside an expectation group block; for more details:
                    // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups

                    toBeGreaterThan(30) // fails
                    toBeLessThan(20)    // still evaluated, use `.feature.` if you want fail fast behaviour
                }
        }
    }
}
