package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import ch.tutteli.spek.extensions.MemoizedTempFolder
import ch.tutteli.spek.extensions.memoizedTempFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.dsl.Skip.No
import org.spekframework.spek2.dsl.Skip.Yes
import org.spekframework.spek2.dsl.TestBody
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.*
import java.nio.file.attribute.*
import java.nio.file.attribute.AclEntryPermission.*
import java.nio.file.attribute.AclEntryType.ALLOW
import java.nio.file.attribute.AclEntryType.DENY
import java.nio.file.attribute.PosixFilePermission.*
import java.util.regex.Pattern.quote

abstract class PathExpectationsSpec(
    toExist: Fun0<Path>,
    notToExist: Fun0<Path>,
    toStartWith: Fun1<Path, Path>,
    notToStartWith: Fun1<Path, Path>,
    toEndWith: Fun1<Path, Path>,
    notToEndWith: Fun1<Path, Path>,
    toBeReadable: Fun0<Path>,
    notToBeReadable: Fun0<Path>,
    toBeWritable: Fun0<Path>,
    notToBeWritable: Fun0<Path>,
    toBeExecutable: Fun0<Path>,
    notToBeExecutable: Fun0<Path>,
    toBeRegularFile: Fun0<Path>,
    toBeADirectory: Fun0<Path>,
    toBeASymbolicLink: Fun0<Path>,
    toBeAbsolute: Fun0<Path>,
    toBeRelative: Fun0<Path>,
    toBeAnEmptyDirectory: Fun0<Path>,
    toHaveTheDirectoryEntry: Fun1<Path, String>,
    toHaveTheDirectoryEntries: Fun2<Path, String, Array<out String>>,
    toHaveTheSameBinaryContentAs: Fun1<Path, Path>,
    toHaveTheSameTextualContentAs: Fun3<Path, Path, Charset, Charset>,
    toHaveTheSameTextualContentAsDefaultArgs: Fun1<Path, Path>,
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

    include(object : SubjectLessSpec<Path>(
        "$describePrefix[Path] ",
        toExist.forSubjectLess(),
        notToExist.forSubjectLess(),
        toStartWith.forSubjectLess(Paths.get("a")),
        notToStartWith.forSubjectLess(Paths.get("a")),
        toEndWith.forSubjectLess(Paths.get("a")),
        notToEndWith.forSubjectLess(Paths.get("a")),
        toBeReadable.forSubjectLess(),
        notToBeReadable.forSubjectLess(),
        toBeWritable.forSubjectLess(),
        notToBeWritable.forSubjectLess(),
        toBeExecutable.forSubjectLess(),
        toBeRegularFile.forSubjectLess(),
        toBeADirectory.forSubjectLess(),
        toBeASymbolicLink.forSubjectLess(),
        toBeAbsolute.forSubjectLess(),
        toBeRelative.forSubjectLess(),
        toBeAnEmptyDirectory.forSubjectLess(),
        toHaveTheDirectoryEntry.forSubjectLess("a"),
        toHaveTheDirectoryEntries.forSubjectLess("a", arrayOf("b", "c")),
        toHaveTheSameBinaryContentAs.forSubjectLess(Paths.get("a")),
        toHaveTheSameTextualContentAs.forSubjectLess(Paths.get("a"), Charsets.ISO_8859_1, Charsets.ISO_8859_1),
        toHaveTheSameTextualContentAsDefaultArgs.forSubjectLess(Paths.get("a")),
        parentFeature.forSubjectLess(),
        parent.forSubjectLess { },
        resolveFeature.forSubjectLess("test"),
        resolve.forSubjectLess("test") { toEqual(Paths.get("a/my.txt")) },
        fileNameFeature.forSubjectLess(),
        fileName.forSubjectLess { },
        fileNameWithoutExtensionFeature.forSubjectLess(),
        fileNameWithoutExtension.forSubjectLess { }
    ) {})

    val tempFolder by memoizedTempFolder()

    // Linux & Mac
    val ifAclNotSupported =
        if (fileSystemSupportsAcls()) No
        else Yes("ACLs are not supported on this file system")

    // Windows
    val ifPosixNotSupported =
        if (fileSystemSupportsPosixPermissions()) No
        else Yes("POSIX permissions are not supported on this file system")

    // Windows with neither symlink nor admin privileges
    val ifSymlinksNotSupported =
        if (fileSystemSupportsCreatingSymlinks()) No
        else Yes("creating symbolic links is not supported on this file system")

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fileNameDescr = FILE_NAME.getDefault()
    val fileNameWithoutExtensionDescr = FILE_NAME_WITHOUT_EXTENSION.getDefault()

    fun Suite.it(
        description: String,
        skip: Skip = No,
        forceNoLink: Skip = No,
        timeout: Long = delegate.defaultTimeout
    ): SymlinkTestBuilder {
        val skipWithLink = if (forceNoLink == No) ifSymlinksNotSupported else forceNoLink
        return SymlinkTestBuilder({ tempFolder }, skipWithLink) { prefix, innerSkip, body ->
            val skipToUse = if (skip == No) innerSkip else skip
            it(prefix + description, skipToUse, timeout, body)
        }
    }

    fun Suite.itPrintsParentAccessDeniedDetails(
        forceNoLinks: Skip = No,
        block: (Path) -> Unit
    ) {
        // this test case makes only sense on POSIX systems, where a missing execute permission on the parent means
        // that children cannot be accessed. On Windows, for example, one can still access children without any
        // permissions on the parent.
        it(
            "POSIX: prints parent permission error details",
            skip = ifPosixNotSupported,
            forceNoLink = forceNoLinks
        ) withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))

            start.whileWithPermissions(OWNER_READ, OWNER_WRITE, GROUP_READ) {
                expect {
                    block(doesNotExist)
                }.toThrow<AssertionError>().message {
                    toContain(
                        String.format(FAILURE_DUE_TO_PARENT.getDefault(), start),
                        FAILURE_DUE_TO_ACCESS_DENIED.getDefault(),
                        String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g=r o="),
                        expectedPosixOwnerAndGroupHintFor(start)
                    )
                    containsExplanationFor(maybeLink)
                }
            }
        }
    }

    fun throwingPath(): Path {
        val baseFile = tempFolder.tmpDir.resolve("throwing")

        // using spyk on baseFile and mocking #getFileSystem does not work on Java 8 on Linux (but everywhere else).
        // because of that, we use plain old manual delegation:
        return object : Path by baseFile {
            override fun getFileSystem() = throw IOException(TEST_IO_EXCEPTION_MESSAGE)
        }
    }

    fun Suite.itPrintsFileAccessExceptionDetails(block: (Path) -> Unit) {
        it("prints details if an unknown IoException is thrown") {
            expect {
                block(throwingPath())
            }.toThrow<AssertionError>().message {
                toContain(String.format(FAILURE_DUE_TO_ACCESS_EXCEPTION.getDefault(), IOException::class.simpleName))
                toContain(TEST_IO_EXCEPTION_MESSAGE)
            }
        }
    }

    fun Suite.itPrintsFileAccessProblemDetails(
        forceNoLinks: Skip = No,
        checkParentHints: Boolean = true,
        block: (Path) -> Unit
    ) {
        it(
            "prints the closest existing parent if it is a directory",
            forceNoLink = forceNoLinks
        ) withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir").toRealPath()
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val existingParentHintMessage =
                String.format(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY.getDefault(), start)
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                toContain(existingParentHintMessage)
                containsExplanationFor(maybeLink)
            }
        }

        it("explains if a parent is a file", forceNoLink = forceNoLinks) withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newFile("startFile")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val parentErrorMessage = String.format(FAILURE_DUE_TO_PARENT.getDefault(), start)
            val parentErrorDescription =
                String.format(
                    FAILURE_DUE_TO_WRONG_FILE_TYPE.getDefault(),
                    A_FILE.getDefault(),
                    A_DIRECTORY.getDefault()
                )
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                toContain(parentErrorMessage, parentErrorDescription)
                containsExplanationFor(maybeLink)
            }
        }

        it(
            "prints an explanation for link loops",
            skip = if (forceNoLinks == No) ifSymlinksNotSupported else forceNoLinks
        ) {
            val testDir = tempFolder.newDirectory("loop").toRealPath()
            val a = testDir.resolve("a")
            val b = a.createSymbolicLink(testDir.resolve("b"))
            b.createSymbolicLink(a)

            expect {
                block(a)
            }.toThrow<AssertionError> {
                messageToContain(String.format(FAILURE_DUE_TO_LINK_LOOP.getDefault(), "$a -> $b -> $a"))
            }
        }

        if (checkParentHints) {
            itPrintsParentAccessDeniedDetails(forceNoLinks, block)
        }
        itPrintsFileAccessExceptionDetails(block)
    }

    describeFun(toExist, notToExist) {
        val toExistFun = toExist.lambda
        val notToExistFun = notToExist.lambda

        context("not accessible") {
            context("non-existent path") {
                it("${toExist.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect {
                        expect(notExisting).toExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                        containsExplanationFor(maybeLink)
                    }
                }
                it("${notToExist.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect(notExisting).notToExistFun()
                }
            }

            context("${toExist.name} has failure hints") {
                itPrintsFileAccessProblemDetails { testFile ->
                    expect(testFile).toExistFun()
                }
            }

            context("${notToExist.name} has failure hints") {
                itPrintsParentAccessDeniedDetails { testFile ->
                    expect(testFile).notToExistFun()
                }

                itPrintsFileAccessExceptionDetails { testFile ->
                    expect(testFile).notToExistFun()
                }
            }
        }

        context("existing") {
            context("file") {
                it("${toExist.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("test"))
                    expect(file).toExistFun()
                }
                it("${notToExist.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("toExist-though-shouldnt"))
                    expect {
                        expect(file).notToExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
            context("directory") {
                it("${toExist.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newDirectory("test"))
                    expect(file).toExistFun()
                }

                it("${notToExist.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val folder = maybeLink.create(tempFolder.newDirectory("toExist-though-shouldnt"))
                    expect {
                        expect(folder).notToExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(toStartWith, notToStartWith) {
        val toStartWithFun = toStartWith.lambda
        val notToStartWithFun = notToStartWith.lambda

        val path = "/some/path/for/test"
        val expectPath = expect(Paths.get(path))
        context("path '$path'") {
            mapOf(
                "/some/pa" to false,
                "/some/path" to true,
                "/some/path/" to true,
                "/some/path/fo" to false,
                "/some/path/for" to true,
                "/some/path/for/" to true,
                "/some/path/for/test" to true,
                "/some/other" to false,
                "/some/path/other" to false
            ).forEach { (path, toStartWithHolds) ->
                context("compare against $path") {
                    if (toStartWithHolds) {
                        it("${toStartWith.name} - does not throw") {
                            expectPath.toStartWithFun(Paths.get(path))
                        }
                        it("${notToStartWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.notToStartWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${STARTS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${toStartWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.toStartWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${STARTS_WITH.getDefault()}:")
                            }
                        }
                        it("${notToStartWith.name} - does not throw") {
                            expectPath.notToStartWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    describeFun(toEndWith, notToEndWith) {
        val toEndWithFun = toEndWith.lambda
        val notToEndWithFun = notToEndWith.lambda

        val path = "/some/path/for/test"
        val expectPath = expect(Paths.get(path))
        context("path '$path'") {
            mapOf(
                "/some/path/for/test" to true,
                "some/path/for/test" to true,
                "me/path/for/test" to false,
                "/path/for/test" to false,
                "path/for/test" to true,
                "th/for/test" to false,
                "/for/test" to false,
                "for/test" to true,
                "or/test" to false,
                "/test" to false,
                "test" to true,
                "other/test" to false,
                "other/for/test" to false
            ).forEach { (path, toEndWithHolds) ->
                context("compare against $path") {
                    if (toEndWithHolds) {
                        it("${toEndWith.name} - does not throw") {
                            expectPath.toEndWithFun(Paths.get(path))
                        }
                        it("${notToEndWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.notToEndWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${ENDS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${toEndWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.toEndWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${ENDS_WITH.getDefault()}:")
                            }
                        }
                        it("${notToEndWith.name} - does not throw") {
                            expectPath.notToEndWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    describeFun(toBeReadable) {
        val toBeReadableFun = toBeReadable.lambda
        val expectedMessage = "$toBeDescr: ${READABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeReadableFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeReadableFun()
            }
        }

        context("readable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                expect(file).toBeReadableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                expect(folder).toBeReadableFun()
            }
        }

        context("POSIX: not readable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=wx g=x o=")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(file).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = READABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(folder).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = READABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        context("ACL: not readable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(file).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = READABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("DENY", "READ_DATA"),
                            // we only check a few of the allowed options
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(folder).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = READABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            folder.expectedAclEntryPartFor("DENY", "READ_DATA"),
                            // we only check a few of the allowed options
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(notToBeReadable) {
        val notToBeReadableFun = notToBeReadable.lambda
        val expectedMessage = "$notToBeDescr: ${READABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeReadableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not readable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeReadableFun()
            }
        }

        context("POSIX: readable", skip = ifPosixNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        context("POSIX: not readable", skip = ifPosixNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(file).notToBeReadableFun()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(folder).notToBeReadableFun()
                }
            }
        }

        context("ACL: not writable", skip = ifAclNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithAcl(TestAcls::ownerNoRead) {
                    expect(file).notToBeReadableFun()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoRead) {
                    expect(folder).notToBeReadableFun()
                }
            }
        }

        context("ACL: writable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    describeFun(toBeWritable) {
        val toBeWritableFun = toBeWritable.lambda
        val expectedMessage = "$toBeDescr: ${WRITABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeWritableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeWritableFun()
            }
        }

        context("writable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                expect(file).toBeWritableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                expect(folder).toBeWritableFun()
            }
        }

        context("POSIX: not writable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rx g= o=x")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = WRITABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = WRITABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        context("ACL: not writable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(file).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = WRITABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            file.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(folder).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = WRITABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            folder.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            folder.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(notToBeWritable) {
        val notToBeWritableFun = notToBeWritable.lambda
        val expectedMessage = "$notToBeDescr: ${WRITABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeWritableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not writeable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeWritable()
            }
        }

        context("POSIX: writable", skip = ifPosixNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        context("POSIX: not writable", skip = ifPosixNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(file).notToBeWritable()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(folder).notToBeWritable()
                }
            }
        }

        context("ACL: not writable", skip = ifAclNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect(file).notToBeWritable()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect(folder).notToBeWritable()
                }
            }
        }

        context("ACL: writable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeWritable()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    describeFun(toBeExecutable) {
        val toBeExecutableFun = toBeExecutable.lambda
        val expectedMessage = "$toBeDescr: ${EXECUTABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeExecutableFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeExecutableFun()
            }
        }

        context("POSIX: executable", skip = ifPosixNotSupported) {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(file).toBeExecutableFun()
                }
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(folder).toBeExecutableFun()
                }
            }
        }

        context("ACL: executable", skip = ifAclNotSupported) {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithAcl(TestAcls::all) {
                    expect(file).toBeExecutableFun()
                }
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect(folder).toBeExecutableFun()
                }
            }
        }

        context("POSIX: not executable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g=r o=")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_READ, GROUP_READ) {
                    expect {
                        expect(file).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = EXECUTABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_READ, GROUP_READ) {
                    expect {
                        expect(folder).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = EXECUTABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        context("ACL: not executable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect {
                        expect(file).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = EXECUTABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("DENY", "EXECUTE"),
                            // we only check a few of the allowed options
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect {
                        expect(folder).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = EXECUTABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            folder.expectedAclEntryPartFor("DENY", "EXECUTE"),
                            // we only check a few of the allowed options
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(notToBeExecutable) {
        val notToBeExecutableFun = notToBeExecutable.lambda
        val expectedMessage = "$notToBeDescr: ${EXECUTABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeExecutableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not executable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeExecutable()
            }
        }

        context("POSIX: executable", skip = ifPosixNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        context("POSIX: not executable", skip = ifPosixNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithPermissions(OWNER_READ, OTHERS_EXECUTE) {
                    expect(file).notToBeExecutable()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithPermissions(OWNER_READ, OTHERS_EXECUTE) {
                    expect(folder).notToBeExecutable()
                }
            }
        }

        context("ACL: not executable", skip = ifAclNotSupported) {
            it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect(file).notToBeExecutable()
                }
            }

            it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect(folder).notToBeExecutable()
                }
            }
        }

        context("ACL: executable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeExecutable()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeExecutable()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    describeFun(toBeRegularFile) {
        val toBeRegularFileFun = toBeRegularFile.lambda
        val expectedMessage = "$toBeDescr: ${A_FILE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeRegularFileFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeRegularFileFun()
            }
        }

        it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect(file).toBeRegularFileFun()
        }

        it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect {
                expect(folder).toBeRegularFileFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
                containsExplanationFor(maybeLink)
            }
        }
    }

    describeFun(toBeASymbolicLink) {
        val toBeASymbolicLinkFun = toBeASymbolicLink.lambda
        val expectedMessage = "$toBeDescr: ${A_SYMBOLIC_LINK.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") {
                val path = tempFolder.resolve("nonExistent")
                expect {
                    expect(path).toBeASymbolicLinkFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                }
            }

            itPrintsFileAccessProblemDetails(Yes("link resolution will not be triggered")) { testFile ->
                expect(testFile).toBeASymbolicLinkFun()
            }
        }

        it("throws an AssertionError for a file") {
            val file = tempFolder.newFile("test")
            expect {
                expect(file).toBeASymbolicLinkFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                )
            }
        }

        it("throws an AssertionError for a directory") {
            val folder = tempFolder.newDirectory("test")
            expect {
                expect(folder).toBeASymbolicLinkFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
            }
        }

        it("does not throw for a symbolic link") {
            val target = tempFolder.resolve("target")
            val link = tempFolder.newSymbolicLink("link", target)
            expect(link).toBeASymbolicLinkFun()
        }
    }

    describeFun(toBeADirectory) {
        val toBeADirectoryFun = toBeADirectory.lambda
        val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeADirectoryFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeADirectoryFun()
            }
        }

        it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).toBeADirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).toBeADirectoryFun()
        }
    }

    describeFun(toBeAbsolute) {
        val toBeAbsoluteFun = toBeAbsolute.lambda

        it("throws an AssertionError for relative path") {
            val path = Paths.get("test/bla.txt")
            expect {
                expect(path).toBeAbsoluteFun()
            }.toThrow<AssertionError> {
                messageToContain("$toBeDescr: ${ABSOLUTE_PATH.getDefault()}")
            }
        }

        it("does not throw for absolute path") {
            val path = tempFolder.newFile("test")
            expect(path).toBeAbsoluteFun()
        }
    }

    describeFun(toBeRelative) {
        val toBeRelativeFun = toBeRelative.lambda

        it("throws an AssertionError for absolute path") {
            val path = tempFolder.newFile("test")
            expect {
                expect(path).toBeRelativeFun()
            }.toThrow<AssertionError> {
                messageToContain("$toBeDescr: ${RELATIVE_PATH.getDefault()}")
            }
        }

        it("does not throw for relative path") {
            val path = Paths.get("test/bla.txt")
            expect(path).toBeRelativeFun()
        }
    }

    describeFun(toBeAnEmptyDirectory) {
        val toBeAnEmptyDirectoryFun = toBeAnEmptyDirectory.lambda
        val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"
        val expectedEmptyMessage = "$toBeDescr: ${AN_EMPTY_DIRECTORY.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeAnEmptyDirectoryFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeAnEmptyDirectoryFun()
            }
        }

        it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).toBeAnEmptyDirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        it("throws an AssertionError for a non-empty directory") withAndWithoutSymlink { maybeLink ->
            val dir = tempFolder.newDirectory("notEmpty")
            val showMax = 10
            (0 until showMax + 1).forEach {
                dir.newFile("f$it")
            }
            val folder = maybeLink.create(dir)
            expect {
                expect(folder).toBeAnEmptyDirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedEmptyMessage)
                containsExplanationFor(maybeLink)
                val sb = StringBuilder()
                // entries should be sorted but not naturally, i.e. f10 comes before f2
                val files = ((0..1) + (10..showMax) + (2 until 10)).take(showMax)
                files.forEach {
                    sb.append(".*${listBulletPoint}f$it.*$lineSeparator")
                }
                sb.append(".*${listBulletPoint}\\.\\.\\.")
                toContainRegex(sb.toString())
                notToContain("f${showMax + 1}")
            }
        }

        it("throws an AssertionError for a directory that contains an empty directory") withAndWithoutSymlink
            { maybeLink ->
                val dir = tempFolder.newDirectory("notEmpty")
                dir.newDirectory("a")
                val folder = maybeLink.create(dir)
                expect {
                    expect(folder).toBeAnEmptyDirectoryFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedEmptyMessage)
                    containsExplanationFor(maybeLink)
                    toContain("a")
                    notToContain("$listBulletPoint...")
                }
            }

        it("does not throw for an empty directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).toBeAnEmptyDirectoryFun()
        }
    }

    val hasDirectoryEntryVariations = listOf(
        DirectoryEntryVariation("directory", "directories") { entry -> newDirectory(entry) },
        DirectoryEntryVariation("file", "files") { entry -> newFile(entry) },
        DirectoryEntryVariation("symlink with existing target", "symlinks with existing targets") { entry ->
            newFile("$entry-target").createSymbolicLink(resolve(entry))
        },
        DirectoryEntryVariation("symlink with non-existing target", "symlinks with non-existing targets") { entry ->
            resolve("$entry-not-existing-target").createSymbolicLink(resolve(entry))
        }
    )

    describeFun(toHaveTheDirectoryEntry) {
        val hasDirectoryEntryFun = toHaveTheDirectoryEntry.lambda

        hasDirectoryEntryVariations.forEach { (singleName, _, createEntry) ->
            it("does not throw if the parameter is a child $singleName") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                expect(folder).hasDirectoryEntryFun("a")
            }
        }

        it("throws if the parameter does not exist") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
            expect {
                expect(folder).hasDirectoryEntryFun("fileA")
            }.toThrow<AssertionError>().message {
                toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                toContain("fileA")
            }
        }
    }

    describeFun(toHaveTheDirectoryEntries) {
        val hasDirectoryEntryFun = toHaveTheDirectoryEntries.lambda

        hasDirectoryEntryVariations.forEach { (singleName, multiName, createEntry) ->
            it("does not throw if the single parameter is a child $singleName") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                expect(folder).hasDirectoryEntryFun("a", emptyArray())
            }

            it("does not throw if two parameters are child $multiName") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                folder.createEntry("b")
                expect(folder).hasDirectoryEntryFun("a", arrayOf("b"))
            }

            it("does not throw if three parameters are child $multiName") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                folder.createEntry("b")
                folder.createEntry("c")
                expect(folder).hasDirectoryEntryFun("a", arrayOf("b", "c"))
            }

            it("it throws if the first $singleName does not exist") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file2")
                folder.createEntry("file3")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file1")
                    notToContain("file2")
                    notToContain("file3")
                }
            }

            it("it throws if the second $singleName does not exist") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file1")
                folder.createEntry("file3")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file2")
                    notToContain("file1")
                    notToContain("file3")
                }
            }

            it("it throws if the third $singleName does not exist") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file1")
                folder.createEntry("file2")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    notToContain("file2")
                    notToContain("file1")
                    toContain("file3")
                }
            }

            it("it throws if the first and third $multiName do not exist") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file2")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    notToContain("file2")
                    toContain("file1")
                    toContain("file3")
                }
            }
        }

        it("does not throw when using all entry types") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
            hasDirectoryEntryVariations.first().createEntry(folder, "file1")
            hasDirectoryEntryVariations.drop(1).forEachIndexed { index, variation ->
                variation.createEntry(folder, "file${index + 2}")
            }
            val otherEntries = (2..(hasDirectoryEntryVariations.size)).map { "file$it" }.toTypedArray()
            expect(folder).hasDirectoryEntryFun("file1", otherEntries)
        }

        it("it throws if the directory does not exist") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
            val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"

            expect {
                expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                containsExplanationFor(maybeLink)
            }
        }
    }

    describeFun(toHaveTheSameBinaryContentAs, toHaveTheSameTextualContentAs, toHaveTheSameTextualContentAsDefaultArgs) {
        val toHaveTheSameBinaryContentAsFun = toHaveTheSameBinaryContentAs.lambda
        val toHaveTheSameTextualContentAsFun = toHaveTheSameTextualContentAs.lambda
        val toHaveTheSameTextualContentAsDefaultArgsAsFun = toHaveTheSameTextualContentAsDefaultArgs.lambda

        //TODO 1.3.0 replace with Representable and remove suppression
        @Suppress("DEPRECATION")
        fun errorToHaveTheSameTextualContentAs(sourceEncoding: Charset, targetEncoding: Charset) =
            ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(HAS_SAME_TEXTUAL_CONTENT, sourceEncoding, targetEncoding).getDefault()

        context("empty content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text1"))
                val targetPath = maybeLink.create(tempFolder.newFile("text2"))
                return sourcePath to targetPath
            }

            it("${toHaveTheSameBinaryContentAs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if ISO_8859_1, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(
                    targetPath,
                    Charsets.ISO_8859_1,
                    Charsets.ISO_8859_1
                )
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-16, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.ISO_8859_1)
            }

            it("${toHaveTheSameTextualContentAsDefaultArgs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
            }
        }

        context("has same binary content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text3"))
                val targetPath = maybeLink.create(tempFolder.newFile("text4"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("same")
                return sourcePath to targetPath
            }

            it("${toHaveTheSameBinaryContentAs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_16))
                }
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-16, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(
                        targetPath,
                        Charsets.UTF_16,
                        Charsets.ISO_8859_1
                    )
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.ISO_8859_1))
                }
            }

            it("${toHaveTheSameTextualContentAsDefaultArgs.name} - does not throw if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
            }
        }

        context("has same textual content in UTF-8 and UTF-16") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text5"))
                val targetPath = maybeLink.create(tempFolder.newFile("text6"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("same", Charsets.UTF_16)
                return sourcePath to targetPath
            }

            it("${toHaveTheSameBinaryContentAs.name} - throws AssertionError") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            it("${toHaveTheSameTextualContentAs.name} - does not throw if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-16, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_8))
                }
            }

            it("${toHaveTheSameTextualContentAsDefaultArgs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }
        }

        context("has different textual content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text5"))
                val targetPath = maybeLink.create(tempFolder.newFile("text6"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("targetPath")
                return sourcePath to targetPath
            }

            it("${toHaveTheSameBinaryContentAs.name} - throws AssertionError") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            it("${toHaveTheSameTextualContentAs.name} - throws AssertionError if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_16))
                }
            }

            it("${toHaveTheSameTextualContentAsDefaultArgs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(errorToHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }
        }
    }

    describeFun(parentFeature, parent) {
        val parentFunctions = unifySignatures(parentFeature, parent)

        context("folder with parent") {
            parentFunctions.forEach { (name, parentFun, _) ->
                it("$name - toEqual(folder.parent) holds") {
                    val childFolder = tempFolder.newDirectory("child")
                    val parentFolder = childFolder.parent
                    expect(childFolder).parentFun { toEqual(parentFolder) }
                }
                it("$name - toEqual(folder) fails") {
                    expect {
                        val childFolder = tempFolder.newDirectory("child")
                        expect(childFolder).parentFun { toEqual(childFolder) }
                    }.toThrow<AssertionError> {
                        message { toContainRegex("$toEqualDescr: .*(/|\\\\)child") }
                    }
                }
            }
        }

        context("folder without parent") {
            parentFunctions.forEach { (name, parentFun, hasExtraHint) ->
                it("$name - toEqual(folder.parent) fails" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        val rootFolder = tempFolder.tmpDir.root
                        expect(rootFolder).parentFun { toEqual(Paths.get("non-existing")) }
                    }.toThrow<AssertionError> {
                        messageToContain(DOES_NOT_HAVE_PARENT.getDefault())
                        if (hasExtraHint) messageToContain("non-existing")
                    }
                }
            }
        }
    }

    describeFun(resolveFeature, resolve) {
        val resolveFunctions = unifySignatures(resolveFeature, resolve)

        context("resolve child") {
            resolveFunctions.forEach { (name, resolveFun, _) ->
                it("$name - toEqual(child) holds") {
                    val resolvedFolder = tempFolder.newDirectory("child")
                    val rootFolder = resolvedFolder.parent
                    expect(rootFolder).resolveFun("child") { toEqual(resolvedFolder) }
                }
            }
        }

        context("resolve non-existing") {
            resolveFunctions.forEach { (name, resolveFun, hasExtraHint) ->
                it("$name - toEqual(folder) fails" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        val resolvedFolder = tempFolder.newDirectory("child")
                        val rootFolder = resolvedFolder.parent
                        expect(rootFolder).resolveFun("non-existing") { toEqual(resolvedFolder) }
                    }.toThrow<AssertionError> {
                        messageToContain("non-existing")
                        if (hasExtraHint) messageToContain("child")
                    }
                }
            }
        }
    }

    describeFun(fileNameFeature, fileName) {
        val fileNameFunctions = unifySignatures(fileNameFeature, fileName)

        context("path a/my.txt") {
            fileNameFunctions.forEach { (name, fileNameFun, _) ->
                it("$name - toEqual(my.txt) holds") {
                    expect(Paths.get("a/my.txt")).fileNameFun { toEqual("my.txt") }
                }
                it("$name - toEqual(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my")).fileNameFun { toEqual("my.txt") }
                    }.toThrow<AssertionError> {
                        messageToContain("$fileNameDescr: \"my\"")
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
                it("$name - toEqual(my) holds") {
                    expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toEqual("my") }
                }
                it("$name - toEqual(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toEqual("my.txt") }
                    }.toThrow<AssertionError> {
                        messageToContain("$fileNameWithoutExtensionDescr: \"my\"")
                    }
                }
            }
        }

        val directory = "a/my/"
        context("directory $directory") {
            fileNameWithoutExtensionFunctions.forEach { (name, fileNameWithoutExtensionFun, _) ->
                it("$name - toEqual(my) holds") {
                    expect(Paths.get(directory)).fileNameWithoutExtensionFun { toEqual("my") }
                }
                it("$name - toEqual(my.txt) fails") {
                    expect {
                        expect(Paths.get("a/my/")).fileNameWithoutExtensionFun { toEqual("my.txt") }
                    }.toThrow<AssertionError> {
                        messageToContain("$fileNameWithoutExtensionDescr: \"my\"")
                    }
                }
            }
        }

        context("path with double extension") {
            fileNameWithoutExtensionFunctions.forEach { (name, fileNameWithoutExtensionFun, _) ->
                it("$name - toEqual(my.tar) holds") {
                    expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toEqual("my.tar") }
                }
                it("$name - toEqual(my) fails") {
                    expect {
                        expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toEqual("my") }
                    }.toThrow<AssertionError> {
                        messageToContain("$fileNameWithoutExtensionDescr: \"my.tar\"")
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
                    expect(Paths.get("/foo/no-extension-here")).extensionFun { toEqual("") }
                }
            }
        }

        context("Path with extension") {
            extensionFunctions.forEach { (name, extensionFun, _) ->
                it("$name - returns the extension") {
                    expect(Paths.get("/foo/something.txt")).extensionFun { toEqual("txt") }
                }
            }
        }
    }
})

private const val TEST_IO_EXCEPTION_MESSAGE = "unknown test error"

internal object TestAcls {
    // although counter-intuitive, the entries must be in this order!
    fun ownerNoRead(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, READ_DATA),
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )

    fun ownerNoWrite(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, WRITE_DATA),
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )

    fun all(owner: UserPrincipal) = listOf(
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )

    fun ownerNoExecute(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, EXECUTE),
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )
}

private fun aclEntry(type: AclEntryType, principal: UserPrincipal, vararg permissions: AclEntryPermission) =
    AclEntry.newBuilder()
        .setType(type)
        .setPrincipal(principal)
        .setPermissions(*permissions)
        .build()

/**
 * Sets the provided [permissionsToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 */
private inline fun Path.whileWithPermissions(vararg permissionsToUse: PosixFilePermission, block: () -> Unit) =
    whileWithPermissions(setOf(*permissionsToUse), block = block)

/**
 * Sets the provided [permissionsToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 */
private inline fun Path.whileWithPermissions(
    permissionsToUse: Set<PosixFilePermission>,
    block: () -> Unit
) {
    val oldPermissions = this.posixFilePersmissions
    this.setPosixFilePermissions(permissionsToUse)
    try {
        block()
    } finally {
        this.setPosixFilePermissions(oldPermissions)
    }
}

/**
 * Sets the provided [aclToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 *
 * @param aclToUse A function that gets passed the [UserPrincipal] for this file’s owner and returns an access control list
 */
private inline fun Path.whileWithAcl(aclToUse: (owner: UserPrincipal) -> List<AclEntry>, block: () -> Unit) {
    val aclView = this.getFileAttributeView<AclFileAttributeView>()
        ?: throw IllegalStateException("Failed to get ACL for $this")
    val oldAcl = aclView.acl
    aclView.acl = aclToUse(aclView.owner)
    try {
        block()
    } finally {
        aclView.acl = oldAcl
    }
}

class SymlinkTestBuilder(
    private val tempFolderProvider: () -> MemoizedTempFolder,
    private val skipWithLink: Skip,
    private val testBodyConsumer: (testPrefix: String, skip: Skip, testBody: TestBody.() -> Unit) -> Unit
) {
    /**
     * Allows to run the provided test body multiple times in order to check proper symbolic link handling:
     *  * On the first invocation of [body], the `maybeLink` parameter will be [NoLink], i.e., the test run will not use symbolic links.
     *  * On the second invocation of [body], the `maybeLink` parameter will be [SimpleLink], i.e., the test run will use a symbolic link that points to the actual subject.
     *
     *  In effect, the test body will be executed once “directly”, and once via a symbolic link to the tested path.
     *  The test body is expected to:
     *   * pass the test subject (a [Path]) through [MaybeLink.create]
     *   * call [containsExplanationFor] when checking an assertion error
     */
    internal infix fun withAndWithoutSymlink(body: TestBody.(maybeLink: MaybeLink) -> Unit) {
        callWith(NoLink(), No, body)
        callWith(SimpleLink(tempFolderProvider), skipWithLink, body)
    }

    private inline fun callWith(
        maybeLink: InternalMaybeLink,
        skip: Skip,
        crossinline body: TestBody.(MaybeLink) -> Unit
    ) {
        val prefix = maybeLink.description.run { if (length > 0) "$this: " else "" }
        testBodyConsumer(prefix, skip) {
            body(maybeLink)
            maybeLink.confirmCreateWasCalled()
        }
    }
}

internal fun expectedPosixOwnerAndGroupHintFor(path: Path): String {
    val posixAttributes = path.readAttributes<PosixFileAttributes>()
    return String.format(
        HINT_OWNER_AND_GROUP.getDefault(),
        posixAttributes.owner().name,
        posixAttributes.group().name
    )
}

internal val Path.aclOwner: UserPrincipal
    get() = this.getFileAttributeView<AclFileAttributeView>()?.owner
        ?: throw IllegalStateException("failed to read access control list of $this")

internal fun expectedAclOwnerHintFor(path: Path): String = String.format(HINT_OWNER.getDefault(), path.aclOwner.name)
internal fun Path.expectedAclEntryPartFor(type: String, permission: String) =
    "$type ${quote(this.aclOwner.name)}: .*$permission.*"

/**
 * Helper to create test cases for both with and without symbolic link handling.
 */
internal interface MaybeLink {
    fun create(path: Path): Path
    fun <T : CharSequence> checkAssertionErrorMessage(expect: Expect<T>)
}

internal abstract class InternalMaybeLink(internal val description: String) : MaybeLink {
    private var createWasCalled = false
    final override fun create(path: Path): Path {
        createWasCalled = true
        return callCheckedCreate(path)
    }

    internal abstract fun callCheckedCreate(path: Path): Path
    final override fun <T : CharSequence> checkAssertionErrorMessage(expect: Expect<T>) {
        confirmCreateWasCalled()
        callCheckedCheckAssertionErrorMessage(expect)
    }

    internal abstract fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>)
    internal fun confirmCreateWasCalled() {
        check(createWasCalled) { "Expected the symbolic link creator function to have been called!" }
    }
}

internal class NoLink : InternalMaybeLink("") {
    override fun callCheckedCreate(path: Path) = path
    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {}
}

internal class SimpleLink(private val tempFolderProvider: () -> MemoizedTempFolder) :
    InternalMaybeLink("via symbolic link") {
    private var link: Path? = null
    private var path: Path? = null
    override fun callCheckedCreate(path: Path): Path {
        this.path = path
        val tempFolder = tempFolderProvider()
        // we assume in the tests that the temporary directory does not contain symlinks, thus we resolve them here.
        val link = Files.createSymbolicLink(tempFolder.tmpDir.toRealPath().resolve("__linkTo_" + path.fileName), path)
        this.link = link
        return link
    }

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {
        expect.toContain(ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link!!, path!!).getDefault())
    }
}

internal fun <T : CharSequence> Expect<T>.containsExplanationFor(maybeLink: MaybeLink) =
    maybeLink.checkAssertionErrorMessage(this)

//TODO 1.3.0 replace with Representable and remove suppression
@Suppress("DEPRECATION")
private fun expectedPermissionTypeHintFor(type: ch.tutteli.atrium.reporting.translating.Translatable, being: ch.tutteli.atrium.reporting.translating.Translatable) = String.format(
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
    type.getDefault(),
    being.getDefault()
)

internal data class DirectoryEntryVariation(
    val singleName: String,
    val multipleName: String,
    val createEntry: Path.(entry: String) -> Path
)
