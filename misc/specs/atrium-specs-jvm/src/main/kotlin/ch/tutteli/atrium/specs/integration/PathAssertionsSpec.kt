package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.NOT_TO
import ch.tutteli.atrium.translations.DescriptionBasic.TO
import ch.tutteli.atrium.translations.DescriptionPathAssertion
import ch.tutteli.spek.extensions.TempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path
import java.nio.file.Paths

abstract class PathAssertionsSpec(
    exists: Fun0<Path>,
    existsNot: Fun0<Path>,
    endsWith: Fun1<Path, Path>,
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
            it("throws an AssertionError") {
                expect {
                    expect(Paths.get("nonExistingFile")).existsFun()
                }.toThrow<AssertionError> {
                    messageContains(
                        "${TO.getDefault()}: ${DescriptionPathAssertion.EXIST.getDefault()}"
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

    describeFun(existsNot.name) {
        val existsNotFun = existsNot.lambda
        context("non existing") {
            it("does not throw") {
                expect(Paths.get("nonExistingFile")).existsNotFun()
            }
        }

        val expectedMessageIfExisting = "${NOT_TO.getDefault()}: ${DescriptionPathAssertion.EXIST.getDefault()}"

        context("existing file") {
            it("throws an AssertionError") {
                val file = tempFolder.newFile("exists-though-shouldnt")
                expect {
                    expect(file).existsNotFun()
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfExisting)
                }
            }
        }

        context("existing folder") {
            it("throws an AssertionError") {
                val folder = tempFolder.newFolder("exists-though-shouldnt")
                expect {
                    expect(folder).existsNotFun()
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfExisting)
                }
            }
        }
    }

    describeFun(endsWith.name) {
        val endsWithFun = endsWith.lambda
        context("ends with") {
            it("does not throw") {
                expect(Paths.get("/not/existed/for/test"))
                    .endsWithFun(Paths.get("for/test"))
            }
        }
        val expectedMessageIfNotEndsWith = "${TO.getDefault()}: ${DescriptionPathAssertion.ENDS_WITH.getDefault()}"

        context("not ends with") {
            it("throws an AssertionError") {
                expect {
                    expect(Paths.get("/not/existed/for/test"))
                        .endsWithFun(Paths.get("/for/test"))
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfNotEndsWith)
                }
            }
        }
    }
})

