package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.spek.extensions.TempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path
import java.nio.file.Paths

abstract class PathAssertionsSpec(
    verbs: AssertionVerbFactory,
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

    val expect = verbs::checkException

    describeFun(exists.name) {
        val existsFun = exists.lambda
        context("non existing") {
            it("throws AssertionError") {
                expect {
                    verbs.check(Paths.get("nonExistingFile")).existsFun()
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
                verbs.check(file).existsFun()
            }
        }
        context("existing folder") {
            it("does not throw") {
                val file = tempFolder.newFolder("test")
                verbs.check(file).existsFun()
            }
        }
    }
})

