package me.hanyuliu.offlectric

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class OfflectricPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configurations.create("offlectric")

        def copyTask = project.task(type: Copy, 'copyOfflineRobolectricJar') {
            from project.configurations.offlectric
            into "${project.buildDir}/robolectric"

            doLast{
                project.configurations.offlectric.dependencies.each { dep ->
                    println "${project.buildDir} ~ ${dep.properties}"
                }
            }
        }

        copyTask.group = "offlectric"
        copyTask.description = "Runs adb devices command"
    }
}
