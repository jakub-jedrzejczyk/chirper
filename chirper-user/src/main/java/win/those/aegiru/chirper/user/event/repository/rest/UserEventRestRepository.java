package win.those.aegiru.chirper.user.event.repository.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import win.those.aegiru.chirper.user.event.repository.api.UserEventRepository;

import java.util.UUID;

@Repository
public class UserEventRestRepository implements UserEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public UserEventRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void create(UUID id) {
        restTemplate.put("/api/users/{id}", null, id);
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete("/api/users/{id}", id);
    }
}
