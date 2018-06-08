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
package io.doov.core.dsl.meta;

import java.util.Locale;

import io.doov.core.dsl.lang.Readable;

public interface SyntaxTree extends Readable {

    @Override
    default String readable() {
        return readable(Locale.getDefault());
    }

    /**
     * Returns the human readable version of this object.
     *
     * @param locale the locale to use
     * @return the readable string
     * @see #readable()
     */
    String readable(Locale locale);

    void accept(MetadataVisitor visitor, int depth);

}
