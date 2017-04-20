package com.kraluk.greminder.rest;

import com.kraluk.greminder.util.AppProfile.AppFeature;
import com.kraluk.greminder.util.Version;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.kraluk.greminder.util.AppProfile.TEST;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test suite for class {@link MaintenanceResource}
 *
 * @author lukasz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles({TEST, AppFeature.SCHEDULER_DISABLED})
public class MaintenanceResourceIntegrationTests {

    private static final MediaType CONTENT_TYPE =
        new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPingAndGetPong() throws Exception {
        mockMvc.perform(get("/ping"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE))
            .andExpect(content().string(containsString("pong")));
    }

    @Test
    public void testVersion() throws Exception {
        mockMvc.perform(get("/version"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE))
            .andExpect(content().string(Version.VERSION));
    }
}
