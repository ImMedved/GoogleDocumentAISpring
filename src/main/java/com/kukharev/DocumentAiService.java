package com.kukharev;

@Service
public class DocumentAiService {

    private final DocumentUnderstandingServiceClient client;

    @Autowired
    public DocumentAiService() throws IOException {
        this.client = DocumentUnderstandingServiceClient.create();
    }

    public String processDocument(File file) throws IOException {
        InputConfig inputConfig = InputConfig.newBuilder().setMimeType("application/pdf")
                .setGcsSource(GcsSource.newBuilder().setUri(file.toURI().toString()).build()).build();
        Document document = client.processDocument(inputConfig);
        return document.getText();
    }
}

@Service
public class FileConversionService {

    public void convertToDocx(String text, String outputPath) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);

        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
