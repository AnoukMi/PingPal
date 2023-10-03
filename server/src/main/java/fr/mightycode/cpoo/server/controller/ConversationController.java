@RestController
@RequestMapping("user")
@AllArgsConstructor
@CrossOrigin
public class ConversationController {
    private final ConversationService conversationService;
}