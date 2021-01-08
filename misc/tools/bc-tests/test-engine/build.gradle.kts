val junitPlatformVersion: String by rootProject.extra
val spek2Version: String by rootProject.extra

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")
                api("org.spekframework.spek2:spek-dsl-jvm:$spek2Version")
                api("org.spekframework.spek2:spek-runner-junit5:$spek2Version")
                api("org.spekframework.spek2:spek-runtime-jvm:$spek2Version")
                api(kotlin("reflect"))
            }
        }
    }
}

