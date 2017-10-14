package com.github.difflib.ui;

import com.github.difflib.algorithm.DiffAlgorithm;

import java.util.ServiceLoader;

/**
 * Created by apulbere on 10/14/17.
 */
public class Main {

    public static void main(String[] args) {
        ServiceLoader<DiffAlgorithm> loader = ServiceLoader.load(DiffAlgorithm.class);
        if (!loader.iterator().hasNext()) {
            System.out.println("Alas, I have no algorithms!");
        }

        for(DiffAlgorithm diffAlgorithm: loader) {
            System.out.println(diffAlgorithm.getClass().getSimpleName());
        }
    }
}
