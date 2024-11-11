package org.lee.common.exception;

import org.lee.common.config.Conf;

public class BadConfigurationError extends InternalError{
    private final Conf name;
    private final String value;

    public BadConfigurationError(Conf name, String value) {
        super(ErrorCode.BAD_CONFIGURATION_ERROR, String.format("The configuration is invalid: Conf.%s = %s", name, value));
        this.name = name;
        this.value = value;
    }

    public Conf getConfName() {
        return name;
    }

    public String getConfValue() {
        return value;
    }
}
