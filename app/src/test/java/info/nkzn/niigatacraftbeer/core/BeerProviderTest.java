package info.nkzn.niigatacraftbeer.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class BeerProviderTest {

    BeerProvider provider;

    @Before
    public void setUp() {
        provider = BeerProvider.getInstance();
    }

    @Test
    public void instanceIsNotNull() {
        assertThat(provider, is(notNullValue()));
    }
}