// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

@Deprecated(
    "Switch to DescriptionPathProof, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof")
)
enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.ABSOLUTE_PATH")
    )
    ABSOLUTE_PATH("an absolute path"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.DOES_NOT_HAVE_PARENT")
    )
    DOES_NOT_HAVE_PARENT("!! does not have a parent"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.ENDS_NOT_WITH")
    )
    ENDS_NOT_WITH("does not end with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.ENDS_WITH")
    )
    ENDS_WITH("ends with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.EXIST")
    )
    EXIST("exist"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.EXTENSION")
    )
    EXTENSION("extension"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FILE_NAME")
    )
    FILE_NAME("file name"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.PARENT")
    )
    PARENT("parent"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FILE_NAME_WITHOUT_EXTENSION")
    )
    FILE_NAME_WITHOUT_EXTENSION("file name without extension"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.STARTS_WITH")
    )
    STARTS_WITH("starts with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.STARTS_NOT_WITH")
    )
    STARTS_NOT_WITH("does not start with"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.READABLE")
    )
    READABLE("readable"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.WRITABLE")
    )
    WRITABLE("writable"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.EXECUTABLE")
    )
    EXECUTABLE("executable"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.A_FILE")
    )
    A_FILE("a file"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.A_DIRECTORY")
    )
    A_DIRECTORY("a directory"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.AN_EMPTY_DIRECTORY")
    )
    AN_EMPTY_DIRECTORY("an empty directory"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.DIRECTORY_CONTAINS")
    )
    DIRECTORY_CONTAINS("directory contains"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.A_SYMBOLIC_LINK")
    )
    A_SYMBOLIC_LINK("a symbolic link"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.A_UNKNOWN_FILE_TYPE")
    )
    A_UNKNOWN_FILE_TYPE("a unknown file type"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_NO_SUCH_FILE")
    )
    FAILURE_DUE_TO_NO_SUCH_FILE("no file system entry exists at this location"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT")
    )
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exists at this location, but it is not %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_OWNER")
    )
    HINT_OWNER("the owner is %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_OWNER_AND_GROUP")
    )
    HINT_OWNER_AND_GROUP("the owner is %s, the group is %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_ACTUAL_POSIX_PERMISSIONS")
    )
    HINT_ACTUAL_POSIX_PERMISSIONS("the permissions are %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_ACTUAL_ACL_PERMISSIONS")
    )
    HINT_ACTUAL_ACL_PERMISSIONS("the Access Control List is:"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_PARENT")
    )
    FAILURE_DUE_TO_PARENT("failure at parent path"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_ACCESS_DENIED")
    )
    FAILURE_DUE_TO_ACCESS_DENIED("access was denied"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_ACCESS_EXCEPTION")
    )
    FAILURE_DUE_TO_ACCESS_EXCEPTION("access threw a %s:"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_WRONG_FILE_TYPE")
    )
    FAILURE_DUE_TO_WRONG_FILE_TYPE("was %s instead of %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.FAILURE_DUE_TO_LINK_LOOP")
    )
    FAILURE_DUE_TO_LINK_LOOP("found a symbolic link loop: %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_CLOSEST_EXISTING_PARENT_DIRECTORY")
    )
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("the closest existing parent directory is %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HINT_FOLLOWED_SYMBOLIC_LINK")
    )
    HINT_FOLLOWED_SYMBOLIC_LINK("followed the symbolic link %s to %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HAS_SAME_TEXTUAL_CONTENT")
    )
    HAS_SAME_TEXTUAL_CONTENT("has same textual content with encoding %s, %s"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.HAS_SAME_BINARY_CONTENT")
    )
    HAS_SAME_BINARY_CONTENT("has same binary content"),

    @Deprecated(
        "will be removed with 2.0.0 at the latest",
        ReplaceWith("ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionPathProof.RELATIVE_PATH")
    )
    RELATIVE_PATH("a relative path"),
}
