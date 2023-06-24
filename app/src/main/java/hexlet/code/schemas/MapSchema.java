package hexlet.code.schemas;import java.util.Map;import java.util.HashMap;import java.util.function.Predicate;public final class MapSchema extends BaseSchema {    public MapSchema() {        Predicate<Object> typeCheck =                inputDate -> inputDate instanceof Map<?, ?> || inputDate == null;        addCheck(typeCheck);    }    public MapSchema sizeof(int inputSize) {        Predicate<Object> sizeof =                inputDate -> ((Map<?, ?>) inputDate).size() >= inputSize;        addCheck(sizeof);        return this;    }    public MapSchema shape(Map<String, BaseSchema> inputSchema) {        HashMap<?, BaseSchema> scheme = new HashMap<>(inputSchema);        Predicate<Object> shape =                input -> input == null || ((Map<?, ?>) input).keySet()                        .stream().allMatch(key -> {                            Object valueInputMap = ((Map<?, ?>) input).get(key);                            return scheme.get(key).isValid(valueInputMap);                        });        addCheck(shape);        return this;    }}