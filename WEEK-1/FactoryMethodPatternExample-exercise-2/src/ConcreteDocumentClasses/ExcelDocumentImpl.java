package ConcreteDocumentClasses;

import DocumentInterfaces.ExcelDocument;

public class ExcelDocumentImpl implements ExcelDocument {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }
    
    @Override
    public void addFormula() {
        System.out.println("Adding formula to Excel document...");
    }
    
    @Override
    public String getExcelVersion() {
        return "Microsoft Excel 2023 (.xlsx)";
    }
}
