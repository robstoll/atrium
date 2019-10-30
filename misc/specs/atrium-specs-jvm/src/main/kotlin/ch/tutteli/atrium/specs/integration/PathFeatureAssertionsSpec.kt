package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
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
        fileNameFeature.forSubjectLess().adjustName { "$it feature" },
        fileName.forSubjectLess { },
        fileNameWithoutExtensionFeature.forSubjectLess().adjustName { "$it feature" },
        fileNameWithoutExtension.forSubjectLess { }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fileNameDescr = DescriptionPathAssertion.FILE_NAME.getDefault()
    val fileNameWithoutExtensionDescr = DescriptionPathAssertion.FILE_NAME_WITHOUT_EXTENSION.getDefault()
    val doesNotHaveParentDescr = DescriptionPathAssertion.DOES_NOT_HAVE_PARENT.getDefault()

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
                    messageContains(doesNotHaveParentDescr)
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
                    messageContains(doesNotHaveParentDescr)
                }
            }
        }
    }

    describeFun("val ${fileNameFeature.name}") {
        val fileNameVal = fileNameFeature.lambda

        context("path a/my.txt") {
            it("toBe(my.txt) holds") {
                expect(Paths.get("a/my.txt")).fileNameVal().toBe("my.txt")
            }
            it("toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my")).fileNameVal().toBe("my.txt")
                }.toThrow<AssertionError> {
                    messageContains("$fileNameDescr: \"my\"")
                }
            }
        }
    }

    describeFun("fun ${fileName.name}") {
        val fileNameFun = fileName.lambda

        context("path a/my.txt") {
            it("toBe(my.txt) holds") {
                expect(Paths.get("a/my.txt")).fileNameFun { toBe("my.txt") }
            }
            it("toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my")).fileNameFun { toBe("my.txt") }
                }.toThrow<AssertionError> {
                    messageContains("$fileNameDescr: \"my\"")
                }
            }
        }
    }

    describeFun("val ${fileNameWithoutExtensionFeature.name}") {
        val fileNameWithoutExtensionVal = fileNameWithoutExtensionFeature.lambda

        context("File with extension") {
            it("toBe(my) holds") {
                expect(Paths.get("a/my.txt")).fileNameWithoutExtensionVal().toBe("my")
            }
            it("toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my.txt")).fileNameWithoutExtensionVal().toBe("my.txt")
                }.toThrow<AssertionError> {
                    messageContains("$fileNameWithoutExtensionDescr: \"my\"")
                }
            }
        }
    }

    describeFun("fun ${fileNameWithoutExtension.name}") {
        val fileNameWithoutExtensionFun = fileNameWithoutExtension.lambda

        context("File with extension") {
            it("toBe(my) holds") {
                expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toBe("my") }
            }
            it("toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toBe("my.txt") }
                }.toThrow<AssertionError> {
                    messageContains("$fileNameWithoutExtensionDescr: \"my\"")
                }
            }
        }

        val directory = "a/my/"
        context("directory $directory") {
            it("toBe(my) holds") {
                expect(Paths.get(directory)).fileNameWithoutExtensionFun { toBe("my") }
            }
            it("toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my/")).fileNameWithoutExtensionFun { toBe("my.txt") }
                }.toThrow<AssertionError> {
                    messageContains("$fileNameWithoutExtensionDescr: \"my\"")
                }
            }
        }

        context("path with double extension") {
            it("toBe(my.tar) holds") {
                expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toBe("my.tar") }
            }
            it("toBe(my) fails") {
                expect {
                    expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toBe("my") }
                }.toThrow<AssertionError> {
                    messageContains("$fileNameWithoutExtensionDescr: \"my.tar\"")
                }
            }
        }
    }

    describeFun("val ${extensionFeature.name}") {
        val extensionVal = extensionFeature.lambda

        context("Path without extension") {
            it("${extensionFeature.name} is empty") {
                expect(Paths.get("/foo/no-extension-here")).extensionVal().toBe("")
            }
        }

        context("Path with extension") {
            it("${extensionFeature.name} contains the extension") {
                expect(Paths.get("/foo/something.txt")).extensionVal().toBe("txt")
            }
        }
    }

    describeFun(extension.name) {
        val extensionFun = extension.lambda

        context("Path without extension") {
            it("Returns empty extension") {
                expect(Paths.get("/foo/no-extension-here")).extensionFun { toBe("") }
            }
        }

        context("Path with extension") {
            it("Returns the extension") {
                expect(Paths.get("/foo/something.txt")).extensionFun { toBe("txt") }
            }
        }
    }
})
