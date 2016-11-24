package ca.jbrains.pos.learn;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

public class LearnApacheVelocity {
    @Test
    public void tutorial() throws Exception {
        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        final VelocityContext velocityContext = new VelocityContext(new HashMap<String, String>() {{
            put("placeholder", "::text content::");
        }});

        // YOU CAN'T BE SERIOUS...
        final RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        final SimpleNode rootAstNode = runtimeServices.parse(new StringReader(
                "This is a template, ${placeholder}"
        ), "::irrelevant template name::");
        final Template template = new Template();
        template.setRuntimeServices(runtimeServices);
        template.setData(rootAstNode);
        template.initDocument();

        final StringWriter canvas = new StringWriter();
        template.merge(velocityContext, canvas);

        Assert.assertEquals("This is a template, ::text content::", canvas.toString());
    }
}
