package net.deali.codingtest

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap

class BestAlbum {

    @Test
    fun testSolution() {
        Assert.assertEquals(Arrays.toString(intArrayOf(4, 1, 3, 0)), Arrays.toString(solution2(arrayOf("classic", "pop", "classic", "classic", "pop"), intArrayOf(500, 600, 150, 800, 2500))))
    }

    class Music(val id: Int, val genre: String, val count: Int)

    private fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val musicList = arrayListOf<Music>()
        val genreTotalCountHashMap = HashMap<String, Int>()
        val ids = arrayListOf<Int>()

        genres.forEachIndexed { index, key ->
            musicList.add(Music(id = index, genre = key, count = plays[index]))
            genreTotalCountHashMap[key] = genreTotalCountHashMap.getOrDefault(key, 0) + plays[index]
        }

        genreTotalCountHashMap.toList().sortedByDescending {//토탈 카운트 기준으로 내림차순 정렬
            it.second
        }.map {//id 로 맵
            it.first
        }.forEach {// classic , pop
            musicList.filter { music ->
                music.genre == it
            }.sortedByDescending {
                it.count
            }.map {
                it.id
            }.take(2).let {
                ids.addAll(it)
            }
        }
        return ids.toIntArray()
    }

    private fun solution2(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices //0 ~ geners-1  IntRange 변환
            .groupBy { int ->
                genres[int]
            } // Map 으로 변환 key : genres[int] (ex : "classic", "pop")  value :  Integer List (고유 번호) 변환
            .toList() //List<Pair> 변환  Pair는 first : 키와 second : value
            .sortedByDescending {
            //List<Pair> 어떤 장르가 앞으로 갈지  sum 이 큰 순으로 리스트 정렬
                it.second.sumOf { id -> plays[id] }
            }
            .map {
                it.second.sortedByDescending { plays[it] }.take(2)
            }
            //Descending Sort 한 리스트 중 앞에 2개를 가져온  List<List<Int>>
            .flatten() //하나의 List<Int>
            .toIntArray() // IntArray 변환
    }

}
