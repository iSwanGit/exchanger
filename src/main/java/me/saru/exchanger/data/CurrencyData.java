package me.saru.exchanger.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

public class CurrencyData {

    public static class Req {

    }

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonDeserialize(using = CurrencyResDeserializer.class)
    public static class Res {
        public Res(boolean success, Long krw, Long jpy, Long php) {
            this.success = success;
            this.krw = krw;
            this.jpy = jpy;
            this.php = php;
        }

        private boolean success;
        private Long krw;
        private Long jpy;
        private Long php;
    }
}
