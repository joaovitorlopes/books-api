package joaovitorlopes.com.github.books_api.service;

public interface IDataConversion {
    <T> T getData(String json, Class<T> tClass);
}
