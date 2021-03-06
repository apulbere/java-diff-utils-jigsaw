/*-
 * #%L
 * java-diff-utils
 * %%
 * Copyright (C) 2009 - 2017 java-diff-utils
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */
package com.github.difflib.algorithm;

import com.github.difflib.patch.Patch;

import java.util.Arrays;
import java.util.List;

/**
 * The general interface for computing diffs between two lists of elements of type T.
 *
 * @author <a href="dm.naumenko@gmail.com">Dmitry Naumenko</a>
 * @param T The type of the compared elements in the 'lines'.
 */
public interface DiffAlgorithm<T> {

    /**
     * Computes the difference between the original sequence and the revised sequence and returns it
     * as a {@link Patch} object.
     *
     * @param original The original sequence. Must not be {@code null}.
     * @param revised The revised sequence. Must not be {@code null}.
     * @return The patch representing the diff of the given sequences. Never {@code null}.
     */
    public default List<Change> diff(T[] original, T[] revised) throws DiffException {
        return diff(Arrays.asList(original), Arrays.asList(revised));
    }

    /**
     * Computes the difference between the original sequence and the revised sequence and returns it
     * as a {@link Patch} object.
     *
     * @param original The original sequence. Must not be {@code null}.
     * @param revised The revised sequence. Must not be {@code null}.
     * @return The patch representing the diff of the given sequences. Never {@code null}.
     */
    public List<Change> diff(List<T> original, List<T> revised) throws DiffException;
}
