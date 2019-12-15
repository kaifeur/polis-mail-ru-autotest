package transformer;

import com.codeborne.selenide.ElementsCollection;
import wrapper.MusicWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class MusicTransformer implements Transformer<MusicWrapper> {
    private static MusicTransformer instance = new MusicTransformer();

    private MusicTransformer() {
    }

    public static MusicTransformer getInstance() {
        return instance;
    }

    @Override
    public List<MusicWrapper> transform(final ElementsCollection elements) {
        return elements.snapshot().stream()
                .map(MusicWrapper::new)
                .collect(Collectors.toList());
    }
}
