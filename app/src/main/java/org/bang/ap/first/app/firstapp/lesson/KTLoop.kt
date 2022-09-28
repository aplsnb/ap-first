package org.bang.ap.first.app.firstapp.lesson

// kotlin循环
fun main() {
    val items = listOf("java", "kotlin", "android")
    // for-in
    for (item in items) {
        println(item)
    }

    // forEach
    items.forEach {
        println("forEach:$it")
    }

    // forEachIndexed
    items.forEachIndexed { index, item ->
        println("forEachIndexed:$index,$item")
    }

    // while循环
    var index = 0
    while (index < items.size) { // while循环的遍历方式
        println("the $index element is ${items[index++]}")
    }

    // do-while循环
    index = 0
    do {
        println("the $index element is ${items[index++]}")
    } while (index < items.size)

    // 区间迭代器
    // 遍历区间，注意kotlin的区间的包含也是闭合的[1, 10]
    for (i in 1..10) {
        println("$i")
    }

    // until不包含最后一个元素[1, 10)
    for (i in 1 until 10) {
        println("$i")
    }

    // 是指从10...1，step步长为2
    for (i in 10 downTo 1 step 2) {
        println("$i")
    }

    // continue跳过本次循环继续下一次循环/break终止循环
    for (i in 0..10) {
        if (i >= 5) {
            break
        }
        if (i == 2) {
            continue
        }
        println("$i")
    }
}
