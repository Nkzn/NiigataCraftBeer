package info.nkzn.niigatacraftbeer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class BeerItemViewTest {

    BeerItemView view;

    @Before
    public void setUp() {
        view = BeerItemView_.build(Robolectric.application);
    }

    @Test
    public void formatDateがちゃんと動く() throws Exception {
        // Given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.JUNE, 28, 13, 33);

        // When
        String actual = view.formatDate(calendar.getTime());
        String expected = "2014/06/28 13:33 に飲みました";

        // Then
        assertThat(actual, is(expected));
    }
}