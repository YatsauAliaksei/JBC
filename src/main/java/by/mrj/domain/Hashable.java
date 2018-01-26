package by.mrj.domain;

public interface Hashable {
    /**
     * Returns JSON object representation of it's immutable part.
     */
    String hash();
}
