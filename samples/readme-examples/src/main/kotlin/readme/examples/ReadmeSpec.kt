package readme.examples

//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
import ch.tutteli.atrium.api.fluent.en_GB.jdk8.exists
import ch.tutteli.atrium.api.fluent.en_GB.jdk8.isRegularFile
import ch.tutteli.atrium.api.fluent.en_GB.jdk8.isWritable
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
//snippet-mapArguments-start
import ch.tutteli.atrium.domain.builders.utils.mapArguments
//snippet-mapArguments-end
//snippet-subExpect-start
import ch.tutteli.atrium.domain.builders.utils.subExpect
//snippet-subExpect-end
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.niok.deleteRecursively
import org.spekframework.spek2.Spek
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*


/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to use:
 * ```
 * ./gr :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 */
class ReadmeSpec : Spek({

    test("ex-first") {
        //snippet-import-insert

        val x = 10
        expect(x).toBe(9)
    }

    test("ex-single") {
        // two single assertions, only first evaluated
        expect(4 + 6).isLessThan(5).isGreaterThan(10)
    }

    test("ex-group") {
        // assertion group with two assertions, both evaluated
        expect(4 + 6) {
            isLessThan(5)
            isGreaterThan(10)
        }
    }

    test("ex-toThrow1") {
        expect {
            //this block does something but eventually...
            throw IllegalArgumentException("name is empty")
        }.toThrow<IllegalStateException>()
    }

    test("ex-toThrow2") {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>().message.startsWith("firstName")
    }

    test("ex-toThrow3") {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException> {
            message { startsWith("firstName") }
        }
    }

    test("ex-notToThrow") {
        expect {
            //this block does something but eventually...
            throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
        }.notToThrow()
    }

    //snippet-Person-start
    data class Person(val firstName: String, val lastName: String, val isStudent: Boolean) {
        fun fullName() = "$firstName $lastName"
        fun nickname(includeLastName: Boolean) = when (includeLastName) {
            false -> "Mr. $firstName"
            true -> "$firstName aka. $lastName"
        }
    }

    val myPerson = Person("Robert", "Stoll", false)
    //snippet-Person-end

    test("ex-property-methods-single") {
        //snippet-Person-insert
        expect(myPerson)
            .feature({ f(it::isStudent) }) { toBe(true) } // fails, subject still Person afterwards
            .feature { f(it::fullName) }                  // not evaluated anymore, subject String afterwards
            .startsWith("rob")                            // not evaluated anymore
    }

    //@formatter:off
    test("ex-property-methods-group") {
        expect(myPerson) { // forms an assertion group block

            feature({ f(it::firstName) }) { // forms an assertion group block
                startsWith("Pe")            // fails
                endsWith("er")              // is evaluated nonetheless
            }                               // fails as a whole

            // still evaluated, as it is in outer assertion group block
            feature { f(it::lastName) }.toBe("Dummy")
        }
    }
    //@formatter:on

    test("ex-methods-args") {
        expect(myPerson)
            .feature { f(it::nickname, false) } // subject narrowed to String
            .toBe("Robert aka. Stoll")  // fails
            .startsWith("llotS")         // not evaluated anymore
    }

    //@formatter:off
    //snippet-Family-start
    data class FamilyMember(val name: String)

    data class Family(val members: List<FamilyMember>)

    val myFamily = Family(listOf(FamilyMember("Robert")))
    //snippet-Family-end
    //@formatter:on

    test("ex-arbitrary-features") {
        //snippet-Family-insert
        expect(myFamily)
            .feature("number of members", { members.size }) { toBe(1) } // subject still Family afterwards
            .feature("first member's name") { members.first().name }    // subject narrowed to String
            .toBe("Peter")
    }

    //snippet-within-funs-start
    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
        feature({ f(it::first) }) { toBe(expected) }

    fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
        feature(Pair<F, *>::first) { toBe(expected) }
    //snippet-within-funs-end

    test("ex-within-assertion-functions") {
        //snippet-within-funs-insert

        expect(listOf(1 to "a", 2 to "b")).get(10) {
            firstToBeDoneWrong(1)
            firstToBe(1)
        }
    }


    @Suppress("UNUSED_PARAMETER")
    //snippet-worst-case-start
    class WorstCase {
        val propAndFun: Int = 1
        fun propAndFun(): Int = 1

        fun overloaded(): Int = 1
        fun overloaded(b: Boolean): Int = 1
    }
    //snippet-worst-case-end

    test("code-ambiguity-problems") {
        //snippet-worst-case-insert

        expect(WorstCase()) {
            feature { p<Int>(it::propAndFun) }
            feature { f0<Int>(it::propAndFun) }
            feature { f0<Int>(it::overloaded) }
            feature { f1<Boolean, Int>(it::overloaded, true) }.toBe(1)
        }
    }


    test("ex-type-assertions-1") {
        //snippet-type-assertions-insert
        expect(x).isA<SubType1>()
            .feature { f(it::number) }
            .toBe(2)
    }
    test("ex-type-assertions-2") {
        expect(x).isA<SubType2> {
            feature { f(it::word) }.toBe("goodbye")
            feature { f(it::flag) }.toBe(false)
        }
    }
})

//@formatter:off
//snippet-type-assertions-start
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
//snippet-type-assertions-end
//@formatter:on

object ReadmeSpec2 : Spek({
    val toDelete: MutableSet<Path> = HashSet()

    afterGroup {
        toDelete.forEach { it.deleteRecursively() }
    }

    test("ex-nullable-1") {
        val slogan1: String? = "postulating assertions made easy"
        expect(slogan1).toBe(null)
    }
    test("ex-nullable-2") {
        val slogan2: String? = null
        expect(slogan2).toBe("postulating assertions made easy")
    }
    val slogan2: String? = null
    test("ex-nullable-3") {
        expect(slogan2)     // subject has type String?
            .notToBeNull()  // subject narrowed to String
            .startsWith("atrium")
    }
    test("ex-nullable-4") {
        expect(slogan2).notToBeNull { startsWith("atrium") }
    }

    test("ex-collection-short-1") {
        expect(listOf(1, 2, 2, 4)).contains(2, 3)
    }

    test("ex-collection-short-2") {
        expect(listOf(1, 2, 2, 4)).contains(
            { isLessThan(0) },
            { isGreaterThan(2).isLessThan(4) }
        )
    }

    test("ex-collection-any") {
        expect(listOf(1, 2, 3, 4)).any { isLessThan(0) }
    }
    test("ex-collection-none") {
        expect(listOf(1, 2, 3, 4)).none { isGreaterThan(2) }
    }
    test("ex-collection-all") {
        expect(listOf(1, 2, 3, 4)).all { isGreaterThan(2) }
    }

    test("ex-collection-builder-1") {
        expect(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
    }
    test("ex-collection-builder-2") {
        expect(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)
    }
    test("ex-collection-builder-3") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })
    }
    test("ex-collection-builder-4") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
    }
    test("ex-collection-builder-5") {
        expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)
    }

    test("ex-map-1") {
        expect(mapOf("a" to 1, "b" to 2)).contains("c" to 2, "a" to 1, "b" to 1)
    }
    test("ex-map-2") {
        expect(mapOf("a" to 1, "b" to 2)).contains(
            KeyValue("c") { toBe(2) },
            KeyValue("a") { isGreaterThan(2) },
            KeyValue("b") { isLessThan(2) }
        )
    }

    //snippet-SimplePerson-start
    data class Person(val firstName: String, val lastName: String, val age: Int)
    //snippet-SimplePerson-end

    test("ex-map-3") {
        //snippet-SimplePerson-insert
        val bernstein = Person("Leonard", "Bernstein", 50)
        expect(mapOf("bernstein" to bernstein))
            .getExisting("bernstein") {
                feature { f(it::firstName) }.toBe("Leonard")
                feature { f(it::age) }.toBe(60)
            }
            .getExisting("einstein") {
                feature { f(it::firstName) }.toBe("Albert")
            }
    }
    test("ex-map-4") {
        expect(mapOf("a" to 1, "b" to 2)) {
            keys { all { startsWith("a") } }
            values { none { isGreaterThan(1) } }
        }
    }
    test("ex-map-5") {
        expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().contains.inOrder.only.entries(
            { isKeyValue("a", 1) },
            {
                key.startsWith("a")
                value.isGreaterThan(2)
            }
        )
    }

    test("ex-path-exsits") {
        expect(Paths.get("/usr/bin/noprogram")).exists()
    }

    test("ex-path-writable") {
        expect(Paths.get("/root/.ssh/config")).isWritable()
    }

    val tmpdir = Paths.get(System.getProperty("java.io.tmpdir"))
    test("ex-path-symlink-and-parent-not-folder") {
        val directory = Files.createDirectory(tmpdir.resolve("atrium-path"))
        val file = Files.createFile(directory.resolve("file"))
        val filePointer = Files.createSymbolicLink(directory.resolve("directory"), file)

        expect(filePointer.resolve("subfolder/file")).isRegularFile()
    }
    toDelete.add(tmpdir.resolve("atrium-path"))

    //snippet-data-driven-1-start
    fun myFun(i: Int) = (i + 97).toChar()
    //snippet-data-driven-1-end

    test("ex-data-driven-1") {
        //snippet-data-driven-1-insert

        expect("calling myFun with...") {
            mapOf(
                1 to 'a',
                2 to 'c',
                3 to 'e'
            ).forEach { (arg, result) ->
                feature { f(::myFun, arg) }.toBe(result)
            }
        }
    }

    test("ex-data-driven-2") {
        //snippet-subExpect-insert

        expect("calling myFun with ...") {
            mapOf(
                1 to subExpect<Char> { isLessThan('f') },
                2 to subExpect { toBe('c') },
                3 to subExpect { isGreaterThan('e') }
            ).forEach { (arg, assertionCreator) ->
                feature({ f(::myFun, arg) }, assertionCreator)
            }
        }
    }

    //snippet-data-driven-3-start
    fun myNullableFun(i: Int) = if (i > 0) i.toString() else null
    //snippet-data-driven-3-end

    test("ex-data-driven-3") {
        //snippet-data-driven-3-insert

        expect("calling myNullableFun with ...") {
            mapOf(
                Int.MIN_VALUE to subExpect<String> { contains("min") },
                -1 to null,
                0 to null,
                1 to subExpect { toBe("1") },
                2 to subExpect { endsWith("2") },
                Int.MAX_VALUE to subExpect { toBe("max") }
            ).forEach { (arg, assertionCreatorOrNull) ->
                feature { f(::myNullableFun, arg) }.toBeNullIfNullGivenElse(assertionCreatorOrNull)
            }
        }
    }


    test("exs-add-info-1") {
        expect(listOf(1, 2, 3)).contains.inOrder.only.values(1, 3)
    }
    test("exs-add-info-2") {
        expect(9.99f).toBeWithErrorTolerance(10.0f, 0.01f)
    }
    test("ex-add-info-3") {
        expect {
            try {
                throw UnsupportedOperationException("not supported")
            } catch (t: Throwable) {
                throw IllegalArgumentException("no no no...", t)
            }
        }.toThrow<IllegalStateException> { messageContains("no no no") }
    }

    test("ex-pitfall-1") {
        expect(BigDecimal.TEN).isEqualIncludingScale(BigDecimal("10.0"))
    }
    test("ex-pitfall-2") {
        expect(listOf(1)).get(0) {}
    }


    //snippet-own-boolean-1-start
    fun Expect<Int>.isMultipleOf(base: Int) =
        createAndAddAssertion("is multiple of", base) { it % base == 0 }
    //snippet-own-boolean-1-end
    test("code-own-boolean-1") {
        //snippet-own-boolean-1-insert
    }
    test("ex-own-boolean-1") {
        expect(12).isMultipleOf(5)
    }

    //snippet-own-boolean-2-start
    fun Expect<Int>.isEven() =
        createAndAddAssertion("is", RawString.create("an even number")) { it % 2 == 0 }
    //snippet-own-boolean-2-end
    test("code-own-boolean-2") {
        //snippet-own-boolean-2-insert
    }
    test("ex-own-boolean-2") {
        expect(13).isEven()
    }
})


object Between1 : Spek({
    test("code-own-compose-1") {
        fun <T : Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
            isGreaterThanOrEqual(lowerBoundInclusive).and.isLessThan(upperBoundExclusive)
    }
})

object Between2 : Spek({
    test("code-own-compose-2") {
        fun <T : Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
            addAssertionsCreatedBy {
                isGreaterThanOrEqual(lowerBoundInclusive)
                isLessThan(upperBoundExclusive)
            }
    }
})

object OwnPerson : Spek({


    test("code-own-compose-3a") {
        //snippet-own-Person-insert
    }

    //snippet-own-compose-3b-start
    fun Expect<Person>.hasNumberOfChildren(number: Int): Expect<Person> = apply {
        feature(Person::children) { hasSize(number) }
    }
    //snippet-own-compose-3b-end
    test("code-own-compose-3b") {
        //snippet-own-compose-3b-insert
    }

    test("ex-own-compose-3") {
        expect(Person("Susanne", "Whitley", 43, listOf()))
            .hasNumberOfChildren(2)
    }

    //snippet-own-compose-4-start
    fun Expect<Person>.hasAdultChildren(): Expect<Person> = apply {
        feature(Person::children) {
            all { feature(Person::age).isGreaterThanOrEqual(18) }
        }
    }
    //snippet-own-compose-4-end
    test("code-own-compose-4") {
        //snippet-own-compose-4-insert
    }
    test("ex-own-compose-4") {
        expect(Person("Susanne", "Whitley", 43, listOf()))
            .hasAdultChildren()
    }


    test("code-own-compose-5") {
        //snippet-children-insert
    }
    //@formatter:off
    test("ex-own-compose-5"){
        expect(Person("Susanne", "Whitley", 43, listOf(Person("Petra", "Whitley", 12, listOf()))))
            .children { // using the fun -> assertion group, ergo sub-assertions don't fail fast
                none { feature { f(it::firstName) }.startsWith("Ro") }
                all { feature { f(it::lastName) }.toBe("Whitley") }
            } // subject is still Person here
            .apply { // only evaluated because the previous assertion group holds
                children  // using the val -> subsequent assertions are about children and fail fast
                    .hasSize(2)
                    .any { feature { f(it::age) }.isGreaterThan(18) }
            } // subject is still Person here due to the `apply`
            .children // using the val -> subsequent assertions are about children and fail fast
            .hasSize(2)
    }
    //@formatter:on


    //snippet-own-compose-6-start
    fun <T : List<Pair<String, String>>> Expect<T>.areNamesOf(
        person: Person, vararg otherPersons: Person
    ): Expect<T> {
        val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
        return contains.inAnyOrder.only.values(pair, *otherPairs)
    }
    //snippet-own-compose-6-end

    test("code-own-compose-6") {
        //snippet-mapArguments-insert

        //snippet-own-compose-6-insert
    }
    test("code-own-compose-7") {
        fun <T : List<Pair<String, String>>> Expect<T>.sameInitialsAs(
            person: Person, vararg otherPersons: Person
        ): Expect<T> {
            val (first, others) = mapArguments(person, otherPersons).toExpect<Pair<String, String>> {
                first.startsWith(it.firstName[0].toString())
                second.startsWith(it.lastName[0].toString())
            }
            return contains.inOrder.only.entries(first, *others)
        }
    }
})

//snippet-own-Person-start
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val children: Collection<Person>
    // ...  and others
)
//snippet-own-Person-end

//snippet-children-start
val Expect<Person>.children: Expect<Collection<Person>> get() = feature(Person::children)

fun Expect<Person>.children(assertionCreator: Expect<Collection<Person>>.() -> Unit): Expect<Person> =
    feature(Person::children, assertionCreator)
//snippet-children-end


object I18n : Spek({

    test("code-i18n-1") {
        fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
            createAndAddAssertion(DescriptionIntAssertion.IS_MULTIPLE_OF, base) { it % base == 0 }

        //snippet-DescriptionIntAssertion-insert
    }

    test("code-i18n-2") {
        fun Expect<Int>.isEven(): Expect<Int> =
            createAndAddAssertion(DescriptionBasic.IS, RawString.create(DescriptionIntAssertions.EVEN)) { it % 2 == 0 }

        //snippet-DescriptionIntAssertions-insert
    }

    //snippet-i18n-3a-start
    fun _isMultipleOf(container: Expect<Int>, base: Int): Assertion =
        ExpectImpl.builder.createDescriptive(container, DescriptionIntAssertion.IS_MULTIPLE_OF, base) {
            it % base == 0
        }
    //snippet-i18n-3a-end

    test("code-i18n-3a") {
        //snippet-i18n-3a-insert
    }
    test("code-i18n-3b") {
        fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
            addAssertion(_isMultipleOf(this, base))
    }
    test("code-i18n-3c") {
        fun Expect<Int>.istVielfachesVon(base: Int): Expect<Int> =
            addAssertion(_isMultipleOf(this, base))
    }
})

//snippet-DescriptionIntAssertion-start
enum class DescriptionIntAssertion(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
//snippet-DescriptionIntAssertion-end


//snippet-DescriptionIntAssertions-start
enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
//snippet-DescriptionIntAssertions-end

object Faq : Spek({
    test("code-faq-1") {
        expect(sequenceOf(1, 2, 3)).asIterable().contains(2)
    }
    test("code-faq-2") {
        expect(sequenceOf(1, 2, 3)).feature { f(it::asIterable) }.contains(2)
    }
})
