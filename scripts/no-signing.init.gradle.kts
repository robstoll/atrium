// Used by scripts/build-samples-against-head.sh when atrium is published to maven local.
//
// Disables the sign tasks of atrium, as the artifacts are only used locally to build the samples
// against the current HEAD version of atrium (nobody consumes signed artifacts).

import org.gradle.api.Action
import org.gradle.api.execution.TaskExecutionGraph
import org.gradle.plugins.signing.Sign

gradle.taskGraph.whenReady(
    Action<TaskExecutionGraph> {
        allTasks.filterIsInstance<Sign>().forEach { it.enabled = false }
    }
)
