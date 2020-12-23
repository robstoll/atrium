package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion
import ch.tutteli.spek.extensions.memoizedTempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.nio.file.Path
import java.nio.file.Paths

abstract class PathFeatureAssertionsSpec(
    parentFeature: Feature0<Path, Path>,
    parent: Fun1<Path, Expect<Path>.() -> Unit>,
    resolveFeature: Feature1<Path, String, Path>,
    resolve: Fun2<Path, String, Expect<Path>.() -> Unit>,
    fileNameFeature: Feature0<Path, String>,
    fileName: Fun1<Path, Expect<String>.() -> Unit>,
    fileNameWithoutExtensionFeature: Feature0<Path, String>,
    fileNameWithoutExtension: Fun1<Path, Expect<String>.() -> Unit>,
    extensionFeature: Feature0<Path, String>,
    extension: Fun1<Path, Expect<String>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val tempFolder by memoizedTempFolder()

    include(object : SubjectLessSpec<Path>(describePrefix,
        parentFeature.forSubjectLess().adjustName { "$it feature" },
        parent.forSubjectLess { },
        resolveFeature.forSubjectLess("test").adjustName { "$it feature" },
        resolve.forSubjectLess("test") { toBe(Paths.get("a/my.txt")) },
        fileNameFeature.forSubjectLess().adjustName { "$it feature" },
        fileName.forSubjectLess { },
        fileNameWithoutExtensionFeature.forSubjectLess().adjustName { "$it feature" },
        fileNameWithoutExtension.forSubjectLess { }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fileNameDescr = DescriptionPathAssertion.FILE_NAME.getDefault()
    val fileNameWithoutExtensionDescr = DescriptionPathAssertion.FILE_NAME_WITHOUT_EXTENSION.getDefault()

    describeFun(parentFeature, parent) {
        val parentFunctions = unifySignatures(parentFeature, parent)

        context("folder with parent") {
            parentFunctions.forEach { (name, parentFun, _) ->
                it("$name - toBe(folder.parent) holds") {
                    val childFolder = tempFolder.newDirectory("child")
                    val parentFolder = childFolder.parent
                    expect(childFolder).parentFun { toBe(parentFolder) }
                }
                it("$name - toBe(folder) fails") {
                    expect {
                        val childFolder = tempFolder.newDirectory("child")
                        expect(childFolder).parentFun { toBe(childFolder) }
                    }.toThrow<AssertionError> {
                        message { containsRegex("$toBeDescr: .*(/|\\\\)child") }
                    }
                }
            }
        }

        context("folder without parent") {
            parentFunctions.forEach { (name, parentFun, hasExtraHint) ->
                it("$name - toBe(folder.parent) fails" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        val rootFolder = tempFolder.tmpDir.root
                        expect(rootFolder).parentFun { toBe(Paths.get("non-existing")) }
                    }.toThrow<AssertionError> {
                        messageContains(DescriptionPathAssertion.DOES_NOT_HAVE_PARENT.getDefault())
                        if (hasExtraHint) messageContains("non-existing")
                    }
                }
            }
        }
    }

    describeFun(resolveFeature, resolve) {
        val resolveFunctions = unifySignatures(resolveFeature, resolve)

        context("resolve child") {
            resolveFunctions.forEach { (name, resolveFun, _) ->
                it("$name - toBe(child) holds") {
                    val resolvedFolder = tempFolder.newDirectory("child")
                    val rootFolder = resolvedFolder.parent
                    expect(rootFolder).resolveFun("child") { toBe(resolvedFolder) }
                }
            }
        }

        context("resolve non-existing") {
            resolveFunctions.forEach { (name, resolveFun, hasExtraHint) ->
                it("$name - toBe(folder) fails" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        val resolvedFolder = tempFolder.newDirectory("child")
                        val rootFolder = resolvedFolder.parent
                        expect(rootFolder).resolveFun("non-existing") { toBe(resolvedFolder) }
                    }.toThrow<AssertionError> {
                        messageContains("non-existing")
                        if (hasExtraHint) messageContains("child")
                    }
                }
            }
        }
    }

    describeFun(fileNameFeature, fileName) {
        val fileNameFunctions = unifySignatures(fileNameFeature, fileName)

        context("path a/my.txt") {
            fileNameFunctions.forEach { (name, fileNameFun, _) ->
                it("$name - toBe(my.txt) holds") {
                    expect(Paths.get("a/my.txt")).fileNameFun { toBe("my.txt") }
                }
                it("$name - toBe(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my")).fileNameFun { toBe("my.txt") }
                    }.toThrow<AssertionError> {
                        messageContains("$fileNameDescr: \"my\"")
                    }
                }
            }
        }
    }

    describeFun(fileNameWithoutExtensionFeature, fileNameWithoutExtension) {
        val fileNameWithoutExtensionFunctions =
            unifySignatures(fileNameWithoutExtensionFeature, fileNameWithoutExtension)

        context("File with extension") {
            fileNameWithoutExtensionFunctions.forEach { (name, fileNameWithoutExtensionFun, _) ->
                it("$name - toBe(my) holds") {
                    expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toBe("my") }
                }
                it("$name - toBe(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toBe("my.txt") }
                    }.toThrow<AssertionError> {
                        messageContains("$fileNameWithoutExtensionDescr: \"my\"")
                    }
                }
            }
        }

        val directory = "a/my/"
        context("directory $directory") {
            fileNameWithoutExtensionFunctions.forEach { (name, fileNameWithoutExtensionFun, _) ->
                it("$name - toBe(my) holds") {
                    expect(Paths.get(directory)).fileNameWithoutExtensionFun { toBe("my") }
                }
                it("$name - toBe(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my/")).fileNameWithoutExtensionFun { toBe("my.txt") }
                    }.toThrow<AssertionError> {
                        messageContains("$fileNameWithoutExtensionDescr: \"my\"")
                    }
                }
            }
        }

        context("path with double extension") {
            fileNameWithoutExtensionFunctions.forEach { (name, fileNameWithoutExtensionFun, _) ->
                it("$name - toBe(my.tar) holds") {
                    expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toBe("my.tar") }
                }
                it("$name - toBe(my) fails") {
                    expect {
                        expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toBe("my") }
                    }.toThrow<AssertionError> {
                        messageContains("$fileNameWithoutExtensionDescr: \"my.tar\"")
                    }
                }
            }
        }
    }

    describeFun(extensionFeature, extension) {
        val extensionFunctions = unifySignatures(extensionFeature, extension)

        context("Path without extension") {
            extensionFunctions.forEach { (name, extensionFun, _) ->
                it("$name - returns empty extension") {
                    expect(Paths.get("/foo/no-extension-here")).extensionFun { toBe("") }
                }
            }
        }

        context("Path with extension") {
            extensionFunctions.forEach { (name, extensionFun, _) ->
                it("$name - returns the extension") {
                    expect(Paths.get("/foo/something.txt")).extensionFun { toBe("txt") }
                }
            }
        }
    }
})
