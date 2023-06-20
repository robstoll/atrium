//TODO 1.1.0 remove with update to tutteli-gradle-plugin 4.10.0
tasks.configureEach<AbstractArchiveTask> {
    // Ensure builds are reproducible
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
    dirMode = "775".toInt(8)
    fileMode = "664".toInt(8)
}
