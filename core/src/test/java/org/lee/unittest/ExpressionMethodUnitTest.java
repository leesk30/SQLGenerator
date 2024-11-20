package org.lee.unittest;

import org.lee.common.utils.NodeUtils;
import org.lee.generator.expression.CommonExpressionGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.relation.Relation;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.expression.Expression;
import org.lee.sql.literal.LiteralLong;
import org.lee.sql.symbol.Aggregation;
import org.lee.sql.symbol.Operator;
import org.lee.sql.type.TypeTag;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class ExpressionMethodUnitTest {
    private final RangeTableEntry relation;
    private final RangeTableReference reference;
    private final SQLGeneratorContext context;
    public ExpressionMethodUnitTest(){
        Field f1 = new Field("a", TypeTag.bigint);
        Field f2 = new Field("b", TypeTag.int_);
        Field f3 = new Field("c", TypeTag.string);
        this.relation = new Relation("db1", "t1", Arrays.asList(f1, f2, f3));
        this.reference = new RangeTableReference(relation);
        this.context = SQLGeneratorContext.create("src/test/resources/tpcds.json");
    }

    @Test
    public void testExpressionIsScalarStyle(){
        Aggregation aggregation = new Aggregation("max(%s)", TypeTag.bigint, TypeTag.bigint);
        Expression expression = new Expression(
                aggregation,
                Collections.singletonList(this.reference.getFieldReferences().get(0).toCompleteExpression())
        );
        assert expression.getString().trim().equals("max(db1.t1.a)");
        assert new TargetEntry(expression).isScalarStyle();
        Operator operator = new Operator("%s + %s", 1, TypeTag.bigint, TypeTag.bigint, TypeTag.bigint);
        LiteralLong literal = new LiteralLong(1);

        Expression expr1 = new Expression(
                operator,
                Arrays.asList(expression, literal.toCompleteExpression())
        );
        assert expr1.getString().trim().equals("max(db1.t1.a) + 1");
        assert new TargetEntry(expr1).isScalarStyle();

        Expression expr2 = new Expression(
                operator,
                Arrays.asList(expression, this.reference.getFieldReferences().get(0).toCompleteExpression())
        );
        assert expr2.getString().trim().equals("max(db1.t1.a) + db1.t1.a");
        assert !new TargetEntry(expr2).isScalarStyle();
    }

    @Test
    public void testExpressionCasting(){
        CommonExpressionGenerator generator = new CommonExpressionGenerator(ExpressionLocation.project, false, false, null, Collections.emptyList());
        IntStream.range(0, 50).parallel().forEach(
                i -> {
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.bigint, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.char_, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.date, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.string, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.date, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.date, TypeTag.boolean_, 3));
                    System.out.println("=====================================================================");
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.bigint, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.char_, TypeTag.int_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.date, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.char_, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.string, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.date, 3));
                    NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.date, TypeTag.boolean_, 3));}
        );
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.bigint, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.char_, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.date, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.string, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.date, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.date, TypeTag.boolean_, 3));
        System.out.println("=====================================================================");
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.bigint, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.char_, TypeTag.int_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.int_, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.date, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.char_, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.timestamp, TypeTag.string, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.boolean_, TypeTag.date, 3));
        NodeUtils.printList(context.getSymbolTable().findCasterSignatures(TypeTag.date, TypeTag.boolean_, 3));
    }
}
