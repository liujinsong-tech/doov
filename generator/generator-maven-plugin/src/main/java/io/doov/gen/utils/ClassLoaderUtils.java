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
package io.doov.gen.utils;

import static java.lang.Thread.currentThread;

import java.io.File;
import java.net.*;
import java.util.function.Function;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

public class ClassLoaderUtils {

    public static URLClassLoader getUrlClassLoader(MavenProject project) throws MojoFailureException {
        try {
            // collect compile classpath classes
            URL[] urls = project.getCompileClasspathElements().stream()
                    .map(File::new)
                    .filter(File::exists)
                    .map(new FileToUrl()).toArray(URL[]::new);

            return new URLClassLoader(urls, currentThread().getContextClassLoader());
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoFailureException(e.getMessage(), e);
        }
    }

    private static final class FileToUrl implements Function<File, URL> {

        @Override
        public URL apply(File file) {
            try {
                return file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
