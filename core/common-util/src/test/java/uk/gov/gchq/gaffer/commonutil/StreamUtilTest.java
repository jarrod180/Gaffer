package uk.gov.gchq.gaffer.commonutil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import java.io.InputStream;
import java.net.URI;

/**
 * Created on 25/04/2017.
 */
public class StreamUtilTest {

    public static final String FILE_NAME = "URLSchema.json";

    @Test
    public void testOpenStreamsURLNotEmpty() throws Exception {
        //Given
        final URI resource = getClass().getClassLoader().getResource(FILE_NAME).toURI();
        if (resource == null) fail("Test json file not found:" + FILE_NAME);

        //When
        final InputStream[] inputStreams = StreamUtil.openStreams(resource);

        //Then
        try {
            assertNotNull(inputStreams);
            assertFalse("InputStreams length is 0", inputStreams.length == 0);
        } finally {
            StreamUtil.closeStreams(inputStreams);
        }
    }
}