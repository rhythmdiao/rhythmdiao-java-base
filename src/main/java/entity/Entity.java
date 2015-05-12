package entity;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int foo;
    private String bar;


    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("foo", foo)
                .add("bar", bar)
                .toString();
    }
}
