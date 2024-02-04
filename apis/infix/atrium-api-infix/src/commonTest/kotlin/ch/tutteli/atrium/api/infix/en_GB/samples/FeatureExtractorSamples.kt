package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class FeatureExtractorSamples {
    data class Person(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val children: Collection<Person>
    )

    class DataGenerator {
        fun getRandomPersonsWithChildren(): List<Person> =
            listOf(Person("Felix", "Mendelssohn", 2, children = emptyList()))

        fun getRandomPersonsHasABugReturnsEmptyList(): List<Person> = emptyList()
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
        // you can use withFailureDescription if you want to specify a custom failure description instead of using
        // the default one.

        // imagine the data generator should not return an empty list
        val persons = dataGenerator.getRandomPersonsHasABugReturnsEmptyList()
        fails { // because persons is empty
            expect(persons).toHaveElementsAndAll { // fails
                // hence the sub-expectations within extractSubject cannot be evaluated
                // and instead we show the custom failure description
                this extractSubject withFailureDescription("no person defined, cannot extract subject") { person ->
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
