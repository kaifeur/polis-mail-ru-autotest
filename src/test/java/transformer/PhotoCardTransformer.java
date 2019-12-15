package transformer;

import com.codeborne.selenide.ElementsCollection;
import wrapper.PhotoCardWrapper;

import java.util.List;
import java.util.stream.Collectors;

public final class PhotoCardTransformer implements Transformer<PhotoCardWrapper> {
    private static PhotoCardTransformer instance = new PhotoCardTransformer();

    private PhotoCardTransformer() {
    }

    public static PhotoCardTransformer getInstance() {
        return instance;
    }

    @Override
    public List<PhotoCardWrapper> transform(final ElementsCollection elements) {
        return elements.snapshot().stream()
                .map(PhotoCardWrapper::new)
                .collect(Collectors.toList());
    }
}
