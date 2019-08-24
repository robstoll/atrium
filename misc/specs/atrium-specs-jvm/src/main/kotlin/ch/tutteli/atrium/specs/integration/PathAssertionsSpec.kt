package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.spek.extensions.TempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path
import java.nio.file.Paths

abstract class PathAssertionsSpec(
    exists: Fun0<Path>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val tempFolder = TempFolder.perTest() //or perGroup()
    registerListener(tempFolder)

    include(object : SubjectLessSpec<Path>(
        "$describePrefix[Path] ",
        exists.forSubjectLess()
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    describeFun(exists.name) {
        val existsFun = exists.lambda
        context("non existing") {
            it("throws AssertionError") {
                expect {
                    expect(Paths.get("nonExistingFile")).existsFun()
                }.toThrow<AssertionError> {
                    messageContains(
                        "exists: false",
                        "${DescriptionAnyAssertion.TO_BE.getDefault()}: true"
                    )
                }
            }
        }
        context("existing file") {
            it("does not throw") {
                val file = tempFolder.newFile("test")
                expect(file).existsFun()
            }
        }
        context("existing folder") {
            it("does not throw") {
                val file = tempFolder.newFolder("test")
                expect(file).existsFun()
            }
        }
    }
})

