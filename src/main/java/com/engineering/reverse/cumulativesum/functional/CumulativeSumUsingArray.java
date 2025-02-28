package com.engineering.reverse.cumulativesum.functional;

import java.util.List;
import java.util.Objects;

public class CumulativeSumUsingArray {
    public static List<Long> compute(List<Long> inputList) {
        Objects.requireNonNull(inputList, "Input list can not be null");

        int inputSize = inputList.size();

        Long[] result = inputList
                .stream()
//                .parallel()
                .collect(
                        () -> new Long[inputSize],
                        (Long[] cumm, Long element) -> {
                            int index = availableIndex(cumm);
                            assert index >= 0;

                            cumm[index] = index > 0 ? element + cumm[index-1] : element;
                            },
                        (Long[] left, Long[] right) -> {
                            int leftIndex = availableIndex(left);
                            int rightIndex = availableIndex(right);
                            Long leftLastValue = left[leftIndex - 1];

                            for (int index = 0; index < rightIndex; index++) {
                                left[leftIndex + index] = leftLastValue + right[index];
                            }
                        }
                );

        return List.of(result);
    }

    private static int availableIndex(Long[] list) {
        Objects.requireNonNull(list, "Provided list to compute available index is null.");
        for(int index = 0; index < list.length; index++) {
            if((list[index] == null) || (list[index] == 0))
                return index;
        }
        return -1;
    }
}
