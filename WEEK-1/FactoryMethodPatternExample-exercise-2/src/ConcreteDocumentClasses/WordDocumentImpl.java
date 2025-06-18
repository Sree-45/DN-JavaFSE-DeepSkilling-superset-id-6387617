package ConcreteDocumentClasses;

import DocumentInterfaces.WordDocument;

public class WordDocumentImpl implements WordDocument {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }
    
    @Override
    public void formatText() {
        System.out.println("Formatting text in Word document...");
    }
    
    @Override
    public String getWordVersion() {
        return "Microsoft Word 2023 (.docx)";
    }
}