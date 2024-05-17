package com.kukharev;

@Service
public class FileProcessingService {

    private final DocumentAiService documentAiService;
    private final FileConversionService fileConversionService;

    @Autowired
    public FileProcessingService(DocumentAiService documentAiService, FileConversionService fileConversionService) {
        this.documentAiService = documentAiService;
        this.fileConversionService = fileConversionService;
    }

    public void processFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);
        String docText = documentAiService.processDocument(tempFile);
        fileConversionService.convertToDocx(docText, tempFile.getName() + ".docx");
    }

    public void processFolder(String folderPath) throws IOException {
        Files.walk(Paths.get(folderPath))
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        processFile(new MockMultipartFile(path.getFileName().toString(), Files.newInputStream(path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
