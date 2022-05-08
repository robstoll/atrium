// Note that this project is not part of the project per default.
// you need to specify the environment variable BC in order that this project (as well as the subprojects)
// are included -> alternatively, you can remove the `if` in settings.gradle.kts (search for System.getenv("BC"))

val junitPlatformVersion: String by rootProject.extra
val spekVersion: String by rootProject.extra

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")
                api("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                api("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
                api("org.spekframework.spek2:spek-runtime-jvm:$spekVersion")
                api(kotlin("reflect"))
            }
        }
    }
}

