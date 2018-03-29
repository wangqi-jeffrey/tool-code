package com.test;

import java.util.List;
import java.util.ListIterator;

public class BinarySearchTest {
	public int binarySearch(int[] data, int aim) {// 以int数组为例，aim为需要查找的数
		int start = 0;
		int end = data.length - 1;

		int mid = (start + end) / 2;// a
		while (data[mid] != aim && end > start) {// 如果data[mid]等于aim则死循环，所以排除
			if (data[mid] > aim) {
				end = mid - 1;
			} else if (data[mid] < aim) {
				start = mid + 1;
			}
			mid = (start + end) / 2;// b，注意a，b
		}
		return (data[mid] != aim) ? -1 : mid;// 返回结果
	}

	private static <T> int indexedBinarySearch(
			List<? extends Comparable<? super T>> list, T key) {
		int low = 0;
		int high = list.size() - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			Comparable<? super T> midVal = list.get(mid);
			int cmp = midVal.compareTo(key);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found
	}

	private static <T> int iteratorBinarySearch(
			List<? extends Comparable<? super T>> list, T key) {
		int low = 0;
		int high = list.size() - 1;
		ListIterator<? extends Comparable<? super T>> i = list.listIterator();

		/*while (low <= high) {
			int mid = (low + high) >>> 1;
			Comparable<? super T> midVal = get(i, mid);
			int cmp = midVal.compareTo(key);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid; // key found
		}*/
		return -(low + 1); // key not found
	}
}
