package io.doov.core.dsl.mapping;

import static io.doov.core.dsl.meta.MappingMetadata.mapping;
import static io.doov.core.dsl.meta.ast.AstVisitorUtils.astToString;

import java.util.Locale;

import io.doov.core.FieldModel;
import io.doov.core.dsl.DslField;
import io.doov.core.dsl.impl.DefaultContext;
import io.doov.core.dsl.impl.ModelInterceptor;
import io.doov.core.dsl.lang.*;
import io.doov.core.dsl.meta.*;

/**
 * 1-to-1 mapping rule
 *
 * @param <I> in type
 * @param <O> out type
 */
public class SimpleMappingRule<I, O> implements MappingRule {

    private final MappingMetadata metadata;
    private final DslField<I> inFieldInfo;
    private final DslField<O> outFieldInfo;
    private final TypeConverter<I, O> typeConverter;

    SimpleMappingRule(DslField<I> inFieldInfo, DslField<O> outFieldInfo, TypeConverter<I, O> typeConverter) {
        this.inFieldInfo = inFieldInfo;
        this.outFieldInfo = outFieldInfo;
        this.metadata = mapping(inFieldInfo, outFieldInfo);
        this.typeConverter = typeConverter;
    }

    @Override
    public String readable(Locale locale) {
        return astToString(this, locale);
    }

    @Override
    public void accept(MetadataVisitor visitor, int depth) {
        metadata.accept(visitor, depth);
        typeConverter.accept(visitor, depth);
    }

    @Override
    public boolean validate(FieldModel inModel, FieldModel outModel) {
        return inModel.getFieldInfos().stream().anyMatch(f -> f.id().equals(inFieldInfo.id()))
                && outModel.getFieldInfos().stream().anyMatch(f -> f.id().equals(outFieldInfo.id()));
    }

    @Override
    public <C extends Context> C executeOn(FieldModel inModel, FieldModel outModel, C context) {
        ModelInterceptor in = new ModelInterceptor(inModel, context);
        ModelInterceptor out = new ModelInterceptor(outModel, context);
        out.set(outFieldInfo.id(), typeConverter.convert(in, context, inFieldInfo));
        return context;
    }

    @Override
    public Context executeOn(FieldModel inModel, FieldModel outModel) {
        return this.executeOn(inModel, outModel, new DefaultContext(metadata));
    }

}