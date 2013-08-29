package net.codetemplate.uda.domain;

public interface UserRepository {
    User findBy(String id) throws NotFoundException;

    class NotFoundException extends RuntimeException {
        private String id;

        public NotFoundException(String id) {
            this.id = id;
        }
    }
}
