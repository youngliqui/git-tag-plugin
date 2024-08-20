package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class HasCurrentStateTagTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void hasCurrentStateTag() {
        def extension = project.extensions.getByType(GitExtension)
        extension.hasCurrentStateTag = gitService.hasCurrentStateTag()
        if (extension.hasCurrentStateTag) {
            throw new GradleException("The tag has already been assigned to the current state: $extension.lastTagString")
        }
        println "Has current state tag: $extension.hasCurrentStateTag"
    }
}
