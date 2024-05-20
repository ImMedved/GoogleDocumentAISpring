package com.kukharev.service;

import com.google.cloud.documentai.v1beta3.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentAiService {

    private final DocumentProcessorServiceClient client;

    @Autowired
    public DocumentAiService(DocumentProcessorServiceClient client) {
        this.client = client;
    }

    public String processDocument(File file) throws IOException {
        ByteString content = ByteString.readFrom(new FileInputStream(file));
        RawDocument rawDocument = RawDocument.newBuilder().setContent(content).setMimeType("application/pdf").build();
        ProcessRequest request = ProcessRequest.newBuilder()
                .setRawDocument(rawDocument)
                .setName("projects/your-project-id/locations/us/processors/your-processor-id")
                .build();

        ProcessResponse response = client.processDocument(request);
        Document document = response.getDocument();

        return extractText(document);
    }

    private String extractText(Document document) {
        StringBuilder extractedText = new StringBuilder();
        List<Document.Page> pages = document.getPagesList();

        for (Document.Page page : pages) {
            for (Document.Page.Paragraph paragraph : page.getParagraphsList()) {
                extractedText.append(paragraph.getLayout().getTextAnchor().getContent());
            }
        }

        return extractedText.toString();
    }
}