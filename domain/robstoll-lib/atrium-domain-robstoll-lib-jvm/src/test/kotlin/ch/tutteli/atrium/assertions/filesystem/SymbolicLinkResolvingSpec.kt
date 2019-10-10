package ch.tutteli.atrium.assertions.filesystem

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.explainForResolvedLink
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.fileSystemSupportsCreatingSymlinks
import ch.tutteli.atrium.translations.DescriptionPathAssertion.FAILURE_DUE_TO_LINK_LOOP
import ch.tutteli.atrium.translations.DescriptionPathAssertion.HINT_FOLLOWED_SYMBOLIC_LINK
import ch.tutteli.spek.extensions.TempFolder
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.lifecycle.CachingMode.TEST
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object SymbolicLinkResolvingSpec : Spek({
    val tempFolder = TempFolder.perTest()
    registerListener(tempFolder)

    // Windows with neither symlink nor admin privilege
    val ifSymlinksNotSupported =
        if (fileSystemSupportsCreatingSymlinks()) Skip.No else Skip.Yes("creating symbolic links is not supported on this file system")

    val testAssertion = ExpectImpl.builder.createDescriptive(Untranslatable("testAssertion"), null) { true }
    val expectListener by memoized(TEST) {
        mock<(Path) -> Assertion> {
            on { invoke(any()) } doReturn testAssertion
        }
    }

    describe("explainForResolvedLink", skip = ifSymlinksNotSupported) {
        describe("resolves correctly") {
            it("an existing file to itself") {
                val file = tempFolder.newFile("testFile")

                explainForResolvedLink(file, expectListener)
                verify(expectListener)(file)
            }

            it("an existing directory to itself") {
                val folder = tempFolder.newFile("testDir")

                explainForResolvedLink(folder, expectListener)
                verify(expectListener)(folder)
            }

            it("a non-existent path to itself") {
                val notExisting = tempFolder.tmpDir.resolve("notExisting")

                explainForResolvedLink(notExisting, expectListener)
                verify(expectListener)(notExisting)
            }

            it("a relative path to its absolute target") {
                val relativePath = Paths.get(".")

                explainForResolvedLink(relativePath, expectListener)
                verify(expectListener)(relativePath.toRealPath())
            }

            it("a symbolic link to its target") {
                val target = tempFolder.tmpDir.resolve("notExisting")
                val link = tempFolder.newSymbolicLink("link", target)

                explainForResolvedLink(link, expectListener)
                verify(expectListener)(target)
            }

            it("a relative symbolic link to its absolute target") {
                val target = tempFolder.newFile("testFile")
                val folder = tempFolder.newFolder("testFolder")
                val relativeLink =
                    Files.createSymbolicLink(folder.resolve("testLink"), Paths.get("..").resolve(target.fileName))

                explainForResolvedLink(relativeLink, expectListener)
                verify(expectListener)(target)
            }

            it("a symbolic link chain as far as possible") {
                val nowhere = tempFolder.tmpDir.resolve("dont-exist")
                val toNowhere = tempFolder.newSymbolicLink("link-to-nowhere", nowhere)
                val start = tempFolder.newSymbolicLink("start", toNowhere)

                explainForResolvedLink(start, expectListener)
                verify(expectListener)(nowhere)
            }

            it("multiple symbolic links to their target") {
                val target = tempFolder.tmpDir.resolve("notExisting")
                val grandparent = tempFolder.newFolder("__linksgrandparent")
                val parent = Files.createDirectory(grandparent.resolve("step"))
                val grandparentlink = tempFolder.newSymbolicLink("__linkTo_grandparent", grandparent)
                val innerLink = Files.createSymbolicLink(parent.resolve("__linkTo_${target.fileName}"), target)
                val innerLinkInGrandparentLink = grandparentlink.resolve(parent.fileName).resolve(innerLink.fileName)
                val linkToInnerLink = tempFolder.newSymbolicLink(
                    "__transitive_linkTo_${target.fileName}", innerLinkInGrandparentLink
                )

                explainForResolvedLink(linkToInnerLink, expectListener)
                verify(expectListener)(target)
            }
        }

        describe("explains correctly") {
            it("returns the original assertion if no link is involved") {
                val file = tempFolder.newFile("testFile")

                val resultAssertion = explainForResolvedLink(file, expectListener)
                expect(resultAssertion).isSameAs(testAssertion)
            }

            it("adds an explanation for one symbolic link") {
                val target = tempFolder.tmpDir.resolve("notExisting")
                val link = tempFolder.newSymbolicLink("link", target)

                val resultAssertion = explainForResolvedLink(link, expectListener)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(link, target) },
                        { isSameAs(testAssertion) }
                    )
            }

            it("adds explanations for a symbolic link chain as far as possible") {
                val nowhere = tempFolder.tmpDir.resolve("dont-exist")
                val toNowhere = tempFolder.newSymbolicLink("link-to-nowhere", nowhere)
                val start = tempFolder.newSymbolicLink("start", toNowhere)

                val resultAssertion = explainForResolvedLink(start, expectListener)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(start, toNowhere) },
                        { describesLink(toNowhere, nowhere) },
                        { isSameAs(testAssertion) }
                    )
            }

            it("adds explanations for multiple symbolic links") {
                val target = tempFolder.tmpDir.resolve("notExisting")
                val grandparent = tempFolder.newFolder("__linksgrandparent")
                val parent = Files.createDirectory(grandparent.resolve("step"))
                val grandparentlink = tempFolder.newSymbolicLink("__linkTo_grandparent", grandparent)
                val innerLink = Files.createSymbolicLink(parent.resolve("__linkTo_${target.fileName}"), target)
                val innerLinkInGrandparentLink =
                    grandparentlink.resolve(parent.fileName).resolve(innerLink.fileName)
                val linkToInnerLink = tempFolder.newSymbolicLink(
                    "__transitive_linkTo_${target.fileName}", innerLinkInGrandparentLink
                )

                val resultAssertion = explainForResolvedLink(linkToInnerLink, expectListener)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(linkToInnerLink, innerLinkInGrandparentLink) },
                        { describesLink(grandparentlink, grandparent) },
                        { describesLink(innerLink, target) },
                        { isSameAs(testAssertion) }
                    )
            }
        }

        it("adds an explanation for link loops") {
            val a = tempFolder.tmpDir.resolve("linkA")
            val b = tempFolder.newSymbolicLink("linkB", a)
            Files.createSymbolicLink(a, b)

            val resultAssertion = explainForResolvedLink(a, expectListener)
            expect(resultAssertion).isA<AssertionGroup>()
                .feature { p(it::assertions) }.containsExactly(
                    { describesLinkLoop(a, b, a) },
                    { isSameAs(testAssertion) }
                )
        }

        it("keeps explanations for links that are not part of the loop") {
            val a = tempFolder.tmpDir.resolve("linkA")
            val c = tempFolder.newSymbolicLink("linkC", a)
            val b = tempFolder.newSymbolicLink("linkB", c)
            Files.createSymbolicLink(a, b)
            val grandparent = tempFolder.newFolder("__linksgrandparent")
            val parent = Files.createDirectory(grandparent.resolve("step"))
            val grandparentlink = tempFolder.newSymbolicLink("__linkTo_grandparent", grandparent)
            val innerLink = Files.createSymbolicLink(parent.resolve("__linkTo_${a.fileName}"), a)
            val innerLinkInGrandparentLink = grandparentlink.resolve(parent.fileName).resolve(innerLink.fileName)
            val linkToInnerLink = tempFolder.newSymbolicLink(
                "__transitive_linkTo_${a.fileName}", innerLinkInGrandparentLink
            )

            val resultAssertion = explainForResolvedLink(linkToInnerLink, expectListener)
            expect(resultAssertion).isA<AssertionGroup>()
                .feature { p(it::assertions) }.containsExactly(
                    { describesLink(linkToInnerLink, innerLinkInGrandparentLink) },
                    { describesLink(grandparentlink, grandparent) },
                    { describesLink(innerLink, a) },
                    { describesLinkLoop(a, b, c, a) },
                    { isSameAs(testAssertion) }
                )
        }
    }
})

fun <T : Assertion> Expect<T>.describesLink(link: Path, target: Path) {
    isA<ExplanatoryAssertion> {
        feature { p(it::explanation) }.toBe(
            RawString.create(TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link, target))
        )
    }
}

fun <T : Assertion> Expect<T>.describesLinkLoop(vararg loop: Path) {
    isA<AssertionGroup> {
        feature { p(it::assertions) }.containsExactly {
            isA<ExplanatoryAssertion> {
                val expectedExplanation = TranslatableWithArgs(FAILURE_DUE_TO_LINK_LOOP, loop.joinToString(" -> "))
                feature { p(it::explanation) }.toBe(RawString.create(expectedExplanation))
            }
        }
        feature { p(it::type) }.toBe(WarningAssertionGroupType)
    }
}
