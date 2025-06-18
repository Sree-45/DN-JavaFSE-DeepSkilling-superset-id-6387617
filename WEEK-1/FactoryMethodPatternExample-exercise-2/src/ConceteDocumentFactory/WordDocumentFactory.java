package ConceteDocumentFactory;

import AbstractDocumentFactory.DocumentFactory;
import DocumentInterfaces.WordDocument;
import ConcreteDocumentClasses.WordDocumentImpl;

public class WordDocumentFactory extends DocumentFactory {
    @Override
    public WordDocument createDocument() {
        return new WordDocumentImpl();
    }
}
