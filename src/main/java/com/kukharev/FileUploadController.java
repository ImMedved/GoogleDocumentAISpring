package com.kukharev;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final FileProcessingService fileProcessingService;

    @Autowired
    public FileUploadController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileProcessingService.processFile(file);
            return ResponseEntity.ok("File processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file.");
        }
    }

    @PostMapping("/uploadFolder")
    public ResponseEntity<String> uploadFolder(@RequestParam("path") String folderPath) {
        try {
            fileProcessingService.processFolder(folderPath);
            return ResponseEntity.ok("Folder processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing folder.");
        }
    }
}
