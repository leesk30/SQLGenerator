package org.lee.entry.complex;

import org.lee.entry.Normalized;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.List;

public abstract class AdaptiveRecordScalar extends Record implements Scalar {

    public static AdaptiveRecordScalar adaptProjectable(Projectable projectable){
        return new AdaptiveProjectableRecordScalar(projectable);
    }

    public static AdaptiveRecordScalar adaptScalarList(List<Scalar> scalars){
        return new AdaptiveListRecordScalar(scalars);
    }

    public static AdaptiveRecordScalar adaptScalarList(Scalar ... scalars){
        return new AdaptiveListRecordScalar(Arrays.asList(scalars));
    }


    private static class AdaptiveProjectableRecordScalar extends AdaptiveRecordScalar implements Normalized<Projectable> {
        private final Projectable projectable;
        public AdaptiveProjectableRecordScalar(Projectable projectable) {
            super(projectable.project().size());
            addAll(projectable.project());
            this.projectable = projectable;
        }

        @Override
        public String getString() {
            // No parentheses here because the projectable always wrapped by parentheses in subquery.
            return projectable.getString();
        }

        @Override
        public Projectable getWrapped() {
            return projectable;
        }

    }

    private static class AdaptiveListRecordScalar extends AdaptiveRecordScalar {
        protected AdaptiveListRecordScalar(List<Scalar> recordFieldList) {
            super(recordFieldList);
        }

        protected AdaptiveListRecordScalar(Record record){
            super(record);
        }

        @Override
        public String getString() {
            // Because the adaptive record always use in predicate wrapped by signatures/symbols.
            // `toScalar` make sure that:
            //      when only one element in adaptive record we don't need
            return toScalar().getString();
        }
    }

    protected AdaptiveRecordScalar(List<Scalar> recordFieldList){
        super(recordFieldList);
    }

    protected AdaptiveRecordScalar(int width){
        super(width);
    }


    @Override
    public TypeTag getType() {
        return TypeTag.null_;
    }

    @Override
    public AdaptiveRecordScalar toAdaptiveScalar() {
        return this;
    }

}
