module com.github.difflib.histogram {
    requires com.github.difflib.api;
    requires org.eclipse.jgit;
    provides com.github.difflib.algorithm.DiffAlgorithm
        with com.github.difflib.histogram.HistogramDiff;
}