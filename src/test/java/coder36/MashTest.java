package coder36;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark on 03/03/2015.
 */
public class MashTest {

    Mash mash;

    @Before
    public void before() throws Exception {
         mash = new Mash( new ObjectMapper().readValue(ClassLoader.getSystemResourceAsStream("test.json"), Map.class) );
    }

    @Test
    public void simple_node() {
        assertEquals("mark", mash.get("name"));
    }

    @Test
    public void nested_node() {
        assertEquals("today", mash.get("statement.generated"));
    }

    @Test
    public void missing_node_returns_empty_string() {
        assertEquals("", mash.get("statement.generated.list"));
    }

    @Test
    public void integer_node() {
        assertEquals( mash.get("number"), 36 );
    }

    @Test
    public void double_node() {
        assertEquals( (Double) mash.get("decimal"), 36.1, 0.001 );
    }

    @Test
    public void triple_nested_nodes() {
        assertEquals("2015-01-01", mash.get("statement.period.from"));
    }

    @Test
    public void returns_a_mash() {
        assertTrue(mash.get("statement") instanceof Mash);
    }

    @Test
    public void testReadmeInstructions() {
        Mash json = new Mash( (Map) new RestTemplate().getForObject( "https://pure-savannah-9942.herokuapp.com/bill.json", Map.class ));
        double total = (Double) json.get( "total" );
        String period_from = json.get_s( "statement.period.from" );
        String period_to = json.get_s( "statement.period.to" );

        //or

        Mash period = (Mash) json.get( "statement.period" );
        period_to = (String) period.get("to");
    }


}
