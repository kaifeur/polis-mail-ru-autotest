package transformer;

import com.codeborne.selenide.ElementsCollection;
import wrapper.GroupCardWrapper;

import java.util.List;
import java.util.stream.Collectors;

public final class GroupCardTransformer implements Transformer<GroupCardWrapper> {
    private static GroupCardTransformer instance = new GroupCardTransformer();

    private GroupCardTransformer() {
    }

    public static GroupCardTransformer getInstance() {
        return instance;
    }

    @Override
    public List<GroupCardWrapper> transform(final ElementsCollection elements) {
        return elements.snapshot().stream()
                .map(GroupCardWrapper::new)
                .collect(Collectors.toList());
    }
}
