package win.those.aegiru.chirper.user.event.repository.api;

import java.util.UUID;

public interface UserEventRepository {
    void create(UUID id);

    void delete(UUID id);
}
