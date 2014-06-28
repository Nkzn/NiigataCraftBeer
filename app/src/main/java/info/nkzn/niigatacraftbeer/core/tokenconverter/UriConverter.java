package info.nkzn.niigatacraftbeer.core.tokenconverter;

import android.net.Uri;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.JsonPullParser;
import net.vvakame.util.jsonpullparser.util.OnJsonObjectAddListener;
import net.vvakame.util.jsonpullparser.util.TokenConverter;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import static net.vvakame.util.jsonpullparser.util.JsonUtil.*;

/**
 * <code>{"uri": "hoge://hogehoge/fugafuga"}</code>なJSONオブジェクトをパースするための{@link TokenConverter}.
 *
 * @author nakagawa
 */
public class UriConverter extends TokenConverter<Uri> {

    static UriConverter conv = null;

    /**
     * {@link UriConverter}のインスタンスを返します
     *
     * @return {@link UriConverter}
     */
    public static UriConverter getInstance() {
        if (conv == null) {
            conv = new UriConverter();
        }
        return conv;
    }

    @Override
    public Uri parse(JsonPullParser parser, OnJsonObjectAddListener listener) throws IOException, JsonFormatException {

        switch (parser.getEventType()) {
            case VALUE_NULL:
                return null;
            case VALUE_STRING:
                final String uriString = parser.getValueString();

                final Uri uri;
                try {
                    uri = Uri.parse(new URI(uriString).toString()); // URI文法チェックのためだけにjava.net.URIを使用
                } catch (URISyntaxException e) {
                    throw new JsonFormatException(e, parser);
                }

                return uri;
            default:
                throw new JsonFormatException("URIが文字列ではありませんでした", parser);
        }

    }

    @Override
    public void encodeNullToNull(Writer writer, Uri uri) throws IOException {
        if (uri == null) {
            put(writer, "null");
            return;
        }

        put(writer, uri.toString());
    }
}
