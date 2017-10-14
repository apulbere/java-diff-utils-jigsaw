/*
 * Copyright 2017 java-diff-utils.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.difflib.histogram;

import com.github.difflib.algorithm.Change;
import com.github.difflib.algorithm.DiffAlgorithm;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.DeltaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.diff.Sequence;
import org.eclipse.jgit.diff.SequenceComparator;

/**
 * HistorgramDiff using JGit - Library. This one is much more performant than the
 * orginal Myers implementation.
 *
 * @author toben
 */
public class HistogramDiff<T> implements DiffAlgorithm<T> {

    @Override
    public List<Change> diff(List<T> original, List<T> revised) throws DiffException {
        Objects.requireNonNull(original, "original list must not be null");
        Objects.requireNonNull(revised, "revised list must not be null");
        EditList diffList = new EditList();
        diffList.addAll(new org.eclipse.jgit.diff.HistogramDiff().diff(new DataListComparator<>(), new DataList<>(original), new DataList<>(revised)));
        List<Change> patch = new ArrayList<>();
        for (Edit edit : diffList) {
            DeltaType type = DeltaType.EQUAL;
            switch (edit.getType()) {
                case DELETE:
                    type = DeltaType.DELETE;
                    break;
                case INSERT:
                    type = DeltaType.INSERT;
                    break;
                case REPLACE:
                    type = DeltaType.CHANGE;
                    break;
            }
            patch.add(new Change(type,edit.getBeginA(), edit.getEndA(), edit.getBeginB(), edit.getEndB()));
        }
        return patch;
    }
}

class DataListComparator<T> extends SequenceComparator<DataList<T>> {

    @Override
    public boolean equals(DataList<T> original, int orgIdx, DataList<T> revised, int revIdx) {
        return original.data.get(orgIdx).equals(revised.data.get(revIdx));
    }

    @Override
    public int hash(DataList<T> s, int i) {
        return s.data.get(i).hashCode();
    }

}

class DataList<T> extends Sequence {

    final List<T> data;

    public DataList(List<T> data) {
        this.data = data;
    }

    @Override
    public int size() {
        return data.size();
    }
}