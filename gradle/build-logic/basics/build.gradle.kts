plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.buildParameters)
    api(buildLibs.vlsi.crlf)
    api(buildLibs.vlsi.gradle)
    api(buildLibs.taskTree)
}
