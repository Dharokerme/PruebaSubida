package in.reqres.util;

public enum ContentBody {
    JSON_BODY("{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}"),

    JSON_BODY_WITHOUT_PASSWORD("{\n" +
                                   "    \"email\": \"eve.holt@reqres.in\",\n" +
                                   "}");

    private final String value;

    ContentBody(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


