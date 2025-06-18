package ConceteDocumentFactory;

import AbstractDocumentFactory.DocumentFactory;
import ConcreteDocumentClasses.ExcelDocumentImpl;
import DocumentInterfaces.ExcelDocument;

public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public ExcelDocument createDocument() {
        return new ExcelDocumentImpl();
    }
}
