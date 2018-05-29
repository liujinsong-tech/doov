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
package io.doov.sample.validation;

import static io.doov.assertions.Assertions.assertThat;
import static io.doov.sample.field.dsl.DslSampleModel.accountCountry;
import static io.doov.sample.model.Country.*;

import java.util.Locale;

import org.junit.jupiter.api.*;

import io.doov.core.dsl.lang.Result;
import io.doov.sample.field.dsl.DslSampleModel;
import io.doov.sample.field.dsl.DslSampleModel.SampleModelRule;
import io.doov.sample.model.Country;
import io.doov.sample.model.SampleModel;

/**
 * Validate that a profile country is French or Canadian
 */
public class FailureCauseSample4Test {

    private final SampleModelRule rule = DslSampleModel.when(accountCountry.anyMatch(CAN, FR)).validate();

    private final Locale locale = Locale.FRENCH;
    private final SampleModel model = new SampleModel();

    @BeforeEach
    public void plaintText() {
        System.out.print(rule.readable(locale));
    }

    @AfterEach
    public void blankline() {
        System.out.println("");
    }

    @Test
    public void getFailureCause_setup_0() {
        Result result = rule.withShortCircuit(false).executeOn(model);
        assertThat(result).isFalse();
        System.out.println("> " + result.getFailureCause(locale));
    }

    @Test
    public void getFailureCause_setup_1() {
        model.getAccount().setCountry(UK);

        Result result = rule.withShortCircuit(false).executeOn(model);
        assertThat(result).isFalse();
        System.out.println("> " + result.getFailureCause(locale));
    }

    @Test
    public void getFailureCause_setup_2() {
        model.getAccount().setCountry(CAN);

        Result result = rule.withShortCircuit(false).executeOn(model);
        assertThat(result).isTrue();
        System.out.println("> " + result.getFailureCause(locale));
    }

}
