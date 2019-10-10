package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion
import ch.tutteli.spek.extensions.TempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path

abstract class PathFeatureAssertionsSpec(
    getFeature: Feature1<Path, Int, Int>,
    get: Fun2<Path, Int, Expect<Int>.() -> Unit>,
    getNullableFeature: Feature1<Path, Int, Int?>,
    getNullable: Fun2<Path, Int, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val tempFolder = TempFolder.perTest() //or perGroup()
    registerListener(tempFolder)
    val file = tempFolder.newFile("test")

    include(object : SubjectLessSpec<Path>(describePrefix,
        getFeature.forSubjectLess(1).adjustName { "$it feature" },
        get.forSubjectLess(1) { toBe(1) }
    ) {})
    include(object : SubjectLessSpec<Path>("$describePrefix[nullable Element] ",
        getNullableFeature.forSubjectLess(1).adjustName { "$it feature" },
        getNullable.forSubjectLess(1) { toBe(null) }
    ) {})

    include(object : AssertionCreatorSpec<Path>(
        describePrefix, file,
        get.forAssertionCreatorSpec("$toBeDescr: 2", 1) { toBe(2) }
    ) {})
    include(object : AssertionCreatorSpec<Path>(
        "$describePrefix[nullable Element] ", file,
        getNullable.forAssertionCreatorSpec("$toBeDescr: 2", 1) { toBe(2) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(file)

    val fileName = DescriptionPathAssertion.FILE_NAME.getDefault()

    describeFun("${getFeature.name} feature") {
        val getFun = getFeature.lambda
        context("file $file") {
            it("can perform sub-assertion on existing index") {
                fluent.getFun(0).toBe(1)
            }
            it("non-existing index throws") {
                expect {
                    fluent.getFun(4).toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(4): $fileName")
                }
            }
        }
    }

    describeFun(get.name) {
        val getFun = get.lambda
        context("file $file") {
            it("can perform sub-assertion on existing index") {
                fluent.getFun(0) { toBe(1) }
            }
            it("non-existing index throws but shows intended sub-assertion") {
                expect {
                    fluent.getFun(4) { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(4): $fileName", "$toBeDescr: 3")
                }
            }
        }
    }
})
