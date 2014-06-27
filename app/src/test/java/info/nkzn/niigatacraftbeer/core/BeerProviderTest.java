package info.nkzn.niigatacraftbeer.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class BeerProviderTest {

    @Test
    public void 各ブルワリーの情報が取得できる() throws Exception {
        // Given

        // When
        final List<Brewery> actual = BeerProvider.getBreweries(Robolectric.application);
        final Brewery[] expected = { // テストデータは無作為抽出
                new Brewery("ケイズブルーイング・ブルーマスター","ブルーマスター","かぼす&ハニー","あまおうノーブルスイート","あまおうオートミールブラック"),
                new Brewery("志賀高原ビール","DPA","IPA","苦いラガー","House　IPA","\"EVEN PRIME\"White IPA","ポーター","山伏 壱 saison one"),
                new Brewery("ワイマーケットブルーイング","Hop Seduction Session IPA","Craft Heart Red","のんとまこと","マンゴーオレンジエール","Noirheads"),
                new Brewery("新潟麦酒","ヨーロピアンケルシュ","Niigata Beer","スパークリングマンゴー","ヴァイツェン","エール・ド・ルレクチェ","レッドアイ","エスプレッソ","ゴールデンエディンバラ")
        };

        // Then
        assertThat(actual, hasItems(expected));
    }
}