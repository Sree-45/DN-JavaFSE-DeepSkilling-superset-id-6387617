package ConcreteDocumentClasses;

import DocumentInterfaces.PdfDocument;

public class PdfDocumentImpl implements PdfDocument {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }
    
    @Override
    public void addWatermark() {
        System.out.println("Adding watermark to PDF document...");
    }
    
    @Override
    public String getPdfVersion() {
        return "Adobe PDF (.pdf)";
    }
}