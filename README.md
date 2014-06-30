新潟クラフトビールの陣非公式アプリ
========================

どのブルワリーのビールを飲んだかチェックしたり、Instagram経由で写真をシェアしたりするやつです。

データ構造
----------

元になるデータは `R.raw.niigata_craft_beer_2014_json` から読んでくるようにしてあります。

下記のような形でJSONを作って置いておけば、いい感じに動きます。

```
[
  {
    "brewery":"ケイズブルーイング・ブルーマスター",
    "beers":[
      {"name":"ブルーマスター"},
      {"name":"かぼす&ハニー"},
      {"name":"あまおうノーブルスイート"},
      {"name":"あまおうオートミールブラック"}
    ]
  },

...

  {
    "brewery":"富士桜高原麦酒",
    "beers":[
      {"name":"ヴァイツェン"},
      {"name":"シュバルツヴァイツェン"},
      {"name":"カッパーヴァイツェン"},
      {"name":"ラオホ"},
      {"name":"プレミアムピルスナー"},
      {"name":"ピルス"}
    ]
  }
]
```

License
----------

```
Copyright 2014 Yukiya Nakagawa.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```