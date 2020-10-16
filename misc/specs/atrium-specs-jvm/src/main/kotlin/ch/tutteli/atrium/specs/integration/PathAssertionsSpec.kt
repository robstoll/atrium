package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import ch.tutteli.spek.extensions.MemoizedTempFolder
import ch.tutteli.spek.extensions.memoizedTempFolder
import io.mockk.every
import io.mockk.spyk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.dsl.Skip.No
import org.spekframework.spek2.dsl.Skip.Yes
import org.spekframework.spek2.dsl.TestBody
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.*
import java.nio.file.attribute.AclEntryPermission.*
import java.nio.file.attribute.AclEntryType.ALLOW
import java.nio.file.attribute.AclEntryType.DENY
import java.nio.file.attribute.PosixFilePermission.*
import java.util.regex.Pattern.quote

abstract class PathAssertionsSpec(
    exists: Fun0<Path>,
    existsNot: Fun0<Path>,
    startsWith: Fun1<Path, Path>,
    startsNotWith: Fun1<Path, Path>,
    endsWith: Fun1<Path, Path>,
    endsNotWith: Fun1<Path, Path>,
    isReadable: Fun0<Path>,
    isWritable: Fun0<Path>,
    isExecutable: Fun0<Path>,
    isRegularFile: Fun0<Path>,
    isDirectory: Fun0<Path>,
    isRelative: Fun0<Path>,
    hasSameBinaryContentAs: Fun1<Path, Path>,
    hasSameTextualContentAs: Fun3<Path, Path, Charset, Charset>,
    hasSameTextualContentAsDefaultArgs: Fun1<Path, Path>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Path>(
        "$describePrefix[Path] ",
        exists.forSubjectLess(),
        existsNot.forSubjectLess(),
        startsWith.forSubjectLess(Paths.get("a")),
        startsNotWith.forSubjectLess(Paths.get("a")),
        endsWith.forSubjectLess(Paths.get("a")),
        endsNotWith.forSubjectLess(Paths.get("a")),
        isReadable.forSubjectLess(),
        isWritable.forSubjectLess(),
        isExecutable.forSubjectLess(),
        isRegularFile.forSubjectLess(),
        isDirectory.forSubjectLess(),
        isRelative.forSubjectLess(),
        hasSameBinaryContentAs.forSubjectLess(Paths.get("a")),
        hasSameTextualContentAs.forSubjectLess(Paths.get("a"), Charsets.ISO_8859_1, Charsets.ISO_8859_1),
        hasSameTextualContentAsDefaultArgs.forSubjectLess(Paths.get("a"))
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

    fun Suite.it(description: String, skip: Skip = No, timeout: Long = delegate.defaultTimeout) =
        SymlinkTestBuilder({ tempFolder }, skipWithLink = ifSymlinksNotSupported) { prefix, innerSkip, body ->
            val skipToUse = if (skip == No) innerSkip else skip
            it(prefix + description, skipToUse, timeout, body)
        }

    fun Suite.itPrintsParentAccessDeniedDetails(block: (Path) -> Unit) {
        // this test case makes only sense on POSIX systems, where a missing execute permission on the parent means
        // that children cannot be accessed. On Windows, for example, one can still access children whithout any
        // permissions on the parent.
        it(
            "POSIX: prints parent permission error details",
            skip = ifPosixNotSupported
        ) withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))

            start.whileWithPermissions(OWNER_READ, OWNER_WRITE) {
                expect {
                    block(doesNotExist)
                }.toThrow<AssertionError>().message {
                    contains(
                        String.format(FAILURE_DUE_TO_PARENT.getDefault(), start),
                        FAILURE_DUE_TO_ACCESS_DENIED.getDefault(),
                        String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g= o="),
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
            override fun getFileSystem() = spyk(baseFile.fileSystem) {
                every { provider() } returns spyk(baseFile.fileSystem.provider()) {
                    every {
                        readAttributes(any(), any<Class<BasicFileAttributes>>(), *anyVararg())
                    } throws IOException(TEST_IO_EXCEPTION_MESSAGE)
                    every { checkAccess(any(), *anyVararg()) } throws IOException(TEST_IO_EXCEPTION_MESSAGE)
                }
            }
        }
    }

    fun Suite.itPrintsFileAccessExceptionDetails(block: (Path) -> Unit) {
        it("prints details if an unknown IoException is thrown") {
            expect {
                block(throwingPath())
            }.toThrow<AssertionError>().message {
                contains(String.format(FAILURE_DUE_TO_ACCESS_EXCEPTION.getDefault(), IOException::class.simpleName))
                contains(TEST_IO_EXCEPTION_MESSAGE)
            }
        }
    }

    fun Suite.itPrintsFileAccessProblemDetails(block: (Path) -> Unit) {
        it("prints the closest existing parent if it is a directory") withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir").toRealPath()
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val existingParentHintMessage =
                String.format(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY.getDefault(), start)
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                contains(existingParentHintMessage)
                containsExplanationFor(maybeLink)
            }
        }

        it("explains if a parent is a file") withAndWithoutSymlink { maybeLink ->
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
                contains(parentErrorMessage, parentErrorDescription)
                containsExplanationFor(maybeLink)
            }
        }

        it("prints an explanation for link loops", skip = ifSymlinksNotSupported) {
            val testDir = tempFolder.newDirectory("loop").toRealPath()
            val a = testDir.resolve("a")
            val b = a.createSymbolicLink(testDir.resolve("b"))
            b.createSymbolicLink(a)

            expect {
                block(a)
            }.toThrow<AssertionError> {
                messageContains(String.format(FAILURE_DUE_TO_LINK_LOOP.getDefault(), "$a -> $b -> $a"))
            }
        }

        itPrintsParentAccessDeniedDetails(block)
        itPrintsFileAccessExceptionDetails(block)
    }

    describeFun(exists, existsNot) {
        val existsFun = exists.lambda
        val existsNotFun = existsNot.lambda

        context("not accessible") {
            context("non-existent path") {
                it("${exists.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect {
                        expect(notExisting).existsFun()
                    }.toThrow<AssertionError>().message {
                        contains("${TO.getDefault()}: ${EXIST.getDefault()}")
                        containsExplanationFor(maybeLink)
                    }
                }
                it("${existsNot.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect(notExisting).existsNotFun()
                }
            }

            context("${exists.name} has failure hints") {
                itPrintsFileAccessProblemDetails { testFile ->
                    expect(testFile).existsFun()
                }
            }

            context("${existsNot.name} has failure hints") {
                itPrintsParentAccessDeniedDetails { testFile ->
                    expect(testFile).existsNotFun()
                }

                itPrintsFileAccessExceptionDetails { testFile ->
                    expect(testFile).existsNotFun()
                }
            }
        }

        context("existing") {
            context("file") {
                it("${exists.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("test"))
                    expect(file).existsFun()
                }
                it("${existsNot.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("exists-though-shouldnt"))
                    expect {
                        expect(file).existsNotFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
            context("directory") {
                it("${exists.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                    val file = maybeLink.create(tempFolder.newDirectory("test"))
                    expect(file).existsFun()
                }

                it("${existsNot.name} - throws an AssertionError") withAndWithoutSymlink { maybeLink ->
                    val folder = maybeLink.create(tempFolder.newDirectory("exists-though-shouldnt"))
                    expect {
                        expect(folder).existsNotFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(startsWith, startsNotWith) {
        val startsWithFun = startsWith.lambda
        val startsNotWithFun = startsNotWith.lambda

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
            ).forEach { (path, startsWithHolds) ->
                context("compare against $path") {
                    if (startsWithHolds) {
                        it("${startsWith.name} - does not throw") {
                            expectPath.startsWithFun(Paths.get(path))
                        }
                        it("${startsNotWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.startsNotWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageContains("${STARTS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${startsWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.startsWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageContains("${STARTS_WITH.getDefault()}:")
                            }
                        }
                        it("${startsNotWith.name} - does not throw") {
                            expectPath.startsNotWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    describeFun(endsWith, endsNotWith) {
        val endsWithFun = endsWith.lambda
        val endsNotWithFun = endsNotWith.lambda

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
            ).forEach { (path, endsWithHolds) ->
                context("compare against $path") {
                    if (endsWithHolds) {
                        it("${endsWith.name} - does not throw") {
                            expectPath.endsWithFun(Paths.get(path))
                        }
                        it("${endsNotWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.endsNotWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageContains("${ENDS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${endsWith.name} - throws an AssertionError") {
                            expect {
                                expectPath.endsWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageContains("${ENDS_WITH.getDefault()}:")
                            }
                        }
                        it("${endsNotWith.name} - does not throw") {
                            expectPath.endsNotWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    describeFun(isReadable) {
        val isReadableFun = isReadable.lambda
        val expectedMessage = "$isDescr: ${READABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isReadableFun()
                }.toThrow<AssertionError>().message {
                    contains(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isReadableFun()
            }
        }

        context("readable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                expect(file).isReadableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                expect(folder).isReadableFun()
            }
        }

        context("POSIX: not readable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=wx g=x o=")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(file).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(folder).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(file).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = READABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
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
                        expect(folder).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = READABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
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

    describeFun(isWritable) {
        val isWritableFun = isWritable.lambda
        val expectedMessage = "$isDescr: ${WRITABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isWritableFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isWritableFun()
            }
        }

        context("writable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                expect(file).isWritableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                expect(folder).isWritableFun()
            }
        }

        context("POSIX: not writable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rx g= o=x")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(folder).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(file).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = WRITABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
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
                        expect(folder).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = WRITABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
                            folder.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            folder.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(isExecutable) {
        val isExecutableFun = isExecutable.lambda
        val expectedMessage = "$isDescr: ${EXECUTABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isExecutableFun()
                }.toThrow<AssertionError>().message {
                    contains(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isExecutableFun()
            }
        }

        context("POSIX: executable", skip = ifPosixNotSupported) {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(file).isExecutableFun()
                }
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(folder).isExecutableFun()
                }
            }
        }

        context("ACL: executable", skip = ifAclNotSupported) {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithAcl(TestAcls::all) {
                    expect(file).isExecutableFun()
                }
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect(folder).isExecutableFun()
                }
            }
        }

        context("POSIX: not executable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g=r o=")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_READ, GROUP_READ) {
                    expect {
                        expect(file).isExecutableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(folder).isExecutableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
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
                        expect(file).isExecutableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = EXECUTABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
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
                        expect(folder).isExecutableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = EXECUTABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
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

    describeFun(isRegularFile) {
        val isRegularFileFun = isRegularFile.lambda
        val expectedMessage = "$isDescr: ${A_FILE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isRegularFileFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isRegularFileFun()
            }
        }

        it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect(file).isRegularFileFun()
        }

        it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect {
                expect(folder).isRegularFileFun()
            }.toThrow<AssertionError>().message {
                contains(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
                containsExplanationFor(maybeLink)
            }
        }
    }

    describeFun(isDirectory) {
        val isDirectoryFun = isDirectory.lambda
        val expectedMessage = "$isDescr: ${A_DIRECTORY.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isDirectoryFun()
                }.toThrow<AssertionError>().message {
                    contains(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isDirectoryFun()
            }
        }

        it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).isDirectoryFun()
            }.toThrow<AssertionError>().message {
                contains(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).isDirectoryFun()
        }
    }

    describeFun(isRelative) {
        val isRelativeFun = isRelative.lambda

        it("throws an AssertionError for absolute path") {
            val path = tempFolder.newFile("test")
            expect {
                expect(path).isRelativeFun()
            }.toThrow<AssertionError> {
                messageContains("$isDescr: ${RELATIVE_PATH.getDefault()}")
            }
        }

        it("does not throw for relative path") {
            val path = Paths.get("test/bla.txt")
            expect(path).isRelativeFun()
        }
    }

    describeFun(hasSameBinaryContentAs, hasSameTextualContentAs, hasSameTextualContentAsDefaultArgs) {
        val hasSameBinaryContentAsFun = hasSameBinaryContentAs.lambda
        val hasSameTextualContentAsFun = hasSameTextualContentAs.lambda
        val hasSameTextualContentAsDefaultArgsAsFun = hasSameTextualContentAsDefaultArgs.lambda

        fun errorHasSameTextualContentAs(sourceEncoding: Charset, targetEncoding: Charset) =
            TranslatableWithArgs(HAS_SAME_TEXTUAL_CONTENT, sourceEncoding, targetEncoding).getDefault()

        context("empty content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text1"))
                val targetPath = maybeLink.create(tempFolder.newFile("text2"))
                return sourcePath to targetPath
            }

            it("${hasSameBinaryContentAs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameBinaryContentAsFun(targetPath)
            }

            it("${hasSameTextualContentAs.name} - does not throw if ISO_8859_1, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.ISO_8859_1, Charsets.ISO_8859_1)
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-16, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.ISO_8859_1)
            }

            it("${hasSameTextualContentAsDefaultArgs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsDefaultArgsAsFun(targetPath)
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

            it("${hasSameBinaryContentAs.name} - does not throw") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameBinaryContentAsFun(targetPath)
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_16))
                }
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-16, ISO_8859_1 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.ISO_8859_1)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_16, Charsets.ISO_8859_1))
                }
            }

            it("${hasSameTextualContentAsDefaultArgs.name} - does not throw if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsDefaultArgsAsFun(targetPath)
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

            it("${hasSameBinaryContentAs.name} - throws AssertionError") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    contains(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            it("${hasSameTextualContentAs.name} - does not throw if UTF-8, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-16, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_8))
                }
            }

            it("${hasSameTextualContentAsDefaultArgs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
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

            it("${hasSameBinaryContentAs.name} - throws AssertionError") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    contains(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            it("${hasSameTextualContentAs.name} - throws AssertionError if UTF-16, UTF-16 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_16))
                }
            }

            it("${hasSameTextualContentAsDefaultArgs.name} - throws AssertionError if UTF-8, UTF-8 is used") withAndWithoutSymlink { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).hasSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    contains(errorHasSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
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

    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {
        expect.contains(TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link!!, path!!).getDefault())
    }
}

internal fun <T : CharSequence> Expect<T>.containsExplanationFor(maybeLink: MaybeLink) =
    maybeLink.checkAssertionErrorMessage(this)

private fun expectedPermissionTypeHintFor(type: Translatable, being: Translatable) = String.format(
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
    type.getDefault(),
    being.getDefault()
)
