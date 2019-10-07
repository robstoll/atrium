package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.spek.extensions.TempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path

abstract class PathFeatureAssertionsSpec(
    parentFeature: Feature0<Path, Path>,
    parent: Fun1<Path, Expect<Path>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val tempFolder = TempFolder.perTest()
    registerListener(tempFolder)

    include(object : SubjectLessSpec<Path>(describePrefix,
        parentFeature.forSubjectLess().adjustName { "$it feature" },
        parent.forSubjectLess() { }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    describeFun("val ${parentFeature.name}") {
        val parentVal = parentFeature.lambda

        context("Folder with parent") {
            it("toBe(folder.parent) holds") {
                val childFolder = tempFolder.newFolder("child")
                val parentFolder = childFolder.parent
                expect(childFolder).parentVal().toBe(parentFolder)
            }
            it("toBe(folder) fails") {
                expect {
                    val childFolder = tempFolder.newFolder("child")
                    expect(childFolder).parentVal().toBe(childFolder)
                }.toThrow<AssertionError> {
                    messageContains("child")
                }
            }
        }

        context("Folder without parent") {
            it("toBe(folder.parent) fails") {
                expect {
                    val rootFolder = tempFolder.tmpDir.root
                    expect(rootFolder).parentVal().toBe(rootFolder)
                }.toThrow<AssertionError> {
                    messageContains("does not have a parent")
                }
            }
        }
    }

    describeFun("fun ${parent.name}") {
        val parentFun = parent.lambda

        context("Folder with parent") {
            it("toBe(folder.parent) holds") {
                val childFolder = tempFolder.newFolder("child")
                val parentFolder = childFolder.parent
                expect(childFolder).parentFun { toBe(parentFolder) }
            }
            it("toBe(folder) fails") {
                expect {
                    val childFolder = tempFolder.newFolder("child")
                    expect(childFolder).parentFun { toBe(childFolder) }
                }.toThrow<AssertionError> {
                    messageContains("child")
                }
            }
        }

        context("Folder without parent") {
            it("toBe(folder.parent) fails") {
                expect {
                    val rootFolder = tempFolder.tmpDir.root
                    expect(rootFolder).parentFun { toBe(rootFolder) }
                }.toThrow<AssertionError> {
                    messageContains("does not have a parent")
                }
            }
        }
    }
})
