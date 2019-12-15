package transformer;

import com.codeborne.selenide.ElementsCollection;
import wrapper.DiscussionWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class DiscussionTransformer implements Transformer<DiscussionWrapper> {
    private static DiscussionTransformer instance = new DiscussionTransformer();

    private DiscussionTransformer() {
    }

    public static DiscussionTransformer getInstance() {
        return instance;
    }

    @Override
    public List<DiscussionWrapper> transform(final ElementsCollection elements) {
        return elements.snapshot().stream()
                .map(DiscussionWrapper::new)
                .collect(Collectors.toList());
    }
}
