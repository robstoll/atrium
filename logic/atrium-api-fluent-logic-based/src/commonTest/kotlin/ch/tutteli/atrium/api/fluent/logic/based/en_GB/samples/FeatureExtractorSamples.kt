package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
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
                .toBeLessThan(20)    // fails
                .toBeGreaterThan(30) // not evaluated/reported because `toBeLessThan` already fails
            //                          use `.its { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun its() {
        val person = Person(name = "John Smith", age = 25)

        expect(person).its({ age }) { // subject within this expectation-group is of type Int
            toBeGreaterThan(18)
            toBeLessThan(35)
        } // subject here is back to type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).its({ age }) {
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
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kprop) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromProperty() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature(Person::name) { // subject within this expectation-group is of type String
                toStartWith("John")
                toEndWith("Smith")
            } // subject here is back to type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(Person::name) {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated, use `.feature(kprop).` if you want fail fast behaviour
            }
        }

    }

    @Test
    fun featureFromFunctionFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person)
            .feature(Person::name) // subject is now of type String
            .toStartWith("John")
            .toEndWith("Smith")

        fails {
            expect(person)
                .feature(::f)
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunction() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person).feature(::f) { // subject is now String, after function [f] is applied
            toStartWith("John")
            toEndWith("Smith")
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f) {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated, use `.feature(kfun).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithArgumentFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person)
            .feature(
                ::f,
                "Dr."
            ) // subject is now String (actually the return value of calling `f` with the given argument)
            .toStartWith("Dr. John")
            .toEndWith("Smith")

        fails {
            expect(person)
                .feature(::f, "Dr.")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun, arg1) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunctionWithArgument() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person).feature(
            ::f,
            "Dr."
        ) {// subject within this expectation-group is of type String (actually the return value of calling `f` with the given argument)
            toStartWith("Dr. John")
            toEndWith("Smith")
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.") {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated even though `toStartWith` already fails
                //                      use `.feature(kfun, arg1).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithTwoArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP"
            ) // subject is now String (actually the return value of calling `f` with the given two arguments)
            .toStartWith("Dr. John")
            .toEndWith("Smith, PMP")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun, arg1, arg2) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunctionWithTwoArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP"
            ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given two arguments)
                toStartWith("Dr. John")
                toEndWith("Smith, PMP")
            } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.", "PMP") {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated even though `toStartWith` already fails
                //                      use `.feature(kfun, arg1, arg2).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithThreeArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native"
            ) // subject is now String (actually the return value of calling `f` with the given three arguments)
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toEndWith("English level: Native")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun, a1, a2, a3) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunctionWithThreeArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native"
            ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given three arguments)
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toEndWith("English level: Native")
            } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.", "PMP", "Native") {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated even though `toStartWith` already fails
                //                      use `.feature(kfun, a1, a2, a3).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithFourArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native",
                "C1"
            ) // subject is now String (actually the return value of calling `f` with the given four arguments)
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toContain("English level: Native")
            .toEndWith("German level: C1")

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.", "PMP", "Native", "C1")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun, a1, a2, a3, a4) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunctionWithFourArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native",
                "C1"
            ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given four arguments)
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toContain("English level: Native")
                toEndWith("German level: C1")
            } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.", "PMP", "Native", "C1") {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated even though `toStartWith` already fails
                //                      use `.feature(kfun, a1, a2, a3, a4).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithFiveArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native",
                "C1",
                "B2"
            ) // subject is now String (actually the return value of calling `f` with the given five arguments)
            .toStartWith("Dr. John Smith")
            .toContain("PMP")
            .toContain("English level: Native")
            .toContain("German level: C1")
            .toEndWith("Spanish level: B2")

        fails {
            expect(person)
                .feature(::f, "Dr.", "PMP", "Native", "C1", "B2")
                .toStartWith("Kevin") // fails
                .toEndWith("Bacon")   // not evaluated/reported because `toStartWith` already fails
            //                           use `.feature(kfun, a1, a2, a3, a4, a5) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureFromFunctionWithFiveArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person)
            .feature(
                ::f,
                "Dr.",
                "PMP",
                "Native",
                "C1",
                "B2"
            ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given five arguments)
                toStartWith("Dr. John Smith")
                toContain("PMP")
                toContain("English level: Native")
                toContain("German level: C1")
                toEndWith("Spanish level: B2")
            } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature(::f, "Dr.", "PMP", "Native", "C1", "B2") {
                toStartWith("Kevin") // fails
                toEndWith("Bacon")   // still evaluated even though `toStartWith` already fails
                //                      use `.feature(kfun, a1, a2, a3, a4, a5).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureWithDescriptionFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature("Actual age", Person::age) // subject is now Int (actually 25)
            .toBeLessThan(30)
            .toBeGreaterThan(20)

        fails {
            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   * Actual age:        25
            //     - to be less than: 30
            expect(person)
                .feature("Actual age", Person::age)
                .toBeLessThan(20)    // fails
                .toBeGreaterThan(30) // not evaluated/reported because `toBeLessThan` already fails
            //                          use `.feature(descr, kprop) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureWithDescription() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature("Actual age", Person::age) {
                // subject within this expectation-group is now of type Int (actually 25)
                toBeLessThan(30)
                toBeGreaterThan(20)
            } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   * Actual age:           25
            //     - to be less than:    30
            //     - to be greater than: 30

            expect(person).feature("Actual age", Person::age) {
                toBeLessThan(20)    // fails
                toBeGreaterThan(30) // still evaluated even though `toBeLessThan` already fails
                //                     use `.feature(descr, kprop).` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureWithMetaFeatureProviderFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature { f(it::age) } // `it` refers to `person`, resulting subject is of type Int (actually 25)
            .toBeGreaterThan(20)
            .toBeLessThan(30)

        fails {
            expect(person)
                .feature { f(it::age) }
                .toBeGreaterThan(30) // fails
                .toBeLessThan(20)    // not evaluated/reported because `toBeGreaterThan` already fails
            //                          use `.feature({ extractor }) { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun featureWithMetaFeatureProvider() {
        val person = Person(name = "John Smith", age = 25)

        expect(person)
            .feature({ f(it::age) }) { // `it` refers to `person`, subject within this expectation-group is of type Int (actually 25)
                toBeGreaterThan(20)
                toBeLessThan(30)
            } // subject here is back to Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person).feature({ f(it::age) }) {
                toBeGreaterThan(30) // fails
                toBeLessThan(20)    // still evaluated even though `toBeLessThan` already fails
                //                     use `.feature({ extractor }).` if you want fail fast behaviour
            }
        }
    }

    data class Person2(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val children: Collection<Person2>
    )

    class DataGenerator {
        fun getRandomPersonsWithChildren(): List<Person2> =
            listOf(Person2("Felix", "Mendelssohn", 2, children = emptyList()))

        fun getRandomPersonsHasABugReturnsEmptyList(): List<Person2> = emptyList()
    }

    private val dataGenerator = DataGenerator()

    @Test
    fun extractSubject() {
        val persons = dataGenerator.getRandomPersonsWithChildren()
        expect(persons).toHaveElementsAndAll {
            extractSubject { person ->
                feature { f(it::children) }.notToHaveElementsOrAll {
                    because("person should at least be 16 years older than its children") {
                        feature { f(it::age) }.toBeLessThan(person.age - 16)
                    }
                }
            }
        }
    }

    @Test
    fun extractSubjectWithFailureDescription() {
        // imagine the data generator should not return an empty list
        val persons = dataGenerator.getRandomPersonsHasABugReturnsEmptyList()
        fails { // because persons is empty
            expect(persons).toHaveElementsAndAll { // fails
                // hence the sub-expectations within extractSubject cannot be evaluated
                // and instead we show the custom failure description
                extractSubject("no person defined, cannot extract subject") { person ->
                    feature { f(it::children) }.notToHaveElementsOrAll {
                        because("person should at least be 16 years older than its children") {
                            feature { f(it::age) }.toBeLessThan(person.age - 16)
                        }
                    }
                }
            }
        }
    }
}
