package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Tag;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagServiceTest extends TestContext {

    @Test public void testFindTagById() {

        // choose exist Tag Id
        final Long id = 1L;

        Tag tag = tagService.findById(id);

        assertNotNull(tag);
        assertEquals(id, tag.getId());
    }

    @Test public void testCreateTag() {

        final String expectDesc = "A disease is an abnormal condition that affects the body of an organism.";
        final String expectName = "Disease";

        Tag tag = new Tag(expectName, expectDesc);

        assertNull(tag.getId());
        tagService.create(tag);
        assertNotNull(tag.getId());

        // reload Tag
        final Tag expectTag = tagService.findById(tag.getId());

        assertEquals(expectName, expectTag.getName());
        assertEquals(expectDesc, expectTag.getDesc());
    }
}
