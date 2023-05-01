package TheBigDev.modelo.dao;

import java.util.List;

public interface DaoInterface<T, R> {

    public void insert(T o);

    public List<T> list();

    public void update(T o);

    public void delete(T o);

    public T read(R o);
}
