/*
 * Copyright 2017 Courtanet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.doov.core.dsl.meta.predicate;

import static io.doov.core.dsl.meta.DefaultOperator.always_false;
import static io.doov.core.dsl.meta.DefaultOperator.always_true;
import static io.doov.core.dsl.meta.DefaultOperator.lambda;
import static io.doov.core.dsl.meta.MetadataType.FIELD_PREDICATE;
import static io.doov.core.dsl.meta.MetadataType.LEAF_PREDICATE;
import static io.doov.core.dsl.meta.MetadataType.LEAF_VALUE;
import static io.doov.core.dsl.meta.MetadataType.TEMPLATE_IDENTIFIER;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import io.doov.core.dsl.lang.Readable;
import io.doov.core.dsl.meta.LeafMetadata;
import io.doov.core.dsl.meta.MetadataType;

public class ValuePredicateMetadata<M extends ValuePredicateMetadata<M>> extends LeafMetadata<M>
        implements PredicateMetadata {

    private final AtomicInteger evalTrue = new AtomicInteger();
    private final AtomicInteger evalFalse = new AtomicInteger();

    public ValuePredicateMetadata(MetadataType type) {
        super(type);
    }

    @Override
    public AtomicInteger evalTrue() {
        return evalTrue;
    }

    @Override
    public AtomicInteger evalFalse() {
        return evalFalse;
    }

    // boolean

    public static <M extends ValuePredicateMetadata<M>> M trueMetadata() {
        return new ValuePredicateMetadata<M>(LEAF_PREDICATE).operator(always_true);
    }

    public static <M extends ValuePredicateMetadata<M>> M falseMetadata() {
        return new ValuePredicateMetadata<M>(LEAF_PREDICATE).operator(always_false);
    }

    // values

    public static <M extends ValuePredicateMetadata<M>> M valueMetadata(Supplier<?> value) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueSupplier(value);
    }

    public static <M extends ValuePredicateMetadata<M>> M valueMetadata(Object value) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueObject(value);
    }

    public static <M extends ValuePredicateMetadata<M>> M readableMetadata(Readable readable) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueReadable(readable);
    }

    public static <M extends ValuePredicateMetadata<M>> M readableMetadata(Readable... readables) {
        ValuePredicateMetadata<M> metadata = new ValuePredicateMetadata<>(LEAF_VALUE);
        for (Readable readable : readables) {
            metadata.valueReadable(readable);
        }
        return (M) metadata;
    }

    public static <M extends ValuePredicateMetadata<M>> M stringMetadata(String value) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueString(value);
    }

    public static <M extends ValuePredicateMetadata<M>> M stringMetadata(String value, String value2) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueString(value).valueString(value2);
    }

    public static <M extends ValuePredicateMetadata<M>> M unknownMetadata(String value) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueUnknown(value);
    }

    // list values

    public static <M extends ValuePredicateMetadata<M>> M valueListMetadata(Collection<?> values) {
        return new ValuePredicateMetadata<M>(LEAF_VALUE).valueListObject(values);
    }

    // any match

    // all match

    public static <M extends ValuePredicateMetadata<M>> M allMatchMetadata(String... readables) {
        return new ValuePredicateMetadata<M>(FIELD_PREDICATE)
                .valueReadable(lambda)
                .valueListObject(Arrays.asList(readables));
    }

    // none match

    public static <M extends ValuePredicateMetadata<M>> M noneMatchMetadata(String... readables) {
        return new ValuePredicateMetadata<M>(FIELD_PREDICATE)
                .valueReadable(lambda)
                .valueListObject(Arrays.asList(readables));
    }

    public static <M extends ValuePredicateMetadata<M>> M templateParam(String parameterIdentifier) {
        return new ValuePredicateMetadata<M>(TEMPLATE_IDENTIFIER).valueReadable(() -> parameterIdentifier);
    }

}
