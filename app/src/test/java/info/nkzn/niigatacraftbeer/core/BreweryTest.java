package info.nkzn.niigatacraftbeer.core;

import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class BreweryTest {

    @Test
    public void Parcelableの振る舞いが正しい() {
        // Given
        final ArrayList<Brewery> breweries = new ArrayList<Brewery>(Arrays.asList(
                new Brewery("ケイズブルーイング・ブルーマスター","ブルーマスター","かぼす&ハニー","あまおうノーブルスイート","あまおうオートミールブラック"),
                new Brewery("志賀高原ビール","DPA","IPA","苦いラガー","House　IPA","\"EVEN PRIME\"White IPA","ポーター","山伏 壱 saison one"),
                new Brewery("ワイマーケットブルーイング","Hop Seduction Session IPA","Craft Heart Red","のんとまこと","マンゴーオレンジエール","Noirheads"),
                new Brewery("新潟麦酒","ヨーロピアンケルシュ","Niigata Beer","スパークリングマンゴー","ヴァイツェン","エール・ド・ルレクチェ","レッドアイ","エスプレッソ","ゴールデンエディンバラ")
        ));

        // When
        final Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("test", breweries);
        final ArrayList<Brewery> actual = bundle.getParcelableArrayList("test");

        // Then
        assertThat(actual, is(breweries));
    }

}