package ConceteDocumentFactory;

import AbstractDocumentFactory.DocumentFactory;
import ConcreteDocumentClasses.PdfDocumentImpl;
import DocumentInterfaces.PdfDocument;

public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public PdfDocument createDocument() {
        return new PdfDocumentImpl();
    }
}