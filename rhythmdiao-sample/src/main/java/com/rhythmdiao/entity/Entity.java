package com.rhythmdiao.entity;

import java.io.Serializable;

public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int foo;
    private final String bar;

    public int getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }

    private Entity(EntityBuilder builder) {
        this.foo = builder.foo;
        this.bar = builder.bar;
    }

    public static class EntityBuilder implements Builder {
        private int foo;
        private String bar;

        public EntityBuilder withFoo(int foo) {
            this.foo = foo;
            return this;
        }

        public EntityBuilder withBar(String bar) {
            this.bar = bar;
            return this;
        }

        public Entity build() {
            return new Entity(this);
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "foo=" + foo +
                ", bar='" + bar + '\'' +
                '}';
    }
}
