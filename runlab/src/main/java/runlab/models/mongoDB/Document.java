package runlab.models.mongoDB;

import java.time.LocalDateTime;

public abstract class Document {
    public LocalDateTime createdDateTime = LocalDateTime.now();
}
