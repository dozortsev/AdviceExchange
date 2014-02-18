package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Tag;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagServiceTest extends TestContext {

    @Test public void testFindTagById() {

        // choose random Tag Id
        final Long id = 1L;

        Tag tag = tagService.findById(id);

        assertNotNull(tag);
        assertEquals(id, tag.getId());
    }

    @Test public void testCreateTag() {

        Tag tag = new Tag("Doctor", "All things related to the doctor");

        assertNull(tag.getId());
        tagService.create(tag);
        assertNotNull(tag.getId());
    }
}
