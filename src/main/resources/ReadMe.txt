
// check fetch of relation for find wrong mapping
openjpa-jdbc
org.apache.openjpa.jdbc.meta.strats.HandlerFieldStrategy.class

    public void map(boolean adapt) {
        if (field.getHandler() == null)
            throw new MetaDataException(_loc.get("no-handler", field));
        assertNotMappedBy();

        // map join key (if any)
line 75 :        field.mapJoin(adapt, false);