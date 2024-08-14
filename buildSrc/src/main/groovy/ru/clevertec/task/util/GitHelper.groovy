package ru.clevertec.task.util

class GitHelper {
    static String getCurrentBranch() {
        executeGitCommand("rev-parse --abbrev-ref HEAD").trim()
    }

    static String getLastTag() {
        executeGitCommand("describe --abbrev=0 --tags").trim()
    }

    static boolean hasCurrentStateTag() {
        executeGitCommand("describe --exact-match --tags").trim()
    }

    static boolean hasUncommittedChanges() {
        executeGitCommand("status --porcelain").trim()
    }

    static void createTag(String tagName) {
        executeGitCommand("tag $tagName")
    }

    static void pushTag(String tagName) {
        executeGitCommand("push origin $tagName")
    }

    private static String executeGitCommand(String command) {
        def process = "git $command".execute()
        process.waitFor()

        process.text
    }
}
