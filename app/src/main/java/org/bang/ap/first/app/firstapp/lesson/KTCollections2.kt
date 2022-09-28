package org.bang.ap.first.app.firstapp.lesson

import androidx.collection.arrayMapOf

/**
 * // kotlin集合
 * List：是一个有序列表，可通过索引（下标）访问元素。元素可以在list中出现多次、元素可重复。
 * Set：是元素唯一的集合。一般来说set中元素的顺序并不重要、无序集合。
 * Map：（字典）是一组键值对。键是唯一的，每个键都刚好映射到一个值，值可以重复。
 * */
fun main() {
    // 列表的创建方式——可变列表
    val arrayString = mutableListOf<String>()
    arrayString.add("1")
    arrayString.add("2")
    arrayString.add("3")
    arrayString.add("4")
    arrayString.add(3, "3")

    val arrayString2 = mutableListOf("1", "2", "3", "3")
    arrayString2.add("4")

    // 列表的创建方式——不可变列表
    // 必须指定元素类型
    // 必须指定初始化数据元素 不可变
    val arrayInt = listOf(1, 2, 3)
//    arrayInt.add
//    arrayInt.remove

    // map字典的创建——可变字典
    // （字典）是一组键值对。键是唯一的，每个键都刚好映射到一个值，值可以重复。
    val array = arrayMapOf(Pair("key", "value"))
    val arrayMap = mutableMapOf<String, String>()
    arrayMap["1"] = "1"
    arrayMap["2"] = "2"
    arrayMap["3"] = "3"
    arrayMap["3"] = "4" // 此时，会覆盖上面的记录

    // map字典的创建——使用Pair指定集合中初始化的元素
    val arrayMap2 = mutableMapOf(Pair("key", "value"))

    // map字典的创建——不可变字典，不可动态添加、删除元素
    val arrayMap3 = mapOf(Pair("key", "value"))
    val arrayMap4 = mapOf<String, String>()

    // set集合的创建——可变集合，元素唯一
    val set = mutableSetOf<Int>()
    set.add(1)
    set.add(2)
    set.add(3)
    set.add(3) // 添加不进去
    for (item in set) {
        println(item)
    }

    val set2 = mutableSetOf(1, 2, 3)
    set2.add(1)
    set2.add(2)
    set2.forEach {
        println(it)
    }

    // set集合的创建——不可变集合，元素唯一
    val set3 = setOf(1, 2, 3)
//    set3.add

    // 下面的方法同样适用于set，map，list
    val arrayExamples = mutableListOf("1", "2", "3", "4")
    println("isEmpty：${arrayExamples.isEmpty()}")
    println("contains：${arrayExamples.contains("6")}")
    println("containsAll：${arrayExamples.containsAll(listOf("1", "2"))}")
    println("get：${arrayExamples[0]}")
    println("indexOf：${arrayExamples.indexOf("6")}") // 判断元素在集合中的下标位置
    println("lastIndexOf：${arrayExamples.lastIndexOf("5")}") // 判断元素在集合中的下标位置
    val iterator = arrayExamples.iterator()
    iterator.forEach {
        println("it：$it")
    }
//    arrayExamples.clear() // 集合中的元素被清除
    println("length：${arrayExamples.size}")
    arrayExamples[0] = "0" // 修改下标对应的元素，index不能超过集合size的大小
    arrayExamples.add("7") // 指定位置添加元素
    arrayExamples.removeAt(0)

    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.reverse()
    numbers.forEach {
        println("reverse：$it")
    }

    numbers.shuffle() // 随机排列元素
    numbers.forEach {
        println("shuffle：$it")
    }

    numbers.sort() // 排序，从小到大排序
    numbers.forEach {
        println("sort：$it")
    }

    numbers.sortDescending() // 排序，从大到小排序
    numbers.forEach {
        println("sortDescending：$it")
    }
}
