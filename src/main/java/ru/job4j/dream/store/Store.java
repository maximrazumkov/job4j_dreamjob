package ru.job4j.dream.store;

import ru.job4j.dream.model.*;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    Post findById(int id);

    void saveCandidate(Candidate candidate);

    Candidate findCandidateById(int id);

    Collection<City> findAllCities();

    int createPhoto(Photo photo);

    void deletePhotoById(int id);

    void updateCandidate(Candidate candidate);

    Photo findPhototeById(int id);
}
