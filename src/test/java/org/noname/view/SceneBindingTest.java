package org.noname.view;

import org.junit.Assert;
import org.junit.Test;
import org.noname.entities.EmployeeSimple;
import org.noname.entities.ResumeLink;

import java.util.Map;

import static org.junit.Assert.*;

public class SceneBindingTest {

    @Test
    public void extract() {

        ResumeLink link = new ResumeLink("title", "link");
        EmployeeSimple instance = new EmployeeSimple(link, 29, null);


        Map<String, Object> extract = SceneBinding.extract(instance, "");
        Assert.assertEquals(extract.get(".resumeLink.jobTitle"), "title");
    }
}