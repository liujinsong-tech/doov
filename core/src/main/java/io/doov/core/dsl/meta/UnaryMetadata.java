/*
 * Copyright 2017 Courtanet
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.doov.core.dsl.meta;

import static io.doov.core.dsl.meta.DefaultOperator.not;
import static io.doov.core.dsl.meta.MetadataType.UNARY_PREDICATE;

import java.util.Arrays;
import java.util.List;

import io.doov.core.dsl.lang.Context;
import io.doov.core.dsl.meta.ast.AstVisitorUtils;

public class UnaryMetadata extends PredicateMetadata {
    private final Operator operator;
    private final Metadata value;

    private UnaryMetadata(Operator operator, Metadata value) {
        this.operator = operator;
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public static UnaryMetadata notMetadata(Metadata value) {
        return new UnaryMetadata(DefaultOperator.not, value);
    }

    @Override
    public String readable() {
        return AstVisitorUtils.astToString(this);
    }

    @Override
    public void accept(MetadataVisitor visitor) {
        visitor.visit(this);
        value.accept(visitor);
    }

    @Override
    public List<Metadata> childs() {
        return Arrays.asList(value);
    }

    @Override
    public MetadataType type() {
        return UNARY_PREDICATE;
    }

    @Override
    public Metadata message(Context context) {
        if (operator == not)
            new UnaryMetadata(operator, value);
        return new UnaryMetadata(operator, value.message(context));
    }
}
