package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StubStore implements Store {

    private final Map<Integer, Candidate> store = new HashMap<>();
    private int ids = 0;

    public StubStore() {
    }

    @Override
    public Collection<Post> findAllPosts() {
        return null;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return new ArrayList<Candidate>(this.store.values());
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public Post findById(int id) {
        return null;
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        candidate.setId(this.ids++);
        this.store.put(candidate.getId(), candidate);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return null;
    }

    @Override
    public Collection<City> findAllCities() {
        return null;
    }
}
