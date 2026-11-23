// Used by scripts/build-samples-against-head.sh when a sample is built.
//
// Adds mavenLocal() to the repositories of the sample, so that it resolves atrium from maven local
// without the sample having to declare it itself. Note that the repository is added after the sample
// was evaluated so that it is the last repository to be checked. Otherwise, artifacts present in
// maven local without Gradle module metadata (e.g. installed via maven install) would shadow the
// ones published with metadata to maven central.

import org.gradle.api.Action
import org.gradle.api.Project

gradle.afterProject(
    Action<Project> {
        repositories.mavenLocal()
    }
)
