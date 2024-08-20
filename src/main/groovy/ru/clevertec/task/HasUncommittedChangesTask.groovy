package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class HasUncommittedChangesTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void hasUncommittedChanges() {
        def extension = project.extensions.getByType(GitExtension)
        extension.hasUncommittedChanges = gitService.hasUncommittedChanges()
        println "Has uncommitted changes: $extension.hasUncommittedChanges"
    }
}
