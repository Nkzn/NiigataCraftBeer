package info.nkzn.niigatacraftbeer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import info.nkzn.niigatacraftbeer.core.Brewery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class BreweryItemViewTest {

    @Test
    public void bindすると適切な値が表示される() throws Exception {
        // Given
        final Brewery brewery = new Brewery("ジョジョの奇妙なブルワリー", "オラオラビール", "レロレロビール", "ノトーリアスI・P・A", "前略 君が泣くまで殴るのをやめないぜSORRY");

        // When
        BreweryItemView actual = BreweryItemView_.build(Robolectric.application);
        actual.bind(brewery);

        final String expected1 = "ジョジョの奇妙なブルワリー";
        final String expected2 = "オラオラビール, レロレロビール, ノトーリアスI・P・A, 前略 君が泣くまで殴るのをやめないぜSORRY";

        // Then
        assertThat(actual.text1.getText().toString(), is(expected1));
        assertThat(actual.text2.getText().toString(), is(expected2));
    }
}