import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class QuestionDataLoader {
    private final QuestionService questionService;

    public QuestionDataLoader(QuestionService questionService) {
        this.questionService = questionService;
    }

    public CompletableFuture<List<Question>> loadQuestions() {
        return CompletableFuture.supplyAsync(() -> questionService.getAllQuestions())
                .thenApply(questions -> questions.stream() 
                        .filter(this::isValidQuestion) 
                        .collect(Collectors.toList()));
    }

    private boolean isValidQuestion(Question question) {
        return question != null && question.getId() != null && question.getText() != null;
    }
}