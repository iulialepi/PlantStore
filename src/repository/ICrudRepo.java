package repository;


public interface ICrudRepo<ID, E> {

    void add(E entity);
    E delete(ID id);
    E update(ID id, E newEntity);

    E findById(ID id);
    Iterable<E> findAll();

}
