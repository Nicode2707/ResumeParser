import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.File;
import java.util.regex.*;

import static org.apache.pdfbox.Loader.*;

public class ResumeParser {
    public static void main(String[] args) {
        try {
            // 👉 Step 1: Load resume.pdf file
            File file = new File("src/resume.pdf");
            // Place your resume in project root
            String resumeText;
            try (PDDocument document = loadPDF(file)) {

                // 👉 Step 2: Extract text
                PDFTextStripper stripper = new PDFTextStripper();
                resumeText = stripper.getText(document);
            }

            System.out.println("📄 Resume Text:\n" + resumeText);
            System.out.println("\n🔍 Extracted Info:");

            // 👉 Step 3: Parse Email
            Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}");
            Matcher emailMatcher = emailPattern.matcher(resumeText);
            if (emailMatcher.find()) {
                System.out.println("Email: " + emailMatcher.group());
            }

            // 👉 Step 4: Parse Phone
            Pattern phonePattern = Pattern.compile("\\+?\\d[\\d\\s().-]{8,}");
            Matcher phoneMatcher = phonePattern.matcher(resumeText);
            if (phoneMatcher.find()) {
                System.out.println("Phone: " + phoneMatcher.group());
            }

            // 👉 Step 5: LinkedIn
            Pattern linkedinPattern = Pattern.compile("https?://(www\\.)?linkedin\\.com/in/[a-zA-Z0-9-_/]+");
            Matcher linkedinMatcher = linkedinPattern.matcher(resumeText);
            if (linkedinMatcher.find()) {
                System.out.println("LinkedIn: " + linkedinMatcher.group());
            }

            // 👉 Step 6: GitHub
            Pattern githubPattern = Pattern.compile("https?://(www\\.)?github\\.com/[a-zA-Z0-9-_/]+");
            Matcher githubMatcher = githubPattern.matcher(resumeText);
            if (githubMatcher.find()) {
                System.out.println("GitHub: " + githubMatcher.group());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
