package helper.domain;

import java.io.Serializable;

public interface LTXParser<T> extends Serializable{
    public T parse(String string);
}
