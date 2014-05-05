package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Tag;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagServiceTest extends TestContext {

    @Test public void testCreateTag() {

        final String expectDesc = "A disease is an abnormal condition that affects the body of an organism.";
        final String expectName = "Disease";

        final Tag tag = new Tag(expectName, expectDesc);

        assertNull(tag.getId());
        tagService.create(tag);
        assertNotNull(tag.getId());

        // reload Tag
        final Tag expectTag = tagService.findById(tag.getId());

        assertEquals(expectName, expectTag.getName());
        assertEquals(expectDesc, expectTag.getDesc());
    }

    @Test public void testFindTagByName() {

        // choose exist Tag name
        final String name = "toothache";

        // expected data
        final Long id = 4L;
        final String desc = "Toothache is pain in or around a tooth.";

        final Tag tag = tagService.findTagByName(name);

        assertNotNull(tag);
        assertEquals(id, tag.getId());
        assertEquals(name, tag.getName());
        assertEquals(desc, tag.getDesc());
    }
}
