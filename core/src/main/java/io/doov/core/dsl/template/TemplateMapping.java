/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package io.doov.core.dsl.template;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.doov.core.dsl.DslField;
import io.doov.core.dsl.lang.*;
import io.doov.core.dsl.mapping.MappingRegistry;
import io.doov.core.dsl.meta.Metadata;

public class TemplateMapping {

    public static class Map1<T1 extends DslField<?>> implements DSLBuilder {

        private final TemplateSpec.Template1<T1> template;
        private final MappingRule mappingRule;

        Map1(Function<T1, MappingRule> ruleFunction, TemplateSpec.Template1<T1> template) {
            this.template = template;
            this.mappingRule = ruleFunction.apply(template.param1.create());
        }

        public MappingRule bind(T1 p1) {
            template.param1.bind(p1);
            return mappingRule;
        }

        @Override
        public Metadata metadata() {
            return mappingRule.metadata();
        }
    }

    public static class Map2<T1 extends DslField<?>, T2 extends DslField<?>> implements DSLBuilder {

        private final TemplateSpec.Template2<T1, T2> template;
        private MappingRule mappingRule;

        Map2(BiFunction<T1, T2, MappingRule> ruleFunction, TemplateSpec.Template2<T1, T2> template) {
            this.template = template;
            this.mappingRule = ruleFunction.apply(template.param1.create(), template.param2.create());
        }

        public MappingRule bind(T1 p1, T2 p2) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            return mappingRule;
        }

        @Override
        public Metadata metadata() {
            return mappingRule.metadata();
        }
    }

    public static class Map3<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template3<T1, T2, T3> template;
        private MappingRule mappingRule;

        Map3(Function3<T1, T2, T3, MappingRule> ruleFunction, TemplateSpec.Template3<T1, T2, T3> template) {
            this.template = template;
            this.mappingRule = ruleFunction.apply(template.param1.create(),
                    template.param2.create(),
                    template.param3.create());
        }

        public MappingRule bind(T1 p1, T2 p2, T3 p3) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            return mappingRule;
        }

        @Override
        public Metadata metadata() {
            return mappingRule.metadata();
        }
    }

    public static class Map4<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>, T4 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template4<T1, T2, T3, T4> template;
        private MappingRule mappingRule;

        Map4(Function4<T1, T2, T3, T4, MappingRule> ruleFunction, TemplateSpec.Template4<T1, T2, T3, T4> template) {
            this.template = template;
            this.mappingRule = ruleFunction.apply(template.param1.create(), template.param2.create(),
                    template.param3.create(), template.param4.create());
        }

        public MappingRule bind(T1 p1, T2 p2, T3 p3, T4 p4) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            template.param4.bind(p4);
            return mappingRule;
        }

        @Override
        public Metadata metadata() {
            return mappingRule.metadata();
        }
    }

    public static class Map5<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>, T4 extends DslField<?>, T5 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template5<T1, T2, T3, T4, T5> template;
        private MappingRule mappingRule;

        Map5(Function5<T1, T2, T3, T4, T5, MappingRule> ruleFunction,
                TemplateSpec.Template5<T1, T2, T3, T4, T5> template) {
            this.template = template;
            this.mappingRule = ruleFunction.apply(template.param1.create(), template.param2.create(),
                    template.param3.create(), template.param4.create(), template.param5.create());
        }

        public MappingRule bind(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            template.param4.bind(p4);
            template.param5.bind(p5);
            return mappingRule;
        }

        @Override
        public Metadata metadata() {
            return mappingRule.metadata();
        }
    }

    public static class Registry1<T1 extends DslField<?>> implements DSLBuilder {

        private final TemplateSpec.Template1<T1> template;
        private final MappingRegistry mappingRegistry;

        Registry1(Function<T1, MappingRegistry> registryFunction, TemplateSpec.Template1<T1> template) {
            this.template = template;
            mappingRegistry = registryFunction.apply(template.param1.create());
        }

        public MappingRegistry bind(T1 p1) {
            template.param1.bind(p1);
            return mappingRegistry;
        }

        @Override
        public Metadata metadata() {
            return mappingRegistry.metadata();
        }
    }

    public static class Registry2<T1 extends DslField<?>, T2 extends DslField<?>> implements DSLBuilder {

        private final TemplateSpec.Template2<T1, T2> template;
        private final MappingRegistry mappingRegistry;

        Registry2(BiFunction<T1, T2, MappingRegistry> registryFunction, TemplateSpec.Template2<T1, T2> template) {
            this.template = template;
            mappingRegistry = registryFunction.apply(template.param1.create(), template.param2.create());
        }

        public MappingRegistry bind(T1 p1, T2 p2) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            return mappingRegistry;
        }

        @Override
        public Metadata metadata() {
            return mappingRegistry.metadata();
        }
    }

    public static class Registry3<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template3<T1, T2, T3> template;
        private final MappingRegistry mappingRegistry;

        Registry3(
                Function3<T1, T2, T3, MappingRegistry> registryFunction,
                TemplateSpec.Template3<T1, T2, T3> template) {
            this.template = template;
            mappingRegistry = registryFunction.apply(template.param1.create(),
                    template.param2.create(), template.param3.create());
        }

        public MappingRegistry bind(T1 p1, T2 p2, T3 p3) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            return mappingRegistry;
        }

        @Override
        public Metadata metadata() {
            return mappingRegistry.metadata();
        }
    }

    public static class Registry4<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>, T4 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template4<T1, T2, T3, T4> template;
        private final MappingRegistry mappingRegistry;

        Registry4(Function4<T1, T2, T3, T4, MappingRegistry> registryFunction,
                TemplateSpec.Template4<T1, T2, T3, T4> template) {
            this.template = template;
            mappingRegistry = registryFunction.apply(template.param1.create(), template.param2.create(),
                    template.param3.create(), template.param4.create());
        }

        public MappingRegistry bind(T1 p1, T2 p2, T3 p3, T4 p4) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            template.param4.bind(p4);
            return mappingRegistry;
        }

        @Override
        public Metadata metadata() {
            return mappingRegistry.metadata();
        }
    }

    public static class Registry5<T1 extends DslField<?>, T2 extends DslField<?>, T3 extends DslField<?>, T4 extends DslField<?>, T5 extends DslField<?>>
            implements DSLBuilder {

        private final TemplateSpec.Template5<T1, T2, T3, T4, T5> template;
        private final MappingRegistry mappingRegistry;

        Registry5(Function5<T1, T2, T3, T4, T5, MappingRegistry> registryFunction,
                TemplateSpec.Template5<T1, T2, T3, T4, T5> template) {
            this.template = template;
            mappingRegistry = registryFunction.apply(template.param1.create(), template.param2.create(),
                    template.param3.create(), template.param4.create(), template.param5.create());
        }

        public MappingRegistry bind(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5) {
            template.param1.bind(p1);
            template.param2.bind(p2);
            template.param3.bind(p3);
            template.param4.bind(p4);
            template.param5.bind(p5);
            return mappingRegistry;
        }

        @Override
        public Metadata metadata() {
            return mappingRegistry.metadata();
        }
    }
}