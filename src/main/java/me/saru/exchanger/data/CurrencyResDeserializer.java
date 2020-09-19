package me.saru.exchanger.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static java.util.Optional.ofNullable;

public class CurrencyResDeserializer extends JsonDeserializer<CurrencyData.Res> {

    @Override
    public CurrencyData.Res deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectCodec objectCodec = p.getCodec();
        JsonNode jsonNode = objectCodec.readTree(p);

        JsonNode quotes = jsonNode.get("quotes");
        if (quotes == null) {
            return new CurrencyData.Res(false, null, null, null);
        }

        final Long krw = ofNullable(quotes.get("USDKRW")).map(JsonNode::asLong).orElse(null);
        final Long jpy = ofNullable(quotes.get("USDJPY")).map(JsonNode::asLong).orElse(null);
        final Long php = ofNullable(quotes.get("USDPHP")).map(JsonNode::asLong).orElse(null);

        return new CurrencyData.Res(true, krw, jpy, php);
    }
}
