package info.nkzn.niigatacraftbeer.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
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

    @Test
    public void canReadJson() throws Exception {
        // Given

        // When
        final String actual = provider.readJsonAsset(Robolectric.application);
        final String expected = "[{\"shop_name\":\"ケイズブルーイング・ブルーマスター\",\"beers\":[\"ブルーマスター\",\"かぼす&ハニー\",\"あまおうノーブルスイート\"\"あまおうオートミールブラック\"]},{\"shop_name\":\"富士桜高原麦酒\",\"beers\":[\"ヴァイツェン\"\"シュバルツヴァイツェン\"\"カッパーヴァイツェン\"\"ラオホ\"\"プレミアムピルスナー\"\"ピルス\"]},{\"shop_name\":\"キムラ（ブラックアイル）\",\"beers\":[\"ブラックアイル　レッドカイトエール\"\"ブラックアイル　ペールエール\"\"ブラックアイル　ポーター\"]},{\"shop_name\":\"ブリマーブルーイング\",\"beers\":[\"ペールエール\"\"ゴールデンエール\"\"セッション・インディア・ブラックエール\"]},{\"shop_name\":\"ナガノトレーディング\",\"beers\":[\"Ballast Point Sculpin IPA\"\"Coronado Islander IPA\"\"Coronado Orange Avenue Wit\"]},{\"shop_name\":\"胎内高原ビール\",\"beers\":[\"ピルスナー\"\"ヴァイツェン\"\"ラオホ\"\"限定ピルスナー\"]},{\"shop_name\":\"志賀高原ビール\",\"beers\":[\"DPA\"\"IPA\"\"苦いラガー\"\"House　IPA\"\"\\\"EVEN PRIME\\\"White IPA\"\"ポーター\"\"山伏 壱 saison one\"]},{\"shop_name\":\"ノースアイランドビール\",\"beers\":[\"限定ビール\"\"ピルスナー\"\"ヴァイツェン\"\"ブラウンエール\"\"IPA\"\"スタウト\"\"コリアンダーブラック\"]},{\"shop_name\":\"コエドブルワリー\",\"beers\":[\"瑠璃\"\"伽羅\"\"白\"\"漆黒\"\"紅赤\"]},{\"shop_name\":\"ロコビア\",\"beers\":[\"香りの生\"\"佐倉スチーム\"\"ジェネラル･ウインター\"\"Saison de Seigle ライ麦セゾン\"]},{\"shop_name\":\"御殿場高原ビール\",\"beers\":[\"ヴァイツェン\"\"シュバルツ\"\"ピルス\"\"ヴァイツェンボック\"\"御殿場コシヒカリラガー\"\"サマーエール\"]},{\"shop_name\":\"あくらビール\",\"beers\":[\"秋田美人のビール\"\"さくら酵母ウィート\"\"キィウィIPA\"\"ホッピーヴァイツェン\"\"宮城古代米にがーいアンバー\"]},{\"shop_name\":\"箕面ビール\",\"beers\":[\"ヴァイツェン\"\"W-IPA\"\"おさるIPA\"\"ペールエール\"\"スタウト\"]},{\"shop_name\":\"日本クラフトビール㈱\",\"beers\":[\"Far Yeast Tokyo Blonde\"\"Far Yeast Tokyo White\"]},{\"shop_name\":\"八海山泉ビール\",\"beers\":[\"ヴァイツェン\"\"アルト\"]},{\"shop_name\":\"えぞ麦酒\",\"beers\":[\"チョコレートスタウト\"\"Lost Coast Wetermelon Wheat\"\"Lost Coast Indica IPA\"\"Mad River Steelhead Extra Pale\"]},{\"shop_name\":\"那須高原ビール\",\"beers\":[\"那須高原ビール愛\"\"ヴァイツェン\"\"イングリッシュエール\"\"スコティッシュエール\"]},{\"shop_name\":\"KOBATSUトレーディング\",\"beers\":[\"プランク　ヘフェヴァイツエン\"\"プランク　ピルザール\"\"プランク　エクスポートドゥンケル\"\"プランク ヘラーヴァイツェンボック\"]},{\"shop_name\":\"いわて蔵ビール\",\"beers\":[\"ヴァイツェン\"\"ゴールデンエール\"\"オイスタースタウト\"\"レッドエール\"\"IPA\"]},{\"shop_name\":\"サンクトガーレン\",\"beers\":[\"湘南ゴールド\"\"パイナップルエール\"\"YOKOHAMA XPA\"\"アンバーエール\"]},{\"shop_name\":\"エチゴビール\",\"beers\":[\"コシヒカリ越後ビール\"\"ホワイトエール\"\"レッドエール\"\"越後の龍\"]},{\"shop_name\":\"ヤッホーブルーイング\",\"beers\":[\"よなよなエール\"\"インドの青鬼\"\"軽井沢高原ビール ブリテッシュペールエール\"\"軽井沢高原ビール 夏季限定\"\"水曜日のネコ\"]},{\"shop_name\":\"ワイマーケットブルーイング\",\"beers\":[\"Hop Seduction Session IPA\"\"Craft Heart Red\"\"のんとまこと\"\"マンゴーオレンジエール\"\"Noirheads\"]},{\"shop_name\":\"アウトサイダーブルーイング\",\"beers\":[\"サンライズラガー\"\"ガンスリンガーIPA\"\"フランダースベルジャンホワイト\"]},{\"shop_name\":\"ハーブストムーン\",\"beers\":[\"ピルスナー\"\"シュバルツ\"\"ナツコ（夏の小麦ビール）\"\"グレープフルーツエール\"]},{\"shop_name\":\"新潟麦酒\",\"beers\":[\"ヨーロピアンケルシュ\"\"Niigata Beer\"\"スパークリングマンゴー\"\"ヴァイツェン\"\"エール・ド・ルレクチェ\"\"レッドアイ\"\"エスプレッソ\"\"ゴールデンエディンバラ\"]},{\"shop_name\":\"城端麦酒\",\"beers\":[\"麦やエール\"\"はかまエール\"\"トロピカルピンク\"\"Kaede「楓」\"]},{\"shop_name\":\"スワンレイクビール\",\"beers\":[\"アンバーエール\"\"ポーター\"\"Black-IPA\"\"越乃米こしひかり\"\"Imperial IPA\"]},{\"shop_name\":\"伊勢角屋麦酒\",\"beers\":[\"ペールエール\"\"ブラウンエール\"\"スタウト\"\"インペリアルレッドエール\"\"ゆず濾過エール\"]},{\"shop_name\":\"日本海夕陽ブルワリー\",\"beers\":[\"サンセットピルス\"\"コシヒカリラガー\"\"焙煎コシヒカリラガー\"]},{\"shop_name\":\"AQベボリューション \",\"beers\":[\"Avery White Rascal\"\"Left Hand Good Juju\"\"IPA（いろいろ）\"\"Victory Golden Monkey\"\"Heretic Shallow Grave Porter\"\"Left Hand Milk Stout\"]},{\"shop_name\":\"木曽路ビール\",\"beers\":[\"ペールエール\"\"ポーター\"\"ゴールデンエール\"\"ナイアガラペールエール\"]}]";

        // Then
        assertThat(actual, is(expected));
    }
}