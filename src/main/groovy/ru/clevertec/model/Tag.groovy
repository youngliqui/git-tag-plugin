package ru.clevertec.model

class Tag {
    private int major
    private int minor
    private int patch
    private String label

    Tag() {
        major = 0;
        minor = 0;
        patch = 0;
        label = "";
    }

    Tag(int major, int minor, int patch, String label) {
        this.major = major
        this.minor = minor
        this.patch = patch
        this.label = label
    }

    @Override
    String toString() {
        patch == 0 ? "v$major.$minor$label" : "v$major.$minor.$patch$label"
    }

    void incrementMajor() {
        major++
        minor = 0
        patch = 0
        label = ""
    }

    void incrementMinor() {
        minor++
        patch = 0
        label = ""
    }

    void setLabel(String label) {
        this.label = label
    }

    static Tag fromString(String tagString) {
        def matcher = tagString =~ /^v(\d+)\.(\d+)(?:\.(\d+))?(-\w+)?$/
        if (matcher) {
            int major = matcher[0][1] as Integer
            int minor = matcher[0][2] as Integer
            int patch = matcher[0][3] ? (matcher[0][3] as Integer) : 0
            String label = matcher[0][4] ?: ""
            return new Tag(major, minor, patch, label)
        }
        throw new IllegalArgumentException("Invalid tag format: $tagString")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        Tag tag = (Tag) o

        if (major != tag.major) return false
        if (minor != tag.minor) return false
        if (patch != tag.patch) return false
        if (label != tag.label) return false

        return true
    }

    int hashCode() {
        int result
        result = major
        result = 31 * result + minor
        result = 31 * result + patch
        result = 31 * result + (label != null ? label.hashCode() : 0)
        return result
    }
}
