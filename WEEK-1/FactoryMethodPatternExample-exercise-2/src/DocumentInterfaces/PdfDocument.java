package DocumentInterfaces;

public interface PdfDocument {
    void open();
    void save();
    void close();
    void addWatermark();
    String getPdfVersion();
}
