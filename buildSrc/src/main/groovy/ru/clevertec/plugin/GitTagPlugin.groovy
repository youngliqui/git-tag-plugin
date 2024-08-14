package ru.clevertec.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.clevertec.task.CreateTagTask

class GitTagPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("createTag", type: CreateTagTask) {
            group = "git utils"
            description = "Creates a git tag depending on the current branch"
        }
    }
}
