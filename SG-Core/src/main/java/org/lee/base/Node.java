package org.lee.base;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Node {
    String EMPTY = "";
    String ENDING = ";";
    String LP = "(";
    String RP = ")";
    String AS = "AS";
    String SPACE = " ";
    String COMMA = ", ";
    String SELECT = "SELECT";
    String INSERT = "INSERT";
    String VALUES = "VALUES";
    String DELETE = "DELETE";
    String UPDATE = "UPDATE";
    String MERGE = "MERGE";
    String INTO = "INTO";
    String FROM = "FROM";
    String WITH = "WITH";
    String WHERE = "WHERE";
    String ORDER = "ORDER";
    String GROUP = "GROUP";
    String BY = "BY";
    String PIVOT = "PIVOT";
    String UNPIVOT = "UNPIVOT";
    String LIMIT = "LIMIT";
    String OFFSET = "OFFSET";
    String MATERIALIZED = "MATERIALIZED";
    String DOT = ".";
    String DESC = "DESC";
    String ASC = "ASC";
    String NULLS = "NULLS";
    String NULL = "NULL";
    String FIRST = "FIRST";
    String LAST = "LAST";
    String NEWLINE = "\n";
    String ALL = "ALL";
    String SOME = "SOME";
    String ANY = "ANY";
    String EXISTS = "EXISTS";
    String CREATE = "CREATE";
    String TABLE = "TABLE";
    String VIEW = "VIEW";
    String IF = "IF";
    String CASE = "CASE";
    String WHEN = "WHEN";
    String THEN = "THEN";
    String NOT = "NOT";
    String FOR = "FOR";
    String IN = "IN";
    String JOIN = "JOIN";
    String ON = "ON";

    String getString();
    NodeTag getNodeTag();


    default String concatWithSpace(String ... word){
        return String.join(SPACE, word);
    }

    default String concatWithComma(String ... word){
        return String.join(COMMA, word);
    }

    default String concatWithNewline(String ... word){
        return String.join(NEWLINE, word);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Stream<T> nodeStream, final Function<T, String> toStringMethod) {
        final String reducerSeparator = "%s" + separator + "%s";
        return nodeStream
                .map(toStringMethod)
                .filter(s -> !StringUtils.isEmpty(s) && !StringUtils.isBlank(s))
                .reduce((s1, s2) -> String.format(reducerSeparator, s1, s2))
                .orElse("null");
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Stream<T> nodeStream){
        return nodeArrayToString(separator, nodeStream, Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final Stream<T> nodeStream){
        return nodeArrayToString(", ", nodeStream, Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final List<T> nodeList){
        return nodeArrayToString(separator, nodeList.stream(), Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final List<T> nodeList) {
        return nodeArrayToString(", ", nodeList.stream(), Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final T[] nodeArr){
        return nodeArrayToString(separator, Arrays.stream(nodeArr), Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final T[] nodeArr){
        return nodeArrayToString(", ", Arrays.stream(nodeArr), Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Iterator<T> nodeIterator){
        return nodeArrayToString(separator, StreamSupport.stream(Spliterators.spliteratorUnknownSize(nodeIterator, 0), false), Node::getString);
    }

    default <T extends Node> String nodeArrayToString(final Iterator<T> nodeIterator){
        return nodeArrayToString(", ", nodeIterator, Node::getString);
    }


    default <T extends Node> String nodeArrayToString(final Stream<T> nodeStream, final Function<T, String> toStringMethod){
        return nodeArrayToString(", ", nodeStream, toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final List<T> nodeList, final Function<T, String> toStringMethod){
        return nodeArrayToString(separator, nodeList.stream(), toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final List<T> nodeList, final Function<T, String> toStringMethod) {
        return nodeArrayToString(", ", nodeList, toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final T[] nodeArr, final Function<T, String> toStringMethod){
        return nodeArrayToString(separator, Arrays.stream(nodeArr), toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final T[] nodeArr, final Function<T, String> toStringMethod){
        return nodeArrayToString(", ", nodeArr, toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Iterator<T> nodeIterator, final Function<T, String> toStringMethod){
        return nodeArrayToString(separator, StreamSupport.stream(Spliterators.spliteratorUnknownSize(nodeIterator, 0), false), toStringMethod);
    }

    default <T extends Node> String nodeArrayToString(final Iterator<T> nodeIterator, final Function<T, String> toStringMethod){
        return nodeArrayToString(", ", nodeIterator, toStringMethod);
    }
}
