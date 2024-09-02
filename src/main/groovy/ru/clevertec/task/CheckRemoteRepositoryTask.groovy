package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class CheckRemoteRepositoryTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void checkRemoteRepository() {
        def extension = project.extensions.getByType(GitExtension)
        extension.hasRemoteRepository = gitService.hasRemoteRepository()
        if (!extension.hasRemoteRepository) {
            throw new GradleException("No remote repository found." +
                    " Please ensure that your Git repository has a remote configured.")
        }
        println "Has remote repository: $extension.hasRemoteRepository"
    }
}
