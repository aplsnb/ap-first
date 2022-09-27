package org.bang.ap.first.app.firstapp.lesson

import com.google.gson.Gson

// kotlin容器
fun main() {
    // kotlin数组
    // 1. 使用arrayOf创建数组，必须指定数组的元素，可以是任意类型
    val arrayNumber: Array<Int> = arrayOf(1, 2, 3, 4)

    // 集合中的元素可以是任意类型
    // kotlin中的Any等于java中的Object
    val arrayObjects: Array<Any?> = arrayOf(1, true, "2", Gson())

    // 2. 使用arrayOfNulls创建空数组
    // 创建一个指定大小的、所有元素都为空的数组，但必须指定集合中的元素类型
    // ?代表集合中的元素可为null
    val arrayOfNulls: Array<String?> = arrayOfNulls(5)
    arrayOfNulls[0] = "element1"
    arrayOfNulls[1] = "element2"
    arrayOfNulls[2] = "element3"
    arrayOfNulls[3] = "element4"
    arrayOfNulls[4] = null

    // 3. 利用array的构造函数动态创建数组
    // 创建一个Array<String>并初始化为["0"，"1"，"4"，"9"，"16"]
    // i = 0, 1, 2, 3, 4
    // i * i = "0"，"1"，"4"，"9"，"16"
    // 数组创建的时候，会循环5次，i就是数组的下标
    // -> 右边的表达式的结果，就是数组中当前的下标
    val asc: Array<String> = Array(5) { i -> (i * i).toString() }

    // 4. 字节数组
    val bytes = ByteArray(5)
    bytes[0] = 1

    // 5. 使用IntArray创建整型的数据数组
    // 1. 创建一个长度为5的空的IntArray
    // Byte、Short、Int、Long、Float、Double、Boolean、Char
    val ints = IntArray(5)
    ints[0] = 1

    // 3. 创建一个长度为5的IntArray并且里面每个元素都为100
    val intArr3 = IntArray(5) { 100 }

    // 4. 注意这里it是它索引下标值，所以这是创建一个长度为5的IntArray[0, 2, 4, 6, 8]
    val intArr4 = IntArray(5) { it * 2 } // it，lambda表达式专有变量，这里指的是数组的下标
    val intArr5 = IntArray(5) { i -> i * 2 } // 与上方的作用一样，但是上方的写法更加简洁

    // 数组如何进行for循环遍历
    // 依次取出数组中的元素，用for-in的形式
    for (item in intArr4) {
        println(item)
    }

    // 根据下标再取出对应位置的元素
    // for-in
    for (i in intArr4.indices) {
        println(i.toString() + "->" + intArr4[i])
    }

    // 同时遍历下标和元素
    for ((index, item) in intArr4.withIndex()) {
        println("$index -> $item")
    }

    // forEach会依次回调我们数组中的元素
    intArr4.forEach {
        println("forEach：$it") // it代表的是数组的元素
    }

    // forEach增强版，会依次回调给我们数组中的下标和元素
    intArr4.forEachIndexed { index, item ->
        println("$index -> $item")
    }
}
