import com.google.cloud.language.v1.AnalyzeSentimentRequest;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Sentiment;

public class SentimentAnalysisExample {
    public static void main(String[] args) {
        String text = "I love programming with Google Cloud Natural Language API!";
        
        try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();

            AnalyzeSentimentRequest request = AnalyzeSentimentRequest.newBuilder()
                    .setDocument(doc)
                    .build();

            Sentiment sentiment = languageServiceClient.analyzeSentiment(request).getDocumentSentiment();
            System.out.printf("Text: %s\n", text);
            System.out.printf("Sentiment score: %.2f\n", sentiment.getScore());
            System.out.printf("Sentiment magnitude: %.2f\n", sentiment.getMagnitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}