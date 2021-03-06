package io.doov.sample.validation;

import static io.doov.assertions.Assertions.assertThat;
import static io.doov.core.dsl.lang.ReduceType.FAILURE;
import static io.doov.core.dsl.lang.ReduceType.SUCCESS;
import static io.doov.sample.field.dsl.DslSampleModel.accountCompany;
import static io.doov.sample.field.dsl.DslSampleModel.when;
import static io.doov.sample.model.Company.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.doov.core.dsl.lang.Result;
import io.doov.sample.field.dsl.DslSampleModel.SampleModelRule;
import io.doov.sample.model.*;

public class RulesReductionTest {
    SampleModel sample = SampleModels.sample();
    Result result;

    @Test
    public void test_account() {
        SampleModelRule demoRule = when(accountCompany.noneMatch(DAILYMOTION, BLABLACAR))
                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isTrue();
    }

    @Test
    public void test_account_KO() {
        sample.getAccount().setCompany(DAILYMOTION);
        SampleModelRule demoRule = when(accountCompany.noneMatch(DAILYMOTION, BLABLACAR))
                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isFalse();
    }

    @Test
    public void test_account_2() {
        SampleModelRule demoRule = when(accountCompany.notEq(DAILYMOTION)
                .and(accountCompany.notEq(BLABLACAR)))
                        .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isTrue();
    }

    @Test
    public void test_account_2_KO() {
        sample.getAccount().setCompany(DAILYMOTION);
        SampleModelRule demoRule = when(accountCompany.notEq(DAILYMOTION)
                .and(accountCompany.notEq(BLABLACAR)))
                        .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isFalse();
    }

    @Test
    public void test_account_3() {
        SampleModelRule demoRule = when(accountCompany.eq(DAILYMOTION)
                .or(accountCompany.eq(BLABLACAR)).not())
                        .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isTrue();
    }

    @Test
    public void test_account_3_KO() {
        sample.getAccount().setCompany(DAILYMOTION);
        SampleModelRule demoRule = when(accountCompany.eq(DAILYMOTION)
                .or(accountCompany.eq(BLABLACAR)).not())
                        .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isFalse();
    }

    @Test
    public void test_account_4() {
        SampleModelRule demoRule = when(accountCompany.anyMatch(LES_FURETS, CANAL_PLUS, MEETIC, OODRIVE))
                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isTrue();
    }

    @Test
    public void test_account_4_KO() {
        sample.getAccount().setCompany(DAILYMOTION);
        SampleModelRule demoRule = when(accountCompany.anyMatch(LES_FURETS, CANAL_PLUS, MEETIC, OODRIVE))
                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isFalse();
    }

    @Test
    public void test_account_5() {
        SampleModelRule demoRule = when(
                accountCompany.eq(LES_FURETS).or(accountCompany.eq(CANAL_PLUS)
                        .or(accountCompany.eq(MEETIC).or(accountCompany.eq(OODRIVE)))))
                                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isTrue();
    }

    @Test
    public void test_account_5_KO() {
        sample.getAccount().setCompany(DAILYMOTION);
        SampleModelRule demoRule = when(
                accountCompany.eq(LES_FURETS).or(accountCompany.eq(CANAL_PLUS)
                        .or(accountCompany.eq(MEETIC).or(accountCompany.eq(OODRIVE)))))
                                .validate();
        result = demoRule.withShortCircuit(false).executeOn(sample);
        assertThat(result).isFalse();
    }

    @AfterEach
    void afterEach() {
        System.out.println("SUCCESS> " + result.reduce(SUCCESS));
        System.out.println("FAILURE> " + result.reduce(FAILURE));
    }
}
