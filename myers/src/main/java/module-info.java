module com.github.difflib.myers {
    requires com.github.difflib.api;
    provides com.github.difflib.algorithm.DiffAlgorithm
        with com.github.difflib.myers.MyersDiff;
}