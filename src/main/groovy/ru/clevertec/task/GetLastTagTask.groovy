package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import ru.clevertec.extension.GitExtension
import ru.clevertec.service.GitService

class GetLastTagTask extends DefaultTask {
    @Input
    GitService gitService

    @TaskAction
    void getLastTag() {
        def extension = project.extensions.getByType(GitExtension)
        extension.lastTagString = gitService.getLastTag()
        println "Last tag: $extension.lastTagString"
    }
}
