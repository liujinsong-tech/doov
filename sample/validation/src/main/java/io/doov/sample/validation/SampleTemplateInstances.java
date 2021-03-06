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

import static io.doov.sample.field.dsl.DslSampleModel.*;
import static io.doov.sample.validation.SampleTemplates.*;

import java.io.*;
import java.util.*;

import io.doov.core.dsl.lang.ValidationRule;

public class SampleTemplateInstances {

    public static final ValidationRule RULE_EMAIL = T_EMAIL.bind(accountEmail);

    public static final ValidationRule RULE_ACCOUNT = T_ACCOUNT
            .bind(userBirthdate, accountEmail, configurationMaxEmailSize, accountCountry, accountPhoneNumber);

    public static final ValidationRule RULE_ACCOUNT_2 = T_ACCOUNT_2
            .bind(userBirthdate, accountEmail, configurationMaxEmailSize, accountCountry, accountPhoneNumber);

    public static final ValidationRule RULE_USER = T_USER.bind(userFirstName, userLastName)
            .withShortCircuit(false);

    public static final ValidationRule RULE_USER_2 = T_USER_2
            .bind(accountPhoneNumber, accountPhoneNumber, accountEmail);

    public static final ValidationRule RULE_USER_ADULT = T_USER_ADULT.bind(userBirthdate, accountCreationDate);

    public static final ValidationRule RULE_USER_ADULT_FIRSTDAY = T_USER_ADULT_FIRSTDAY
            .bind(userBirthdate, accountCreationDate);

    public static final ValidationRule RULE_FIRST_NAME = T_FIRST_NAME.bind(userFirstName);

    public static final ValidationRule RULE_ID = T_ID.bind(userId);

    public static final ValidationRule RULE_AGE = T_AGE.bind(userBirthdate);

    public static final ValidationRule RULE_AGE_2 = T_AGE_2.bind(userBirthdate);

    public static final ValidationRule RULE_SUM = TEMPLATE_SUM.bind(configurationMinAge, configurationMaxEmailSize);

    public static final ValidationRule RULE_MIN = TEMPLATE_MIN.bind(configurationMinAge, configurationMaxEmailSize);

    public static final ValidationRule RULE_DOUBLE_LAMBDA = TEMPLATE_DOUBLE_LAMBDA.bind(favoriteSiteName1);

    public static final ValidationRule RULE_BORN_1980 = TEMPLATE_BORN_1980.bind(userBirthdate);

    public static final ValidationRule RULE_ACCOUNT_TIME_CONTAINS = TEMPLATE_ACCOUNT_TIME_CONTAINS
            .bind(accountTimezone);

    public static final ValidationRule RULE_COMPANY_NOT_BLABLA = TEMPLATE_COMPANY_NOT_BLABLA.bind(accountCompany);

    public static List<ValidationRule> rules() {
        return Arrays.asList(
                RULE_EMAIL,
                RULE_ACCOUNT,
                RULE_ACCOUNT_2,
                RULE_USER,
                RULE_USER_2,
                RULE_USER_ADULT,
                RULE_USER_ADULT_FIRSTDAY,
                RULE_FIRST_NAME,
                RULE_ID,
                RULE_AGE,
                RULE_AGE_2,
                RULE_SUM,
                RULE_MIN,
                RULE_DOUBLE_LAMBDA,
                RULE_BORN_1980,
                RULE_ACCOUNT_TIME_CONTAINS,
                RULE_COMPANY_NOT_BLABLA);
    }

    public static void main(String[] args) throws IOException {
        SampleWriter.of("validation_template_instances.html", Locale.FRANCE).write(rules());
        System.exit(1);
    }
}
