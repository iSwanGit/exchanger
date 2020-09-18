package me.saru.exchanger.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CurrencyResDeserializer extends JsonDeserializer<CurrencyData.Res> {

    @Override
    public CurrencyData.Res deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectCodec objectCodec = p.getCodec();
        JsonNode jsonNode = objectCodec.readTree(p);

        JsonNode quotes = jsonNode.get("quotes");
        if (quotes == null) {
            return new CurrencyData.Res(false, null, null, null);
        }

        final JsonNode krw = quotes.get("USDKRW");
        final JsonNode jpy = quotes.get("USDJPY");
        final JsonNode php = quotes.get("USDPHP");

        return new CurrencyData.Res(true,
                krw == null ? null : krw.asLong(),
                jpy == null ? null : jpy.asLong(),
                php == null ? null : php.asLong()
        );
    }
}
