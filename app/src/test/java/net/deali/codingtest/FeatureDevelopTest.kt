package net.deali.codingtest

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class FeatureDevelopTest {

    @Test
    fun test() {
        Assert.assertEquals(Arrays.toString(intArrayOf(2, 1)), Arrays.toString(solution(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5))))
    }

    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val queue: LinkedList<Int> = LinkedList()
        val intArrayList = ArrayList<Int>()
        progresses.forEach {
            queue.add(it)
        }

        while (queue.size > 0) {
            speeds.forEachIndexed { index, i ->
                val realIndex = index - (speeds.size - queue.size)
                if (realIndex >= 0) {
                    queue[realIndex] = queue[realIndex] + i
                }
            }
            var n = 0
            while (queue.peek() ?: 0 >= 100) {
                queue.pop()
                ++n
            }
            if (n > 0) {
                intArrayList.add(n)
            }
        }

        return intArrayList.toIntArray()
    }

    @Test
    fun test2() {
        Assert.assertEquals(Arrays.toString(intArrayOf(2, 1)), Arrays.toString(solution2(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5))))
    }

    fun solution2(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = intArrayOf()

        var lastDay = 0
        var cnt = 0
        progresses
            .mapIndexed { idx, progress -> Pair(progress, speeds[idx].toDouble()) }
            .map { (100 - it.first) / it.second }
            .map { Math.ceil(it) }
            .map { it.toInt() }
            .forEach { curDay ->
                if (lastDay >= curDay) {
                    cnt++
                } else {
                    if (lastDay != 0)
                        answer = answer.plus(cnt)
                    lastDay = curDay
                    cnt = 1
                }
            }
        answer = answer.plus(cnt)

        return answer
    }

}