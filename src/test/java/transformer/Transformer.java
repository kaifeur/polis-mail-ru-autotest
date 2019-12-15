package transformer;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

public interface Transformer<T> {
    List<T> transform(final ElementsCollection elements);
}
