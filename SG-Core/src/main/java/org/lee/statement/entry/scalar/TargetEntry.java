package org.lee.statement.entry.scalar;

import org.apache.commons.lang3.StringUtils;
import org.lee.statement.node.Alias;
import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public class TargetEntry implements Scalar, Alias {
    private String alias = "";
    private final Scalar target;

    public TargetEntry(Scalar targetScalar){
        this.target = targetScalar;
    }

    @Override
    public boolean hasAlias() {
        return alias != null && !(StringUtils.isBlank(alias) || StringUtils.isEmpty(alias));
    }

    @Override
    public String getAlias() {
        return alias != null ? alias: "";
    }

    @Override
    public void setAlias() {
        if(hasAlias()){
            System.out.println("The alias has already been set");
        }
        // todo auto Alias
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.targetEntry;
    }

    public Field toField(){
        String fieldName = hasAlias() ? alias : target.getString();
        TypeTag fieldType = target.getType();
        return new Field(fieldName, fieldType);
    }

    @Override
    public TypeTag getType() {
        return target.getType();
    }
}
