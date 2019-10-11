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
    startsWith: Fun1<Path, Path>,
    startsNotWith: Fun1<Path, Path>,
    endsWith: Fun1<Path, Path>,
    endsNotWith: Fun1<Path, Path>,
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

    describeFun(exists.name, existsNot.name) {
        val existsFun = exists.lambda
        val existsNotFun = existsNot.lambda
        context("file or folder exists") {
            it("${exists.name} throws an AssertionError when file does not exist") {
                expect {
                    expect(Paths.get("nonExistingFile")).existsFun()
                }.toThrow<AssertionError> {
                    messageContains(
                        "${TO.getDefault()}: ${DescriptionPathAssertion.EXIST.getDefault()}"
                    )
                }
            }

            it("${exists.name} does not throw when file exists") {
                val file = tempFolder.newFile("test")
                expect(file).existsFun()
            }

            it("${exists.name} does not throw when folder exists") {
                val file = tempFolder.newFolder("test")
                expect(file).existsFun()
            }
        }

        context("file or folder does not exist") {
            val expectedMessageIfExisting = "${NOT_TO.getDefault()}: ${DescriptionPathAssertion.EXIST.getDefault()}"
            it("${existsNot.name} throws an AssertionError when file exists") {
                val file = tempFolder.newFile("exists-though-shouldnt")
                expect {
                    expect(file).existsNotFun()
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfExisting)
                }
            }

            it("${existsNot.name} throws an AssertionError when folder exists") {
                val folder = tempFolder.newFolder("exists-though-shouldnt")
                expect {
                    expect(folder).existsNotFun()
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfExisting)
                }
            }

            it("${existsNot.name} does not throw when file does not exist") {
                expect(Paths.get("nonExistingFile")).existsNotFun()
            }
        }
    }

    describeFun(startsWith.name, startsNotWith.name) {
        val startsWithFun = startsWith.lambda
        val startsNotWithFun = startsNotWith.lambda
        context("starts with") {
            it("${startsWith.name} does not throw when tested path starts with expected path") {
                expect(Paths.get("/some/path/for/test"))
                    .startsWithFun(Paths.get("/some/path/"))
            }

            it("${startsWith.name} throws an AssertionError when tested path does not start with expected path") {
                val expectedMessageIfNotStartsWith = "${DescriptionPathAssertion.STARTS_WITH.getDefault()}:"
                expect {
                    expect(Paths.get("/some/path/for/test"))
                        .startsWithFun(Paths.get("/other/path"))
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfNotStartsWith)
                }
            }
        }

        context("does not start with") {
            it("${startsNotWith.name} does not throw when tested path does not start with expected path") {
                expect(Paths.get("/some/path/for/test"))
                    .startsNotWithFun(Paths.get("/other/path/"))
            }

            it("${startsNotWith.name} does not match partials") {
                expect(Paths.get("/some/path/for/test"))
                    .startsNotWithFun(Paths.get("/some/pa"))
            }

            it("${startsNotWith.name} throws an AssertionError when tested path starts with expected path") {
                val expectedMessageIfStartsWith = "${DescriptionPathAssertion.STARTS_NOT_WITH.getDefault()}:"
                expect {
                    expect(Paths.get("/some/path/for/test"))
                        .startsNotWithFun(Paths.get("/some/path"))
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfStartsWith)
                }
            }
        }
    }

    describeFun(endsWith.name, endsNotWith.name) {
        val endsWithFun = endsWith.lambda
        val endsNotWithFun = endsNotWith.lambda

        context("ends with") {
            it("${endsWith.name} does not throw when tested path ends with expected path") {
                expect(Paths.get("/not/existed/for/test"))
                    .endsWithFun(Paths.get("for/test"))
            }

            it("${endsWith.name} throws an AssertionError when tested path does not end with expected path") {
                val expectedMessageIfNotEndsWith = "${DescriptionPathAssertion.ENDS_WITH.getDefault()}:"
                expect {
                    expect(Paths.get("/not/existed/for/test"))
                        .endsWithFun(Paths.get("/for/test"))
                }.toThrow<AssertionError> {
                    messageContains(expectedMessageIfNotEndsWith)
                }
            }
        }

        context("path ends not with") {
            it("${endsNotWith.name} throws an AssertionError when tested path ends with expected path") {
                expect {
                    expect(Paths.get("/path/ends/with/this"))
                        .endsNotWithFun(Paths.get("with/this"))
                }.toThrow<AssertionError> {
                    messageContains("${DescriptionPathAssertion.ENDS_NOT_WITH.getDefault()}:")
                }
            }

            it("${endsNotWith.name} does not throw when tested path does not end with expected path") {
                expect(Paths.get("/path/ends/with/this"))
                    .endsNotWithFun(Paths.get("with/another"))
            }
        }
    }
})
