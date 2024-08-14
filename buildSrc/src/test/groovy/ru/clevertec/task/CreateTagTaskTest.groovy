package ru.clevertec.task


import org.junit.jupiter.api.Test
import ru.clevertec.task.util.Tag

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class CreateTagTaskTest {
    @Test
    void testCalculateTagWithDevBranch() {
        def expected = new Tag(1, 1, 0, "")

        def actual = CreateTagTask.calculateTag("dev", "v1.0")

        assertEquals(expected, actual)
    }

    @Test
    void testCalculateTagWithQaBranch() {
        def expected = new Tag(0, 3, 0, "")

        def actual = CreateTagTask.calculateTag("qa", "v0.2-rc")

        assertEquals(expected, actual)
    }

    @Test
    void testCalculateTagWithStageBranch() {
        def expected = new Tag(3, 5, 0, "-rc")

        def actual = CreateTagTask.calculateTag("stage", "v3.4")

        assertEquals(expected, actual)
    }

    @Test
    void testCalculateTagWithMasterBranch() {
        def expected = new Tag(2, 0, 0, "")

        def actual = CreateTagTask.calculateTag("master", "v1.5-rc")

        assertEquals(expected, actual)
    }

    @Test
    void testCalculateTagWithAnotherBranch() {
        def expected = new Tag(0, 2, 0, "-SNAPSHOT")

        def actual = CreateTagTask.calculateTag("feature", "v0.1")

        assertEquals(expected, actual)
    }

    @Test
    void testCalculateTagWithIncorrectLastTag() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CreateTagTask.calculateTag("test", "none")
        )
    }
}
