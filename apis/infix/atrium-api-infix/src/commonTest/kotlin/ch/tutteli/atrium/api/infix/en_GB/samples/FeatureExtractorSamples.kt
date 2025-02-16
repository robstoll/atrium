package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.because
import ch.tutteli.atrium.api.infix.en_GB.extractSubject
import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.its
import ch.tutteli.atrium.api.infix.en_GB.notToHaveElementsOrAll
import ch.tutteli.atrium.api.infix.en_GB.of
import ch.tutteli.atrium.api.infix.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.toBeLessThan
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEndWith
import ch.tutteli.atrium.api.infix.en_GB.toHaveElementsAndAll
import ch.tutteli.atrium.api.infix.en_GB.toStartWith
import ch.tutteli.atrium.api.infix.en_GB.withFailureDescription
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class FeatureExtractorSamples {

    data class Person(
        val name: String, val age: Int
    )

    @Test
    fun itsFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) its { age } toBeLessThan 30 toBeGreaterThan 20
        //              |              |                | subject is still of type Int (value = 25)
        //              |              | subject is still of type Int (value = 25)
        //              | subject is now Int (value = 25)


        fails {
            expect(person) its { age } toBeLessThan 20 toBeGreaterThan 30
            //              |              |                | not reported because `toBeLessThan` fails
            //              |              | fails
            //              | subject is now Int (value = 25)
        }
    }

    @Test
    fun its() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) its feature({ age }) {
            it toBeGreaterThan 18
            it toBeLessThan 35
        } // subject here is back to type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) its feature({ age }) {
                it toBeGreaterThan 40 // fails
                it toBeLessThan 50 // still evaluated, use `its { ... }` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromPropertyFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature Person::name toStartWith "John" toEndWith "Smith"
        //              |              |                | subject is still of type String (value = John Smith)
        //              |              | subject is still of type String (value = John Smith)
        //              | subject is now String (value = John Smith)

        fails {
            expect(person) feature Person::name toStartWith "Kevin" toEndWith "Bacon"
            //              |              |                | not reported because `toStartWith` fails
            //              |              | fails
            //              | subject is now String (value = 25)
        }
    }

    @Test
    fun featureFromProperty() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature of(Person::name) { // subject within this expectation-group is of type String
            it toStartWith "John"
            it toEndWith "Smith"
        } // subject here is back to type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(Person::name) {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature Person::name ...` if you want fail fast behaviour
            }
        }

    }

    @Test
    fun featureFromFunctionFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person) feature ::f toStartWith "John" toEndWith "Smith"
        //              |              |                | subject is still of type String (value = John Smith)
        //              |              | subject is still of type String (value = John Smith)
        //              | subject is now String (value = John Smith)

        fails {
            expect(person) feature ::f toStartWith "Kevin" toEndWith "Bacon"
            //              |              |                | not reported because `toStartWith` fails
            //              |              | fails
            //              | subject is now String (value = John Smith)
        }
    }

    @Test
    fun featureFromFunctionWithCreator() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person) = person.name

        expect(person) feature of(::f) { // subject is now String, after function [f] is applied
            it toStartWith "John"
            it toEndWith "Smith"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f) {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature ::f ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithArgumentFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person) feature of(
            ::f, "Dr."
        ) toStartWith "Dr. John" toEndWith "Smith"
        // subject is now String (actually the return value of calling `f` with the given argument)

        fails {
            expect(person) feature of(
                ::f, "Dr."
            ) toStartWith "Kevin" toEndWith "Bacon"
            // fails, not evaluated/reported because `toStartWith` already fails
        }
    }

    @Test
    fun featureFromFunctionWithArgument() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String) = "$title ${person.name}"

        expect(person) feature of(
            ::f, "Dr."
        ) {// subject within this expectation-group is of type String (actually the return value of calling `f` with the given argument)
            it toStartWith "Dr. John"
            it toEndWith "Smith"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f, "Dr.") {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithTwoArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person) feature of(
            ::f, "Dr.", "PMP"
        ) toStartWith "Dr. John" toEndWith "Smith, PMP"
        // subject is now String (actually the return value of calling `f` with the given two arguments)

        fails {
            expect(person) feature of(
                ::f, "Dr.", "PMP"
            ) toStartWith "Kevin" toEndWith "Bacon"
            // fails not evaluated/reported because `toStartWith` already fails
        }
    }

    @Test
    fun featureFromFunctionWithTwoArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String) = "$title ${person.name}, $suffix"

        expect(person) feature of(
            ::f, "Dr.", "PMP"
        ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given two arguments)
            it toStartWith "Dr. John"
            it toEndWith "Smith, PMP"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f, "Dr.", "PMP") {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithThreeArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native"
        ) toStartWith "Dr. John Smith" toContain "PMP" toEndWith "English level: Native"
        // subject is now String (actually the return value of calling `f` with the given three arguments)

        fails {
            expect(person) feature of(
                ::f, "Dr.", "PMP", "Native"
            ) toStartWith "Kevin" toEndWith "Bacon" // fails
            // not evaluated/reported because `toStartWith` already fails
        }
    }

    @Test
    fun featureFromFunctionWithThreeArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String) =
            "$title ${person.name}, $suffix. English level: $english"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native"
        ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given three arguments)
            it toStartWith "Dr. John Smith"
            it toContain "PMP"
            it toEndWith "English level: Native"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f, "Dr.", "PMP", "Native") {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithFourArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native", "C1"
        ) toStartWith "Dr. John Smith" toContain "PMP" toContain "English level: Native" toEndWith "German level: C1"
        // subject is now String (actually the return value of calling `f` with the given four arguments)

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(
                ::f, "Dr.", "PMP", "Native", "C1"
            ) toStartWith "Kevin" toEndWith "Bacon" // fails
            // not evaluated/reported because `toStartWith` already fails
        }
    }

    @Test
    fun featureFromFunctionWithFourArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native", "C1"
        ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given four arguments)
            it toStartWith "Dr. John Smith"
            it toContain "PMP"
            it toContain "English level: Native"
            it toEndWith "German level: C1"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f, "Dr.", "PMP", "Native", "C1") {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureFromFunctionWithFiveArgumentsFeature() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native", "C1", "B2"
        ) toStartWith "Dr. John Smith" toContain "PMP" toContain "English level: Native" toContain "German level: C1" toEndWith "Spanish level: B2"
        // subject is now String (actually the return value of calling `f` with the given five arguments)

        fails {
            expect(person) feature of(
                ::f, "Dr.", "PMP", "Native", "C1", "B2"
            ) toStartWith "Kevin" toEndWith "Bacon" // fails
            // not evaluated/reported because `toStartWith` already fails
        }
    }

    @Test
    fun featureFromFunctionWithFiveArguments() {
        val person = Person(name = "John Smith", age = 25)

        fun f(person: Person, title: String, suffix: String, english: String, german: String, spanish: String) =
            "$title ${person.name}, $suffix. English level: $english, German level: $german, Spanish level: $spanish"

        expect(person) feature of(
            ::f, "Dr.", "PMP", "Native", "C1", "B2"
        ) { // subject within this expectation-group is of type String (actually the return value of calling `f` with the given five arguments)
            it toStartWith "Dr. John Smith"
            it toContain "PMP"
            it toContain "English level: Native"
            it toContain "German level: C1"
            it toEndWith "Spanish level: B2"
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of(::f, "Dr.", "PMP", "Native", "C1", "B2") {
                it toStartWith "Kevin" // fails
                it toEndWith "Bacon" // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureWithDescription() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature of(
            "Actual age", Person::age
        ) toBeLessThan 30 toBeGreaterThan 20 // subject is now Int (actually 25)

        fails {
            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   * Actual age:        25
            //     - to be less than: 30
            expect(person) feature of("Actual age", Person::age) toBeLessThan 20 toBeGreaterThan 30
            // fails
            // not evaluated/reported because `toBeLessThan` already fails
        }
    }

    @Test
    fun featureWithDescriptionAssertionCreator() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature of("Actual age", Person::age) {
            // subject within this expectation-group is now of type Int (actually 25)
            it toBeLessThan 30
            it toBeGreaterThan 20
        } // subject here is back type Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            // Reporting will include the description:
            // expected that subject: Person(name=John Smith, age=25)
            //   * Actual age:           25
            //     - to be less than:    30
            //     - to be greater than: 30

            expect(person) feature of("Actual age", Person::age) {
                it toBeLessThan 20    // fails
                it toBeGreaterThan 30 // still evaluated, use `feature of(...) ...` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun featureWithMetaFeatureProviderFeature() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature { f(it::age) } toBeGreaterThan 20 toBeLessThan 30
        // `it` refers to `person`, resulting subject is of type Int (actually 25)

        fails {
            expect(person) feature { f(it::age) } toBeGreaterThan 30 toBeLessThan 20
            //              |                       |                | not reported because `toBeGreaterThan` fails
            //              |                       | fails
            //              | subject is now Int (value = 25)
        }
    }

    @Test
    fun featureWithMetaFeatureProvider() {
        val person = Person(name = "John Smith", age = 25)

        expect(person) feature of({ f(it::age) }) { // `it` refers to `person`, subject within this expectation-group is of type Int (actually 25)
            it toBeGreaterThan 20
            it toBeLessThan 30
        } // subject here is back to Person

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(person) feature of({ f(it::age) }) {
                it toBeGreaterThan 30 // fails
                it toBeLessThan 20 // still evaluated, use `feature { ... } ...` if you want fail fast behaviour
            }
        }
    }

    data class Person2(
        val firstName: String, val lastName: String, val age: Int, val children: Collection<Person2>
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
        expect(persons) toHaveElementsAndAll {
            extractSubject { person ->
                feature { f(it::children) } notToHaveElementsOrAll {
                    because("person should at least be 16 years older than its children") {
                        feature { f(it::age) } toBeLessThan person.age - 16
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
            expect(persons) toHaveElementsAndAll { // fails
                // hence the sub-expectations within extractSubject cannot be evaluated
                // and instead we show the custom failure description
                withFailureDescription<Person2>("no person defined, cannot extract subject") {
                    extractSubject { person ->
                        feature { f(it::children) } notToHaveElementsOrAll {
                            because("person should at least be 16 years older than its children") {
                                feature { f(it::age) } toBeLessThan person.age - 16
                            }
                        }
                    }
                }
            }
        }
    }
}
